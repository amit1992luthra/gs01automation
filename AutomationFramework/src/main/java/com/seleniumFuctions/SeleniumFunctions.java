package com.seleniumFuctions;

import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.ElementNotSelectableException;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.core.BaseVariables;
import com.core.CommonUtils;
import com.core.ReadWriteUtils;

import io.qameta.allure.Allure;
import org.junit.*;

import com.core.*;
public class SeleniumFunctions{
	WebDriverWait wait ;
	WebDriver driver ;
	AssertUtils assertUtils = AssertUtils.getInstance();
	public SeleniumFunctions(){
		
		this.driver=BaseVariables.getInstance().getWebdriver();
		this.wait = new WebDriverWait(driver, TestProperties.getInstance().getwaitTime());
	}
	
	String LocatorValue ="";
	
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
	
	
	public Boolean ClickElement(String elementName) throws Exception
    {
		Boolean Clicked = false;
        try
        {
        	WebElement El = wait.until(ExpectedConditions.elementToBeClickable(GetElement(elementName)));
        }
        catch (ElementClickInterceptedException Iex)
        {
            ClickToElementByJS(elementName);
        }
        catch (Exception ex)
        {
            throw new Exception(ex.toString());
        }
        return Clicked;
    }
	
	public void ClickToElementByJS(String elementName)
    {
        WebElement element = null;
		try {
			element = GetElement(elementName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        JavascriptExecutor js = (JavascriptExecutor) driver;  
        js.executeScript("arguments[0].click();", element);        
    }
	 
	public LocatorType LocatorTypeName(String elementName)
	{
		LocatorType locatorType = BaseVariables.getInstance().GetPageList().get("LoginPage").getLocatorList().get(elementName).getLocatorType();
		return locatorType;
	}
	public WebElement GetElement(String elementName) throws Exception
    {
        WebElement element = null;
		
        try
        {
        	LocatorValue = BaseVariables.getInstance().GetPageList().get("LoginPage").getLocatorList().get(elementName).getLocatorValue();
        	element = FindElement(LocatorTypeName(elementName), LocatorValue);

        }
        catch (NoSuchElementException ex)
        {
        	assertUtils.fail(ex.getMessage());
        	
        	LOGGER.info("Unable to find element:" + elementName + "Exception:" + ex);
            throw new Exception("Unable to Find Element:" + elementName);
        }
        catch (ElementNotSelectableException ex)
        {
        	AssertUtils.getInstance().fail(ex.getMessage());
            LOGGER.info("Unable to select element:" + elementName + "Exception:" + ex);
            throw new Exception("Unable to select element:" + elementName);
        }
        catch (ElementNotVisibleException ex)
        {
        	AssertUtils.getInstance().fail(ex.getMessage());
            LOGGER.info("Element is not visible:" + elementName + "Exception:" + ex);
            throw new Exception("Element is not visible:" + elementName);
        }
        catch (TimeoutException ex)
        {
        	AssertUtils.getInstance().fail(ex.getMessage());
            LOGGER.info("Timeout Issue while finding element:" + elementName + "Exception:" + ex);
            throw new Exception("Timeout Issue while finding element:" + elementName);
        }
        catch (StaleElementReferenceException ex)
        {
        	org.junit.Assert.fail(ex.getMessage());
            LOGGER.info("Stale Element Exceltion occured for element:" + elementName + "Exception:" + ex);
            throw new Exception("Stale Element Exceltion occured for element:" + elementName);
        }
        catch (Exception ex)
        {
        	AssertUtils.getInstance().fail(ex.getMessage());
            LOGGER.info("Unable to find element:" + elementName + "Exception:" + ex);
            throw new Exception("Unable to find element:" + elementName + "Exception:" + ex);
        }

        return element;
    }
	
	protected WebElement FindElement(LocatorType locatorType, String locatorValue) throws Exception
    {
        WebElement ele = null;
        try
        {                
            switch (locatorType)
            {
                case XPath:
                    if (IsElementPresent(By.xpath(locatorValue)))
                    {
                    	ele = driver.findElement(By.xpath(locatorValue));
                    }

                    break;
                case ID:
                    if (IsElementPresent(By.id(locatorValue)))
                    {
                    	ele = driver.findElement(By.id(locatorValue));
                    }

                    break;
                case TagName:
                    if (IsElementPresent(By.tagName(locatorValue)))
                    {
                    	ele = driver.findElement(By.tagName(locatorValue));
                    }

                    break;
                case Name:
	                {
	                	ele = driver.findElement(By.name(locatorValue));
	                	 if (IsElementPresent(By.name(locatorValue)))
	                     {
	                     	ele = driver.findElement(By.name(locatorValue));
	                     }
	                }                  

                    break;
                case ClassName:
                    if (IsElementPresent(By.className(locatorValue)))
                    {
                    	ele = driver.findElement(By.className(locatorValue));
                    }

                    break;
                case LinkText:
                    if (IsElementPresent(By.linkText(locatorValue)))
                    {
                    	ele = driver.findElement(By.linkText(locatorValue));
                    }

                    break;
                case CssSelector:
                    if (IsElementPresent(By.cssSelector(locatorValue)))
                    {
                    	ele = driver.findElement(By.cssSelector(locatorValue));                    }

                    break;
            }
        }
        catch (NoSuchElementException ex)
        {
            LOGGER.info("Unable to find element:" + ele + "Exception:" + ex);
            throw new Exception("Unable to Find Element:" + ele);
        }
        return ele;
    }
	
	public void SendKeysToElement(String elementName, String text, Boolean clearAndSend, String ValToUpdate) throws Exception
    {
        WebElement El;
        try
        {
        	if(!ValToUpdate.isEmpty())
            {
                El = GetDynamicElement(elementName, ValToUpdate);
            }                    
            else
            {
                El = GetElement(elementName);
            }
            if (clearAndSend)
            {
                El.clear();
            }
            El.sendKeys(text);
            Thread.sleep(1000);
        }
        catch (Exception ex)
        {
        	Allure.addAttachment("Issue while inputting '" + text, "' in element:" + elementName + " .Error occured is:" + ex);
        	LOGGER.info("Issue while inputting '" + text + "' in element:" + elementName + " .Error occured is:" + ex);
            throw new Exception("Issue while inputting '" + text + "' in element:" + elementName);
        }
    }
	
	public void NavigateToURL(String URL) throws Exception
	   {
	       try
	       {
	    	   driver.get(URL);
	    	   LOGGER.info("Navigating to URL:" + URL);
	           driver.manage().window().maximize();
	       }
	       catch (Exception e)
	       {
	           LOGGER.info("Navigation to URL " + URL + " failed due to " + e.getMessage());
	           throw new Exception("Navigation to URL " + URL + " failed due to " + e.getMessage());
	       }
	   }
	
	public WebElement GetDynamicElement(String elementName, String valuetoreplace) throws Exception
    {
        WebElement element = null;
        try
        {
        	element = FindElement(LocatorTypeName(elementName), LocatorValue);
        }
        catch (NoSuchElementException ex)
        {
            LOGGER.info("Unable to find element:" + elementName + " Exception:" + ex);
            throw new Exception("Unable to Find Element: " + elementName);
        }
        catch (ElementNotSelectableException ex)
        {
            LOGGER.info("Unable to select element: " + elementName + " Exception:" + ex);
            throw new Exception("Unable to select element: " + elementName);
        }
        catch (ElementNotVisibleException ex)
        {
            LOGGER.info("Element: " + elementName + " is not visible.Exception:" + ex);
            throw new Exception("Element: " + elementName + " is not visible.");
        }
        catch (TimeoutException ex)
        {
            LOGGER.info("Timeout Issue while finding element: " + elementName + "Exception:" + ex);
            throw new Exception("Timeout Issue while finding element: " + elementName);
        }
        catch (StaleElementReferenceException ex)
        {
            LOGGER.info("Stale Element Exceltion occured for element: " + elementName + "Exception:" + ex);
            throw new Exception("Stale Element Exceltion occured for element: " + elementName);
        }
        catch (Exception ex)
        {
            LOGGER.info("Unable to find element: " + elementName + "Exception:" + ex);
            throw new Exception("Unable to find element: " + elementName + "Exception:" + ex);
        }
        return element;
    }
	
	private Boolean IsElementPresent(By by)
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
	
}
