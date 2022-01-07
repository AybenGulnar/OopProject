package project;


import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

public abstract class IndividualBankCard extends IndividualCard {

	// Variables
	private double balance;
	private Account account;

	// Constructors
	public IndividualBankCard(String cardNo, Customer owner, String cvv, Date expirationDate, Account account) {
		super(cardNo, owner, cvv, expirationDate);
		this.balance = account.getBalance();
		this.account = account;
	}

	// Getters and Setters
	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

}
