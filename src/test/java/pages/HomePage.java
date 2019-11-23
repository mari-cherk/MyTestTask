package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class HomePage extends Page {

    //WebDriver driver;

    @FindBy(css = ".price")
    List<WebElement> productsCurrency;

    public List<String> currencySymbols = productsCurrency.stream().map(WebElement::getText).collect(Collectors.toList());


    @FindBy(css = ".ui-autocomplete-input")
    WebElement searchField;

    @FindBy(xpath = "//*[@class='header-top']//*[@class='material-icons search']")
    WebElement searchButton;

    public HomePage(){

        //this.driver = Page.driver;
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver,10);
        PageFactory.initElements(factory,this);

    }

    public SearchPage searchByWord(String searchWord){

        searchField.sendKeys(searchWord);
        searchButton.click();

        return new SearchPage();
    }
}
