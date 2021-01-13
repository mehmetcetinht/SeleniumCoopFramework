package stepdefs;


import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Optional;

import java.io.FileInputStream;
import java.util.Properties;

public class MySteps extends CommonSteps {

    private WebDriver driver;
    private final CommonSteps commonSteps;

    public MySteps(CommonSteps commonSteps) {
        this.commonSteps = commonSteps;
    }

    @After
    private void tearDown() {
        // Close browser
        driver.quit();
    }

    @Then("^I see (\\w+(?: \\w+)*) element$")
    public void seeElement(String elementKey) {
        WebElement logoutButton = driver.findElement(By.xpath("//*[text()=' Logout']"));
        Assert.assertTrue(logoutButton.isDisplayed(), "Element is not exist at the current page!");

    }


    @When("^I login with username \"([^\"]*)\" password \"([^\"]*)\"$")
    public void loginWithUser(String Username,String Password) {

            WebElement username = driver.findElement(By.id("username"));
            WebElement password = driver.findElement(By.id("password"));
            WebElement loginButton = driver.findElement(By.xpath("//*[text()=' Login']"));

            username.sendKeys(Username);
            password.sendKeys(Password);
            loginButton.click();

    }


    @Then("^I check url changed to \"([^\"]*)\"$")
    public void ıCheckUrlChangedTo(String url) {
        String newUrl = url;
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, newUrl, "Actual page url is not the same as expected!");
    }


    @Then("^I close browser$")
    public void closeBrowser() {
        tearDown();
    }


    @When("^I close first pop up$")
    public void closeFirstPopUp() {
        WebElement closeButton = driver.findElement(By.xpath("//*[@title='Close']"));
        if (closeButton.isDisplayed()) {
            closeButton.click();
        }
    }

}
