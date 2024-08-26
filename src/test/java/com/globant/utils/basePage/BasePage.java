package com.globant.utils.basePage;

import com.globant.pages.CartPage;
import com.globant.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    @FindBy(id = "react-burger-menu-btn")
    protected WebElement showBurgerMenuBtn;

    @FindBy(css = ".shopping_cart_link")
    protected WebElement goToCartHeaderLink;

    @FindBy(id = "logout_sidebar_link")
    protected WebElement burgerMenuLogoutLink;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(this.driver, Duration.ofSeconds(10));
        PageFactory.initElements(this.driver, this);
    }

    protected void waitElementIsDisplayed(WebElement webElement) {
        this.wait.until(ExpectedConditions.visibilityOf(webElement));
    }

    protected void waitElementsAreDisplayed(List<WebElement> webElementsList) {
        this.wait.until(ExpectedConditions.visibilityOfAllElements(webElementsList));
    }

    protected void waitElementBeClickable(WebElement webElement) {
        this.wait.until(ExpectedConditions.elementToBeClickable(webElement));
    }

    protected boolean verifyUrlContains(String string) throws NullPointerException {
        String url = this.driver.getCurrentUrl();
        if (url == null || url.isEmpty())
            throw new NullPointerException("BasePage - VerifyUrlContains: Unable to obtain URL");
        else if (string == null)
            throw new NullPointerException("BasePage - VerifyUrlContains: Expression to evaluate is null");

        return url.toLowerCase().contains(string.toLowerCase());
    }

    public boolean isCartEmpty() {
        this.waitElementIsDisplayed(this.goToCartHeaderLink);
        // If the cart is empty, it will not have text.
        return this.goToCartHeaderLink.getText().isEmpty();
    }

    public CartPage goToCart() {
        this.waitElementIsDisplayed(this.goToCartHeaderLink);
        this.goToCartHeaderLink.click();

        if (this.verifyUrlContains("cart"))
            return new CartPage(this.driver);
        return null;
    }

    public void clickOpenBurgerMenu() {
        this.waitElementIsDisplayed(this.showBurgerMenuBtn);
        this.showBurgerMenuBtn.click();
    }

    public LoginPage logout() {
        this.clickOpenBurgerMenu();
        this.waitElementIsDisplayed(this.burgerMenuLogoutLink);
        this.burgerMenuLogoutLink.click();
        return new LoginPage(this.driver);
    }
}
