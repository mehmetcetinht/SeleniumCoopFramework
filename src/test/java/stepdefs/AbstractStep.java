package stepdefs;


import org.openqa.selenium.WebDriver;

public class AbstractStep {
    WebDriver driver;

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }
}
