package lambdatest;

//import java.lang.reflect.Method;
//import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
//import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import org.testng.asserts.SoftAssert;

import org.openqa.selenium.*;

public class LambdaTest {

   // public static RemoteWebDriver driver;
    public RemoteWebDriver driver = null;
	  

  @Test(priority=0)
  public void testScenarioOne() throws Exception {
		  
			    WebDriverWait wait = new WebDriverWait(driver, 20);
				((WebDriver) driver).get("https://www.lambdatest.com/selenium-playground");
				driver.manage().window().maximize();
				SoftAssert softassert = new SoftAssert();
				String expectedTitle = "LambdaTest";
				String originalTitle = driver.getTitle();
				softassert.assertEquals(originalTitle, expectedTitle);
  }
  @Test(priority=1)
  public void  testScenarioTwo() throws InterruptedException
  {
	  ((WebDriver) driver).get("https://www.lambdatest.com/selenium-playground");
		driver.manage().window().maximize();
		driver.findElement(By.linkText("Checkbox Demo")).click();
		WebElement checkbox= driver.findElement(By.id("isAgeSelected"));
	    checkbox.click();
	    System.out.println(checkbox.isSelected());
		checkbox.click();
		System.out.println(checkbox.isSelected());		
}
  @Test(priority=2)
  public void  testScenarioThree() throws InterruptedException
  {
	    ((WebDriver) driver).get("https://www.lambdatest.com/selenium-playground");
	    driver.manage().window().maximize();
		driver.findElement(By.linkText("Javascript Alerts")).click();
        driver.findElement(By.xpath("//*[@id=\"__next\"]/section[4]/div/div/div[2]/div[1]/button")).click();
        String text = driver.switchTo().alert().getText();
		String exp = "I am an alert box!";
		Assert.assertEquals(exp, text);
        driver.switchTo().alert().accept();
  }	
  @BeforeMethod
  @Parameters({ "browser", "version", "platform" })
  public void setUpClass(String browser, String version, String platform) throws Exception {

  	    String username = "sakshinarkhede9235";
		String accesskey = "Btd44rb6gzUJADc4HK5HbnkND54UNThkte6CZWkB4v7zpxv4BK";

  		DesiredCapabilities capability = new DesiredCapabilities();    	
        
  		capability.setCapability(CapabilityType.BROWSER_NAME, browser);
  		capability.setCapability(CapabilityType.VERSION,version);
  		capability.setCapability(CapabilityType.PLATFORM, platform);		
  		
          capability.setCapability("build", "New TestNG");
  		capability.setCapability("network", true);
  		capability.setCapability("video", true);
  		capability.setCapability("console", true);
  		capability.setCapability("visual", true);

  		String gridURL = "https://" + username + ":" + accesskey + "@hub.lambdatest.com/wd/hub";
  		System.out.println(gridURL);
  		driver = new RemoteWebDriver(new URL(gridURL), capability);
  		System.out.println(capability);
  		System.out.println(driver.getSessionId());
        driver.get("https://www.lambdatest.com/selenium-playground/");
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS); // Waiting time to page the load completely
  		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().deleteAllCookies();
	 
  }
  @AfterMethod
  public void close()
  {
	  driver.quit();
  }

}