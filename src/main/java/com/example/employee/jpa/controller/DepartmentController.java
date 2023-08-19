package com.example.employee.jpa.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.employee.jpa.model.Department;
import com.example.employee.jpa.service.DepartmentJpaService;


import jakarta.validation.Valid;



@RestController
@RequestMapping("/api/department")
public class DepartmentController {
	
	@Autowired
    public DepartmentJpaService departmentService ;
	
	
	@GetMapping("/v1")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public List<Department> getdepartment(){
		return departmentService.getDepartment();
	}
	
	@GetMapping("/v1/{departmentId}")
	@PreAuthorize("hasAuthority('ROLE_USER')")
    public Department getDepartmentById(@PathVariable("departmentId") Integer departmentId){
		return departmentService.getDepartmentById(departmentId);
    } 
	
	@PostMapping("/v1")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public Department addDepartment(@Valid @RequestBody Department department) {
		return departmentService.addDepartment(department);
	} 
	
	
	@GetMapping("/v2/find/{dname}")
	public Department getDepartmentByName(@PathVariable("dname") String dname) {
		return departmentService.getDepartmentByName(dname);
	}
	
	/*@GetMapping("/v2/find/{name}")
	public Department getDepartmentByName(@RequestParam(name = "dname") String dname) {
		return departmentService.getDepartmentByName(dname);
	}*/
	
	
}








/*{
 * 
 * http://localhost:8085/api/department
 * 
 * 
{
"dname" : "ECE",
"employees" : [
    {
        "employeeName": "Keerthi",
        "email": "kk@gmail.com",
        "age" : 28,
        "address" : {
            "state" : "Hyderabad"
        }
               
    },
    {
        "employeeName": "Harsha",
        "email": "hh@gmail.com",
        "age" : 28,
         "address" : {
            "state" : "Banglalore"
        }
        
    }
]
}

*
{
"dname" : "Agricultural",
"employees" : [
    {
        "employeeName": "Ramya",
        "email": "rr@gmail.com",
        "age" : 30,
        "address" : {
            "state" : "Pune"
        }
               
    },
    {
        "employeeName": "Harsha",
        "email": "hh@gmail.com",
        "age" : 32,
         "address" : {
            "state" : "Nasik"
        }
        
    }
]
}*/

/*{
"dname" : "CSE",
"employees" : [
    {b
        "employeeName": "Keerthi",
        "email": "kk@gmail.com",
        "age" : 28            

        
    },
    {
        "employeeName": "Harsha",
        "email": "hh@gmail.com",
        "age" : 30      
        
    }
]
}*/


/*{
"dname" : "IT",
"employees" : [
    {
        "employeeName": "Swathi",
        "email": "ss@gmail.com",
        "age" : 29            

        
    },
    {
        "employeeName": "Pavan",
        "email": "pp@gmail.com",
        "age" : 35     
        
    }
]
}*/


