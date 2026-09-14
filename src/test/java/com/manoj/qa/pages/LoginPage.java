package com.manoj.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    private static final String LOGIN_URL =
            "https://www.saucedemo.com/";

    private static final By USERNAME_INPUT =
            By.id("user-name");

    private static final By PASSWORD_INPUT =
            By.id("password");

    private static final By LOGIN_BUTTON =
            By.id("login-button");

    private static final By ERROR_MESSAGE =
            By.cssSelector("[data-test='error']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.get(LOGIN_URL);
    }

    public void loginAs(String username, String password) {
        type(USERNAME_INPUT, username);
        type(PASSWORD_INPUT, password);
        click(LOGIN_BUTTON);
    }

    public String getErrorMessage() {
        return getText(ERROR_MESSAGE);
    }

    public boolean isLoginPageDisplayed() {
        return isDisplayed(LOGIN_BUTTON);
    }
}