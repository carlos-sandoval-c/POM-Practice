package com.globant.pages;

import com.globant.utils.basePage.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutCompletePage extends BasePage {
    @FindBy(css = ".pony_express")
    private WebElement checkoutImg;

    @FindBy(css = ".complete-header")
    private WebElement confirmationText;

    public CheckoutCompletePage(WebDriver driver) {
        super(driver);
    }

    public boolean isCheckoutImgDisplayed() {
        super.waitElementIsDisplayed(this.checkoutImg);
        return this.checkoutImg.isDisplayed();
    }

    public String getStatusMessage() {
        super.waitElementIsDisplayed(this.confirmationText);
        return this.confirmationText.getText();
    }
}
