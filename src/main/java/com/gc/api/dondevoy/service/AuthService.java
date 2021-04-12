package com.gc.api.dondevoy.service;

import com.gc.api.dondevoy.dto.authentication.RequestAuthDto;
import com.gc.api.dondevoy.security.JwtResponse;

public interface AuthService {
	JwtResponse authenticate(RequestAuthDto requestAuthDto);
}
