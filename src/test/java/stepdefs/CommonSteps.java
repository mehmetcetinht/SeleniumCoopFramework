package stepdefs;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Optional;

import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

public class CommonSteps extends AbstractStep {

    private int timeOut = 10;


    //Click Functions-----------------------------------

    /**
     * Bu fonksiyon istenilen alana,
     * belirlenen saniye içinde tıklar
     */
    @When("^I click ([^\"]*)$")
    public void clickElement(By byLocator) {
        WebDriverWait wait = new WebDriverWait(driver, timeOut);
        wait.until(ExpectedConditions.elementToBeClickable(byLocator))
                .click();
    }

    /**
     * Bu fonksiyon istenilen alana,
     * istenilen saniye içinde tıklar
     */
    @When("^I click ([^\"]*) in ([^\"]*) seconds$")
    public void clickInSecond(By byLocator, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.elementToBeClickable(byLocator))
                .click();
    }


    //Fill Functions-----------------------------------

    /**
     * Bu fonksiyon istenilen alana,
     * belirlenen saniye içinde,
     * istenilen texti yazar
     */
    @When("^I fill ([^\"]*) element with: \"([^\"]*)\"$")
    public void sendKeys(By byLocator, String text) {
        WebDriverWait wait = new WebDriverWait(driver, timeOut);
        wait.until(ExpectedConditions.elementToBeClickable(byLocator))
                .sendKeys(text);
    }

    /**
     * Bu fonksiyon istenilen alana,
     * istenilen saniye içinde,
     * istenilen texti yazar
     */
    @When("^I fill ([^\"]*) element with: \"([^\"]*)\" within ([^\"]*) seconds$")
    public void sendKeysInSeconds(By byLocator, int timeoutInSeconds, String text) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.until(ExpectedConditions.elementToBeClickable(byLocator))
                .sendKeys(text);

    }

    /**
     * Bu fonksiyon istenilen alanın text'ini temizler
     */
    @When("^I clear ([^\"]*) element$")
    public void clearTextArea(By byLocator) {
        WebDriverWait wait = new WebDriverWait(driver, timeOut);
        wait.until(ExpectedConditions.elementToBeClickable(byLocator))
                .clear();
    }

    /**
     * Bu fonksiyon istenilen alana;
     * rastgele olarak,
     * istenilen sayıda,
     * tercih edilen(String/integer)
     * texti yazar
     */
    @When("^(?:I)? fill: \"([^\"]*)\" with random (\\d+) (string|integer)")
    public void randomFill(By elementKey, int index, String type) {
        if (type.equalsIgnoreCase("string")) {
            String alphabet = "abcdefghiklmnoprstvyzx";
            Random rand = new Random();
            String longText = "";
            for (int i = 0; i < index; i++) {
                int randIndex = rand.nextInt(alphabet.length());
                char character = alphabet.charAt(randIndex);
                longText += character;
            }
            sendKeys(elementKey, longText);
        } else {
            String number = "1234567890";
            Random rand = new Random();
            String longText = "";
            for (int i = 0; i < index; i++) {
                int randIndex = rand.nextInt(number.length());
                char character = number.charAt(randIndex);
                longText += character;
            }
            sendKeys(elementKey, longText);
        }
    }

    /**
     * Bu fonksiyon istenilen alana, istenilen gün kadar sonrasının tarihini yazar
     */
    @When("^(?:I)? fill: \"([^\"]*)\" with date of (\\d+) days later")
    public void selectDaysLater(By elementKey, int dayLater) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        Date date = new Date();
        System.out.println(formatter.format(date));
        LocalDate d = LocalDate.now().plusDays(dayLater);
        sendKeys(elementKey, d.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
    }


    //Wait Methods-----------------------------------
    private void sleep(long m) {
        try {
            Thread.sleep(m);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Bu fonksiyon istenilen saniye kadar beklemeyi sağlar
     */
    @Then("^I wait for ([^\"]*) seconds$")
    public void WaitForNSeconds(int x) {
        int seconds = x * 1000;
        sleep(seconds);
    }


    //Set Up Browser-----------------------------------

    /**
     * Bu fonksiyon istenilen saniye kadar beklemeyi sağlar
     */
    @Given("I open (\\w+(?: \\w+)*) at (\\w+(?: \\w+)*)$")
    public void openBrowser(String page, @Optional("chrome") String browser) {
//        Create driver
        switch (browser) {
            case "chrome":
                System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
                driver = new ChromeDriver();
                break;

            case "firefox":
                System.setProperty("webdriver.chrome.driver", "src/main/resources/geckodriver.exe");
                driver = new FirefoxDriver();
                break;

            default:
                System.out.println("Tests started with Chrome since the browser type '" + browser + "' is not defined!");
                System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
                driver = new ChromeDriver();
                break;
        }

//        sleep(3000);
//        Open Browser
        try {
            Properties prop = new Properties();
            FileInputStream ip = new FileInputStream("src/test/config.properties");

            prop.load(ip);
            String url = prop.getProperty(page);
            driver.get(url);
        } catch (Exception e) {
            System.out.println("Cannot read file!");
        }
//        maximize browser window
        driver.manage().window().maximize();

//        tearDown();
    }


    @Then("^I control text \"([^\"]*)\" of element ([^\"]*)$")
    public void controlTextOf(String expectedText, By elementKey) {

        WebDriverWait wait = new WebDriverWait(driver, timeOut);
        String elementText = wait.until(ExpectedConditions.visibilityOfElementLocated(elementKey)).getText();
        Assert.assertTrue(elementText.contains(expectedText), "Actual message does not contains expected message!\nActual Message: " + elementText + "\nExpected Message: " + expectedText);
    }

}
