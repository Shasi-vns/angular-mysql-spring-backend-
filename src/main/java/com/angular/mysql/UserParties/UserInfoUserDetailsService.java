package com.angular.mysql.UserParties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;



@Component
public class UserInfoUserDetailsService implements UserDetailsService{
	
	 @Autowired
	    private UserInfoRepository repository;

	    @Override
	    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	        User userInfo = repository.findByName(username);
	        if(userInfo== null){
	        	
	        	userInfo = repository.getUserByMobile(username);
	        	if(userInfo == null) {
	            throw new UsernameNotFoundException("could not find user");
	        	}
	        	return new UserInfoUserDetails(userInfo);
	        }

	        return new UserInfoUserDetails(userInfo);

	    }

}