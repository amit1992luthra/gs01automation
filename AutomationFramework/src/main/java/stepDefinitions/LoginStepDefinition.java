 package stepDefinitions;

 import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.junit.Assert;
import org.openqa.selenium.By;
 import org.openqa.selenium.JavascriptExecutor;
 import org.openqa.selenium.Point;
 import org.openqa.selenium.WebDriver;
 import org.openqa.selenium.WebElement;
 import org.openqa.selenium.chrome.ChromeDriver;
 import org.openqa.selenium.interactions.Actions;

import com.ContextInjection.ScenarioContext;
import com.core.CommonUtils;
import com.core.Hooks;
import com.core.TestProperties;
import com.core.WebDriverUtils;
import com.seleniumFuctions.SeleniumFunctions;

import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
 import cucumber.api.java.en.Then;
 import cucumber.api.java.en.When;
import cucumber.runtime.junit.Assertions;
import io.qameta.allure.Description;
import io.qameta.allure.Story;


 public class LoginStepDefinition extends SeleniumFunctions{
	
	 ScenarioContext ScenarioContext ;
	 public LoginStepDefinition(ScenarioContext scenarioContext){
		 this.ScenarioContext= scenarioContext;
	 }
	
	

 
@Description("Verify home page title")
@Story("Test verify base page title")
@Given("^user is already on Login Page$")
 public void user_already_on_login_page(){ 	
	 try {
		NavigateToURL(TestProperties.getInstance().getBaseUrl());
	} catch (Exception e) {
		
	}
	
	 ScenarioContext.setScenarioContext("GivenTestKey", "GivenTestvalue");
	 System.out.println("Senario");
 }


 
 
 

 @Then("^user enters \"(.*)\" and \"(.*)\"$")
 public void user_enters_username_and_password(String username, String
 password){
	 
	 System.out.println(ScenarioContext.getScenarioContext("GivenTestKey"));
	 try {
		 LoadApplication();
		Page("LoginPage").sendKeysToElement("username", username, false, "");
		Page("LoginPage").sendKeysToElement("password", password, false, "");
	} 
	 catch (Exception e) {
		e.printStackTrace();
	}
 }

 @Then("^user clicks on login button$")
 public void user_clicks_on_login_button() 
 {
	 try {
	Page("LoginPage").clickElement("submit");
	 }
	 catch(Exception ex) {
	 }
 }
 
 //And user click on Add user Link
 @And("^user click on Add user Link$")
 public void user_clicks_on_Add_User_button() 
 {
	 try {
	Page("HomePage").clickElement("AddUser");
	 }
	 catch(Exception ex) {
	 }
 }

 






 }
