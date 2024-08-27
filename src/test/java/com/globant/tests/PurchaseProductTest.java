package com.globant.tests;

import com.globant.pages.*;
import com.globant.utils.baseTest.BaseTest;
import com.globant.utils.persistence.DataPropertiesProvider;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PurchaseProductTest extends BaseTest {
    @Test(dataProvider = "purchase-data", dataProviderClass = DataPropertiesProvider.class)
    public void verifySuccessfulPurchase(String firstName, String lastName, String zipCode) {
        InventoryPage inventoryPage = super.loadInventoryPage();
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
