package com.manager.employee.service;

import java.util.List;

import com.manager.employee.entity.Employee;

public interface EmployeeService {

	public List<Employee> getAllEmployeeFromDb();

	public void updateEmployee(Employee employee);

	public void saveAllEmployeesToDb();

}
