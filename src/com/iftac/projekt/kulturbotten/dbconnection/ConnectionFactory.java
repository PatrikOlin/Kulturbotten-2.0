package com.iftac.projekt.kulturbotten.dbconnection;

import java.sql.Connection;

public interface ConnectionFactory {

	public Connection getConnection();
}