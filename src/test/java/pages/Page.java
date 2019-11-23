package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.util.concurrent.TimeUnit;

public class Page {

    public static final String browser = "chrome";
    public static final String testsiteurl = "http://prestashop-automation.qatestlab.com.ua/ru/";

    public static WebDriver driver;
    public static TopNavigation topNav;



    public static void initConfiguration(){

        if(browser.equals("firefox")) {

            System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "\\src\\test\\resources\\geckodriver.exe");
            driver = new FirefoxDriver();
        } else if(browser.equals("chrome")){

            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\src\\test\\resources\\chromedriver.exe");
            driver = new ChromeDriver();

        } else if (browser.equals("ie")) {

            System.setProperty("webdriver.ie.driver", System.getProperty("user.dir") + "\\src\\test\\resources\\IEDriverServer.exe");
            driver = new InternetExplorerDriver();
        }

        driver.get(testsiteurl);
        driver.manage().window().maximize();
        topNav = new TopNavigation(driver);

    }

    void waitFor(long mills) {
        try {
            TimeUnit.MILLISECONDS.sleep(mills);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    public static void quitBrowser(){

        driver.quit();
    }
}
