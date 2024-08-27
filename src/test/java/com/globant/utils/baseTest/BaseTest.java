package com.globant.utils.baseTest;

import com.globant.pages.InventoryPage;
import com.globant.pages.LoginPage;
import com.globant.utils.DriverManager;
import com.globant.utils.persistence.DataPropertiesProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

public class BaseTest {
    protected DriverManager driverManager;
    protected static Logger logger = LogManager.getLogger(BaseTest.class);

    protected void navigateTo(String url) throws IllegalArgumentException {
        if (url == null || url.isEmpty())
            throw new IllegalArgumentException("BaseTest - NavigateTo: The url string is empty!");
        this.driverManager.goToUrl(url);
    }

    protected LoginPage loadFirstPage() throws NullPointerException {
        if (this.driverManager == null || this.driverManager.getDriver() == null)
            throw new NullPointerException("BaseTest - LoadFirstPage: Invalid driver");
        return new LoginPage(this.driverManager.getDriver());
    }

    protected InventoryPage loadInventoryPage() {
        return new InventoryPage(this.driverManager.getDriver());
    }

    @BeforeTest()
    @Parameters({"browser", "url"})
    protected void setupDriver(String browser, String url) throws NullPointerException, IllegalArgumentException {
        if (browser == null || url == null)
            throw new NullPointerException("BaseTest - SetupDriver: Parameters are null");
        else if (url.isEmpty())
            throw new IllegalArgumentException("BaseTest - SetupDriver: Empty URL");

        DataPropertiesProvider.loadProperties();
        this.driverManager = new DriverManager(browser);
        this.driverManager.maximizeWindow();
        this.navigateTo(url);
    }

    @BeforeMethod()
    @Parameters({"username", "password"})
    public InventoryPage verifySuccessfulLogin(String username, String password) {
        LoginPage loginPage = this.loadFirstPage();
        Assert.assertTrue(loginPage.isFormDisplayed());
        loginPage.setUsername(username);
        loginPage.setPassword(password);
        return loginPage.selectLoginOption();
    }

    @AfterTest()
    protected void closeDriver() {
        this.driverManager.close();
    }
}
