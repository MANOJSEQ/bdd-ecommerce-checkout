package com.manoj.qa.hooks;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public final class DriverManager {

    private static final ThreadLocal<WebDriver> DRIVER =
            new ThreadLocal<>();

    private DriverManager() {
        // Prevents this utility class from being instantiated.
    }

    public static void startDriver() {
        if (DRIVER.get() != null) {
            return;
        }

        ChromeOptions options = new ChromeOptions();

        options.addArguments(
                "--disable-notifications",
                "--disable-save-password-bubble",
                "--disable-features=PasswordLeakDetection"
        );

        Map<String, Object> preferences = new HashMap<>();

        preferences.put(
                "credentials_enable_service",
                false
        );

        preferences.put(
                "profile.password_manager_enabled",
                false
        );

        preferences.put(
                "profile.password_manager_leak_detection",
                false
        );

        options.setExperimentalOption(
                "prefs",
                preferences
        );

        boolean headless = Boolean.parseBoolean(
                System.getProperty("headless", "false")
        );

        if (headless) {
            options.addArguments(
                    "--headless=new",
                    "--window-size=1920,1080",
                    "--no-sandbox",
                    "--disable-dev-shm-usage"
            );
        }

        WebDriver driver = new ChromeDriver(options);

        driver.manage()
              .timeouts()
              .pageLoadTimeout(Duration.ofSeconds(30));

        if (!headless) {
            driver.manage().window().maximize();
        }

        DRIVER.set(driver);
    }

    public static WebDriver getDriver() {
        WebDriver driver = DRIVER.get();

        if (driver == null) {
            throw new IllegalStateException(
                    "WebDriver has not been started."
            );
        }

        return driver;
    }

    public static void quitDriver() {
        WebDriver driver = DRIVER.get();

        if (driver != null) {
            try {
                driver.quit();
            } finally {
                DRIVER.remove();
            }
        }
    }
}