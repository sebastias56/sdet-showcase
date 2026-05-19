package com.showcase.tests;

import com.showcase.framework.config.ConfigReader;
import com.showcase.framework.driver.DriverFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;

abstract class BaseTest {

  protected WebDriver driver;

  @BeforeEach
  void setUp() {
    driver = DriverFactory.createDriver(ConfigReader.getBrowserType());
  }

  @AfterEach
  void tearDown() {
    if (driver != null) {
      driver.quit();
    }
  }
}
