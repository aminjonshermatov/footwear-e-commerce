package com.shermatov.ecommerce.authservice.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CreteUserDto {

    private String email;
    private String firstName;
    private String password;
}
