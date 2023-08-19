package com.example.employee.jpa.repository;


import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Sort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import com.example.employee.jpa.model.Employee;

@Repository 
public interface EmployeeJpaRepository extends JpaRepository<Employee, Integer> {

	

	

	List<Employee> findByAgeLessThan(Integer age);

	List<Employee> findByEmployeeNameContaining(String name);

	List<Employee> findByEmployeeNameStartingWith(String name);
	
	@Query("SELECT e FROM Employee e WHERE e.employeeName LIKE %?1%")
	List<Employee> findByEmployeeNameLike(String name);
	
	@Query("SELECT e FROM Employee e WHERE e.age >= ?1")
	List<Employee> findByAgeGreaterThanEqual(Integer age);
	
	
	@Query("SELECT e FROM Employee e WHERE e.createdAt >= ?1")
	List<Employee> findByDateGreaterThanEqual(Date date);

	@Query("SELECT e FROM Employee e WHERE e.age BETWEEN ?1 AND ?2")
	List<Employee> findByAgeBetween(Integer start, Integer end);
	
	@Query("SELECT e FROM Employee e WHERE e.createdAt BETWEEN ?1 AND ?2")
	List<Employee> findByDateBetween(Date start, Date end);
	
	@Query("SELECT e FROM Employee e ORDER BY e.age DESC")
	List<Employee> findByOrderByAgeDesc();
	
	@Query("SELECT e FROM Employee e")
	Page<Employee> findAllWithPagination(Pageable pageable);

	@Query("SELECT COUNT(e) FROM Department d JOIN d.employees e WHERE d.id = ?1")
    Integer countEmployeesInDepartment(Integer departmentId);
}

