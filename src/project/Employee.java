package project;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class Employee {
	private String name;
	private String userPassword;
	private String userName;
	private String id;
	private double salary;
	private String mail;
	private double bonus;

	ArrayList<Worker> addedWorker = new ArrayList<>();
	ArrayList<Customer> addedCustomer = new ArrayList<>();
	ArrayList<Account> addMoneyAccount = new ArrayList<>();
	ArrayList<Card> decidedCard = new ArrayList<>();
	ArrayList<Loan> decidedLoan = new ArrayList<>();
	ArrayList<AccountTransaction> displayedAccountTransaction = new ArrayList<>();
	

	
	public Employee(String name, String userPassword, String id, double salary, String mail) {
		super();
		this.name = name;
		this.userPassword = userPassword;
		this.userName = name + id;
		this.id = id;
		this.salary = salary;
		this.mail = mail;
	}
	
	public Employee(String name, String id, double salary, String mail) {
		super();
		Random r = new Random(); 
		String pass = "";
		for (int i = 0; i < 10; i++) {
			pass += Character.toString((char)r.nextInt(25)+65);
		}
		this.name = name;
		this.userPassword = pass;
		this.userName = name + id;
		this.id = id;
		this.salary = salary;
		this.mail = mail;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public ArrayList<Worker> getAddedWorker() {
		return addedWorker;
	}

	public void setAddedWorker(ArrayList<Worker> addedWorker) {
		this.addedWorker = addedWorker;
	}

	public ArrayList<Customer> getAddedCustomer() {
		return addedCustomer;
	}

	public void setAddedCustomer(ArrayList<Customer> addedCustomer) {
		this.addedCustomer = addedCustomer;
	}

	public ArrayList<Account> getAddMoneyAccount() {
		return addMoneyAccount;
	}

	public void setAddMoneyAccount(ArrayList<Account> addMoneyAccount) {
		this.addMoneyAccount = addMoneyAccount;
	}

	public ArrayList<Card> getDecidedCard() {
		return decidedCard;
	}

	public void setDecidedCard(ArrayList<Card> decidedCard) {
		this.decidedCard = decidedCard;
	}

	public ArrayList<Loan> getDecidedLoan() {
		return decidedLoan;
	}

	public void setDecidedLoan(ArrayList<Loan> decidedLoan) {
		this.decidedLoan = decidedLoan;
	}
	
	public double getBonus() {
		return bonus;
	}

	public void setBonus(double bonus) {
		this.bonus = bonus;
	}

	public abstract void calculateBonus();

}
