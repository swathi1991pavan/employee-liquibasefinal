package com.example.employee.jpa.repository;

import java.util.ArrayList;
import java.util.List;

import com.example.employee.jpa.model.Employee;

public interface EmployeeRepository {
	
	ArrayList<Employee> getEmployees();
	
	Employee addEmployee(Employee employee);
	
	Employee getEmployeeById(Integer employeeId) ;
	
    Employee updateEmployee(Integer employeeId,Employee employee);
	
	void deleteEmployee(Integer employeeId);

	
	List<Employee> findAgeLessThan(Integer age);

	List<Employee> findEmployeesContainingByName(String name);
	
	List<Employee> findEmployeesNameStartingBy(String name);

}
