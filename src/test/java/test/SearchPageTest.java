package test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.HomePage;
import pages.Page;
import pages.SearchPage;

import static pages.Page.topNav;

public class SearchPageTest {

    @BeforeTest
    public void setUp(){

        Page.initConfiguration();
    }

    @Test
    public void siteSearch(){

        HomePage home = new HomePage();
        SearchPage search = new SearchPage();

        topNav.changeCurrency("usd");

        home.searchByWord("dress");
        Assert.assertEquals(search.foundProducts.size(), search.getNumberResults(), "Wrong number of the Search Results");
        System.out.println("Correct number of the Search Results is " + search.getNumberResults());

        Assert.assertTrue(search.foundProducts.stream().anyMatch(x -> x.getText().contains(topNav.getCurrency())), "Wrong currency");
        System.out.println("The current currency is " + topNav.getCurrency());
        System.out.println("Correct currency");

        search.setIncreaseSort();

        search.checkPrices().forEach(x -> Assert.assertEquals(x, true, "Wrong sort"));
        System.out.println("Correct sort");

        search.checkDiscount().forEach(x -> Assert.assertEquals(x, true, "Wrong sort"));
        System.out.println("Correct discounts");

    }


    @AfterTest
    public void tearDown(){
        if(Page.driver!=null){
            Page.quitBrowser();
        }
    }
}
