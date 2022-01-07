package database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Loan {
	public static String database_ = "jdbc:mysql://localhost:3306/denemeoop";
	public static String databaseUsername = "denemeoop";
	public static String databasePassword = "denemeoop";
    public static double getRemainingDebt(project.Loan loan) {
        String[] tables = { "CorporateLoan", "IndividualLoan" };
        for (String table : tables) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection connection = DriverManager.getConnection(database_,databaseUsername, databasePassword);
                Statement stmt = (Statement) connection.createStatement();

                ResultSet query1 = ((java.sql.Statement) stmt)
                        .executeQuery("SELECT * FROM " + table + " WHERE loanNo = '" + loan.getLoanNo() + "'");
                while (query1.next()) {

                    double remainingDebt = query1.getDouble("remainingDebt");
                    connection.close();
                    return remainingDebt;

                }
                connection.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return 0;
    }

    public static void payLoanInterest(project.Loan loan, double amount) {
        double newRemainingDebt = getRemainingDebt(loan) - amount;
        String[] tables = { "CorporateLoan", "IndividualLoan" };
        for (String table : tables) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection connection = DriverManager.getConnection(database_,databaseUsername, databasePassword);
                Statement stmt = (Statement) connection.createStatement();

                String query1 = "UPDATE " + table + " SET remainingDebt=" + newRemainingDebt + " WHERE loanNo='"
                        + loan.getLoanNo() + "'";
                stmt.executeUpdate(query1);

                connection.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
}