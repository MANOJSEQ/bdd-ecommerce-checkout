package com.manoj.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutCompletePage extends BasePage {

    private static final By PAGE_TITLE =
            By.cssSelector("[data-test='title']");

    private static final By CONFIRMATION_MESSAGE =
            By.cssSelector("[data-test='complete-header']");

    private static final By CONFIRMATION_DESCRIPTION =
            By.cssSelector("[data-test='complete-text']");

    private static final By BACK_HOME_BUTTON =
            By.id("back-to-products");

    public CheckoutCompletePage(WebDriver driver) {
        super(driver);
    }

    public String getConfirmationMessage() {
        return getText(CONFIRMATION_MESSAGE);
    }

    public String getConfirmationDescription() {
        return getText(CONFIRMATION_DESCRIPTION);
    }

    public boolean isOrderComplete() {
        return isDisplayed(CONFIRMATION_MESSAGE)
                && getConfirmationMessage()
                        .equalsIgnoreCase("Thank you for your order!");
    }

    public void returnToProducts() {
        click(BACK_HOME_BUTTON);
    }

    public boolean isCheckoutCompletePageDisplayed() {
        return isDisplayed(PAGE_TITLE)
                && getText(PAGE_TITLE)
                        .equalsIgnoreCase("Checkout: Complete!");
    }
}