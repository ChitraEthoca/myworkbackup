package com.automation.framework.interfaces;

import java.util.HashMap;

import com.automation.framework.exceptions.BrowserException;
import com.automation.framework.exceptions.ElementFailException;
import com.automation.framework.exceptions.ObjectNameNotFoundException;

public interface Window {

	public void closeBrowser(HashMap<String, Object> params) throws BrowserException, ElementFailException, ObjectNameNotFoundException;

	public void navigateToURL(HashMap<String, Object> params) throws BrowserException, ElementFailException, ObjectNameNotFoundException;

	public void switchBetweenWindows(HashMap<String, Object> params) throws BrowserException, ElementFailException, ObjectNameNotFoundException;

	public void switchToWindowTitle(HashMap<String, Object> params)throws BrowserException, ElementFailException, ObjectNameNotFoundException;

	public void maximize(HashMap<String, Object> params) throws BrowserException, ElementFailException, ObjectNameNotFoundException;

	public void closeAllWindows(HashMap<String, Object> params) throws BrowserException, ElementFailException, ObjectNameNotFoundException;

	public void switchToLastWindow(HashMap<String, Object> params) throws BrowserException, ElementFailException, ObjectNameNotFoundException;

}