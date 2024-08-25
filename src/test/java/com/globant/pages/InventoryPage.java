package com.globant.pages;

import com.globant.utils.RandInt;
import com.globant.utils.basePage.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class InventoryPage extends BasePage {
    @FindBy(css = ".shopping_cart_link")
    private WebElement goToCartLink;

    @FindBy(xpath = "//button[contains(@class, 'btn_inventory')][contains(@name, 'add')]")
    private List<WebElement> productsAddToCartBtnList;

    public InventoryPage(WebDriver driver) {
        super(driver);
    }

    public void addToCartRandomProduct() {
        if (!this.productsAddToCartBtnList.isEmpty())
            super.waitElementsAreDisplayed(this.productsAddToCartBtnList);

        int indexProduct = RandInt.getRandomIndex(this.productsAddToCartBtnList.size());
        if (indexProduct < 0)
            return;

        this.productsAddToCartBtnList.get(RandInt.getRandomIndex(indexProduct)).click();
    }

    public void addToCartRandomProducts(int totalProducts) {
        if (!this.productsAddToCartBtnList.isEmpty())
            super.waitElementsAreDisplayed(this.productsAddToCartBtnList);

        int[] indexProductsArr = RandInt.getRandomIndex(this.productsAddToCartBtnList.size(), totalProducts);
        if (indexProductsArr == null)
            return;

        for (int index : indexProductsArr) {
            this.productsAddToCartBtnList.get(index).click();
        }
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
