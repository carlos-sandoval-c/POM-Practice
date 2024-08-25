package com.globant.pages;

import com.globant.utils.basePage.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Random;

public class InventoryPage extends BasePage {
    @FindBy(css = ".shopping_cart_link")
    WebElement goToCartLink;

    @FindBy(xpath = "//button[contains(@class, 'btn_inventory')][contains(@name, 'add')]")
    List<WebElement> productsAddToCartBtnList;

    public InventoryPage(WebDriver driver) {
        super(driver);
    }

    public void addToCartRandomProduct() {
        if (!this.productsAddToCartBtnList.isEmpty())
            super.waitElementsAreDisplayed(this.productsAddToCartBtnList);

        Random rand = new Random();
        int randProductIndex = rand.nextInt(this.productsAddToCartBtnList.size());
        this.productsAddToCartBtnList.get(randProductIndex).click();
    }

    public boolean isCartEmpty() {
        super.waitElementIsDisplayed(this.goToCartLink);
        // If the cart is empty, it will not have text.
        return this.goToCartLink.getText().isEmpty();
    }

    public CartPage goToCart() {
        super.waitElementIsDisplayed(this.goToCartLink);
        this.goToCartLink.click();

        if (super.verifyUrlContains("cart"))
            return new CartPage(super.driver);
        return null;
    }
}
