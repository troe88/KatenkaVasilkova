package base;

import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import static driver.DriverFactory.closeBrowser;
import static driver.DriverFactory.getDriver;

public class TestBaseForUnderBenefitIconsTest {

    private static final String INDEX_PAGE_URL = "https://epam.github.io/JDI/index.html";
    private long time;

    @BeforeSuite(alwaysRun = true)
    public void beforeSuite() {
        time = System.currentTimeMillis();
    }

    @BeforeMethod(alwaysRun = true)
    public void beforeMethod() {

        getDriver().manage().window().maximize();

        if (!getDriver().getCurrentUrl().equals(INDEX_PAGE_URL)) {
            getDriver().get(INDEX_PAGE_URL);
        }

        boolean signedIn = getDriver().findElement(By.cssSelector("#user-name")).isDisplayed();
        if (!signedIn) {
            signInAs("epam", "1234");
        }
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod() {
        closeBrowser();
    }

    @AfterSuite(alwaysRun = true)
    public void afterSuite() {
        System.out.println(String.format("All suite tests run at %d milliseconds", System.currentTimeMillis() - time));
    }

    private void signInAs(String login, String password) {
        getDriver().findElement(By.className("profile-photo")).click();
        getDriver().findElement(By.id("name")).sendKeys(login);
        getDriver().findElement(By.id("password")).sendKeys(password);
        getDriver().findElement(By.id("login-button")).click();
    }

}
