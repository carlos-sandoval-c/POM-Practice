package com.globant.tests;

import com.globant.pages.CartPage;
import com.globant.pages.InventoryPage;
import com.globant.utils.baseTest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class CartManagementTest extends BaseTest {
    @Test
    @Parameters({"total_products"})
    public void verifyProductAddAndRemove(int total_products) {
        InventoryPage inventoryPage = super.loadInventoryPage();
        Assert.assertNotNull(inventoryPage);
        inventoryPage.addToCartRandomProducts(total_products);
        Assert.assertFalse(inventoryPage.isCartEmpty());

        CartPage cartPage = inventoryPage.goToCart();
        Assert.assertNotNull(cartPage);
        Assert.assertTrue(cartPage.isTitleDisplayed());
        Assert.assertTrue(cartPage.haveProductsInCart());
        cartPage.removeAllProducts();
        Assert.assertFalse(cartPage.haveProductsInCart());
    }
}
