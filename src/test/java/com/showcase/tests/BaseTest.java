package com.showcase.tests;

import com.showcase.framework.config.ConfigReader;
import com.showcase.framework.driver.DriverFactory;
import com.showcase.framework.utils.ScreenshotUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler;
import org.openqa.selenium.WebDriver;

abstract class BaseTest {

    protected WebDriver driver;

    @RegisterExtension
    private final TestExecutionExceptionHandler screenshotOnFailure = (context, throwable) -> {
        if (driver != null) {
            try {
                ScreenshotUtils.takeScreenshot(driver, context.getRequiredTestMethod().getName());
            } catch (RuntimeException screenshotException) {
                throwable.addSuppressed(screenshotException);
            }
        }

        throw throwable;
    };

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
