package project;

import java.util.ArrayList;
import java.util.List;

public class BMException extends Exception{
	private String acause;
	public BMException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public BMException(String cause) {
		super();
		this.acause = cause;
	}
	
	public String getaCause() {
		return this.acause;
	}
}
