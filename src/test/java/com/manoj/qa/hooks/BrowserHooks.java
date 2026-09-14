package com.manoj.qa.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class BrowserHooks {

    @Before
    public void startBrowser() {
        DriverManager.startDriver();
    }

    @After
    public void closeBrowser(Scenario scenario) {
        WebDriver driver = DriverManager.getDriver();

        try {
            if (scenario.isFailed()) {
                byte[] screenshot =
                        ((TakesScreenshot) driver)
                                .getScreenshotAs(OutputType.BYTES);

                scenario.attach(
                        screenshot,
                        "image/png",
                        "Failure screenshot"
                );
            }
        } finally {
            DriverManager.quitDriver();
        }
    }
}