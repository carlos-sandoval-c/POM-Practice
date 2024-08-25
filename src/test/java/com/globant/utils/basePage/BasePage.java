package com.globant.utils.basePage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

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
}
