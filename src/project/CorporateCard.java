package project;


import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

public abstract class CorporateCard extends Card {
	
	public CorporateCard(String cardNo, Customer owner, String cvv, Date expirationDate) {
		super(cardNo, owner, cvv, expirationDate);
		// TODO Auto-generated constructor stub
	}

}
