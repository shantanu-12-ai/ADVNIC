package com.jisasoftech.ADVNIC.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.edge.EdgeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;

public class WebDriverManagerUtil {
    private static WebDriver driver;

    /**
     * Initializes the WebDriver based on the browser type.
     * The browser type can be passed via system property or config file.
     */
    public static WebDriver initializeDriver() {
        String browser = System.getProperty("browser", "chrome"); // Default to Chrome if not specified

        switch (browser.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;
            default:
                throw new IllegalArgumentException("Unsupported browser: " + browser);
        }

        // Basic WebDriver configurations
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        return driver;
    }

    /**
     * Returns the initialized WebDriver instance.
     */
    public WebDriver getDriver() {
        if (driver == null) {
            throw new IllegalStateException("Driver is not initialized. Call initializeDriver() first.");
        }
        return driver;
    }

    /**
     * Quits the WebDriver and cleans up resources.
     */
    public void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
