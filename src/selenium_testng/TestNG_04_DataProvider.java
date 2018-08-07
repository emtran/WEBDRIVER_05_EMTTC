package selenium_testng;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestNG_04_DataProvider {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.get("http://live.guru99.com/index.php");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	@Test(dataProvider = "UserandPassword")
	public void LoginWithMultiUser(String user, String password) {
		
		WebElement myAccountLink = driver.findElement(By.xpath("//div[@class='footer']//a[@title=\"My Account\"]"));
		myAccountLink.click();
		
		WebElement emailTextbox = driver.findElement(By.xpath("//input[@id='email']"));
		emailTextbox.sendKeys(user);

		WebElement passwordTextbox = driver.findElement(By.xpath("//input[@id='pass']"));
		passwordTextbox.sendKeys(password);

		WebElement loginButton = driver.findElement(By.xpath("//button[@id='send2']"));
		loginButton.click();

		Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='My Dashboard']")).isDisplayed());

		WebElement acountLink = driver.findElement(By.xpath("//header[@id='header']//span[text()='Account']"));
		acountLink.click();

		WebElement logoutButton = driver.findElement(By.xpath("//a[@title='Log Out']"));
		logoutButton.click();
		
		
	}

	@DataProvider(name = "UserandPassword")
	public Object[][] getUserPass() {
		return new Object[][] { { "automationvalid_01@gmail.com", "111111" },
				{ "automationvalid_02@gmail.com", "111111" }, { "automationvalid_03@gmail.com", "111111" } };
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
