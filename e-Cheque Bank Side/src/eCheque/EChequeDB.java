/*
 * EChequeDB.java
 *
 * Created on June 6, 2007, 6:02 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package eCheque;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
/**
 * 
 * @author Saad
 */

public class EChequeDB {

	private static final String DATABASE_CONFIG_FILE = "databaseconfig.properties";
	
	private String jdbc_driver;
	private String database_url;
	private String userName;
	private String password;
	private Connection connection = null;
	private Statement sqlStatement = null;
	private DatabaseMode databaseMode;
	private ResultSet resultSet;

	/** Creates a new instance of EChequeDB 
	 * @throws IOException */
	public EChequeDB() throws IOException {
		InputStream input = new FileInputStream(new File(DATABASE_CONFIG_FILE));
		Properties props = new Properties();
		props.load(input);
		
		jdbc_driver = props.getProperty("JDBC_DRIVER");
		database_url = props.getProperty("DATABASE_URL");
		userName = props.getProperty("USERNAME");
		password = props.getProperty("PASSWORD");
	}

	private boolean connectToDataBase() throws ClassNotFoundException,
			SQLException {
		// Initialize Connection to DB:
		Class.forName(jdbc_driver); // load database driver class
		// establish connection to database
		connection = DriverManager.getConnection(database_url, userName,
				password);
		return true;
	}

	private boolean closeDataBaseConnection() {
		try {
			// close the database connection channel
			connection.close();
			sqlStatement.close();
			return true;
		} catch (SQLException exp) {
			exp.printStackTrace();
			return false;
		}
	}

	private boolean createStatment() throws SQLException {

		sqlStatement = connection.createStatement();
		return true;
	}

	private void executeSQLStatment(String statment, DatabaseMode statType)
			throws SQLException {

		// Initialize sql statment and execute it.
		if (statType == DatabaseMode.QUERY) {
			resultSet = sqlStatement.executeQuery(statment);
		}
		
		if (statType == DatabaseMode.UPDATE) {
			sqlStatement.executeUpdate(statment);
		}
	}

	public boolean runDB(DatabaseMode mode, String databaseStat) {
		databaseMode = mode;
		boolean flag = false;
		try {
			connectToDataBase();
			createStatment();

			// run the specific sql statment
			executeSQLStatment(databaseStat, databaseMode);
			flag = true;
		} catch (ClassNotFoundException exp) {
			exp.printStackTrace();
		} catch (MySQLIntegrityConstraintViolationException exp)
		{
			return false;
		} catch (SQLException exp) {
			exp.printStackTrace();
			

		} finally {
			closeDataBaseConnection();
		}

		return flag;
	}

	public boolean runDB(DatabaseMode mode, String databaseStat, double[] balance) {
		databaseMode = mode;
		boolean flag = false;

		try {
			connectToDataBase();
			System.out.println("You are connected to e-Cheque Bank DB");
			createStatment();
			System.out.println("You have created a statement");

			// run the specific sql statment
			executeSQLStatment(databaseStat, databaseMode);
			if (resultSet.next()) {
				balance[0] = resultSet.getDouble(1);
				flag = true;
			} else {
				balance[0] = 0.0;
				flag = false;
			}

		} catch (ClassNotFoundException exp) {
			exp.printStackTrace();

		} catch (SQLException exp) {
			exp.printStackTrace();

		} finally {
			closeDataBaseConnection();
		}

		return flag;
	}

	public boolean runDB(String databaseStat, DatabaseMode mode) {
		databaseMode = mode;
		boolean flag = false;

		try {
			connectToDataBase();
			System.out.println("You are connected to e-Cheque Bank DB");
			createStatment();
			System.out.println("You have created a statement");

			// run the specific sql statment
			executeSQLStatment(databaseStat, databaseMode);
			if (resultSet.next()) {
				flag = true;
			} else {
				flag = false;
			}

		} catch (ClassNotFoundException exp) {
			exp.printStackTrace();

		} catch (SQLException exp) {
			exp.printStackTrace();

		} finally {
			closeDataBaseConnection();
		}

		return flag;
	}
}