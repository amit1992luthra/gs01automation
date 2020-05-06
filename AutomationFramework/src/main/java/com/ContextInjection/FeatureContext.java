package com.ContextInjection;

import java.util.HashMap;
import java.util.Map;

public class FeatureContext {

private   Map<String,Object> featureContext = new HashMap<String,Object>();
	private static FeatureContext instance = null;
	
	public static FeatureContext getInstance(){
		if (instance==null) {
			
			instance = new FeatureContext();
		}
		return instance;
	}
	
	public void setFeatureContext(String key , Object value){
		featureContext.put(key.toString(), value);
	}
	
	public Object getFeatureContext(String key){
		return featureContext.get(key.toString());
	}

	public boolean isContain(String key){
		return featureContext.containsKey(key.toString());
	}
	
}
