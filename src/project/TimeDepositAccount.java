package project;


import java.sql.Date;
import java.time.LocalTime;

public class TimeDepositAccount extends Account {

	// Variables
	private CurrentAccount mainAccount;
	private Date startingDate;
	private Date endDate;
	private double interest = -1.0;

	// Constructors

	public TimeDepositAccount(Customer owner, String accountNo, CurrentAccount mainAccount,
			Date startingDate, Date endDate, double interest, Employee chargeEmployee) {
		super(owner, accountNo, chargeEmployee);
		this.mainAccount = mainAccount;
		this.startingDate = startingDate;
		this.endDate = endDate;
		this.interest = interest;
	}
	
	

	public TimeDepositAccount(Customer owner, String accountNo, CurrentAccount mainAccount,
			Date startingDate, Date endDate, double interest) {
		super(owner, accountNo);
		this.mainAccount = mainAccount;
		this.startingDate = startingDate;
		this.endDate = endDate;
		this.interest = interest;
	}



	// Getters and Setters
	public CurrentAccount getMainAccount() {
		return mainAccount;
	}

	public void setMainAccount(CurrentAccount mainAccount) {
		this.mainAccount = mainAccount;
	}

	public Date getStartingDate() {
		return startingDate;
	}

	public void setStartingDate(Date startingDate) {
		this.startingDate = startingDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public double getInterest() {
		return interest;
	}

	public void setInterest(double interest) {
		this.interest = interest;
	}
	
	public Class<? extends TimeDepositAccount> getType(){
		return this.getClass();
	}
}
