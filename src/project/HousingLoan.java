package project;


import java.sql.Date;

public abstract class HousingLoan extends IndividualLoan{
	public String deed;

	public HousingLoan(Customer loanTaker, Customer guarantor, int maturity,
			double amount, Date lastPayment, Date givenLoanDate, Account givenLoanAccount, String deed) {
		super( loanTaker, guarantor, maturity, amount,  lastPayment, givenLoanDate, givenLoanAccount);
		
		this.deed = deed;
	}
	
	public String getDeed() {
		return deed;
	}

	public void setDeed(String deed) {
		this.deed = deed;
	}

	
}
