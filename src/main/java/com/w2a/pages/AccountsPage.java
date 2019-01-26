package com.w2a.pages;

import com.w2a.pages.crm.accounts.CreateAccountPage;

import static com.w2a.base.Page.click;

public class AccountsPage {

    public CreateAccountPage goToCreateAccount() {
      click("createAccountsLink_XPATH");
      return new CreateAccountPage();
    }

    public void goToImportAccounts() {

    }

    public void goToDisableModule() {

    }

}
