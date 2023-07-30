package com.angular.mysql.Employee;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.angular.mysql.Jwt.JwtResponse;
import com.angular.mysql.Jwt.JwtService;
import com.angular.mysql.Jwt.JwtUtils;
import com.angular.mysql.UserParties.User;
import com.angular.mysql.UserParties.UserInfoRepository;
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
	    private UserInfoRepository up;
	    
	    @Autowired
	    private JwtUtils jwtUt;
	    

	@Autowired
	private EmployeeService empSer;
	
	@GetMapping("/employee")
    public ResponseEntity<List<Employee>> getemployee(){
		
		
		Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
		
		us.setLastLoginDate(loggedInUser.getName());
		
		List<Employee> emp = empSer.getEmployee();
		return new ResponseEntity<>(emp,HttpStatus.OK);
       
    }
	
	
	@GetMapping("/employee/{id}")
    public ResponseEntity<Optional<Employee>> getemp(@PathVariable("id") Integer id){
		Optional<Employee> emp = empSer.getEmployeebyId(id);
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
		
		String authReqName = us.uname(authRequest.getUsername());
		
		authRequest.setUsername(authReqName);
		
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        
        if (authentication.isAuthenticated()) {	
        	JwtResponse resp ;
			resp = jwtUt.createJwtToken(authRequest);
			
        	return new ResponseEntity<>(resp,HttpStatus.OK);
        	
        	} 
        else {
        	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        	//throw new Exception();
        	}
        
	}
	
	
	@PostMapping("/addUser")
    public ResponseEntity<User> add(@RequestBody User u) {
    	u.setPassword(us.encodePassword(u.getPassword()));
    	User user = us.insertUser(u);
    	return new ResponseEntity<>(user,HttpStatus.CREATED);
    }
	
	@PostMapping("/selfRegister")
    public ResponseEntity<User> selfregister(@RequestBody User u) {
    	u.setPassword(us.encodePassword(u.getPassword()));
    	User user = us.insertUser(u);
    	return new ResponseEntity<>(user,HttpStatus.CREATED);
    }
	
	@GetMapping("/users")
    public ResponseEntity<List<User>> getusers(){
		List<User> u = us.getAllUsers();
		return new ResponseEntity<>(u,HttpStatus.OK);
       
    }
	
	
	@PutMapping("/editUser")
	public ResponseEntity<User> update(@RequestBody User e){
		User u = us.insertUser(e);
		return new ResponseEntity<>(u,HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteUser/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable("id") Integer id){
		us.delUser(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	
}




















