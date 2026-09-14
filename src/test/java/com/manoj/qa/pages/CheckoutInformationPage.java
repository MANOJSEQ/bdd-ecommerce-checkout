package com.manoj.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutInformationPage extends BasePage {

    private static final By PAGE_TITLE =
            By.cssSelector("[data-test='title']");

    private static final By FIRST_NAME_INPUT =
            By.id("first-name");

    private static final By LAST_NAME_INPUT =
            By.id("last-name");

    private static final By POSTAL_CODE_INPUT =
            By.id("postal-code");

    private static final By CONTINUE_BUTTON =
            By.id("continue");

    private static final By ERROR_MESSAGE =
            By.cssSelector("[data-test='error']");

    public CheckoutInformationPage(WebDriver driver) {
        super(driver);
    }

    public void enterCustomerInformation(
            String firstName,
            String lastName,
            String postalCode
    ) {
        type(FIRST_NAME_INPUT, firstName);
        type(LAST_NAME_INPUT, lastName);
        type(POSTAL_CODE_INPUT, postalCode);
    }

    public void continueCheckout() {
        click(CONTINUE_BUTTON);
    }

    public String getErrorMessage() {
        return getText(ERROR_MESSAGE);
    }

    public boolean isCheckoutInformationPageDisplayed() {
        return isDisplayed(PAGE_TITLE)
                && getText(PAGE_TITLE)
                        .equalsIgnoreCase("Checkout: Your Information");
    }
}