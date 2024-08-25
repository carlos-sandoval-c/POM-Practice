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

    public WebDriver getDriver() {
        return this.driver;
    }

    public WebDriverWait getWait() {
        return this.wait;
    }

    public void waitElementIsDisplayed(WebElement webElement) {
        this.wait.until(ExpectedConditions.visibilityOf(webElement));
    }

    public void waitElementIsDisplayed(List<WebElement> webElementsList) {
        this.wait.until(ExpectedConditions.visibilityOfAllElements(webElementsList));
    }

    public void waitElementBeClickable(WebElement webElement) {
        this.wait.until(ExpectedConditions.elementToBeClickable(webElement));
    }
}
