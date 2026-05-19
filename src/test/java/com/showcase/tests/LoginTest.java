package com.showcase.tests;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.showcase.pages.LoginPage;
import org.junit.jupiter.api.Test;

class LoginTest extends BaseTest {

  @Test
  void shouldLoginWithValidCredentials() {
    LoginPage loginPage = new LoginPage(driver);

    loginPage.open();

    assertTrue(loginPage.isLoaded(), "Login page should be loaded");

    loginPage.enterUsername("tomsmith");
    loginPage.enterPassword("SuperSecretPassword!");
    loginPage.submit();

    assertTrue(
        loginPage.getFlashMessage().contains("You logged into a secure area!"),
        "Success login message should be displayed");
  }
}
