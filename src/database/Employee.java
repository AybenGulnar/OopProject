package database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import project.*;

public class Employee {
	public static String database_ = "jdbc:mysql://localhost:3306/denemeoop";
	public static String databaseUsername = "denemeoop";
	public static String databasePassword = "denemeoop";
	public static List<project.Account> getAccounts(project.Employee employee) {

		ArrayList<project.Account> accounts = new ArrayList<project.Account>();
		String[] tables = { "CurrentAccount", "TimeDepositAccount" };
		for (String table : tables) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection connection = DriverManager.getConnection(database_,databaseUsername, databasePassword);
				Statement stmt = (Statement) connection.createStatement();

				ResultSet query1 = stmt
						.executeQuery("SELECT * FROM " + table + " WHERE chargeEmployee = '" + employee.getId() + "'");
				while (query1.next()) {
					if (table == "CurrentAccount") {
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
						
						project.Customer customer = General.getCustomer(customerNo);
						project.Employee employee_ = General.getEmployee(chargeEmployee);

						project.CurrentAccount currentAccount = new CurrentAccount(customer, account_No, employee_);
						currentAccount.setBalance(amount);
						currentAccount.setBlocked(isBlocked);
						accounts.add(currentAccount);
					} else if (table == "TimeDepositAccount") {
						String account_No = query1.getString("accountNo");
						String mainAccount = query1.getString("mainAccount");
						double amount = query1.getDouble("amount");
						String customerNo = query1.getString("customerNo");
						String chargeEmployee = query1.getString("chargeEmployee");
						boolean isBlocked = false;
						Date startingDate = query1.getDate("startingDate");
						Date endDate = query1.getDate("endDate");
						double interest = query1.getDouble("interest");
						if (query1.getInt("isBlocked") == 1) {
							isBlocked = true;
						} else {
							isBlocked = false;
						}
						project.CurrentAccount mainAccount_ = (CurrentAccount) General.getAccount(mainAccount);
						project.Customer customer = General.getCustomer(customerNo);
						project.Employee employee_ = General.getEmployee(chargeEmployee);

						project.TimeDepositAccount timeDepositAccount = new project.TimeDepositAccount(customer,
								account_No, mainAccount_, startingDate, endDate, interest, employee_);
						timeDepositAccount.setBalance(amount);
						timeDepositAccount.setBlocked(isBlocked);
						accounts.add(timeDepositAccount);
					}

				}
				connection.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		return accounts;
	}

	public static ArrayList<project.Application> getApplications(project.Employee employee) {
		ArrayList<project.Application> applications = new ArrayList<project.Application>();
		
		String[] tables = { "CardApplication, AccountApplication, LoanApplication" };
		for (String table : tables) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection connection = DriverManager.getConnection(database_,databaseUsername, databasePassword);
				Statement stmt = (Statement) connection.createStatement();

				ResultSet query1 = stmt.executeQuery("SELECT * FROM " + table + " WHERE applicationNo = '" + "111" + "'");
				System.out.println("94");
				while (query1.next()) {
					System.out.println("78978");
					if(table == "CardApplication") {
						//applicationNo 	applicant 	applicationStatus 	applicationResult 	chargeEmployee 	kardNo 	
						String applicationNo = query1.getString("applicationNo");
						String applicant = query1.getString("applicant");
						String applicationStatus = query1.getString("applicationStatus");
						boolean applicationResult= false;
						if(query1.getInt("applicationResult") == 1) {
							applicationResult = true;
						}
						String kardNo = query1.getString("kardNo");
						
						project.Customer customer = General.getCustomer(applicant);
						project.Card card = General.getCard(kardNo);
						//String applicationNo, Customer applicant, String applicationStatus, boolean applicationResult, Card card
						project.CardApplication application = new project.CardApplication(applicationNo, customer, card, applicationStatus);
						application.setApplicationResult(applicationResult);
						applications.add(application);
					}
					else if(table == "AccountApplication") {
						System.out.println("fgsfdsg");
						//applicationNo 	applicant 	applicationStatus 	applicationResult 	accountNo 	chargeEmployee 	
						String applicationNo = query1.getString("applicationNo");
						String applicant = query1.getString("applicant");
						String applicationStatus = query1.getString("applicationStatus");
						boolean applicationResult= false;
						if(query1.getInt("applicationResult") == 1) {
							applicationResult = true;
						}
						String accountNo = query1.getString("accountNo");

						
						project.Customer customer = General.getCustomer(applicant);
						project.Account account = General.getAccount(accountNo);
						project.AccountApplication application = new project.AccountApplication(applicationNo, customer, account, applicationStatus);
						application.setApplicationResult(applicationResult);
						applications.add(application);
					}
					else if(table == "LoanApplication") {
						//applicationNo 	applicant 	applicationStatus 	applicationResult 	accountNo 	chargeEmployee 	
						String applicationNo = query1.getString("applicationNo");
						String applicant = query1.getString("applicant");
						String applicationStatus = query1.getString("applicationStatus");
						boolean applicationResult= false;
						if(query1.getInt("applicationResult") == 1) {
							applicationResult = true;
						}
						String loanNo = query1.getString("loanNo");
						
						project.Customer customer = General.getCustomer(applicant);
						project.Loan loan = General.getLoan(loanNo);
						//String applicationNo, Customer applicant, String applicationStatus, boolean applicationResult, Card card
						project.LoanApplication application = new project.LoanApplication(applicationNo, customer, loan, applicationStatus);
						application.setApplicationResult(applicationResult);
						applications.add(application);
					}
				}
				connection.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		return applications;
	}

