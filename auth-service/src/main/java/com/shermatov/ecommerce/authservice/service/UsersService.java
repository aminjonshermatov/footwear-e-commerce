package com.shermatov.ecommerce.authservice.service;

import com.shermatov.ecommerce.authservice.dto.RegisterUserDto;
import com.shermatov.ecommerce.authservice.model.UserResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UsersService {

    Flux<UserResponse> getUsers();
    Mono<UserResponse> createUser(RegisterUserDto registerUserDto);

}
