package pages;

import org.decimal4j.util.DoubleRounder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.testng.Reporter;

import java.util.ArrayList;
import java.util.List;

public class SearchPage extends Page {

    @FindBy(css = ".col-md-6.hidden-sm-down.total-products")
    WebElement totalNumberResults;

    @FindBy(css = ".price")
    public List<WebElement> foundProducts;

    @FindBy(css = ".material-icons.pull-xs-right")
    WebElement sortDropdown;

    @FindBy(xpath = "//*[@class='dropdown-menu']/a[4]")
    WebElement increaseItem;

    @FindBy(css = ".product-description")
    List<WebElement> productBlocks;

    @FindBy(css = ".thumbnail-container")
    List<WebElement> productContainers;

    public SearchPage() {
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, 10);
        PageFactory.initElements(factory, this);
    }

    public int getNumberResults() {
        int numberResults = Integer.parseInt(totalNumberResults.getText().replaceAll("\\D+", ""));
        //int numberResults = Integer.parseInt(numberResultsString);
        log.debug(numberResults + " search results are get");
        Reporter.log(numberResults + " search results are get");
        return numberResults;
    }

    public void setIncreaseSort() {
        sortDropdown.click();
        log.debug("The Sort dropdown is expanded");
        Reporter.log("The Sort dropdown is expanded");
        increaseItem.click();
        log.debug("The sort from low to high is chosen");
        Reporter.log("The sort from low to high is chosen");
        waitFor(4000);
    }

    public List checkPrices() {
        List<Integer> priceOfFoundProducts = new ArrayList<>();
        for (WebElement productBlock : productBlocks) {
            if (productBlock.findElements(By.className("regular-price")).size() > 0) {
                Integer priceint = Integer.parseInt(productBlock.findElement(By.className("regular-price")).getText().replaceAll("\\D+", ""));
                //Integer priceint = Integer.parseInt(price);
                priceOfFoundProducts.add(priceint);
            } else {
                Integer priceint = Integer.parseInt(productBlock.findElement(By.className("price")).getText().replaceAll("\\D+", ""));
                //Integer priceint = Integer.parseInt(price);
                priceOfFoundProducts.add(priceint);
            }
        }
        log.debug("Displayed prices and discounts are compared");
        Reporter.log("Displayed prices and discounts are compared");
        List<Boolean> priceCheck = new ArrayList<>();
        for (int i = 1; i < priceOfFoundProducts.size(); i++) {
            if (priceOfFoundProducts.get(i) >= priceOfFoundProducts.get(i - 1)) {
                priceCheck.add(true);
            } else
                priceCheck.add(false);
        }
        log.debug("The order of prices is checked");
        Reporter.log("The order of prices is checked");
        return priceCheck;
    }

    public List checkDiscount() {
        List<Boolean> discountsCheck = new ArrayList<>();
        for (WebElement productContainer : productContainers) {
            if (productContainer.findElements(By.className("regular-price")).size() > 0) {
                int percent = Integer.parseInt(productContainer.findElement(By.className("discount-percentage")).getText().replaceAll("\\D", ""));
                String oldpriceString = (productContainer.findElement(By.className("regular-price")).getText());
                double oldPrice = Double.parseDouble(oldpriceString.substring(0, oldpriceString.length() - 1).replace(",", "."));
                String newPriceString = productContainer.findElement(By.className("price")).getText();
                double newPrice = Double.parseDouble(newPriceString.substring(0, oldpriceString.length() - 1).replace(",", "."));
                double culNewPriceLong = oldPrice - oldPrice * percent / 100;
                double culNewPrice = DoubleRounder.round(culNewPriceLong, 2);
                if (newPrice == culNewPrice) {
                    discountsCheck.add(true);
                } else {
                    discountsCheck.add(false);
                }
                System.out.println(oldPrice + " - " + oldPrice + " * " + percent + " /100 = " + newPrice);
            }
        }
        log.debug("Discounts are calculated");
        Reporter.log("Discounts are calculated");
        return discountsCheck;
    }
}