package com.showcase.framework.config;

import com.showcase.framework.driver.BrowserType;

public final class ConfigReader {

    private ConfigReader() {
    }

    public static BrowserType getBrowserType() {
        String browser = System.getProperty("browser", "firefox").trim().toLowerCase();

        return switch (browser) {
            case "chrome" -> BrowserType.CHROME;
            case "firefox" -> BrowserType.FIREFOX;
            default -> throw new IllegalArgumentException(
                "Unsupported browser: " + browser + ". Supported values: chrome, firefox");
        };
    }
}
