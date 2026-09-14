package com.manoj.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage extends BasePage {

    private static final By PAGE_TITLE =
            By.cssSelector("[data-test='title']");

    private static final By CHECKOUT_BUTTON =
            By.id("checkout");

    private static final By CART_ITEMS =
            By.cssSelector("[data-test='inventory-item']");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    private By getProduct(String productName) {
        String xpath = String.format(
                "//div[@data-test='inventory-item']" +
                "[.//div[@data-test='inventory-item-name' " +
                "and normalize-space()='%s']]",
                productName
        );

        return By.xpath(xpath);
    }

    private By getRemoveButton(String productName) {
        String xpath = String.format(
                "//div[@data-test='inventory-item']" +
                "[.//div[@data-test='inventory-item-name' " +
                "and normalize-space()='%s']]//button",
                productName
        );

        return By.xpath(xpath);
    }

    public boolean containsProduct(String productName) {
        return !driver.findElements(getProduct(productName)).isEmpty();
    }

    public void removeProduct(String productName) {
        click(getRemoveButton(productName));
    }

    public boolean isEmpty() {
        return driver.findElements(CART_ITEMS).isEmpty();
    }

    public void proceedToCheckout() {
        click(CHECKOUT_BUTTON);
    }

    public boolean isCartPageDisplayed() {
        return isDisplayed(PAGE_TITLE)
                && getText(PAGE_TITLE).equalsIgnoreCase("Your Cart");
    }
}