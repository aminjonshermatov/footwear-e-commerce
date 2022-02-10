package com.shermatov.ecommerce.authservice.repository;

import com.shermatov.ecommerce.authservice.entity.RoleEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Repository
@Transactional(readOnly = true)
public interface RoleRepository extends ReactiveCrudRepository<RoleEntity, String> {

    Mono<RoleEntity> findByRoleName(String roleName);
}
