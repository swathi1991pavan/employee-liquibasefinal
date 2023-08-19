package com.example.employee.jpa.controller;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import com.example.employee.jpa.controller.JwtController;
import com.example.employee.jpa.dto.AuthRequest;
import com.example.employee.jpa.model.UserInfo;
import com.example.employee.jpa.repository.UserInfoJpaRepository;
import com.example.employee.jpa.service.JwtService;
import com.example.employee.jpa.service.UserInfoUserDetailsService;
import com.fasterxml.jackson.databind.ObjectMapper;

/*@SpringBootTest
@AutoConfigureMockMvc
public class JwtControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JwtService jwtService;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void testAuthenticateAndGetToken_Success() throws Exception {
        AuthRequest authRequest = new AuthRequest("Swathi", "Swathi");

        // Create a mock user for testing
        UserDetails userDetails = User.builder()
                .username("Swathi")
                .password(passwordEncoder.encode("Swathi"))
                .roles("ADMIN")
                .build();
        System.out.println("hi");
        System.out.println(userDetails);
        // Mock the behavior of the UserDetailsService to return the mock user
        //when(userDetailsService.loadUserByUsername("Swathi"))
           // .thenReturn(mockUserDetails);
        
        UserDetails userDetails1 = userDetailsService.loadUserByUsername("Swathi");
        System.out.println("hello");
        System.out.println(userDetails1);
        
     // Check if the provided password matches the encoded password in UserDetails
        boolean passwordMatches = passwordEncoder.matches("swathi", userDetails1.getPassword());
        System.out.println(passwordMatches);

        if (passwordMatches) {
            // Create an authentication token
            Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails1, null, userDetails1.getAuthorities());
            System.out.println("hello123");
            System.out.println(authentication);
            System.out.println("123");
            // Mock the jwtService behavior
            when(jwtService.generateToken("Swathi"))
                .thenReturn("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJTd2F0aGkiLCJpYXQiOjE2OTE1ODAyNTIsImV4cCI6MTY5MTU4MjA1Mn0.7ZxdZar3EkLwtjUFoQALtw_Qc0BD7xZnHE5rICZAZhQ");

            mockMvc.perform(MockMvcRequestBuilders.post("/api/authenticate/v1")
                    .contentType("application/json")
                    .content(new ObjectMapper().writeValueAsString(authRequest)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJTd2F0aGkiLCJpYXQiOjE2OTE1ODAyNTIsImV4cCI6MTY5MTU4MjA1Mn0.7ZxdZar3EkLwtjUFoQALtw_Qc0BD7xZnHE5rICZAZhQ"));
        } else {
            // Handle case where password doesn't match
            // You can throw an exception or handle it in a way that suits your needs
            // For this example, I'll just fail the test
            fail("Password does not match.");
        }
    }
}

       /* // Create an authentication token using the mock user details
        Authentication authentication = new UsernamePasswordAuthenticationToken(mockUserDetails, null, mockUserDetails.getAuthorities());
        System.out.println(authentication);
        // Mock the jwtService behavior
        when(jwtService.generateToken("Swathi"))
            .thenReturn("your_mocked_token_here");
        System.out.println(jwtService.generateToken("Swathi"));
        mockMvc.perform(MockMvcRequestBuilders.post("/api/authenticate/v1")
                .contentType("application/json")
                .content(new ObjectMapper().writeValueAsString(authRequest)))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().string("your_mocked_token_here"));
        System.out.println(MockMvcResultMatchers.status().isOk());
    }
}*/

//if i use this it is hitting the database.
@SpringBootTest
@AutoConfigureMockMvc
public class JwtControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JwtService jwtService;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private UserInfoJpaRepository repository;

    @Test
    public void testAuthenticateAndGetToken_Success() throws Exception {
        AuthRequest authRequest = new AuthRequest("Swathi", "swathi");
        
        UserInfo userdet = new UserInfo(1,"Swathi","ss@gmail.com","swathi","ROLE_ADMIN");
        // Load user details by username
        
        System.out.println(userdet);
        UserDetails userDetails = userDetailsService.loadUserByUsername("Swathi");
        
        System.out.println(userDetails);

        // Check if the provided password matches the encoded password in UserDetails
        boolean passwordMatches = passwordEncoder.matches("swathi", userDetails.getPassword());
        System.out.println(passwordMatches);

        if (passwordMatches) {
            // Create an authentication token
            Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            System.out.println(authentication);

            // Mock the jwtService behavior
            when(jwtService.generateToken("Swathi"))
                .thenReturn("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJTd2F0aGkiLCJpYXQiOjE2OTE1ODAyNTIsImV4cCI6MTY5MTU4MjA1Mn0.7ZxdZar3EkLwtjUFoQALtw_Qc0BD7xZnHE5rICZAZhQ");

            mockMvc.perform(MockMvcRequestBuilders.post("/api/authenticate/v1")
                    .contentType("application/json")
                    .content(new ObjectMapper().writeValueAsString(authRequest)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJTd2F0aGkiLCJpYXQiOjE2OTE1ODAyNTIsImV4cCI6MTY5MTU4MjA1Mn0.7ZxdZar3EkLwtjUFoQALtw_Qc0BD7xZnHE5rICZAZhQ"));
        } else {
            // Handle case where password doesn't match
            // You can throw an exception or handle it in a way that suits your needs
            // For this example, I'll just fail the test
            fail("Password does not match.");
        }
    }
}


