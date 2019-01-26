package com.w2a.pages;

import org.openqa.selenium.By;

import com.w2a.base.Page;


public class SignUpPage extends Page{


	public void doSignUp(String email, String password) {

		driver.findElement(By.id("emailfield")).sendKeys(email);
		driver.findElement(By.id("password")).sendKeys(password);

		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		driver.findElement(By.id("signup-termservice")).click();

		driver.findElement(By.id("signupbtn")).click();
	}

}
