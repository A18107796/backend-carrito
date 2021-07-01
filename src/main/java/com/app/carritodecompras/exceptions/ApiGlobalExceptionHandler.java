package com.app.carritodecompras.exceptions;

import java.net.ConnectException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApiGlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(NotFoundException.class)
	@ResponseBody
	public ErrorMessage notFoundRequest(HttpServletRequest request, Exception exception) {
		return new ErrorMessage(exception, request.getRequestURI());
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(BadRequestException.class)
	@ResponseBody
	public ErrorMessage badRequest(HttpServletRequest request, BadRequestException exception) {

		if (exception.getErrors() instanceof String) {
			return new ErrorMessage(exception, request.getRequestURI());
		}
		return new ErrorMessage(exception, request.getRequestURI(), (List<?>) exception.getErrors());
	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(InternalServerError.class)
	@ResponseBody
	public ErrorMessage internalServerError(HttpServletRequest request, Exception exception) {
		return new ErrorMessage(exception, request.getRequestURI());
	}
	
	@ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
	@ExceptionHandler(ConnectException.class)
	@ResponseBody
	public ErrorMessage serviceUnavailable(HttpServletRequest request, ConnectException exception) {
		return new ErrorMessage(exception, request.getRequestURI());
	}

}
