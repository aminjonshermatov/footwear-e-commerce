package com.shermatov.ecommerce.authservice.service;

import com.shermatov.ecommerce.authservice.dto.LoginUserDto;
import com.shermatov.ecommerce.authservice.dto.RegisterUserDto;
import com.shermatov.ecommerce.authservice.model.UserResponse;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

public interface AuthService {

    Mono<UserResponse> register(RegisterUserDto registerUserDto);
    Mono<UserResponse> login(LoginUserDto loginUserDto);
}
