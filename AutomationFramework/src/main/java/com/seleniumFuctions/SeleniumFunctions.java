package com.seleniumFuctions;

import java.util.Random;
import java.util.Set;
import java.util.logging.Logger;

import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.ElementNotSelectableException;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.core.BaseVariables;
import com.core.CommonUtils;
import com.core.ReadWriteUtils;
import com.core.*;
public class SeleniumFunctions {
	WebDriverWait wait ;
	WebDriver driver ;
	
	public SeleniumFunctions(){
		
		this.driver=BaseVariables.getInstance().getWebdriver();
		this.wait = new WebDriverWait(driver, TestProperties.getInstance().getwaitTime());
	}
	
	String LocValue ="";
	
	private static final Logger LOGGER = Logger.getLogger(CommonUtils.class.getName());
	private String PageName = "";
	
	
	
	
	
	public SeleniumFunctions Page(String PageName) {
		this.PageName = PageName;
		return this;
	}
	
	public void LoadApplication() 
	{
		ReadWriteUtils RWU = new ReadWriteUtils();
		RWU.ReadLocators();
	}
	
	public Boolean clickElement(String elmtName) throws Exception
    {
		Boolean Clicked = false;
        try
        {            
            WebElement El = wait.until(ExpectedConditions.elementToBeClickable(getElement(elmtName)));
            El.click();


            Clicked = true;
            Thread.sleep(4000);
        }
        catch (ElementClickInterceptedException Iex)
        {
            clickToElementByJS(elmtName);
        }
        catch (Exception ex)
        {
            throw new Exception(ex.toString());
        }
        return Clicked;
    }
	
	public Boolean clickDynamicElement(String elmtName, String replacedBy)throws Exception
    {
        Boolean Clicked = false;
        try
        {
            WebElement element = getElementDynamically(elmtName, replacedBy);
            element.click();
            Clicked = true;
        }
        catch (ElementNotVisibleException ex)
        {
            LOGGER.info("Element:" + elmtName + " is not visible/present on this page.Exception Description:" + ex.getMessage());
            throw new Exception("Element:" + elmtName + " is not visible/present on this page");
        }
        catch (StaleElementReferenceException ex)
        {
            LOGGER.info("Element:" + elmtName + " is no longer present on this page.Exception Description:" + ex.getMessage());
            throw new Exception("Element:" + elmtName + " is no longer present on this page");
        }
        catch (Exception ex)
        {
            LOGGER.info("Exception while clicking dynamic element:" + ex.getMessage());
            throw new Exception("Exception while clicking dynamic element:" + ex.toString());
        }
        return Clicked;
    }
	
	
	
