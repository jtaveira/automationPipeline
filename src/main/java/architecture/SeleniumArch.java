package architecture;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import reporter.CorrectReport;
import reporter.HtmlReporter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;

/**
 * Created by jose taveira gomes on 13/11/2018.
 */

public class SeleniumArch {

    WebDriver driver;
    WebDriverWait wait;
    BufferedWriter report;
    String reportLocation;
    HtmlReporter reporter = new HtmlReporter();
    DecimalFormat df = new DecimalFormat("#.#####");

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
        CorrectReport correction = new CorrectReport();
        correction.correctReport(reportLocation);
    }

    public void setTestTitle(String title) throws IOException{
        reporter.setTestTitle(report, title);
        reporter.writeToReport(report, "<span class=\"timestamp\"></span>");
    }

    public void startTestReport() throws IOException{
        reporter.startTestReport(report);
    }

    public void endTestReport() throws IOException{
        reporter.endTestReport(report);
    }

    public void setReportFile(String path) throws IOException {
        this.reportLocation = "reports/" + path;
        this.report = new BufferedWriter(new FileWriter("reports/" + path));
    }

    public void setWebDriverWait(Integer time) {
        wait = new WebDriverWait(driver, time);
    }

    public String captureScreenshot() {

        File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        String timestamp = ""+ System.currentTimeMillis();

        try {
            FileUtils.copyFile(src, new File("reports/screenshots/" + timestamp + ".png"));
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }

        return "screenshots/" + timestamp + ".png";
    }

    public void clickElement(String locator) throws IOException {

        double start = System.currentTimeMillis();

        try {
            wait.until(ExpectedConditions.elementToBeClickable(new By.ByXPath(locator)));

            double end = System.currentTimeMillis();

            reporter.openStep(report, "PASSED", "Click Element");

                reporter.openAction(report, "PASSED", "Wait until locator is clickable", df.format((end - start) / 1000.0));
                    reporter.openLocator(report, locator);
                reporter.closeAction(report);
        }

        catch(Throwable exc) {

            double end = System.currentTimeMillis();

            reporter.openStep(report, "FAILED", "Click Element");

                reporter.openAction(report, "FAILED", "Wait until locator is clickable",df.format((end - start) / 1000.0));
                    reporter.openLocator(report, locator);
                    reporter.openException(report, exc);
                    reporter.openScreenshot(report, captureScreenshot());
                reporter.closeAction(report);

            reporter.closeStep(report);
            throw exc;
        }

        start = System.currentTimeMillis();

        try {
            driver.findElement(new By.ByXPath(locator)).click();

            double end = System.currentTimeMillis();

                reporter.openAction(report, "PASSED", "Find element and click", df.format((end - start) / 1000.0));
                    reporter.openLocator(report, locator);
                reporter.closeAction(report);

            reporter.closeStep(report);
        }
        catch(Throwable exc) {

            double end = System.currentTimeMillis();

                reporter.openAction(report, "FAILED", "Find element and click", df.format((end - start) / 1000.0));
                    reporter.openLocator(report, locator);
                    reporter.openException(report, exc);
                    reporter.openScreenshot(report, captureScreenshot());
                reporter.closeAction(report);

            reporter.closeStep(report);
            throw exc;
        }
    }

    public void submitElement(String locator) throws IOException{

        double start = System.currentTimeMillis();

        try {
            wait.until(ExpectedConditions.elementToBeClickable(new By.ByXPath(locator)));

            double end = System.currentTimeMillis();

            reporter.openStep(report, "PASSED", "Submit Element");

                reporter.openAction(report, "PASSED", "Wait until locator is clickable", df.format((end - start) / 1000.0));
                    reporter.openLocator(report, locator);
                reporter.closeAction(report);
        }
        catch(Throwable exc) {

            double end = System.currentTimeMillis();

            reporter.openStep(report, "FAILED", "Submit Element");

                reporter.openAction(report, "FAILED", "Wait until locator is clickable", df.format((end - start) / 1000.0));
                    reporter.openLocator(report, locator);
                    reporter.openException(report, exc);
                    reporter.openScreenshot(report, captureScreenshot());
                reporter.closeAction(report);

            reporter.closeStep(report);
            throw exc;
        }

        start = System.currentTimeMillis();

        try {
            driver.findElement(new By.ByXPath(locator)).submit();

            double end = System.currentTimeMillis();

                reporter.openAction(report, "PASSED", "Submit", df.format((end - start) / 1000.0));
                    reporter.openLocator(report, locator);
                reporter.closeAction(report);

            reporter.closeStep(report);
        }
        catch(Throwable exc) {

            double end = System.currentTimeMillis();

                reporter.openAction(report, "FAILED", "Submit", df.format((end - start) / 1000.0));
                    reporter.openLocator(report, locator);
                    reporter.openException(report, exc);
                    reporter.openScreenshot(report, captureScreenshot());
                reporter.closeAction(report);

            reporter.closeStep(report);
            throw exc;
        }
    }

    public void typeInElement(String locator, String message) throws IOException{

        double start = System.currentTimeMillis();

        try {
            wait.until(ExpectedConditions.elementToBeClickable(new By.ByXPath(locator)));

            double end = System.currentTimeMillis();

            reporter.openStep(report, "PASSED", "Type in Element");

                reporter.openAction(report, "PASSED", "Wait until locator is clickable", df.format((end - start) / 1000.0));
                    reporter.openLocator(report, locator);
                reporter.closeAction(report);
        }

        catch(Throwable exc) {

            double end = System.currentTimeMillis();

            reporter.openStep(report, "FAILED", "Type in Element");

                reporter.openAction(report, "FAILED", "Wait until locator is clickable", df.format((end - start) / 1000.0));
                    reporter.openLocator(report, locator);
                    reporter.openException(report, exc);
                    reporter.openScreenshot(report, captureScreenshot());
                reporter.closeAction(report);

            reporter.closeStep(report);
            throw exc;
        }

        start = System.currentTimeMillis();

        try {
            driver.findElement(new By.ByXPath(locator)).sendKeys(message);

            double end = System.currentTimeMillis();

                reporter.openAction(report, "PASSED", "Send Keys " + message, df.format((end - start) / 1000.0));
                    reporter.openLocator(report, locator);
                reporter.closeAction(report);

            reporter.closeStep(report);
        }
        catch(Throwable exc) {

            double end = System.currentTimeMillis();

                reporter.openAction(report, "FAILED", "Send Keys " + message, df.format((end - start) / 1000.0));
                    reporter.openLocator(report, locator);
                    reporter.openException(report, exc);
                    reporter.openScreenshot(report, captureScreenshot());
                reporter.closeAction(report);

            reporter.closeStep(report);
            throw exc;
        }
    }

    public void waitElement(String locator) throws IOException {

        double start = System.currentTimeMillis();

        try {
            wait.until(ExpectedConditions.elementToBeClickable(new By.ByXPath(locator)));

            double end = System.currentTimeMillis();

            reporter.openStep(report, "PASSED", "Wait Element");

                reporter.openAction(report, "PASSED", "Wait until locator is clickable", df.format((end - start) / 1000.0));
                    reporter.openLocator(report, locator);
                reporter.closeAction(report);

            reporter.closeStep(report);
        }

        catch(Throwable exc) {

            double end = System.currentTimeMillis();

            reporter.openStep(report, "FAILED", "Wait Element");

                reporter.openAction(report, "FAILED", "Wait until locator is clickable", df.format((end - start) / 1000.0));
                    reporter.openLocator(report, locator);
                    reporter.openException(report, exc);
                    reporter.openScreenshot(report, captureScreenshot());
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
