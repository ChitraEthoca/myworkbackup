package com.pge.COC.ReUsable.utility;
import java.sql.SQLException;
import java.util.ArrayList;

import com.pge.COC.ReUsable.utility.DB;
import com.pge.COC.ReUsable.utility.GlobalData;
import com.pge.COC.ReUsable.utility.PasswordUtility;

public class DBAccess {
public String getAccountNo(String sql1, String sql2) throws SQLException, InterruptedException{
	String accno = "";
	String sql3;
	boolean found=false;
	String ccbPassword = new PasswordUtility().decrypt(GlobalData.globalData.get("CCBDBPasswd"));
	//ArrayList <String> result1 = new DB().Execute_Query(GlobalData.globalData.get("CCBDBName"), GlobalData.globalData.get("CCBDBUserId"), GlobalData.globalData.get("CCBDBPasswd"), sql1);
	ArrayList <String> result1 = new DB().Execute_Query(GlobalData.globalData.get("CCBDBName"), GlobalData.globalData.get("CCBDBUserId"), ccbPassword, sql1);
	Thread.sleep(2000);
	for (int i = 0; i < result1.size(); i++) {
		sql3 = sql2.replace("Account_Number", result1.get(i));
		Thread.sleep(5000);
		//ArrayList <String> result2 = new DB().Execute_Query(GlobalData.globalData.get("CCBDBName"), GlobalData.globalData.get("CCBDBUserId"), GlobalData.globalData.get("CCBDBPasswd"), sql3);
		ArrayList <String> result2 = new DB().Execute_Query(GlobalData.globalData.get("CCBDBName"), GlobalData.globalData.get("CCBDBUserId"), ccbPassword, sql3);
		Thread.sleep(2000);
		if (result2.size()>0) {
			found = true;
			accno = result2.get(0);
			break;
		}
	}
	if (found) {
	return accno;
	} else{
		return "Account not found";
	}
}
}
