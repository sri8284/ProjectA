package com.reni.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value=Include.NON_NULL)
public class OnRoadResource extends Basic {
	
	private static final long serialVersionUID = 6144529142848164911L;
	private String empName;
	private Integer empId;
	private String employeeType;
	private String assigmentType;
	
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public Integer getEmpId() {
		return empId;
	}
	public void setEmpId(Integer empId) {
		this.empId = empId;
	}
	public String getEmployeeType() {
		return employeeType;
	}
	public void setEmployeeType(String employeeType) {
		this.employeeType = employeeType;
	}
	public String getAssigmentType() {
		return assigmentType;
	}
	public void setAssigmentType(String assigmentType) {
		this.assigmentType = assigmentType;
	}
	
	
	
}
