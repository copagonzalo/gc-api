package com.gc.api.dondevoy.security;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gc.api.dondevoy.util.exception.RestExceptionResponse;

@Component
public class AuthEntryPointJwt implements AuthenticationEntryPoint {

	private static final Logger logger = LoggerFactory.getLogger(AuthEntryPointJwt.class);

	
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {

		logger.error("Unauthorized error: {}", authException.getMessage());
		// Cors Headers
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Vary", "Origin");
		response.addHeader("Vary", "Access-Control-Request-Method");
		response.addHeader("Vary", "Access-Control-Request-Headers");
		
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		String json = getExceptionJsonResponse(request, authException);
		response.getWriter().write(json);
	}
	
	private String getExceptionJsonResponse(HttpServletRequest request, Throwable e) throws JsonProcessingException {
		Map<String, Object> errorMap = new HashMap<>();
		errorMap.put("path", request.getRequestURI());
		errorMap.put("message", e.getMessage());
		errorMap.put("timestamp", new Date());
		errorMap.put("trace", ExceptionUtils.getStackTrace(e));
		RestExceptionResponse restExceptionResponse = new RestExceptionResponse(HttpStatus.UNAUTHORIZED.value(), errorMap);
		restExceptionResponse.setExceptionMessage(e.getMessage());
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(restExceptionResponse);
	}
}
