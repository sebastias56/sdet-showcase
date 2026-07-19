package com.showcase.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

  private static final String URL = "https://the-internet.herokuapp.com/login";

  private final By usernameInput = By.id("username");
  private final By passwordInput = By.id("password");
  private final By loginButton = By.cssSelector("button[type='submit']");
  private final By flashMessage = By.id("flash");

  public LoginPage(WebDriver driver) {
    super(driver);
  }

  public void open() {
    driver.get(URL);
  }

  public void enterUsername(String username) {
    type(usernameInput, username);
  }

  public void enterPassword(String password) {
    type(passwordInput, password);
  }

  public void submit() {
    click(loginButton);
  }

  public SecureAreaPage login(String username, String password) {
    enterUsername(username);
    enterPassword(password);
    submit();
    return new SecureAreaPage(driver);
  }

  public String getFlashMessage() {
    return getText(flashMessage);
  }

  public boolean isLoaded() {
    return isDisplayed(usernameInput);
  }
}
