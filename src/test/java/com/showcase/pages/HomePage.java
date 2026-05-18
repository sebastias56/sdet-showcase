package com.showcase.pages;

import org.openqa.selenium.WebDriver;

public class HomePage {

  private static final String URL = "https://the-internet.herokuapp.com/";

  private final WebDriver driver;

  public HomePage(WebDriver driver) {
    this.driver = driver;
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
