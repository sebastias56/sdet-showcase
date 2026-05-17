package com.showcase.tests;

import com.showcase.framework.driver.BrowserType;
import com.showcase.framework.driver.DriverFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;

abstract class BaseTest {

  protected WebDriver driver;

  @BeforeEach
  void setUp() {
    driver = DriverFactory.createDriver(BrowserType.FIREFOX);
  }

  @AfterEach
  void tearDown() {
    if (driver != null) {
      driver.quit();
    }
  }
}
