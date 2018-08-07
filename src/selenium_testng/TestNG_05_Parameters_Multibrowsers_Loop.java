package selenium_testng;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TestNG_05_Parameters_Multibrowsers_Loop {
	WebDriver driver;

	@Parameters ({"browser"})
	@BeforeClass
	public void beforeClass(String browser) {
		if(browser.equals("firefox")) {
			driver = new FirefoxDriver();
		}else if (browser.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", ".\\driver\\chromedriver.exe");
			driver = new ChromeDriver();
		}else {
			System.setProperty("webdriver.ie.driver", ".\\driver\\IEDriverServer.exe");
			driver = new InternetExplorerDriver();
		}
		driver.get("http://live.guru99.com/index.php");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	@Parameters ({"user","password"})
	@Test(invocationCount = 3)
	public void Login(String email, String password) {
		
		WebElement myAccountLink = driver.findElement(By.xpath("//div[@class='footer']//a[@title=\"My Account\"]"));
		myAccountLink.click();
		
		WebElement emailTextbox = driver.findElement(By.xpath("//input[@id='email']"));
		emailTextbox.sendKeys(email);

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
