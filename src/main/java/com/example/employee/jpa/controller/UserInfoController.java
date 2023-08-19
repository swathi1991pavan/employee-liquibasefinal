package com.example.employee.jpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.employee.jpa.model.UserInfo;
import com.example.employee.jpa.service.UserInfoService;

@RestController
@RequestMapping("/api/addUser")
public class UserInfoController {
	
	@Autowired
    public UserInfoService userService ;
	
	@PostMapping
	public String addUser(@RequestBody UserInfo userInfo) {
		return userService.addUser(userInfo);
		
	}
	
}




/*

http://localhost:8085/api/addUser


{
"name" : "Swathi" ,
"email" : "ss@gmail.com",
"password" : "swathi" ,
"roles" : "ROLE_ADMIN"
}

*

{
    "name" : "Ron" ,
    "email" : "rr@gmail.com",
    "password" : "ron" ,
    "roles" : "ROLE_USER"
}*/