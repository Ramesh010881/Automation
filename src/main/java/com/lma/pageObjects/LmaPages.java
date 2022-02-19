package com.lma.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.lma.accelarators.Base;

public class LmaPages extends Base {

	static WebDriver ldriver;

	public LmaPages(WebDriver driver) {
		ldriver = driver;
		PageFactory.initElements(driver, this);
	}

	// ========================= PAGE METHODS ============================

	
	public LmaCounterPartyPage CounterParty() {
		return new LmaCounterPartyPage(ldriver);
	}
	public LmaLoginPage LoginPage() {
		return new LmaLoginPage(ldriver);
	}
	
	
	
}
