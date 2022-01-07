package project;


import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

public abstract class CorporateCreditCard extends CorporateCard {

	// Variables
	private double cardLimit;
	private double availableLimit;

	// Constructors
	public CorporateCreditCard(String cardNo, Customer owner, String cvv, Date expirationDate, double cardLimit ) {
		super(cardNo, owner, cvv, expirationDate);
		this.cardLimit = cardLimit;
		double spending =0;
		for(Payment payment: getPayments()) {
			spending+= payment.getAmount();
		}
		this.availableLimit = this.cardLimit - spending;
	}

	// Getters and Setters
	public double getCardLimit() {
		return cardLimit;
	}

	public void setCardLimit(double cardLimit) {
		this.cardLimit = cardLimit;
	}

	public double getAvailableLimit() {
		return availableLimit;
	}

	public void setAvailableLimit(double availableLimit) {
		this.availableLimit = availableLimit;
	}

}
