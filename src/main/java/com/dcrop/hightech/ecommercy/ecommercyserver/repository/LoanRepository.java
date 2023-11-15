package com.dcrop.hightech.ecommercy.ecommercyserver.repository;

import java.util.List;

import com.dcrop.hightech.ecommercy.ecommercyserver.entity.Loans;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanRepository extends CrudRepository<Loans, Long> {
	
	List<Loans> findByCustomerIdOrderByStartDtDesc(int customerId);

}