package com.manager.employee.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.manager.employee.entity.Employee;
import com.manager.employee.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);

	@Autowired
	private EmployeeRepository repo;

	List<Employee> updatedEmployees = new ArrayList<>();

	@Override
	public List<Employee> getAllEmployeeFromDb() {
		return repo.findByIfProcessed(false);
	}

	@Async
	public void updateEmployee(Employee employee) {
		if (employee.geteId().equals(1002)) {
			logger.info("thread sleep");
			// to check if the task is running async or not
			sleep(30);
			employee.setEmailId(employee.geteName() + "@gmail.com");
			employee.setIfProcessed(true);
			repo.save(employee);
			logger.info("{}", employee);
		} else {
			
			employee.setEmailId(employee.geteName() + "@gmail.com");
			employee.setIfProcessed(true);
			repo.save(employee);
		}
		updatedEmployees.add(employee);
	}

	private void sleep(int i) {
		try {
			TimeUnit.SECONDS.sleep(i);
		} catch (InterruptedException e) {
			e.printStackTrace();
			Thread.currentThread().interrupt();
		}

	}

	@Override
	public void saveAllEmployeesToDb() {
		// TODO Auto-generated method stub
		repo.saveAll(updatedEmployees);

	}

}
