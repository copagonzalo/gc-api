package com.gc.api.dondevoy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import com.gc.api.dondevoy.dto.authentication.RequestAuthDto;
import com.gc.api.dondevoy.model.User;
import com.gc.api.dondevoy.security.JwtResponse;
import com.gc.api.dondevoy.security.JwtUtils;
import com.gc.api.dondevoy.service.AuthService;
import com.gc.api.dondevoy.service.UserService;

@Service
public class AuthServiceImpl implements AuthService {

	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	UserService userService;
	
	@Autowired
	JwtUtils jwtUtils;
	
	@Override
	public JwtResponse authenticate(RequestAuthDto requestAuthDto) {
		User user = userService.getByUsername(requestAuthDto.getUsername());
		if (user==null) {
			throw new UsernameNotFoundException("User does not exist.");
		}
		Authentication authentication;
		try {
			authentication = authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(user, requestAuthDto.getPassword()));
			SecurityContextHolder.getContext().setAuthentication(authentication);
			user = (User) authentication.getPrincipal();
			String jwt = jwtUtils.generateJwtToken(user);
			return JwtResponse.builder().token(jwt).user(user).build();
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

}
