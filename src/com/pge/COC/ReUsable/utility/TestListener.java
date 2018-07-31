package com.pge.COC.ReUsable.utility;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.automation.framework.core.COCDriverScript;
import com.automation.framework.exceptions.BrowserException;

public class TestListener implements ITestListener {
	WebDriver driver = null;
	String filePath = "snapshots//";
	
	@Override
	public void onTestSuccess(ITestResult result) {
		COCDriverScript.logMessage("info","*****" + result.getName() + " test has passed *****");
		String testClassName = getTestClassName(result.getInstanceName()).trim();
		result.getName().toString().trim();
		try {
			if(RunTestNG.testsResultsMap != null){
				RunTestNG.testsResultsMap.put(testClassName, "PASS");
			}
			if(RunTestNG.testsRunnerMap != null){
				RunTestNG.applyPassStyle(RunTestNG.testsRunnerMap.get(testClassName));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			COCDriverScript.logMessage("error","BrowserException while taking screenshot:"+e);
		}
	}

	@Override
	public void onTestFailure(ITestResult result){
		COCDriverScript.logMessage("info","***** Error " + result.getName() + " test has failed *****");
		String testClassName = getTestClassName(result.getInstanceName()).trim();
		String methodName = result.getName().toString().trim();
		try {
			result.getThrowable().getMessage();
			StackTraceElement[] stackTrace = result.getThrowable().getCause().getStackTrace();
			StackTraceElement element = stackTrace[0];
			takeScreenShot(testClassName,methodName,result,element);
			if(RunTestNG.testsResultsMap != null){
				RunTestNG.testsResultsMap.put(testClassName, "FAIL");
			}
			if(RunTestNG.testsRunnerMap != null){
				RunTestNG.applyFailStyle(RunTestNG.testsRunnerMap.get(testClassName));
			}
			
		} catch (BrowserException e) {
			COCDriverScript.logMessage("error","BrowserException while taking screenshot:"+e);
		} catch (Exception e){
			COCDriverScript.logMessage("testStepFail","Exception:"+e.getMessage());
		}
	}

	public void takeScreenShot(String testClassName, String methodName, ITestResult result, StackTraceElement element) throws BrowserException{
		// get the driver
		Date date = new Date() ;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss") ;
		if (COCDriverScript.driver == null) {
			COCDriverScript.logMessage("error","Browser is not launched or Driver is failed to initialize");
			throw new BrowserException(
					new Throwable(
							"Browser is not launched or Driver is failed to initialize"));
		}
		driver = COCDriverScript.driver;
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		// The below method will save the screen shot in d drive with test
		// method name
		String msg= result.getThrowable().getMessage().replace("java.lang.Throwable:", "");
		String packageName = element.getClassName();
		String className;
		if (COCDriverScript.mapClassName.get(packageName) == null) {
			className = COCDriverScript.getClassname(element.getClassName());
			COCDriverScript.mapClassName.put(packageName, className);
		} else {
			className = COCDriverScript.mapClassName.get(packageName);
		}
		try {
			FileUtils.copyFile(scrFile, new File(filePath +testClassName+"."+methodName+"."+(dateFormat.format(date)) + ".png"));
			COCDriverScript.logMessage("info","***Placed screen shot in " + filePath + " ***");
			COCDriverScript.logMessage("testStepFail", " $$$$$ " + className + " : " + element.getMethodName()
			+ " $$$$$ "+msg+" $$$$$ file:///"+FileUtils.getFile(filePath +testClassName+"."+methodName+"."+(dateFormat.format(date)) + ".png").getAbsolutePath());
			Reporter.log("Snapshot taken failed step:"+"<a href='" + FileUtils.getFile(filePath +testClassName+"."+methodName+"."+(dateFormat.format(date)) + ".png").getAbsolutePath() + "'>screenshot</a>");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String getTestClassName(String testName) {
		String[] reqTestClassname = testName.split("\\.");
		int i = reqTestClassname.length - 1;
		COCDriverScript.logMessage("info","Required Test Name : " + reqTestClassname[i]);
		return reqTestClassname[i];
	}

	public void onFinish(ITestContext context) {
	}

	public void onTestStart(ITestResult result) {
	}

	public void onTestSkipped(ITestResult result) {
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	}

	public void onStart(ITestContext context) {
	}
}