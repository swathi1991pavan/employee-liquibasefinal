package com.example.employee.jpa.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

import jakarta.persistence.Table;

@Entity  
@Table(name="department_tbl")  
public class Department extends AuditModel{    
  
	@Id   
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	private Integer id;    
	private String dname;    
  
	@OneToMany(cascade = CascadeType.ALL)  
	@JoinColumn(name="depart_id" , referencedColumnName="id")  
	private List<Employee> employees;  

	public Department() { }
	
	
	public Department(Integer id, String dname, List<Employee> employees) {
		super();
		this.id = id;
		this.dname = dname;
		this.employees = employees;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDname() {
		return dname;
	}

	public void setDname(String dname) {
		this.dname = dname;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}


	@Override
	public String toString() {
		return "Department [id=" + id + ", dname=" + dname + ", employees=" + employees + "]";
	}

	

}  
