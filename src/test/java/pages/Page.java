package pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Reporter;

import java.util.concurrent.TimeUnit;

public class Page {

    public static Logger log = Logger.getLogger("devpinoyLogger");
    public static final String browser = "chrome";
    public static final String testsiteurl = "http://prestashop-automation.qatestlab.com.ua/ru/";

    public static WebDriver driver;
    public static TopNavigation topNav;



    public static void initConfiguration(){

        if(browser.equals("firefox")) {

            System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "\\src\\test\\resources\\geckodriver.exe");
            driver = new FirefoxDriver();
            log.debug("Launching Firefox");
        } else if(browser.equals("chrome")){

            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\src\\test\\resources\\chromedriver.exe");
            driver = new ChromeDriver();
            log.debug("Launching Chrome");

        } else if (browser.equals("ie")) {

            System.setProperty("webdriver.ie.driver", System.getProperty("user.dir") + "\\src\\test\\resources\\IEDriverServer.exe");
            driver = new InternetExplorerDriver();
            log.debug("Launching IE");
        }

        driver.get(testsiteurl);
        log.debug("Navigated to " + testsiteurl);
        driver.manage().window().maximize();
        log.debug("Maximizing the window");
        topNav = new TopNavigation(driver);

    }

    public static void click(WebElement element) {

        element.click();
        log.debug("Clicking on an Element : "+element);
        Reporter.log("Clicking on an Element : "+element);

    }


    public static void type(WebElement element, String value) {

        element.sendKeys(value);

        log.debug("Typing in an Element : "+element+" entered value as : "+value);
        Reporter.log("Typing in an Element : "+element+" entered value as : "+value);

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
        log.debug("Leaving the browser");
    }
}
