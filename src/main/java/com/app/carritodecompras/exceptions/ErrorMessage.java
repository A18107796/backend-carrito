package com.app.carritodecompras.exceptions;

import java.net.ConnectException;
import java.util.List;
import java.util.Map;

public class ErrorMessage {

	private String exception;

	private String message;

	private String path;

	private List<?> errors2;

	public ErrorMessage(Exception exception, String path) {

		this.exception = exception.getClass().getSimpleName();
		this.message = exception.getMessage();
		if (exception instanceof ConnectException) {
			this.message = "La base de datos no se encuentra disponible";
		}
		this.path = path;
	}

	public ErrorMessage(Exception exception, String path, List<?> errors2) {
		this.exception = exception.getClass().getSimpleName();
		this.message = exception.getMessage();
		this.path = path;
		this.errors2 = errors2;
	}

	public List<?> getErrors2() {
		return errors2;
	}

	public void setErrors2(List<?> errors2) {
		this.errors2 = errors2;
	}

	public String getException() {
		return exception;
	}

	public String getMessage() {
		return message;
	}

	public String getPath() {
		return path;
	}

	@Override
	public String toString() {
		return "ErrorMessage [exception=" + exception + ", message=" + message + ", path=" + path + "]";
	}

}
