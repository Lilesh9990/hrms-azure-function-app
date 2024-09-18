package com.adt.azfunction.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.adt.azfunction.model.Employee;
import com.adt.azfunction.repository.EmployeeRepository;
import com.adt.azfunction.util.Utility;
import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.HttpRequestMessage;

@Service
public class EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;

	private static final int MAX_PAGE_SIZE = 50;
	private static final int DEFAULT_PAGE_SIZE = 10;

	public Page<Employee> getAllEmps(HttpRequestMessage<Optional<String>> request, ExecutionContext context) {
		int page = Utility.stringToInt(request.getQueryParameters().getOrDefault("page", "0"), context);
		int size = Utility.stringToInt(request.getQueryParameters().getOrDefault("size", "10"), context);
		if (size <= 0 || size > MAX_PAGE_SIZE) {
			size = DEFAULT_PAGE_SIZE;
		}
		if (page <= 0) {
			page = 0;
		}
		Pageable pageable = PageRequest.of(page, size);
		return employeeRepository.getAllEmployees(pageable);
	}

}
