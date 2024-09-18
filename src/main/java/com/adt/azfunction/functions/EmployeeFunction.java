package com.adt.azfunction.functions;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.adt.azfunction.model.Employee;
import com.adt.azfunction.service.EmployeeService;
import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.HttpMethod;
import com.microsoft.azure.functions.HttpRequestMessage;
import com.microsoft.azure.functions.HttpResponseMessage;
import com.microsoft.azure.functions.HttpStatus;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;

@Component
public class EmployeeFunction {

	@Autowired
	EmployeeService employeeService;

	@FunctionName("getAllEmp")
	public HttpResponseMessage getAllEmp(@HttpTrigger(name = "request", methods = {
			HttpMethod.GET }, authLevel = AuthorizationLevel.ANONYMOUS) HttpRequestMessage<Optional<String>> request,
			ExecutionContext context) {
		context.getLogger().info("EmployeeFunction: started getAllEmp.");
		Page<Employee> employeePage = employeeService.getAllEmps(request, context);
		List<Employee> employees = employeePage.getContent();
		context.getLogger().info("EmployeeFunction: ended getAllEmp.");
		return request.createResponseBuilder(HttpStatus.OK).body(employees).header("Content-Type", "application/json")
				.build();
	}

}
