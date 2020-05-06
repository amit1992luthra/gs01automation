package com.Pojo;
import com.seleniumFuctions.*;;

public class Locator
{
    private LocatorType LocatorType;

    private String LocatorName;

    private String LocatorValue;

    public LocatorType getLocatorType ()
    {
        return LocatorType;
    }

    public void setLocatorType (LocatorType LocatorType)
    {
        this.LocatorType = LocatorType;
    }

    public String getLocatorName ()
    {
        return LocatorName;
    }

    public void setLocatorName (String LocatorName)
    {
        this.LocatorName = LocatorName;
    }

    public String getLocatorValue ()
    {
        return LocatorValue;
    }

    public void setLocatorValue (String LocatorValue)
    {
        this.LocatorValue = LocatorValue;
    }
}