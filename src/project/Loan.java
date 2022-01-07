package project;


import java.sql.Date;
import java.util.Random;

public abstract class Loan {
	
	private String loanNo = "NaN";
	private Customer loanTaker;
	private Customer guarantor;
	private double interest = 0.25;
	private int maturity;
	private double amount;
	private double remainingDebt;
	private Date lastPayment;
	private Date givenLoanDate;
	private Account givenLoanAccount;
	private double refundedAmount;
	
	public Loan(Customer loanTaker, Customer guarantor, int maturity, double amount, Date lastPayment, Date givenLoanDate, Account givenLoanAccount) {
		super();
		Random r = new Random();
		this.loanNo = String.valueOf(r.nextInt(9999999));
		this.loanTaker = loanTaker;
		this.guarantor = guarantor;
		this.interest = 0.25;
		this.maturity = maturity;
		this.amount = amount;
		this.remainingDebt = amount;
		this.lastPayment = lastPayment;
		this.givenLoanDate = givenLoanDate;
		this.givenLoanAccount = givenLoanAccount;
	}

	public String getLoanNo() {
		return loanNo;
	}

	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}

	public Customer getLoanTaker() {
		return loanTaker;
	}

	public void setLoanTaker(Customer loanTaker) {
		this.loanTaker = loanTaker;
	}

	public Customer getGuarantor() {
		return guarantor;
	}

	public void setGuarantor(Customer guarantor) {
		this.guarantor = guarantor;
	}

	public double getInterest() {
		return interest;
	}

	public void setInterest(double interest) {
		this.interest = interest;
	}

	public int getMaturity() {
		return maturity;
	}

	public void setMaturity(int maturity) {
		this.maturity = maturity;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getRemainingDebt() {
		return remainingDebt;
	}

	public void setRemainingDebt(double remainingDebt) {
		this.remainingDebt = remainingDebt;
	}

	public Date getLastPayment() {
		return lastPayment;
	}

	public void setLastPayment(Date lastPayment) {
		this.lastPayment = lastPayment;
	}

	public Date getGivenLoanDate() {
		return givenLoanDate;
	}

	public void setGivenLoanDate(Date givenLoanDate) {
		this.givenLoanDate = givenLoanDate;
	}

	public Account getGivenLoanAccount() {
		return givenLoanAccount;
	}

	public void setGivenLoanAccount(Account givenLoanAccount) {
		this.givenLoanAccount = givenLoanAccount;
	}
	
	public double getRefundedAmount() {
		return refundedAmount;
	}

	public void setRefundedAmount(double refundedAmount) {
		this.refundedAmount = refundedAmount;
	}
	

	public void payInstallments(double amount) {
		setRemainingDebt(getRemainingDebt() -amount);
	}

	public void payInstallments() {
		setRemainingDebt(0);
	}

	public double calculatePayingPlan() {
		return getAmount()/getMaturity();
	}
	
}
