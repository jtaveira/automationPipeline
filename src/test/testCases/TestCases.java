package testCases;

import architecture.SeleniumArch;
import locators.*;
import org.junit.*;

/**
 * Created by jose taveira gomes on 19/11/2018.
 */

public class TestCases {

    SeleniumArch selenium;
    PageUrl urls = new PageUrl();
    Header header = new Header();
    LoginPage login = new LoginPage();
    DashboardPage dash = new DashboardPage();

    @BeforeClass
    public static void suiteSetup() {

    }

    @Before
    public void testSetup() {
        selenium = new SeleniumArch();
    }

    @After
    public void testTeardown() {
        selenium.quit();
    }

    @AfterClass
    public static void suiteTeardown() {

    }

    @Test
    public void testLogin() {

        selenium.navigateTo(urls.login);
        selenium.typeInElement(login.usernameInput, "testprovider@inhealth.ae");
        selenium.typeInElement(login.passwordInput, "Test123!");
        selenium.submitElement(login.passwordInput);
        selenium.waitElement(dash.eligibilityButton);
    }

    @Test
    public void testLogin2() {

        selenium.navigateTo(urls.login);
        selenium.typeInElement(login.usernameInput, "testprovider@inhealth.ae");
        selenium.typeInElement(login.passwordInput, "Test123!");
        selenium.submitElement(login.passwordInput);
        selenium.waitElement(dash.eligibilityButton);
    }
}
