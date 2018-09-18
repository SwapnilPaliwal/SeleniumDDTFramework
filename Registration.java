package com.facebookRegistration;

import org.testng.annotations.Test;
import com.facebookRegistrationLibrary.Library;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;

public class Registration {

	WebDriver driver;

	@Test(dataProvider = "data")
	public void register(String firstName, String lastName, String emailOrNumber, String reEnter, String password,
			String day, String month, String year, String gender) throws Exception {

		if (firstName != null && lastName != null && emailOrNumber != null && reEnter != null && password != null
				&& day != null && month != null && year != null && gender != null) {
			System.setProperty("webdriver.chrome.driver",
					"D:\\Seed Infotech\\Software Testing\\Automation\\chromedriver_win32\\2.35\\chromedriver.exe");
			driver = new ChromeDriver();
			driver.get("https://www.facebook.com/r.php");
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			// driver.manage().window().maximize();
			driver.findElement(By.id("u_0_k")).sendKeys(firstName);
			driver.findElement(By.id("u_0_m")).sendKeys(lastName);
			driver.findElement(By.id("u_0_p")).sendKeys(emailOrNumber);
			driver.findElement(By.xpath("//input[@id='u_0_s']")).sendKeys(reEnter);
			driver.findElement(By.id("u_0_w")).sendKeys(password);

			// String result = year.replaceAll("[-+.^:,]", "");
			/* String newValue = Double.toString(Math.floor(year)); */
			Select listDay = new Select(driver.findElement(By.xpath("//select[@id='day']")));
			String numWihoutDecimal1 = String.valueOf(day).split("\\.")[0];
			listDay.selectByVisibleText(numWihoutDecimal1);

			Select listMonth = new Select(driver.findElement(By.xpath("//select[@id='month']")));
			listMonth.selectByVisibleText(month);

			Select listYear = new Select(driver.findElement(By.xpath("//select[@id='year']")));
			String numWihoutDecimal2 = String.valueOf(year).split("\\.")[0];
			listYear.selectByVisibleText(numWihoutDecimal2);
			Thread.sleep(3000);	
/*
			//	To Check Status Of Radio Female Button :
				Boolean displayedStatus = driver.findElement(By.xpath("//input[@id='u_0_6']")).isDisplayed();
				System.out.println("Displayed or not : "+displayedStatus);
				Boolean EnabledStatus = driver.findElement(By.xpath("//input[@id='u_0_6']")).isEnabled();
				System.out.println("Enabled or not : "+EnabledStatus);
				Boolean selectedStatus = driver.findElement(By.xpath("//input[@id='u_0_6']")).isSelected();
				System.out.println("Selected or not : "+selectedStatus);
*/		
/*
			//	To Check Status Of Radio Male Button :
				Boolean displayedStatus = driver.findElement(By.xpath("//input[@id='u_0_7']")).isDisplayed();
				System.out.println("Displayed or not : "+displayedStatus);
				Boolean EnabledStatus = driver.findElement(By.xpath("//input[@id='u_0_7']")).isEnabled();
				System.out.println("Enabled or not : "+EnabledStatus);
				Boolean selectedStatus = driver.findElement(By.xpath("//input[@id='u_0_7']")).isSelected();
				System.out.println("Selected or not : "+selectedStatus);
*/			
			// Click on female button if it is displayed, Enabled and not selected
/*			WebElement genFemale = driver.findElement(By.xpath("//input[@id='u_0_6']"));
			if (genFemale.isDisplayed() && genFemale.isEnabled() && !genFemale.isSelected()) {
				genFemale.click();
				System.out.println("Clicked1");
			} else {
				genFemale.click();
				System.out.println("Clicked2");
			}

			// Click on male button if it is displayed, Enabled and not selected
			WebElement genMale = driver.findElement(By.xpath("//input[@id='u_0_7']"));
			if (genMale.isDisplayed() && genMale.isEnabled() && !genMale.isSelected()) {
				genMale.click();
				System.out.println("Clicked1");
			} else {
				genMale.click();
				System.out.println("Clicked2");
			}
			Thread.sleep(5000);
			
	*/				
			WebElement genFemale = driver.findElement(By.xpath("//input[@id='u_0_6']"));
			WebElement genMale = driver.findElement(By.xpath("//input[@id='u_0_7']"));
			if (gender.contains("Male")) {
				if (genMale.isDisplayed() && genMale.isEnabled() && !genMale.isSelected()) {
					genMale.click();
					System.out.println("Male Clicked");
				} else {
					System.out.println("Please check your condition 1");
				}
			} else if (gender.contains("Female")) {
				if (genFemale.isDisplayed() && genFemale.isEnabled() && !genFemale.isSelected()) {
					genFemale.click();
					System.out.println("Female Clicked");
				} else {
					System.out.println("Please check your condition 2");
				}
			}  /*
				 * else if (gender.contains("")) {
				 * System.out.println("Value is NULL, Please provide some value"); }
				*/
			// It can run if any unmatched value of gender founds including integer values and null values etc.
			else {
				System.out.println("Please check data provider string value for Gender");
			}			
		
/*		//	Click to Submit
		//	driver.findElement(By.xpath("//button[@id='u_0_12']")).click();
*/			
			
		} else {
			System.out.println("Null value found");
		}
	}

	@AfterMethod
	public void afterMethod() {
		if (driver != null) {
			driver.quit();
		} else {
			System.out.println("Driver value is null");
		}
	}

	@DataProvider(name = "data")
	public Object[][] passData() {
		Library readData = new Library(
				"D:\\Seed Infotech\\MyWorkSelenium\\MySeleniumWorkspace\\FrameworkInSeleniumFinal\\TestData\\RegstrationCredentials.xlsx");
		int rows = readData.getRowCount("FacebookCredentials");
		int cols = readData.getColumnCount("FacebookCredentials");

		Object[][] data = new Object[rows][cols];

		// Dynamic row and manual column
		/*	  for(int rowNum=1; rowNum< rows; rowNum++) {
			  data[rowNum][0] = readData.getCellData("FacebookCredentials", 0, rowNum);
			  data[rowNum][1] = readData.getCellData("FacebookCredentials", 1, rowNum);
			  data[rowNum][2] = readData.getCellData("FacebookCredentials", 2, rowNum);
			  data[rowNum][3] = readData.getCellData("FacebookCredentials", 3, rowNum);
			  data[rowNum][4] = readData.getCellData("FacebookCredentials", 4, rowNum);
			  data[rowNum][5] = readData.getCellData("FacebookCredentials", 5, rowNum);
			  data[rowNum][6] = readData.getCellData("FacebookCredentials", 6, rowNum);
			  data[rowNum][7] = readData.getCellData("FacebookCredentials", 7, rowNum);
		}*/
		
		
		// Dynamic row and dynamic column
		for (int rowNum = 7; rowNum < rows; rowNum++) {
			for (int colNum = 0; colNum < cols; colNum++) {
				data[rowNum][colNum] = readData.getCellData("FacebookCredentials", colNum, rowNum);
				System.out.println("Data is ::::: " + data[rowNum][colNum]);
			}
		}
		return data;
	}
}
