package com.app.carritodecompras.exceptions;

public class InternalServerError extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final String DESCRIPTION = "Internal Server Error (500) No Data Base Connection";

	public InternalServerError(String detail) {
		super(DESCRIPTION + ": " + detail);
	}

}
