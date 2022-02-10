package com.shermatov.ecommerce.authservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import reactor.core.publisher.Flux;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserResponse {

    private String userId;
    private String email;
    private String username;
    private Flux<String> roles;
}
