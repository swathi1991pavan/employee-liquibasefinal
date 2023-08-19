package com.example.employee.jpa.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.Table;

@Entity
@Table(name="address")
public class Address {
	@Id  
    @GeneratedValue(strategy=GenerationType.IDENTITY)  
    private Integer addressId;    
	    	
	private String state; 
	 
	public Address() { }
   

	public Integer getAddressId() {
		return addressId;
	}

	public void setAddressId(Integer addressId) {
		this.addressId = addressId;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Address(Integer addressId, String state) {
		super();
		this.addressId = addressId;
		this.state = state;
	}

	
   
    
    
   
}  

