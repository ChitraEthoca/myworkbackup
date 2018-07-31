package com.automation.framework.core;

import java.io.IOException;

import org.apache.log4j.Logger;

import com.automation.framework.exceptions.DriverScriptException;

/**
 * @author manoj
 *
 */
public class BaseTest {

	public final static Logger logger = Logger.getLogger(BaseTest.class);
	private COCDriverScript d2DriverScript;

	/**
	 * to intialize the driver script
	 * @param almID 
	 * 
	 * @throws DriverScriptException
	 */
	public BaseTest(String almID) throws DriverScriptException {
		initializeDriver(almID);
	}

	/**
	 * @param almID 
	 * @throws DriverScriptException
	 *             Initialize driver.
	 * 
	 * @throws
	 */
	private void initializeDriver(String almID) throws DriverScriptException {
		try {
			d2DriverScript = new COCDriverScript(almID);
			if (logger.isDebugEnabled()) {
				COCDriverScript.logMessage("info","initializeDriver method: Start");
			}
			d2DriverScript.getGlobalConfigMap("config//GlobalParameter.xlsx");
//			d2DriverScript.initializeTestBaseSetup();
//			d2DriverScript.launchApplication();
		} catch (Exception e) {
			COCDriverScript.logMessage("error",e.getMessage());
			throw new DriverScriptException(e.getMessage());
		}
		finally{
            try {
                            Runtime.getRuntime().exec("taskkill /F /IM iexplore.exe");
                           // Runtime.getRuntime().exec("taskkill /F /IM chrome.exe");
                            Runtime.getRuntime().exec("taskkill /F /IM firefox.exe");
                            Runtime.getRuntime().exec("taskkill /F /IM safari.exe");
                            Runtime.getRuntime().exec("taskkill /F /IM opera.exe");
                            Runtime.getRuntime().exec("taskkill /F /IM winword.exe");
                            Runtime.getRuntime().exec("taskkill /F /IM excel.exe");
                            Runtime.getRuntime().exec("taskkill /F /IM AcroRd32.exe");
            } catch (IOException e) {
                            COCDriverScript.logMessage("testStepFail","Task kill method fails as a result of IOException");
                            e.printStackTrace();
            }
}
		if (logger.isDebugEnabled()) {
			COCDriverScript.logMessage("info","initializeDriver method: End");
		}
	}
}
