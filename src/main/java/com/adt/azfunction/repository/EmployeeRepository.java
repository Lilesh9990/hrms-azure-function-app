package com.adt.azfunction.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.adt.azfunction.model.Employee;

@Component
public class EmployeeRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public Page<Employee> getAllEmployees(Pageable pageable) {
		String sql = "SELECT first_name, last_name FROM user_schema.\"_employee\" e";
		int totalRows = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM user_schema.\"_employee\"", Integer.class);
		int offset = pageable.getPageNumber() * pageable.getPageSize();
		sql += " LIMIT " + pageable.getPageSize() + " OFFSET " + offset;
		List<Employee> employeeList = jdbcTemplate.query(sql, new RowMapper<Employee>() {
			@Override
			public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
				Employee employee = new Employee();
				employee.setFirstName(rs.getString("first_name"));
				employee.setLastName(rs.getString("last_name"));
				return employee;
			}
		});
		return new PageImpl<>(employeeList, pageable, totalRows);
	}
}
