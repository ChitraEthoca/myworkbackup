package com.automation.framework.utilities;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.UnsupportedEncodingException;

import javax.xml.bind.DatatypeConverter;

import com.automation.framework.core.COCDriverScript;
import com.automation.framework.exceptions.DriverScriptException;

public class Util {

	/**
	 * Encode the string.
	 *
	 * @param value the value
	 * @return the string
	 */
	public static String encode(String value){
		try {
			return DatatypeConverter.printBase64Binary(value.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Decode the string.
	 *
	 * @param value the value
	 * @return the string
	 */
	public static String decode(String value){
		try {
			return new String(DatatypeConverter.parseBase64Binary(value), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	/*
	 * 
	 * get File path for Import the documents
	 * 
	 */
	public static String getFilePathForImport(String importFile) throws DriverScriptException{
		COCDriverScript.logMessage("info", "Verifying "+importFile+" exists..");
		File file  = new File("importdocuments//"+importFile);
		if(file.exists()){
			COCDriverScript.logMessage("info", importFile+" exists and absolute path to file is "+file.getAbsolutePath());
			return file.getAbsolutePath();
		}else{
			Robot robot = null;
			try {
				robot = new Robot();
			} catch (AWTException e) {
			}
			robot.keyPress(KeyEvent.VK_ESCAPE);
			robot.keyPress(KeyEvent.VK_ESCAPE);
			robot.keyPress(KeyEvent.VK_ESCAPE);
		}
		throw new DriverScriptException(importFile+" does not exists ");
	}
}
