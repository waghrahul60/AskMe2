package com.askme.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.askme.model.Subquery;

@Repository
public interface SubqueryRepository extends JpaRepository<Subquery, Long> {
	  Optional<Subquery> findByName(String subqueryName);
}
