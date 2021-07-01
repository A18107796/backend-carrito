package com.app.carritodecompras.exceptions;

public class MalFormedHeaderException extends BadRequestException {

	private static final String DESCRIPTION = "Token with wrong  format";

	public MalFormedHeaderException(String detail) {
		super(DESCRIPTION + ": " + detail);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
