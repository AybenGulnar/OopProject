package project;


public class CurrentAccount extends Account {

	// Constructors
	public CurrentAccount(Customer owner, String accountNo, Employee chargeEmployee) {
		super(owner, accountNo, chargeEmployee);
	}

	public CurrentAccount(Customer owner, String accountNo) {
		super(owner, accountNo);
	}



	// Functions
	
	public Class<? extends CurrentAccount> getType(){
		return this.getClass();
	}

}
