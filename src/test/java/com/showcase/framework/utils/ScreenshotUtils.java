package com.showcase.framework.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public final class ScreenshotUtils {

    private static final Path SCREENSHOTS_DIR = Path.of("target", "screenshots");
    private static final DateTimeFormatter TIMESTAMP_FORMAT =
        DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss_SSS");

    private ScreenshotUtils() {
    }

    public static Path takeScreenshot(WebDriver driver, String testName) {
        if (!(driver instanceof TakesScreenshot takesScreenshot)) {
            throw new RuntimeException("Driver does not support screenshots");
        }

        try {
            Files.createDirectories(SCREENSHOTS_DIR);

            String safeTestName = testName.replaceAll("[^a-zA-Z0-9-_]", "_");
            String timestamp = LocalDateTime.now().format(TIMESTAMP_FORMAT);
            Path screenshotPath = SCREENSHOTS_DIR.resolve(safeTestName + "_" + timestamp + ".png");

            byte[] screenshot = takesScreenshot.getScreenshotAs(OutputType.BYTES);
            Files.write(screenshotPath, screenshot);

            return screenshotPath;
        } catch (IOException | RuntimeException exception) {
            throw new RuntimeException("Failed to capture screenshot for test: " + testName, exception);
        }
    }
}
