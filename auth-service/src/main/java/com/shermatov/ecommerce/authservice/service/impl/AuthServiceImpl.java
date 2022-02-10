package com.shermatov.ecommerce.authservice.service.impl;

import com.shermatov.ecommerce.authservice.dto.LoginUserDto;
import com.shermatov.ecommerce.authservice.dto.RegisterUserDto;
import com.shermatov.ecommerce.authservice.entity.RoleEntity;
import com.shermatov.ecommerce.authservice.entity.UserEntity;
import com.shermatov.ecommerce.authservice.entity.UserToRoleEntity;
import com.shermatov.ecommerce.authservice.exception.CredentialsIsNotValidException;
import com.shermatov.ecommerce.authservice.exception.EmailAlreadyExistException;
import com.shermatov.ecommerce.authservice.model.UserResponse;
import com.shermatov.ecommerce.authservice.repository.RoleRepository;
import com.shermatov.ecommerce.authservice.repository.UserRepository;
import com.shermatov.ecommerce.authservice.repository.UserToRoleRepository;
import com.shermatov.ecommerce.authservice.service.AuthService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public record AuthServiceImpl(UserRepository userRepository,
                              RoleRepository roleRepository,
                              UserToRoleRepository userToRoleRepository,
                              BCryptPasswordEncoder passwordEncoder) implements AuthService {

    static String DEFAULT_ROLE = "USER";

    @Override
    public Mono<UserResponse> register(RegisterUserDto registerUserDto) {
        return userRepository.findByEmail(registerUserDto.getEmail())
                .flatMap(__ -> Mono.error(new EmailAlreadyExistException()))
                .switchIfEmpty(Mono.defer(() -> userRepository.save(UserEntity.builder()
                                .email(registerUserDto.getEmail())
                                .username(registerUserDto.getUsername())
                                .password(passwordEncoder.encode(registerUserDto.getPassword()))
                                .build())
                        .flatMap(userEntity -> roleRepository.findByRoleName(DEFAULT_ROLE)
                                .flatMap(roleEntity -> userToRoleRepository.save(UserToRoleEntity.builder()
                                                .userId(userEntity.getUserId())
                                                .roleId(roleEntity.getRoleId())
                                                .build()
                                        )
                                )
                                .map(userToRoleEntity -> UserResponse.builder()
                                        .userId(userEntity.getUserId())
                                        .build())
                        )))
                .cast(UserResponse.class);
    }

    @Override
    public Mono<UserResponse> login(LoginUserDto loginUserDto) {
        return userRepository.findByEmailAndPassword(loginUserDto.getEmail(), loginUserDto.getPassword())
                .map(userEntity -> UserResponse.builder()
                        .userId(userEntity.getUserId())
                        .username(userEntity.getUsername())
                        .email(userEntity.getEmail())
                        .build())
                .map(userResponse -> {
                    userResponse.setRoles(userToRoleRepository.findByUserId(userResponse.getUserId())
                            .flatMap(userToRoleEntity -> roleRepository.findById(userToRoleEntity.getRoleId())
                                    .map(RoleEntity::getRoleName)));

                    return userResponse;
                })
                .switchIfEmpty(Mono.error(new CredentialsIsNotValidException()));
    }
}
