package com.manager;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.manager.employee.entity.Employee;
import com.manager.employee.service.EmployeeService;

@SpringBootApplication
public class EmployeeUpdatorApplication implements CommandLineRunner {

	@Autowired
	private EmployeeService service;

	Logger logger = LoggerFactory.getLogger(EmployeeUpdatorApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(EmployeeUpdatorApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		// getting all employees from db which needs to be updated(if processed = false)
		List<Employee> employees = service.getAllEmployeeFromDb();
		logger.info("size ={}", employees.size());
		// updating all employees and saving it to db async.
		for (Employee e : employees)
			service.updateEmployee(e);

		// save all updated employees by batch insert
		service.saveAllEmployeesToDb();

	}
}