	public Boolean clickDynamicElementByJS(String elmtName, String replacedBy)throws Exception
    {
        Boolean Clicked = false;
        try
        {
            JavascriptExecutor ex = (JavascriptExecutor)driver;
            WebElement element = getElementDynamically(elmtName, replacedBy);
            ex.executeScript("arguments[0].click();", element);
            Clicked = true;
        }
        catch (Exception ex)
        {
            LOGGER.info("Exception while clicking dynamic element by JS:" + ex.getMessage());
            throw new Exception("Exception while clicking dynamic element by JS:" + ex.toString());
        }
        return Clicked;
    }
	
	
	public void clickToElementByJS(String elmtName)
    {
        WebElement element = null;
		try {
			element = getElement(elmtName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        JavascriptExecutor js = (JavascriptExecutor) driver;  
        js.executeScript("arguments[0].click();", element);        
    }
	
	public String GetElementAttribute(String elmtName, String attributeName) throws Exception
    {
        String value = "";
        try
        {
            WebElement El = getElement(elmtName);
            Thread.sleep(4000);
            //add wait here
//            WaitForPageToLoad();
            value = El.getAttribute(attributeName);            
        }
        catch (Exception ex)
        {
            throw new Exception("Error while fetching value of element:" + elmtName + ".Error occured is:" + ex.getMessage());
        }
        return value;
    }
	
	public String GetElementText(String elmtName, String attributeName) throws Exception
    {
        String value = "";
        try
        {
            WebElement El = getElement(elmtName);
            Thread.sleep(4000);
            //add wait here
//            WaitForPageToLoad();
            value = El.getText();
            
        }
        catch (Exception ex)
        {
            throw new Exception("Error while fetching value of element:" + elmtName + ".Error occured is:" + ex.getMessage());
        }
        return value;
    }
	
	public LocatorType locatorTypeName(String elmtName)
	{
		LocatorType locatorType = BaseVariables.getInstance().GetPageList().get("LoginPage").getLocatorList().get(elmtName).getLocatorType();
		return locatorType;
//		
	}	 
	public WebElement getElement(String elmtName) throws Exception
    {
        WebElement element = null;
        try
        {
        	LocValue = BaseVariables.getInstance().GetPageList().get("LoginPage").getLocatorList().get(elmtName).getLocatorValue();
        	element = FindElement(locatorTypeName(elmtName), LocValue);

        }
        catch (NoSuchElementException ex)
        {
        	LOGGER.info("Unable to find the element" + elmtName + "Exception Description:" + ex);
            throw new Exception("Unable to find the element" + elmtName);
        }
        catch (ElementNotSelectableException ex)
        {
            LOGGER.info("Unable to select element:" + elmtName + "Exception Description:" + ex);
            throw new Exception("Unable to select element:" + elmtName);
        }
        catch (ElementNotVisibleException ex)
        {
            LOGGER.info("Element is not visible:" + elmtName + "Exception Description:" + ex);
            throw new Exception("Element is not visible:" + elmtName);
        }
        catch (TimeoutException ex)
        {
            LOGGER.info("Timeout Error while finding element:" + elmtName + "Exception Description:" + ex);
            throw new Exception("Timeout Error while finding element:" + elmtName);
        }
        catch (StaleElementReferenceException ex)
        {
            LOGGER.info("Stale Element:" + elmtName + "Exception Description:" + ex);
            throw new Exception("Stale Element:" + elmtName);
        }
        catch (Exception ex)
        {
            LOGGER.info("Unable to find the element" + elmtName + "Exception Description:" + ex);
            throw new Exception("Unable to find the element" + elmtName + "Exception Description:" + ex);
        }

        return element;
    }
	
	protected WebElement FindElement(LocatorType locType, String locValue) throws Exception
    {
        WebElement elmt = null;
        try
        {                
            switch (locType)
            {
                case XPath:
                    if (isElementPresent(By.xpath(locValue)))
                    {
                    	elmt = driver.findElement(By.xpath(locValue));
                    }

                    break;
                case ID:
                    if (isElementPresent(By.id(locValue)))
                    {
                    	elmt = driver.findElement(By.id(locValue));
                    }

                    break;
                case TagName:
                    if (isElementPresent(By.tagName(locValue)))
                    {
                    	elmt = driver.findElement(By.tagName(locValue));
                    }

                    break;
                case Name:
	                {
	                	elmt = driver.findElement(By.name(locValue));
	                	 if (isElementPresent(By.name(locValue)))
	                     {
	                     	elmt = driver.findElement(By.name(locValue));
	                     }
	                }                  

                    break;
                case ClassName:
                    if (isElementPresent(By.className(locValue)))
                    {
                    	elmt = driver.findElement(By.className(locValue));
                    }

                    break;
                case LinkText:
                    if (isElementPresent(By.linkText(locValue)))
                    {
                    	elmt = driver.findElement(By.linkText(locValue));
                    }

                    break;
                case CssSelector:
                    if (isElementPresent(By.cssSelector(locValue)))
                    {
                    	elmt = driver.findElement(By.cssSelector(locValue));                    }

                    break;
            }
        }
        catch (NoSuchElementException ex)
        {
            LOGGER.info("Unable to find the element" + elmt + "Exception Description:" + ex);
            throw new Exception("Unable to find the element" + elmt);
        }
        return elmt;
    }
	
	protected Set<WebElement> FindElements(LocatorType locType, String locValue)throws Exception
    {
		Set<WebElement> elmt = null;
        try
        {
            switch (locType)
            {
                case XPath:
                    if (isElementPresent(By.xpath(locValue)))
                    {
                        elmt = (Set<WebElement>) driver.findElements(By.xpath(locValue));
                    }

                    break;
                case ID:
                    if (isElementPresent(By.id(locValue)))
                    {
                        elmt = (Set<WebElement>) driver.findElements(By.id(locValue));
                    }

                    break;
                case TagName:
                    if (isElementPresent(By.tagName(locValue)))
                    {
                        elmt = (Set<WebElement>) driver.findElements(By.tagName(locValue));
                    }

                    break;
                case Name:
                    if (isElementPresent(By.name(locValue)))
                    {
                        elmt = (Set<WebElement>) driver.findElements(By.name(locValue));
                    }

                    break;
                case ClassName:
                    if (isElementPresent(By.className(locValue)))
                    {
                        elmt = (Set<WebElement>) driver.findElements(By.className(locValue));
                    }

                    break;
                case LinkText:
                    if (isElementPresent(By.linkText(locValue)))
                    {
                        elmt = (Set<WebElement>) driver.findElements(By.linkText(locValue));
                    }

                    break;
                case CssSelector:
                    if (isElementPresent(By.cssSelector(locValue)))
                    {
                        elmt = (Set<WebElement>) driver.findElements(By.cssSelector(locValue));
                    }

                    break;
            }
        }
        catch (NoSuchElementException ex)
        {
            LOGGER.info("Unable to find the element" + elmt + "Exception Description:" + ex);
            throw new Exception("Unable to find the element" + elmt);
        }
        return elmt;
    }
	
	public void sendKeysToElement(String elmtName, String text, Boolean clearExistingText, String replacedBy) throws Exception
    {
		
		driver.get("https://activate-qt.gs1.org/login");
        WebElement El;
//      add wait here
        try
        {
        	if(!replacedBy.isEmpty())
            {
                El = getElementDynamically(elmtName, replacedBy);
            }                    
            else
            {
                El = getElement(elmtName);
            }
//          add wait here
            if (clearExistingText)
            {
              
                El.clear();
            }
            El.sendKeys(text);
            Thread.sleep(4000);
//            add wait here for page load
        }
        catch (Exception ex)
        {
        	LOGGER.info("Error occured while entering '" + text + "' in element:" + elmtName + " .Error occured is:" + ex);
            throw new Exception("Error occured while entering '" + text + "' in element:" + elmtName);
        }
    }
	
	
	
	public Boolean NavigateToURL(String URL) throws Exception
	   {
		Boolean IsNavigationSuccess = false;
	       try
	       {
	    	   driver.get(URL);
	    	   LOGGER.info("Navigating to URL:" + URL);
	           driver.manage().window().maximize();
	           IsNavigationSuccess = true;
	       }
	       catch (UnhandledAlertException Ex)
	        {
	            handleAlert();
	        }
	       catch (Exception e)
	       {
	           LOGGER.info("Navigation to URL " + URL + " failed due to " + e.getMessage());
	           throw new Exception("Navigation to URL " + URL + " failed due to " + e.getMessage());
	       }
	       return IsNavigationSuccess;
	   }
	
	public void scrollTo(String length)
    {
        
    
        JavascriptExecutor javaScriptExecutor = (JavascriptExecutor)driver;
        javaScriptExecutor.executeAsyncScript("window.scrollBy(0,"+ length+")");
        
    }
	
	public void openNewWindow()throws Exception
    {
		JavascriptExecutor javaScriptExecutor = (JavascriptExecutor)driver;
        javaScriptExecutor.executeAsyncScript("window.open()");
    }
		
	public WebElement getElementDynamically(String elmtName, String valuetoreplace) throws Exception
    {
        WebElement element = null;
        try
        {
        	element = FindElement(locatorTypeName(elmtName), LocValue);
        }
        catch (NoSuchElementException ex)
        {
            LOGGER.info("Unable to find the element" + elmtName + " Exception Description:" + ex);
            throw new Exception("Unable to find the element " + elmtName);
        }
        catch (ElementNotSelectableException ex)
        {
            LOGGER.info("Unable to select element: " + elmtName + " Exception Description:" + ex);
            throw new Exception("Unable to select element: " + elmtName);
        }
        catch (ElementNotVisibleException ex)
        {
            LOGGER.info("Element: " + elmtName + " is not visible.Exception Description:" + ex);
            throw new Exception("Element: " + elmtName + " is not visible.");
        }
        catch (TimeoutException ex)
        {
            LOGGER.info("Timeout Error while finding element: " + elmtName + "Exception Description:" + ex);
            throw new Exception("Timeout Error while finding element: " + elmtName);
        }
        catch (StaleElementReferenceException ex)
        {
            LOGGER.info("Stale Element: " + elmtName + "Exception Description:" + ex);
            throw new Exception("Stale Element: " + elmtName);
        }
        catch (Exception ex)
        {
            LOGGER.info("Unable to find the element " + elmtName + "Exception Description:" + ex);
            throw new Exception("Unable to find the element " + elmtName + "Exception Description:" + ex);
        }
        return element;
    }
	/*public Set<WebElement> GetElementSetDynamically(String elmtName, String valuetoreplace) throws Exception
    {
		Set<WebElement> element = null;
        try
        {
//            String LocValue = Helper.UpdateXPathWithOriginalVal(StepBase.getInstance().PageList[PageName].LocatorList[elmtName].LocValue, valuetoreplace);
//            element = FindElements(StepBase.getInstance().PageList[PageName].LocatorList[elmtName].LocatorType, LocValue);
            element = (Set<WebElement>) FindElement(LocatorTypeName(elmtName), LocValue);
        }
        catch (NoSuchElementException ex)
        {
            LOGGER.info("Unable to find elements:" + elmtName + " Exception Description:" + ex);
            throw new Exception("Unable to Find Elements: " + elmtName);
        }
        catch (ElementNotSelectableException ex)
        {
            LOGGER.info("Unable to select elements: " + elmtName + " Exception Description:" + ex);
            throw new Exception("Unable to select elements: " + elmtName);
        }
        catch (ElementNotVisibleException ex)
        {
            LOGGER.info("Element: " + elmtName + " is not visible.Exception Description:" + ex);
            throw new Exception("Element: " + elmtName + " is not visible.");
        }
        catch (TimeoutException ex)
        {
            LOGGER.info("Timeout Error while finding element: " + elmtName + "Exception Description:" + ex);
            throw new Exception("Timeout Error while finding element: " + elmtName);
        }
        catch (StaleElementReferenceException ex)
        {
            LOGGER.info("Stale Element: " + elmtName + "Exception Description:" + ex);
            throw new Exception("Stale Element: " + elmtName);
        }
        catch (Exception ex)
        {
            LOGGER.info("Unable to find the element " + elmtName + "Exception Description:" + ex);
            throw new Exception("Unable to find the element " + elmtName + "Exception Description:" + ex);
        }
        return (Set<WebElement>) element;
    }
	
	public void WaitForPageToLoad()throws Exception
    {
        wait = new WebDriverWait(driver, 10);
        TimeSpan timeout = new TimeSpan(0, 0, 30);

        JavascriptExecutor javascript = (JavascriptExecutor)driver;
        if (javascript == null)
            throw new ArgumentException("driver", "Driver must support javascript execution");

        wait.Until((d) =>
        {
            try
            {
                String readyState = javascript.ExecuteScript(
                "if (document.readyState) return document.readyState;").toString();
                return readyState.toLowerCase() == "complete";
            }
            catch (InvalidOperationException e)
            {
                //Window is no longer available
                return e.Message.ToLower().Contains("unable to get browser");
            }
            catch (WebDriverException e)
            {
                //Browser is no longer available
                return e.Message.ToLower().Contains("unable to connect");
            }
            catch (Exception)
            {
                return false;
            }
        });
    }*/
	private Boolean isElementPresent(By by)
    {
        try
        {
        	WebElement element =wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            return  element.isDisplayed();
        }
        catch (Exception e)
        {
            return false;
        }
    }
	
	
	public void selectElementByText(String elmtName, String val) throws Exception
    {
        try
        {
            WebElement elm = getElement(elmtName);            
            Select se = new Select(elm);
            se.selectByVisibleText(val);
        }
        catch (NoSuchElementException ex)
        {
            LOGGER.info("Unable to find the element" + elmtName + ". Exception occured is:" + ex.getMessage());
            throw new Exception("Unable to find the element " + elmtName + ".");
        }
        catch (Exception ex)
        {
            LOGGER.info("Unable to find the element" + elmtName + ". Exception occured is:" + ex.getMessage());
            throw new Exception("Unable to find the element " + elmtName + ".");
        }
    }

    public void switchToNewWindow()
    {
    	Set<String> allWindowHandles = driver.getWindowHandles();
    	for(String handle : allWindowHandles)        
        {
            driver.switchTo().window(handle);
        }
    }
	
    public Boolean SwitchToFrame(String framename)
    {
        Boolean IsSwitched = false;
        try
        {
            //wait = Browser.wait;
            driver.switchTo().frame(framename);
            IsSwitched = true;
        }
        catch(Exception ex)
        {
            LOGGER.info("Unable to switch frame" + ex.getMessage());
        }
        return IsSwitched;
    }

    public Boolean SwitchToFrame(int frameIndex)throws Exception
    {
        Boolean IsSwitched = false;
        try
        {
            //wait = Browser.wait;
            driver.switchTo().frame(frameIndex);
            IsSwitched = true;
        }
        catch (Exception ex)
        {
            LOGGER.info("Unable to switch frame" + ex.getMessage());
        }
        return IsSwitched;
    }

    public void switchToDefaultFrame()throws Exception
    {
        try
        {
            //wait = Browser.wait;
            driver.switchTo().defaultContent();
        }
        catch (Exception ex)
        {
            LOGGER.info("Unable to switch to the parent frame" + ex.getMessage());
        }
    }
    public Boolean verifyTitle(String ExpTitle)
    {
        Boolean titleMatched = false;
        try
        {
        	
            if (driver.getTitle().contains(ExpTitle))
            {
                titleMatched = true;
            }
            Assert.assertTrue(titleMatched);
            
        }
        catch (Exception ex)
        {
            Assert.fail("Unable to verify title: Exception" + ex.getMessage());
        }
        return titleMatched;
    }

    public Boolean checkElementVisibility(String ElmName) throws Exception
    {
        Boolean IsVisible = false;
        try
        {
            WebElement elm = getElement(ElmName);
            IsVisible = elm.isDisplayed();
        }
        catch (ElementNotVisibleException ex)
        {
            IsVisible = false;
        }
        
        return IsVisible;
    }
    
    
    
    public WebElement focusElement(String element) throws Exception
    {
        WebElement elmtName = getElement(element);
        Actions actions = new Actions(driver);
        actions.moveToElement(elmtName);
        actions.perform();
        return elmtName;
    }
    public WebElement focusElementAndClick(String element) throws Exception
    {
        WebElement elmtName = getElement(element);
        Actions actions = new Actions(driver);
        actions.moveToElement(elmtName).click();
        actions.perform();
        return elmtName;
    }

    public WebElement doubleClickElement(String element) throws Exception
    {
        WebElement elmtName = getElement(element);
        Actions actions = new Actions(driver);
        actions.doubleClick(elmtName);
        actions.perform();

        return elmtName;
    }
    
   
    public WebElement scrollElementToCenterView(String elmtName) throws Exception
    {
        WebElement element = getElement(elmtName);
        JavascriptExecutor javaScriptExecutor = (JavascriptExecutor)driver;
        javaScriptExecutor.executeAsyncScript("arguments[0].scrollIntoView({block: 'center'});", element);
        return element;
    }

    public WebElement scrollToElement(String elmtName) throws Exception
    {
        WebElement element = getElement(elmtName);
        JavascriptExecutor javaScriptExecutor = (JavascriptExecutor)driver;
        javaScriptExecutor.executeAsyncScript("arguments[0].scrollIntoView(false);", element);
        return element;
    }
    
    public void RefreshBrowser()
    {
       driver.navigate().refresh();
    }
    public Boolean handleAlert()throws Exception
    {
        Boolean IsHandled = false;
        try
        {
            Alert myAlert = driver.switchTo().alert();
            myAlert.accept();
            IsHandled = true;
        }
        catch(Exception ex)
        {
            LOGGER.info("Unable to handle the alert");
        }
        return IsHandled;
    }
    
    public Boolean isAlertPresent()
    {
        try
        {
        	driver.switchTo().alert();
            return true;
        }   
        catch (NoAlertPresentException Ex)
        {
            return false;
        }   
    }
    
    public Boolean VerifyPageUrl(String URL)
    {
        return driver.getCurrentUrl().contains(URL);

    }
    
    
    //Generating Random String 
    
    public String randomstring()
    {
    	   int leftLimit = 97; // letter 'a'
    	    int rightLimit = 122; // letter 'z'
    	    int targetStringLength = 10;
    	    Random random = new Random();
    	    StringBuilder buffer = new StringBuilder(targetStringLength);
    	    for (int i = 0; i < targetStringLength; i++) {
    	        int randomLimitedInt = leftLimit + (int) 
    	          (random.nextFloat() * (rightLimit - leftLimit + 1));
    	        buffer.append((char) randomLimitedInt);
    	    }
    	    String generatedString = buffer.toString();
    	 
    	    return generatedString;
    }

    
    
}
