package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.testng.Reporter;

import java.util.List;

public class HomePage extends Page {

    @FindBy(css = ".price")
    public List<WebElement> productsCurrency;

    @FindBy(css = ".ui-autocomplete-input")
    WebElement searchField;

    @FindBy(xpath = "//*[@class='header-top']//*[@class='material-icons search']")
    WebElement searchButton;

    public HomePage() {
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, 10);
        PageFactory.initElements(factory, this);
    }

    public SearchPage searchByWord(String searchWord) {
        searchField.sendKeys(searchWord);
        log.debug(searchWord + " is typed in the search field");
        Reporter.log(searchWord + " is typed in the search field");
        searchButton.click();
        log.debug("The search button is clicked");
        Reporter.log("The search button is clicked");
        return new SearchPage();
    }
}
