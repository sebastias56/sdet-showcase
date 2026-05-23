package com.showcase.framework.driver;

import com.showcase.framework.config.ConfigReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class DriverFactory {

  private static final Logger LOGGER = LoggerFactory.getLogger(DriverFactory.class);

  private DriverFactory() {}

  public static WebDriver createDriver(BrowserType browserType) {
    if (browserType == null) {
      throw new IllegalArgumentException("Browser type must not be null");
    }

    boolean headless = ConfigReader.getHeadless();

    LOGGER.info("Creating WebDriver for browser: {}", browserType);
    LOGGER.info("Headless mode: {}", headless);

    return switch (browserType) {
      case CHROME -> {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        if (headless) {
          options.addArguments(
              "--headless=new",
              "--no-sandbox",
              "--disable-dev-shm-usage",
              "--window-size=1920,1080");
        }
        yield new ChromeDriver(options);
      }
      case FIREFOX -> {
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = new FirefoxOptions();
        if (headless) {
          options.addArguments("-headless");
        }
        yield new FirefoxDriver(options);
      }
    };
  }
}
