package project;


import java.sql.Date;
import java.time.LocalDate;

public class MoneyTransfer extends AccountTransaction{

	private Account forwarder;
	private Account receiver;

	

	public MoneyTransfer(Date date, String description, Account forwarder, Account receiver, double amount, String accountTransectionNo) {
		super(date, description, accountTransectionNo, amount);
		this.forwarder = forwarder;
		this.receiver = receiver;
	}

	public Account getForwarder() {
		return forwarder;
	}

	public Account getReceiver() {
		return receiver;
	}

	

	public void setForwarder(Account forwarder) {
		this.forwarder = forwarder;
	}

	public void setReceiver(Account receiver) {
		this.receiver = receiver;
	}

	
	
}
