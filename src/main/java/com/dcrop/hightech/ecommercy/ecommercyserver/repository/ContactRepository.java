package com.dcrop.hightech.ecommercy.ecommercyserver.repository;

import com.dcrop.hightech.ecommercy.ecommercyserver.entity.Contact;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends CrudRepository<Contact, Long> {
	
	
}