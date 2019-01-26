package com.w2a.pages;

import com.w2a.pages.crm.CRMHomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.w2a.base.Page;

public class ZohoAppPage extends Page{

	public ZohoAppPage() {
		super();
	}

	public void introducingZiaSearch() {
		WebElement element1 = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("button.close-btn")));
		element1.click();
	}

	public void goToAllApps() {
		click("allAppMenu_CSS");
	}

	public CRMHomePage goToCRM(){
		//driver.findElement(By.xpath("//*[@id=\"zl-category-1\"]/div/ul/li[1]/div/a")).click();
		WebElement element2 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"zl-category-1\"]/div/ul/li[1]/div/a")));
		element2.click();
		return new CRMHomePage();
    }

	public void goToMail() {

	}

	public void goToCreator() {

	}

	public void goToBooks() {

	}

}
