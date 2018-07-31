package com.pge.COC.ReUsable.utility;

import java.util.HashMap;

public class GlobalData {
	public static HashMap<String, String>  globalData;
	static{
		globalData = new HashMap<String, String>();
		ExcelTestData excel = new ExcelTestData("DataSheets//Global_Params.xlsx", "Global_Params");
		System.out.println(excel.getColCount());
		for (int i = 0; i < excel.getColCount(); i++) {
			globalData.put(excel.getCellValue(0, i), excel.getCellValue(1, i));
			System.out.println(excel.getCellValue(0, i) + ":" + excel.getCellValue(1, i));
		}
	}
	public static void main(String args[]){
		System.out.println(globalData.get("Url"));
	}
}



