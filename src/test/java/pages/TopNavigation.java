package pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.testng.Reporter;

import static pages.Page.click;

public class TopNavigation {

    WebDriver driver;
    public static Logger log = Logger.getLogger("devpinoyLogger");

    @FindBy(css = ".expand-more._gray-darker.hidden-sm-down")
    public WebElement currency;

    @FindBy(xpath = "//*[@class=\"currency-selector dropdown js-dropdown\"]//*[@class=\"material-icons expand-more\"]")
    WebElement currencyDropdown;

    @FindBy(linkText = "EUR €")
    WebElement uahCurrency;

    @FindBy(linkText = "UAH ₴")
    WebElement eurCurrency;

    @FindBy(linkText = "USD $")
    WebElement usdCurrency;


    public TopNavigation(WebDriver driver){

        this.driver = driver;
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver,10);
        PageFactory.initElements(factory, this);
    }

    public String getCurrency(){

        String currentCurrency = currency.getText().split(" ")[1];
        log.debug("Getting the current currency");
        Reporter.log("Getting the current currency");

        return currentCurrency;
    }

    public void changeCurrency(String currency){

        click(currencyDropdown);

        if(currency.equals("eur")){
            click(eurCurrency);
        } else if(currency.equals("uah")){
            click(uahCurrency);
        }else{
            click(usdCurrency);
        }
        log.debug("Changing the currency");
        Reporter.log("Changing the currency");

    }

}
