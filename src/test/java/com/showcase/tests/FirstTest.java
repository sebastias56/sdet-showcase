package com.showcase.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class FirstTest {

  @Test
  void googleHomePageCheck() {
    WebDriverManager.firefoxdriver().setup();

    WebDriver webDriver = new FirefoxDriver();
    try {
      webDriver.get("https://the-internet.herokuapp.com/");

      assertTrue(
              webDriver.getTitle().contains("The Internet"),
              "Page title should contain 'The Internet'"
      );
    } finally {
      webDriver.quit();
    }
  }
}
