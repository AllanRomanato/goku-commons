package br.com.gokustore.utils.exceptions;

public class InternalServerErrorException extends RuntimeException{

	public InternalServerErrorException() {
		super();
	}
	
	public InternalServerErrorException(String msg) {
		super(msg);
	}
	
	public InternalServerErrorException(Throwable t) {
		super(t);
	}
	
	public InternalServerErrorException(String msg, Throwable t) {
		super(msg, t);
	}
	
}
