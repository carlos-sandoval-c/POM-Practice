package com.globant.pages;

import com.globant.utils.RandInt;
import com.globant.utils.basePage.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class InventoryPage extends BasePage {
    @FindBy(xpath = "//button[contains(@class, 'btn_inventory')][contains(@name, 'add')]")
    private List<WebElement> productsAddToCartBtnList;

    public InventoryPage(WebDriver driver) {
        super(driver);
    }

    public void addToCartRandomProduct() {
        if (!this.productsAddToCartBtnList.isEmpty())
            super.waitElementsAreDisplayed(this.productsAddToCartBtnList);

        int indexProduct = RandInt.getRandomIndex(this.productsAddToCartBtnList.size());
        if (indexProduct < 0 || indexProduct >= this.productsAddToCartBtnList.size())
            return;

        this.productsAddToCartBtnList.get(indexProduct).click();
    }

    public void addToCartRandomProducts(int totalProducts) {
        if (!this.productsAddToCartBtnList.isEmpty())
            super.waitElementsAreDisplayed(this.productsAddToCartBtnList);

        int[] indexProductsArr = RandInt.getRandomIndex(this.productsAddToCartBtnList.size(), totalProducts);
        if (indexProductsArr == null)
            return;

        for (int index : indexProductsArr) {
            if (index < 0 || index >= this.productsAddToCartBtnList.size())
                return;
            this.productsAddToCartBtnList.get(index).click();
        }
    }
}
