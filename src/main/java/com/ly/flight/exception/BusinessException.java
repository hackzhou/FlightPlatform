package com.ly.flight.exception;

public class BusinessException extends Exception {
	private static final long serialVersionUID = 6459734265489250929L;

	public BusinessException(){
		super();
	}
	
	public BusinessException(String str){
		super(str);
	}
}
