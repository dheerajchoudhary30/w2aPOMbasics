package com.w2a.testcases;

import com.w2a.base.Page;
import com.w2a.pages.ZohoAppPage;
import com.w2a.utilities.Utilities;
import org.testng.annotations.Test;

import java.util.Hashtable;

public class CreateAccountTest extends BaseTest{

    @Test(dataProviderClass = Utilities.class,dataProvider = "dp")
    public void createAccountTest(Hashtable<String,String> data){

        ZohoAppPage zapage = new ZohoAppPage();
        zapage.introducingZiaSearch();
        zapage.goToAllApps();
        zapage.goToCRM();
        Page.switchToWindow();
        (Page.menu.goToAccounts()).goToCreateAccount().createAccounts(data.get("accountName"));

    }

}
