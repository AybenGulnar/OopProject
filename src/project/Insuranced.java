package project;


import java.sql.Date;

public class Insuranced extends TransportLoan{
	private String insuranceNo;

	public Insuranced(Customer loanTaker, Customer guarantor,  int maturity, double amount,
 Date lastPayment, Date givenLoanDate, Account givenLoanAccount, String licensePlate, int vehicleAge, String insuranceNo) {
		super( loanTaker, guarantor, maturity, amount, lastPayment, givenLoanDate,
				givenLoanAccount, licensePlate, vehicleAge);
		this.insuranceNo = insuranceNo;
	}

	public String getInsuranceNo() {
		return insuranceNo;
	}

	public void setInsuranceNo(String insuranceNo) {
		this.insuranceNo = insuranceNo;
	}
	
	public String toString() {// overriding the toString() method
		return "Insuranced";
	}
}	
