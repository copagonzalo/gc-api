package com.gc.api.dondevoy.dto.authentication;

import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestAuthDto {

	/*
	 * Username is required
	 */
	@Size(min = 1, max = 255)
	private String username;

	/*
	 * Password is required
	 */
	@Size(min = 1, max = 255)
	private String password;
}
