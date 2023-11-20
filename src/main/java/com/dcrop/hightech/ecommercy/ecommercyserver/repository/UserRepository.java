package com.dcrop.hightech.ecommercy.ecommercyserver.repository;

import com.dcrop.hightech.ecommercy.ecommercyserver.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByUsername(String username);

}
