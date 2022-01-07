package database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;

import project.*;

public class General {
	public static String database_ = "jdbc:mysql://localhost:3306/denemeoop";
	public static String databaseUsername = "denemeoop";
	public static String databasePassword = "denemeoop";
	public static boolean checkPass(String userName, String pass) {
		String sifre = null;
		String[] tables = { "IndividualCustomer", "CorporateCustomer", "Manager", "Worker" };
		for (String table : tables) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection connection = DriverManager.getConnection(database_,databaseUsername, databasePassword);
				Statement stmt = (Statement) connection.createStatement();

				ResultSet query1 = stmt
						.executeQuery("SELECT userPass FROM " + table + " WHERE userName = '" + userName + "'");
				while (query1.next()) {
					sifre = query1.getString("userPass");
					if (new String(sifre).equals(pass)) {
						connection.close();
						return true;
					} else {
						connection.close();
						return false;
					}

				}
				connection.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		return false;
	}

	public static void changePass(String userName, String pass) {
		String[] tables = { "IndividualCustomer", "CorporateCustomer", "Manager", "Worker" };
		for (String table : tables) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection connection = DriverManager.getConnection(database_,databaseUsername, databasePassword);
				Statement stmt = (Statement) connection.createStatement();

				String query1 = "UPDATE " + table + " SET userPass='" + pass + "' WHERE userName='" + userName + "'";
				stmt.executeUpdate(query1);

				connection.close();
			} catch (Exception e) {
				// System.out.println(e);
			}
		}
	}

	// customer
	public static boolean isThereCustomer(String customerNo) {
		String[] tables = { "IndividualCustomer", "CorporateCustomer" };
		for (String table : tables) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection connection = DriverManager.getConnection(database_,databaseUsername, databasePassword);
				Statement stmt = (Statement) connection.createStatement();

				ResultSet query1 = stmt
						.executeQuery("SELECT * FROM " + table + " WHERE customerNo = '" + customerNo + "'");
				while (query1.next()) {

					connection.close();
					return true;
				}
				connection.close();
			} catch (Exception e) {
				// System.out.println(e);
			}
		}
		return false;
	}

	public static project.Customer getCustomer(String customerNo) {

		String[] tables = { "IndividualCustomer", "CorporateCustomer" };
		for (String table : tables) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection connection = DriverManager.getConnection(database_,databaseUsername, databasePassword);
				Statement stmt = (Statement) connection.createStatement();

				ResultSet query1 = stmt
						.executeQuery("SELECT * FROM " + table + " WHERE customerNo = '" + customerNo + "'");
				
				while (query1.next()) {
					if (table == "IndividualCustomer") {
						String name = query1.getString("name");
						String userPass = query1.getString("userPass");
						String customer_No = query1.getString("customerNo");
						String adress = query1.getString("adress");
						String mail = query1.getString("mail");
						double income = query1.getDouble("income");
						String chargeEmployee = query1.getString("chargeEmployee");
						String individualId = query1.getString("individualId");
						String occupation = query1.getString("occupation");
						Date birthDate = query1.getDate("birthDate");
						connection.close();

//String name, String customerNo, String adress, String mail, double income,
//Employee chargeEmployee, String individualID, String occupation, Date birthDate
						
						project.Customer customer = new IndividualCustomer(name, customer_No, adress, mail,
								income, getEmployee(chargeEmployee), individualId, occupation, birthDate);
						customer.setUserPass(userPass);
						return customer;
					} else if (table == "CorporateCustomer") {
						String name = query1.getString("name");
						String userPass = query1.getString("name");
						String customer_No = query1.getString("customerNo");
						String adress = query1.getString("adress");
						;
						String mail = query1.getString("mail");
						double income = query1.getDouble("income");
						String chargeEmployee = query1.getString("chargeEmployee");
						String taxNo = query1.getString("individualId");
						connection.close();
						project.Customer customer = new CorporateCustomer(name, customer_No, adress, mail,
								income, getEmployee(chargeEmployee), taxNo);
						customer.setUserPass(userPass);
						return customer;
					}

				}
				connection.close();
			} catch (Exception e) {
				// System.out.println(e);
			}
		}
		return null;
	}

	public static void createCustomer(project.Customer customer) {
		String table = " ";
		String name = " ";
		String userName = " ";
		String userPass = " ";
		String customerNo = " ";
		String adress = " ";
		String mail = " ";
		double income = 0;
		String chargeEmployee = " ";
		String individualId = " ";
		String occupation = " ";
		String taxNo = " ";
		Date birthDate = null;

		if (customer instanceof project.IndividualCustomer) {
			project.IndividualCustomer icustomer = (IndividualCustomer) customer;

			name = icustomer.getName();
			userName = icustomer.getUserName();
			userPass = icustomer.getUserPass();
			customerNo = icustomer.getCustomerNo();
			adress = icustomer.getAdress();
			mail = icustomer.getMail();
			income = icustomer.getIncome();
			if(icustomer.getChargeEmployee() != null) {
				chargeEmployee = icustomer.getChargeEmployee().getId();
			}
			individualId = icustomer.getIndividualID();
			birthDate = icustomer.getBirthDate();
			table = "IndividualCustomer";

			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection connection = DriverManager.getConnection(database_,databaseUsername, databasePassword);
				Statement stmt = (Statement) connection.createStatement();

				String s = "( name,userName, userPass,  customerNo, adress, mail, income, chargeEmployee, individualId, occupation, birthDate)";
				String query1 = "INSERT INTO " + table + s + " VALUES ('" + name + "','" + userName + "','" + userPass
						+ "','" + customerNo + "','" + adress + "','" + mail + "'," + income + ",'" + chargeEmployee
						+ "','" + individualId + "','" + occupation + "','" + birthDate + "')";
				stmt.executeUpdate(query1);

				connection.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		} else if (customer instanceof project.CorporateCustomer) {
			project.CorporateCustomer ccustomer = (CorporateCustomer) customer;

			name = ccustomer.getName();
			userPass = ccustomer.getUserPass();
			userName = ccustomer.getUserName();
			customerNo = ccustomer.getCustomerNo();
			adress = ccustomer.getAdress();
			mail = ccustomer.getMail();
			income = ccustomer.getIncome();
			chargeEmployee = ccustomer.getChargeEmployee().getId();
			taxNo = ccustomer.getTaxNo();
			table = "CorporateCustomer";

			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection connection = DriverManager.getConnection(database_,databaseUsername, databasePassword);
				Statement stmt = (Statement) connection.createStatement();
// 	name 	userName 	userPass 	customerNo 	adress 	mail 	income 	chargeEmployee 	taxNo 	
				String s = "( name, userName, userPass, customerNo, adress, mail, income, chargeEmployee, taxNo)";
				String query1 = "INSERT INTO " + table + s + " VALUES ('" + name + "','"+ userName +"','" + userPass + "','" + customerNo
						+ "','" + adress + "','" + mail + "'," + income + ",'" + chargeEmployee + "','" + taxNo + "' )";
				stmt.executeUpdate(query1);

				connection.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}
	
	public static String createCustomerNo() {
		  String []tables = {"IndividualCustomer", "CorporateCustomer"};
		  int biggest = 0;
		  int current;
		  Random r = new Random();
		  int no = r.nextInt(999999999);
		  for(String table: tables) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection connection = DriverManager.getConnection(database_,databaseUsername, databasePassword);
					Statement stmt = (Statement) connection.createStatement();
					//select * from Uye_Puan order by Puan desc LIMIT 10
					ResultSet query2 = stmt.executeQuery(
							"SELECT customerNo  FROM " + table + " order by customerNo desc LIMIT 1");
					while (query2.next()) {
						
						current =  Integer.parseInt(query2.getString("customerNo"));
						if(current>biggest) {
							biggest = current;
						}
					}
					
					connection.close();
				} catch (Exception e) {
					// System.out.println(e);
				}
		  }
		  biggest++;
		  return  String.valueOf(no);
	}

	// account
	public static boolean isThereAccount(String accountNo) {
		String[] tables = { "CurrentAccount ", "TimeDepositAccount " };
		for (String table : tables) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection connection = DriverManager.getConnection(database_,databaseUsername, databasePassword);
				Statement stmt = (Statement) connection.createStatement();

				ResultSet query1 = stmt
						.executeQuery("SELECT * FROM " + table + " WHERE accountNo = '" + accountNo + "'");
				while (query1.next()) {

					connection.close();
					return true;
				}
				connection.close();
			} catch (Exception e) {
				// System.out.println(e);
			}
		}
		return false;
	}

	public static project.Account getAccount(String accountNo) {
		String[] tables = { "CurrentAccount", "TimeDepositAccount" };
		for (String table : tables) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection connection = DriverManager.getConnection(database_,databaseUsername, databasePassword);
				Statement stmt = (Statement) connection.createStatement();

				ResultSet query1 = stmt
						.executeQuery("SELECT * FROM " + table + " WHERE accountNo = '" + accountNo + "'");
				while (query1.next()) {
					if (table == "CurrentAccount") {
						// customerNo accountNo amount isBlocked

						String account_No = query1.getString("accountNo");
						double amount = query1.getDouble("amount");
						String customerNo = query1.getString("customerNo");
						String chargeEmployee = query1.getString("chargeEmployee");
						boolean isBlocked = false;
						if (query1.getInt("isBlocked") == 1) {
							isBlocked = true;
						} else {
							isBlocked = false;
						}
						
						project.Customer customer = getCustomer(customerNo);
						project.Employee employee = getEmployee(chargeEmployee);

						project.Account currentAccount = new CurrentAccount(customer, account_No, employee);
						currentAccount.setBalance(amount);
						currentAccount.setBlocked(isBlocked);
						return currentAccount;
					} else if (table == "TimeDepositAccount") {
						System.out.println("281 girdi");
						String account_No = query1.getString("accountNo");
						double amount = query1.getDouble("amount");
						String customerNo = query1.getString("customerNo");
						String chargeEmployee = query1.getString("chargeEmployee");
						System.out.println("286 girdi");
						String mainAccount = query1.getString("mainAccount");
						System.out.println("288 girdi");
						Date startingDate = query1.getDate("startingDate");
						System.out.println("290 girdi");
						Date endDate = query1.getDate("endDate");
						System.out.println("290 girdi");
						double interest = query1.getDouble("interest");
						boolean isBlocked = false;
						if (query1.getInt("isBlocked") == 1) {
							isBlocked = true;
						} else {
							isBlocked = false;
						}
						System.out.println("298 girdi");
						project.CurrentAccount mainAccount_ = (CurrentAccount) getAccount(mainAccount);
						System.out.println("300 girdi");
						project.Customer customer = getCustomer(customerNo);
						project.Employee employee = getEmployee(chargeEmployee);
						// Customer owner, String accountNo, CurrentAccount mainAccount, Date
						// startingDate, Date endDate, double interest, Employee chargeEmployee
						project.TimeDepositAccount timeDepositAccount = new TimeDepositAccount(customer, account_No,
								mainAccount_, startingDate, endDate, interest, employee);
						timeDepositAccount.setBalance(amount);
						timeDepositAccount.setBlocked(isBlocked);

						return timeDepositAccount;
					}

				}
				connection.close();
			} catch (Exception e) {
				// System.out.println(e);
			}
		}
		return null;

	}

	public static void createAccount(project.Account account) {
		String table = " ";
		String customerNo = " ";
		String accountNo = " ";
		double amount = 0;
		int isBlocked = 0;
		String chargeEmployee = " ";
		String mainAccount = " ";
		Date startingDate = null;
		Date endDate = null;
		double interest = 0;

		if (account instanceof project.TimeDepositAccount) {
			project.TimeDepositAccount tdaccount = (TimeDepositAccount) account;
			customerNo = tdaccount.getOwner().getCustomerNo();
			accountNo = tdaccount.getAccountNo();
			amount = tdaccount.getBalance();
			if (tdaccount.isBlocked()) {
				isBlocked = 1;
			} else {
				isBlocked = 0;
			}
			chargeEmployee = tdaccount.getChargeEmployee().getId();
			mainAccount = tdaccount.getMainAccount().getAccountNo();
			startingDate = tdaccount.getStartingDate();
			endDate = tdaccount.getEndDate();
			interest = tdaccount.getInterest();
			table = "TimeDepositAccount";

			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection connection = DriverManager.getConnection(database_,databaseUsername, databasePassword);
				Statement stmt = (Statement) connection.createStatement();

				String s = "( customerNo, accountNo, amount, isBlocked, chargeEmployee, mainAccount, startingDate, endDate, interest)";
				String query1 = "INSERT INTO " + table + s + " VALUES ('" + customerNo + "','" + accountNo + "',"
						+ amount + "," + isBlocked + ",'" + chargeEmployee + "','" + mainAccount + "','" + startingDate
						+ "','" + endDate + "'," + interest + ")";
				stmt.executeUpdate(query1);

				connection.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		} else if (account instanceof project.CurrentAccount) {
			project.CurrentAccount caccount = (CurrentAccount) account;
			

			customerNo = caccount.getOwner().getCustomerNo();
			accountNo = caccount.getAccountNo();
			amount = caccount.getBalance();
			if (caccount.isBlocked()) {
				isBlocked = 1;
			} else {
				isBlocked = 0;
			}
			if(caccount.getChargeEmployee() != null) {
				chargeEmployee = caccount.getChargeEmployee().getId();
			}
			table = "CurrentAccount";

			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection connection = DriverManager.getConnection(database_,databaseUsername, databasePassword);
				Statement stmt = (Statement) connection.createStatement();
				
				String s = "( customerNo, accountNo, amount, isBlocked, chargeEmployee)";
				String query1 = "INSERT INTO " + table + s + " VALUES ('" + customerNo + "','" + accountNo + "',"
						+ amount + "," + isBlocked + ",'" + chargeEmployee + "')";
				
				stmt.executeUpdate(query1);
				
				connection.close();
				
			} catch (Exception e) {
				System.out.println(e);
			}

		}
	}
	
	public static String createAccountNo() {
		  String []tables = {"CurrentAccount", "TimeDepositAccount"};
		  int biggest = 0;
		  int current;
		  Random r = new Random();
		  int no = r.nextInt(999999999);
		  for(String table: tables) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection connection = DriverManager.getConnection(database_,databaseUsername, databasePassword);
					Statement stmt = (Statement) connection.createStatement();
					//select * from Uye_Puan order by Puan desc LIMIT 10
					ResultSet query2 = stmt.executeQuery(
							"SELECT accountNo  FROM " + table + " order by accountNo desc LIMIT 1");
					while (query2.next()) {
						
						current =  Integer.parseInt(query2.getString("accountNo"));
						if(current>biggest) {
							biggest = current;
						}
					}
					
					connection.close();
				} catch (Exception e) {
					// System.out.println(e);
				}
		  }
		  biggest++;
		  return  String.valueOf(no);
	}

	// application
	public static boolean isThereApplication(String applicationNo) {
		String[] tables = { "CardApplication", "LoanApplication", "AccountApplication" };
		for (String table : tables) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection connection = DriverManager.getConnection(database_,databaseUsername, databasePassword);
				Statement stmt = (Statement) connection.createStatement();

				ResultSet query1 = ((java.sql.Statement) stmt)
						.executeQuery("SELECT * FROM " + table + " WHERE applicationNo = '" + applicationNo + "'");
				while (query1.next()) {

					connection.close();
					return true;
				}
				connection.close();
			} catch (Exception e) {
				// System.out.println(e);
			}
		}
		return false;

	}

	public static project.Application getApplication(String applicationNo) {

		String[] tables = { "CardApplication", "LoanApplication", "AccountApplication" };
		for (String table : tables) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection connection = DriverManager.getConnection(database_,databaseUsername, databasePassword);
				Statement stmt = (Statement) connection.createStatement();

				ResultSet query1 = ((java.sql.Statement) stmt)
						.executeQuery("SELECT * FROM " + table + " WHERE applicationNo = '" + applicationNo + "'");
				while (query1.next()) {
					if (table == "CardApplication") {
						// applicationNo applicant applicationStatus applicationResult chargeEmployee
						// kardNo

						String application_No = query1.getString("applicationNo");
						String applicationStatus = query1.getString("applicationStatus");
						String cardNo = query1.getString("kardNo");
						String customerNo = query1.getString("applicant");
						boolean applicationResult = false;

						if (query1.getInt("applicationResult") == 1) {
							applicationResult = true;
						} else {
							applicationResult = false;
						}

						connection.close();
						project.Customer applicant = getCustomer(customerNo);
						project.Card card = getCard(cardNo);

						project.CardApplication application = new project.CardApplication(application_No, applicant,card, applicationStatus);
						application.setApplicationResult(applicationResult);
						return application;
					} else if (table == "LoanApplication") {
						String application_No = query1.getString("applicationNo");
						String applicationStatus = query1.getString("applicationStatus");
						boolean applicationResult = false;
						if (query1.getInt("applicationResult") == 1) {
							applicationResult = true;
						} else {
							applicationResult = false;
						}
						connection.close();
						project.Customer applicant = getCustomer(query1.getString("applicant"));
						project.Loan loan = getLoan(query1.getString("loanNo"));

						project.LoanApplication application = new project.LoanApplication(application_No, applicant,loan, applicationStatus);
						application.setApplicationResult(applicationResult);
						return application;
					} else if (table == "AccountApplication") {
						String application_No = query1.getString("applicationNo");
						String applicationStatus = query1.getString("applicationStatus");
						boolean applicationResult = false;
						if (query1.getInt("applicationResult") == 1) {
							applicationResult = true;
						} else {
							applicationResult = false;
						}
						connection.close();
						project.Customer applicant = getCustomer(query1.getString("applicant"));
						project.Account account = getAccount(query1.getString("accountNo"));
						AccountApplication application = new project.AccountApplication(application_No, applicant, account, applicationStatus);
						application.setApplicationResult(applicationResult);
						return application;
					}
				}
				connection.close();
			} catch (Exception e) {
				// System.out.println(e);
			}
		}

		return null;
	}
	
	public static ArrayList<project.Application> getUnfinishedApplication(project.Employee employee){
		ArrayList<project.Application> applications = new ArrayList<project.Application>();
		ArrayList<String> tables = new ArrayList<String>();
		if(employee instanceof project.CanDecideCard) {
			tables.add("CardApplication");
		}
		if(employee instanceof project.CanDecideLoan) {
			tables.add("LoanApplication");
		}
		if(employee instanceof project.CanDecideOpenMoneyAccount) {
			tables.add("AccountApplication");
		}
		
		
		for (String table : tables) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection connection = DriverManager.getConnection(database_,databaseUsername, databasePassword);
				Statement stmt = (Statement) connection.createStatement();

				ResultSet query1 = ((java.sql.Statement) stmt)
						.executeQuery("SELECT * FROM " + table + " WHERE applicationStatus = '" + "Waiting" + "'");
				while (query1.next()) {
					if (table == "CardApplication") {
						// applicationNo applicant applicationStatus applicationResult chargeEmployee
						// kardNo

						String application_No = query1.getString("applicationNo");
						String cardNo = query1.getString("kardNo");
						String customerNo = query1.getString("applicant");

						project.Customer applicant = getCustomer(customerNo);

						project.Card card = getCard(cardNo);

						project.Application application = new project.CardApplication(application_No, applicant, card);
						
						applications.add(application);
					} else if (table == "LoanApplication") {
						String application_No = query1.getString("applicationNo");
						
						project.Customer applicant = getCustomer(query1.getString("applicant"));
						project.Loan loan = getLoan(query1.getString("loanNo"));

						project.LoanApplication application = new project.LoanApplication(application_No, applicant,loan);
						applications.add(application);
					} else if (table == "AccountApplication") {
						String application_No = query1.getString("applicationNo");
						project.Customer applicant = getCustomer(query1.getString("applicant"));
						
						project.Account account = getAccount(query1.getString("accountNo"));
						AccountApplication application = new project.AccountApplication(application_No, applicant,account);
						applications.add(application);;
					}
				}
				connection.close();
			} catch (Exception e) {
				// System.out.println(e);
			}
		}

		return applications;
		
	}

	public static void createApplication(project.Application application) {
		String table = " ";
		String applicationNo = " ";
		String applicant = " ";
		String applicationStatus = " ";
		int applicationResult = 0;
		String card = " ";
		String loan = " ";
		String account = " ";

		if (application instanceof project.CardApplication) {
			project.CardApplication capplication = (CardApplication) application;
			applicationNo = capplication.getApplicationNo();
			applicant = capplication.getApplicant().getCustomerNo();
			applicationStatus = capplication.getApplicationStatus();
			if (capplication.getApplicationResult()) {
				applicationResult = 1;
			} else {
				applicationResult = 0;
			}

			card = capplication.getCard().getCardNo();
			table = "CardApplication";

			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection connection = DriverManager.getConnection(database_,databaseUsername, databasePassword);
				Statement stmt = (Statement) connection.createStatement();

				String s = "( applicationNo, applicant, applicationStatus, applicationResult, kardNo)";
				String query1 = "INSERT INTO " + table + s + " VALUES ('" + applicationNo + "','" + applicant + "','"
						+ applicationStatus + "'," + applicationResult + ",'" + card + "')";
				((java.sql.Statement) stmt).executeUpdate(query1);

				connection.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		} else if (application instanceof project.LoanApplication) {
			project.LoanApplication lapplication = (LoanApplication) application;

			applicationNo = lapplication.getApplicationNo();
			applicant = lapplication.getApplicant().getCustomerNo();
			applicationStatus = lapplication.getApplicationStatus();
			if (lapplication.getApplicationResult()) {
				applicationResult = 1;
			} else {
				applicationResult = 0;
			}
			loan = lapplication.getLoan().getLoanNo();
			table = "LoanApplication";

			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection connection = DriverManager.getConnection(database_,databaseUsername, databasePassword);
				Statement stmt = (Statement) connection.createStatement();

				String s = "( applicationNo, applicant, applicationStatus, applicationResult, loanNo)";
				String query1 = "INSERT INTO " + table + s + " VALUES ('" + applicationNo + "','" + applicant + "','"
						+ applicationStatus + "'," + applicationResult + ",'" + loan + "')";
				((java.sql.Statement) stmt).executeUpdate(query1);

				connection.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		} else if (application instanceof project.AccountApplication) {
			project.AccountApplication aapplication = (AccountApplication) application;

			applicationNo = aapplication.getApplicationNo();
			applicant = aapplication.getApplicant().getCustomerNo();
			applicationStatus = aapplication.getApplicationStatus();
			if (aapplication.getApplicationResult()) {
				applicationResult = 1;
			} else {
				applicationResult = 0;
			}
			account = aapplication.getAccount().getAccountNo();
			table = "AccountApplication";

			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection connection = DriverManager.getConnection(database_,databaseUsername, databasePassword);
				Statement stmt = (Statement) connection.createStatement();

				String s = "( applicationNo, applicant, applicationStatus, applicationResult, accountNo)";
				String query1 = "INSERT INTO " + table + s + " VALUES ('" + applicationNo + "','" + applicant + "','"
						+ applicationStatus + "'," + applicationResult + ",'" + account + "')";
				((java.sql.Statement) stmt).executeUpdate(query1);

				connection.close();
			} catch (Exception e) {
				System.out.println(e);
			}

		}
	}
	
	public static String createApplicationNo() {
		  String []tables = {"LoanApplication", "CardApplication", "AccountApplication"};
		  int biggest = 0;
		  int current;
		  Random r = new Random();
		  int no = r.nextInt(999999999);
		  for(String table: tables) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection connection = DriverManager.getConnection(database_,databaseUsername, databasePassword);
					Statement stmt = (Statement) connection.createStatement();
					//select * from Uye_Puan order by Puan desc LIMIT 10
					ResultSet query2 = stmt.executeQuery(
							"SELECT applicationNo  FROM " + table + " order by applicationNo desc LIMIT 1");
					while (query2.next()) {
						
						current =  Integer.parseInt(query2.getString("applicationNo"));
						if(current>biggest) {
							biggest = current;
						}
					}
					
					connection.close();
				} catch (Exception e) {
					// System.out.println(e);
				}
		  }
		  biggest++;
		  return  String.valueOf(no);
	}

	// card
	public static boolean isThereCard(String cardNo) {
		String[] tables = { "IndividualCard", "CorporateCard", "IndividualBankCard", "BankCardCustomer", "YoungCard",
				"IndividualCreditCard", " SilverCard", "GoldCard", "CorporateBankCard", "BankCardBusiness",
				"SilverCardBusiness", " GoldCardBusiness", "  CorporateCreditCard" };
		for (String table : tables) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection connection = DriverManager.getConnection(database_,databaseUsername, databasePassword);
				Statement stmt = (Statement) connection.createStatement();

				ResultSet query1 = ((java.sql.Statement) stmt)
						.executeQuery("SELECT * FROM " + table + " WHERE cardNo = '" + cardNo + "'");
				while (query1.next()) {

					connection.close();
					return true;
				}
				connection.close();
			} catch (Exception e) {
				// System.out.println(e);
			}
		}
		return false;
	}

	public static project.Card getCard(String cardNo) {
		String[] tables = { "CorporateCard", "IndividualCard" };
		for (String table : tables) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection connection = DriverManager.getConnection(database_,databaseUsername, databasePassword);
				Statement stmt = (Statement) connection.createStatement();

				ResultSet query1 = ((java.sql.Statement) stmt)
						.executeQuery("SELECT * FROM " + table + " WHERE cardNo = '" + cardNo + "'");
				while (query1.next()) {
					String cardName = query1.getString("cardName");
					String cardNo_ = query1.getString("cardNo");
					String cvv = query1.getString("cvv");
					Date exprationDate = query1.getDate("exprationDate");
					String cardPass = query1.getString("cardPass");
					double cardPoint = query1.getDouble("cardPoint");
					double balance = query1.getDouble("balance");
					String customerNo = query1.getString("customer");

					double cardLimit = query1.getDouble("cardLimit");
					double availableLimit = query1.getDouble("availableLimit");

					String accountNo = query1.getString("account");
					connection.close();
					project.Account account = getAccount(accountNo);
					project.Customer owner = getCustomer(customerNo);
					if (table == "CorporateCard") {
						// cardName cardNo customer cvv exprationDate cardPass cardPoint balance account

						if (new String(cardName).equals("BankCardBusiness")) {
							// String cardNo, Customer owner, String cvv, Date expirationDate, String
							// cardPass, double carPoint, double balance, Account account
							project.BankCardBusiness card = new project.BankCardBusiness(cardNo_, owner, cvv,
									exprationDate, account);
							card.setCardPass(cardPass);
							card.setBalance(balance);
							return card;
						} else if (new String(cardName).equals("SilverCardBusiness")) {

							project.SilverCardBusiness card = new project.SilverCardBusiness(cardNo_, owner, cvv,
									exprationDate, cardLimit);

							card.setCardPass(cardPass);
							card.setAvailableLimit(availableLimit);
							return card;
						} else if (new String(cardName).equals("GoldCardBusiness")) {
							project.GoldCardBusiness card = new project.GoldCardBusiness(cardNo_, owner, cvv,
									exprationDate, cardLimit);
							card.setAvailableLimit(availableLimit);
							return card;
						}

					} else if (table == "IndividualCard") {
						if (new String(cardName).equals("BankCardCustomer")) {
							project.BankCardCustomer bankCard = new project.BankCardCustomer(cardNo_, owner, cvv,
									exprationDate, account);
							bankCard.setBalance(balance);
							return bankCard;
						} else if (new String(cardName).equals("YoungCard")) {
							project.YoungCard bankCard = new project.YoungCard(cardNo_, owner, cvv, exprationDate,
									account);
							bankCard.setBalance(balance);
							return bankCard;
						} else if (new String(cardName).equals("SilverCard")) {
							project.SilverCard creditCard = new project.SilverCard(cardNo_, owner, cvv, exprationDate,
									cardLimit, cardPoint,  availableLimit);
							creditCard.setAvailableLimit(availableLimit);
							return creditCard;

						} else if (new String(cardName).equals("GoldCard")) {
							project.GoldCard creditCard = new project.GoldCard(cardNo_, owner, cvv, exprationDate,
									cardLimit,cardPoint,  availableLimit);
							creditCard.setAvailableLimit(availableLimit);
							return creditCard;
						} 
					}
				}
				connection.close();
			} catch (Exception e) {
				// System.out.println(e);
			}
		}

		return null;
	}

	public static void createCard(project.Card card) {
		// cardName cardNo customer cvv exprationDate cardPass cardPoint balance account
		// cardName cardNo customer cvv exprationDate cardPass cardPoint cardLimit
		// availableLimit
		String table = " ";
		String cardName = " ";
		String cardNo = " ";
		String customer = " ";
		String cvv = " ";
		Date exprationDate = null;
		String cardPass = " ";
		double cardPoint = 0;

		double cardLimit = 0;
		double maxCardLimit = 0;
		double availableLimit = 0;

		String account = " ";
		double balance = 0;

		if (card instanceof project.CorporateCard) {
			project.CorporateCard ccard = (project.CorporateCard) card;
			table = "CorporateCard";

			if (card instanceof project.BankCardBusiness) {
				project.BankCardBusiness bankCard = (BankCardBusiness) card;
				cardName = "BankCardBusiness";
				account = bankCard.getAccount().getAccountNo();
				balance = bankCard.getBalance();
			} else if (card instanceof project.SilverCardBusiness) {
				project.SilverCardBusiness creditCard = (SilverCardBusiness) card;
				cardLimit = creditCard.getCardLimit();
				availableLimit = creditCard.getAvailableLimit();

				cardName = "SilverCardBusiness";
			} else if (card instanceof project.GoldCardBusiness) {
				project.GoldCardBusiness creditCard = (GoldCardBusiness) card;
				cardLimit = creditCard.getCardLimit();
				availableLimit = creditCard.getAvailableLimit();

				cardName = "GoldCardBusiness";
			}

			cardNo = ccard.getCardNo();
			customer = ccard.getOwner().getCustomerNo();
			cvv = ccard.getCvv();
			exprationDate = ccard.getExpirationDate();
			cardPass = ccard.getCardPass();
			cardPoint = ccard.getCarPoint();

			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection connection = DriverManager.getConnection(database_,databaseUsername, databasePassword);
				Statement stmt = (Statement) connection.createStatement();
				// cardName 	cardNo 	customer 	cvv 	exprationDate 	cardPass 	
				//cardPoint 	cardLimit 	maxCardLimit 	availableLimit 	balance 	account 	
				String s = "( cardName, cardNo, customer, cvv, exprationDate, cardPass, cardPoint, cardLimit, availableLimit, balance, account)";
				String query1 = "INSERT INTO " + table + s + " VALUES ('" + cardName + "','" + cardNo + "','" + customer + "','" + cvv
						+ "','" + exprationDate + "', '" + cardPass + "'," + cardPoint + "," + cardLimit + ","
						+ availableLimit + "," + balance + ",'" + account + "')";
				((java.sql.Statement) stmt).executeUpdate(query1);
				connection.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		} else if (card instanceof project.IndividualCard) {
			project.IndividualCard icard = (project.IndividualCard) card;
			table = "IndividualCard";

			if (card instanceof project.BankCardCustomer) {
				project.BankCardCustomer bankCard = (BankCardCustomer) card;
				cardName = "BankCardCustomer";
				account = bankCard.getAccount().getAccountNo();
				balance = bankCard.getBalance();
			} else if (card instanceof project.YoungCard) {
				project.YoungCard bankCard = (YoungCard) card;
				cardName = "YoungCard";
				account = bankCard.getAccount().getAccountNo();
				balance = bankCard.getBalance();
			} else if (card instanceof project.SilverCard) {
				project.SilverCard creditCard = (SilverCard) card;
				cardLimit = creditCard.getCardLimit();
				availableLimit = creditCard.getAvailableLimit();
				cardName = "SilverCard";
			} else if (card instanceof project.GoldCard) {
				project.GoldCard creditCard = (GoldCard) card;
				cardLimit = creditCard.getCardLimit();
				availableLimit = creditCard.getAvailableLimit();

				cardName = "GoldCard";
			}

			cardNo = icard.getCardNo();
			customer = icard.getOwner().getCustomerNo();
			cvv = icard.getCvv();
			exprationDate = icard.getExpirationDate();
			cardPass = icard.getCardPass();
			cardPoint = icard.getCarPoint();

			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection connection = DriverManager.getConnection(database_,databaseUsername, databasePassword);
				Statement stmt = (Statement) connection.createStatement();
				// cardName cardNo customer cvv exprationDate cardPass cardPoint cardLimit
				// maxCardLimit availableLimit balance account
				String s = "( cardName, cardNo, customer, cvv, exprationDate, cardPass, cardPoint, cardLimit, maxCardLimit, availableLimit, balance, account)";
				String query1 = "INSERT INTO " + table + s + " VALUES ('" + cardName + "','" + cardNo + "','" + customer
						+ "','" + cvv + "','" + exprationDate + "',' " + cardPass + "'," + cardPoint + "," + cardLimit
						+ "," + maxCardLimit + "," + availableLimit + "," + balance + ",'" + account + "')";
				((java.sql.Statement) stmt).executeUpdate(query1);
				connection.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}
	
	public static String createCardNo() {
		  String []tables = {"IndividualCard", "CorporateCard"};
		  long biggest = 0;
		  long current;
		  Random r = new Random();
		  int no = r.nextInt(999999999);
		  for(String table: tables) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection connection = DriverManager.getConnection(database_,databaseUsername, databasePassword);
					Statement stmt = (Statement) connection.createStatement();
					//select * from Uye_Puan order by Puan desc LIMIT 10
					ResultSet query2 = stmt.executeQuery(
							"SELECT cardNo  FROM " + table + " order by cardNo desc LIMIT 1");
					while (query2.next()) {
						
						current =  Integer.parseInt(query2.getString("cardNo"));
						if(current>biggest) {
							biggest = current;
						}
					}
					
					connection.close();
				} catch (Exception e) {
					// System.out.println(e);
				}
		  }
		  biggest++;
		  return  String.valueOf(no);
	}

	// loan
	public static boolean isThereLoan(String loanNo) {
		String[] tables = { "IndividualLoan", "CorporateLoan", "CashLoan", "NonCashLoan", "SpotLoan", "InstallmentLoan",
				"GuaranteeLoan", "GuaranteeLoan", "ProjectLoan", " TransportLoan", " Insuranced", "NotInsuranced",
				"HousingLoan", "EmployeeHousingLoan", "PersonalFinanceLoan", "RetiredHousingLoan",
				"RetiredPersonalFinanceloan", "ConsumerPersonalFinanceLoan", "EmployeePersonalFinanceloan" };
		for (String table : tables) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection connection = DriverManager.getConnection(database_,databaseUsername, databasePassword);
				Statement stmt = (Statement) connection.createStatement();

				ResultSet query1 = ((java.sql.Statement) stmt)
						.executeQuery("SELECT * FROM " + table + " WHERE loanNo = '" + loanNo + "'");
				while (query1.next()) {

					connection.close();
					return true;
				}
				connection.close();
			} catch (Exception e) {
				// System.out.println(e);
			}
		}
		return false;

	}

	public static project.Loan getLoan(String loanNo) {
		String[] tables = { "CorporateLoan", "IndividualLoan" };
		for (String table : tables) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection connection = DriverManager.getConnection(database_,databaseUsername, databasePassword);
				Statement stmt = (Statement) connection.createStatement();

				ResultSet query1 = ((java.sql.Statement) stmt)
						.executeQuery("SELECT * FROM " + table + " WHERE loanNo = '" + loanNo + "'");
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
						connection.close();

						project.Account givenLoanAccount = getAccount(query1.getString("givenLoanAccount"));
						project.Customer loanTaker = getCustomer(query1.getString("loanTaker"));
						project.Customer guarantor = getCustomer(query1.getString("guarantor"));

						if (new String(loanName).equals("DebitCurrentAccountLoan")) {
                            // String loanNo, Customer loanTaker, Customer guarantor, double interest, int
                            // maturity, double amount, double remainingDebt, Date lastPayment, Date
                            // givenLoanDate, Account givenLoanAccount
                            project.DebitCurrentAccountLoan loan = new project.DebitCurrentAccountLoan(
                                    loanTaker, guarantor, maturity, amount, lastPaymentDate,
                                    givenLoanDate, givenLoanAccount);
                            loan.setRefundedAmount(refundedAmount);
                            loan.setRemainingDebt(remainingDebt);
                            return loan;
                        } else if (new String(loanName).equals( "SpotLoan")) {
                            // String loanNo, Customer loanTaker, Customer guarantor, double interest, int
                            // maturity, double amount, double remainingDebt, Date lastPayment, Date
                            // givenLoanDate, Account givenLoanAccount
                            project.SpotLoan loan = new project.SpotLoan(loanTaker, guarantor, 
                                    maturity, amount, lastPaymentDate, givenLoanDate, givenLoanAccount);
                            loan.setRefundedAmount(refundedAmount);
                            loan.setRemainingDebt(remainingDebt);
                            return loan;
                        } else if (new String(loanName).equals("InstallmentLoan") ) {
                            project.InstallmentLoan loan = new project.InstallmentLoan(loanTaker, guarantor,
                                     maturity, amount, lastPaymentDate, givenLoanDate,
                                    givenLoanAccount);
                            loan.setRefundedAmount(refundedAmount);
                            loan.setRemainingDebt(remainingDebt);
                            return loan;
                        } else if (new String(loanName).equals("GuaranteeLoan") ) {
                            project.GuaranteeLoan loan = new project.GuaranteeLoan( loanTaker, guarantor,
                                    maturity, amount, lastPaymentDate, givenLoanDate,
                                    givenLoanAccount);
                            loan.setRefundedAmount(refundedAmount);
                            loan.setRemainingDebt(remainingDebt);
                            return loan;
                        } else if (new String(loanName).equals("ReferenceLoan")) {
                            project.ReferenceLoan loan = new project.ReferenceLoan(loanTaker, guarantor,
                                     maturity, amount, lastPaymentDate, givenLoanDate,
                                    givenLoanAccount);
                            loan.setRefundedAmount(refundedAmount);
                            loan.setRemainingDebt(remainingDebt);
                            return loan;
                        } else if (new String(loanName).equals("ProjectLoan")) {
                            project.ProjectLoan loan = new project.ProjectLoan(loanTaker, guarantor, 
                                    maturity, amount, lastPaymentDate, givenLoanDate, givenLoanAccount);
                            loan.setRefundedAmount(refundedAmount);
                            loan.setRemainingDebt(remainingDebt);
                            return loan;
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
						
						//connection.close();
						
						project.Account givenLoanAccount = getAccount(query1.getString("givenLoanAccount"));
						project.Customer loanTaker = getCustomer(query1.getString("loanTaker"));
						project.Customer guarantor = getCustomer(query1.getString("guarantor"));

						if (new String(loanName).equals("Insuranced") ) {
                            // String loanNo, Customer loanTaker, Customer guarantor, double interest, int
                            // maturity, double amount, double remainingDebt, Date lastPayment, Date
                            // givenLoanDate, Account givenLoanAccount String licensePlate, int vehicleAge,
                            // String insuranceNo
							
                            project.Insuranced loan = new project.Insuranced(loanTaker, guarantor, 
                                    maturity, amount, lastPaymentDate, givenLoanDate, givenLoanAccount,
                                    licensePlate, vehicleAge, insuranceNo);
                            loan.setRefundedAmount(refundedAmount);
                            loan.setRemainingDebt(remainingDebt);
                            return loan;
                        } else if (new String(loanName).equals("NotInsuranced")) {
                            // String loanNo, Customer loanTaker, Customer guarantor, double interest, int
                            // maturity, double amount, double remainingDebt, Date lastPayment, Date
                            // givenLoanDate, Account givenLoanAccount String licensePlate, int vehicleAge
                            project.NotInsuranced loan = new project.NotInsuranced( loanTaker, guarantor,
                                    maturity, amount, lastPaymentDate, givenLoanDate,
                                    givenLoanAccount, licensePlate, vehicleAge);
                            loan.setRefundedAmount(refundedAmount);
                            loan.setRemainingDebt(remainingDebt);
                            return loan;
                        } else if (new String(loanName).equals("EmployeeHousingLoan") ) {
                            project.EmployeeHousingLoan loan = new project.EmployeeHousingLoan(loanTaker,
                                    guarantor,  maturity, amount, lastPaymentDate,
                                    givenLoanDate, givenLoanAccount, deedNo);
                            loan.setRefundedAmount(refundedAmount);
                            loan.setRemainingDebt(remainingDebt);
                            return loan;
                        } else if (new String(loanName).equals("RetiredHousingLoan")) {
                            project.RetiredHousingLoan loan = new project.RetiredHousingLoan( loanTaker,
                                    guarantor, maturity, amount, lastPaymentDate,
                                    givenLoanDate, givenLoanAccount, deedNo);
                            loan.setRefundedAmount(refundedAmount);
                            loan.setRemainingDebt(remainingDebt);
                            return loan;
                        } else if (new String(loanName).equals("EmployeePersonalFinanceLoan") ) {
                            project.EmployeePersonalFinanceLoan loan = new project.EmployeePersonalFinanceLoan( loanTaker,
                                    guarantor, maturity, amount, lastPaymentDate,
                                    givenLoanDate, givenLoanAccount);
                            loan.setRefundedAmount(refundedAmount);
                            loan.setRemainingDebt(remainingDebt);
                            return loan;
                        } else if (new String(loanName).equals("RetiredPersonalFinanceLoan")) {
                            project.RetiredPersonalFinanceLoan loan = new project.RetiredPersonalFinanceLoan(
                                    loanTaker, guarantor,  maturity, amount, lastPaymentDate,
                                    givenLoanDate, givenLoanAccount);
                            loan.setRefundedAmount(refundedAmount);
                            loan.setRemainingDebt(remainingDebt);
                            return loan;
                        } else if (new String(loanName).equals("ConsumerPersonalFinanceLoan") ) {
                            project.ConsumerPersonalFinanceLoan loan = new project.ConsumerPersonalFinanceLoan(
                                    loanTaker, guarantor,  maturity, amount, lastPaymentDate,
                                    givenLoanDate, givenLoanAccount);
                            loan.setRefundedAmount(refundedAmount);
                            loan.setRemainingDebt(remainingDebt);
                            return loan;
                        }

					}
				}
				connection.close();
			} catch (Exception e) {
				// System.out.println(e);
			}
		}
		return null;
	}

	public static void createLoan(project.Loan loan) {

		String table = " ";
		String loanName = " ";
		String loanNo = " ";
		String loanTaker = " ";
		String guarantor = " ";
		double interest = 0.25;
		int maturity = 0;
		double amount = 0;
		double refundedAmount = 0;
		double remainingDebt = 0;
		Date lastPaymentDate = null;
		Date givenLoanDate = null;
		String givenLoanAccount = " ";
		String licencePlate = " ";
		int vehicleAge = 0;
		String insuranceNo = " ";
		String deedNo = " ";

		if (loan instanceof project.CorporateLoan) {
			project.CorporateLoan cloan = (project.CorporateLoan) loan;
			table = "CorporateLoan";

			if (loan instanceof project.DebitCurrentAccountLoan) {
				loanName = "DebitCurrentAccountLoan";
			} else if (loan instanceof project.SpotLoan) {
				loanName = "SpotLoan";
			} else if (loan instanceof project.InstallmentLoan) {
				loanName = "InstallmentLoan";
			} else if (loan instanceof project.GuaranteeLoan) {
				loanName = "GuaranteeLoan";
			} else if (loan instanceof project.ReferenceLoan) {
				loanName = "ReferenceLoan";
			} else if (loan instanceof project.ProjectLoan) {
				loanName = "ProjectLoan";
			}
			loanNo = cloan.getLoanNo();
			loanTaker = cloan.getLoanTaker().getCustomerNo();
			guarantor = cloan.getGuarantor().getCustomerNo();
			interest = cloan.getInterest();
			maturity = cloan.getMaturity();
			amount = cloan.getAmount();
			refundedAmount = cloan.getRefundedAmount();
			remainingDebt = cloan.getRemainingDebt();
			lastPaymentDate = cloan.getLastPayment();
			givenLoanDate = cloan.getGivenLoanDate();
			givenLoanAccount = cloan.getGivenLoanAccount().getAccountNo();

			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection connection = DriverManager.getConnection(database_,databaseUsername, databasePassword);
				Statement stmt = (Statement) connection.createStatement();

				String s = "( loanName, loanNo, loanTaker, guarantor, interest, maturity, amount, refundedAmount, "
						+ "	remainingDebt, lastPaymentDate, givenLoanDate, givenLoanAccount )";
				String query1 = "INSERT INTO " + table + s + " VALUES ('" + loanName + "','" + loanNo + "','"
						+ loanTaker + "','" + guarantor + "', " + interest + "," + maturity + "," + amount + ","
						+ refundedAmount + "," + remainingDebt + ",'" + lastPaymentDate + "','" + givenLoanDate + "','"
						+ givenLoanAccount + "')";
				((java.sql.Statement) stmt).executeUpdate(query1);
				connection.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		} else if (loan instanceof project.IndividualLoan) {
			project.IndividualLoan iloan = (project.IndividualLoan) loan;
			table = "IndividualLoan";

			if (loan instanceof project.Insuranced) {
				project.Insuranced realLoan = (project.Insuranced) loan;
				licencePlate = realLoan.getLicensePlate();
				vehicleAge = realLoan.getVehicleAge();
				insuranceNo = realLoan.getInsuranceNo();
				loanName = "Insuranced";
			} else if (loan instanceof project.NotInsuranced) {
				project.NotInsuranced realLoan = (project.NotInsuranced) loan;
				licencePlate = realLoan.getLicensePlate();
				vehicleAge = realLoan.getVehicleAge();
				loanName = "NotInsuranced";
			} else if (loan instanceof project.EmployeeHousingLoan) {
				project.EmployeeHousingLoan realLoan = (project.EmployeeHousingLoan) loan;
				deedNo = realLoan.getDeed();
				loanName = "EmployeeHousingLoan";
			} else if (loan instanceof project.RetiredHousingLoan) {
				project.RetiredHousingLoan realLoan = (project.RetiredHousingLoan) loan;
				deedNo = realLoan.getDeed();
				loanName = "RetiredHousingLoan";
			} else if (loan instanceof project.EmployeePersonalFinanceLoan) {
				loanName = "EmployeePersonalFinanceLoan";
			} else if (loan instanceof project.RetiredPersonalFinanceLoan) {
				loanName = "RetiredPersonalFinanceLoan";
			} else if (loan instanceof project.ConsumerPersonalFinanceLoan) {
				loanName = "ConsumerPersonalFinanceLoan";
			}
			loanNo = iloan.getLoanNo();
			loanTaker = iloan.getLoanTaker().getCustomerNo();
			guarantor = iloan.getGuarantor().getCustomerNo();
			interest = iloan.getInterest();
			maturity = iloan.getMaturity();
			amount = iloan.getAmount();
			refundedAmount = iloan.getRefundedAmount();
			remainingDebt = iloan.getRemainingDebt();
			lastPaymentDate = iloan.getLastPayment();
			givenLoanDate = iloan.getGivenLoanDate();
			givenLoanAccount = iloan.getGivenLoanAccount().getAccountNo();

			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection connection = DriverManager.getConnection(database_,databaseUsername, databasePassword);
				Statement stmt = (Statement) connection.createStatement();
//loanNo 	loanTaker 	guarantor 	interest 	maturity 	amount 	refundedAmount 	remainingDebt 	lastPaymentDate 	
//givenLoanDate 	givenLoanAccount 	licencePlate 	vehicleAge 	insuranceNo 	deedNo 	
				String s = "( loanName, loanNo, loanTaker, guarantor, interest, maturity, amount, refundedAmount,"
						+ "	remainingDebt, lastPaymentDate, givenLoanDate, givenLoanAccount, licencePlate, vehicleAge, insuranceNo, deedNo  )";
				String query1 = "INSERT INTO " + table + s + " VALUES ('" + loanName + "','" + loanNo + "','"
						+ loanTaker + "','" + guarantor + "', " + interest + "," + maturity + "," + amount + ","
						+ refundedAmount + "," + remainingDebt + ",'" + lastPaymentDate + "','" + givenLoanDate + "','"
						+ givenLoanAccount + "','" + licencePlate + "'," + vehicleAge + ",'" + insuranceNo + "','"
						+ deedNo + "')";
				((java.sql.Statement) stmt).executeUpdate(query1);
				connection.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}

	}

	// employee
	public static boolean isThereEmployee(String idNo) {
		String[] tables = { "Manager", "Worker" };
		for (String table : tables) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection connection = DriverManager.getConnection(database_,databaseUsername, databasePassword);
				Statement stmt = (Statement) connection.createStatement();

				ResultSet query1 = ((java.sql.Statement) stmt)
						.executeQuery("SELECT * FROM " + table + " WHERE employeeId = '" + idNo + "'");
				while (query1.next()) {

					connection.close();
					return true;
				}
				connection.close();
			} catch (Exception e) {
				// System.out.println(e);
			}
		}
		return false;

	}

	public static project.Employee getEmployee(String username) {
		String[] tables = { "Manager", "Worker" };
		for (String table : tables) {
			try {
				
				Class.forName("com.mysql.cj.jdbc.Driver");
				
				Connection connection = DriverManager.getConnection(database_,databaseUsername, databasePassword);
				
				Statement stmt = (Statement) connection.createStatement();
				
				ResultSet query1 = stmt.executeQuery("SELECT * FROM " + table + " WHERE userName = '" + username + "'");
				while (query1.next()) {
					if (table == "Manager") {
						// name userName userPass employeeId salary mail
						String name = query1.getString("name");
						String userPass = query1.getString("userPass");
						String employeeId = query1.getString("employeeId");
						double salary = query1.getDouble("salary");
						String mail = query1.getString("mail");

						connection.close();
						// (String name, String userPassword, String userName, String id, double salary,
						// String mail)
						project.Manager employee = new Manager(name, userPass, employeeId, salary, mail);
						employee.setUserName(username);
						return employee;
					} else if (table == "Worker") {
						String name = query1.getString("name");
						String userPass = query1.getString("userPass");
						String employeeId = query1.getString("employeeId");
						double salary = query1.getDouble("salary");
						String mail = query1.getString("mail");

						connection.close();
						project.Worker employee = new Worker(name, userPass, employeeId, salary, mail);
						employee.setUserName(username);
						return employee;
					}

				}
				connection.close();
			} catch (Exception e) {
				// System.out.println(e);
			}
		}
		return null;
	}

	public static void createEmployee(project.Employee employee) {
		// name userName userPass employeeId salary mail
		String table = " ";
		String name = " ";
		String userName = " ";
		String userPass = " ";
		String employeeId = " ";
		double salary = 0;
		String mail = " ";
		String chargeEmployee = " ";

		if (employee instanceof project.Worker) {
			project.Worker worker = (Worker) employee;
			table = "Worker";
			name = worker.getName();
			userName = worker.getUserName();
			userPass = worker.getUserPassword();
			employeeId = worker.getId();
			salary = worker.getSalary();
			mail = worker.getMail();
			chargeEmployee = worker.getChargeEmployee().getId();

			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection connection = DriverManager.getConnection(database_,databaseUsername, databasePassword);
				Statement stmt = (Statement) connection.createStatement();

				String s = "( name, userName, userPass, employeeId, salary, mail, chargeEmployee )";
				String query1 = "INSERT INTO " + table + s + " VALUES ('" + name + "','" + userName + "','" + userPass
						+ "','" + employeeId + "', " + salary + ",'" + mail + "','" + chargeEmployee + "')";
				((java.sql.Statement) stmt).executeUpdate(query1);
				connection.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		} else if (employee instanceof project.Manager) {
			project.Manager worker = (Manager) employee;
			table = "Manager";
			name = worker.getName();
			userName = worker.getUserName();
			userPass = worker.getUserPassword();
			employeeId = worker.getId();
			salary = worker.getSalary();
			mail = worker.getMail();

			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection connection = DriverManager.getConnection(database_,databaseUsername, databasePassword);
				Statement stmt = (Statement) connection.createStatement();
				// name userName userPass employeeId salary mail
				String s = "( name, userName, userPass, employeeId, salary, mail )";
				String query1 = "INSERT INTO " + table + s + " VALUES ('" + name + "','" + userName + "','" + userPass
						+ "','" + employeeId + "', " + salary + ",'" + mail + "')";
				((java.sql.Statement) stmt).executeUpdate(query1);
				connection.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

	// account transection
	public static boolean isThereAccountTransection(String accountTransactionnNo) {
		String[] tables = { "MoneyTransfer", "Payment" };
		for (String table : tables) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection connection = DriverManager.getConnection(database_,databaseUsername, databasePassword);
				Statement stmt = (Statement) connection.createStatement();

				ResultSet query1 = ((java.sql.Statement) stmt).executeQuery(
						"SELECT * FROM " + table + " WHERE accountTransactionNo = '" + accountTransactionnNo + "'");
				while (query1.next()) {

					connection.close();
					return true;
				}
				connection.close();
			} catch (Exception e) {
				// System.out.println(e);
			}
		}
		return false;
	}

	public static project.AccountTransaction getAccountTransaction(String accountTransactionNo) {

		String[] tables = { "MoneyTransfer", "Payment" };
		for (String table : tables) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection connection = DriverManager.getConnection(database_,databaseUsername, databasePassword);
				Statement stmt = (Statement) connection.createStatement();

				ResultSet query1 = stmt.executeQuery(
						"SELECT * FROM " + table + " WHERE accountTransactionNo = '" + accountTransactionNo + "'");
				while (query1.next()) {
					if (table == "MoneyTransfer") {
						// date description forwarder receiver amount accountTransactionNo
						Date date = query1.getDate("date");
						String description = query1.getString("description");
						String forwarderNo = query1.getString("forwarder");
						String receiverNo = query1.getString("receiver");
						double amount = query1.getDouble("amount");
						String accountTransactionNo_ = query1.getString("accountTransactionNo");


						project.Account forwarder = getAccount(forwarderNo);
						project.Account receiver = getAccount(receiverNo);

						// Date date, String description, Account forwarder, Account receiver, double
						// amount, String accountTransectionNo
						project.MoneyTransfer moneyTransfer = new MoneyTransfer(date, description, forwarder, receiver,
								amount, accountTransactionNo_);
						return moneyTransfer;
					} else if (table == "Payment") {
						// date description card amount accountTransactionNo
						Date date = query1.getDate("date");
						String description = query1.getString("description");
						String cardNo = query1.getString("card");
						double amount = query1.getDouble("amount");
						String accountTransactionNo_ = query1.getString("accountTransactionNo");


						project.Card card = getCard(cardNo);
						// Date date, String description, Card card, double expenseAmount, String
						// accountTransectionNo
						project.Payment payment = new Payment(date, description, card, amount, accountTransactionNo_);
						return payment;
					}

				}
				connection.close();
			} catch (Exception e) {
				// System.out.println(e);
			}
		}
		return null;
	}

	public static void createAccountTransaction(project.AccountTransaction accountTransaction) {
		String table = " ";
		Date date = null;
		String description = " ";
		String forwarder = " ";
		String receiver = " ";
		double amount = 0;
		String card = " ";
		double expenseAmount = 0;
		String accountTransactionNo = " ";

		if (accountTransaction instanceof project.MoneyTransfer) {
			project.MoneyTransfer moneyTransfer = (MoneyTransfer) accountTransaction;
			date = moneyTransfer.getDate();
			description = moneyTransfer.getDescription();
			forwarder = moneyTransfer.getForwarder().getAccountNo();
			receiver = moneyTransfer.getReceiver().getAccountNo();
			amount = moneyTransfer.getAmount();
			accountTransactionNo = moneyTransfer.getAccountTransectionNo();
			table = "MoneyTransfer";

			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection connection = DriverManager.getConnection(database_,databaseUsername, databasePassword);
				Statement stmt = (Statement) connection.createStatement();

				String s = "( date, description, forwarder, receiver, amount, accountTransactionNo)";
				String query1 = "INSERT INTO " + table + s + " VALUES ('" + date + "','" + description + "','"
						+ forwarder + "','" + receiver + "', " + amount + ",'" + accountTransactionNo + "')";
				((java.sql.Statement) stmt).executeUpdate(query1);

				connection.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		} else if (accountTransaction instanceof project.Payment) {
			project.Payment payment = (Payment) accountTransaction;

			date = payment.getDate();
			description = payment.getDescription();
			card = payment.getCard().getCardNo();
			expenseAmount = payment.getAmount();
			if(payment.getCard() instanceof project.IndividualBankCard) {
				project.IndividualBankCard bankCard = (project.IndividualBankCard) payment.getCard();
				forwarder = bankCard.getAccount().getAccountNo();
			}
			else if(payment.getCard() instanceof project.CorporateBankCard) {
				project.CorporateBankCard bankCard = (project.CorporateBankCard) payment.getCard();
				forwarder = bankCard.getAccount().getAccountNo();
			}
			accountTransactionNo = payment.getAccountTransectionNo();
			table = "Payment";

			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection connection = DriverManager.getConnection(database_,databaseUsername, databasePassword);
				Statement stmt = (Statement) connection.createStatement();
//date 	description 	card 	amount 	accountTransactionNo
				String s = "( date, description, card, amount, accountTransactionNo, forwarder)";
				String query1 = "INSERT INTO " + table + s + " VALUES ('" + date + "','" + description + "','" + card
						+ "'," + expenseAmount + ",'" + accountTransactionNo + "','"+ forwarder+"')";
				((java.sql.Statement) stmt).executeUpdate(query1);

				connection.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}

	}
	
	public static String createAccountTransactionNo() {
		  String []tables = {"MoneyTransfer", "Payment"};
		  int biggest = 0;
		  int current;
		  for(String table: tables) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection connection = DriverManager.getConnection(database_,databaseUsername, databasePassword);
					Statement stmt = (Statement) connection.createStatement();
					//select * from Uye_Puan order by Puan desc LIMIT 10
					ResultSet query2 = stmt.executeQuery(
							"SELECT accountTransactionNo  FROM " + table + " order by accountTransactionNo desc LIMIT 1");
					while (query2.next()) {
						
						current =  Integer.parseInt(query2.getString("accountTransactionNo"));
						if(current>biggest) {
							biggest = current;
						}
					}
					
					connection.close();
				} catch (Exception e) {
					// System.out.println(e);
				}
		  }
		  biggest++;
		  return  String.valueOf(biggest);
	}
	
	
}
