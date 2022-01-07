package project;


import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

public class BankCardBusiness extends CorporateBankCard {

	// Constructors
	public BankCardBusiness(String cardNo, Customer owner, String cvv, Date expirationDate, Account account) {
		super(cardNo, owner, cvv, expirationDate, account);
	}
	
	public String toString() {// overriding the toString() method
		return "Bank Card Business";
	}
}
