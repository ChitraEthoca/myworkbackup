package com.pge.COC.DataBase.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Utilized for creating DB Connections, get DB Results
 * 
 * @author Joydip Mukherjee J923 | 1370430
 *
 */
public class DBOperation_Oracle {

	private Connection connection;

	public DBOperation_Oracle(String hostName, String portNo, String serviceName, String userName, String password) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@" + hostName + ":" + portNo + "/" + serviceName;
			this.connection = DriverManager.getConnection(url, userName, password);
		}

		catch (ClassNotFoundException e) {
			e.printStackTrace();

		} catch (SQLException e) {
			e.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	/**
	 * 
	 * @param tnsnameoraPath Path of tnsnames.ora. For us it could be C:/ProgramData/TNS_ADMIN
	 * @param tnsName
	 * @param userName
	 * @param password
	 */
	public DBOperation_Oracle(String tnsnameoraPath,String tnsName, String userName, String password) {
		try {

			System.setProperty("oracle.net.tns_admin", tnsnameoraPath);
			String dbURL = "jdbc:oracle:thin:@"+tnsName;
			Class.forName("oracle.jdbc.OracleDriver");
			this.connection = DriverManager.getConnection(dbURL, userName, password);
			System.out.println("Connection established");
		}

		catch (ClassNotFoundException e) {
			e.printStackTrace();

		} catch (SQLException e) {
			e.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	/**
	 * return the {@link Connection} object
	 * 
	 * @return {@link Connection}
	 */
	public Connection getConnection() {
		return this.connection;

	}

	/**
	 * Close the connection. On success return true. else false
	 * 
	 * @return {@link Boolean}
	 */
	public boolean closeConnection() {
		try {
			this.connection.close();
			return true;
		} catch (SQLException e) {
			return false;
		}
	}

	/**
	 * Return the results of select query in {@link ArrayList}
	 * <{@link HashMap}<{@link String}, {@link String}>> format
	 * 
	 * @param selectQuery
	 *            {@link String}
	 * @return {@link ArrayList} <{@link HashMap}<{@link String},
	 *         {@link String}>>
	 */
	public ArrayList<HashMap<String, String>> getDataFromDB(String selectQuery) {
		ArrayList<HashMap<String, String>> dbResult = new ArrayList<HashMap<String, String>>();
		try {
			System.out.println(selectQuery);
			Statement statement = this.connection.createStatement();
			ResultSet resultSet = statement.executeQuery(selectQuery);
			ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
			int columnCount = resultSetMetaData.getColumnCount();

			while (resultSet.next()) {
				HashMap<String, String> row = new HashMap<String, String>();
				for (int i = 1; i <= columnCount; i++) {
					row.put(resultSetMetaData.getColumnName(i), resultSet.getString(i));
				}
				dbResult.add(row);
			}
			return dbResult;

		} catch (SQLException e) {
			e.printStackTrace();
			return dbResult;
		} catch (Exception e) {
			e.printStackTrace();
			return dbResult;
		}
	}

	/**
	 * Utilized for Insert, update or delete any record
	 * 
	 * @param sqlQuery
	 *            {@link String}
	 * @return {@link Integer} Number of Rows get impacted
	 */
	public int updateInsertDataInDB(String sqlQuery) {
		int i = 0;
		try {
			Statement statement = this.connection.createStatement();
			i = statement.executeUpdate(sqlQuery);
			return i;

		} catch (SQLException e) {
			e.printStackTrace();
			return i;
		} catch (Exception e) {
			e.printStackTrace();
			return i;
		}
	}

/*	public static void main(String a[]) {
		DBOperation_Oracle dbOperation = new DBOperation_Oracle("C:/ProgramData/TNS_ADMIN","SQMDT_ORA12",
				"SQMDSTG", "Stgsqmd$$287");
		ArrayList<HashMap<String, String>> results1 = dbOperation
				.getDataFromDB("select * from SQMDCORE.USAGE_FACTOR where trade_dt='02-SEP-2017'");

		System.out.println("Size: "+ results1.size());
		
		for (HashMap<String, String> row : results1) {
			System.out.println(row.get("USG_FACT_ID"));
			System.out.println(row.get("TRADE_DT"));
			System.out.println(row.get("PROFL_PCT"));
			System.out.println(row.get("INTVL_PCT"));
			System.out.println(row.get("COMT"));

		}
	}*/

}