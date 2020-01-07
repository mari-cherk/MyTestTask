package pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class Page {
    public static Logger log = Logger.getLogger("devpinoyLogger");
    public static final String browser = "chrome";
    public static final String testsiteurl = "http://prestashop-automation.qatestlab.com.ua/ru/";
    public static WebDriver driver;
    public static TopNavigation topNav;

    public static void initConfiguration() {
        driver.get(testsiteurl);
        log.debug("Navigated to " + testsiteurl);
        driver.manage().window().maximize();
        log.debug("The window is maximized");
        topNav = new TopNavigation(driver);
    }

    void waitFor(long mills) {
        try {
            TimeUnit.MILLISECONDS.sleep(mills);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    public static void quitBrowser() {
        driver.quit();
        log.debug("The browser is left");
    }
}
