package com.raj.spring.config;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.raj.spring.entity.Dept;
import com.raj.spring.repository.DepartmentRepository;

/**
 * @author Rajasekar
 * 
 */
@ActiveProfiles("HSQL_EMBEDDED")
@ContextConfiguration(classes = { JPAHSQLEmbeddedConfig.class })
@RunWith(SpringJUnit4ClassRunner.class)
public class JPAHsqlIntegrationTest {

	@Autowired
	DepartmentRepository departmentRepo;

	@Before
	public void setupDept() {
		Dept dept = new Dept();
		dept.setDeptId(new BigDecimal("1"));
		dept.setDeptName("Admin");
		departmentRepo.save(dept);
	}

	@Test
	public void testDept() {
		Dept dept1 = departmentRepo.findOne(new BigDecimal("1"));
		assertEquals("Department name is differing", dept1.getDeptName(),
				"Admin");
	}

}
