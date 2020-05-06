package com.core;

import org.junit.Assert;

public class AssertUtils {
	
	private static AssertUtils instance = null ;
	
	public static AssertUtils getInstance(){
		if(instance==null){
			instance = new AssertUtils();
		}
		return instance;
	}
	
	public void assertEquals(String expected , String actual, String message){
		try {
			if((expected == null && actual == null) || (expected == null && actual.equals("null")) || (actual == null && expected.equals("null")) || (expected.equals("null") && actual.equals("null")))
			{
				Assert.assertTrue(true);
			}
			else 
			{
				Assert.assertTrue(message + " | expected : " + expected + " but actual : " + actual, expected.equalsIgnoreCase(actual));
			}
		} catch (Exception e) {
			Assert.fail(message + " | expected : " + expected + " but actual : " + actual);
		}
	}

	
	public void assertEqualsObject(Object expected , Object actual, String message){
		try {
			if((expected == null && actual == null) || (expected == null && actual.equals("null")) || (actual == null && expected.equals("null")))
			{
				Assert.assertTrue(true);
			}
			else 
			{
				Assert.assertEquals(message + " | expected : " + expected + " but actual : " + actual,expected, actual);
			}
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	public void assertTrue(boolean result, String message){
		try {
			Assert.assertTrue( message,result);
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}
	
	public void assertNotNull(Object object, String message){
		try {
			Assert.assertNotNull(message,object);
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	public void fail(String message){
		try {
			Assert.fail(message);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
}
	
	public void assertNull(Object object, String message){
		try {
			Assert.assertNull(message,object);
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}



}
