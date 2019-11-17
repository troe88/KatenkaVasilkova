package hw3;

import base.TestBase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import utils.LocationHelper.WhereIsWebElement;

import static driver.DriverFactory.getDriver;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class StructuredHw1Test extends TestBase {

    private HomePage homePage;

    @BeforeMethod(alwaysRun = true)
    public void beforeMethod() {
        super.beforeMethod();
        //1	   Open test site by URL	https://epam.github.io/JDI/
        homePage = new HomePage();
        homePage.open();
    }

    @Test
    public void pageTitleTest() {
        //2	   Assert Browser title is "Home Page"
        assertEquals(getDriver().getTitle(), HomePage.getExpectedTitle());
    }

    @Test
    public void loginTest() {
        //3    Perform login "username: epam, pass: 1234"
        homePage.loginAs("epam", "1234");

        //4    Assert User name in the left-top side of screen that user is loggined "PITER CHAILOVSKII"
        assertEquals(homePage.getUserName(), "PITER CHAILOVSKII");
    }

    @Test
    public void pageTitleTestAgain() {
        //5	   Assert Browser title "Home Page"
        pageTitleTest();
    }

    @Test
    public void navBarItemsTest() {
        //6	   Assert that there are 4 items on the header section are displayed and they have proper texts "HOME", "CONTACT FORM", "SERVICE", "METALS & COLORS"
        assertTrue(homePage.getNavBarElementsSize() == 4
                && homePage.allNavBarElementsAreDisplayed()
                && homePage.getActualNavBarElementsTexts().equals(homePage.getExpectedNavBarElementsTexts())
        );
    }

    @Test
    public void benefitIconsTest() {
        //7	   Assert that there are 4 images on the Index Page and they are displayed	4 images
        assertTrue(homePage.getBenefitIconsSize() == 4 && homePage.allBenefitIconsAreDisplayed());
    }

    @Test
    public void textUnderBenefitIconsTest() {
        //8	   Assert that there are 4 texts on the Index Page under icons and they have proper text
        assertTrue(homePage.getBenefitIconsSize() == 4
                && homePage.allBenefitIconsAreDisplayed()
                && homePage.getActualUnderBenefitIconsElementsTexts().equals(homePage.getExpectedUnderBenefitIconsTexts())
        );
    }

    @Test
    public void mainHeadersTextTest() {
        //9	   Assert a text of the main headers "EPAM FRAMEWORK WISHES…" and "LOREM IPSUM..."
        assertEquals(homePage.getActualMainHeadersTexts(), homePage.getExpectedMainHeadersElementsTexts());
    }

    @Test
    public void iframeIsDisplayedOnPageTest() {
        //10    Assert that there is the iframe in the center of page
        assertTrue(homePage.getIframeLocationInTheWindow().toString().contains("CENTER"));
    }

    @Test
    public void epamLogoInIframeTest() {
        //11    Switch to the iframe and check that there is Epam logo in the left top conner of iframe
        homePage.switchToIframe();
        assertEquals(homePage.getEpamLogoLocationInTheIframe(), WhereIsWebElement.LEFT_TOP);
    }

    @Test
    public void subHeaderTextTest() {
        //13    Assert a text of the sub header	JDI GITHUB
        assertEquals(homePage.getSubHeaderText(), "JDI GITHUB");
    }

    @Test
    public void subHeaderLinkTest() {
        //14    Assert that JDI GITHUB is a link and has a proper URL https://github.com/epam/JDI
        assertEquals(homePage.getSubHeaderLink(), "https://github.com/epam/JDI");
    }

    @Test
    public void leftSectionIsDisplayedOnPageTest() {
        //15    Assert that there is Left Section
        assertTrue(homePage.getLeftSectionLocationInTheWindow().toString().contains("LEFT"));
    }

    @Test
    public void footerIsDisplayedTest() {
        //16    Assert that there is Footer
        assertTrue(homePage.footerIsDisplayed());
    }

}
