package br.com.gokustore.utils.exceptions;

public class BadRequestException extends RuntimeException{

	public BadRequestException() {
		super();
	}
	
	public BadRequestException(String msg) {
		super(msg);
	}
	
	public BadRequestException(Throwable t) {
		super(t);
	}
	
	public BadRequestException(String msg, Throwable t) {
		super(msg, t);
	}
}
