package com.automation.framework.interfaces;


import java.util.HashMap;

import com.automation.framework.exceptions.BrowserException;
import com.automation.framework.exceptions.ElementFailException;
import com.automation.framework.exceptions.ObjectNameNotFoundException;

public interface Validation {

	public void verifyPageTitle(HashMap<String, Object> params) throws BrowserException, ElementFailException, ObjectNameNotFoundException;
	
	public void verifyElementText(HashMap<String, Object> params) throws BrowserException, ElementFailException, ObjectNameNotFoundException;

	public void verifyPageURL(HashMap<String, Object> params) throws BrowserException, ElementFailException, ObjectNameNotFoundException;
}
