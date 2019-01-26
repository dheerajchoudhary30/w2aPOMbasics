package com.w2a.pages;

import org.openqa.selenium.By;

import com.w2a.base.Page;

public class HomePage extends Page{

	public LoginPage goToLogin() {
		click("loginLink_CSS");
		return new LoginPage();
	}

	public void goToSignUp() {
	    click("signUpLink_XPATH");
	}

	public void goToSupport() {
		click("supportLink_XPATH");
	}

	public void goToCustomers() {
		click("customers_XPATH");
	}

	public void goToContactSales() {
		click("contactSales_XPATH");
	}

}
