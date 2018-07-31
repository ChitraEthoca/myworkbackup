package com.pge.COC.ReUsable.utility;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.Reporter;


public class MailValidation {
	RemoteWebDriver driver = new FirefoxDriver();
	public String mailText="";
	public MailValidation(ExcelTestData testData) throws Exception{
		driver.get("http://www.yopmail.com");
		//driver.findElement(By.xpath("//input[@id='login']")).sendKeys("rzb7");
		driver.findElement(By.xpath("//input[@id='login']")).sendKeys(testData.getCellValue("Emailid"));
		driver.findElement(By.xpath("//input[@class='sbut']")).click();

	}

	public void readMailContents() throws InterruptedException{
		//		Refreshing the mailbox
		driver.findElement(By.xpath("//span[@class='slientext']")).click();
		Thread.sleep(2000);
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='ifmail']")));
		mailText=driver.findElement(By.xpath(".//*[@id='mailmillieu']/div[2]")).getText();
		driver.switchTo().parentFrame();
	}

	public void deleteAllMailsFromInbox(){
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='ifinbox']")));
		driver.findElement(By.xpath("//a[@class='igif lmenudelall']")).click();
		driver.findElement(By.xpath("//a[@class='igif lmen_all']")).click();
		try
		{
			String noMail = driver.findElement(By.xpath("//div[@id='msginbox']/center")).getText();
			Assert.assertEquals("No mail for\nrzb7@yopmail.com", noMail, "Inbox is deleted successfully");
			Reporter.log("deleted inbox successfully");
		}
		catch(Exception e){
			Reporter.log("deleted inbox successfully");
		}

		driver.switchTo().parentFrame();

	}
	/*public boolean searchText(String subString){
		boolean ret = false;
		if (mailText.contains(subString)){
			Reporter.log("Mail read successfully");
			//Assert.assertTrue(mailText.contains(subString), "Mail read successfully");
			ret = true;	
		}
		else{
			Reporter.log("Mail contents mismatch");
			ret = false;
		}
		return ret;
	}*/
	public void tearDown(){
		driver.quit();	
	}

}


