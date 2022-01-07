package project;


import java.sql.Date;
import java.time.LocalDate;

public class IndividualCustomer extends Customer{
	private String individualID;
	private String occupation;
	private Date birthDate;
	
//String name,  String customerNo, String adress, String mail, double income, Employee chargeEmployee
	public IndividualCustomer(String name, String userPass, String customerNo, String adress, String mail, double income,
			String individualID, String occupation, Date birthDate) {
		super(name, userPass, customerNo, adress, mail, income);
		this.individualID = individualID;
		this.occupation = occupation;
		this.birthDate = birthDate;
	}

	public IndividualCustomer(String name, String customerNo, String adress, String mail, double income,
			Employee chargeEmployee, String individualID, String occupation, Date birthDate) {
		super(name, customerNo, adress, mail, income, chargeEmployee);
		this.individualID = individualID;
		this.occupation = occupation;
		this.birthDate = birthDate;
	}
	
	

	public String getIndividualID() {
		return individualID;
	}

	public void setIndividualID(String individualID) {
		this.individualID = individualID;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}


	public boolean isAvaliableForLoan() {
		System.out.println("isAvaliableForLoan fonksiyou boþ");
		return false;
	}


	
	
	
}
