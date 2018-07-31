package com.automation.framework.interfaces;

import java.util.HashMap;

import com.automation.framework.exceptions.BrowserException;
import com.automation.framework.exceptions.ElementFailException;
import com.automation.framework.exceptions.ObjectNameNotFoundException;

/**
 * 
 * @author Automation COE
 *
 */
public interface Element {
	//Ritesh modified
	public void click(HashMap<String, Object> params) throws BrowserException, ElementFailException, ObjectNameNotFoundException;
	
	public void getText(HashMap<String, Object> params) throws BrowserException, ElementFailException, ObjectNameNotFoundException ;
	
	public void doubleClick(HashMap<String, Object> params) throws BrowserException, ElementFailException, ObjectNameNotFoundException;

	public void rightClick(HashMap<String, Object> params) throws BrowserException, ElementFailException, ObjectNameNotFoundException;
	
	public void IsElementExist(HashMap<String, Object> params) throws BrowserException, ElementFailException, ObjectNameNotFoundException;
	
	public void isElementDisplayed(HashMap<String, Object> params) throws BrowserException, ElementFailException, ObjectNameNotFoundException;
	
	public void getElementAttributeValue(HashMap<String, Object> params) throws BrowserException, ElementFailException, ObjectNameNotFoundException;
	
	public void mouseHover(HashMap<String, Object> params) throws BrowserException, ElementFailException, ObjectNameNotFoundException;
	
	public void clear(HashMap<String, Object> params)throws BrowserException, ElementFailException, ObjectNameNotFoundException ;
	
	public void setValueToXpathAndClick(HashMap<String, Object> params)throws BrowserException, ElementFailException, ObjectNameNotFoundException ;
}
