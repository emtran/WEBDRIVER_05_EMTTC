package selenium_api;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.google.common.base.Function;

public class Topic_09_WebDriverWait {
	WebDriver driver;
	WebDriverWait wait;
	
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		wait = new WebDriverWait(driver,30);
	}

	public void launchBrowser(String baseUrl) {
		driver.get(baseUrl);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	@Test
	public void TC_01_ImplicitWait() {
		launchBrowser("http://the-internet.herokuapp.com/dynamic_loading/2");
		driver.findElement(By.xpath("//div[@id='start']/button")).click();
		WebElement result = driver.findElement(By.xpath("//h4[text()='Hello World!']"));
		Assert.assertTrue(result.isDisplayed());	
	}
	
	@Test
	public void TC_02_ExplicitWait() {
		launchBrowser("http://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
		
		WebElement datePicker = driver.findElement(By.xpath("//div[@class='contentWrapper']"));
		wait.until(ExpectedConditions.visibilityOf(datePicker));
		
		WebElement selectedDate = driver.findElement(By.xpath("//span[@id='ctl00_ContentPlaceholder1_Label1']")) ;
		String beforeSelected = selectedDate.getText().trim();
		Assert.assertEquals(beforeSelected, "No Selected Dates to display.");
		
		driver.findElement(By.xpath("//a[text()='7']")).click();
		By ajaxIcon = By.xpath("//div[@class='raDiv']");
		wait.until(ExpectedConditions.invisibilityOfElementLocated(ajaxIcon));
	
	}
	
	@Test
	public void TC_03_FluentWait_01() {
		launchBrowser("https://stuntcoders.com/snippets/javascript-countdown/");
		
		WebElement countdown = driver.findElement(By.xpath("//div[@id='javascript_countdown_time']"));
		wait.until(ExpectedConditions.visibilityOf(countdown));
		
		new FluentWait<WebElement>(countdown)
			.withTimeout(15, TimeUnit.SECONDS)
			.pollingEvery(1, TimeUnit.SECONDS)
			.ignoring(NoSuchElementException.class)
			.until(new Function<WebElement, Boolean>(){
				public Boolean apply(WebElement element) {
					boolean flag = element.getText().endsWith("00");
					System.out.println("Time = " + element.getText());
					return flag;
				}
			});
	}
	
	@Test
	public void TC_03_FluentWait_02() {
		launchBrowser("http://toolsqa.wpengine.com/automation-practice-switch-windows/");
		
		WebElement countdown = driver.findElement(By.xpath("//span[@id='clock']"));
		wait.until(ExpectedConditions.visibilityOf(countdown));
		
		new FluentWait<WebElement>(countdown)
			.withTimeout(45, TimeUnit.SECONDS)
			.pollingEvery(1, TimeUnit.SECONDS)
			.ignoring(NoSuchElementException.class)
			.until(new Function<WebElement, Boolean>(){
				public Boolean apply(WebElement element) {
					WebElement redButton = driver.findElement(By.xpath("//button[@style='color: red;']"));
					boolean redValue = redButton.isDisplayed();
//					WebElement buzzText = driver.findElement(By.xpath("//span[@id='clock' and text()='Buzz  Buzz']"));
//					boolean buzzValue = buzzText.isDisplayed();
					return redValue;
				}
			});
		
		
	}

	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
