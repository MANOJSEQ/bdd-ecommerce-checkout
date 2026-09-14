package com.manoj.qa.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage {

    protected final WebDriver driver;
    private final WebDriverWait wait;

    protected BasePage(WebDriver driver) {
        this.driver = driver;

        this.wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(10)
        );
    }

    protected void click(By locator) {
        WebElement element = wait.until(
                ExpectedConditions.elementToBeClickable(locator)
        );

        element.click();
    }

    protected void type(By locator, String text) {
        WebElement element = wait.until(
                ExpectedConditions.visibilityOfElementLocated(locator)
        );

        element.clear();
        element.sendKeys(text);
    }

    protected String getText(By locator) {
        WebElement element = wait.until(
                ExpectedConditions.visibilityOfElementLocated(locator)
        );

        return element.getText();
    }

    protected boolean isDisplayed(By locator) {
        try {
            return wait.until(
                    ExpectedConditions.visibilityOfElementLocated(locator)
            ).isDisplayed();
        } catch (Exception exception) {
            return false;
        }
    }

    protected void waitUntilTextIs(
            By locator,
            String expectedText
    ) {
        wait.until(
                ExpectedConditions.textToBe(
                        locator,
                        expectedText
                )
        );
    }

    protected void waitUntilInvisible(By locator) {
        wait.until(
                ExpectedConditions
                        .invisibilityOfElementLocated(locator)
        );
    }

    protected void waitUntilUrlContains(String urlPart) {
        wait.until(
                ExpectedConditions.urlContains(urlPart)
        );
    }

    protected String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}