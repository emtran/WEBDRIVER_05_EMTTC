package selenium_api;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_10_UploadFile {
	WebDriver driver;
	WebDriverWait wait;

	// Get auto project directory
	String workingDirectory = System.getProperty("user.dir");
	String fileName = "uploadFile.png";
	String filePath = workingDirectory + "\\images\\uploadFile.png";
	
	String folderName = "Cuong Em " + randomNumber();
	String email = "cuongem@gmail.com";
	String firstName = "ninja";

	@BeforeClass
	public void beforeClass() {
		// driver = new FirefoxDriver();

		System.setProperty("webdriver.chrome.driver", ".\\driver\\chromedriver.exe");
		driver = new ChromeDriver();

		// System.setProperty("webdriver.ie.driver", ".\\driver\\IEDriverServer.exe");
		// driver = new InternetExplorerDriver();

		// System.out.println(workingDirectory);
		// System.out.println(filePath);
		
		wait = new WebDriverWait(driver,30);

	}

	public void launchBrowser(String baseUrl) {
		driver.get(baseUrl);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Sendkeys_API() {
		launchBrowser("http://blueimp.github.com/jQuery-File-Upload/");
		WebElement addFiles = driver.findElement(By.xpath("//input[@name='files[]']"));
		addFiles.sendKeys(filePath);
		WebElement loadedFiles = driver.findElement(By.xpath("//p[@class='name' and text()='" + fileName + "']"));
		Assert.assertTrue(loadedFiles.isDisplayed());
	}

	@Test
	public void TC_02_AutoIT() throws Exception {
		launchBrowser("http://blueimp.github.com/jQuery-File-Upload/");
		WebElement addFiles = driver.findElement(By.cssSelector(".fileinput-button"));
		addFiles.click();
		Runtime.getRuntime().exec(new String[] { ".\\autoIT\\chrome.exe", filePath });
		WebElement loadedFiles = driver.findElement(By.xpath("//p[@class='name' and text()='" + fileName + "']"));
		Assert.assertTrue(loadedFiles.isDisplayed());
	}
	
	@Test
	public void TC_03_Robot() throws Exception {
		launchBrowser("http://blueimp.github.com/jQuery-File-Upload/");
		
		//Specify the file location with extension
		StringSelection select = new StringSelection(filePath);

		//Copy to clipboard
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(select, null);

		//Click
		WebElement addFiles = driver.findElement(By.cssSelector(".fileinput-button"));
		addFiles.click();

		Robot robot = new Robot();
		Thread.sleep(1000);

		// Nhan phim Enter
		robot.keyPress(KeyEvent.VK_ENTER);
		// Nha phim Enter
		robot.keyRelease(KeyEvent.VK_ENTER);

		// Nhan phim Ctrl V
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);

		// Nha phim Ctrl V
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_V);
		Thread.sleep(1000);

		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		
		WebElement loadedFiles = driver.findElement(By.xpath("//p[@class='name' and text()='" + fileName + "']"));
		Assert.assertTrue(loadedFiles.isDisplayed());
	}
	
	@Test
	public void TC_04_UploadFile() {
		launchBrowser("https://encodable.com/uploaddemo/");
		
		WebElement addFiles = driver.findElement(By.xpath("//input[@id='uploadname1']"));
		addFiles.sendKeys(filePath);
		
		Select uploadDropdown = new Select(driver.findElement(By.xpath("//select[@name='subdir1']")));
		uploadDropdown.selectByVisibleText("/uploaddemo/files/");
		
		WebElement addName = driver.findElement(By.xpath("//input[@id='newsubdir1']"));
		addName.sendKeys(folderName);
		
		WebElement addEmail = driver.findElement(By.xpath("//input[@id='formfield-email_address']"));
		addEmail.sendKeys(email);
		
		WebElement addFirstName = driver.findElement(By.xpath("//input[@id='formfield-first_name']"));
		addFirstName.sendKeys(firstName);
		
		driver.findElement(By.xpath("//input[@id='uploadbutton']")).click();
		
		WebElement addSuccess = driver.findElement(By.xpath("//input[@id='formfield-first_name']"));
		wait.until(ExpectedConditions.visibilityOf(addSuccess));
		
		Assert.assertTrue(driver.findElement(By.xpath("//dd[text()='Email Address: "+email+"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//dd[text()='First Name: "+firstName+"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='"+fileName+"']")).isDisplayed());
		 
		driver.findElement(By.xpath("//a[text()='View Uploaded Files']")).click();
		driver.findElement(By.xpath("//a[text()='"+folderName+"']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='"+fileName+"']")).isDisplayed());
		
	}
	
	private int randomNumber() {
		Random random = new Random();
		int number = random.nextInt(999999);
		return number;
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
