package project;


import java.util.*;
import java.sql.Date;

public abstract class IndividualLoan extends Loan{

	
	public IndividualLoan(Customer loanTaker, Customer guarantor,  int maturity,
			double amount, java.sql.Date lastPayment, java.sql.Date givenLoanDate, Account givenLoanAccount) {
		super( loanTaker, guarantor, maturity, amount, lastPayment, givenLoanDate,
				givenLoanAccount);
	}
}
