package selenium_testng;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;

public class TestNG_03_Priority_Skip {

	@Test(priority = 1)
	public void TC_01() {
		System.out.println("Testcase 01");
	}

	@Test()
	public void TC_02() {
		System.out.println("Testcase 02");
	}

	@Test(priority = 2)
	 public void TC_03() {
		 System.out.println("Testcase 03");
	}

	@Test(priority = 3, enabled = false)
	public void TC_04() {
		System.out.println("Testcase 04");
	}

	@BeforeClass
	public void beforeClass() {
	}

	@AfterClass
	public void afterClass() {
	}

}
