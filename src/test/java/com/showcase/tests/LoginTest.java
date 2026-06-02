package com.showcase.tests;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.showcase.pages.LoginPage;
import java.util.stream.Stream;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class LoginTest extends BaseTest {

  private static final String VALID_USERNAME = "tomsmith";
  private static final String VALID_PASSWORD = "SuperSecretPassword!";
  private static final String INVALID_USERNAME = "invalid-user";
  private static final String INVALID_PASSWORD = "invalid-password";
  private static final String SUCCESS_LOGIN_MESSAGE = "You logged into a secure area!";
  private static final String INVALID_USERNAME_MESSAGE = "Your username is invalid!";
  private static final String INVALID_PASSWORD_MESSAGE = "Your password is invalid!";

  @Test
  @Tag("smoke")
  @Tag("login")
  void shouldLoginWithValidCredentials() {
    LoginPage loginPage = openLoginPage();

    loginPage.enterUsername(VALID_USERNAME);
    loginPage.enterPassword(VALID_PASSWORD);
    loginPage.submit();

    assertTrue(
        loginPage.getFlashMessage().contains(SUCCESS_LOGIN_MESSAGE),
        "Success login message should be displayed");
  }

  @ParameterizedTest(name = "username={0}, expectedMessage={2}")
  @MethodSource("invalidLoginScenarios")
  @Tag("regression")
  @Tag("login")
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

  private static Stream<Arguments> invalidLoginScenarios() {
    return Stream.of(
        Arguments.of(INVALID_USERNAME, VALID_PASSWORD, INVALID_USERNAME_MESSAGE),
        Arguments.of(VALID_USERNAME, INVALID_PASSWORD, INVALID_PASSWORD_MESSAGE));
  }

  private LoginPage openLoginPage() {
    LoginPage loginPage = new LoginPage(driver);
    loginPage.open();
    assertTrue(loginPage.isLoaded(), "Login page should be loaded");
    return loginPage;
  }
}
