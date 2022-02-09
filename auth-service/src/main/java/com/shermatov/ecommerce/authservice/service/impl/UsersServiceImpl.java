package com.shermatov.ecommerce.authservice.service.impl;

import com.shermatov.ecommerce.authservice.dto.CreteUserDto;
import com.shermatov.ecommerce.authservice.entity.UserEntity;
import com.shermatov.ecommerce.authservice.exception.EmailAlreadyExistException;
import com.shermatov.ecommerce.authservice.model.UserResponse;
import com.shermatov.ecommerce.authservice.repository.UserRepository;
import com.shermatov.ecommerce.authservice.service.UsersService;
import org.springframework.beans.BeanUtils;
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
                        .firstName(userEntity.getFirstName())
                        .email(userEntity.getEmail())
                        .build());
    }

    @Override
    public Mono<UserResponse> createUser(CreteUserDto creteUserDto) {
        return userRepository.findByEmail(creteUserDto.getEmail())
                .flatMap(t -> Mono.error(new EmailAlreadyExistException()))
                .switchIfEmpty(userRepository.save(
                        UserEntity.builder()
                                .userId(null)
                                .firstName(creteUserDto.getFirstName())
                                .email(creteUserDto.getEmail())
                                .password(creteUserDto.getPassword())
                                .build()
                ))
                .map(UserEntity.class::cast)
                .map(createdUser -> UserResponse.builder()
                        .userId(createdUser.getUserId())
                        .firstName(createdUser.getFirstName())
                        .email(createdUser.getEmail())
                        .build());
    }


}
