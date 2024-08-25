package com.globant.tests;

import com.globant.pages.*;
import com.globant.utils.baseTest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class PurchaseProductTest extends BaseTest {
    private InventoryPage loadInventoryPage() {
        return new InventoryPage(super.driverManager.getDriver());
    }

    @BeforeMethod()
    @Parameters({"username", "password"})
    public InventoryPage verifySuccessfulLogin(String username, String password) {
        LoginPage loginPage = super.loadFirstPage();
        Assert.assertTrue(loginPage.isFormDisplayed());
        loginPage.setUsername(username);
        loginPage.setPassword(password);
        return loginPage.selectLoginOption();
    }

    @Test
    @Parameters({"firstName", "lastName", "zipCode"})
    public void verifySuccessfulPurchase(String firstName, String lastName, String zipCode) {
        InventoryPage inventoryPage = this.loadInventoryPage();
        Assert.assertNotNull(inventoryPage);
        inventoryPage.addToCartRandomProduct();
        Assert.assertFalse(inventoryPage.isCartEmpty());

        CartPage cartPage = inventoryPage.goToCart();
        Assert.assertNotNull(cartPage);
        Assert.assertTrue(cartPage.isTitleDisplayed());
        Assert.assertTrue(cartPage.haveProductsInCart());

        CheckoutInfoPage checkoutInfoPage = cartPage.goToCheckout();
        Assert.assertNotNull(checkoutInfoPage);
        Assert.assertTrue(checkoutInfoPage.isTitleDisplayed());
        Assert.assertTrue(checkoutInfoPage.isFormDisplayed());
        checkoutInfoPage.setFirstName(firstName);
        checkoutInfoPage.setLastName(lastName);
        checkoutInfoPage.setZipCode(zipCode);

        CheckoutOverviewPage checkoutOverviewPage = checkoutInfoPage.selectContinueOption();
        Assert.assertNotNull(checkoutOverviewPage);
        Assert.assertTrue(checkoutOverviewPage.isTitleDisplayed());

        CheckoutCompletePage checkoutCompletePage = checkoutOverviewPage.selectFinishOption();
        Assert.assertNotNull(checkoutCompletePage);
        Assert.assertTrue(checkoutCompletePage.isCheckoutImgDisplayed());
        Assert.assertEquals(checkoutCompletePage.getStatusMessage(), "Thank you for your order!");
    }
}
