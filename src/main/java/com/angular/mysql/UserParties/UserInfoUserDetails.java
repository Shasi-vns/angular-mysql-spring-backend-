package com.angular.mysql.UserParties;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

;

public class UserInfoUserDetails implements UserDetails {
	
	private User userInfo;
	
	
	public UserInfoUserDetails(User userInfo) {
		
		this.userInfo=userInfo;
//        name=userInfo.getName();
//        password=userInfo.getPassword();
//        String roles = userInfo.getRole();
//        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
//
//        authorities.add(new SimpleGrantedAuthority(roles));
    }

	@Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
		String roles = userInfo.getRole();
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        authorities.add(new SimpleGrantedAuthority(roles));
 

        return authorities;
    }

    @Override
    public String getPassword() {
        return userInfo.getPassword();
    }

    @Override
    public String getUsername() {
        return userInfo.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

