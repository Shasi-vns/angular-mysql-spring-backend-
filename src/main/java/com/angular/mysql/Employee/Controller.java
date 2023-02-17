package com.angular.mysql.Employee;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.angular.mysql.Jwt.JwtResponse;
import com.angular.mysql.Jwt.JwtService;
import com.angular.mysql.Jwt.JwtUtils;
import com.angular.mysql.UserParties.UserService;





@RestController
public class Controller {
	
	 @Autowired
	    private JwtService jwtService;

	    @Autowired
	    private AuthenticationManager authenticationManager;
	    
	    @Autowired
	    private UserService us;
	    
	    @Autowired
	    private JwtUtils jwtUt;

	@Autowired
	private EmployeeService empSer;
	
	@GetMapping("/employee")
    public ResponseEntity<List<Employee>> getemployee(){
		List<Employee> emp = empSer.getEmployee();
		return new ResponseEntity<>(emp,HttpStatus.OK);
       
    }
	
	
	@GetMapping("/employee/{id}")
    public ResponseEntity<Employee> getemp(@PathVariable("id") Integer id){
		Employee emp = empSer.getEmployeebyId(id);
		return new ResponseEntity<>(emp,HttpStatus.OK);
       
    }
	
	@PostMapping("/addEmp")
	public ResponseEntity<Employee> addEmp(@RequestBody Employee e){
		Employee emp = empSer.insertEmp(e);
		return new ResponseEntity<>(emp,HttpStatus.CREATED);
	}


	@PutMapping("/editEmp")
	public ResponseEntity<Employee> update(@RequestBody Employee e){
		Employee emp = empSer.insertEmp(e);
		return new ResponseEntity<>(emp,HttpStatus.OK);
	}



	@DeleteMapping("/delEmp/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Integer id){
		empSer.deleteemp(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	
	@PostMapping("/authenticate")
    public ResponseEntity<JwtResponse> authenticateAndGetToken(@RequestBody AuthRequest authRequest) throws Exception{
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
        	
        	JwtResponse resp = jwtUt.createJwtToken(authRequest);
        	//return jwtUt.createJwtToken(authRequest);
        	return new ResponseEntity<>(resp,HttpStatus.OK);
            //return jwtService.generateToken(authRequest.getUsername());
        } else {
        	throw new UsernameNotFoundException("User not found");
        }

    }






}




















