Feature: Account Creation on eCommerce Website

  As a new user
  I want to create an account on the eCommerce website
  So that I can make purchases, save my preferences, and track my order history

#  Background:
#    Given I am on the account creation page

  @ValidAccountCreation
  Scenario: Successful Account Creation
    Given Account creation url as "https://magento.softwaretestingboard.com/customer/account/create/"
    When I enter a valid first name
    And I enter a valid last name
    And I enter a valid unique email address
    And I enter a valid password
    And I click the Submit button
    Then I should be in Account Dashboard which has title as "My Account"
    And A account success message "Thank you for registering with Main Website Store."

    @ValidAccountCreation
  Scenario: Successful Login After Account Creation

    Given I am on the login page
    When I enter the registered email address
    And I enter the correct password
    And I click the sign in button
    Then I should be successfully logged into my account "My Account"

      @ValidAccountCreation
  Scenario: Attempt to Create Account with Already Registered Email
    When I enter a valid first name1
    And I enter a valid last name1
    And I enter an email address already registered
    And I enter a valid password1
    And I click the Sign in button
   Then I should see an error message "There is already an account with this email address. If you are sure that it is your email address, click here to get your password and access your account."

        @ValidAccountCreation
  Scenario: Attempt to Create Account with Invalid Email Format
    When I enter a valid first name
    And I enter a valid last name
    And I enter an invalid email address format
    And I enter a valid password
    And I click the Create an Account button
    Then I should see an error message
#
#  Scenario: Attempt to Create Account with Short Password
#    When I enter a valid first name
#    And I enter a valid last name
#    And I enter a valid unique email address
#    And I enter a short password
#    And I click the create account button
#    Then I should see an error message
#
#  Scenario: Create Account with Missing Mandatory Fields
#    When I leave the first name blank
#    And I enter a valid last name
#    And I enter a valid unique email address
#    And I enter a valid password
#    And I click the create account button
#    Then I should see an error message
