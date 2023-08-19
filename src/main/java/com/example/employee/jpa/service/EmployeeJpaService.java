package com.example.employee.jpa.service;

import org.springframework.data.domain.Pageable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Sort;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.example.employee.jpa.model.Address;
import com.example.employee.jpa.model.Employee;

import com.example.employee.jpa.repository.EmployeeJpaRepository;
import com.example.employee.jpa.repository.EmployeeRepository;

@Service
public class EmployeeJpaService implements EmployeeRepository{
	
	@Autowired
    private EmployeeJpaRepository employeeJpaRepository;
	
	@Autowired
	private AddressJpaService addressService;
	
	@Override
	public ArrayList<Employee> getEmployees() {
		List<Employee> employeeList = employeeJpaRepository.findAll();
        ArrayList<Employee> employees = new ArrayList<>(employeeList);
        return employees;
	}
	
	@Override
	@Transactional
	public Employee addEmployee(Employee employee) {
		/*if (employee.getEmployeeName().equals("pppp")){
			throw new RuntimeException("some error occured");
			
		}*/
		 
		employeeJpaRepository.save(employee);
	    return employee;
	}

	 @Override
	 public Employee getEmployeeById(Integer employeeId) {
		 try {
			 Employee employee = employeeJpaRepository.findById(employeeId).get();
			 return employee;
		 }catch(Exception e) {
			 throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Check the id");
		 }
		
		 
	 }
	

	@Override
	public Employee updateEmployee(Integer employeeId, Employee employee) {
		try {
			
			Employee existingEmployee =employeeJpaRepository.findById(employeeId).get();
			if(existingEmployee != null) {
				
				if(employee.getEmployeeName() != null){
					existingEmployee.setEmployeeName(employee.getEmployeeName());
				}   
		        				
		        if(employee.getEmail() != null){		            
		            existingEmployee.setEmail(employee.getEmail());
		        }
		        
				/*if(employee.getDepartment() !=null) {					
					existingEmployee.setDepartment(employee.getDepartment());
				}*/
				
				
			}
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);			
		}
		
		
		return getEmployeeById(employeeId);
	}

	@Override
	public void deleteEmployee(Integer employeeId) {
		try {
			
			Employee employee = getEmployeeById(employeeId);
			
			if(employee != null) {
				employeeJpaRepository.deleteById(employeeId);				
			}
			
		}catch (Exception e) {			
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		
	}

	

	@Override
	public List<Employee> findAgeLessThan(Integer age) {
		return employeeJpaRepository.findByAgeLessThan(age);
	}

	@Override
	public List<Employee> findEmployeesContainingByName(String name) {
		return employeeJpaRepository.findByEmployeeNameContaining(name);
	}

	@Override
	public List<Employee> findEmployeesNameStartingBy(String name) {
		return employeeJpaRepository.findByEmployeeNameStartingWith(name);
	}

	public List<Employee> findEmployeeNameLike(String name) {
	return employeeJpaRepository.findByEmployeeNameLike(name);
	}
	
	
	public List<Employee> findAgeGreaterThanEqual(Integer age){
		return employeeJpaRepository.findByAgeGreaterThanEqual(age);
		
	}
	
	public List<Employee> findDateGreaterThanEqual(Date date){
		return employeeJpaRepository.findByDateGreaterThanEqual(date);
		
	}
	
	
	public List<Employee> findAgeBetween(Integer start, Integer end){
		return employeeJpaRepository.findByAgeBetween(start,end);
	}
	
	public List<Employee> findDateBetween(Date start, Date end){
		return employeeJpaRepository.findByDateBetween(start,end);
		
	}
	
	public List<Employee> findOrderByAgeDesc(){
		return employeeJpaRepository.findByOrderByAgeDesc();
	}

	public Page<Employee> findAllWithPagination(Pageable pageable) {
		return employeeJpaRepository.findAllWithPagination(pageable);
	}
	
	public Integer countEmployeesInDepartment(Integer departmentId) {
		return employeeJpaRepository.countEmployeesInDepartment(departmentId);
	}

	
	

}
