package com.globant.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverManager {
    private final WebDriver driver;

    public DriverManager(String browser) {
        switch (browser.toLowerCase()) {
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                this.driver = new FirefoxDriver();
                break;
            case "chrome":
            default:
                WebDriverManager.chromedriver().setup();
                this.driver = new ChromeDriver();
                break;
        }
    }

    public WebDriver getDriver() {
        return this.driver;
    }

    public void goToUrl(String url) throws IllegalArgumentException {
        if (url == null || url.isEmpty())
            throw new IllegalArgumentException("DriverManger: Try to get empty url");
        this.driver.get(url);
    }

    public void maximizeWindow() {
        this.driver.manage().window().maximize();
    }

    public void close() {
        this.driver.close();
    }
}