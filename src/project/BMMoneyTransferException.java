package project;

import java.util.List;

public class BMMoneyTransferException extends BMException{

	public BMMoneyTransferException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public BMMoneyTransferException(String cause) {
		super(cause);
	}
}
