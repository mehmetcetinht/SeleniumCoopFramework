Feature: Demo

  Scenario: Sign Up
    Given I open loginUrl at firefox
    When I fill: "x" with random 10 string
    When I click y
    Then I control text "y" of element x
    Then I wait for 2 seconds
    When I close first pop up
    When I login with username "tomsmith" password "SuperSecretPassword!"
    When I wait for 3 seconds
    Then I check url changed to "http://the-internet.herokuapp.com/secure"
#    Then I see logoutButton element
    Then I close browser
