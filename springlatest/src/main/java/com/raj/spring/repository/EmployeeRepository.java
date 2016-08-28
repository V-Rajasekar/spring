package com.raj.spring.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.raj.spring.entity.Dept;
import com.raj.spring.entity.Employee;

/**
 * @author Rajasekar
 *
 */
@Component
public interface EmployeeRepository extends CrudRepository<Employee, Integer> {

	//@Cacheable(value = "persistanceCache", key="#p0.deptId")
	public List<Employee> findByDepartment(Dept dept);
}
