package com.gc.api.dondevoy.security;

import com.gc.api.dondevoy.model.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JwtResponse {

	private String token;
	
	@Default
	private String type = "Bearer";
	
	private User user;
}
