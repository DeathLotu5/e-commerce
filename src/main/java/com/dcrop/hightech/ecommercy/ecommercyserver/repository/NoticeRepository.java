package com.dcrop.hightech.ecommercy.ecommercyserver.repository;

import java.util.List;

import com.dcrop.hightech.ecommercy.ecommercyserver.entity.Notice;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface NoticeRepository extends CrudRepository<Notice, Long> {
	
	@Query(value = "from Notice n where CURDATE() BETWEEN noticBegDt AND noticEndDt")
	List<Notice> findAllActiveNotices();

}