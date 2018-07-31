package com.automation.framework.interfaces;

import java.util.HashMap;

public interface Table {

	
	public void getNumberOfRowOfTable(HashMap<String, Object> params) throws Exception;

	
	public void getNumberOfColumnOfTable(HashMap<String, Object> params) throws Exception;

	
	public void getDataFromParticularCell(HashMap<String, Object> params) throws Exception;
	

	
	
	
}
