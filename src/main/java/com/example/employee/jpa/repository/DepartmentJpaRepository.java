package com.example.employee.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.employee.jpa.model.Department;

@Repository
public interface DepartmentJpaRepository extends JpaRepository<Department, Integer>{

	Department findByDname(String name);
	
	

}
