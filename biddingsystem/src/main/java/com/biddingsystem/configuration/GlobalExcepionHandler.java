package com.biddingsystem.configuration;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.biddingsystem.dto.ResponseDto;
import com.biddingsystem.exception.BidSystemException;
import com.biddingsystem.exception.UnAuthorizedException;

@ControllerAdvice
public class GlobalExcepionHandler {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ResponseDto> responseHandler(Exception ex) {
		if (ex instanceof UnAuthorizedException) {
			UnAuthorizedException exp = (UnAuthorizedException) ex;
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
					new ResponseDto(null, exp.getMessage(), true, exp
							.getErrorCode()));
		} else if (ex instanceof BidSystemException) {
			BidSystemException exp = (BidSystemException) ex;
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
					new ResponseDto(null, exp.getMessage(), true, exp
							.getErrorCode()));
		}
		 return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
				new ResponseDto(null, ex.getMessage(), true,""));
	}
}