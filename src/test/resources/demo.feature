#TODO Backgroundda çalışacak, Before-After gibi methodlar için class
#TODO JSON File oluşturmak ve içinden dataları çekmek
#TODO Ortak By methodları için diğer projler incelenecek
#TODO Duplicate method çözümüne bakılcak
Feature: Demo
  Background:
    Given I open loginUrl at firefox

  Scenario: Positive Login
#    When I wait for 3 seconds
#    Then I control text "Login" of element x
    When I login with username "tomsmith" password "SuperSecretPassword!"
    When I wait for 3 seconds
    Then I check url changed to "http://the-internet.herokuapp.com/secure"
#    Then I see logoutButton element
    Then I close browser
