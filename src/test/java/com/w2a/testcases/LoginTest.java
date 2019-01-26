package com.w2a.testcases;


import com.w2a.pages.HomePage;
import com.w2a.pages.LoginPage;
import com.w2a.utilities.Utilities;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

import java.util.Hashtable;

import static com.w2a.base.Page.excel;

public class LoginTest extends BaseTest {

    @Test(dataProviderClass = Utilities.class,dataProvider = "dp")
    public void loginTest(Hashtable<String,String> data){
        HomePage home = new HomePage();
        Utilities.shouldTestRun(new Object() {}
                .getClass()
                .getEnclosingMethod()
                .getName(), excel);

        if(!data.get("RunMode").equalsIgnoreCase("Y")) {
            throw new SkipException("Skipping the test for the data set as runmode is NO");
        }

        LoginPage lp = home.goToLogin();
        lp.doLogin(data.get("username"), data.get("password"));

    }
}
