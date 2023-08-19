package com.example.employee.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.employee.jpa.model.Address;


@Repository
public interface AddressJpaRepository extends JpaRepository<Address, Integer>{

}
