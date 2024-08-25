package com.globant.pages;

import com.globant.utils.basePage.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
    @FindBy(css = ".login_wrapper")
    private WebElement loginContainer;

    @FindBy(id = "user-name")
    private WebElement usernameInput;

    @FindBy(css = "input[type='password']")
    private WebElement passwordInput;

    @FindBy(xpath = "//form/input[contains(@value, 'Login')]")
    private WebElement loginBtn;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public boolean isFormDisplayed() {
        return this.loginContainer.isDisplayed();
    }

    public void setUsername(String username) throws IllegalArgumentException {
        if (username == null || username.isEmpty())
            throw new IllegalArgumentException("LoginPage - SetUsername: Invalid username is provided");
        this.usernameInput.sendKeys(username);
    }

    public void setPassword(String password) throws IllegalArgumentException {
        if (password == null || password.isEmpty())
            throw new IllegalArgumentException("LoginPage - SetPassword: Invalid password is provided");
        this.passwordInput.sendKeys(password);
    }

    public InventoryPage selectLoginOption() {
        this.loginBtn.click();

        if (super.verifyUrlContains("inventory"))
            return new InventoryPage(super.driver);
        // Invalid credentials or invalid flow
        return null;
    }
}
