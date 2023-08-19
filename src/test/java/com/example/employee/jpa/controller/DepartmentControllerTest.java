package com.example.employee.jpa.controller;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.example.employee.jpa.dto.AuthRequest;
import com.example.employee.jpa.model.Department;
import com.example.employee.jpa.model.Employee;
import com.example.employee.jpa.service.DepartmentJpaService;
import com.example.employee.jpa.service.EmployeeJpaService;
import com.example.employee.jpa.service.JwtService;
import com.fasterxml.jackson.databind.ObjectMapper;



@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")

public class DepartmentControllerTest {

	
	@MockBean
	private DepartmentJpaService departmentService ;
	
	@Autowired
    private MockMvc mockMvc;
	
	private Department department;
	
	@BeforeEach
	public void setup() {
		 department = new Department(87,"climbing",null);
	}
	
	
	@Test
	@WithMockUser(username = "mary", roles = "ADMIN")
	public void testAddDepartmentWithJwtAuthentication() throws Exception {
	 	when(departmentService.addDepartment(department)).thenReturn(department);
	 	mockMvc.perform(post("/api/department/v1")
	           .contentType(MediaType.APPLICATION_JSON)
	           .content(new ObjectMapper().writeValueAsString(department)))
	           .andExpect(status().isOk());
	}
	
	

	@Test
	@WithMockUser(username = "mary", roles = "ADMIN")
	public void testGetdepartment() throws Exception {
	    Department department1 = new Department(87, "climbing", null);
	    Department department2 = new Department(88, "hiking", null);
	    
	    ArrayList<Department> mockDepartments = new ArrayList<>();
	    mockDepartments.add(department1);
	    mockDepartments.add(department2);
	    
	    when(departmentService.getDepartment()).thenReturn(mockDepartments);
	    
	    mockMvc.perform(get("/api/department/v1"))
	           .andExpect(status().isOk());
	}

		
	@Test
	@WithMockUser(username = "mary", roles = "USER")
	public void testGetDepartmentById() throws Exception {
	    Integer departmentId = 87;
	    
	  	when(departmentService.getDepartmentById(departmentId)).thenReturn(department);

	    mockMvc.perform(get("/api/department/v1/" + departmentId))
	           .andExpect(status().isOk());
	    assertEquals(department,departmentService.getDepartmentById(departmentId));
	}
	
	@Test
	@WithMockUser(username = "mary" ,roles= "ADMIN")
	public void testGetDepartmentByName() throws Exception {
	    String dname = "climbing";
	    
	  	when(departmentService.getDepartmentByName(dname)).thenReturn(department);

	  	mockMvc.perform(get("/api/department/v2/find/"+ dname))
	           .andExpect(status().isOk());
	    //assertEquals(department,departmentService.getDepartmentByName(departmentName));
	}
}












/* @Autowired
 private MockMvc mockMvc;

 @Autowired
 private ObjectMapper objectMapper; // ObjectMapper to convert objects to JSON

 @Autowired
 private JwtController jwtController; // Inject your JwtController for token generation

 @Autowired
 private JwtService jwtService; 
 
  
 
 @Test
 @Transactional
 public void testAddDepartmentWithJwtAuthentication() throws Exception {
     String departmentJson = "{"
             + "\"dname\": \"Aerospace123\","
             + "\"employees\": []"
             + "}";

     // Authenticate and get JWT token using your JwtController
     AuthRequest authRequest = new AuthRequest();
     authRequest.setUsername("Swathi");
     authRequest.setPassword("swathi");

     String jwtToken = jwtController.authenticateAndGetToken(authRequest);
     System.out.println(jwtToken);
     
     // Use direct string value instead of argument matcher
     String generatedToken = jwtService.generateToken("Swathi");
     assertEquals(jwtToken, generatedToken);
     
     mockMvc.perform(post("/api/department/v1")
             .header("Authorization", "Bearer " + jwtToken)
             .contentType(MediaType.APPLICATION_JSON)
             .content(departmentJson))
             .andExpect(status().isOk());
 }*/