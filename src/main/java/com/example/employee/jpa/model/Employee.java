package com.example.employee.jpa.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity

@Table(name = "employees")
public class Employee extends AuditModel{
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name="EmployeeId")
	private Integer employeeId;
	
	@Column (name="EmployeeName" ,nullable=false, length=50)
	@NotEmpty(message = "user name shouldn't be null")
	@Size(min = 3,max=50 , message= "Size should be between 3 to 50")
	private String employeeName;
	
	@Column (name="Email" )
	@Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}" , message = "invalid email address")
	private String email;
	
	private Integer age;
	
	@OneToOne(cascade=CascadeType.ALL)  
    private Address address;
	
	


	/*@Column (name="Age")
	@NotEmpty(message = "user name shouldn't be null")
	@Min(value = 18, message = "Age should not be less than 18")
    @Max(value = 150, message = "Age should not be greater than 100")*/
	
	
	/*@DateTimeFormat(pattern="dd/MM/yyyy")
	private Date dateOfJoining;*/
	
	/*@Column (name="Department" , nullable=false, length=50)
	@NotEmpty (message ="shouldn't be null")
	@Size(min = 3,max=50 , message = "Size should be between 3 to 50")
	private String department;*/
	
	/*@ManyToOne(cascade=CascadeType.ALL)  
	@JoinColumn(name="add_id" , referencedColumnName="addressId") 
	private Address address;*/
	
	

	public Employee(){}
	
	






	public Employee(Integer employeeId,
			@NotEmpty(message = "user name shouldn't be null") @Size(min = 3, max = 50, message = "Size should be between 3 to 50") String employeeName,
			@Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}", message = "invalid email address") String email,
			Integer age,Address address) {
		super();
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.email = email;
		this.age = age;
		this.address = address;
		
	}








	public Integer getEmployeeId() {
		return employeeId;
	}
	
	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}
	
	public String getEmployeeName() {
		return employeeName;
	}
	
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Integer getAge() {
		return age;
	}



	public void setAge(Integer age) {
		this.age = age;
	}








	public Address getAddress() {
		return address;
	}








	public void setAddress(Address address) {
		this.address = address;
	}








	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", employeeName=" + employeeName + ", email=" + email + ", age="
				+ age + ", address=" + address + "]";
	}
	

}

	/*public Date getDateOfJoining() {
		return dateOfJoining;
	}



	public void setDateOfJoining(Date dateOfJoining) {
		this.dateOfJoining = dateOfJoining;*/
	

	/*public String getDepartment() {
		return department;
	}
	
	public void setDepartment(String department) {
		this.department = department;
	}
	
	/*public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}*/

