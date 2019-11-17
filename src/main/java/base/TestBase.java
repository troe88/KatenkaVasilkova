package base;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import static driver.DriverFactory.closeBrowser;
import static driver.DriverFactory.getDriver;
import static java.lang.String.format;

public class TestBase {

    private long time;

    @BeforeSuite(alwaysRun = true)
    public void beforeSuite() {
        time = System.currentTimeMillis();
    }

    @BeforeMethod(alwaysRun = true)
    public void beforeMethod() {
        getDriver().manage().window().maximize();
    }

    @AfterMethod
    public void afterMethod() {
        //17    Close Browser
        closeBrowser();
    }

    @AfterSuite
    public void afterSuite() {
        System.out.println(format("All test suite ran in %d milliseconds", System.currentTimeMillis() - time));
    }
}
