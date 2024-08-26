package com.globant.pages;

import com.globant.utils.basePage.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutInfoPage extends BasePage {
    @FindBy(css = ".title")
    private WebElement sectionTitle;

    @FindBy(css = "form > .checkout_info")
    private WebElement formContainer;

    @FindBy(id = "first-name")
    private WebElement firstNameInput;

    @FindBy(id = "last-name")
    private WebElement lastNameInput;

    @FindBy(xpath = "//*[@class='form_group']/input[@placeholder='Zip/Postal Code']")
    private WebElement zipCodeInput;

    @FindBy(css = "input[type='submit']")
    private WebElement continueBtn;

    public CheckoutInfoPage(WebDriver driver) {
        super(driver);
    }

    public boolean isTitleDisplayed() {
        super.waitElementIsDisplayed(sectionTitle);
        return this.sectionTitle.isDisplayed();
    }

    public boolean isFormDisplayed() {
        super.waitElementIsDisplayed(this.formContainer);
        return this.formContainer.isDisplayed();
    }

    public void setFirstName(String firstName) {
        if (firstName == null || firstName.isEmpty())
            throw new IllegalArgumentException("CheckoutPage - SetFirstName: Invalid first name is provided");

        super.waitElementIsDisplayed(this.firstNameInput);
        this.firstNameInput.sendKeys(firstName);
    }

    public void setLastName(String lastName) {
        if (lastName == null || lastName.isEmpty())
            throw new IllegalArgumentException("CheckoutPage - SetLastName: Invalid last name is provided");

        super.waitElementIsDisplayed(this.lastNameInput);
        this.lastNameInput.sendKeys(lastName);
    }

    public void setZipCode(String zipCode) {
        if (zipCode == null || zipCode.isEmpty())
            throw new IllegalArgumentException("CheckoutPage - SetZipCode: Invalid zip code is provided");

        super.waitElementIsDisplayed(this.zipCodeInput);
        this.zipCodeInput.sendKeys(zipCode);
    }

    public CheckoutOverviewPage selectContinueOption() {
        super.waitElementBeClickable(this.continueBtn);
        this.continueBtn.click();

        if (super.verifyUrlContains("checkout-step-two"))
            return new CheckoutOverviewPage(super.driver);
        // Invalid credentials or invalid flow
        return null;
    }
}
