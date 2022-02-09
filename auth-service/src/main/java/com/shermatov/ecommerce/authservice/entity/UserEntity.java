package com.shermatov.ecommerce.authservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Table(value = "users")
public class UserEntity {

    @Id
    private String userId;
    private String firstName;
    private String email;
    private String password;
}
