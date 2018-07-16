package selenium_api;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_03_WebElement_WebBrowser {
	
	WebDriver driver;
	String baseUrl = "http://daominhdam.890m.com/";
	

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		
		//System.setProperty("webdriver.chrome.driver", "..\\WEBDRIVER_05_EMTTC\\driver\\chromedriver.exe");
		//driver = new ChromeDriver();
		
		//System.setProperty("webdriver.ie.driver", "..\\WEBDRIVER_05_EMTTC\\driver\\IEDriverServer.exe");
		//driver = new InternetExplorerDriver();
		
	}

	@Test
	public void TC01_ElementIsDisplayedOnPage() {
		driver.get(baseUrl);
		
		WebElement emailTextbox = driver.findElement(By.xpath("//input[@id='mail']"));
		Boolean checkEmailIsDisplayed = emailTextbox.isDisplayed();
		if(checkEmailIsDisplayed == true){
			emailTextbox.sendKeys("Automation Testing CE");
		}else System.out.println("Email Textbox is not displayed");
		
		
		WebElement ageCheckbox = driver.findElement(By.xpath("//input[@id='under_18']"));
		Boolean checkAgeUder18IsDisplayed = ageCheckbox.isDisplayed();
		if(checkAgeUder18IsDisplayed == true){
			ageCheckbox.click();
		}else System.out.println("Age Checkbox is not displayed");
		
		
		WebElement eduTextarea = driver.findElement(By.xpath("//textarea[@id='edu']"));
		Boolean checkEduIsDisplayed = eduTextarea.isDisplayed();
		if(checkEduIsDisplayed == true){
			eduTextarea.sendKeys("Automation Testing CE");
		}else System.out.println("Edu Textarea is not displayed");
	}
	
	
	public void EmlementIsEnable(String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		Boolean checkElement = element.isEnabled();
		if(checkElement == true){
			System.out.println("Element " + "{" + locator + "}" + " is enabled");
		}else System.out.println("Element " + "{" + locator + "}" + " is disabled");
		
	}
	
	@Test
	public void TC02_ElementIsEnableDisableOnPage() {
		driver.get(baseUrl);
		
		/*WebElement emailTextbox = driver.findElement(By.xpath("//input[@id='mail']"));
		Boolean checkEmailIsEnabled = emailTextbox.isEnabled();
		if(checkEmailIsEnabled == true){
			System.out.println("Email Textbox is enabled");
		}else System.out.println("Email Textbox is disable");
		
		WebElement pwTextbox = driver.findElement(By.xpath("//input[@id='password']"));
		Boolean checkpwIsEnabled = pwTextbox.isEnabled();
		if(checkpwIsEnabled == true){
			System.out.println("Pw Textbox is enabled");
		}else System.out.println("Pw Textbox is disable");*/
		
		EmlementIsEnable("//input[@id='mail']");
		
		EmlementIsEnable("//input[@id='password']");
		
		EmlementIsEnable("//input[@id='under_18']");
		
		EmlementIsEnable("//input[@id='radio-disabled']");
		
		EmlementIsEnable("//textarea[@id='edu']");
		
		EmlementIsEnable("//textarea[@id='bio']");
		
		EmlementIsEnable("//select[@id='job1']");
		
		EmlementIsEnable("//select[@id='job2']");
		
		EmlementIsEnable("//input[@id='development']");
		
		EmlementIsEnable("//input[@id='check-disbaled']");
		
		EmlementIsEnable("//input[@id='slider-1']");
		
		EmlementIsEnable("//input[@id='slider-2']");
		
		EmlementIsEnable("//button[@id='button-disabled']");
		
		EmlementIsEnable("//button[@id='button-enabled']");
	
	}
	
	@Test
	public void TC03_ElementIsSelectedOnPage() {
		driver.get(baseUrl);
		
		WebElement ageRadioButton = driver.findElement(By.xpath("//input[@id='under_18']"));
		ageRadioButton.click();
		Boolean checkAgeUder18IsSelected = ageRadioButton.isSelected();
		if(checkAgeUder18IsSelected == false){
			ageRadioButton.click();
		}
		
		WebElement interestsCheckbox = driver.findElement(By.xpath("//input[@id='development']"));
		interestsCheckbox.click();
		Boolean checkDevelopmentIsSelected = interestsCheckbox.isSelected();
		if(checkDevelopmentIsSelected == false){
			interestsCheckbox.click();
		}
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
