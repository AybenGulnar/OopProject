package database;

import java.sql.*;
import java.util.ArrayList;

import project.*;

public class Account {
	public static String database_ = "jdbc:mysql://localhost:3306/denemeoop";
	public static String databaseUsername = "denemeoop";
	public static String databasePassword = "denemeoop";
	public static void blockAccount(project.Account account) {
		String[] tables = { "CurrentAccount", "TimeDepositAccount" };
		for (String table : tables) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection connection = DriverManager.getConnection(database_,databaseUsername, databasePassword);
				Statement stmt = (Statement) connection.createStatement();

				String query1 = "UPDATE " + table + " SET isBlocked=" + 1 + " WHERE accountNo='"
						+ account.getAccountNo() + "'";

				stmt.executeUpdate(query1);

				connection.close();
			} catch (Exception e) {
				// System.out.println(e);
			}
		}
	}

	public static void activateAccount(project.Account account) {
		String[] tables = { "CurrentAccount", "TimeDepositAccount" };
		for (String table : tables) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection connection = DriverManager.getConnection(database_,databaseUsername, databasePassword);
				Statement stmt = (Statement) connection.createStatement();

				String query1 = "UPDATE " + table + " SET isBlocked=" + 0 + " WHERE accountNo='"
						+ account.getAccountNo() + "'";

				stmt.executeUpdate(query1);

				connection.close();
			} catch (Exception e) {
				// System.out.println(e);
			}
		}
	}

	public static void addBalance(project.Account account, double amount) {
		String[] tables = { "CurrentAccount", "TimeDepositAccount" };
		for (String table : tables) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection connection = DriverManager.getConnection(database_,databaseUsername, databasePassword);
				Statement stmt = (Statement) connection.createStatement();

				double balance = 0;

				ResultSet query2 = stmt.executeQuery(
						"SELECT amount FROM " + table + " WHERE accountNo = '" + account.getAccountNo() + "'");
				while (query2.next()) {
					balance = query2.getDouble("amount");
				}
				String query1 = "UPDATE " + table + " SET amount=" + (amount + balance) + " WHERE accountNo='"
						+ account.getAccountNo() + "'";
				stmt.executeUpdate(query1);

				connection.close();
			} catch (Exception e) {
				// System.out.println(e);
			}
		}
	}

	public static void setStartingDate(project.Account account, Date startingDate) {
		String[] tables = { "CurrentAccount", "TimeDepositAccount" };
		for (String table : tables) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection connection = DriverManager.getConnection(database_,databaseUsername, databasePassword);
				Statement stmt = (Statement) connection.createStatement();

				String query1 = "UPDATE " + table + " SET startingDate=" + startingDate + " WHERE accountNo='"
						+ account.getAccountNo() + "'";

				stmt.executeUpdate(query1);

				connection.close();
			} catch (Exception e) {
				// System.out.println(e);
			}
		}
		System.out.println("Baþlangýç tarihi set edildi (Fonksiyon boþ)");
	}

	public static void setEndDate(project.Account account, Date endDate) {
		String[] tables = { "CurrentAccount", "TimeDepositAccount" };
		for (String table : tables) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection connection = DriverManager.getConnection(database_,databaseUsername, databasePassword);
				Statement stmt = (Statement) connection.createStatement();

				String query1 = "UPDATE " + table + " SET endDate=" + endDate + " WHERE accountNo='"
						+ account.getAccountNo() + "'";

				stmt.executeUpdate(query1);

				connection.close();
			} catch (Exception e) {
				// System.out.println(e);
			}
		}
		System.out.println("Bitiþ tarihi set edildi (Fonksiyon boþ)");
	}

	public static void setInterest(project.Account account, Double interest) {
		String[] tables = { "CurrentAccount", "TimeDepositAccount" };
		if (account instanceof project.CurrentAccount) {
			System.out.println("vadeli hesapta faiz set edilemez");
			return;
		}
		for (String table : tables) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection connection = DriverManager.getConnection(database_,databaseUsername, databasePassword);
				Statement stmt = (Statement) connection.createStatement();

				String query1 = "UPDATE " + table + " SET interest=" + interest + " WHERE accountNo='"
						+ account.getAccountNo() + "'";

				stmt.executeUpdate(query1);

				connection.close();
			} catch (Exception e) {
				// System.out.println(e);
			}
		}
	}

	public static void setBalance(project.Account account, double balance) {
		String[] tables = { "CurrentAccount", "TimeDepositAccount" };
		for (String table : tables) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection connection = DriverManager.getConnection(database_,databaseUsername, databasePassword);
				Statement stmt = (Statement) connection.createStatement();

				String query1 = "UPDATE " + table + " SET amount=" + balance + " WHERE accountNo='"
						+ account.getAccountNo() + "'";

				stmt.executeUpdate(query1);

				connection.close();
			} catch (Exception e) {
				// System.out.println(e);
			}
		}
	}

	public static void setChargeEmployee(project.Account account, project.Employee employee) {
		String[] tables = { "CurrentAccount", "TimeDepositAccount" };
		for (String table : tables) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection connection = DriverManager.getConnection(database_,databaseUsername, databasePassword);
				Statement stmt = (Statement) connection.createStatement();

				String query1 = "UPDATE " + table + " SET chargeEmployee='" + employee.getId() + "' WHERE accountNo='"
						+ account.getAccountNo() + "'";

				stmt.executeUpdate(query1);

				connection.close();
			} catch (Exception e) {
				// System.out.println(e);
			}
		}
	}

	public static ArrayList<project.AccountTransaction> getAccountTransactions(project.Account account) {
		ArrayList<project.AccountTransaction> accountTransactions = new ArrayList<project.AccountTransaction>();
		String[] tables = { "MoneyTransfer", "Payment" };
		for (String table : tables) {

			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection connection = DriverManager.getConnection(database_,databaseUsername, databasePassword);
				Statement stmt = (Statement) connection.createStatement();
				ArrayList<ResultSet> queries = new ArrayList<ResultSet>();
				ResultSet query1 = stmt
						.executeQuery("SELECT * FROM " + table + " WHERE forwarder = '" + account.getAccountNo() + "'");
				queries.add(query1);
//				if(table == "MoneyTransfer") {
//					ResultSet query2 = stmt
//							.executeQuery("SELECT * FROM " + table + " WHERE receiver = '" + account.getAccountNo() + "'");
//					queries.add(query2);
//				}
				
				//for (ResultSet query : queries) {
					
					while (query1.next()) {
						String accountTransactionNo = query1.getString("accountTransactionNo");

						if (table == "MoneyTransfer") {
							project.MoneyTransfer moneyTransfer = (project.MoneyTransfer)database.General.getAccountTransaction(accountTransactionNo);
							accountTransactions.add(moneyTransfer);

						} else if (table == "Payment") {
							project.Payment payment = (project.Payment)database.General.getAccountTransaction(accountTransactionNo);
							accountTransactions.add(payment);
						}
					}
				//}
				connection.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}

		return accountTransactions;
	}
}
