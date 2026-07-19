package com.showcase.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SecureAreaPage extends BasePage {

  private final By logoutButton = By.cssSelector("a.button.secondary.radius");
  private final By flashMessage = By.id("flash");

  public SecureAreaPage(WebDriver driver) {
    super(driver);
  }

  public boolean isLoaded() {
    return isDisplayed(logoutButton);
  }

  public String getFlashMessage() {
    return getText(flashMessage);
  }

  public LoginPage logout() {
    click(logoutButton);
    return new LoginPage(driver);
  }
}
