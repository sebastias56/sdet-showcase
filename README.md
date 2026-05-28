# SDET Showcase

A Java-based web test automation framework built as a learning and portfolio project for QA Automation / SDET roles.

The goal of this repository is to show how a maintainable Selenium framework can be designed step by step, using simple and defendable decisions instead of unnecessary abstractions.

## Tech Stack

- Java 17
- Maven
- JUnit 5
- Selenium WebDriver
- WebDriverManager
- SLF4J
- Logback
- GitHub Actions

## Current Features

- Cross-browser execution with Chrome and Firefox
- Headless mode support
- Page Object Model
- Explicit waits through a shared `BasePage`
- Screenshots on test failure
- Basic logging with SLF4J and Logback
- GitHub Actions CI
- CI browser matrix for Chrome and Firefox
- Screenshot artifacts uploaded on CI failure

## Project Structure

```text
src/main/java/com/showcase/framework
```

Reusable framework code, including browser configuration, browser selection, and driver creation.

```text
src/test/java/com/showcase/pages
```

Page Objects used by the tests. This includes `HomePage`, `LoginPage`, and shared page behavior in `BasePage`.

```text
src/test/java/com/showcase/tests
```

JUnit 5 test classes and test lifecycle setup through `BaseTest`.

```text
src/test/java/com/showcase/framework/utils
```

Testing utilities used by the framework, such as `ScreenshotUtils`.

```text
.github/workflows
```

GitHub Actions workflow configuration for running the test suite in CI.

## How To Run

Run the full suite with the default browser:

```bash
mvn clean test
```

Run with Chrome:

```bash
mvn clean test -Dbrowser=chrome
```

Run with Firefox:

```bash
mvn clean test -Dbrowser=firefox
```

Run Chrome in headless mode:

```bash
mvn clean test -Dbrowser=chrome -Dheadless=true
```

Run Firefox in headless mode:

```bash
mvn clean test -Dbrowser=firefox -Dheadless=true
```

Supported browser values:

```text
chrome
firefox
```

Supported headless values:

```text
true
false
```

## CI

GitHub Actions runs the test suite automatically on:

- push to `main`
- pull request to `main`

The CI workflow runs the suite in headless mode using a browser matrix:

- Chrome
- Firefox

## Screenshots

When a test fails, the framework captures a screenshot and stores it in:

```text
target/screenshots/
```

In GitHub Actions, screenshots are uploaded as workflow artifacts when the CI job fails.

Artifact names include the browser name:

```text
screenshots-chrome
screenshots-firefox
```

## Design Decisions

### JUnit 5

JUnit 5 was chosen because it is modern, widely used in Java projects, and integrates cleanly with Maven Surefire.

### Page Object Model

Page Object Model keeps test logic separate from page interaction details. Tests describe the behavior being validated, while Page Objects handle locators and browser interactions.

### Explicit Waits

The framework uses explicit waits instead of implicit waits to make synchronization more intentional and easier to reason about. Wait behavior is centralized in `BasePage`.

### Configurable Headless Mode

Headless mode is controlled through a system property:

```bash
-Dheadless=true
```

This keeps local execution visible by default while allowing CI to run reliably without a desktop UI.

## Roadmap

Possible future improvements:

- Test data management
- Negative login scenarios
- Basic reporting
- GitHub Actions scheduled runs
- Parallel execution
- Selenium Grid or remote execution

## Author

Nahuel Muñiz  
QA Automation / SDET
