package bankmanagement;

import java.sql.Date;
import java.util.Random;

import project.*;

public class functions {

	public static project.MoneyTransfer createMoneyTransfer(String description, project.Account forwarder,
			project.Account receiver, double amount) {
		// Date date, String description, Account forwarder, Account receiver, double
		// amount, String accountTransectionNo
		long millis = System.currentTimeMillis();
		java.sql.Date date = new java.sql.Date(millis);
		
		project.MoneyTransfer moneyTransfer = new project.MoneyTransfer(date, description, forwarder, receiver, amount,
				database.General.createAccountTransactionNo());
		
		return moneyTransfer;
	}
	
	public static project.CurrentAccount createCurrentAccount(Customer customer){
		project.CurrentAccount account = new CurrentAccount(customer, database.General.createCustomerNo());
		database.General.createAccount(account);
		return account;
	}

	public static project.IndividualCustomer createIndividualCustomer(String name, String userPass, String adress,
			String mail, double income, String individualID, String occupation, Date birthDate) {
		// String name, String userPass, String customerNo, String adress, String mail,
		// double income, String individualID, String occupation, Date birthDate
		project.IndividualCustomer customer = new IndividualCustomer(name, userPass, database.General.createAccountNo(),
				adress, mail, income, individualID, occupation, birthDate);
		database.General.createCustomer(customer);
		return customer;
	}
	
	public static project.IndividualCustomer createIndividualCustomer(String name, String adress, String mail,
			double income, project.Employee chargeEmployee, String individualID, String occupation, Date birthDate) {
		// String name, String customerNo, String adress, String mail, double income,
		// Employee chargeEmployee, String individualID, String occupation, Date
		// birthDate)
		project.IndividualCustomer customer = new IndividualCustomer(name, database.General.createCustomerNo(), adress, mail, income,
				chargeEmployee, individualID, occupation, birthDate);
		database.General.createCustomer(customer);
		return customer;
	}

	public static project.Worker createWorker(String name, String id, double salary, String mail,
			project.Employee chargeEmployee) throws BMAddWorkerException {
		if (chargeEmployee instanceof project.CanAddWorker) {
			project.Worker worker = new Worker(name, id, salary, mail, chargeEmployee);
			database.General.createEmployee(worker);
			return worker;
		} else {
			throw new project.BMAddWorkerException("You are not authorized for this");
		}
	}

	public static project.Payment createPayment(project.Card card, double amount) {
		long millis = System.currentTimeMillis();
		java.sql.Date date = new java.sql.Date(millis);
		project.Payment payment = new project.Payment(date, "Payment with card", card, amount, database.General.createAccountTransactionNo());
		database.General.createAccountTransaction(payment);
		return payment;
	}

	public static project.CorporateCustomer createCorporateCustomer(String name, String adress, String mail,
			double income, project.Employee chargeEmployee, String taxNo) {

		project.CorporateCustomer customer = new CorporateCustomer(name, database.General.createCustomerNo(), adress, mail,
				income, chargeEmployee, taxNo);
		database.General.createCustomer(customer);
		return customer;
	}

	public static project.CorporateCustomer createCorporateCustomer(String name, String userPass, String adress,
			String mail, double income, String taxNo) {

		project.CorporateCustomer customer = new CorporateCustomer(name, userPass,
				database.General.createCustomerNo(), adress, mail, income, taxNo);
		database.General.createCustomer(customer);
		return customer;
	}

	

	public static project.IndividualCreditCard createIndividualCreditCard(double limit, project.Customer customer) {
		
		long millis = System.currentTimeMillis();
		java.sql.Date date = new java.sql.Date(millis);
		Random r = new Random(); 
		int cvv = r.nextInt(899)+100;
		if (limit <= 5000) {
			
			
			project.SilverCard card = new SilverCard(database.General.createCardNo(), customer, String.valueOf(cvv),
					date, limit, 799999, limit);
			database.General.createCard(card);
			return card;
		} else if (limit <= 10000) {
			project.GoldCard card = new GoldCard(database.General.createCardNo(), customer, String.valueOf(cvv), date,
					limit, 799999, limit);
			database.General.createCard(card);
			return card;
		}
		return null;
	}

	public static project.CorporateCreditCard createCorporateCreditCard(double limit, project.Customer customer) {
		long millis = System.currentTimeMillis();
		java.sql.Date date = new java.sql.Date(millis);
		Random r = new Random(); 
		int cvv = r.nextInt(899)+100;
		if (limit <= 25000) {

			// String cardNo, Customer owner, String cvv, Date expirationDate, double
			// cardLimit
			project.SilverCardBusiness card = new project.SilverCardBusiness(database.General.createCardNo(), customer,
					String.valueOf(cvv), date, limit);
			database.General.createCard(card);
			return card;
		} else if (limit <= 50000) {

			project.GoldCardBusiness card = new GoldCardBusiness(database.General.createCardNo(), customer,
					String.valueOf(cvv), date, limit);
			database.General.createCard(card);
			return card;
		}
		return null;

	}

