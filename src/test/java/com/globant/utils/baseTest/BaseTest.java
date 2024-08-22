package com.globant.utils.baseTest;

import com.globant.utils.DriverManager;
import com.globant.utils.basePage.BasePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.lang.reflect.InvocationTargetException;

public class BaseTest {
    protected DriverManager driverManager;
    protected static Logger logger = LogManager.getLogger(BaseTest.class);

    public void navigateTo(String url) {
        if (url == null || url.isEmpty()) {
            BaseTest.logger.error("BaseTest - NavigateTo: The url string is empty!");
        }
        this.driverManager.goToUrl(url);
    }

    /**
     * Use generic to avoid changes on the first page
     */
    public <T extends BasePage> T loadPageByClass(Class<T> pageClass) {
        Class<WebDriver> expectedParameterClass = WebDriver.class;
        T page = null;

        try {
            page = pageClass.getDeclaredConstructor(expectedParameterClass).newInstance(this.driverManager.getDriver());
        } catch (NoSuchElementException | NoSuchMethodException e) {
            BaseTest.logger.error("BaseTest - LoadAndGetFirstPage: Error getting the page constructor");
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException e) {
            BaseTest.logger.error("BaseTest - LoadAndGetFirstPage: Error generating the new instance");
        }
        return page;
    }

    @BeforeTest()
    @Parameters({"browser", "url"})
    public void setupDriver(String browser, String url) {
        this.driverManager = new DriverManager(browser);
        this.driverManager.maximizeWindow();
        this.navigateTo(url);
    }

    @AfterTest()
    public void closeDriver() {
        this.driverManager.close();
    }
}
