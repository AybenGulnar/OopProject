package project;


import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

public abstract class IndividualCreditCard extends IndividualCard {

	// Variables
	private double cardLimit;
	private double maxCardLimit;
	private double availableLimit;

	// Constructors
	public IndividualCreditCard(String cardNo, Customer owner, String cvv, Date expirationDate, double cardLimit, 
			double maxCardLimit) {
		super(cardNo, owner, cvv, expirationDate);
		this.cardLimit = cardLimit;
		this.maxCardLimit = maxCardLimit;
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

	public double getMaxCardLimit() {
		return maxCardLimit;
	}

	public void setMaxCardLimit(double maxCardLimit) {
		this.maxCardLimit = maxCardLimit;
	}

	public double getAvailableLimit() {
		return availableLimit;
	}

	public void setAvailableLimit(double availableLimit) {
		this.availableLimit = availableLimit;
	}

	// Functions
	public void increaseLimit(double amount) {
		if(cardLimit+ amount <= maxCardLimit) {
			cardLimit += amount;
		}
	}

}
