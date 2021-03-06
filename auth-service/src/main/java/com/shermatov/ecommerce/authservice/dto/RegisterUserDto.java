package com.shermatov.ecommerce.authservice.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class RegisterUserDto {

    @Email(message = "E-mail is not valid")
    private String email;

    @NotBlank(message = "username must be non empty")
    @Size(min = 3, max = 10, message = "Length of username must be on range [3, 10] inclusive")
    private String username;

    @Size(min = 4, max = 10, message = "Length of password must be on range [3, 10] inclusive")
    private String password;
}
