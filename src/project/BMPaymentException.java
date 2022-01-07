package project;

public class BMPaymentException extends BMException{
	public BMPaymentException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public BMPaymentException(String cause) {
		super(cause);
	}
}
