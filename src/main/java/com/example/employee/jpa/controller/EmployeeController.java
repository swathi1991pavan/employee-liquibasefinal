package com.example.employee.jpa.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;


import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.employee.jpa.model.Employee;
import com.example.employee.jpa.service.EmployeeJpaService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/employees")

public class EmployeeController {
	
	@Autowired
    public EmployeeJpaService employeeService ;
	
	
	@GetMapping("/v1")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public List<Employee> getEmployees(){
		return employeeService.getEmployees();
	}
	
	@GetMapping("/v1/{employeeId}")
	@PreAuthorize("hasAuthority('ROLE_USER')")
    public Employee getEmployeeById(@PathVariable("employeeId") Integer employeeId){
		return employeeService.getEmployeeById(employeeId);
    } 
	
	@PostMapping("/v1")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public Employee addEmployee(@Valid @RequestBody Employee employee) {
		return employeeService.addEmployee(employee);
	}
	
	@PutMapping("/v1/{employeeId}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public Employee updateEmployee(@PathVariable("employeeId")  Integer employeeId , @RequestBody Employee employee) {
		return employeeService.updateEmployee(employeeId, employee);
	}
	
	@DeleteMapping("/v1/{employeeId}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public void deleteEmployee(@PathVariable("employeeId")Integer employeeId) {
		employeeService.deleteEmployee(employeeId);
	}
	
	
	
	@GetMapping("/v2/search/{age}")
    public List<Employee> findAgeLessThan(@PathVariable Integer age){
		return employeeService.findAgeLessThan(age);
        
    }
	
	
	@GetMapping("/v2/find")
    public List<Employee> findEmployeesContainingByName(@Param("name") String name){
        return employeeService.findEmployeesContainingByName(name);
	}
        
	@GetMapping("/v2/starts/{name}")
    public List<Employee> findEmployeesNameStartingBy(@PathVariable String name){
        return employeeService.findEmployeesNameStartingBy(name);
        
	}
	
	@GetMapping("/v2/like/{name}")
    public List<Employee> findEmployeeNameLike(@PathVariable String name){
        return employeeService.findEmployeeNameLike(name);
        
	}
	
	@GetMapping("/v2/findbyage/{age}")
    public List<Employee> findAgeGreaterThanEqual(@PathVariable Integer age){
        return employeeService.findAgeGreaterThanEqual( age);
        
	}
	
	@GetMapping("/v2/dateGreaterThanEqual/{date}")
	public List<Employee> findDateGreaterThanEqual(@PathVariable   Date date) {
        return employeeService.findDateGreaterThanEqual(date);
    }

	@GetMapping("/v2/ageBetween/{start}/{end}")
	public List<Employee> findAgeBetween(@PathVariable Integer start, @PathVariable Integer end ) {
        return employeeService.findAgeBetween(start,end);
    }
	
	
	@GetMapping("/v2/dateBetween/{start}/{end}")
	public List<Employee> findDateBetween(@PathVariable  Date start, @PathVariable  Date end ) {
        return employeeService.findDateBetween(start,end);
    }
	
	@GetMapping("/v2/ageDesc")
	public List<Employee> findOrderByAgeDesc(){
		return employeeService.findOrderByAgeDesc();
	}
	
	@GetMapping("/v2/pagination")
    public Page<Employee> getAllTutorials(Pageable pageable) {
        return employeeService.findAllWithPagination(pageable);
    }
	
	 @GetMapping("/v2/count/{departmentId}")
	    public Integer countEmployeesInDepartment(@PathVariable Integer departmentId) {
	        return employeeService.countEmployeesInDepartment(departmentId);
	    }
	
}









































//http://localhost:8085/api/employees/dateGreaterThanEqual/2023-08-02

//http://localhost:8085/api/employees/ageBetween/29/36

//http://localhost:8085/api/employees/dateBetween/2023-08-01/2023-08-31

//@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)

//http://localhost:8085/api/employees/pagination?page=0&size=1
