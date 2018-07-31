package com.pge.COC.ReUsable.utility;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.perfectomobile.selenium.api.IMobileDriver;
import com.perfectomobile.selenium.util.EclipseConnector;
public class ClearCache {
public static void main(String args[]) throws InterruptedException, IOException{
	DesiredCapabilities capabilities = new DesiredCapabilities("Safari", "", Platform.ANY);
//	String host = "";		
	capabilities.setCapability("user", "rzb7@pge.com");
	capabilities.setCapability("password", "password");

	// Use the automationName capability to define the required framework - Appium (this is the default) or PerfectoMobile.
	// capabilities.setCapability("automationName", "PerfectoMobile");

	//Based on device id or Device type the driver will be instantiated
	capabilities.setCapability("deviceName", "A694B1A06B3EE498ACCDA332092C4E7EC9067D2C");
	

		capabilities.setCapability("OS", "iOS");
	setExecutionIdCapability(capabilities, "pge.perfectomobile.com");
	WebDriver driver = new RemoteWebDriver(new URL("https://pge.perfectomobile.com/nexperience/perfectomobile/wd/hub"), capabilities);
	

        //defining variables
        HashMap<String, Object> params = new HashMap();
        String osVer;
//        switchToContext("NATIVE_APP");
        //get os version:
        params.put("property", "osVersion");
        osVer = (String) ((RemoteWebDriver) driver).executeScript("mobile:handset:info", params);
        
      //Close Safari browser
        
//        params.clear();
//       
//        ((IMobileDriver) (driver)).getDevice("A694B1A06B3EE498ACCDA332092C4E7EC9067D2C").getNativeDriver("Safari").close();
       
        
        //Launch Settings Application on it's main page
        params.clear();
        params.put("name", "Settings");
        try {
            ((RemoteWebDriver) driver).executeScript("mobile:application:close", params);
        } catch (Exception e) {}
        Thread.sleep(1000);
        ((RemoteWebDriver) driver).executeScript("mobile:application:open", params);
        Thread.sleep(1000);
        //Scroll to Top
        params.clear();
        params.put("location", "1%,1%");
        ((RemoteWebDriver) driver).executeScript("mobile:touch:tap", params);
        //click Safari:
        params.clear();
        params.put("content", "Safari");
        params.put("scrolling", "scroll");
//        params.put("levels.high", "100");
        params.put("next", "SWIPE=(20%,70%),(20%,30%);WAIT=2000");
        params.put("screen.width", "70%");
        params.put("screen.top", "0%");
        params.put("screen.left", "0%");
        params.put("screen.height", "100%");
        ((RemoteWebDriver) driver).executeScript("mobile:text:select", params);
        //swipe to bottom:
        params.clear();
        params.put("start", "50%,90%");
        params.put("end", "50%,10%");
        for (int i=0;i<3;i++){
            ((RemoteWebDriver) driver).executeScript("mobile:touch:swipe", params);
        }
        //clear Cache
        params.clear();
        params.put("value", "//*[starts-with(text(),'Clear History')]");
        params.put("framework", "perfectoMobile");
        ((RemoteWebDriver) driver).executeScript("mobile:application.element:click", params);
        params.put("value", "//*[(@class='UIAButton' or @class='UIATableCell') and starts-with(@label,'Clear') and @isvisible='true']");
        ((RemoteWebDriver) driver).executeScript("mobile:application.element:click", params);
        //below version 8 need to clear also data:
        if (!osVer.startsWith("8.")){
            params.put("value", "//*[starts-with(text(),'Clear Cookies')]");
            ((RemoteWebDriver) driver).executeScript("mobile:application.element:click", params);
            params.put("value", "//*[(@class='UIAButton' or @class='UIATableCell') and starts-with(@label,'Clear') and @isvisible='true']");
            ((RemoteWebDriver) driver).executeScript("mobile:application.element:click", params);
        }
    }
	private static void setExecutionIdCapability(DesiredCapabilities capabilities, String host) throws IOException {
	EclipseConnector connector = new EclipseConnector();
	String eclipseHost = connector.getHost();
	if ((eclipseHost == null) || (eclipseHost.equalsIgnoreCase(host))) {
		String executionId = connector.getExecutionId();
		capabilities.setCapability(EclipseConnector.ECLIPSE_EXECUTION_ID, executionId);
	}
}
}
