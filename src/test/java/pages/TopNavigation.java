package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class TopNavigation {

    WebDriver driver;

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

        return currentCurrency;
    }

    public void changeCurrency(String currency){

        currencyDropdown.click();
        //System.out.println(topNavigation.eurCurrency.getText());

        if(currency.equals("eur")){
            eurCurrency.click();
        } else if(currency.equals("uah")){
            uahCurrency.click();
        }else{
            usdCurrency.click();
        }

    }

}
