package com.showcase.tests;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.showcase.pages.LoginPage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

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

  @ParameterizedTest(name = "username={0}, expectedMessage={2}")
  @CsvSource({
    "invalid-user, SuperSecretPassword!, Your username is invalid!",
    "tomsmith, invalid-password, Your password is invalid!"
  })
  void shouldShowErrorMessageWithInvalidCredentials(
      String username, String password, String expectedMessage) {
    LoginPage loginPage = openLoginPage();

    loginPage.enterUsername(username);
    loginPage.enterPassword(password);
    loginPage.submit();

    assertTrue(
        loginPage.getFlashMessage().contains(expectedMessage),
        "Expected login error message should be displayed");
  }

  private LoginPage openLoginPage() {
    LoginPage loginPage = new LoginPage(driver);
    loginPage.open();
    assertTrue(loginPage.isLoaded(), "Login page should be loaded");
    return loginPage;
  }
}
