package com.example.employee.jpa.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;



import com.example.employee.jpa.model.Employee;
import com.example.employee.jpa.service.EmployeeJpaService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
class EmployeeControllerTest {
	
	@MockBean
	private EmployeeJpaService employeeService ;
	
	@Autowired
    private MockMvc mockMvc;
	
		
	@Test
	@WithMockUser(username = "mary", roles = "ADMIN")
	
	public void testAddEmployee() throws Exception {
	    Employee employee = new Employee(1, "swathipavan", "ss@gmail.com", 29, null);
	    when(employeeService.addEmployee(employee)).thenReturn(employee);
	    
	   
	   // Add this assertion to check the response status
	   MockHttpServletResponse response = mockMvc.perform(post("/api/employees/v1")
	            .contentType(MediaType.APPLICATION_JSON)
	            .content(new ObjectMapper().writeValueAsString(employee)))
			    .andExpect(status().isOk())
	            .andReturn()
	            .getResponse();
	    
	    assertEquals(200, response.getStatus());
	    System.out.println(response.getStatus());
	}
		
	
				
	}
	

	
