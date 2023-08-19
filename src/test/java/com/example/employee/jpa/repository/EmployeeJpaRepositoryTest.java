package com.example.employee.jpa.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.awt.print.Pageable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.example.employee.jpa.model.Employee;

@DataJpaTest 
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class EmployeeJpaRepositoryTest {
	
	@Autowired 
	private EmployeeJpaRepository employeeRepository;
	
	@Autowired 
	private TestEntityManager entityManager;
	
	@BeforeEach
	void setup() {
		Employee employeeOne = new Employee(101, "Gohitha", "gg@gmail.com", 27,null);
		Employee employeeTwo = new Employee (102,"Pavan","pp@gmail.com",30,null);
	    employeeRepository.save(employeeOne);
	    employeeRepository.save(employeeTwo);
		
	}
	
	@Test
	public void testFindByAgeLessThan() {
		
		List<Employee> employeeEntity = employeeRepository.findByAgeLessThan(30);
		System.out.println(employeeEntity);
		System.out.println(employeeEntity.size());
		assertEquals(1, employeeEntity.size());
	}
	
	@Test
	public void  testFindByEmployeeNameContaining() {
		List<Employee> employee = employeeRepository.findByEmployeeNameContaining("hitha");
		System.out.println(employee);
		assertEquals(1, employee.size());
		
	}
	
	@Test
	public void  testFindByEmployeeNameStartingWith() {
		List<Employee> employee = employeeRepository.findByEmployeeNameStartingWith("G");
		System.out.println(employee);
		assertEquals(1, employee.size());
		
	}
	
	
	@Test
	public void  testFindByEmployeeNameLike() {
		
		List<Employee> employee = employeeRepository.findByEmployeeNameLike("%va%");
		System.out.println(employee);
		assertEquals(1, employee.size());
		
	}
	
	@Test
	public void  testFindByAgeGreaterThanEqual() {
		List<Employee> employee = employeeRepository.findByAgeGreaterThanEqual(29);
		System.out.println(employee);
		assertEquals(1, employee.size());
		
	}
	
	@Test
	public void  testfindByDateGreaterThanEqual() throws ParseException {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    Date dateToCompare = sdf.parse("2023-08-06");
		List<Employee> employee = employeeRepository.findByDateGreaterThanEqual(dateToCompare);
		System.out.println(employee);
		assertEquals(2, employee.size());
		
	}
	
	@Test
	public void  testFindByAgeBetween()  {
		
		List<Employee> employee = employeeRepository.findByAgeBetween(25,29);
		System.out.println(employee);
		assertEquals(1, employee.size());
		
	}
	
	@Test
	public void testFindByDateBetween() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    Date startDate = sdf.parse("2023-08-06");
	    Date endDate = sdf.parse("2023-08-11");
	    List<Employee> employee = employeeRepository.findByDateBetween(startDate,endDate);
		System.out.println(employee);
		assertEquals(2, employee.size());
	}
	
	@Test
	public void  testFindByOrderByAgeDesc() {
		System.out.println("Hi This is swathi");
		List<Employee> employee = employeeRepository.findByOrderByAgeDesc();
		System.out.println(employee);
		assertEquals(2, employee.size());
        assertEquals("Gohitha", employee.get(1).getEmployeeName()); // Oldest employee
        assertEquals("Pavan", employee.get(0).getEmployeeName()); // Youngest employee
		
	}
	

	
	
	@Test
	public void testFindAllWithPagination() {
	    // Define pagination parameters
	    int page = 0; // Page number
	    int pageSize = 5; // Number of items per page
	    PageRequest pageable = PageRequest.of(page, pageSize);

	    Page<Employee> employeePage = employeeRepository.findAllWithPagination(pageable);

	    // Verify the result
	    List<Employee> employees = employeePage.getContent();
	    System.out.println(employees);
	    assertEquals(2, employees.size()); // Should be equal to pageSize
        assertEquals(2, employeePage.getTotalElements()); // Total number of elements
        assertEquals(1, employeePage.getTotalPages()); // Total number of pages
        
       /* // Verify the content of the first employee
        Employee firstEmployee = employees.get(0);
        assertEquals("Ramya", firstEmployee.getEmployeeName());
        assertEquals("rr@gmail.com", firstEmployee.getEmail());
        assertEquals(30, firstEmployee.getAge());*/

	}
	
	
}
	
	

