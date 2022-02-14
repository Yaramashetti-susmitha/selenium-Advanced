package com.lambdatest;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import org.openqa.selenium.Keys;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Navigation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;



import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;



public class Testng1 {

  
   public String ptfm;
       public RemoteWebDriver driver = null;
	public String clickLink;  

  @Test(priority=0)
  public void scenarioOne() throws Exception {
		  
			 
    driver.get(" https://www.lambdatest.com/");
    WebDriverWait wait = new WebDriverWait(driver, 15);
    wait.until(ExpectedConditions.titleContains("Most Powerful Cross Browser Testing Tool Online | LambdaTest")); 
    
    WebElement scroll = driver.findElement(By.cssSelector("a[class='uppercase font-bold text-black text-size-16 tracking-widest inline-block hover:underline']"));
   
    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", scroll);
    Thread.sleep(500); 
    if(ptfm.equalsIgnoreCase("macOS Sierra"))
      {
        clickLink = Keys.chord(Keys.COMMAND, Keys.ENTER);
        scroll.sendKeys(clickLink);
     }
     else{
    clickLink = Keys.chord(Keys.CONTROL ,Keys.ENTER);
    scroll.sendKeys(clickLink);}
    ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
    driver.switchTo().window(tabs.get(1));
    System.out.println(tabs);
   String  actual= driver.getCurrentUrl();
    String expected = "https://www.lambdatest.com/integrations";
    Assert.assertEquals(actual, expected);    
    WebElement codelessAutomation = driver.findElement(By.xpath("//h2[text()='Codeless Automation']"));
    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", codelessAutomation);
    driver.findElement(By.cssSelector("a[href='https://www.lambdatest.com/support/docs/testingwhiz-integration/']")).click();
    String getActualTitle = driver.getTitle();
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertEquals(getActualTitle, "TestingWhiz Integration | LambdaTest");
   driver.close();
  Set<String> count= driver.getWindowHandles();
   System.out.println(count.size());
  driver.switchTo().window(tabs.get(0));
   driver.navigate().to("https://www.lambdatest.com/blog");
   WebElement community = driver.findElement(By.xpath("(//a[@href='https://community.lambdatest.com/'])[2]"));
   ((JavascriptExecutor) driver).executeScript("arguments[0].click();", community);
   String url =driver.getCurrentUrl();
   Assert.assertEquals(url, "https://community.lambdatest.com/");
		
  }
  
  @BeforeMethod
  @Parameters({ "browser", "version", "platform" })
  public void setUpClass(String browser, String version, String platform) throws Exception {

  	String username = "susmithavarma733"; 
		String accesskey = "6w7Qw7t3krNKItvr8UTfl0Trh7q94QxureEY4iPYfvmRfbYCK6"; 
        ptfm= platform;
  		DesiredCapabilities capability = new DesiredCapabilities();    	
        
  		capability.setCapability(CapabilityType.BROWSER_NAME, browser);
  		capability.setCapability(CapabilityType.VERSION,version);
  		capability.setCapability(CapabilityType.PLATFORM, platform);		
  		
         capability.setCapability("build", "Advanced");
  		capability.setCapability("network", true);
  		capability.setCapability("video", true);
  		capability.setCapability("console", true);
  		capability.setCapability("visual", true);

  		String gridURL = "https://" + username + ":" + accesskey + "@hub.lambdatest.com/wd/hub";
  		//System.out.println(gridURL);
  		driver = new RemoteWebDriver(new URL(gridURL), capability);
  		System.out.println(capability);
  		//System.out.println(driver.getSessionId());
	 
  }
  @AfterMethod
  public void close()
  {
	  driver.quit();
  }

}