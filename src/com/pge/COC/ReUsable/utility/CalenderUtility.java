package com.pge.COC.ReUsable.utility;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CalenderUtility {

	public static Date GetCurrentDate(){
		Date date = new Date(); // wherever you get this from

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		date = cal.getTime();
		System.out.println("Today's Date : " + date);
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		System.out.println("Today's date in another format : " + dateFormat.format(date));
		cal.add(Calendar.DATE, 10); // add 10 days

		date = cal.getTime();

		System.out.println("Date after adding 10 days: " + dateFormat.format(date));
		return date;

	}

//	public static void clickOn28thOfMonth(WebDriver driver, String lnkDate) throws InterruptedException{
//		//		Thread.sleep(15000);
//		//		WebDriverWait wait = new WebDriverWait(driver, 180);
//
//		//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(lnkDate))); 
//		if(new PageHandling().checkElementExistence(driver, By.xpath(lnkDate), 3000)){
//			driver.findElement(By.xpath(lnkDate)).click();
//			String date =driver.findElement(By.xpath(lnkDate)).getText();
//		}
//	}
	//need a method that will click on the first available link
	public static String clickOnAnyDate(WebDriver driver) throws InterruptedException{
		By calendarDates = By.xpath(
				"//a[text()='1']"
						+ "|//a[text()='2']"
						+ "|//a[text()='3']"
						+ "|//a[text()='4']"
						+ "|//a[text()='5']"
						+ "|//a[text()='6']"
						+ "|//a[text()='7']"
						+ "|//a[text()='20']"
						+ "|//a[text()='21']"
						+ "|//a[text()='22']"
						+ "|//a[text()='23']"
						+ "|//a[text()='25']"
						+ "|//a[text()='24']"
						+ "|//a[text()='29']"
						+ "|//a[text()='30']"
						+ "|//a[text()='31']"
				);
		List <WebElement> calendarElements = driver.findElements(calendarDates);
		driver.manage().timeouts().implicitlyWait(60,TimeUnit.SECONDS);
		calendarElements = driver.findElements(calendarDates);
		String dateClicked = calendarElements.get(0).getText();
		calendarElements.get(0).click();
		return dateClicked;
	}
	public void goToNextMonth(WebDriver driver) throws InterruptedException{
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
//		Thread.sleep(5000);
		driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/a[2]/span")).click();
	}
	public void goToPrevMonth(WebDriver driver) throws InterruptedException{
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
//		Thread.sleep(5000);
		driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/a[1]/span")).click();
	}
	public boolean validateDate(WebDriver driver,int date){
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		try{
			driver.findElement(By.xpath("//a[text()='"+date+"']"));
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			return true;
			
		} catch(NoSuchElementException e){
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			return false;
		}
	}

}

