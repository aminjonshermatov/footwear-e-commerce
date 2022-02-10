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
@Table(value = "roles")
public class RoleEntity {

    @Id
    private String roleId;
    private String roleName;
}
