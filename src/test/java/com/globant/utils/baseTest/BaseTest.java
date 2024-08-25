package com.globant.utils.baseTest;

import com.globant.pages.LoginPage;
import com.globant.utils.DriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.*;

public class BaseTest {
    protected DriverManager driverManager;
    protected static Logger logger = LogManager.getLogger(BaseTest.class);

    protected void navigateTo(String url) throws IllegalArgumentException {
        if (url == null || url.isEmpty())
            throw new IllegalArgumentException("BaseTest - NavigateTo: The url string is empty!");
        this.driverManager.goToUrl(url);
    }

    protected LoginPage loadFirstPage() {
        if (this.driverManager == null || this.driverManager.getDriver() == null)
            throw new NullPointerException("BaseTest - LoadFirstPage: Invalid driver");
        return new LoginPage(this.driverManager.getDriver());
    }

    @BeforeTest()
    @Parameters({"browser", "url"})
    protected void setupDriver(String browser, String url) throws NullPointerException, IllegalArgumentException {
        if (browser == null || url == null)
            throw new NullPointerException("BaseTest - SetupDriver: Parameters are null");
        else if (url.isEmpty())
            throw new IllegalArgumentException("BaseTest - SetupDriver: Empty URL");

        this.driverManager = new DriverManager(browser);
        this.driverManager.maximizeWindow();
        this.navigateTo(url);
    }

    @AfterTest()
    protected void closeDriver() {
        this.driverManager.close();
    }
}
