package com.example.employee.jpa.repository;

import java.util.ArrayList;

import com.example.employee.jpa.dto.ResponseDto;

public interface UserRepository {
	ResponseDto getUser(Integer userId);
	
	//ArrayList<ResponseDto> getUsers();
}
