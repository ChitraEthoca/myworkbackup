package com.pge.COC.ReUsable.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DB {
	public ArrayList <String> Execute_Query(String dbName, String dbUserId, String dbPasswd, String sql) throws SQLException, InterruptedException{
		ArrayList <String> result = new ArrayList<String> ();
		Connection con=DriverManager.getConnection("jdbc:oracle:thin:@//cisdbtst11-vip:1521/"+dbName+".COMP.PGE.COM",dbUserId,dbPasswd);
		Thread.sleep(5000);
		Statement stmt=con.createStatement();  
		ResultSet rs=stmt.executeQuery(sql); 
		while(rs.next()){  
			result.add(rs.getString(1));  
		} 
		con.close();
		return result;
	}
}
