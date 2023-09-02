package com.vg.tradereportingengine.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class ExceptionHandlerInterceptor extends ResponseEntityExceptionHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionHandlerInterceptor.class);
	
	@ExceptionHandler
	public ResponseEntity<Object> handleCustomBadRequest(final CustomException e) {
		LOGGER.debug(e.getLocalizedMessage(), e);
		String errorMessage = replaceEmptyExceptionMessage(e.getLocalizedMessage());
		return ResponseEntity.badRequest().body(errorMessage);
	}

	private String replaceEmptyExceptionMessage(String message) {
		if (message == null || message.isEmpty()) {
			return "Unidentified exception";
		}

		return message;
	}
}