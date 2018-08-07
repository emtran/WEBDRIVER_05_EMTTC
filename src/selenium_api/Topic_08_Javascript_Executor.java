package selenium_api;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_Javascript_Executor {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		//driver = new FirefoxDriver();
		
		 System.setProperty("webdriver.chrome.driver", ".\\driver\\chromedriver.exe");
		 driver = new ChromeDriver();
		
//		System.setProperty("webdriver.ie.driver", ".\\driver\\IEDriverServer.exe");
//		driver = new InternetExplorerDriver();
	}

	public void launchBrowser(String baseUrl) {
		driver.get(baseUrl);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void JavascriptExcecutor() throws Exception {
		launchBrowser("http://live.guru99.com/");
		
		String domain = (String) executeForBrowserElement("return document.domain;");
		Assert.assertEquals(domain, "live.guru99.com");

		String url = (String) executeForBrowserElement("return document.URL;");
		Assert.assertEquals(url, "http://live.guru99.com/");
		
		WebElement mobilePage = driver.findElement(By.xpath("//a[text()='Mobile']"));
		clickByWebElement(mobilePage);
		
		Thread.sleep(5000);
		
		WebElement addToCart = driver.findElement(By.xpath("//h2[a[text()='Samsung Galaxy']]/following-sibling::div[@class='actions']/button"));
		//WebElement addToCart = driver.findElement(By.xpath("//h2[@class='product-name']/a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']/button"));
		clickByWebElement(addToCart);
		
		Thread.sleep(5000);
		
		String addToCartMsg = (String) executeForBrowserElement("return document.documentElement.innerText;");
		Assert.assertTrue(addToCartMsg.contains("Samsung Galaxy was added to your shopping cart."));
		
		WebElement privacyPage = driver.findElement(By.xpath("//a[text()='Privacy Policy']"));
		clickByWebElement(privacyPage);
		String titlePrivacyPage = (String) executeForBrowserElement("return document.title;");
		Assert.assertEquals(titlePrivacyPage, "Privacy Policy");
		
		scrollToBottomPage();
		
		WebElement wishList = driver.findElement(By.xpath("//th[text()='WISHLIST_CNT']/following-sibling::td"));
		Assert.assertTrue(wishList.isDisplayed());
		
		executeForBrowserElement("window.location ='http://demo.guru99.com/v4/'");
		String domainGuruDemo = (String) executeForBrowserElement("return document.domain;");
		Assert.assertEquals(domainGuruDemo,"demo.guru99.com");
		
	}
	
	//@Test
	public void RemoveAttribute() {
		launchBrowser("https://www.w3schools.com/tags/tryit.asp?filename=tryhtml_input_disabled");
		WebElement iframeResult = driver.findElement(By.xpath("//iframe[@id='iframeResult']"));
		driver.switchTo().frame(iframeResult);
		
		WebElement lastName = driver.findElement(By.xpath("//input[@name='lname']"));
		removeAttributeInDOM(lastName, "disabled");
		lastName.sendKeys("Cuong Em");
		driver.findElement(By.xpath("//input[@value='Submit']")).click();
		
		WebElement result = driver.findElement(By.xpath("//div[contains(text(),'Cuong Em')]"));
		Assert.assertTrue(result.isDisplayed());
	}
	
	public Object executeForBrowserElement(String javaSript) {
        try {
                    JavascriptExecutor js = (JavascriptExecutor) driver;
                    return js.executeScript(javaSript);
        } catch (Exception e) {
                    e.getMessage();
                    return null;
        }
}
	
	public Object clickByWebElement(WebElement element) {
        try {
                    JavascriptExecutor js = (JavascriptExecutor) driver;
                    return js.executeScript("arguments[0].click();", element);
        } catch (Exception e) {
                    e.getMessage();
                    return null;
        }
}
	
	public void highlightElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].style.border='6px groove red'", element);
}
	
	public Object removeAttributeInDOM(WebElement element, String attribute) {
        try {
                    JavascriptExecutor js = (JavascriptExecutor) driver;
                    return js.executeScript("arguments[0].removeAttribute('" + attribute + "');", element);
        } catch (Exception e) {
                    e.getMessage();
                    return null;
        }
}
	
	public Object scrollToBottomPage() {
        try {
                    JavascriptExecutor js = (JavascriptExecutor) driver;
                    return js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
        } catch (Exception e) {
                    e.getMessage();
                    return null;
        }
}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
