package testSuites;

import architecture.SeleniumArch;
import locators.*;
import org.junit.*;

import java.io.IOException;

/**
 * Created by jose taveira gomes on 19/11/2018.
 */

public class TestCases {

    static SeleniumArch selenium;
    PageUrl urls = new PageUrl();
    Header header = new Header();
    LoginPage login = new LoginPage();
    DashboardPage dash = new DashboardPage();

    @BeforeClass
    public static void suiteSetup() throws IOException {
        selenium = new SeleniumArch();
        selenium.setReportFile("testReport.html");
        selenium.startReport("TestCases");
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
    public void testLogin() throws IOException {

        selenium.setTestTitle("Test Login 001");
        selenium.navigateTo(urls.login);
        selenium.typeInElement(login.usernameInput, "testprovider@inhealth.ae");
        selenium.typeInElement(login.passwordInput, "Test123!");
        selenium.submitElement(login.passwordInput);
        selenium.waitElement(dash.eligibilityButton);
    }

    @Test
    public void testLogin2() throws IOException {

        selenium.setTestTitle("Test Login 002");
        selenium.navigateTo(urls.login);
        selenium.typeInElement(login.usernameInput, "testprovider@inhealth.ae");
        selenium.typeInElement(login.passwordInput, "Test123!");
        selenium.submitElement(login.passwordInput);
        selenium.waitElement(dash.eligibilityButton);
    }

    @Test
    public void failLogin() throws IOException {

        selenium.setTestTitle("Test Fail Login 001");
        selenium.navigateTo(urls.login);
        selenium.typeInElement(login.usernameInput, "nhecas@inhealth.ae");
        selenium.typeInElement(login.passwordInput, "Test123!");
        selenium.submitElement(login.passwordInput);
        selenium.clickElement(dash.eligibilityButton);
    }
}
