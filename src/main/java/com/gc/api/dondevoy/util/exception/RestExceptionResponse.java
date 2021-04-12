package com.gc.api.dondevoy.util.exception;

import java.util.List;
import java.util.Map;

import com.gc.api.dondevoy.util.exception.RestConstraintViolationExceptionMessage.ValidationError;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RestExceptionResponse {
	
	private Integer status;
	private String path;
	private String errorMessage;
	private String exceptionMessage;
	private String timeStamp;
	private String trace;
	private List<ValidationError> errors;

	public RestExceptionResponse(int status, Map<String, Object> errorAttributes) {
		this.setStatus(status);
		this.setPath((String) errorAttributes.get("path"));
		this.setErrorMessage((String) errorAttributes.get("message"));
		this.setTimeStamp(errorAttributes.get("timestamp").toString());
		this.setTrace((String) errorAttributes.get("trace"));
	}
}
