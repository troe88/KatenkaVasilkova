package hw2.ex1;

import base.TestBaseForUnderBenefitIconsTest;
import data.DataProviders;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.List;

import static driver.DriverFactory.getDriver;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class UnderBenefitIconsTextsTest extends TestBaseForUnderBenefitIconsTest {

    @Test(dataProvider = "expectedTextsUnderCorrespondingIcons", dataProviderClass = DataProviders.class)
    public void underBenefitIconsTextsTest(int iconIndex, String expectedTextUnderIconWithIndex) {

        //Exercise 1:
        //Develop a dedicated test for asserting 4 texts below 4 pictures on the Index Page -https://epam.github.io/JDI/index.html.
        //The test must be developed with help of the DataProvider.
        //Run it in the parallel by methods through the configuring parameters in a @DataProvider annotation.

        String iconsClass = "benefit-icon";
        List<WebElement> underBenefitIconsElements = getDriver()
                .findElements(By.xpath("//div[@class='" + iconsClass + "']/following::span[@class='benefit-txt']"));

        assertEquals(underBenefitIconsElements.size(), 4);
        assertTrue(underBenefitIconsElements.stream().allMatch(WebElement::isDisplayed));
        assertEquals(expectedTextUnderIconWithIndex, underBenefitIconsElements.get(iconIndex).getText());
    }

}
