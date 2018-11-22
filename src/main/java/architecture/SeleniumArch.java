package architecture;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import reporter.HtmlReporter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by jose taveira gomes on 13/11/2018.
 */

public class SeleniumArch {

    WebDriver driver;
    WebDriverWait wait;
    BufferedWriter report;
    HtmlReporter reporter = new HtmlReporter();

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

    public void startReport() throws IOException{
        reporter.startReport(report);
    }

    public void endReport() throws IOException{
        reporter.endReport(report);
    }

    public void setTestTitle(String title) throws IOException{
        reporter.setTestTitle(report, title);
    }

    public void startTestReport() throws IOException{
        reporter.startTestReport(report);
    }

    public void endTestReport() throws IOException{
        reporter.endTestReport(report);
    }

    public void setReportFile(String path) throws IOException {

        this.report = new BufferedWriter(new FileWriter("reports/" + path));
    }

    public void setWebDriverWait(Integer time) {

        wait = new WebDriverWait(driver, time);
    }

    public void clickElement(String locator) throws IOException {

        try {
            wait.until(ExpectedConditions.elementToBeClickable(new By.ByXPath(locator)));

            reporter.openStep(report, "PASSED", "Click Element");

                reporter.openAction(report, "PASSED", "Wait until locator is clickable");
                    reporter.openLocator(report, locator);
                reporter.closeAction(report);
        }
        catch(Throwable exc) {

            reporter.openStep(report, "FAILED", "Click Element");

                reporter.openAction(report, "FAILED", "Wait until locator is clickable");
                    reporter.openLocator(report, locator);
                    reporter.openException(report, exc);
                reporter.closeAction(report);

            reporter.closeStep(report);
            throw exc;
        }

        try {
            driver.findElement(new By.ByXPath(locator)).click();

                reporter.openAction(report, "PASSED", "Find element and click");
                    reporter.openLocator(report, locator);
                reporter.closeAction(report);

            reporter.closeStep(report);
        }
        catch(Throwable exc) {

                reporter.openAction(report, "FAILED", "Find element and click");
                    reporter.openLocator(report, locator);
                    reporter.openException(report, exc);
                reporter.closeAction(report);

            reporter.closeStep(report);
            throw exc;
        }
    }

    public void submitElement(String locator) throws IOException{

        try {
            wait.until(ExpectedConditions.elementToBeClickable(new By.ByXPath(locator)));

            reporter.openStep(report, "PASSED", "Submit Element");

                reporter.openAction(report, "PASSED", "Wait until locator is clickable");
                    reporter.openLocator(report, locator);
                reporter.closeAction(report);
        }
        catch(Throwable exc) {

            reporter.openStep(report, "FAILED", "Submit Element");

                reporter.openAction(report, "FAILED", "Wait until locator is clickable");
                    reporter.openLocator(report, locator);
                    reporter.openException(report, exc);
                reporter.closeAction(report);

            reporter.closeStep(report);
            throw exc;
        }

        try {
            driver.findElement(new By.ByXPath(locator)).submit();

                reporter.openAction(report, "PASSED", "Submit");
                    reporter.openLocator(report, locator);
                reporter.closeAction(report);

            reporter.closeStep(report);
        }
        catch(Throwable exc) {

                reporter.openAction(report, "FAILED", "Submit");
                    reporter.openLocator(report, locator);
                    reporter.openException(report, exc);
                reporter.closeAction(report);

            reporter.closeStep(report);
            throw exc;
        }
    }

    public void typeInElement(String locator, String message) throws IOException{

        try {
            wait.until(ExpectedConditions.elementToBeClickable(new By.ByXPath(locator)));

            reporter.openStep(report, "PASSED", "Type in Element");

                reporter.openAction(report, "PASSED", "Wait until locator is clickable");
                    reporter.openLocator(report, locator);
                reporter.closeAction(report);
        }
        catch(Throwable exc) {

            reporter.openStep(report, "FAILED", "Type in Element");

                reporter.openAction(report, "FAILED", "Wait until locator is clickable");
                    reporter.openLocator(report, locator);
                    reporter.openException(report, exc);
                reporter.closeAction(report);

            reporter.closeStep(report);
            throw exc;
        }

        try {
            driver.findElement(new By.ByXPath(locator)).sendKeys(message);

                reporter.openAction(report, "PASSED", "Send Keys");
                    reporter.openLocator(report, locator);
                reporter.closeAction(report);

            reporter.closeStep(report);
        }
        catch(Throwable exc) {

                reporter.openAction(report, "FAILED", "Send Keys");
                    reporter.openLocator(report, locator);
                    reporter.openException(report, exc);
                reporter.closeAction(report);

            reporter.closeStep(report);
            throw exc;
        }
    }

    public void waitElement(String locator) throws IOException {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(new By.ByXPath(locator)));

            reporter.openStep(report, "PASSED", "Wait Element");

                reporter.openAction(report, "PASSED", "Wait until locator is clickable");
                    reporter.openLocator(report, locator);
                reporter.closeAction(report);

            reporter.closeStep(report);
        }
        catch(Throwable exc) {

            reporter.openStep(report, "FAILED", "Wait Element");

                reporter.openAction(report, "FAILED", "Wait until locator is clickable");
                    reporter.openLocator(report, locator);
                    reporter.openException(report, exc);
                reporter.closeAction(report);

            reporter.closeStep(report);
            throw exc;
        }
    }

    public void navigateTo(String url) {
        driver.get(url);
    }

    public void quit() {
        driver.quit();
    }

    public void close() {
        driver.close();
    }

    public void maximize() {
        driver.manage().window().maximize();
    }
}
