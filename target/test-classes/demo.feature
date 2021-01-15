Feature: Demo

  Scenario: Positive Logi
    Given I open loginUrl at firefox
#    When I wait for 3 seconds
#    Then I control text "Login" of element x
    When I login with username "tomsmith" password "SuperSecretPassword!"
    When I wait for 3 seconds
    Then I check url changed to "http://the-internet.herokuapp.com/secure"
#    Then I see logoutButton element
    Then I close browser