	public static project.CardApplication createCardApplication(project.Card card) {
		if (card instanceof project.CorporateCreditCard) {
			project.CorporateCreditCard creditCard = (CorporateCreditCard) card;
			database.General.createCard(creditCard);
			project.CardApplication application = new project.CardApplication(database.General.createApplicationNo(), card.getOwner(), creditCard);
			
			return application;
		} else if (card instanceof project.IndividualCreditCard) {
			project.IndividualCreditCard creditCard = (IndividualCreditCard) card;
			database.General.createCard(creditCard);
			project.CardApplication application = new project.CardApplication(database.General.createApplicationNo(), card.getOwner(), creditCard);
			return application;
		}
		return null;
	}

	public static project.LoanApplication createLoanApplication(project.Loan loan) {
		if (loan instanceof project.CorporateLoan) {
			project.CorporateLoan corporateLoan = (CorporateLoan) loan;
			database.General.createLoan(corporateLoan);
			project.LoanApplication application = new project.LoanApplication(database.General.createApplicationNo(), loan.getLoanTaker(), corporateLoan);
			
			return application;
		} else if (loan instanceof project.IndividualLoan) {
			project.IndividualLoan individualLoan = (IndividualLoan) loan;
			database.General.createLoan(individualLoan);
			project.LoanApplication application = new project.LoanApplication(database.General.createApplicationNo(), loan.getLoanTaker(), individualLoan);
			return application;
		}
		return null;
	}

	public static project.AccountApplication createAccountApplication(project.Account account) {
		if (account instanceof project.CurrentAccount) {
			project.CurrentAccount currentAccount = (CurrentAccount) account;
			database.General.createAccount(currentAccount);
			project.AccountApplication application = new project.AccountApplication(database.General.createApplicationNo(), account.getOwner(),
					currentAccount);
			return application;
		} else if (account instanceof project.TimeDepositAccount) {
			project.TimeDepositAccount timeDepositAccount = (TimeDepositAccount) account;
			database.General.createAccount(timeDepositAccount);
			project.AccountApplication application = new project.AccountApplication(database.General.createApplicationNo(), account.getOwner(),
					timeDepositAccount);
			return application;
		}
		return null;
	}

	public static void doMoneyTransfer(project.MoneyTransfer moneyTransfer) throws project.BMMoneyTransferException {
		String cause = null;
		if (moneyTransfer.getForwarder().getBalance() < moneyTransfer.getAmount()) {
			cause = "Insufficient balance";
		} else if (moneyTransfer.getForwarder().isBlocked()) {
			cause = "Forwarder Account Blocked";
		} else if (moneyTransfer.getReceiver().isBlocked()) {
			cause = "Receiver Account Blocked";
		}

		if (cause == null) {
			database.General.createAccountTransaction(moneyTransfer);
			database.Account.addBalance(moneyTransfer.getForwarder(), -moneyTransfer.getAmount());
			database.Account.addBalance(moneyTransfer.getReceiver(), moneyTransfer.getAmount());
		} else {
			throw new project.BMMoneyTransferException(cause);
		}

	}

	public static void confirmLoan(project.LoanApplication loanApplication, project.Employee employee)
			throws project.BMApplicationDecideException {
		boolean canDecide = false;

		for (Object a : employee.getClass().getInterfaces()) {

			if (a == project.CanDecideLoan.class) {
				canDecide = true;
			}
		}
		if (canDecide) {
			loanApplication.setApplicationResult(true);
			database.Application.applicationApprovet(loanApplication);
			database.Application.setApplicationStatus(loanApplication, "Confirmed");
			database.Application.setApplicationEmployee(loanApplication, employee);
		} else {
			throw new project.BMApplicationDecideException("Employee can not decide");
		}
	}

	public static void refuseLoan(project.LoanApplication loanApplication, project.Employee employee)
			throws project.BMApplicationDecideException {

		boolean canDecide = false;

		for (Object a : employee.getClass().getInterfaces()) {

			if (a == project.CanDecideLoan.class) {
				canDecide = true;
			}
		}
		if (canDecide) {
			loanApplication.setApplicationResult(false);
			database.Application.applicationRefuse(loanApplication);
			database.Application.setApplicationStatus(loanApplication, "Denied");
			database.Application.setApplicationEmployee(loanApplication, employee);
		} else {
			throw new project.BMApplicationDecideException("Employee can not decide");
		}
	}

	public static void confirmCard(project.CardApplication cardApplication, project.Employee employee)
			throws project.BMApplicationDecideException {
		boolean canDecide = false;

		for (Object a : employee.getClass().getInterfaces()) {

			if (a == project.CanDecideCard.class) {
				canDecide = true;
			}
		}
		if (canDecide) {
			cardApplication.setApplicationResult(true);
			database.Application.applicationApprovet(cardApplication);
			database.Application.setApplicationStatus(cardApplication, "Confirmed");
			database.Application.setApplicationEmployee(cardApplication, employee);
		} else {
			throw new project.BMApplicationDecideException("Employee can not decide");
		}
	}

