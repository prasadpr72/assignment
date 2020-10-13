package com.biddingsystem.dto;

public class ResponseDto {

	private Object response;

	private String message;

	private boolean isError;

	private String errorCode;

	public ResponseDto(Object response, String message, boolean isError,
			String errorCode) {
		super();
		this.response = response;
		this.message = message;
		this.isError = isError;
		this.errorCode = errorCode;
	}

	public Object getResponse() {
		return response;
	}

	public void setResponse(Object response) {
		this.response = response;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isError() {
		return isError;
	}

	public void setError(boolean isError) {
		this.isError = isError;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

}
