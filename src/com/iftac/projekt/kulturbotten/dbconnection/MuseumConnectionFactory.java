package com.iftac.projekt.kulturbotten.dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MuseumConnectionFactory implements ConnectionFactory {

	private static MuseumConnectionFactory instance;

	private MuseumConnectionFactory() {
	}

	public static synchronized MuseumConnectionFactory getInstance() {
		if (instance == null) {
			instance = new MuseumConnectionFactory();
		}
		return instance;
	}

	static {
		try {
			// The newInstance() call is a work around for some
			// broken Java implementations

			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (Exception ex) {
			System.out.println("Unable to load mysql jdbc driver: " + ex.getMessage());
		}
	}

	@Override
	public Connection getConnection() {

		try {

			// Skapar connection till databasen
			return DriverManager
					.getConnection("jdbc:mysql://localhost/museum?" + "user=root&password=123456&useSSL=false");
		} catch (SQLException e) {
			System.out.println("Unable to connect to database");
			e.printStackTrace();
		}
		return null;
	}

}
