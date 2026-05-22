package com.showcase.tests;

import com.showcase.framework.config.ConfigReader;
import com.showcase.framework.driver.BrowserType;
import com.showcase.framework.driver.DriverFactory;
import com.showcase.framework.utils.ScreenshotUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

abstract class BaseTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseTest.class);

    protected WebDriver driver;

    @RegisterExtension
    private final TestExecutionExceptionHandler screenshotOnFailure = (context, throwable) -> {
        if (driver != null) {
            try {
                String testName = context.getRequiredTestMethod().getName();
                LOGGER.warn("Test failed. Capturing screenshot for: {}", testName);
                ScreenshotUtils.takeScreenshot(driver, testName);
            } catch (RuntimeException screenshotException) {
                throwable.addSuppressed(screenshotException);
            }
        }

        throw throwable;
    };

    @BeforeEach
    void setUp() {
        BrowserType browserType = ConfigReader.getBrowserType();
        LOGGER.info("Starting browser: {}", browserType);
        driver = DriverFactory.createDriver(browserType);
  }

  @AfterEach
  void tearDown() {
    if (driver != null) {
      LOGGER.info("Closing browser");
      driver.quit();
    }
  }
}
