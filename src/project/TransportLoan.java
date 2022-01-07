package project;


import java.sql.Date;

public abstract class TransportLoan extends IndividualLoan{
	
	private String licensePlate;
	private int vehicleAge;
	

	public TransportLoan (Customer loanTaker, Customer guarantor,  int maturity,
			double amount, Date lastPayment, Date givenLoanDate, Account givenLoanAccount, 
			String licensePlate, int vehicleAge) {
		super(loanTaker, guarantor, maturity, amount, lastPayment, givenLoanDate,
				givenLoanAccount);
		
		this.licensePlate = licensePlate;
		this.vehicleAge = vehicleAge;
	}


	public String getLicensePlate() {
		return licensePlate;
	}


	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}


	public int getVehicleAge() {
		return vehicleAge;
	}


	public void setVehicleAge(int vehicleAge) {
		this.vehicleAge = vehicleAge;
	}

	
	
	
}
