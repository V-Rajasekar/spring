package com.raj.spring.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.raj.spring.entity.Dept;

/**
 * @author Rajasekar
 *
 */
@Repository
public interface DepartmentRepository extends CrudRepository<Dept, BigDecimal> {
	public List<Dept> findAll();

}
