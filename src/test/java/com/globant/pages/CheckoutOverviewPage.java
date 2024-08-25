package com.globant.pages;

import com.globant.utils.basePage.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutOverviewPage extends BasePage {
    @FindBy(css = ".title")
    private WebElement sectionTitle;

    @FindBy(id = "finish")
    private WebElement finishBtn;

    public CheckoutOverviewPage(WebDriver driver) {
        super(driver);
    }

    public boolean isTitleDisplayed() {
        super.waitElementIsDisplayed(sectionTitle);
        return this.sectionTitle.isDisplayed();
    }

    public CheckoutCompletePage selectFinishOption() {
        super.waitElementBeClickable(this.finishBtn);
        this.finishBtn.click();

        if (super.verifyUrlContains("checkout-complete"))
            return new CheckoutCompletePage(super.driver);
        // Invalid credentials or invalid flow
        return null;
    }
}
