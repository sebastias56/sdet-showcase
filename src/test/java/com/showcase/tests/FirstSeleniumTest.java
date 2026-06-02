package com.showcase.tests;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.showcase.pages.HomePage;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class FirstSeleniumTest extends BaseTest {

  @Test
  @Tag("smoke")
  @Tag("home")
  void shouldLoadPracticeHomePage() {
    HomePage homePage = new HomePage(driver);

    homePage.open();

    assertTrue(homePage.isLoaded(), "Home page should be loaded");
  }
}
