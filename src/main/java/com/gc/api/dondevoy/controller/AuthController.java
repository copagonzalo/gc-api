package com.gc.api.dondevoy.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gc.api.dondevoy.dto.authentication.RequestAuthDto;
import com.gc.api.dondevoy.security.JwtResponse;
import com.gc.api.dondevoy.security.JwtUtils;
import com.gc.api.dondevoy.service.AuthService;

@BasePathAwareController
@RequestMapping("auth")
public class AuthController {

	@Autowired
	AuthService authService;
	
	@Autowired
	JwtUtils jwtUtils;

	@PostMapping("login")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody RequestAuthDto requestAuthDto) {
		JwtResponse jwtResponse = authService.authenticate(requestAuthDto);
		return ResponseEntity.ok(jwtResponse);
	}
}
