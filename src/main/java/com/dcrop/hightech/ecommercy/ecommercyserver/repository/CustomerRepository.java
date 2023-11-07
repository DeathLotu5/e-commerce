package com.dcrop.hightech.ecommercy.ecommercyserver.repository;

import com.dcrop.hightech.ecommercy.ecommercyserver.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    List<Customer> findByEmail(String email);

}
