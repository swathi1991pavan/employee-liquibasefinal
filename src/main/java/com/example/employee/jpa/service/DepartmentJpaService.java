package com.example.employee.jpa.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.example.employee.jpa.model.Department;
import com.example.employee.jpa.model.Employee;
import com.example.employee.jpa.repository.DepartmentJpaRepository;
import com.example.employee.jpa.repository.DepartmentRepository;
import com.example.employee.jpa.repository.EmployeeJpaRepository;


@Service
public class DepartmentJpaService implements DepartmentRepository {
	
	@Autowired
    private DepartmentJpaRepository departmentJpaRepository;

	@Override
	public ArrayList<Department> getDepartment() {
		List<Department> departmentList = departmentJpaRepository.findAll();
        ArrayList<Department> departments = new ArrayList<>(departmentList);
        return departments;
	}

	@Override
	@Transactional
	public Department addDepartment(Department department) {
		departmentJpaRepository.save(department);
	    return department;
	}
	

	@Override
	public Department getDepartmentById(Integer departmentId) {
		try {
			 Department department = departmentJpaRepository.findById(departmentId).get();
			 return department;
		 }catch(Exception e) {
			 throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Check the id");
		 }
	}

	@Override
	public Department getDepartmentByName(String name) {
		return departmentJpaRepository.findByDname(name);
	}

	
	

	
}
