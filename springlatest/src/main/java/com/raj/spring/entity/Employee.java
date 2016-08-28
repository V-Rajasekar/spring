package com.raj.spring.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Digits;

/**
 * @author Rajasekar
 *
 */
@Entity
@Table(name="EMPLOYEE")
public class Employee {

	@Id
	@Digits(integer = 3, fraction = 0)
	@Column(name = "EMPLOYEE_ID")
	/*@SequenceGenerator(name = "TEMPLOYEE_EMPLOYEE_ID_SEQ", sequenceName = "TEMPLOYEE_EMPLOYEE_ID_SEQ", initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TEMPLOYEE_EMPLOYEE_ID_SEQ")*/
	private int employeeId;

	@Column(name = "FIRSTNAME")
	private String firstName;

	@Column(name = "LASTNAME")
	private String lastName;

	@ManyToOne
	@JoinColumn(name = "DEPT_ID")
	private Dept department;

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Dept getDepartment() {
		return department;
	}

	public void setDepartment(Dept department) {
		this.department = department;
	}

}
