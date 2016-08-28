package com.raj.spring.entity;

import java.math.BigDecimal;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.OneToMany;

/**
 * @author Rajasekar
 *
 */
@Entity
public class Dept {
	@Id
	@Column(name="DEPT_ID")
	private BigDecimal deptId;
	
	@Column(name="DEPT_NAME")
	private String deptName;

	public BigDecimal getDeptId() {
		return deptId;
	}

	public void setDeptId(BigDecimal deptId) {
		this.deptId = deptId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	
	@OneToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumns({ @JoinColumn(name="DEPT_ID")})
	private Collection<Employee> employee;

	public Collection<Employee> getEmployee() {
		return employee;
	}

	public void setEmployee(Collection<Employee> employee) {
		this.employee = employee;
	}
	
	public BigDecimal getCacheKey() {
		return deptId;
	}
	
	
}
