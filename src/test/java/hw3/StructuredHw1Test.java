package hw3;

import base.TestBase;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageObjects.HomePage;

import static driver.DriverFactory.getDriver;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class StructuredHw1Test extends TestBase {

    private HomePage homePage;

    @BeforeTest(alwaysRun = true)
    public void beforeMethod() {
        //1	   Open test site by URL	https://epam.github.io/JDI/
        homePage = new HomePage();
        homePage.open();
    }

    @Test
    public void homePageTest() {

        //2	   Assert Browser title is "Home Page"
        assertEquals(getDriver().getTitle(), HomePage.EXPECTED_TITLE);

        //3    Perform login "username: epam, pass: 1234"
        homePage.loginAs("epam", "1234");

        //4    Assert User name in the left-top side of screen that user is loggined "PITER CHAILOVSKII"
        assertEquals(homePage.getUserName(), "PITER CHAILOVSKII");

        //5	   Assert Browser title "Home Page"
        assertEquals(getDriver().getTitle(), HomePage.EXPECTED_TITLE);

        //6	   Assert that there are 4 items on the header section are displayed and they have proper texts "HOME", "CONTACT FORM", "SERVICE", "METALS & COLORS"
        assertTrue(homePage.getNavBarElementsSize() == 4
                && homePage.allNavBarElementsAreDisplayed()
                && homePage.getActualNavBarElementsTexts().equals(homePage.getExpectedNavBarElementsTexts())
        );

        //7	   Assert that there are 4 images on the Index Page and they are displayed	4 images
        assertTrue(homePage.getBenefitIconsSize() == 4 && homePage.allBenefitIconsAreDisplayed());

        //8	   Assert that there are 4 texts on the Index Page under icons and they have proper text
        assertTrue(homePage.getBenefitIconsSize() == 4
                && homePage.allBenefitIconsAreDisplayed()
                && homePage.getActualUnderBenefitIconsElementsTexts().equals(HomePage.EXPECTED_UNDER_BENEFIT_ICONS_ELEMENTS_TEXTS)
        );

        //9	   Assert a text of the main headers "EPAM FRAMEWORK WISHES…" and "LOREM IPSUM..."
        assertEquals(homePage.getActualMainHeadersTexts(), HomePage.EXPECTED_MAIN_HEADERS_ELEMENTS_TEXTS);

        //10    Assert that there is the iframe in the center of page
        assertTrue(homePage.isIframeDisplayed());

        //11    Switch to the iframe and check that there is Epam logo in the left top conner of iframe
        homePage.switchToIframe();
        assertTrue(homePage.isEpamLogoImageDisplayed());

        //13    Assert a text of the sub header	JDI GITHUB
        assertEquals(homePage.getSubHeaderText(), "JDI GITHUB");

        //14    Assert that JDI GITHUB is a link and has a proper URL https://github.com/epam/JDI
        assertEquals(homePage.getSubHeaderLink(), "https://github.com/epam/JDI");

        //15    Assert that there is Left Section
        assertTrue(homePage.isLeftSectionDisplayed());

        //16    Assert that there is Footer
        assertTrue(homePage.isFooterDisplayed());
    }

}
