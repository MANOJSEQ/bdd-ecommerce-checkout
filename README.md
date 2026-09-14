# BDD E-commerce Checkout Automation

[![BDD E-commerce Tests](https://github.com/MANOJSEQ/bdd-ecommerce-checkout/actions/workflows/bdd-tests.yml/badge.svg)](https://github.com/MANOJSEQ/bdd-ecommerce-checkout/actions/workflows/bdd-tests.yml)

A behaviour-driven test automation framework for the
[SauceDemo](https://www.saucedemo.com/) e-commerce application.

The project automates product selection, cart management, valid checkout and checkout validation using Java, Selenium WebDriver, Cucumber and JUnit Platform.

## Key Features

- Behaviour-driven scenarios written in Gherkin
- Selenium WebDriver browser automation
- Page Object Model architecture
- Explicit waits for reliable interaction
- Separate browser session for every scenario
- Visible and headless Chrome execution
- Automatic screenshots when scenarios fail
- Cucumber HTML and JSON reports
- Scenario filtering using Cucumber tags
- Automated execution through GitHub Actions
- Maven dependency and build management

## Automated Scenarios

| Scenario | Tag | Expected result |
|---|---|---|
| Add a product to the shopping cart | `@smoke` | Selected product appears in the cart |
| Remove a product from the shopping cart | `@regression` | Cart becomes empty |
| Complete an order with valid details | `@smoke @checkout` | Order confirmation is displayed |
| Submit checkout without a postal code | `@negative @checkout` | Required postal-code error is displayed |

Every scenario begins with a fresh login using SauceDemo's standard test account.

## Technology Stack

| Technology | Version | Purpose |
|---|---:|---|
| Java | 21 | Test implementation |
| Selenium WebDriver | 4.49.0 | Browser automation |
| Cucumber-JVM | 7.34.8 | BDD execution |
| Gherkin | Cucumber 7 | Business-readable scenarios |
| JUnit Jupiter | 5.14.2 | Assertions |
| JUnit Platform | 1.14.2 | Test-suite execution |
| Maven | 3 | Dependency and build management |
| GitHub Actions | — | Continuous integration |

## Project Structure

```text
bdd-ecommerce-checkout/
├── .github/
│   └── workflows/
│       └── bdd-tests.yml
├── src/
│   └── test/
│       ├── java/com/manoj/qa/
│       │   ├── hooks/
│       │   │   ├── BrowserHooks.java
│       │   │   └── DriverManager.java
│       │   ├── pages/
│       │   │   ├── BasePage.java
│       │   │   ├── LoginPage.java
│       │   │   ├── InventoryPage.java
│       │   │   ├── CartPage.java
│       │   │   ├── CheckoutInformationPage.java
│       │   │   ├── CheckoutOverviewPage.java
│       │   │   └── CheckoutCompletePage.java
│       │   ├── runners/
│       │   │   └── RunCucumberTest.java
│       │   └── steps/
│       │       └── CheckoutSteps.java
│       └── resources/
│           └── features/
│               └── checkout.feature
├── .gitignore
├── pom.xml
└── README.md
```

## Framework Design

### Feature File

`checkout.feature` describes customer behaviour using:

- `Given` for the initial context
- `When` for customer actions
- `Then` for expected results
- `Background` for login steps shared by every scenario
- Tags for grouping and selectively running scenarios

### Step Definitions

`CheckoutSteps.java` connects Gherkin sentences to Java methods. It calls Page Object methods and uses JUnit assertions to verify results.

### Page Objects

Each application page has a dedicated Java class containing its locators and browser actions. This keeps Selenium code separate from test behaviour and reduces duplication.

### Hooks and Driver Management

`BrowserHooks.java` opens Chrome before each scenario and closes it afterward. Failed scenarios receive a screenshot attachment in the Cucumber report.

`DriverManager.java` manages each WebDriver instance using `ThreadLocal<WebDriver>` and supports visible or headless execution.

## Prerequisites

Install:

- Java 21
- Maven
- Google Chrome

Verify the installations:

```bash
java -version
mvn -version
```

## Running the Tests

Clone the repository:

```bash
git clone https://github.com/MANOJSEQ/bdd-ecommerce-checkout.git
cd bdd-ecommerce-checkout
```

Run with visible Chrome:

```bash
mvn test
```

Run in headless Chrome:

```bash
mvn clean test -Dheadless=true
```

Run only smoke scenarios:

```bash
mvn test -Dcucumber.filter.tags="@smoke"
```

Run checkout scenarios:

```bash
mvn test -Dcucumber.filter.tags="@checkout"
```

Run the negative scenario:

```bash
mvn test -Dcucumber.filter.tags="@negative"
```

## Test Reports

After execution, reports are generated at:

```text
target/cucumber-reports/cucumber.html
target/cucumber-reports/cucumber.json
```

Open the local HTML report on macOS:

```bash
open target/cucumber-reports/cucumber.html
```

Failure screenshots are attached directly to the relevant failed scenario in the Cucumber report.

## Continuous Integration

The GitHub Actions workflow runs automatically for:

- Pushes to `main`
- Pull requests targeting `main`

The workflow:

1. Checks out the repository
2. Configures Java 21
3. Caches Maven dependencies
4. Runs all scenarios in headless Chrome
5. Uploads Cucumber and Surefire reports

Reports are retained as downloadable workflow artifacts for 14 days.

## Test Application

This framework uses SauceDemo, a public demonstration application designed for automation practice.

Test credentials:

```text
Username: standard_user
Password: secret_sauce
```

## Author

**Manoj Alexius Sequeira**

- GitHub: [MANOJSEQ](https://github.com/MANOJSEQ)
- QA Portfolio: [manojseq.github.io/qa-engineering-portfolio](https://manojseq.github.io/qa-engineering-portfolio/)
