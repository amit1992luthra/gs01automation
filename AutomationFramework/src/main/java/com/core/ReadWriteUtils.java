package com.core;
import java.io.File;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import com.Pojo.Locator;
import com.Pojo.Page;
import com.seleniumFuctions.LocatorType;

public class ReadWriteUtils 
{
	public void ReadLocators() {
		
		
		
		File xmlFile = new File(PathConstants.getInstance().getXmlFolderpath(TestProperties.getInstance().getTestDataSheet()));
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;
        HashMap<String, Page> PageList = new HashMap<String, Page>();
        try {
            dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            NodeList PageNodeList = doc.getElementsByTagName("Page");
            for (int i = 0; i < PageNodeList.getLength(); i++) 
            {
            	Page page = new Page();
            	String PageName = PageNodeList.item(i).getAttributes().getNamedItem("name").getNodeValue(); //Fetching PageName
            	
            	NodeList LocatorNodeList = doc.getElementsByTagName("Locator");
            	HashMap<String, Locator> locatorlist = new HashMap<String, Locator>(); 
            	for (int j = 0; j < LocatorNodeList.getLength(); j++)
            	{           		            	
	            	Locator locator = new Locator(); 
	            	locator.setLocatorName(LocatorNodeList.item(j).getAttributes().getNamedItem("name").getNodeValue());
	            	locator.setLocatorType(LocatorType.valueOf(LocatorNodeList.item(j).getAttributes().getNamedItem("Type").getNodeValue()));
	            	locator.setLocatorValue(LocatorNodeList.item(j).getAttributes().getNamedItem("Value").getNodeValue());
	            	
	            	locatorlist.put(LocatorNodeList.item(j).getAttributes().getNamedItem("name").getNodeValue(), locator);
            	}
            	page.setPageName(PageName);
            	page.setLocatorList(locatorlist); 
            	
            	PageList.put(PageName, page);
            }
            BaseVariables.getInstance().SetPageList(PageList);
        }
        catch(Exception ex) {
        	
        }
	}
}
