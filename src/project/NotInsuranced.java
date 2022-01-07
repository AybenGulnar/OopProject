package project;


import java.sql.*;

public class NotInsuranced extends TransportLoan{

	public NotInsuranced( Customer loanTaker, Customer guarantor, int maturity, double amount, 
			Date lastPayment, Date givenLoanDate, Account givenLoanAccount, String licensePlate, int vehicleAge) {
		super( loanTaker, guarantor, maturity, amount, lastPayment, givenLoanDate,
				givenLoanAccount, licensePlate, vehicleAge);
	}	
	
	public String toString() {// overriding the toString() method
		return "NotInsuranced";
	}
}
