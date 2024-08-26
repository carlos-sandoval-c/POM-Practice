package com.globant.pages;

import com.globant.utils.basePage.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CartPage extends BasePage {
    @FindBy(css = ".title")
    private WebElement sectionTitle;

    @FindBy(css = ".cart_item")
    private List<WebElement> productItemCardsList;

    @FindBy(xpath = "//button[contains(@id, 'remove')]")
    private List<WebElement> productItemRemoveBntList;

    @FindBy(id = "checkout")
    private WebElement checkoutBtn;

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public boolean isTitleDisplayed() {
        super.waitElementIsDisplayed(sectionTitle);
        return this.sectionTitle.isDisplayed();
    }

    public boolean haveProductsInCart() {
        if (this.productItemCardsList.isEmpty())
            return false;
        super.waitElementsAreDisplayed(this.productItemCardsList);
        return true;
    }

    public void removeAllProducts() {
        if (this.productItemRemoveBntList.isEmpty())
            return;
        super.waitElementsAreDisplayed(this.productItemRemoveBntList);
        for (WebElement removeBtn : this.productItemRemoveBntList) {
            removeBtn.click();
        }
    }

    public CheckoutInfoPage goToCheckout() {
        super.waitElementIsDisplayed(this.checkoutBtn);
        this.checkoutBtn.click();

        if (super.verifyUrlContains("checkout-step-one"))
            return new CheckoutInfoPage(super.driver);
        return null;
    }
}
