# SDET Showcase

A Java-based web test automation framework built as a learning and portfolio project for QA Automation / SDET roles.

The repository demonstrates how a Selenium framework can be designed incrementally using clear responsibilities, maintainable test structure, cross-browser execution, and automated CI workflows.

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
- Configurable headless execution
- Page Object Model
- Explicit waits through a shared `BasePage`
- Positive and negative login scenarios
- JUnit 5 parameterized tests
- Test classification with JUnit 5 tags
- Screenshots on test failure
- Basic logging with SLF4J and Logback
- Automatic and manual GitHub Actions workflows
- Chrome and Firefox CI matrix
- Nightly full-suite execution
- Surefire report artifacts
- Screenshot artifacts on CI failure

## Project Structure

```text
src/main/java/com/showcase/framework
```

Framework support code, including browser configuration, browser selection, and WebDriver creation.

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
src/test/resources
```

Test runtime configuration, including Logback console logging.

```text
.github/workflows
```

GitHub Actions workflows for automatic CI and manual test execution.

## Running Tests Locally

Run the full suite with the default browser:

```bash
mvn clean test
```

Run the full suite with Chrome:

```bash
mvn clean test -Dbrowser=chrome
```

Run the full suite with Firefox:

```bash
mvn clean test -Dbrowser=firefox
```

Run with the default browser in headless mode:

```bash
mvn clean test -Dheadless=true
```

Run Chrome in headless mode:

```bash
mvn clean test -Dbrowser=chrome -Dheadless=true
```

Run Firefox in headless mode:

```bash
mvn clean test -Dbrowser=firefox -Dheadless=true
```

Run only smoke tests:

```bash
mvn clean test -Dgroups=smoke
```

Run only regression tests:

```bash
mvn clean test -Dgroups=regression
```

Browser and headless properties can be combined with test tags:

```bash
mvn clean test -Dgroups=smoke -Dbrowser=chrome -Dheadless=true
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

Local execution uses Firefox and visible browser mode by default.

## Test Suites

### Smoke

Smoke tests cover:

- loading the practice home page;
- logging in with valid credentials.

Run with:

```bash
mvn clean test -Dgroups=smoke
```

### Regression

Regression tests cover additional behavior that should remain stable. The current regression coverage includes invalid username and invalid password login scenarios.

Run with:

```bash
mvn clean test -Dgroups=regression
```

### Full Suite

The full suite runs every test, regardless of tag.

Run with:

```bash
mvn clean test
```

## GitHub Actions

The repository contains two GitHub Actions workflows.

### Automatic CI

Workflow:

```text
.github/workflows/ci.yml
```

Automatic execution behavior:

- Pull requests to `main` run smoke tests.
- Pushes to `main` run the full suite.
- A nightly schedule runs the full suite at `06:00 UTC`.
- Every execution uses a Chrome and Firefox browser matrix.
- Browser tests run in headless mode.

The nightly schedule currently corresponds to approximately `03:00` in Argentina.

### Manual Tests

Workflow:

```text
.github/workflows/manual-tests.yml
```

The manual workflow can be started from the GitHub Actions interface using `workflow_dispatch`.

Available suite options:

```text
smoke
full
```

Available browser options:

```text
chrome
firefox
all
```

Examples:

- Smoke suite in Chrome
- Smoke suite in Firefox
- Full suite in Chrome
- Full suite in Firefox
- Smoke or full suite in both browsers

Manual executions also run in headless mode.

## CI Artifacts

### Surefire Reports

Maven Surefire reports are stored locally in:

```text
target/surefire-reports/
```

GitHub Actions uploads them after test execution using browser-specific artifact names:

```text
surefire-reports-chrome
surefire-reports-firefox
```

These reports contain test results, execution times, failures, errors, and skipped-test information.

### Screenshots

When a browser test fails, the framework captures a screenshot in:

```text
target/screenshots/
```

GitHub Actions uploads screenshots only when a job fails.

Artifact names include the browser:

```text
screenshots-chrome
screenshots-firefox
```

## Design Decisions

### JUnit 5

JUnit 5 provides a modern test lifecycle, parameterized tests, tags, and clean integration with Maven Surefire.

### Page Object Model

Page Object Model separates test intent from page locators and browser interactions. Tests describe expected behavior while Page Objects handle UI details.

### Explicit Waits

The framework uses explicit waits instead of implicit waits. Synchronization is intentional and centralized in `BasePage`, making timeout behavior easier to understand.

### Configurable Browser Execution

The browser is selected through a system property:

```bash
-Dbrowser=chrome
```

This allows the same suite to run against Chrome or Firefox without code changes.

### Configurable Headless Mode

Headless mode is controlled through:

```bash
-Dheadless=true
```

Visible execution remains the local default, while CI uses headless mode.

### Test Tags

JUnit 5 tags separate smoke and regression coverage without requiring separate test classes or duplicated suites.

### Separate CI Workflows

Automatic CI and manual execution use separate workflow files. This keeps event-driven validation independent from configurable, on-demand test runs.

## Roadmap

Possible future improvements:

- Additional page and workflow coverage
- Broader test data management
- Environment-specific configuration
- HTML or dashboard-style reporting
- Parallel execution
- Selenium Grid or remote browser execution
- Flakiness monitoring and historical test metrics

## Author

Nahuel Muñiz  
QA Automation / SDET
