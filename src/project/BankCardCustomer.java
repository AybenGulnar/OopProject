package project;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

public class BankCardCustomer extends IndividualBankCard {

	// Constructors
	public BankCardCustomer(String cardNo, Customer owner, String cvv, Date expirationDate, Account account) {
		super(cardNo, owner, cvv, expirationDate, account);
		// TODO Auto-generated constructor stub
	}

	// Functions
	@Override
	public double calculateCardPoint() {
		// yüzde 5 puan veriyor
		double point = 0;
		for (Payment payment : getPayments()) {
			point += payment.getAmount();
		}
		return point * 0.05;

	}

	public String toString() {// overriding the toString() method
		return "Bank Card Customer";
	}

}
