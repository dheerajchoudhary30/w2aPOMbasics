package com.w2a.pages.crm.accounts;

import com.w2a.base.Page;

import javax.print.DocFlavor;

public class CreateAccountPage extends Page {


    public void createAccounts(String accountName){
     type("accountName_XPATH",accountName);
    }

}
