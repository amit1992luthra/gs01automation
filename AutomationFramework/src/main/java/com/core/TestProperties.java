package com.core;

import java.io.FileInputStream;
import java.util.Properties;




public class TestProperties {

	private static TestProperties instance = null;
	Properties prop = new Properties();
	
	private String baseUrl;
	private String browserName ;
	private String waitTime ;
	private String testDataSheet ;
	
	
	
	private TestProperties() {

	}

	public static TestProperties getInstance() {
		if (instance == null) {
			instance = new TestProperties();
		}
		return instance;
	}
	
	
	public void initProperties(){
		try {
			prop.load(new FileInputStream(PathConstants.getInstance().getBaseResourcesFolder("test.properties")));
					baseUrl = prop.getProperty("BASE_URL").trim();
					browserName = prop.getProperty("BROWSER_NAME").trim();
					waitTime = prop.getProperty("WAIT_TIME").trim();
					testDataSheet= prop.getProperty("TEST_DATA_SHEET").trim();
				//	featurTag = prop.getProperty("FEATURE_TAG").trim();
					
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		
		
	}
	
	public String getTestDataSheet(){
		return testDataSheet;
	}
	
	public String getBaseUrl() {
		return baseUrl;
	}
	
	public String  getBrowserName(){
		return browserName;
	}
	
	public int getwaitTime(){
		return Integer.parseInt(waitTime);
	}
}
