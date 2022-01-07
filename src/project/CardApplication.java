package project;


public class CardApplication extends Application{
	
	private Card card;

	public CardApplication(String applicationNo, Customer applicant,  Card card) {
		super(applicationNo, applicant);
		this.card = card;
	}
	
	public CardApplication(String applicationNo, Customer applicant,  Card card, String applicationStatus) {
		super(applicationNo, applicant, applicationStatus);
		this.card = card;
	}

	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}
	
	public void applicationAcceptance() {
		setApplicationResult(true);
	}

	public void applicationRefuse() {
		setApplicationResult(false);
	}
	
}
