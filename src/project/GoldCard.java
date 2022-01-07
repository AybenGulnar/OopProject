package project;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

public class GoldCard extends IndividualCreditCard {

	// Constructors
	public GoldCard(String cardNo, Customer owner, String cvv, Date expirationDate, double cardLimit,
			double maxCardLimit, double availableLimit) {
		super(cardNo, owner, cvv, expirationDate, cardLimit, maxCardLimit);
		// TODO Auto-generated constructor stub
	}

	// Functions
	public double calculateCardPoint() {
		// yüzde 5 puan veriyor
		double point = 0;
		for (Payment payment : getPayments()) {
			point += payment.getAmount();
		}
		return point * 0.05;
	}
	
	public String toString() {// overriding the toString() method
		return "Gold Card";
	}

}
