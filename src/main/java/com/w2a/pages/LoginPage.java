package com.w2a.pages;

import org.openqa.selenium.By;


import com.w2a.base.Page;

public class LoginPage extends Page{

	public ZohoAppPage doLogin(String username,String password) {
		type("username_CSS",username);
		type("password_CSS",password);
		click("submit_CSS");
		return new ZohoAppPage();
	}

}
