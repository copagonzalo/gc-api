package com.gc.api.dondevoy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.gc.api.dondevoy.model.User;
import com.gc.api.dondevoy.repository.UserRepository;
import com.gc.api.dondevoy.service.UserService;

@Service("userService")
public class UserServiceImp implements UserService{

	@Autowired
	UserRepository userRepository;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userRepository.findByUsername(username);
	}

	@Override
	public String getToken(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getByUsername(String username) {
		return userRepository.findByUsername(username);
	}

}
