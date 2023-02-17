package com.angular.mysql.Employee;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;





@Service
public class EmployeeService {
	
	@Autowired
    private EmployeeRepository empRep;
	
	public List<Employee> getEmployee(){
		return empRep.findAll();
	}
	
	public Employee getEmployeebyId(Integer id){
		return empRep.getById(id);
	}
	
	
	 public Employee insertEmp(Employee e) {
	       return empRep.save(e);
	        
	}
	 
	 public void deleteemp(Integer id) {
		 empRep.deleteById(id);
	 }
}
