package com.example.employee.jpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.employee.jpa.model.Address;
import com.example.employee.jpa.repository.AddressJpaRepository;
import com.example.employee.jpa.repository.AddressRepository;



@Service
public class AddressJpaService implements AddressRepository{
	
	@Autowired
    private AddressJpaRepository addressJpaRepository;

	
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Address addAddress(Address address) {
		addressJpaRepository.save(address);
		return address;
	}
	
}
