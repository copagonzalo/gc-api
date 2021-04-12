package com.gc.api.dondevoy.util.exception;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.data.rest.core.RepositoryConstraintViolationException;
import org.springframework.util.Assert;
import org.springframework.validation.FieldError;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Value;

public class RestConstraintViolationExceptionMessage {
	
	private final List<ValidationError> errors = new ArrayList<ValidationError>();
	
	/**
	 * Creates a new {@link RepositoryConstraintViolationExceptionMessage} for the
	 * given {@link RepositoryConstraintViolationException} and
	 * {@link MessageSourceAccessor}.
	 *
	 * @param exception must not be {@literal null}.
	 * @param accessor  must not be {@literal null}.
	 */
	public RestConstraintViolationExceptionMessage(RepositoryConstraintViolationException exception,
			MessageSourceAccessor accessor) {

		Assert.notNull(exception, "RepositoryConstraintViolationException must not be null!");
		Assert.notNull(accessor, "MessageSourceAccessor must not be null!");

		for (FieldError fieldError : exception.getErrors().getFieldErrors()) {
			this.errors.add(ValidationError.of(fieldError.getObjectName(), fieldError.getField(),
					fieldError.getRejectedValue(), accessor.getMessage(fieldError)));
		}
	}

	public RestConstraintViolationExceptionMessage(ConstraintViolationException exception, MessageSourceAccessor accessor) {

		Assert.notNull(exception, "ConstraintViolationException must not be null!");
		Assert.notNull(accessor, "MessageSourceAccessor must not be null!");

		for (@SuppressWarnings("rawtypes") ConstraintViolation constraintViolation : exception.getConstraintViolations()) {
			this.errors.add(ValidationError.of(constraintViolation.getRootBeanClass().getName(), constraintViolation.getPropertyPath().toString(),
					constraintViolation.getInvalidValue(), constraintViolation.getMessage()));
		}
	}
	
	@JsonProperty("errors")
	public List<ValidationError> getErrors() {
		return errors;
	}

	@Value(staticConstructor = "of")
	public static class ValidationError {
		String entity, property;
		Object invalidValue;
		String message;
	}

}
