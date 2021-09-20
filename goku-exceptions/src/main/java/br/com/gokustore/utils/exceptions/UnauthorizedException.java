package br.com.gokustore.utils.exceptions;

public class UnauthorizedException extends RuntimeException{

	public UnauthorizedException() {
		super();
	}
	
	public UnauthorizedException(String msg) {
		super(msg);
	}
	
	public UnauthorizedException(Throwable t) {
		super(t);
	}
	
	public UnauthorizedException(String msg, Throwable t) {
		super(msg, t);
	}
}
