package com.thoughtworks.twu.selenium;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public final class Database {

	public static void clean() throws SQLException {
		executeUpdate("DELETE FROM item;");
	}

    public static void insertIntoItems(String name, String price, String description, String type) throws SQLException {
         executeUpdate("INSERT INTO item(item_id, name, price, description, type) values ( 999, '"+name+"','"+price+"','"+description+"','"+type+"');" );
    }

	private static void executeUpdate(String query) throws SQLException {
		Connection connection = null;
		Statement statement = null;
		try {
			connection = createConnection();
			statement = connection.createStatement();
			statement.executeUpdate(query);
		} catch (SQLException e) {
			throw e;
		} finally {
			if (connection != null) connection.close();
			if (statement != null) statement.close();
		}
	}

	private static Connection createConnection() throws SQLException {
		String url = "jdbc:postgresql://localhost:5432/trailblazers";
		String user = "postgres";
		String password = "postgres";
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Could not find driver");
		}
		return DriverManager.getConnection(url, user, password);
	}

}