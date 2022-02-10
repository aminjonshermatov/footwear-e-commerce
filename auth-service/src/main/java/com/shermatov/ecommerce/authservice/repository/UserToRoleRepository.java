package com.shermatov.ecommerce.authservice.repository;

import com.shermatov.ecommerce.authservice.entity.UserToRoleEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;

@Repository
@Transactional(readOnly = true)
public interface UserToRoleRepository extends ReactiveCrudRepository<UserToRoleEntity, String> {

    Flux<UserToRoleEntity> findByUserId(String userId);
    Flux<UserToRoleEntity> findByRoleId(String roleId);
}