	public static List<project.Worker> getWorkers(project.Employee employee) {
		List<project.Worker> workers = new ArrayList<project.Worker>();
		String[] tables = { "Worker" };
		for (String table : tables) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection connection = DriverManager.getConnection(database_,databaseUsername, databasePassword);
				Statement stmt = (Statement) connection.createStatement();

				ResultSet query1 = stmt.executeQuery("SELECT * FROM " + table + " WHERE chargeEmployee = '" + employee.getId() + "'");
				while (query1.next()) {
					
						String name = query1.getString("name");
						String userName = query1.getString("userName");
						String userPass = query1.getString("userPass");
						String employeeId = query1.getString("employeeId");
						double salary = query1.getDouble("salary");
						String mail = query1.getString("mail");

						connection.close();
						project.Worker worker = new Worker(name, userPass, employeeId, salary, mail);
						worker.setUserName(userName);
						workers.add(worker);
					

				}
				connection.close();
			} catch (Exception e) {
				// System.out.println(e);
			}
		}
		return workers;
	}

	public static void setName(project.Employee employee, String name) {
		String[] tables = { "Worker", "Manager" };
		for (String table : tables) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection connection = DriverManager.getConnection(database_,databaseUsername, databasePassword);
				Statement stmt = (Statement) connection.createStatement();

				String query1 = "UPDATE " + table + " SET name='" + name + "' WHERE employeeId='" + employee.getId()
						+ "'";
				stmt.executeUpdate(query1);

				connection.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

	public static void setUserPassword(project.Employee employee, String password) {
		String[] tables = { "Worker", "Manager" };
		for (String table : tables) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection connection = DriverManager.getConnection(database_,databaseUsername, databasePassword);
				Statement stmt = (Statement) connection.createStatement();

				String query1 = "UPDATE " + table + " SET userPass='" + password + "' WHERE employeeId='"
						+ employee.getId() + "'";
				stmt.executeUpdate(query1);

				connection.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

	public static void setUserName(project.Employee employee, String userName) {
		String[] tables = { "Worker", "Manager" };
		for (String table : tables) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection connection = DriverManager.getConnection(database_,databaseUsername, databasePassword);
				Statement stmt = (Statement) connection.createStatement();

				String query1 = "UPDATE " + table + " SET userName='" + userName + "' WHERE employeeId='"
						+ employee.getId() + "'";
				stmt.executeUpdate(query1);

				connection.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

	public static void setSalary(project.Employee employee, Double salary) {
		String[] tables = { "Worker", "Manager" };
		for (String table : tables) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection connection = DriverManager.getConnection(database_,databaseUsername, databasePassword);
				Statement stmt = (Statement) connection.createStatement();

				String query1 = "UPDATE " + table + " SET salary='" + salary + "' WHERE employeeId='" + employee.getId()
						+ "'";
				stmt.executeUpdate(query1);

				connection.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

	public static void setMail(project.Employee employee, String mail) {
		String[] tables = { "Worker", "Manager" };
		for (String table : tables) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection connection = DriverManager.getConnection(database_,databaseUsername, databasePassword);
				Statement stmt = (Statement) connection.createStatement();

				String query1 = "UPDATE " + table + " SET mail='" + mail + "' WHERE employeeId='" + employee.getId()
						+ "'";
				stmt.executeUpdate(query1);

				connection.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

	public static void setChargeEmployee(project.Employee employee, project.Employee employee2) {
		String[] tables = { "Worker", "Manager" };
		
		for (String table : tables) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection connection = DriverManager.getConnection(database_,databaseUsername, databasePassword);
				Statement stmt = (Statement) connection.createStatement();

				String query1 = "UPDATE " + table + " SET chargeEmployee='" + employee2.getId() + "' WHERE employeeId='"
						+ employee.getId() + "'";
				stmt.executeUpdate(query1);

				connection.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}
}