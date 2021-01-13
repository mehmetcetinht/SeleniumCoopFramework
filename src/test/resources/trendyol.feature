Feature: Demo

  Scenario: Sign Up
    Given I open trendyol at firefox
    When I fill: "x" with random 10 string
    When I click y
    When I close first pop up
    When I login with username "tomsmith" password "SuperSecretPassword!"
    When I wait for 3 seconds
    Then I check url changed to "http://the-internet.herokuapp.com/secure"
#    Then I see logoutButton element
    Then I close browser
