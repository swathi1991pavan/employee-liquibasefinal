package com.example.employee.jpa.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.employee.jpa.constants.ApiConstants;
import com.example.employee.jpa.dto.ResponseDto;
import com.example.employee.jpa.dto.UserDto;
import com.example.employee.jpa.model.Employee;
import com.example.employee.jpa.repository.UserInfoJpaRepository;
import com.example.employee.jpa.repository.UserJpaRepository;
import com.example.employee.jpa.repository.UserRepository;


	@Service
	
	public class UserImpl implements UserRepository {
        @Autowired 
	    private UserJpaRepository userRepository;
        
        @Autowired
	    private RestTemplate restTemplate;
        
        
		
        
        @Override
		public ResponseDto getUser(Integer userId) {
			
        	ResponseDto responseDto = new ResponseDto();
        	//Employee employee = userRepository.findById(userId).get();
            //UserDto userDto = mapToUser(user);
            System.out.println("Hi this is swathi");
            ResponseEntity<UserDto> responseEntity = restTemplate
                    .getForEntity(ApiConstants.BASE_URL + userId,
                    UserDto.class);
            System.out.println(responseEntity);
            UserDto userDto = responseEntity.getBody();

            System.out.println(responseEntity.getStatusCode());

            responseDto.setUser(userDto);
            

            return responseDto;
		}


		


		/**@Override
		public ArrayList<ResponseDto> getUsers() {
			ArrayList<ResponseDto> responseDto = new ResponseDto<>();
			ResponseEntity<UserDto> responseEntity = restTemplate
                    .getForEntity("http://192.168.60.16:8081/api/user" ,
                    UserDto.class);
			
			ArrayList<UserDto>  userDto = responseEntity.getBody();
			responseDto.setUser(userDto);
			return responseDto;
		}*/

}
