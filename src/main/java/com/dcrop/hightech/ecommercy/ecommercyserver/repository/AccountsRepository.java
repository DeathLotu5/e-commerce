package com.dcrop.hightech.ecommercy.ecommercyserver.repository;

import com.dcrop.hightech.ecommercy.ecommercyserver.entity.Accounts;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AccountsRepository extends CrudRepository<Accounts, Long> {
	
	Accounts findByCustomerId(int customerId);

}