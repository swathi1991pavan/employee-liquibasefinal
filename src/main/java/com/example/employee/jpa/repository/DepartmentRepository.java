package com.example.employee.jpa.repository;

import java.util.ArrayList;

import com.example.employee.jpa.model.Department;


public interface DepartmentRepository {
	
	ArrayList<Department> getDepartment();
	
	Department addDepartment(Department department);
	
	Department getDepartmentById(Integer departmentId) ;

	Department getDepartmentByName(String name);
}
