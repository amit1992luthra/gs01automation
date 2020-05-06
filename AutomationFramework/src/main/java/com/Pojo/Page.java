package com.Pojo;

import java.util.HashMap;

public class Page
{
	
	HashMap<String, Locator> LocatorList = new HashMap<String, Locator>(); 

    private String PageName;

    public HashMap<String, Locator> getLocatorList()
    {
        return LocatorList;
    }

    public void setLocatorList(HashMap<String, Locator> LocatorList)
    {
        this.LocatorList = LocatorList;
    }

    public String getPageName ()
    {
        return PageName;
    }

    public void setPageName (String PageName)
    {
        this.PageName = PageName;
    }    
}