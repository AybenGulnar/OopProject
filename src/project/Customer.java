package project;
	
import java.util.Random;

import java.util.*;


public abstract class Customer {
	private String name;
	private String userName;
	private String userPass;
	private String customerNo;
	private String adress;
	private String mail;
	private double income;
	private Employee chargeEmployee;
	private List<Card> cards = new ArrayList<Card>();
	private List<Account> accounts = new ArrayList<Account>();
	private List<Application> applications = new ArrayList<Application>();
	private List<Loan> loans = new ArrayList<Loan>();

	public Customer(String name,  String customerNo, String adress, String mail, double income, Employee chargeEmployee) {
		super();
		Random r = new Random(); 
		String pass = "";
		for (int i = 0; i < 10; i++) {
			pass += Character.toString((char)r.nextInt(25)+65);
		}
		userPass= pass;
		this.name = name;
		this.customerNo = customerNo;
		this.adress = adress;
		this.mail = mail;
		this.income = income;
		this.chargeEmployee = chargeEmployee;
		this.userName = customerNo;
		
	}
	
	public Customer(String name, String userPass, String customerNo, String adress, String mail, double income) {
		super();
		this.name = name;
		this.userPass = userPass;
		this.customerNo = customerNo;
		this.adress = adress;
		this.mail = mail;
		this.income = income;
		this.userName = customerNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPass() {
		return userPass;
	}

	public void setUserPass(String userPass) {
		database.General.changePass(customerNo, userPass);
		this.userPass = userPass;
	}

	public String getCustomerNo() {
		return customerNo;
	}

	public void setCustomerNo(String customerNo) {
		this.customerNo = customerNo;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public double getIncome() {
		return income;
	}

	public void setIncome(double income) {
		this.income = income;
	}

	public List<Card> getCards() {
		return cards;
	}

	public void setCards(List<Card> cards) {
		this.cards = cards;
	}

	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}

	public List<Application> getApplications() {
		return applications;
	}

	public void setApplications(List<Application> applications) {
		this.applications = applications;
	}

	public List<Loan> getLoans() {
		return loans;
	}

	public void setLoans(List<Loan> loans) {
		this.loans = loans;
	}
	
	public Employee getChargeEmployee() {
		return chargeEmployee;
	}

	public void setChargeEmployee(Employee chargeEmployee) {
		this.chargeEmployee = chargeEmployee;
	}

	public boolean resetPass() {
		System.out.println("resetPass fonksiyou boþ");
		return false;
	}
	public boolean isAvaliableForLoan() {
		System.out.println("isAvaliableForLoan fonksiyou boþ");
		return false;
	}
	public void display() {
		System.out.println("display fonksiyou boþ");
	}
	public  boolean addApplication(Application application) {
		applications.add(application);
		database.General.createApplication(application);
		System.out.println("addApplication fonksiyou boþ");
		return false;
	}
	public boolean addCard(Card card) {
		System.out.println("addCard fonksiyou boþ");
		return false;
	}
	public boolean addLoan(Loan loan) {
		System.out.println("addLoan fonksiyou boþ");
		return false;
	}
	public boolean addAccount(Account account) {
		database.General.isThereAccount("123456");
		
		database.General.createAccount(account);
		System.out.println("addAccount fonksiyou boþ");
		return false;
	}

}
