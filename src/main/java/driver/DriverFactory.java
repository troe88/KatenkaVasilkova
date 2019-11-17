package driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static java.lang.System.setProperty;

public class DriverFactory {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        if (driver.get() == null) {
            initChromeDriver();
        }
        return driver.get();
    }

    private static void initChromeDriver() {
        //setProperty("webdriver.chrome.driver", "src/main/resources/driver/chromedriver.exe");
        //or another way to setup chromedriver:
        WebDriverManager.chromedriver().setup();
        driver.set(new ChromeDriver());
    }

    public static void closeBrowser(){
        driver.get().close();
        driver.set(null);
    }

}
