package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import java.util.List;

public class HomePage extends Page {

    @FindBy(css = ".price")
    public List<WebElement> productsCurrency;

    @FindBy(css = ".ui-autocomplete-input")
    WebElement searchField;

    @FindBy(xpath = "//*[@class='header-top']//*[@class='material-icons search']")
    WebElement searchButton;

    public HomePage(){

        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver,10);
        PageFactory.initElements(factory,this);

    }

    public SearchPage searchByWord(String searchWord){

        type(searchField,searchWord);
        click(searchButton);

        return new SearchPage();
    }
}
