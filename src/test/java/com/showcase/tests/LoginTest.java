package com.showcase.tests;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.showcase.pages.LoginPage;
import org.junit.jupiter.api.Test;

class LoginTest extends BaseTest {

  @Test
  void shouldLoginWithValidCredentials() {
    LoginPage loginPage = openLoginPage();

    loginPage.enterUsername("tomsmith");
    loginPage.enterPassword("SuperSecretPassword!");
    loginPage.submit();

    assertTrue(
        loginPage.getFlashMessage().contains("You logged into a secure area!"),
        "Success login message should be displayed");
  }

  @Test
  void shouldShowErrorMessageWithInvalidUsername() {
    LoginPage loginPage = openLoginPage();

    loginPage.enterUsername("invalid-user");
    loginPage.enterPassword("SuperSecretPassword!");
    loginPage.submit();

    assertTrue(
        loginPage.getFlashMessage().contains("Your username is invalid!"),
        "Invalid username error message should be displayed");
  }

  @Test
  void shouldShowErrorMessageWithInvalidPassword() {
    LoginPage loginPage = openLoginPage();

    loginPage.enterUsername("tomsmith");
    loginPage.enterPassword("invalid-password");
    loginPage.submit();

    assertTrue(
        loginPage.getFlashMessage().contains("Your password is invalid!"),
        "Invalid password error message should be displayed");
  }

  private LoginPage openLoginPage() {
    LoginPage loginPage = new LoginPage(driver);
    loginPage.open();
    assertTrue(loginPage.isLoaded(), "Login page should be loaded");
    return loginPage;
  }
}
