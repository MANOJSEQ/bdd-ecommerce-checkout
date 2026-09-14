package com.manoj.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InventoryPage extends BasePage {

    private static final By PAGE_TITLE =
            By.cssSelector("[data-test='title']");

    private static final By CART_LINK =
            By.cssSelector("[data-test='shopping-cart-link']");

    private static final By CART_BADGE =
            By.cssSelector("[data-test='shopping-cart-badge']");

    public InventoryPage(WebDriver driver) {
        super(driver);
    }

    private By getProductButton(String productName) {
        String xpath = String.format(
                "//div[@data-test='inventory-item']" +
                "[.//div[@data-test='inventory-item-name' " +
                "and normalize-space()='%s']]//button",
                productName
        );

        return By.xpath(xpath);
    }

    public void addProductToCart(String productName) {
        By productButton = getProductButton(productName);

        click(productButton);
        waitUntilTextIs(productButton, "Remove");
    }

    public void removeProductFromCart(String productName) {
        By productButton = getProductButton(productName);

        click(productButton);
        waitUntilTextIs(productButton, "Add to cart");
    }

    public String getProductButtonText(String productName) {
        return getText(getProductButton(productName));
    }

    public String getCartItemCount() {
        return getText(CART_BADGE);
    }

    public void openCart() {
        click(CART_LINK);
        waitUntilUrlContains("cart.html");
    }

    public boolean isInventoryPageDisplayed() {
        return isDisplayed(PAGE_TITLE)
                && getText(PAGE_TITLE).equalsIgnoreCase("Products");
    }
}