package com.core;

import java.util.HashMap;

import org.openqa.selenium.WebDriver;

import com.Pojo.Locator;
import com.Pojo.Page;



public class BaseVariables {
	private WebDriver webdriver = null;
	private HashMap<String, Page> PageList = new HashMap<String, Page>();
	
	private static BaseVariables instance = null ;
	 public static BaseVariables getInstance() {
	        if (instance == null) {
	            instance = new BaseVariables();
	        }
	        return instance;
	    }	
	
	 
	 public WebDriver getWebdriver() {
        return webdriver;
    }
	 
	 public void setWebdriver(WebDriver webdriver) {
	        this.webdriver = webdriver;
	    }
	 
	 public HashMap<String, Page> GetPageList() {
		 return PageList;
	 }
	 
	 public void SetPageList(HashMap<String, Page> PageList){
		 this.PageList = PageList;
	 }
}
