package project;


import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

public abstract class Card {

	private String cardNo; 
	private Customer owner;
	private String cvv;
	private Date expirationDate;
	private String cardPass;
	private double carPoint=0;
	private ArrayList<Payment> payments = new ArrayList<Payment>(); // Create an ArrayList object

	public Card(String cardNo, Customer owner, String cvv, Date expirationDate) {
		super();
		this.cardNo = cardNo;
		this.owner = owner;
		this.cvv = cvv;
		this.expirationDate = expirationDate;
		Random r = new Random(); 
		String pass = "";
		for (int i = 0; i < 4; i++) {
			pass += Character.toString((char)r.nextInt(9)+48);
		}
		this.cardPass = pass;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	
	public Customer getOwner() {
		return owner;
	}

	public void setOwner(Customer owner) {
		this.owner = owner;
	}

	public String getCvv() {
		return cvv;
	}

	public void setCvv(String cvv) {
		this.cvv = cvv;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}
	
	public String getCardPass() {
		return cardPass;
	}

	public void setCardPass(String cardPass) {
		this.cardPass = cardPass;
	}

	public double getCarPoint() {
		return carPoint;
	}
	
	public void setCarPoint(double carPoint) {
		this.carPoint = carPoint;
	}

	public ArrayList<Payment> getPayments() {
		return payments;
	}

	public void setPayments(ArrayList<Payment> payments) {
		this.payments = payments;
	}

	// Functions
	public void addPayment(Payment payment) {
		payments.add(payment);
	}

	public double calculateCardPoint() {
		double total = 0;
		for(Payment payment: payments){
			total += payment.getAmount();
		}
		
		return total *0.05;
	}

	public double calculate() {
		return 0;
	}
}
