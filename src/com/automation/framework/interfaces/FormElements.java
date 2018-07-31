package com.automation.framework.interfaces;

import java.util.HashMap;

import com.automation.framework.exceptions.BrowserException;
import com.automation.framework.exceptions.ElementFailException;
import com.automation.framework.exceptions.ObjectNameNotFoundException;

public interface FormElements {

	public void isRadioButtonSelected(HashMap<String, Object> params)
			throws BrowserException, ElementFailException, ObjectNameNotFoundException;

	public void isCheckboxChecked(HashMap<String, Object> params)
			throws BrowserException, ElementFailException, ObjectNameNotFoundException;

	public void selectCheckBox(HashMap<String, Object> params)
			throws BrowserException, ElementFailException, ObjectNameNotFoundException;

	public void unSelectCheckBox(HashMap<String, Object> params)
			throws BrowserException, ElementFailException, ObjectNameNotFoundException;

	public void setMandatoryTextToElement(HashMap<String, Object> params)
			throws BrowserException, ElementFailException, ObjectNameNotFoundException;

	public void setOptionalTextToElement(HashMap<String, Object> params)
			throws BrowserException, ElementFailException, ObjectNameNotFoundException;

	public void selectValueFromDropdown(HashMap<String, Object> params)
			throws BrowserException, ElementFailException, ObjectNameNotFoundException;
}
