package com.shermatov.ecommerce.authservice.service.impl;

import com.shermatov.ecommerce.authservice.dto.RegisterUserDto;
import com.shermatov.ecommerce.authservice.entity.UserEntity;
import com.shermatov.ecommerce.authservice.exception.EmailAlreadyExistException;
import com.shermatov.ecommerce.authservice.model.UserResponse;
import com.shermatov.ecommerce.authservice.repository.UserRepository;
import com.shermatov.ecommerce.authservice.service.UsersService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public record UsersServiceImpl(UserRepository userRepository) implements UsersService {

    @Override
    public Flux<UserResponse> getUsers() {
        return userRepository.findAll()
                .map(userEntity -> UserResponse.builder()
                        .userId(userEntity.getUserId())
                        .username(userEntity.getUsername())
                        .email(userEntity.getEmail())
                        .build());
    }

    @Override
    public Mono<UserResponse> createUser(RegisterUserDto registerUserDto) {
        return userRepository.findByEmail(registerUserDto.getEmail())
                .flatMap(t -> Mono.error(new EmailAlreadyExistException()))
                .switchIfEmpty(userRepository.save(
                        UserEntity.builder()
                                .userId(null)
                                .username(registerUserDto.getUsername())
                                .email(registerUserDto.getEmail())
                                .password(registerUserDto.getPassword())
                                .build()
                ))
                .map(UserEntity.class::cast)
                .map(createdUser -> UserResponse.builder()
                        .userId(createdUser.getUserId())
                        .username(createdUser.getUsername())
                        .email(createdUser.getEmail())
                        .build());
    }


}
