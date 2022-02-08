package com.shermatov.usersservice.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
@RequestMapping("/users")
public class UsersController {

    @Value("${microservice.orders-service.endpoints.endpoint.uri}")
    private String foo;

    @GetMapping()
    public String hello() {
        return "hello world: " + foo;
    }
}
