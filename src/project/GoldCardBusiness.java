package project;


import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

public class GoldCardBusiness extends CorporateCreditCard {

	public GoldCardBusiness(String cardNo, Customer owner, String cvv, Date expirationDate,double cardLimit ) {
		super(cardNo, owner, cvv, expirationDate, cardLimit);
		// TODO Auto-generated constructor stub
	}

	public String toString() {// overriding the toString() method
		return "Gold Card Business";
	}
	
}
