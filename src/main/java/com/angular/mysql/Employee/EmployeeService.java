package com.angular.mysql.Employee;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;





@Service
public class EmployeeService {
	
	@Autowired
    private EmployeeRepository empRep;
	
	public List<Employee> getEmployee(){
		return empRep.findAll();
	}
	
	public Optional<Employee> getEmployeebyId(Integer id){
		Optional<Employee> emp = empRep.findById(id);
		if(emp.isPresent()){
			return emp;
		}
		else{
			return null;
		}
	}
	
	
	 public Employee insertEmp(Employee e) {
	       return empRep.save(e);
	        
	}
	 
	 public void deleteemp(Integer id) {
		 empRep.deleteById(id);
	 }
}
