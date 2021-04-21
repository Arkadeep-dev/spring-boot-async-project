package com.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.manager.employee.entity.Employee;
import com.manager.employee.service.EmployeeUpdatable;

@SpringBootApplication
public class EmployeeUpdatorApplication implements CommandLineRunner {

	@Autowired
	private EmployeeUpdatable employeeUpdatable;

	@Value("${employee.fileName}")
	private String fileName;

	public static void main(String[] args) {
		SpringApplication.run(EmployeeUpdatorApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		List<Employee> employees = employeeUpdatable.parseCsvFile(fileName);
		for (Employee e : employees)
			employeeUpdatable.saveEmployeeToDb(e);
	}
}
