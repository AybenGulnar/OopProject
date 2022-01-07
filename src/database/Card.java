package database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import project.*;

public class Card {
	public static String database_ = "jdbc:mysql://localhost:3306/denemeoop";
	public static String databaseUsername = "denemeoop";
	public static String databasePassword = "denemeoop";
	public static List<project.Payment> getPayments(project.Card card) {
		ArrayList<project.Payment> payments = new ArrayList<project.Payment>();
		String[] tables = { "Payment" };
		for (String table : tables) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection connection = DriverManager.getConnection(database_,databaseUsername, databasePassword);
				Statement stmt = (Statement) connection.createStatement();

				ResultSet query1 = stmt.executeQuery("SELECT * FROM " + table + " WHERE card = '" + card.getCardNo() + "'");
				while (query1.next()) {

					Date date = query1.getDate("date");
					String description = query1.getString("description");
					double amount = query1.getDouble("amount");
					String accountTransactionNo_ = query1.getString("accountTransactionNo");

					connection.close();
					project.Payment payment = new Payment(date, description, card, amount, accountTransactionNo_);
					payments.add(payment);

				}
				connection.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		return payments;
	}

	public static void addCardPoint(project.Card card, double point) {
		double newCardPoint = getCardPoint(card) + point;
		String[] tables = { "CorporateCard", "IndividualCard" };
		for (String table : tables) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection connection = DriverManager.getConnection(database_,databaseUsername, databasePassword);
				Statement stmt = (Statement) connection.createStatement();

				String query1 = "UPDATE " + table + " SET cardPoint='" + newCardPoint + "' WHERE cardNo='"
						+ card.getCardNo() + "'";
				stmt.executeUpdate(query1);

				connection.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

	public static double getCardPoint(project.Card card) {
		String[] tables = { "CorporateCard", "IndividualCard" };
		for (String table : tables) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection connection = DriverManager.getConnection(database_,databaseUsername, databasePassword);
				Statement stmt = (Statement) connection.createStatement();

				ResultSet query1 = ((java.sql.Statement) stmt)
						.executeQuery("SELECT * FROM " + table + " WHERE cardNo = '" + card.getCardNo() + "'");
				while (query1.next()) {

					double cardPoint = query1.getDouble("cardPoint");
					connection.close();
					return cardPoint;

				}
				connection.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		return 0;
	}

	public static void setCardLimit(project.Card card, double limit) {
		String[] tables = { "CorporateCard", "IndividualCard" };
		for (String table : tables) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection connection = DriverManager.getConnection(database_,databaseUsername, databasePassword);
				Statement stmt = (Statement) connection.createStatement();

				String query1 = "UPDATE " + table + " SET cardLimit='" + limit + "' WHERE cardNo='" + card.getCardNo()
						+ "'";
				stmt.executeUpdate(query1);

				connection.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

	public static void addAvailableLimit(project.Card card, double limit) {
		double newAvailableLimit = getAvailableLimit(card) + limit;
		String[] tables = { "CorporateCard", "IndividualCard" };
		for (String table : tables) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection connection = DriverManager.getConnection(database_,databaseUsername, databasePassword);
				Statement stmt = (Statement) connection.createStatement();

				String query1 = "UPDATE " + table + " SET availableLimit='" + newAvailableLimit + "' WHERE cardNo='"
						+ card.getCardNo() + "'";
				stmt.executeUpdate(query1);

				connection.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

	public static double getAvailableLimit(project.Card card) {
		String[] tables = { "CorporateCard", "IndividualCard" };
		for (String table : tables) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection connection = DriverManager.getConnection(database_,databaseUsername, databasePassword);
				Statement stmt = (Statement) connection.createStatement();

				ResultSet query1 = ((java.sql.Statement) stmt)
						.executeQuery("SELECT * FROM " + table + " WHERE cardNo = '" + card.getCardNo() + "'");
				while (query1.next()) {

					double availableLimit = query1.getDouble("availableLimit");
					connection.close();
					return availableLimit;

				}
				connection.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		return 0;
	}

	public static void addBalance(project.Card card, double balance) {
		double newBalance = getBalance(card) + balance;
		String[] tables = { "CorporateCard", "IndividualCard" };
		for (String table : tables) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection connection = DriverManager.getConnection(database_,databaseUsername, databasePassword);
				Statement stmt = (Statement) connection.createStatement();

				String query1 = "UPDATE " + table + " SET balance='" + newBalance + "' WHERE cardNo='"
						+ card.getCardNo() + "'";
				stmt.executeUpdate(query1);

				connection.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}

	}

	public static double getBalance(project.Card card) {
		String[] tables = { "CorporateCard", "IndividualCard" };
		for (String table : tables) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection connection = DriverManager.getConnection(database_,databaseUsername, databasePassword);
				Statement stmt = (Statement) connection.createStatement();

				ResultSet query1 = ((java.sql.Statement) stmt)
						.executeQuery("SELECT * FROM " + table + " WHERE cardNo = '" + card.getCardNo() + "'");
				while (query1.next()) {

					double balance = query1.getDouble("balance");
					connection.close();
					return balance;

				}
				connection.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		return 0;
	}
}