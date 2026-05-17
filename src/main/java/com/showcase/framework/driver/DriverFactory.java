package com.showcase.framework.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public final class DriverFactory {

  private DriverFactory() {}

  public static WebDriver createDriver(BrowserType browserType) {
    if (browserType == null) {
      throw new IllegalArgumentException("Browser type must not be null");
    }

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
