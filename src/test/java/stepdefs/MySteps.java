package stepdefs;


import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.After;
import org.junit.AfterClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MySteps extends AbstractStep {
    private final CommonSteps commonSteps;
//    private final MySteps mySteps;

    public MySteps(CommonSteps commonSteps) {
        this.commonSteps = commonSteps;
        this.driver = commonSteps.getDriver();
//        this.mySteps = mySteps;
    }

    @AfterSuite
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
    public void loginWithUser(String Username,String Password) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
            String elementKey = "id"; //bu xml veya json dosyadan gelecek /xpath vs.
            String usernameKey = "username";  //bu xml veya json dosyadan gelecek //username, email
            Method method = By.class.getDeclaredMethod(elementKey, String.class);

            WebElement username = driver.findElement((By) method.invoke(null, usernameKey));
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
