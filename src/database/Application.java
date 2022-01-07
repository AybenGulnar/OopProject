package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Application {
	public static String database_ = "jdbc:mysql://localhost:3306/denemeoop";
	public static String databaseUsername = "denemeoop";
	public static String databasePassword = "denemeoop";
	public static void applicationApprovet(project.Application application) {
        String[] tables = { "CardApplication", "LoanApplication", "AccountApplication" };
        for (String table : tables) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection connection = DriverManager.getConnection(database_,databaseUsername, databasePassword);
                Statement stmt = (Statement) connection.createStatement();

                String query1 = "UPDATE " + table + " SET applicationResult= "+1+" WHERE applicationNo='"
                        + application.getApplicationNo() + "'";
                stmt.executeUpdate(query1);
                setApplicationStatus(application, "application approved");
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
	public static void applicationRefuse(project.Application application) {
        String[] tables = { "CardApplication", "LoanApplication", "AccountApplication" };
        for (String table : tables) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection connection = DriverManager.getConnection(database_,databaseUsername, databasePassword);
                Statement stmt = (Statement) connection.createStatement();

                String query1 = "UPDATE " + table + " SET applicationResult= 0 WHERE applicationNo='"
                        + application.getApplicationNo() + "'";
                stmt.executeUpdate(query1);
                connection.close();
                setApplicationStatus(application, "application rejected");
            } catch (Exception e) {
                System.out.println(e);
            }
        }

    }

	
	public static void setApplicationStatus(project.Application application, String applicationStatus) {
		 String[] tables = { "CardApplication", "LoanApplication", "AccountApplication" };
	        for (String table : tables) {
	            try {
	                Class.forName("com.mysql.cj.jdbc.Driver");
	                Connection connection = DriverManager.getConnection(database_,databaseUsername, databasePassword);
	                Statement stmt = (Statement) connection.createStatement();

	                String query1 = "UPDATE " + table + " SET applicationStatus= '"+applicationStatus+"' WHERE applicationNo='"
	                        + application.getApplicationNo() + "'";
	                stmt.executeUpdate(query1);
	                connection.close();
	            } catch (Exception e) {
	                System.out.println(e);
	            }
	        }
	}
	
	public static void setApplicationEmployee(project.Application application, project.Employee employee) {
		 String[] tables = { "CardApplication", "LoanApplication", "AccountApplication" };
	        for (String table : tables) {
	            try {
	                Class.forName("com.mysql.cj.jdbc.Driver");
	                Connection connection = DriverManager.getConnection(database_,databaseUsername, databasePassword);
	                Statement stmt = (Statement) connection.createStatement();

	                String query1 = "UPDATE " + table + " SET chargeEmployee= '"+employee.getId()+"' WHERE applicationNo='"
	                        + application.getApplicationNo() + "'";
	                stmt.executeUpdate(query1);
	                connection.close();
	            } catch (Exception e) {
	                System.out.println(e);
	            }
	        }
	}
}
