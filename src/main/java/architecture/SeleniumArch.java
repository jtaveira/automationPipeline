package architecture;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
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
            reporter.writeToFile(report, "<div class=\"step\">PASSED - Click Element<div class=\"action\">PASSED - Wait until locator is clickable.</div>");
            reporter.writeToFile(report, "<div class=\"locator\">LOCATOR: " + locator + "</div>");
        }
        catch(Throwable exc) {
            reporter.writeToFile(report, "<div class=\"step\">FAILED - Click Element<div class=\"action\">FAILED - Wait until locator is clickable.</div>");
            reporter.writeToFile(report, "<div class=\"locator\">LOCATOR: " + locator + "</div>");
            throw exc;
        }

        try {
            driver.findElement(new By.ByXPath(locator)).click();
            reporter.writeToFile(report, "<div class=\"action\">PASSED - Click element with locator.</div>");
            reporter.writeToFile(report, "<div class=\"locator\">LOCATOR: " + locator + "</div></div>");
        }
        catch(Throwable exc) {
            reporter.writeToFile(report, "<div class=\"action\">FAILED - Click element with locator.</div>");
            reporter.writeToFile(report, "<div class=\"locator\">LOCATOR: " + locator + "</div></div>");
            throw exc;
        }
    }

    public void submitElement(String locator) throws IOException{

        try {
            wait.until(ExpectedConditions.elementToBeClickable(new By.ByXPath(locator)));
            reporter.writeToFile(report, "<div class=\"step\">PASSED - Submit Element<div class=\"action\">PASSED - Wait until locator is clickable.</div>");
            reporter.writeToFile(report, "<div class=\"locator\">LOCATOR: " + locator + "</div>");
        }
        catch(Throwable exc) {
            reporter.writeToFile(report, "<div class=\"step\">FAILED - Submit Element<div class=\"action\">FAILED - Wait until locator is clickable.</div>");
            reporter.writeToFile(report, "<div class=\"locator\">LOCATOR: " + locator + "</div>");
            throw exc;
        }

        try {
            driver.findElement(new By.ByXPath(locator)).submit();
            reporter.writeToFile(report, "<div class=\"action\">PASSED - Submit.</div>");
            reporter.writeToFile(report, "<div class=\"locator\">LOCATOR: " + locator + "</div></div>");
        }
        catch(Throwable exc) {
            reporter.writeToFile(report, "<div class=\"action\">FAILED - Submit.</div>");
            reporter.writeToFile(report, "<div class=\"locator\">LOCATOR: " + locator + "</div></div>");
            throw exc;
        }
    }

    public void typeInElement(String locator, String message) throws IOException{

        try {
            wait.until(ExpectedConditions.elementToBeClickable(new By.ByXPath(locator)));
            reporter.writeToFile(report, "<div class=\"step\">PASSED - Type in Element<div class=\"action\">PASSED - Wait until locator is clickable.</div>");
            reporter.writeToFile(report, "<div class=\"locator\">LOCATOR: " + locator + "</div>");
        }
        catch(Throwable exc) {
            reporter.writeToFile(report, "<div class=\"step\">FAILED - Type in Element<div class=\"action\">FAILED - Wait until locator is clickable.</div>");
            reporter.writeToFile(report, "<div class=\"locator\">LOCATOR: " + locator + "</div>");
            throw exc;
        }

        try {
            driver.findElement(new By.ByXPath(locator)).sendKeys(message);
            reporter.writeToFile(report, "<div class=\"action\">PASSED - Sent keys to element.</div>");
            reporter.writeToFile(report, "<div class=\"locator\">LOCATOR: " + locator + "</div></div>");
        }
        catch(Throwable exc) {
            reporter.writeToFile(report, "<div class=\"action\">FAILED - Sent keys to element.</div>");
            reporter.writeToFile(report, "<div class=\"locator\">LOCATOR: " + locator + "</div></div>");
            throw exc;
        }
    }

    public void waitElement(String locator) throws IOException {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(new By.ByXPath(locator)));
            reporter.writeToFile(report, "<div class=\"step\">PASSED - Wait Element<div class=\"action\">PASSED - Wait until locator is clickable.</div>");
            reporter.writeToFile(report, "<div class=\"locator\">LOCATOR: " + locator + "</div></div>");
        }
        catch(Throwable exc) {
            reporter.writeToFile(report, "<div class=\"step\">FAILED - Wait Element<div class=\"action\">FAILED - Wait until locator is clickable.</div>");
            reporter.writeToFile(report, "<div class=\"locator\">LOCATOR: " + locator + "</div></div>");
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
