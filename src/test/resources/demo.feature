Feature: Demo

  Scenario: Positive Login
    Given I open loginUrl at firefox
    When I login with username "tomsmith" password "SuperSecretPassword!"
    When I wait for 3 seconds
    Then I check url changed to "http://the-internet.herokuapp.com/secure"
#    Then I see logoutButton element
    Then I close browser
    When I click "x"
