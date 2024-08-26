package com.globant.tests;

import com.globant.pages.InventoryPage;
import com.globant.pages.LoginPage;
import com.globant.utils.baseTest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LogoutTest extends BaseTest {
    @Test
    public void verifySuccessfulLogout() {
        InventoryPage inventoryPage = super.loadInventoryPage();
        Assert.assertNotNull(inventoryPage);
        LoginPage loginPage = inventoryPage.logout();
        Assert.assertTrue(loginPage.isFormDisplayed());
    }
}
