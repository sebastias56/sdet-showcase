package com.showcase.pages;

import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {

  private static final String URL = "https://the-internet.herokuapp.com/";

  public HomePage(WebDriver driver) {
    super(driver);
  }

  public void open() {
    driver.get(URL);
  }

  public String getTitle() {
    return driver.getTitle();
  }

  public boolean isLoaded() {
    return getTitle().contains("The Internet");
  }
}
