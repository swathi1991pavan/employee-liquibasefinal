package com.example.employee.jpa.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.employee.jpa.dto.ResponseDto;
import com.example.employee.jpa.service.UserImpl;


@RequestMapping("api/users")
@RestController
public class UserController{

	@Autowired
    public UserImpl userService ;	
	
@GetMapping("/v1/{id}")
public ResponseEntity<ResponseDto> getUser(@PathVariable("id") Integer userId){
    ResponseDto responseDto = userService.getUser(userId);
    return ResponseEntity.ok(responseDto);
}
    
    /*@GetMapping("/")
    public ResponseEntity<ArrayList<ResponseDto>> getUsers(){
        ArrayList<ResponseDto> responseDto = userService.getUsers();
        return ResponseEntity.ok(responseDto);
    
    }*/
}