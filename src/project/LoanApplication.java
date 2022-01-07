package project;


public class LoanApplication extends Application{
	
	private Loan loan;

	public LoanApplication(String applicationNo, Customer applicant, Loan loan) {
		super(applicationNo, applicant );
		this.loan = loan;
	}
	
	public LoanApplication(String applicationNo, Customer applicant, Loan loan, String applicationStatus) {
		super(applicationNo, applicant, applicationStatus );
		this.loan = loan;
	}

	public Loan getLoan() {
		return loan;
	}

	public void setLoan(Loan loan) {
		this.loan = loan;
	}
	
	public void applicationAcceptance() {
		setApplicationResult(true);
	}

	public void applicationRefuse() {
		setApplicationResult(false);
	}
}
