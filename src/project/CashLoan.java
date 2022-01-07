package project;


import java.sql.Date;

public abstract class CashLoan extends CorporateLoan{

	public CashLoan( Customer loanTaker, Customer guarantor, int maturity, double amount,
			Date lastPayment, Date givenLoanDate, Account givenLoanAccount) {
		super(loanTaker, guarantor, maturity, amount,  lastPayment, givenLoanDate, givenLoanAccount);
	}


	
}
