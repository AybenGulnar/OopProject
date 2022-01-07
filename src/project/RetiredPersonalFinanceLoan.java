package project;


import java.sql.Date;

public class RetiredPersonalFinanceLoan extends PersonalFinanceLoan{

	public RetiredPersonalFinanceLoan( Customer loanTaker, Customer guarantor,  int maturity,
			double amount, Date lastPayment, Date givenLoanDate, Account givenLoanAccount) {
		super( loanTaker, guarantor, maturity, amount,  lastPayment, givenLoanDate, givenLoanAccount);
		// TODO Auto-generated constructor stub
	}
	
	public String toString() {// overriding the toString() method
		return "RetiredPersonalFinanceLoan";
	}
}
