package project;

import database.*;

import java.sql.Date;
import java.time.LocalDate;

public abstract class AccountTransaction {
	private Date date;
	private String description;
	private String accountTransectionNo;
	private double amount;

	public AccountTransaction(Date date, String description, String accountTransectionNo, double amount) {
		super();
		this.date = date;
		this.description = description;
		this.accountTransectionNo = accountTransectionNo;
		this.amount = amount;
	}

	public Date getDate() {
		return date;
	}

	public String getDescription() {
		return description;
	}

	public String getAccountTransectionNo() {
		return accountTransectionNo;
	}
	
	public double getAmount() {
		return amount;
	}


	public void setDate(Date date) {
		this.date = date;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setAccountTransectionNo(String accountTransectionNo) {
		this.accountTransectionNo = accountTransectionNo;
	}
	
	public void setAmount(double amount) {
		this.amount = amount;
	}

}
