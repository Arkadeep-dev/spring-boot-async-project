package com.manager.employee.service;

import java.util.List;

import com.manager.employee.entity.Employee;

public interface EmployeeUpdatable {

	public List<Employee> parseCsvFile(String fileName);

	public void saveEmployeeToDb(Employee employee);

}
