package com.w2a.base;

import com.w2a.pages.AccountsPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TopMenu {

    WebDriver driver;

    public TopMenu(WebDriver driver){
        this.driver = driver;
    }


    public void goToLeads() {

    }

    public void goToContacts() {

    }

    public AccountsPage goToAccounts() {
      Page.click("accountsLink_XPATH");
      return new AccountsPage();
    }

    public void goToDeals() {

    }

    public void goToActivities() {

    }

    public void goToReports() {

    }

    public void goToAnalytics() {

    }

    public void goToMore() {

    }

    public void signOut(){
        driver.findElement(By.id("topdivuserphoto_17305000000055015")).click();
     driver.findElement(By.xpath("//*[@id=\"newSettingsDropDown\"]/div[2]/div/div[5]/span/a")).click();
    }
}
