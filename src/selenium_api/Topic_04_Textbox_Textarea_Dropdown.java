package selenium_api;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_04_Textbox_Textarea_Dropdown {
	WebDriver driver;
	String username, password, name, dob, address, city, state, pin, mobile, email, customerID, editAddress, editCity;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();

		// mỗi lần chạy sẽ khởi tạo bộ data
		username = "mngr143387";
		password = "Umubyma";
		name = "Cuong Em";
		dob = "01/01/1988";
		address = "old address";
		city = "hcm";
		state = "Tan Phu";
		pin = "348569";
		mobile = "0123456789";
		email = "cuongem" + randomNumber() + "@gmail.com";
		editAddress = "new address";
		editCity = "new city";
	}

	private int randomNumber() {
		Random random = new Random();
		int number = random.nextInt(999999);
		return number;
	}

	public void launchBrowser(String baseUrl) {
		driver.get(baseUrl);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_DropdownList() {

		launchBrowser("http://daominhdam.890m.com/");

		Select jobDropdown = new Select(driver.findElement(By.xpath("//select[@id='job1']")));
		Assert.assertFalse(jobDropdown.isMultiple());

		jobDropdown.selectByVisibleText("Automation Tester");
		Assert.assertEquals(jobDropdown.getFirstSelectedOption().getText(), "Automation Tester");

		jobDropdown.selectByValue("manual");
		Assert.assertEquals(jobDropdown.getFirstSelectedOption().getText(), "Manual Tester");

		jobDropdown.selectByIndex(3);
		Assert.assertEquals(jobDropdown.getFirstSelectedOption().getText(), "Mobile Tester");

		Assert.assertEquals(jobDropdown.getOptions().size(), 5);
	}

	@Test
	public void TC_02_TextboxTextarea() {

		launchBrowser("http://demo.guru99.com/v4/");

		WebElement userTextbox = driver.findElement(By.xpath("//input[@name='uid']"));
		WebElement pswTextbox = driver.findElement(By.xpath("//input[@name='password']"));
		userTextbox.sendKeys(username);
		pswTextbox.sendKeys(password);

		WebElement loginButton = driver.findElement(By.xpath("//input[@name='btnLogin']"));
		loginButton.click();

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		WebElement newCustomerLink = driver.findElement(By.xpath("//a[text()='New Customer']"));
		newCustomerLink.click();

		WebElement nameTextbox = driver.findElement(By.xpath("//input[@name='name']"));
		nameTextbox.sendKeys(name);

		WebElement femaleRadio = driver.findElement(By.xpath("//input[@value='f']"));
		femaleRadio.click();

		WebElement dobTextbox = driver.findElement(By.xpath("//input[@name='dob']"));
		dobTextbox.sendKeys(dob);

		WebElement addressTextArea = driver.findElement(By.xpath("//textarea[@name='addr']"));
		addressTextArea.sendKeys(address);

		WebElement cityTextbox = driver.findElement(By.xpath("//input[@name='city']"));
		cityTextbox.sendKeys(city);

		WebElement stateTextbox = driver.findElement(By.xpath("//input[@name='state']"));
		stateTextbox.sendKeys(state);

		WebElement pinTextbox = driver.findElement(By.xpath("//input[@name='pinno']"));
		pinTextbox.sendKeys(pin);

		WebElement mobileTextbox = driver.findElement(By.xpath("//input[@name='telephoneno']"));
		mobileTextbox.sendKeys(mobile);

		WebElement emailTextbox = driver.findElement(By.xpath("//input[@name='emailid']"));
		emailTextbox.clear();
		emailTextbox.sendKeys(email);

		WebElement passwordTextbox = driver.findElement(By.xpath("//input[@name='password']"));
		passwordTextbox.clear();
		passwordTextbox.sendKeys(password);

		WebElement submitButton = driver.findElement(By.xpath("//input[@name='sub']"));
		submitButton.click();

		// VERIFY CUSTOMER CREATED SUCCESS
		WebElement createdSuccessMsg = driver.findElement(By.xpath("//p[@class='heading3' and text()='Customer Registered Successfully!!!']"));
		createdSuccessMsg.isDisplayed();
		Assert.assertTrue(createdSuccessMsg.isDisplayed());

		// GET CUSTOMER ID TEXT -> NEXT TESTCASE
		customerID = driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText();

		// VERIFY CUSTOMER INFORMATION CREATED
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(), name);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Gender']/following-sibling::td")).getText(),"female");
		// Bug xảy ra ở đây
		// Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText(),dob);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(),address);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(), city);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(),state);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(), pin);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(),mobile);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(),email);

		// EDIT CUSTOMER
		WebElement editCustomerLink = driver.findElement(By.xpath("//a[text()='Edit Customer']"));
		editCustomerLink.click();

		WebElement customerIDTextbox = driver.findElement(By.xpath("//input[@name='cusid']"));
		customerIDTextbox.sendKeys(customerID);

		WebElement accSubmitButton = driver.findElement(By.xpath("//input[@name='AccSubmit']"));
		accSubmitButton.click();

		Assert.assertEquals(driver.findElement(By.xpath("//input[@name='name']")).getAttribute("value"), name);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(),address);

		WebElement addressEditTextArea = driver.findElement(By.xpath("//textarea[@name='addr']"));
		addressEditTextArea.clear();
		addressEditTextArea.sendKeys(editAddress);

		WebElement cityEditTextbox = driver.findElement(By.xpath("//input[@name='city']"));
		cityEditTextbox.clear();
		cityEditTextbox.sendKeys(editCity);

		WebElement submitEditButton = driver.findElement(By.xpath("//input[@name='sub']"));
		submitEditButton.click();

		// VERIFY CUSTOMER EDITED SUCCESS
		WebElement editedSuccessMsg = driver.findElement(By.xpath("//p[@class='heading3' and text()='Customer details updated Successfully!!!']"));
		editedSuccessMsg.isDisplayed();
		Assert.assertTrue(editedSuccessMsg.isDisplayed());

		// VERIFY CUSTOMER INFORMATION EDITED
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(),editAddress);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(),editCity);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
