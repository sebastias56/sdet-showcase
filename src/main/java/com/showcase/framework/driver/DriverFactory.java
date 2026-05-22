package com.showcase.framework.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class DriverFactory {

  private static final Logger LOGGER = LoggerFactory.getLogger(DriverFactory.class);

  private DriverFactory() {}

  public static WebDriver createDriver(BrowserType browserType) {
    if (browserType == null) {
      throw new IllegalArgumentException("Browser type must not be null");
    }

    LOGGER.info("Creating WebDriver for browser: {}", browserType);

    return switch (browserType) {
      case CHROME -> {
        WebDriverManager.chromedriver().setup();
        yield new ChromeDriver();
      }
      case FIREFOX -> {
        WebDriverManager.firefoxdriver().setup();
        yield new FirefoxDriver();
      }
    };
  }
}
