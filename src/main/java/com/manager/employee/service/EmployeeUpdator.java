package com.manager.employee.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.manager.employee.entity.Employee;
import com.manager.employee.repository.EmployeeRepository;

@Service
public class EmployeeUpdator implements EmployeeUpdatable {

	Logger logger = LoggerFactory.getLogger(EmployeeUpdator.class);

	@Autowired
	private EmployeeRepository repo;

	@Async
	public void saveEmployeeToDb(Employee employee) {
		logger.info("saving all employees to db");
		repo.save(employee);
	}

	public List<Employee> parseCsvFile(String fileName) {
		List<Employee> employees = new ArrayList<>();

		File file = new File(fileName);
		logger.info("parsing csv file");
		try (Scanner inputStream = new Scanner(file)) {
			while (inputStream.hasNext()) {
				String data = inputStream.next();
				String[] str = data.split(",");

				if (str[3].equals("FALSE")) {
					Employee employee = new Employee();
					employee.seteId(Integer.parseInt(str[0]) + 1000);
					employee.seteName(str[1]);
					employee.setEmailId(str[1] + "@gmail.com");
					employee.setIfProcessed(true);
					employees.add(employee);
				}
			}
		} catch (Exception e) {
			logger.error("error while parsing csv file", e);
			e.printStackTrace();
		}
		return employees;
	}

}
