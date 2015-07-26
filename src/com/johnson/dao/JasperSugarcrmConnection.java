package com.johnson.dao;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;

public class JasperSugarcrmConnection {
  private final static String databaseName = "sugarcrm";
  private final static String url = "jdbc:postgresql://localhost:5433/";
  private final static String driver = "org.postgresql.Driver";
  private final static String username = "postgres";
  private final static String password = "postgres";
  private static BasicDataSource dataSource = null;
  
  public static DataSource SugarcrmDbConn() {
    // If there's already a db connection, return it.
    if(dataSource != null) {
      return dataSource;
    }
    
    // Try to look up the database connection to return.
    try {
      dataSource = new BasicDataSource();
      dataSource.setDriverClassName(driver);
      dataSource.setUsername(username);
      dataSource.setPassword(password);
      dataSource.setUrl( url + databaseName);
      
    } catch (Exception e) {
      e.printStackTrace();
    }
    return dataSource;
  }
  
}
