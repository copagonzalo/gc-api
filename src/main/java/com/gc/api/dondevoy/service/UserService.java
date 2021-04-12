package com.gc.api.dondevoy.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.gc.api.dondevoy.model.User;


public interface UserService extends UserDetailsService {
	
	String getToken(String username);
	
	User getByUsername(String username);
	
}
