package com.globant.tests;

import com.globant.pages.LoginPage;
import com.globant.utils.baseTest.BaseTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class PurchaseProductTest extends BaseTest {
    @Test
    @Parameters({"username", "password"})
    public void verifySuccessfulPurchase(String username, String password) {
        LoginPage loginPage = super.loadFirstPage();
        loginPage.setUsername(username);
        loginPage.setPassword(password);
        loginPage.selectLoginOption();
    }
}
