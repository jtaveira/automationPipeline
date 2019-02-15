package architecture;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
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
import java.util.ArrayList;
import java.util.List;

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

    String passed = "PASSED";
    String failed = "FAILED";
    String waitClickable = "Wait until locator is clickable";
    String clickElement = "Click Element";
    String findElementAndClick = "Find element and click";
    String countElements = "Count Elements";
    String submitElement = "Submit Element";
    String submit = "Submit";
    String typeInElement = "Type in Element";
    String sendKeys = "Send Keys ";
    String waitElement = "Wait Element";
    String navigateTo = "Navigate To";
    String driverGetUrl = "Driver get url";
    String listIsContained = "List is contained in";
    String listAContainsB = "List A contains List B";
    String misingElements = "Missing Elements: ";

    public void setWebDriver(String browser){

        if(browser == "chrome"){
            System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver.exe");
            driver = new ChromeDriver();
        }
        
        else if(browser == "firefox"){
            System.setProperty("webdriver.gecko.driver", "drivers\\geckodriver.exe");
            driver = new FirefoxDriver();
        }
    }

    public void startReport(String suiteTitle) throws IOException{
        reporter.startReport(report, suiteTitle);
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
        new File("reports").mkdir();
        this.reportLocation = "reports/" + path;
        this.report = new BufferedWriter(new FileWriter("reports/" + path));
    }

    public void setWebDriverWait(Integer time) {
        wait = new WebDriverWait(driver, time);
    }

    private void openStepFailed(String locator, double start, Exception exc, String clickElement, String waitClickable) throws IOException {
        double end = System.currentTimeMillis();
        String time = df.format((end - start) / 1000.0);

        reporter.openStep(report, failed, clickElement);
            reporter.openAction(report, failed, waitClickable, time);
                reporter.openLocator(report, locator);
                reporter.openException(report, exc);
                reporter.openScreenshot(report, captureScreenshot());
            reporter.closeAction(report);
        reporter.closeStep(report);
    }

    private void openStepSuccess(String locator, double start, String countElements, String waitClickable) throws IOException {
        double end = System.currentTimeMillis();
        String time = df.format((end - start) / 1000.0);

        reporter.openStep(report, passed, countElements);
            reporter.openAction(report, passed, waitClickable, time);
                reporter.openLocator(report, locator);
            reporter.closeAction(report);
    }

    private void openActionFailed(String locator, double start, Exception exc, String findElementAndClick) throws IOException {
        double end = System.currentTimeMillis();
        String time = df.format((end - start) / 1000.0);

            reporter.openAction(report, failed, findElementAndClick, time);
                reporter.openLocator(report, locator);
                reporter.openException(report, exc);
                reporter.openScreenshot(report, captureScreenshot());
            reporter.closeAction(report);
        reporter.closeStep(report);
    }

    private void openActionSuccess(String locator, double start, String findElementAndClick) throws IOException {
        double end = System.currentTimeMillis();
        String time = df.format((end - start) / 1000.0);

            reporter.openAction(report, passed, findElementAndClick, time);
                reporter.openLocator(report, locator);
            reporter.closeAction(report);
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
            openStepSuccess(locator, start, clickElement, waitClickable);
        }

        catch(Exception exc) {

            openStepFailed(locator, start, exc, clickElement, waitClickable);
            throw exc;
        }

        start = System.currentTimeMillis();

        try {
            driver.findElement(new By.ByXPath(locator)).click();
            openActionSuccess(locator, start, findElementAndClick);
            reporter.closeStep(report);
        }
        catch(Exception exc) {

            openActionFailed(locator, start, exc, findElementAndClick);
            throw exc;
        }
    }

    public List<String> countElements(String locator, String attribute) throws IOException {

        double start = System.currentTimeMillis();

        try {
            wait.until(ExpectedConditions.elementToBeClickable(new By.ByXPath(locator)));
            openStepSuccess(locator, start, countElements, waitClickable);
        }

        catch(Exception exc) {

            openStepFailed(locator, start, exc, countElements, waitClickable);
            throw exc;
        }

        start = System.currentTimeMillis();

        try {

            List<WebElement> elements = driver.findElements(new By.ByXPath(locator));

            openActionSuccess(locator, start, "Counted " + elements.size() + " elements");
            reporter.closeStep(report);

            List<String> stringElements = new ArrayList<>();

            for(WebElement element : elements){
                stringElements.add(element.getAttribute(attribute));
            }

            return stringElements;
        }
        catch(Exception exc) {

            openActionFailed(locator, start, exc, "Counted ?? elements");
            throw exc;
        }
    }

    public void submitElement(String locator) throws IOException{

        double start = System.currentTimeMillis();

        try {
            wait.until(ExpectedConditions.elementToBeClickable(new By.ByXPath(locator)));
            openStepSuccess(locator, start, submitElement, waitClickable);
        }
        catch(Exception exc) {

            openStepFailed(locator, start, exc, submitElement, waitClickable);
            throw exc;
        }

        start = System.currentTimeMillis();

        try {
            driver.findElement(new By.ByXPath(locator)).submit();
            openActionSuccess(locator, start, submit);
            reporter.closeStep(report);
        }
        catch(Exception exc) {

            openActionFailed(locator, start, exc, submit);
            throw exc;
        }
    }

    public void typeInElement(String locator, String message) throws IOException{

        double start = System.currentTimeMillis();

        try {
            wait.until(ExpectedConditions.elementToBeClickable(new By.ByXPath(locator)));
            openStepSuccess(locator, start, typeInElement, waitClickable);
        }

        catch(Exception exc) {

            openStepFailed(locator, start, exc, typeInElement, waitClickable);
            throw exc;
        }

        start = System.currentTimeMillis();

        try {
            driver.findElement(new By.ByXPath(locator)).sendKeys(message);
            openActionSuccess(locator, start, sendKeys + message);
            reporter.closeStep(report);
        }
        catch(Exception exc) {

            openActionFailed(locator, start, exc, sendKeys + message);
            throw exc;
        }
    }

    public void waitElement(String locator) throws IOException {

        double start = System.currentTimeMillis();

        try {
            wait.until(ExpectedConditions.elementToBeClickable(new By.ByXPath(locator)));
            openStepSuccess(locator, start, waitElement, waitClickable);
            reporter.closeStep(report);
        }

        catch(Exception exc) {

            openStepFailed(locator, start, exc, waitElement, waitClickable);
            throw exc;
        }
    }

    public void navigateTo(String url) throws IOException{

        double start = System.currentTimeMillis();

        try {
            driver.get(url);
            openStepSuccess(url, start, navigateTo, driverGetUrl);
            reporter.closeStep(report);
        }

        catch(Exception exc) {

            openStepFailed(url, start, exc, navigateTo, driverGetUrl);
            throw exc;
        }
    }

    public boolean listContains(List<String> listA, List<String> listB) throws IOException {

        double start = System.currentTimeMillis();

        boolean contains = listA.containsAll(listB);

        if(contains) {
            double end = System.currentTimeMillis();
            String time = df.format((end - start) / 1000.0);

            reporter.openStep(report, passed, listIsContained);
                reporter.openAction(report, passed, listAContainsB, time);
                reporter.closeAction(report);
            reporter.closeStep(report);

            return true;
        }

        else {

            double end = System.currentTimeMillis();
            String time = df.format((end - start) / 1000.0);

            List<String> missing = listB;
            missing.removeAll(listA);

            String message = "";

            for(String element : missing){
                message += " " + element;
            }

            reporter.openStep(report, failed, listIsContained);
                reporter.openAction(report, failed, listAContainsB, time);
                    reporter.openLocator(report, misingElements + message);
                reporter.closeAction(report);
            reporter.closeStep(report);

            return false;
        }
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
