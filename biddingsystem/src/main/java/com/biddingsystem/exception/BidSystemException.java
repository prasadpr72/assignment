package com.biddingsystem.exception;

public class BidSystemException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6652292455286426825L;

	private String errorCode;

	private String errorMessage;

	public BidSystemException(String errorCode, String errorMessage) {
		super(errorCode + ":" + errorMessage);
	}

	public String getErrorCode() {
		return errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

}
