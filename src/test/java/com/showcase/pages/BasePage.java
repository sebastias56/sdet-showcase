package com.showcase.pages;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage {

    protected final WebDriver driver;

    private final WebDriverWait wait;

    protected BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    protected void click(By locator) {
        findClickable(locator).click();
    }

    protected void type(By locator, String text) {
        findVisible(locator).sendKeys(text);
    }

    protected String getText(By locator) {
        return findVisible(locator).getText();
    }

    protected boolean isDisplayed(By locator) {
        return findVisible(locator).isDisplayed();
    }

    private WebElement findVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    private WebElement findClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }
}