package project;



public class CorporateCustomer extends Customer{
	private String taxNo;

	
	
	public CorporateCustomer(String name, String customerNo, String adress, String mail,
			double income,Employee chargeEmployee, String taxNo) {
		
		super(name, customerNo, adress, mail, income, chargeEmployee);
		this.taxNo = taxNo;
	}


	public CorporateCustomer(String name, String userPass, String customerNo, String adress, String mail, double income,
			String taxNo) {
		super(name, userPass, customerNo, adress, mail, income);
		this.taxNo = taxNo;
	}

	public String getTaxNo() {
		return taxNo;
	}

	public void setTaxNo(String taxNo) {
		this.taxNo = taxNo;
	}
	
	public boolean isAvaliableForLoan() {
		
		return false;
	}
}
