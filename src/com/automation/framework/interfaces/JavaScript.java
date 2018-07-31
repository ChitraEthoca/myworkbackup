package com.automation.framework.interfaces;

import java.util.HashMap;


 /**
  * 
  * @author Automation COE
  *
  */
public interface JavaScript {
	  public void scrollAndClick(HashMap<String, Object> params) throws Exception;
	  
		public void scrollToBottom(HashMap<String, Object> params) throws Exception;
		
		public void handleAlert(HashMap<String, Object> params) throws Exception;
		
}
