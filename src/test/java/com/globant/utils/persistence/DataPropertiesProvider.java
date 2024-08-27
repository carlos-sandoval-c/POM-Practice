package com.globant.utils.persistence;

import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DataPropertiesProvider {
    private static final String FILE_PATH = "src/test/resources/testData.properties";
    private static final Properties properties = new Properties();

    public static void loadProperties() {
        try {
            File dataSource = new File(DataPropertiesProvider.FILE_PATH);
            FileInputStream dataSourceInputStream = new FileInputStream(dataSource);
            DataPropertiesProvider.properties.load(dataSourceInputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected static String getProperty(String propertyName) {
        return DataPropertiesProvider.properties.getProperty(propertyName);
    }

    @DataProvider(name = "purchase-data")
    public Object[][] getPurchaseData() {
        String firstNameList = DataPropertiesProvider.getProperty("firstName");
        String lastNameList = DataPropertiesProvider.getProperty("lastName");
        String zipCodeList = DataPropertiesProvider.getProperty("zipCode");

        return new Object[][]{{firstNameList, lastNameList, zipCodeList}};
    }

    @DataProvider(name = "total-products")
    public Object[][] getTotalProducts() {
        int value = Integer.parseInt(DataPropertiesProvider.getProperty("totalProductsToAdd"));
        return new Object[][]{{value}};
    }
}
