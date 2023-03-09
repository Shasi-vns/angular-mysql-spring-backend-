package com.angular.mysql.Jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.angular.mysql.Employee.AuthRequest;
import com.angular.mysql.UserParties.User;
import com.angular.mysql.UserParties.UserInfoRepository;



@Service
public class JwtUtils implements UserDetailsService {
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private UserInfoRepository up;
	
	public JwtResponse createJwtToken(AuthRequest authRequest) throws Exception {
        String userName = authRequest.getUsername();
        String userPassword = authRequest.getPassword();
        
       UserDetails userDetails = loadUserByUsername(userName);
        String newGeneratedToken = jwtService.generateToken(authRequest.getUsername());

        User user = up.findByName(userName);
        if (user == null) {
        	user = up.getUserByMobile(userName);
        }
        return new JwtResponse(user, newGeneratedToken);
    }

	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = up.findByName(username);
		return null;
		

		
	}

}
