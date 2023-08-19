package com.example.employee.jpa.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.employee.jpa.model.Address;
import com.example.employee.jpa.model.Department;
import com.example.employee.jpa.model.Employee;
import com.example.employee.jpa.repository.DepartmentJpaRepository;
import com.example.employee.jpa.repository.DepartmentRepository;

@SpringBootTest
class DepartmentJpaServiceTest {
	
	@Autowired
	private DepartmentRepository departmentRepository;
	
	@MockBean
	private DepartmentJpaRepository departmentJpaRepository;
	
		
	@Test
	public void getDepartmentSuccess() {
		when(departmentJpaRepository.findAll()).thenReturn(Stream.of(new Department(1,"CSE",null),new Department(2,"ECE",null)).collect(Collectors.toList()));
	    assertEquals(2,departmentRepository.getDepartment().size()); 
	}
	
	@Test
	public void addDepartmentSuccess() {
		Department department = (new Department(1,"CSE",null));
		when(departmentJpaRepository.save(department)).thenReturn(department);
		assertEquals(department,departmentRepository.addDepartment(department));
		
	}
	
	@Test
	public void getDepartmentByIdSuccess() {
		Integer departmentId=1;
		Department department = (new Department(1,"CSE",null));
		when(departmentJpaRepository.findById(departmentId)).thenReturn(Optional.of(department));
		assertEquals(department,departmentRepository.getDepartmentById(departmentId));
	}
	
	@Test
	public void getDepartmentByNameSuccess() {
		Department department = (new Department(1,"CSE",null));
		String name="CSE";
		when(departmentJpaRepository.findByDname(name)).thenReturn(department);
		assertEquals(department,departmentRepository.getDepartmentByName(name));
	}
	

}
//Optional<Department> department = Optional.of(new Department(1,"CSE",new Employee(1,"swathi","ss@gmail.com",new Address(1,"tela"),28)));