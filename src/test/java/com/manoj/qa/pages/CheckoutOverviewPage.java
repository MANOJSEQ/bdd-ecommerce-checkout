package com.manoj.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutOverviewPage extends BasePage {

    private static final By PAGE_TITLE =
            By.cssSelector("[data-test='title']");

    private static final By FINISH_BUTTON =
            By.id("finish");

    private static final By ORDER_TOTAL =
            By.cssSelector("[data-test='total-label']");

    public CheckoutOverviewPage(WebDriver driver) {
        super(driver);
    }

    private By getProduct(String productName) {
        String xpath = String.format(
                "//div[@data-test='inventory-item-name' " +
                "and normalize-space()='%s']",
                productName
        );

        return By.xpath(xpath);
    }

    public boolean containsProduct(String productName) {
        return isDisplayed(getProduct(productName));
    }

    public String getOrderTotal() {
        return getText(ORDER_TOTAL);
    }

    public void finishCheckout() {
        click(FINISH_BUTTON);
    }

    public boolean isCheckoutOverviewPageDisplayed() {
        return isDisplayed(PAGE_TITLE)
                && getText(PAGE_TITLE)
                        .equalsIgnoreCase("Checkout: Overview");
    }
}