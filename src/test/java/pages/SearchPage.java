package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import java.util.List;

public class SearchPage extends Page {

    @FindBy(css = ".col-md-6.hidden-sm-down.total-products")
    WebElement totalNumberResults;

    @FindBy(css = ".price")
    List<WebElement> foundProducts;

    @FindBy(css = ".material-icons.pull-xs-right")
    WebElement sortDropdown;

    @FindBy(partialLinkText = "Цене: от низкой к высокой")
    WebElement increaseItem;

    @FindBy(css = ".product-description")
    List<WebElement> productBlocks;

    public SearchPage(){

        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver,10);
        PageFactory.initElements(factory,this);
    }

    public int getNumberResults() {

        String numberResultsString = totalNumberResults.getText().replaceAll("\\D+","");
        int numberResults = Integer.parseInt(numberResultsString);
        return numberResults;
    }

    public void setIncreaseSort(){

        sortDropdown.click();
        increaseItem.click();
    }
}
