package test;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.Page;

public class CheckCurrencyTest {

    @BeforeTest
    public void setUp() {

        Page.initConfiguration();
    }

    @Test
    public void checkProductCurrency() {

        HomePage home = new HomePage();

        for (String currencySymbol : home.currencySymbols) {

            Assert.assertEquals(currencySymbol.split(" ")[1], home.topNav.getCurrency(), "Wrong currency");
        }

        System.out.println("The current currency is " + home.topNav.getCurrency());
        System.out.println("Correct currency");

    }


    @AfterTest
    public void tearDown() {
        if (Page.driver != null) {
            Page.quitBrowser();
        }

    }
}
