package com.angular.mysql.UserParties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserService {
	
	@Autowired
	private UserInfoRepository up;

	public String insertUser(User u) {
		up.save(u);
		return "User added Successfully with Id: "+u.getId();
	}
	
	public String encodePassword(String a) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String encodedPassword = encoder.encode(a);
		return encodedPassword;
	}
	

}