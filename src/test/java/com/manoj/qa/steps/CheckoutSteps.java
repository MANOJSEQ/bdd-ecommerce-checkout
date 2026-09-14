package com.manoj.qa.steps;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.openqa.selenium.WebDriver;

import com.manoj.qa.hooks.DriverManager;
import com.manoj.qa.pages.CartPage;
import com.manoj.qa.pages.CheckoutCompletePage;
import com.manoj.qa.pages.CheckoutInformationPage;
import com.manoj.qa.pages.CheckoutOverviewPage;
import com.manoj.qa.pages.InventoryPage;
import com.manoj.qa.pages.LoginPage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CheckoutSteps {

    private LoginPage loginPage;
    private InventoryPage inventoryPage;
    private CartPage cartPage;
    private CheckoutInformationPage checkoutInformationPage;
    private CheckoutOverviewPage checkoutOverviewPage;
    private CheckoutCompletePage checkoutCompletePage;

    private void initialisePages() {
        WebDriver driver = DriverManager.getDriver();

        loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);
        cartPage = new CartPage(driver);

        checkoutInformationPage =
                new CheckoutInformationPage(driver);

        checkoutOverviewPage =
                new CheckoutOverviewPage(driver);

        checkoutCompletePage =
                new CheckoutCompletePage(driver);
    }

    @Given("the customer is on the SauceDemo login page")
    public void theCustomerIsOnTheSauceDemoLoginPage() {
        initialisePages();
        loginPage.open();
    }

    @When("the customer logs in with valid credentials")
    public void theCustomerLogsInWithValidCredentials() {
        loginPage.loginAs(
                "standard_user",
                "secret_sauce"
        );
    }

    @Then("the inventory page should be displayed")
    public void theInventoryPageShouldBeDisplayed() {
        assertTrue(
                inventoryPage.isInventoryPageDisplayed(),
                "The inventory page was not displayed."
        );
    }

    @When("the customer adds {string} to the cart")
    public void theCustomerAddsProductToTheCart(
            String productName
    ) {
        inventoryPage.addProductToCart(productName);
    }

    @Given("the customer has added {string} to the cart")
    public void theCustomerHasAddedProductToTheCart(
            String productName
    ) {
        inventoryPage.addProductToCart(productName);
    }

    @When("the customer opens the shopping cart")
    public void theCustomerOpensTheShoppingCart() {
        inventoryPage.openCart();
    }

    @Then("the cart should contain {string}")
    public void theCartShouldContainProduct(
            String productName
    ) {
        assertTrue(
                cartPage.containsProduct(productName),
                "The cart did not contain: " + productName
        );
    }

    @When("removes {string} from the cart")
    public void removesProductFromTheCart(
            String productName
    ) {
        cartPage.removeProduct(productName);
    }

    @Then("the cart should be empty")
    public void theCartShouldBeEmpty() {
        assertTrue(
                cartPage.isEmpty(),
                "The cart was expected to be empty."
        );
    }

    @When("begins the checkout")
    public void beginsTheCheckout() {
        cartPage.proceedToCheckout();
    }

    @When(
        "enters first name {string}, last name {string} " +
        "and postal code {string}"
    )
    public void entersValidCustomerInformation(
            String firstName,
            String lastName,
            String postalCode
    ) {
        checkoutInformationPage.enterCustomerInformation(
                firstName,
                lastName,
                postalCode
        );

        checkoutInformationPage.continueCheckout();
    }

    @When("finishes the purchase")
    public void finishesThePurchase() {
        assertTrue(
                checkoutOverviewPage
                        .isCheckoutOverviewPageDisplayed(),
                "The checkout overview page was not displayed."
        );

        checkoutOverviewPage.finishCheckout();
    }

    @Then("the order confirmation should display {string}")
    public void theOrderConfirmationShouldDisplay(
            String expectedMessage
    ) {
        assertEquals(
                expectedMessage,
                checkoutCompletePage.getConfirmationMessage(),
                "The order confirmation message was incorrect."
        );
    }

    @When(
        "enters first name {string}, last name {string} " +
        "and no postal code"
    )
    public void entersCustomerInformationWithoutPostalCode(
            String firstName,
            String lastName
    ) {
        checkoutInformationPage.enterCustomerInformation(
                firstName,
                lastName,
                ""
        );

        checkoutInformationPage.continueCheckout();
    }

    @Then("the checkout error should display {string}")
    public void theCheckoutErrorShouldDisplay(
            String expectedError
    ) {
        assertEquals(
                expectedError,
                checkoutInformationPage.getErrorMessage(),
                "The checkout validation message was incorrect."
        );
    }
}