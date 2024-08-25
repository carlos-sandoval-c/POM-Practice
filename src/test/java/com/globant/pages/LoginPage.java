package com.globant.pages;

import com.globant.utils.basePage.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
    @FindBy(id = "user-name")
    private WebElement usernameInput;

    @FindBy(css = "input[type='password']")
    private WebElement passwordInput;

    @FindBy(xpath = "//form/input[contains(@value, 'Login')]")
    private WebElement loginBtn;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void setUsername(String username) throws IllegalArgumentException {
        if (username == null || username.isEmpty())
            throw new IllegalArgumentException("LoginPage - SetUsername: Invalid username is provided");

        super.waitElementIsDisplayed(this.usernameInput);
        this.usernameInput.sendKeys(username);
    }

    public void setPassword(String password) throws IllegalArgumentException {
        if (password == null || password.isEmpty())
            throw new IllegalArgumentException("LoginPage - SetPassword: Invalid password is provided");

        super.waitElementIsDisplayed(this.passwordInput);
        this.passwordInput.sendKeys(password);
    }

    public void selectLoginOption() {
        super.waitElementBeClickable(this.loginBtn);
        this.loginBtn.click();
    }
}
