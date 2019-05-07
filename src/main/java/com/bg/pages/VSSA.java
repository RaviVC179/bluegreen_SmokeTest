package com.bg.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.bg.util.GenericUtils;

public class VSSA extends GenericUtils {
	
	@FindBy(how=How.XPATH,using="//td[contains(text(),'Owner Number (ARVACT):')]/input")
	public WebElement OwnerNumber;
	
	@FindBy(how=How.XPATH,using="//input[@id='btnSearch']")
	public WebElement SearchButton;

}
