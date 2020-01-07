package test;

import org.testng.Assert;
import org.testng.annotations.*;
import pages.DriverManager;
import pages.HomePage;
import pages.Page;

import static pages.Page.topNav;

public class CheckCurrencyTest {

    @BeforeTest
    @Parameters({"browser"})
    public void setUp(String browser) {
        Page.driver = DriverManager.getDriver("browser");
        Page.initConfiguration();
    }

    @Test
    public void checkProductCurrency() {
        HomePage home = new HomePage();
        Assert.assertTrue(home.productsCurrency.stream().anyMatch(x -> x.getText().contains(topNav.getCurrency())), "Wrong currency");
        System.out.println("The current currency is " + topNav.getCurrency());
        System.out.println("Correct currency");
    }

    @AfterTest
    public void tearDown() {
        if (Page.driver != null) {
            Page.quitBrowser();
        }

    }
}
