@ecommerce
Feature: Customer shopping and checkout

  As an online customer
  I want to manage products in my cart
  So that I can complete an order successfully

  Background:
    Given the customer is on the SauceDemo login page
    When the customer logs in with valid credentials
    Then the inventory page should be displayed

  @smoke
  Scenario: Add a product to the shopping cart
    When the customer adds "Sauce Labs Backpack" to the cart
    And the customer opens the shopping cart
    Then the cart should contain "Sauce Labs Backpack"

  @regression
  Scenario: Remove a product from the shopping cart
    Given the customer has added "Sauce Labs Backpack" to the cart
    When the customer opens the shopping cart
    And removes "Sauce Labs Backpack" from the cart
    Then the cart should be empty

  @smoke @checkout
  Scenario: Complete an order using valid customer details
    Given the customer has added "Sauce Labs Backpack" to the cart
    When the customer opens the shopping cart
    And begins the checkout
    And enters first name "Manoj", last name "Sequeira" and postal code "L1 1AA"
    And finishes the purchase
    Then the order confirmation should display "Thank you for your order!"

  @negative @checkout
  Scenario: Postal code is required during checkout
    Given the customer has added "Sauce Labs Backpack" to the cart
    When the customer opens the shopping cart
    And begins the checkout
    And enters first name "Manoj", last name "Sequeira" and no postal code
    Then the checkout error should display "Error: Postal Code is required"