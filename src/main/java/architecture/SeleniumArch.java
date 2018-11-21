package architecture;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by jose taveira gomes on 13/11/2018.
 */

public class SeleniumArch {

    static { System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver.exe"); }
    WebDriver driver = new ChromeDriver();
    WebDriverWait wait = new WebDriverWait(driver, 15);

    public void setWebDriver(String browser){

        switch (browser.toLowerCase()){

            case "chrome": System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver.exe");
                driver = new ChromeDriver();
                break;

            case "firefox": System.setProperty("webdriver.gecko.driver", "drivers\\geckodriver.exe");
                driver = new FirefoxDriver();
                break;

            default:  System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver.exe");
                driver = new ChromeDriver();
                break;
        }
    }

    public void setWebDriverWait(Integer time) {

        wait = new WebDriverWait(driver, time);
    }

    public void waitForLoad() {
        ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
            }
        };
        wait.until(pageLoadCondition);
        System.out.println("Step: Waited for Javascript to load");
    }

    public void clickElement(String locator) {
        wait.until(ExpectedConditions.elementToBeClickable(new By.ByXPath(locator)));
        driver.findElement(new By.ByXPath(locator)).click();
        System.out.println("Step: Clicked Element");
    }

    public void submitElement(String locator) {
        wait.until(ExpectedConditions.elementToBeClickable(new By.ByXPath(locator)));
        driver.findElement(new By.ByXPath(locator)).submit();
        System.out.println("Step: Submitted Element");
    }

    public void typeInElement(String locator, String message) {
        wait.until(ExpectedConditions.elementToBeClickable(new By.ByXPath(locator)));
        driver.findElement(new By.ByXPath(locator)).sendKeys(message);
        System.out.println("Step: Typed '" + message + "' in Element");
    }

    public void waitElement(String locator) {
        wait.until(ExpectedConditions.elementToBeClickable(new By.ByXPath(locator)));
        System.out.println("Step: Waited for Element");
    }

    public void navigateTo(String url) {
        driver.get(url);
        System.out.println("Step: Navigated to " + url);
    }

    public void quit() {
        driver.quit();
        System.out.println("Step: Driver Quit");
    }

    public void close() {
        driver.close();
        System.out.println("Step: Driver Close");
    }

    public void maximize() {
        driver.manage().window().maximize();
        System.out.println("Step: Window Maximized");
    }
}
