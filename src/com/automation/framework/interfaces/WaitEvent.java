package com.automation.framework.interfaces;

import java.util.HashMap;

import com.automation.framework.exceptions.BrowserException;
import com.automation.framework.exceptions.ElementFailException;
import com.automation.framework.exceptions.ObjectNameNotFoundException;

public interface WaitEvent {

	public void implicitWait(HashMap<String, Object> params) throws BrowserException, ElementFailException, ObjectNameNotFoundException;
	
	public void waitUntilElementVisible(HashMap<String, Object> params) throws BrowserException, ElementFailException, ObjectNameNotFoundException;
	
	public void waitUntilElementClickable(HashMap<String, Object> params) throws BrowserException, ElementFailException, ObjectNameNotFoundException;
	
	public void wait(HashMap<String, Object> params) throws BrowserException, ElementFailException, ObjectNameNotFoundException;
}
