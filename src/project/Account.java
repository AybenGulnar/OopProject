package project;
import java.util.ArrayList; // import the ArrayList class

import database.*;

public abstract class Account {
	// Variables
	private Customer owner;
	private String accountNo;
	ArrayList<AccountTransaction> accountTransactions = new ArrayList<AccountTransaction>(); // Create an ArrayList  // object
	private boolean isBlocked = false;
	private double balance = 0;
	private Employee chargeEmployee;

	// Constructors
	public Account(Customer owner, String accountNo, Employee chargeEmployee) {
		this.owner = owner;
		this.accountNo = accountNo;
		this.chargeEmployee = chargeEmployee;
	}
	
	public Account(Customer owner, String accountNo) {
		this.owner = owner;
		this.accountNo = accountNo;
	}
	
	// Getters and Setters

	public Customer getOwner() {
		return owner;
		
	}

	public void setOwner(Customer owner) {
		this.owner = owner;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public ArrayList<AccountTransaction> getAccountTransactions() {
		return accountTransactions;
	}

	public void setAccountTransactions(ArrayList<AccountTransaction> accountTransactions) {
		this.accountTransactions = accountTransactions;
	}

	public boolean isBlocked() {
		return isBlocked;
	}

	public void setBlocked(boolean isBlocked) {
		if(isBlocked) {
		 database.Account.blockAccount(this);
		}
		else {
			database.Account.activateAccount(this);
		}
		this.isBlocked = isBlocked;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public Employee getChargeEmployee() {
		return chargeEmployee;
	}

	public void setChargeEmployee(Employee chargeEmployee) {
		this.chargeEmployee = chargeEmployee;
	}
	
	public abstract Class<? extends Account> getType();

}
