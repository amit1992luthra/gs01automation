package com.seleniumFuctions;

public enum LocatorType
{
    /// <summary>
    /// ID - eg: txtUsername, ddlAccountType
    /// </summary>
    ID,

    /// <summary>
    /// XPath - eg: 
    /// //tagname[@attribute='value'] 
    /// html/body/div[1]/section/div[1]/div/div/div/div[1]/div/div/div/div/div[3]/div[1]/div/h4[1]/b
    /// </summary>
    XPath,

    /// <summary>
    /// CssSelector - eg: a:active, p::before, input:checked
    /// </summary>
    CssSelector,

    /// <summary>
    /// TagName - eg: div, span
    /// </summary>
    TagName,

    /// <summary>
    /// ClassName - eg: pageTitle, customerGrid
    /// </summary>
    ClassName,

    /// <summary>
    /// LinkText - eg:
    /// </summary>
    LinkText,

    /// <summary>
    /// Name - eg:
    /// </summary>
    Name
}
