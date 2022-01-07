package database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import project.CurrentAccount;

import project.Employee;

public class Customer {
	public static String database_ = "jdbc:mysql://localhost:3306/denemeoop";
	public static String databaseUsername = "denemeoop";
	public static String databasePassword = "denemeoop";
	public static ArrayList<project.Account> getAccounts(project.Customer customer) {
		
		ArrayList<project.Account> accounts = new ArrayList<project.Account>();
		String[] tables = { "CurrentAccount", "TimeDepositAccount" };
		for (String table : tables) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection connection = DriverManager.getConnection(database_,databaseUsername, databasePassword);
				Statement stmt = (Statement) connection.createStatement();

				ResultSet query1 = stmt.executeQuery(
						"SELECT * FROM " + table + " WHERE customerNo = '" + customer.getCustomerNo() + "'");
				while (query1.next()) {
					if (table == "CurrentAccount") {
						// customerNo accountNo amount isBlocked

						String account_No = query1.getString("accountNo");
						double amount = (double)query1.getDouble("amount");
						String chargeEmployee = query1.getString("chargeEmployee");
						boolean isBlocked = false;
						if (query1.getInt("isBlocked") == 1) {
							isBlocked = true;
						} else {
							isBlocked = false;
						}
						project.Customer customer_ = General.getCustomer(customer.getCustomerNo());
						project.Employee employee = General.getEmployee(chargeEmployee);

						// project.Employee employee = getEmployee(chargeEmployee);

						project.Account currentAccount = new project.CurrentAccount(customer_, account_No, employee);
						currentAccount.setBalance(amount);
						currentAccount.setBlocked(isBlocked);
						currentAccount.setAccountTransactions(database.Account.getAccountTransactions(currentAccount));
						accounts.add(currentAccount);

					} else if (table == "TimeDepositAccount") {
						String account_No = query1.getString("accountNo");
						double amount = (double)query1.getDouble("amount");
						String chargeEmployee = query1.getString("chargeEmployee");
						String mainAccount = query1.getString("mainAccount");
						Date startingDate = query1.getDate("startingDate");
						Date endDate = query1.getDate("endDate");
						double interest = query1.getDouble("interest");
						boolean isBlocked = false;
						if (query1.getInt("isBlocked") == 1) {
							isBlocked = true;
						} else {
							isBlocked = false;
						}
						// Customer owner, String accountNo, CurrentAccount mainAccount,
						// Date startingDate, Date endDate, double interest, Employee chargeEmployee
						project.CurrentAccount account = (CurrentAccount) General.getAccount(mainAccount);
						project.Employee employee = General.getEmployee(chargeEmployee);
						project.Account TimeDepositAccount = new project.TimeDepositAccount(customer, account_No,
								account, startingDate, endDate, interest, employee);
						TimeDepositAccount.setBalance(amount);
						TimeDepositAccount.setBlocked(isBlocked);
						TimeDepositAccount.setAccountTransactions(database.Account.getAccountTransactions(TimeDepositAccount));
						accounts.add(TimeDepositAccount);
					}

				}
				connection.close();
			} catch (Exception e) {
				// System.out.println(e);
			}

		}
		return accounts;
	}

	public static ArrayList<project.Card> getCards(project.Customer customer) {
		ArrayList<project.Card> cards = new ArrayList<project.Card>();
		String[] tables = { "CorporateCard", "IndividualCard" };
		for (String table : tables) {
			
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection connection = DriverManager.getConnection(database_,databaseUsername, databasePassword);
				Statement stmt = (Statement) connection.createStatement();

				ResultSet query1 =  stmt.executeQuery(
						"SELECT * FROM " + table + " WHERE customer = '" + customer.getCustomerNo() + "'");
				
				while (query1.next()) {
					String cardName = query1.getString("cardName");
					String cardNo_ = query1.getString("cardNo");
					String cvv = query1.getString("cvv");
					Date exprationDate = query1.getDate("exprationDate");
					String cardPass = query1.getString("cardPass");
					double cardPoint = query1.getDouble("cardPoint");
					double balance = query1.getDouble("balance");

					double cardLimit = query1.getDouble("cardLimit");
					double availableLimit = query1.getDouble("availableLimit");

					String accountNo = query1.getString("account");
					project.Account account = General.getAccount(accountNo);
					
					if (table == "CorporateCard") {

						if (new String(cardName).equals("BankCardBusiness")) {
							project.BankCardBusiness card = new project.BankCardBusiness(cardNo_, customer, cvv,
									exprationDate, account);
							cards.add(card);

						} else if (new String(cardName).equals("SilverCardBusiness")) {
							project.SilverCardBusiness card = new project.SilverCardBusiness(cardNo_, customer, cvv,
									exprationDate, cardLimit);
							cards.add(card);

						} else if (new String(cardName).equals("GoldCardBusiness")) {
							project.GoldCardBusiness card = new project.GoldCardBusiness(cardNo_, customer, cvv,
									exprationDate, cardLimit);
							cards.add(card);
						}

					} else if (table == "IndividualCard") {

						if (new String(cardName).equals("BankCardCustomer")) {
							project.BankCardCustomer bankCard = new project.BankCardCustomer(cardNo_, customer, cvv,
									exprationDate, account);
							cards.add(bankCard);

						} else if (new String(cardName).equals("YoungCard")) {
							project.YoungCard bankCard = new project.YoungCard(cardNo_, customer, cvv, exprationDate,
									account);
							cards.add(bankCard);

						} else if (new String(cardName).equals("SilverCard")) {
							project.SilverCard creditCard = new project.SilverCard(cardNo_, customer, cvv, exprationDate,
									cardPoint, cardLimit, availableLimit);
							cards.add(creditCard);

						} else if (new String(cardName).equals("GoldCard")) {
							project.SilverCard creditCard = new project.SilverCard(cardNo_, customer, cvv, exprationDate,
									cardPoint, cardLimit, availableLimit);
							cards.add(creditCard);
						}
					}
				}
				connection.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}

		return cards;
	}

	public static ArrayList<project.Application> getApplications(project.Customer customer) {
		ArrayList<project.Application> applications = new ArrayList<project.Application>();
		String[] tables = { "CardApplication", "LoanApplication", "AccountApplication" };
		for (String table : tables) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection connection = DriverManager.getConnection(database_,databaseUsername, databasePassword);
				Statement stmt = (Statement) connection.createStatement();

				ResultSet query1 = stmt.executeQuery(
						"SELECT * FROM " + table + " WHERE applicant = '" + customer.getCustomerNo() + "'");
				while (query1.next()) {
					if (table == "CardApplication") {
						// applicationNo applicant applicationStatus applicationResult chargeEmployee
						// kardNo

						String application_No = query1.getString("applicationNo");
						String applicationStatus = query1.getString("applicationStatus");

						boolean applicationResult = false;
						if (query1.getInt("applicationResult") == 1) {
							applicationResult = true;
						} else {
							applicationResult = false;
						}

						String kardNo = query1.getString("kardNo");

						project.Card card = General.getCard(kardNo);
						project.CardApplication application = new project.CardApplication(application_No, customer,card);
						application.setApplicationResult(applicationResult);
						application.setApplicationStatus(applicationStatus);

						applications.add(application);

					} else if (table == "LoanApplication") {

						String application_No = query1.getString("applicationNo");
						String applicationStatus = query1.getString("applicationStatus");

						boolean applicationResult = false;
						if (query1.getInt("applicationResult") == 1) {
							applicationResult = true;
						} else {
							applicationResult = false;
						}
						
						String loanNo = query1.getString("loanNo");
						project.Loan loan = General.getLoan(loanNo);

						project.LoanApplication application = new project.LoanApplication(application_No, customer,
								loan);
						application.setApplicationResult(applicationResult);
						application.setApplicationStatus(applicationStatus);
						applications.add(application);

					} else if (table == "AccountApplication") {
						String application_No = query1.getString("applicationNo");
						String applicationStatus = query1.getString("applicationStatus");

						boolean applicationResult = false;
						if (query1.getInt("applicationResult") == 1) {
							applicationResult = true;
						} else {
							applicationResult = false;
						}

						String accountNo = query1.getString("accountNo");
						project.Account account = General.getAccount(accountNo);
						project.AccountApplication application = new project.AccountApplication(application_No,
								customer, account);
						application.setApplicationResult(applicationResult);
						application.setApplicationStatus(applicationStatus);
						applications.add(application);
					}
				}
				connection.close();
			} catch (Exception e) {
				// System.out.println(e);
			}
		}
		return applications;
	}

	public static ArrayList<project.Loan> getLoans(project.Customer customer) {
		ArrayList<project.Loan> loans = new ArrayList<project.Loan>();
		String[] tables = { "CorporateLoan", "IndividualLoan" };
		for (String table : tables) {
			try {
				
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection connection = DriverManager.getConnection(database_,databaseUsername, databasePassword);
				Statement stmt = (Statement) connection.createStatement();

				ResultSet query1 =stmt.executeQuery(
						"SELECT * FROM " + table + " WHERE loanTaker = '" + customer.getCustomerNo() + "'");
				while (query1.next()) {
					if (table == "CorporateLoan") {
						
						// loanName loanNo loanTaker guarantor interest maturity amount refundedAmount
						// remainingDebt lastPaymentDate givenLoanDate givenLoanAccount
						String loanName = query1.getString("loanName");
						String loanNo_ = query1.getString("loanNo");
						double interest = query1.getDouble("interest");
						int maturity = query1.getInt("maturity");
						double amount = query1.getDouble("amount");
						double refundedAmount = query1.getDouble("refundedAmount");
						double remainingDebt = query1.getDouble("remainingDebt");
						Date lastPaymentDate = query1.getDate("lastPaymentDate");
						Date givenLoanDate = query1.getDate("givenLoanDate");
                        String loanTaker_=query1.getString("loanTaker");
                        String guarantor_ = query1.getString("guarantor");
                        String givenLoanAccount_ = query1.getString("givenLoanAccount");
                        
						project.Account givenLoanAccount = General.getAccount(givenLoanAccount_);
                        project.Customer loanTaker = General.getCustomer(loanTaker_);
                        project.Customer guarantor = General.getCustomer(guarantor_);

						if (new String(loanName).equals("DebitCurrentAccountLoan")) {
							// String loanNo, Customer loanTaker, Customer guarantor, double interest, int
							// maturity, double amount, double remainingDebt, Date lastPayment, Date
							// givenLoanDate, Account givenLoanAccount
							project.DebitCurrentAccountLoan loan = new project.DebitCurrentAccountLoan(
									loanTaker, guarantor, maturity, amount, lastPaymentDate,
									givenLoanDate, givenLoanAccount);
							loans.add(loan);

						} else if (new String(loanName).equals("SpotLoan")) {
							// String loanNo, Customer loanTaker, Customer guarantor, double interest, int
							// maturity, double amount, double remainingDebt, Date lastPayment, Date
							// givenLoanDate, Account givenLoanAccount
							project.SpotLoan loan = new project.SpotLoan(loanTaker, guarantor, 
									maturity, amount, lastPaymentDate, givenLoanDate, givenLoanAccount);
							loans.add(loan);

						} else if (new String(loanName).equals("InstallmentLoan")) {
							project.InstallmentLoan loan = new project.InstallmentLoan( loanTaker, guarantor,
									maturity, amount, lastPaymentDate, givenLoanDate,
									givenLoanAccount);

							loans.add(loan);

						} else if (new String(loanName).equals("GuaranteeLoan")) {
							project.GuaranteeLoan loan = new project.GuaranteeLoan( loanTaker, guarantor,
									 maturity, amount, lastPaymentDate, givenLoanDate,
									givenLoanAccount);

							loans.add(loan);

						} else if (new String(loanName).equals("ReferenceLoan")) {
							project.ReferenceLoan loan = new project.ReferenceLoan( loanTaker, guarantor,
									maturity, amount, lastPaymentDate, givenLoanDate,
									givenLoanAccount);
							loans.add(loan);

						} else if (new String(loanName).equals("ProjectLoan")) {
							project.ProjectLoan loan = new project.ProjectLoan(loanTaker, guarantor, 
									maturity, amount, lastPaymentDate, givenLoanDate, givenLoanAccount);
							loans.add(loan);
						}

					} else if (table == "IndividualLoan") {
						// loanNo loanTaker guarantor interest maturity amount refundedAmount
						// remainingDebt
						// lastPaymentDate givenLoanDate givenLoanAccount licencePlate vehicleAge
						// insuranceNo deedNo
						String loanName = query1.getString("loanName");
						String loanNo_ = query1.getString("loanNo");
						double interest = query1.getDouble("interest");
						int maturity = query1.getInt("maturity");
						double amount = query1.getDouble("amount");
						double refundedAmount = query1.getDouble("refundedAmount");
						double remainingDebt = query1.getDouble("remainingDebt");
						Date lastPaymentDate = query1.getDate("lastPaymentDate");
						Date givenLoanDate = query1.getDate("givenLoanDate");
						String licensePlate = query1.getString("licencePlate");
						int vehicleAge = query1.getInt("vehicleAge");
						String insuranceNo = query1.getString("insuranceNo");
						String deedNo = query1.getString("deedNo");
						String loanTaker_=query1.getString("loanTaker");
                        String guarantor_ = query1.getString("guarantor");
                        String givenLoanAccount_ = query1.getString("givenLoanAccount");
						connection.close();

						project.Account givenLoanAccount = General.getAccount(givenLoanAccount_);
                        project.Customer loanTaker = General.getCustomer(loanTaker_);
                        project.Customer guarantor = General.getCustomer(guarantor_);

						if (new String(loanName).equals("Insuranced")) {
							// String loanNo, Customer loanTaker, Customer guarantor, double interest, int
							// maturity, double amount, double remainingDebt, Date lastPayment, Date
							// givenLoanDate, Account givenLoanAccount String licensePlate, int vehicleAge,
							// String insuranceNo
							project.Insuranced loan = new project.Insuranced(loanTaker, guarantor,
									maturity, amount, lastPaymentDate, givenLoanDate, givenLoanAccount,
									licensePlate, vehicleAge, insuranceNo);
							loans.add(loan);

						} else if (new String(loanName).equals("NotInsuranced")) {
							// String loanNo, Customer loanTaker, Customer guarantor, double interest, int
							// maturity, double amount, double remainingDebt, Date lastPayment, Date
							// givenLoanDate, Account givenLoanAccount String licensePlate, int vehicleAge
							project.NotInsuranced loan = new project.NotInsuranced( loanTaker, guarantor,
									maturity, amount, lastPaymentDate, givenLoanDate,
									givenLoanAccount, licensePlate, vehicleAge);
							loans.add(loan);

						} else if (new String(loanName).equals("EmployeeHousingLoan")) {
							project.EmployeeHousingLoan loan = new project.EmployeeHousingLoan( loanTaker,
									guarantor,  maturity, amount, lastPaymentDate,
									givenLoanDate, givenLoanAccount, deedNo);
							loans.add(loan);

						} else if (new String(loanName).equals("RetiredHousingLoan")) {
							project.RetiredHousingLoan loan = new project.RetiredHousingLoan(loanTaker,
									guarantor,  maturity, amount, lastPaymentDate,
									givenLoanDate, givenLoanAccount, deedNo);
							loans.add(loan);

						} else if (new String(loanName).equals("EmployeePersonalFinanceLoan")) {
							project.EmployeePersonalFinanceLoan loan = new project.EmployeePersonalFinanceLoan( loanTaker,
									guarantor, maturity, amount, lastPaymentDate,
									givenLoanDate, givenLoanAccount);
							loans.add(loan);

						} else if (new String(loanName).equals("RetiredPersonalFinanceLoan")) {
							project.RetiredPersonalFinanceLoan loan = new project.RetiredPersonalFinanceLoan(
									loanTaker, guarantor, maturity, amount, lastPaymentDate,
									givenLoanDate, givenLoanAccount);
							loans.add(loan);

						} else if (new String(loanName).equals("ConsumerPersonalFinanceLoan")) {
							project.ConsumerPersonalFinanceLoan loan = new project.ConsumerPersonalFinanceLoan(
									loanTaker, guarantor,  maturity, amount, lastPaymentDate,
									givenLoanDate, givenLoanAccount);
							loans.add(loan);
						}

					}
				}
				connection.close();
			} catch (Exception e) {
				// System.out.println(e);
			}
		}

		return loans;

	}

	public static void setName(project.Customer customer, String name) {
		String[] tables = { "CorporateCustomer", "IndividualCustomer" };
		for (String table : tables) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection connection = DriverManager.getConnection(database_,databaseUsername, databasePassword);
				Statement stmt = (Statement) connection.createStatement();

				String query1 = "UPDATE " + table + " SET name='" + name + "' WHERE customerNo='"
						+ customer.getCustomerNo()+ "'";
				stmt.executeUpdate(query1);

				connection.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

	public static void setAdress(project.Customer customer, String adress) {
		String[] tables = { "CorporateCustomer", "IndividualCustomer" };
		for (String table : tables) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection connection = DriverManager.getConnection(database_,databaseUsername, databasePassword);
				Statement stmt = (Statement) connection.createStatement();

				String query1 = "UPDATE " + table + " SET adress='" + adress + "' WHERE customerNo='"
						+ customer.getCustomerNo()+ "'";
				stmt.executeUpdate(query1);

				connection.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

	public static void setMail(project.Customer customer, String mail) {
		String[] tables = { "CorporateCustomer", "IndividualCustomer" };
		for (String table : tables) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection connection = DriverManager.getConnection(database_,databaseUsername, databasePassword);
				Statement stmt = (Statement) connection.createStatement();

				String query1 = "UPDATE " + table + " SET mail='" + mail + "' WHERE customerNo='"
						+ customer.getCustomerNo()+ "'";
				stmt.executeUpdate(query1);

				connection.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}
}
