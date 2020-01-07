package pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.testng.Reporter;

public class TopNavigation {

    WebDriver driver;
    public static Logger log = Logger.getLogger("devpinoyLogger");

    @FindBy(css = ".expand-more._gray-darker.hidden-sm-down")
    public WebElement currency;

    @FindBy(xpath = "//*[@class='currency-selector dropdown js-dropdown']//*[@class='material-icons expand-more']")
    WebElement currencyDropdown;

    @FindBy(linkText = "EUR €")
    WebElement uahCurrency;

    @FindBy(linkText = "UAH ₴")
    WebElement eurCurrency;

    @FindBy(linkText = "USD $")
    WebElement usdCurrency;

    public TopNavigation(WebDriver driver) {
        this.driver = driver;
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, 10);
        PageFactory.initElements(factory, this);
    }

    public String getCurrency() {
        String currentCurrency = currency.getText().split(" ")[1];
        log.debug(currentCurrency + " is get as current currency");
        Reporter.log(currentCurrency + " is get as current currency");
        return currentCurrency;
    }

    public void changeCurrency(String currency) {
        currencyDropdown.click();
        log.debug("The currency dropdown is expanded");
        Reporter.log("The currency dropdown is expanded");
        if (currency.equals("eur")) {
            eurCurrency.click();
            log.debug("The EUR currency is chosen");
            Reporter.log("The EUR currency is chosen");
        } else if (currency.equals("uah")) {
            uahCurrency.click();
            log.debug("The UAH currency is chosen");
            Reporter.log("The UAH currency is chosen");
        } else {
            usdCurrency.click();
            log.debug("The USD currency is chosen");
            Reporter.log("The USD currency is chosen");
        }
    }

}