	public static void refuseCard(project.CardApplication cardApplication, project.Employee employee)
			throws project.BMApplicationDecideException {

		boolean canDecide = false;

		for (Object a : employee.getClass().getInterfaces()) {

			if (a == project.CanDecideCard.class) {
				canDecide = true;
			}
		}
		if (canDecide) {
			cardApplication.setApplicationResult(false);
			database.Application.applicationRefuse(cardApplication);
			database.Application.setApplicationStatus(cardApplication, "Denied");
			database.Application.setApplicationEmployee(cardApplication, employee);
		} else {
			throw new project.BMApplicationDecideException("Employee can not decide");
		}
	}

	public static void confirmAccount(project.AccountApplication accountApplication, project.Employee employee)
			throws project.BMApplicationDecideException {
		boolean canDecide = false;

		for (Object a : employee.getClass().getInterfaces()) {

			if (a == project.CanDecideOpenMoneyAccount.class) {
				canDecide = true;
			}
		}
		if (canDecide) {
			accountApplication.setApplicationResult(true);
			database.Application.applicationRefuse(accountApplication);
			database.Application.setApplicationStatus(accountApplication, "Confirmed");
			database.Application.setApplicationEmployee(accountApplication, employee);
		} else {
			throw new project.BMApplicationDecideException("Employee can not decide");
		}
	}

	public static void refuseAccount(project.AccountApplication accountApplication, project.Employee employee)
			throws project.BMApplicationDecideException {
		boolean canDecide = false;

		for (Object a : employee.getClass().getInterfaces()) {

			if (a == project.CanDecideOpenMoneyAccount.class) {
				canDecide = true;
			}
		}
		if (canDecide) {
			accountApplication.setApplicationResult(false);
			database.Application.applicationRefuse(accountApplication);
			database.Application.setApplicationStatus(accountApplication, "Denied");
			database.Application.setApplicationEmployee(accountApplication, employee);
		} else {
			throw new project.BMApplicationDecideException("Employee can not decide");
		}
	}

	public static boolean checkCustomerPass(String userName, String password) {

		if (database.General.checkPass(userName, password)) {
			System.out.println("\nLogin Succesful!");
			return true;
		} else {
			System.out.println("\nError! Wrong password or username!");
			return false;
		}
	}

	public static boolean checkEmployeePass(String userName, String password) {

		if (database.General.checkPass(userName, password)) {
			System.out.println("\nLogin Succesful!");
			return true;
		} else {
			System.out.println("\nError! Wrong password or username!");
			return false;
		}
	}

	@SuppressWarnings("deprecation")
	public static void doPaymentWithCard(project.Payment payment, String cvv) throws project.BMPaymentException {

		String cause = null;
		long millis = System.currentTimeMillis();
		java.sql.Date date = new java.sql.Date(millis);
		if (payment.getCard().getCvv() != cvv) {
			cause = "Wrong card information";
		} else if (payment.getCard().getExpirationDate().getYear() < date.getYear()
				|| (payment.getCard().getExpirationDate().getYear() == date.getYear()
						&& payment.getCard().getExpirationDate().getMonth() < date.getMonth())) {
			cause = "Expration date error";
		}

		if (payment.getCard() instanceof project.CorporateBankCard) {
			project.CorporateBankCard bankCard = (CorporateBankCard) payment.getCard();
			if (bankCard.getBalance() < payment.getAmount()) {
				cause = "Insufficient balance";
			} else {
				database.Card.addBalance(bankCard, -payment.getAmount());
			}
		} else if (payment.getCard() instanceof project.IndividualBankCard) {
			project.IndividualBankCard bankCard = (IndividualBankCard) payment.getCard();
			if (bankCard.getBalance() < payment.getAmount()) {
				cause = "Insufficient balance";
			} else {
				database.Card.addBalance(bankCard, -payment.getAmount());
			}
		} else if (payment.getCard() instanceof project.CorporateCreditCard) {
			project.CorporateCreditCard creditCard = (CorporateCreditCard) payment.getCard();
			if (creditCard.getAvailableLimit() < payment.getAmount()) {
				cause = "Insufficient balance";
			} else {
				database.Card.addAvailableLimit(creditCard, -payment.getAmount());
			}

		} else if (payment.getCard() instanceof project.IndividualCreditCard) {
			project.IndividualCreditCard creditCard = (IndividualCreditCard) payment.getCard();
			if (creditCard.getAvailableLimit() < payment.getAmount()) {
				cause = "Insufficient balance";
			} else {
				database.Card.addAvailableLimit(creditCard, -payment.getAmount());
			}
		}

		if (cause == null) {
			database.General.createAccountTransaction(payment);
		}

	}

	public static void changePassword(project.Customer customer, String newPassword) {
		database.General.changePass(customer.getCustomerNo(), newPassword);
	}

	public static void changeEmployeePassword(project.Employee employee, String newPassword) {
		database.General.changePass(employee.getUserName(), newPassword);
	}

	public static void doApplication(project.Application application) {
		database.General.createApplication(application);
	}

}