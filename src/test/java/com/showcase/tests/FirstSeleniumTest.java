package com.showcase.tests;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class FirstSeleniumTest extends BaseTest {

    @Test
    void shouldDisplayCorrectTitleOnPracticeHomePage() {
        driver.get("https://the-internet.herokuapp.com/");

        assertTrue(
            driver.getTitle().contains("The Internet"),
            "Page title should contain 'The Internet'");
    }
}
