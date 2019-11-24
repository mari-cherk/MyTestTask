package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import java.util.ArrayList;
import java.util.List;

public class SearchPage extends Page {

    @FindBy(css = ".col-md-6.hidden-sm-down.total-products")
    WebElement totalNumberResults;

    @FindBy(css = ".price")
    public List<WebElement> foundProducts;

    @FindBy(css = ".material-icons.pull-xs-right")
    WebElement sortDropdown;

    @FindBy(partialLinkText = "Цене: от низкой к высокой")
    WebElement increaseItem;

    @FindBy(css = ".product-description")
    List<WebElement> productBlocks;

    public SearchPage() {

        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, 10);
        PageFactory.initElements(factory, this);
    }

    public int getNumberResults() {

        String numberResultsString = totalNumberResults.getText().replaceAll("\\D+", "");
        int numberResults = Integer.parseInt(numberResultsString);
        return numberResults;
    }

    public void setIncreaseSort() {

        sortDropdown.click();
        increaseItem.click();
    }

    public List checkPrices(){

        waitFor(4000);

        List<Integer> priceOfFoundProducts = new ArrayList<>();

        for (WebElement productBlock : productBlocks) {

            if (productBlock.findElements(By.className("regular-price")).size() > 0) {

                String price = productBlock.findElement(By.className("regular-price")).getText().replaceAll("\\D+", "");
                Integer priceint = Integer.parseInt(price);

                priceOfFoundProducts.add(priceint);
            } else {

                String price = productBlock.findElement(By.className("price")).getText().replaceAll("\\D+", "");
                Integer priceint = Integer.parseInt(price);

                priceOfFoundProducts.add(priceint);
            }

        }
        List<Boolean> priceCheck = new ArrayList<>();

        for(int i = 1; i < priceOfFoundProducts.size(); i++){

            if(priceOfFoundProducts.get(i) >= priceOfFoundProducts.get(i - 1)){

                priceCheck.add(true);
            } else
                priceCheck.add(false);

        }
        return priceCheck;
    }
}
