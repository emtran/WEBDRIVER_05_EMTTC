package selenium_api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_RadioButton_Checkbox_Alert {

	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
	}

	public void launchBrowser(String baseUrl) {
		driver.get(baseUrl);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	public void clickElementByJavascript(WebElement element) {
		JavascriptExecutor je = (JavascriptExecutor) driver;
		je.executeScript("arguments[0].click();", element);
	}

	public void uncheckedTheCheckbox(WebElement element) {
		if (element.isSelected()) {
			JavascriptExecutor je = (JavascriptExecutor) driver;
			je.executeScript("arguments[0].click();", element);
			Assert.assertTrue(!element.isDisplayed());
		}
	}

	public void checkTheRadiobutton(WebElement element) {
		if (!element.isSelected()) {
			JavascriptExecutor je = (JavascriptExecutor) driver;
			je.executeScript("arguments[0].click();", element);
			Assert.assertTrue(element.isDisplayed());
		}
	}

	@Test
	public void TC_01_JavascriptExecutorCode() {
		launchBrowser("http://live.guru99.com/");

		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		String actual = driver.getCurrentUrl();
		String expected = "http://live.guru99.com/index.php/customer/account/login/";
		Assert.assertEquals(actual, expected);

		WebElement createAnAccountButton = driver.findElement(By.xpath("//a[@title='Create an Account']"));
		clickElementByJavascript(createAnAccountButton);
		String actualJE = driver.getCurrentUrl();
		String expectedJE = "http://live.guru99.com/index.php/customer/account/create/";
		Assert.assertEquals(actualJE, expectedJE);
	}

	@Test
	public void TC_02_Checkbox() {
		launchBrowser("http://demos.telerik.com/kendo-ui/styling/checkboxes");

		// Dùng thẻ lable -> hàm isSelected ko work vs thẻ lable
		// WebElement dualZoneCheckbox =
		// driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']"));

		// Dùng thẻ input -> Element not visible nhưng hàm isSelected work vs thẻ input
		// vì vậy dùng thêm hàm javascript để click
		WebElement dualZoneCheckbox = driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input"));
		// dualZoneCheckbox.click();

		// Click vào thẻ input bằng javascript
		clickElementByJavascript(dualZoneCheckbox);

		Assert.assertTrue(dualZoneCheckbox.isSelected());
		uncheckedTheCheckbox(dualZoneCheckbox);

	}

	@Test
	public void TC_03_Radiobutton() {
		launchBrowser("http://demos.telerik.com/kendo-ui/styling/radios");

		WebElement petrolRadiobutton = driver.findElement(By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::input"));

		clickElementByJavascript(petrolRadiobutton);

		Assert.assertTrue(petrolRadiobutton.isSelected());
		checkTheRadiobutton(petrolRadiobutton);
	}

	@Test
	public void TC_04_AcceptAlert() {
		launchBrowser("http://daominhdam.890m.com/");

		driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();

		Alert alert = driver.switchTo().alert();
		alert.accept();

		WebElement resultText = driver.findElement(By.xpath("//p[@id='result']"));
		String actual = resultText.getText();
		String expected = "You clicked an alert successfully";
		Assert.assertEquals(actual, expected);
	}

	@Test
	public void TC_05_CancelAlert() {
		launchBrowser("http://daominhdam.890m.com/");

		driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();

		Alert alert = driver.switchTo().alert();
		// Cancel Alert
		alert.dismiss();

		WebElement resultText = driver.findElement(By.xpath("//p[@id='result']"));
		String actual = resultText.getText();
		String expected = "You clicked: Cancel";
		Assert.assertEquals(actual, expected);
	}

	@Test
	public void TC_06_PromptAlert() {
		launchBrowser("http://daominhdam.890m.com/");
		String name = "cuong em";

		driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();

		Alert alert = driver.switchTo().alert();
		alert.sendKeys(name);
		alert.accept();

		WebElement resultText = driver.findElement(By.xpath("//p[@id='result']"));
		String actual = resultText.getText();
		String expected = "You entered: " + name;
		Assert.assertEquals(actual, expected);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
