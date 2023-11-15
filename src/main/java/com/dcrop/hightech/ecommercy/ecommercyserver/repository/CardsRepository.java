package com.dcrop.hightech.ecommercy.ecommercyserver.repository;

import java.util.List;

import com.dcrop.hightech.ecommercy.ecommercyserver.entity.Cards;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CardsRepository extends CrudRepository<Cards, Long> {
	
	List<Cards> findByCustomerId(int customerId);

}