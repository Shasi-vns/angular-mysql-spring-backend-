package com.angular.mysql.UserParties;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;





@Service
public class UserService {
	
	@Autowired
	private UserInfoRepository up;

	public User insertUser(User u) {
		return up.save(u);
		//return "User added Successfully with Id: "+u.getId();
	}
	
	public List<User> getAllUsers(){
		return up.findAll();
	}
	
	public String uname(String a) {
		User u = up.findByName(a);
		if (u==null) {
		     u = up.getUserByMobile(a);   
		}
		
		return u.getUsername();
	}
	
	public void setLastLoginDate(String a) {
		
		User u = up.findByName(a);
		if (u==null) {
		     u = up.getUserByMobile(a);   
		}

		Date dt = new Date(System.currentTimeMillis());
		SimpleDateFormat ft = new SimpleDateFormat ("E yyyy.MM.dd 'at' hh:mm:ss a zzz");
		u.setLoginDate( "Last Login : "+ft.format(dt));
		up.save(u);
	}
	

	public String encodePassword(String a) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String encodedPassword = encoder.encode(a);
		return encodedPassword;
	}
	
	
	public void delUser(Integer id) {
		up.deleteById(id);
	}

}