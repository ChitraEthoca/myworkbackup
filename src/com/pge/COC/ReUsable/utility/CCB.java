package com.pge.COC.ReUsable.utility;

import org.testng.annotations.AfterMethod;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class CCB {
	public String userid;
	public String passwd;
	public String address;
	public String CHECK_DIGIT;
	public String ACCOUNT_NUMBER;
	public String SSN_TIN;
//	public String[] accDetails;
	By tabPage = By.xpath("//iframe[@name='tabPage']");
	By srch_Frm = By.xpath("//iframe[@name='srch_frame']");
	By cntxtMenu = By.xpath("//iframe[@id='context']");
	By lnkPendingStop = By.xpath("//a[@class='pseudoLink']");


	static WebDriver ie;
	public CCB(String userid, String passwd, String accNo) throws InterruptedException{
		this.userid= userid;
		this.passwd=passwd;
		this.ACCOUNT_NUMBER=accNo;
		init();
		loginCCB();
		setCheckDigit(ACCOUNT_NUMBER);
		setPremiseAddress(ACCOUNT_NUMBER);
		tearDown();
	}
	public void init(){
		File file = new File("Jars/IEDriverServer.exe");
		System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
		DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer();  
		ieCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
		ieCapabilities.setJavascriptEnabled(true);
		ie = new InternetExplorerDriver(ieCapabilities);
		ie.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		ie.navigate().to("http://ccbsysn:7835/spl/cis.jsp?debug=true");

	}
	public void loginCCB() throws InterruptedException{
		ie.findElement(By.xpath("//input[@id='userId']")).sendKeys(userid);
		ie.findElement(By.xpath("//input[@id='password']")).sendKeys(passwd);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {e.printStackTrace();}
		Thread.sleep(5000);
		ie.findElement(By.xpath("//input[@id='loginButton']")).click();
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {e.printStackTrace();}

	}
	public void setCheckDigit(String acctNo) throws InterruptedException{
		ie.switchTo().frame(0);
		ie.findElement(By.xpath("//img[@id='IM_CTRL_CENT']")).click();
		ie.switchTo().frame(ie.findElement(tabPage));
		ie.switchTo().frame(ie.findElement(srch_Frm));

		WebElement acct_id = ie.findElement(By.xpath("//input[@name='ACCT_ID']"));
		acct_id.sendKeys(acctNo);
		acct_id.sendKeys(Keys.ENTER);
		Thread.sleep(10000);

		try{
			ie.switchTo().parentFrame().switchTo().parentFrame().switchTo().parentFrame();
			ie.switchTo().frame(0);
			ie.switchTo().frame(ie.findElement(tabPage));
			ie.findElement(By.xpath("//td[@id='context.2.3']")).isDisplayed();
			System.out.println("present");
		}catch(Exception e){
			ie.switchTo().parentFrame().switchTo().parentFrame();
			ie.switchTo().frame(0);
			WebElement element = ie.findElement(tabPage);
			element.sendKeys(Keys.ENTER);
		}

		Thread.sleep(5000);
		ie.switchTo().parentFrame().switchTo().parentFrame();
		ie.switchTo().frame(0);
		ie.switchTo().frame(ie.findElement(tabPage));
		Thread.sleep(5000);
		String strfullAccNo  = ie.findElement(By.xpath("//td[@id='context.2.3']")).getText();
		Thread.sleep(5000);
		CHECK_DIGIT = strfullAccNo.charAt(11)+"";

		String ssn = null;
		for (int i = 10; i < 60; i++) {
			if (ie.findElements(By.xpath("//td[@class='gridTd']")).get(i).getText().equals(" Social Security Number")){
				ssn = ie.findElements(By.xpath("//td[@class='gridTd']")).get(i+1).getText();
				SSN_TIN = ssn.substring(8, 12);	
				break;
			}else{ 
				if(ie.findElements(By.xpath("//td[@class='gridTd']")).get(i).getText().equals(" Tax Identification Number")){
					ssn= ie.findElements(By.xpath("//td[@class='gridTd']")).get(i+1).getText();
					SSN_TIN = ssn.substring(8, 11);	
					break;

				}
			}
		}
		ie.switchTo().parentFrame().switchTo().parentFrame();
		//SSN_TIN = ssn.substring(8, 12);	
	}

	

	//Get premise address details
	public void setPremiseAddress(String accNo) throws InterruptedException{
		ie.switchTo().frame(0);
		ie.findElement(By.xpath("//img[@id='IM_CTRL_CENT']")).click();
		ie.switchTo().frame(ie.findElement(tabPage));
		ie.switchTo().frame(ie.findElement(srch_Frm));

		WebElement acct_id = ie.findElement(By.xpath("//input[@name='ACCT_ID']"));
		acct_id.sendKeys(accNo);
		acct_id.sendKeys(Keys.ENTER);
		Thread.sleep(10000);
		try{
			ie.switchTo().parentFrame().switchTo().parentFrame().switchTo().parentFrame();
			ie.switchTo().frame(0);
			ie.switchTo().frame(ie.findElement(tabPage));
			ie.findElement(By.xpath("//td[@id='context.2.3']")).isDisplayed();
			System.out.println("present");
		}catch(Exception e){
			ie.switchTo().parentFrame().switchTo().parentFrame();
			ie.switchTo().frame(0);
			WebElement element = ie.findElement(tabPage);
			element.sendKeys(Keys.ENTER);
		}

		Thread.sleep(10000);
		ie.switchTo().parentFrame().switchTo().parentFrame();
		Thread.sleep(5000);
		ie.switchTo().frame(0);
		ie.switchTo().frame(ie.findElement(tabPage));
		ie.findElement(By.xpath("//img[@title='Show Premise Context Menu'][@tabIndex='5']")).click();
		ie.switchTo().parentFrame().switchTo().parentFrame();
		ie.switchTo().frame(0);
		ie.switchTo().frame(ie.findElement(tabPage));
		ie.switchTo().frame(ie.findElement(cntxtMenu));		
		ie.findElement(By.xpath("//td[@id='contextMenuItem0x0']")).click();
		ie.switchTo().parentFrame().switchTo().parentFrame().switchTo().parentFrame();	
		ie.switchTo().frame(0);
		ie.switchTo().frame(ie.findElement(tabPage));
		String street = ie.findElement(By.xpath("//input[@id='ADDRESS1']")).getAttribute("value");
		System.out.println("Street:" + street);

		String city = ie.findElement(By.xpath("//input[@id='CITY']")).getAttribute("value");
		System.out.println("City:" + city);

		String state = ie.findElement(By.xpath("//input[@id='STATE']")).getAttribute("value");
		System.out.println("State:" + state);


		String zip = ie.findElement(By.xpath("//span[@id='STATE_POSTAL']")).getText();
		String finalZip = zip.substring(0, 5);
		System.out.println("Zip:" + finalZip);


		address = street + ", " + city +", " + state + ", " + finalZip;
		System.out.println(address);
		ie.switchTo().parentFrame().switchTo().parentFrame();

	}


	@AfterMethod
	public void tearDown(){
		//	ie.close();
		ie.quit();	
	}

}
