package project;


import java.sql.Date;

public class EmployeeHousingLoan extends HousingLoan{

	public EmployeeHousingLoan( Customer loanTaker, Customer guarantor,int maturity,
			double amount, Date lastPayment, Date givenLoanDate, Account givenLoanAccount,
			String deed) {
		super( loanTaker, guarantor, maturity, amount, lastPayment, givenLoanDate, givenLoanAccount, deed);
	}
	
	public String toString() {// overriding the toString() method
		return "EmployeeHousingLoan";
	}
}
