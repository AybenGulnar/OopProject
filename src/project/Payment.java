package project;

import java.sql.Date;
import java.time.LocalDate;

public class Payment extends AccountTransaction {
	private Card card;
	private Account forwarder = null;

	public Payment(Date date, String description, Card card, double amount, String accountTransectionNo) {
		super(date, description, accountTransectionNo,amount );
		this.card = card;
		if(card instanceof project.IndividualBankCard) {
			project.IndividualBankCard bankCard = (project.IndividualBankCard) card;
			forwarder = bankCard.getAccount();
		}
		else if(card instanceof project.CorporateBankCard) {
			project.CorporateBankCard bankCard = (project.CorporateBankCard) card;
			forwarder = bankCard.getAccount();
		}
	}

	public Card getCard() {
		return card;
	}

	public Account getForwarder() {
		return forwarder;
	}

	public void setCard(Card card) {
		this.card = card;
	}

	public void setForwarder(Account forwarder) {
		this.forwarder = forwarder;
	}

}
