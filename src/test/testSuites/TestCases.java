package testSuites;

import architecture.SeleniumArch;
import locators.*;
import org.junit.*;

import java.io.IOException;
import java.util.List;

import static junit.framework.TestCase.assertTrue;

/**
 * Created by jose taveira gomes on 19/11/2018.
 */

public class TestCases {

    static SeleniumArch selenium;
    TalkDeskUrls urls = new TalkDeskUrls();
    TalkDeskMainPage mainPage = new TalkDeskMainPage();
    TalkDeskCustomerPage customerPage = new TalkDeskCustomerPage();

    @BeforeClass
    public static void suiteSetup() throws IOException {
        selenium = new SeleniumArch();
        selenium.setReportFile("customersReport.html");
        selenium.startReport("User Story: Customers");
    }

    @Before
    public void testSetup() throws IOException {
        selenium.startTestReport();
        selenium.setWebDriver("chrome");
        selenium.setWebDriverWait(15);
    }

    @After
    public void testTeardown() throws IOException {
        selenium.endTestReport();
        selenium.quit();
    }

    @AfterClass
    public static void suiteTeardown() throws IOException {
        selenium.endReport();
    }

    @Test
    public void logosAreDisplayed() throws IOException {
        selenium.setTestTitle("Logos are displayed");
        selenium.navigateTo(urls.mainPage);
        List<String> mainElements = selenium.countElements(mainPage.logoIdentifier, "data-alt");
        selenium.navigateTo(urls.customers);
        List<String> customerElements = selenium.countElements(customerPage.logoIdentifier, "data-alt");
        assertTrue(selenium.listContains(customerElements, mainElements));
    }

    @Test
    public void countLogosFromList() throws IOException {
        selenium.setTestTitle("Count logos from list");
        selenium.navigateTo(urls.customers);
        selenium.countElements(customerPage.logoIdentifier, "data-alt");
    }
}
