package com.shermatov.ecommerce.authservice.controller;

import com.shermatov.ecommerce.authservice.dto.RegisterUserDto;
import com.shermatov.ecommerce.authservice.model.UserResponse;
import com.shermatov.ecommerce.authservice.service.UsersService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public record UserController(UsersService usersService) {

    @GetMapping()
    public Flux<UserResponse> getUsers() {
        return usersService.getUsers();
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<UserResponse> createUser(@Valid @RequestBody RegisterUserDto registerUserDto) {
        return usersService.createUser(registerUserDto);
    }
}
