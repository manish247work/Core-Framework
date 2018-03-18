package Core.Hybrid.TestNG;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import junit.framework.Assert;

public class GenericKeywords {

	public static WebDriver driver = null;
	/**
	 * openBrowser function is use to open the browser based on the value we pass in parameter
	 * 
	 * @Param BrowserType use Browser value as "Mozilla", "IE", "Chrome"
	 * 
	 * @author manishkumar04
	 * 
	 */
	public String openBrowser(String BrowserType) {
		try {
		if (BrowserType.equals("Mozilla")) {
			System.setProperty("webdriver.geko.driver", System.getProperty("user.dir")+"\\src\\test\\java\\Core\\Hybrid\\UtillFiles\\geckodriver.exe");
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
		} else if (BrowserType.equals("IE")) {
			System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+"\\src\\test\\java\\Core\\Hybrid\\UtillFiles\\IEDriverServer.exe");
			driver = new InternetExplorerDriver();
			driver.manage().window().maximize();
		} else if (BrowserType.equals("Chrome")) {
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\src\\test\\java\\Core\\Hybrid\\UtillFiles\\chromedriver.exe");
			driver = new ChromeDriver();
			//driver.manage().window().maximize();
		}
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		}
		catch(Exception e )
		{
			System.out.println("=========================Errors============================");
			System.out.println(" Fail---> unable to find the browser type with browser Name as  " + BrowserType);
			System.out.println(e.getMessage());
			System.out.println("=========================Errors============================");
			e.printStackTrace();
		}
		return "Pass--> Browser open Successfully";
	}

	/**
	 * getElement function is use to find the element based on the different
	 * locators
	 * 
	 * @Param Locators use Locator value as "xpath", "id", "name", "linkText",
	 *        "partialLinkText"
	 * @param value
	 *            enter the value of locator
	 * @author manishkumar
	 * 
	 */
	public static WebElement getElement(String Locatorsvalue) {
		WebElement Element = null;

		try {
			if (Locatorsvalue.endsWith("_xpath")) {
				Element = driver.findElement(By.xpath(Locatorsvalue));
			} else if (Locatorsvalue.endsWith("id")) {
				Element = driver.findElement(By.id(Locatorsvalue));
			} else if (Locatorsvalue.endsWith("name")) {
				Element = driver.findElement(By.name(Locatorsvalue));
			} else if (Locatorsvalue.endsWith("linkText")) {
				Element = driver.findElement(By.linkText(Locatorsvalue));
			} else if (Locatorsvalue.endsWith("partialLinkText")) {
				Element = driver.findElement(By.partialLinkText(Locatorsvalue));
			}else if (Locatorsvalue.endsWith("tagName")) {
				Element = driver.findElement(By.tagName(Locatorsvalue));
			}

		} catch (Exception e) {
			System.out.println("=========================Errors============================");
			Assert.fail("unable to find the element with locator" );
			System.out.println(" unable to find the element with locator " + Locatorsvalue
					+ " or unable to put the value i.e. " + Locatorsvalue);
			System.out.println(e.getMessage());
			System.out.println("=========================Errors============================");
			e.printStackTrace();
		}
		return Element;

	}
	
	/**
	 * isElementVisible function is use to check if element is visible or clickable or not
	 * 
	 * @Param Locators use Locator value as "xpath", "id", "name", "linkText",
	 *        "partialLinkText"
	 * @param value
	 *            enter the value of locator
	 * @author manishkumar
	 * 
	 */
	public static boolean isElementVisible(String Locatorsvalue)
	{
		try {
		if(getElement(Locatorsvalue).isDisplayed() && getElement(Locatorsvalue).isEnabled())
		{
			System.out.println("Element present with Locator " +Locatorsvalue + " and value is "+Locatorsvalue);
			return true;
			
		}
		else
		{
		return false;	
		}
		}
		catch(Exception e )
		{
			System.out.println("=========================Errors============================");
			System.out.println(" element is not present or enabled with Locator " + Locatorsvalue + " and value as "+ Locatorsvalue);
			System.out.println(e.getMessage());
			System.out.println("=========================Errors============================");
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * clickButton function is use to Click on a button
	 * 
	 * @Param Locators use Locator value as "xpath", "id", "name", "linkText",
	 *        "partialLinkText"
	 * @param value
	 *            enter the value of locator
	 * @author manishkumar
	 * 
	 */
	public void clickButton(String Locatorsvalue)
	{
		try {
		if(isElementVisible(Locatorsvalue))
		{
			getElement(Locatorsvalue).click();
		}
		else
		{
			System.out.println("Either element is not visible or not clickable in clickButton function ");
		}
		}
		catch(Exception e)
		{
			System.out.println("=========================Errors============================");
			System.out.println("Getting error message while clicking on the button with " + Locatorsvalue + " and value as "+ Locatorsvalue);
			System.out.println(e.getMessage());
			System.out.println("=========================Errors============================");
			e.printStackTrace();
		}
	}
	/**
	 * enterText function is use to Enter text in a text field
	 * 
	 * @Param locator enter Locator value as "xpath", "id", "name", "linkText","partialLinkText"
	 * 
	 * @param value
	 *            enter the value of locator
	 *            
	 * @param text enter text whatever we need to keyin in the text field
	 * 
	 * @author manishkumar
	 * 
	 */
	public void enterText(String Locatorsvalue, String text)
	{
		try {
		if(isElementVisible(Locatorsvalue))
		{
			getElement(Locatorsvalue).clear();
			getElement(Locatorsvalue).sendKeys(text);
		}
		else
		{
			System.out.println("Either element is not visible or not clickable in enterText function ");
		}
		}
		catch(Exception e)
		{
			System.out.println("=========================Errors============================");
			System.out.println("Getting error message while entering data in the textfield having " + Locatorsvalue + " and value as "+ Locatorsvalue);
			System.out.println(e.getMessage());
			System.out.println("=========================Errors============================");
			e.printStackTrace();
		}
	}
	/**
	 * openApplication function is used to open the Application
	 * 
	 * @Param URL enter the Site URL to open
	 * 
	 * 
	 * @author manishkumar
	 * 
	 */
	public void openApplication(String URL)
	{
		try 
		{
		driver.get(URL);
		}
		catch(Exception e)
		{
			System.out.println("=========================Errors============================");
			System.out.println("Unable to open the Application");
			System.out.println(e.getMessage());
			System.out.println("=========================Errors============================");
			e.printStackTrace();
		}
		}
	
	/**
	 * clickLink function is use to Click on a Link
	 * 
	 * @Param Locators use Locator value as "xpath", "id", "name", "linkText",
	 *        "partialLinkText"
	 * @param value
	 *            enter the value of locator
	 * @author manishkumar
	 * 
	 */
	public void clickLink(String Locatorsvalue)
	{
		try {
		if(isElementVisible(Locatorsvalue))
		{
			getElement(Locatorsvalue).click();
		}
		else
		{
			System.out.println("Either element is not visible or not clickable in clickLink function ");
		}
		}
		catch(Exception e)
		{
			System.out.println("=========================Errors============================");
			System.out.println("Getting error message while clicking on the link with " + Locatorsvalue + " and value as "+ Locatorsvalue);
			System.out.println(e.getMessage());
			System.out.println("=========================Errors============================");
			e.printStackTrace();
		}
	}
	
	/**
	 * VerifyLinkText function is use to get the text of a Link
	 * 
	 * @Param Locatorsvalue enter locator value with underscore locator type ie. //a[contains(text(),'Electronics')]_xpath 
	 * 
	 * @author manishkumar
	 * 
	 */
	public boolean VerifyLinkText(String Locatorsvalue, String expectedString)
	{
		boolean txt = false;
		try {
		if(isElementVisible(Locatorsvalue))
		{
			String actual_value =getElement(Locatorsvalue).getText();
			if(actual_value.equalsIgnoreCase(expectedString))
			 {
				 System.out.println("link text is same as actual ie. " +txt);
				 return true;
			 }
			
		else
		{
			System.out.println("Either element is not equal to the expected link text");
			return false;
		
		}
		}
		else
		{
			System.out.println("Either element is not visible or not clickable in VerifyLinkText function ");
			return false;
		}
		}
		catch(Exception e)
		{
			System.out.println("=========================Errors============================");
			System.out.println("Getting error message while clicking on the link with " + Locatorsvalue + " and value as "+ Locatorsvalue);
			System.out.println(e.getMessage());
			System.out.println("=========================Errors============================");
			e.printStackTrace();
			return txt;
		}
		
	}
	
	/**
	 * getLinkHref function is use to get the href  of a Link
	 * 
	 * @Param Locators use Locator value as "xpath", "id", "name", "linkText",
	 *        "partialLinkText"
	 * @param value
	 *            enter the value of locator
	 * @author manishkumar
	 * 
	 */
	public String getLinkHref(String Locatorsvalue)
	{
		String txt = "Fail" ;
		try {
		if(isElementVisible(Locatorsvalue))
		{
			txt =getElement(Locatorsvalue).getAttribute("href");
			return txt;
		}
		else
		{
			System.out.println("Either element is not visible or not clickable in getLinkHref function ");
			return txt;
		}
		}
		catch(Exception e)
		{
			System.out.println("=========================Errors============================");
			System.out.println("Getting error message while getting the href for link with " + Locatorsvalue + " and value as "+ Locatorsvalue);
			System.out.println(e.getMessage());
			System.out.println("=========================Errors============================");
			e.printStackTrace();
			return txt;
		}
	}
	
	/**
	 * getLinkcounts function is use to get the count of webElements based on locators
	 * 
	 * @Param Locatorsvalue enter locator value with underscore locator type ie. //a[contains(text(),'Electronics')]_xpath 
	 * 
	 * @author manishkumar
	 * 
	 */
	public int getLinkcounts(String Locatorsvalue)
	{
		int count =0;
		try {
			List<WebElement> actual_value =getElements(Locatorsvalue);
			count=  actual_value.size();
			return count;
		}
		catch(Exception e)
		{
			System.out.println("=========================Errors============================");
			System.out.println("Getting error message while clicking on the link with " + Locatorsvalue + " and value as "+ Locatorsvalue);
			System.out.println(e.getMessage());
			System.out.println("=========================Errors============================");
			e.printStackTrace();
			return count;
		}
		
	}
	
	/**
	 * getAllWebElementText function is use to get the list of webElements based on locators
	 * 
	 * @Param Locatorsvalue enter locator value with underscore locator type ie. //a[contains(text(),'Electronics')]_xpath 
	 * 
	 * @author manishkumar
	 * 
	 */
	public ArrayList<String>  getAllWebElementText(String Locatorsvalue)
	{
		ArrayList<String> linkText = null ;
		try {
			List<WebElement> actual_value =getElements(Locatorsvalue);
			 int count=  actual_value.size();
			for(int i =0; i<count;i++)
			{
				String val = actual_value.get(i).getText();
				linkText.add(val);
			}
			return linkText;
		}
		catch(Exception e)
		{
			System.out.println("=========================Errors============================");
			System.out.println("Getting error message while clicking on the link with " + Locatorsvalue + " and value as "+ Locatorsvalue);
			System.out.println(e.getMessage());
			System.out.println("=========================Errors============================");
			e.printStackTrace();
			return linkText;
		}
		
	}
	
	/**
	 * getElements function is use to find the list of similar type of element based on the different
	 * locators
	 * 
	 * @Param Locators use Locator value as "xpath", "id", "name", "linkText",
	 *        "partialLinkText"
	 * @param value
	 *            enter the value of locator
	 * @author manishkumar
	 * 
	 */
	public static List<WebElement> getElements(String Locatorsvalue) {
		List<WebElement> Element = null;

		try {
			if (Locatorsvalue.endsWith("_xpath")) {
				Element = driver.findElements(By.xpath(Locatorsvalue));
			} else if (Locatorsvalue.endsWith("id")) {
				Element = driver.findElements(By.id(Locatorsvalue));
			} else if (Locatorsvalue.endsWith("name")) {
				Element = driver.findElements(By.name(Locatorsvalue));
			} else if (Locatorsvalue.endsWith("linkText")) {
				Element = driver.findElements(By.linkText(Locatorsvalue));
			} else if (Locatorsvalue.endsWith("partialLinkText")) {
				Element = driver.findElements(By.partialLinkText(Locatorsvalue));
			} else if (Locatorsvalue.endsWith("tagName")) {
				Element = driver.findElements(By.tagName(Locatorsvalue));
			}

		} catch (Exception e) {
			System.out.println("=========================Errors============================");
			Assert.fail("unable to find the elements with locator" );
			System.out.println(" unable to find the elements with locator " + Locatorsvalue
					+ " or unable to put the value i.e. " + Locatorsvalue);
			System.out.println(e.getMessage());
			System.out.println("=========================Errors============================");
			e.printStackTrace();
		}
		return Element;

	}
	
	/**
	 * getElements function is use to find the list of similar type of element based on the different
	 * locators
	 * 
	 * @Param Locators use Locator value as "xpath", "id", "name", "linkText",
	 *        "partialLinkText"
	 * @param value
	 *            enter the value of locator
	 * @author manishkumar
	 * 
	 */
	public static int  frameCount(String Locatorsvalue) {
		int count =0;
		try {
			List<WebElement> actual_value =getElements(Locatorsvalue);
			 count=  actual_value.size();
			 return count;
			
		} catch(Exception e)
		{
			System.out.println("=========================Errors============================");
			System.out.println("Getting error message while clicking on the link with " + Locatorsvalue + " and value as "+ Locatorsvalue);
			System.out.println(e.getMessage());
			System.out.println("=========================Errors============================");
			e.printStackTrace();
			return count;
		}
	
	}
	
	
	public static void main(String[] args) {
		System.out.println(System.getProperty("user.dir")+"\\src\\test\\java\\Core\\Hybrid\\UtillFiles");
		GenericKeywords obj = new GenericKeywords();
		obj.openBrowser("Chrome");
		obj.openApplication("https://www.linkedin.com/");
	
		
		
	}

}
