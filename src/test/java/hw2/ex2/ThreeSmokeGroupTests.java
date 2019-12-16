package hw2.ex2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.System.setProperty;
import static java.util.Arrays.asList;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class ThreeSmokeGroupTests {

    @Test(groups = "Smoke")
    public void simpleTestWithHardAsserts11() {

        //set driver properties
        setProperty("webdriver.chrome.driver", "src/main/resources/driver/chromedriver.exe");

        //Open Chrome browser
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        //1	   Open test site by URL	https://epam.github.io/JDI/
        driver.get("https://epam.github.io/JDI/");

        //2	   Assert Browser title is "Home Page"
        assertEquals(driver.getTitle(), "Home Page");

        //3    Perform login "username: epam, pass: 1234"
        driver.findElement(By.className("profile-photo")).click();
        driver.findElement(By.id("name")).sendKeys("epam");
        driver.findElement(By.id("password")).sendKeys("1234");
        driver.findElement(By.id("login-button")).click();

        //4    Assert User name in the left-top side of screen that user is loggined "PITER CHAILOVSKII"
        assertEquals(driver.findElement(By.id("user-name")).getText(), "PITER CHAILOVSKII");

        //5	   Assert Browser title "Home Page"
        assertEquals(driver.getTitle(), "Home Page");

        //6	   Assert that there are 4 items on the header section are displayed and they have proper texts "HOME", "CONTACT FORM", "SERVICE", "METALS & COLORS"
        List<WebElement> navBarElements = driver.findElements(By.cssSelector(".uui-navigation.nav > li > a"));
        List<String> actualNavBarElementsTexts = navBarElements.stream().map(WebElement::getText).collect(Collectors.toList());
        List<String> expectedNavBarElementsTexts = asList("HOME", "CONTACT FORM", "SERVICE", "METALS & COLORS");
        //splited assertion:
        assertEquals(navBarElements.size(), 4);
        assertTrue(navBarElements.stream().allMatch(WebElement::isDisplayed));
        assertEquals(actualNavBarElementsTexts.toArray(), expectedNavBarElementsTexts.toArray());
        //OR the same assertion joined:
        assertTrue(
                navBarElements.size() == 4
                        && navBarElements.stream().allMatch(WebElement::isDisplayed)
                        && actualNavBarElementsTexts.equals(expectedNavBarElementsTexts)
        );

        //7	   Assert that there are 4 images on the Index Page and they are displayed	4 images
        String benefitIconsClass = "benefit-icon";
        List<WebElement> benefitIcons = driver.findElements(By.className(benefitIconsClass));
        //splited assertion:
        assertEquals(benefitIcons.size(), 4);
        assertTrue(benefitIcons.stream().allMatch(WebElement::isDisplayed));
        //OR the same assertion joined:
        assertTrue(benefitIcons.size() == 4 && benefitIcons.stream().allMatch(WebElement::isDisplayed));

        //8	   Assert that there are 4 texts on the Index Page under icons and they have proper text
        List<WebElement> underBenefitIconsElements = driver.findElements(By.xpath("//div[@class='" + benefitIconsClass + "']/following::span[@class='benefit-txt']"));
        List<String> actualUnderBenefitIconsElementsTexts = underBenefitIconsElements.stream().map(WebElement::getText).collect(Collectors.toList());
        List<String> expectedUnderBenefitIconsElementsTexts = asList(
                "To include good practices\nand ideas from successful\nEPAM project",
                "To be flexible and\ncustomizable",
                "To be multiplatform",
                "Already have good base\n(about 20 internal and\nsome external projects),\nwish to get more…"
        );
        //splited assertion:
        assertEquals(underBenefitIconsElements.size(), 4);
        assertTrue(underBenefitIconsElements.stream().allMatch(WebElement::isDisplayed));
        assertEquals(actualUnderBenefitIconsElementsTexts.toArray(), expectedUnderBenefitIconsElementsTexts.toArray());
        //OR the same assertion joined:
        assertTrue(
                underBenefitIconsElements.size() == 4
                        && underBenefitIconsElements.stream().allMatch(WebElement::isDisplayed)
                        && actualUnderBenefitIconsElementsTexts.equals(expectedUnderBenefitIconsElementsTexts)
        );

        //9	   Assert a text of the main headers "EPAM FRAMEWORK WISHES…" and "LOREM IPSUM..."
        List<WebElement> mainHeadersElements = driver.findElements(By.cssSelector("div[class='main-content'] > [class^='main']"));
        List<String> actualMainHeadersElementsTexts = mainHeadersElements.stream().map(WebElement::getText).collect(Collectors.toList());
        List<String> expectedMainHeadersElementsTexts = asList(
                "EPAM FRAMEWORK WISHES…",
                "LOREM IPSUM DOLOR SIT AMET, CONSECTETUR ADIPISICING ELIT, SED DO EIUSMOD TEMPOR INCIDIDUNT UT LABORE ET DOLORE MAGNA ALIQUA. " +
                        "UT ENIM AD MINIM VENIAM, QUIS NOSTRUD EXERCITATION ULLAMCO LABORIS NISI UT ALIQUIP EX EA COMMODO CONSEQUAT DUIS AUTE IRURE DOLOR " +
                        "IN REPREHENDERIT IN VOLUPTATE VELIT ESSE CILLUM DOLORE EU FUGIAT NULLA PARIATUR."
        );
        //splited assertion:
        assertTrue(mainHeadersElements.stream().allMatch(WebElement::isDisplayed));
        assertEquals(actualMainHeadersElementsTexts.toArray(), expectedMainHeadersElementsTexts.toArray());
        //OR the same assertion joined:
        assertTrue(
                mainHeadersElements.stream().allMatch(WebElement::isDisplayed)
                        && actualMainHeadersElementsTexts.equals(expectedMainHeadersElementsTexts)
        );

        //10    Assert that there is the iframe in the center of page
        WebElement iframe = driver.findElement(By.cssSelector("iframe"));
        assertTrue(iframe.isDisplayed());

        //11    Switch to the iframe and check that there is Epam logo in the left top conner of iframe
        driver.switchTo().frame(iframe);
        assertTrue(driver.findElement(By.cssSelector("nav img#epam_logo")).isDisplayed());

        //12    Switch to original window back
        driver.switchTo().defaultContent();

        //13    Assert a text of the sub header	JDI GITHUB
        String subHeaderXpath = "//div[@class='main-content']/h3/following::h3";
        WebElement subHeader = driver.findElement(By.xpath(subHeaderXpath));
        assertEquals(subHeader.getText(), "JDI GITHUB");

        //14    Assert that JDI GITHUB is a link and has a proper URL https://github.com/epam/JDI
        assertEquals(driver.findElement(By.linkText("JDI GITHUB")).getAttribute("href"), "https://github.com/epam/JDI");

        //15    Assert that there is Left Section
        List<WebElement> mainContainer = driver.findElements(By.cssSelector("body > div > div"));
        //check if there are more than one block and one of them is located on the left
        assertTrue(
                mainContainer.size() > 1
                        && mainContainer.stream().anyMatch(e -> e.getLocation().x == 0 && e.isDisplayed())
        );

        //16    Assert that there is Footer
        assertTrue(driver.findElement(By.cssSelector("footer")).isDisplayed());

        //17    Close Browser
        driver.close();
    }

    @Test(groups = "Smoke")
    public void simpleTestWithHardAsserts12() {

        //set driver properties
        setProperty("webdriver.chrome.driver", "src/main/resources/driver/chromedriver.exe");

        //Open Chrome browser
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        //1	   Open test site by URL	https://epam.github.io/JDI/
        driver.get("https://epam.github.io/JDI/");

        //2	   Assert Browser title is "Home Page"
        assertEquals(driver.getTitle(), "Home Page");

        //3    Perform login "username: epam, pass: 1234"
        driver.findElement(By.className("profile-photo")).click();
        driver.findElement(By.id("name")).sendKeys("epam");
        driver.findElement(By.id("password")).sendKeys("1234");
        driver.findElement(By.id("login-button")).click();

        //4    Assert User name in the left-top side of screen that user is loggined "PITER CHAILOVSKII"
        assertEquals(driver.findElement(By.id("user-name")).getText(), "PITER CHAILOVSKII");

        //5	   Assert Browser title "Home Page"
        assertEquals(driver.getTitle(), "Home Page");

        //6	   Assert that there are 4 items on the header section are displayed and they have proper texts "HOME", "CONTACT FORM", "SERVICE", "METALS & COLORS"
        List<WebElement> navBarElements = driver.findElements(By.cssSelector(".uui-navigation.nav > li > a"));
        List<String> actualNavBarElementsTexts = navBarElements.stream().map(WebElement::getText).collect(Collectors.toList());
        List<String> expectedNavBarElementsTexts = asList("HOME", "CONTACT FORM", "SERVICE", "METALS & COLORS");
        //splited assertion:
        assertEquals(navBarElements.size(), 4);
        assertTrue(navBarElements.stream().allMatch(WebElement::isDisplayed));
        assertEquals(actualNavBarElementsTexts.toArray(), expectedNavBarElementsTexts.toArray());
        //OR the same assertion joined:
        assertTrue(
                navBarElements.size() == 4
                        && navBarElements.stream().allMatch(WebElement::isDisplayed)
                        && actualNavBarElementsTexts.equals(expectedNavBarElementsTexts)
        );

        //7	   Assert that there are 4 images on the Index Page and they are displayed	4 images
        String benefitIconsClass = "benefit-icon";
        List<WebElement> benefitIcons = driver.findElements(By.className(benefitIconsClass));
        //splited assertion:
        assertEquals(benefitIcons.size(), 4);
        assertTrue(benefitIcons.stream().allMatch(WebElement::isDisplayed));
        //OR the same assertion joined:
        assertTrue(benefitIcons.size() == 4 && benefitIcons.stream().allMatch(WebElement::isDisplayed));

        //8	   Assert that there are 4 texts on the Index Page under icons and they have proper text
        List<WebElement> underBenefitIconsElements = driver.findElements(By.xpath("//div[@class='" + benefitIconsClass + "']/following::span[@class='benefit-txt']"));
        List<String> actualUnderBenefitIconsElementsTexts = underBenefitIconsElements.stream().map(WebElement::getText).collect(Collectors.toList());
        List<String> expectedUnderBenefitIconsElementsTexts = asList(
                "To include good practices\nand ideas from successful\nEPAM project",
                "To be flexible and\ncustomizable",
                "To be multiplatform",
                "Already have good base\n(about 20 internal and\nsome external projects),\nwish to get more…"
        );
        //splited assertion:
        assertEquals(underBenefitIconsElements.size(), 4);
        assertTrue(underBenefitIconsElements.stream().allMatch(WebElement::isDisplayed));
        assertEquals(actualUnderBenefitIconsElementsTexts.toArray(), expectedUnderBenefitIconsElementsTexts.toArray());
        //OR the same assertion joined:
        assertTrue(
                underBenefitIconsElements.size() == 4
                        && underBenefitIconsElements.stream().allMatch(WebElement::isDisplayed)
                        && actualUnderBenefitIconsElementsTexts.equals(expectedUnderBenefitIconsElementsTexts)
        );

        //9	   Assert a text of the main headers "EPAM FRAMEWORK WISHES…" and "LOREM IPSUM..."
        List<WebElement> mainHeadersElements = driver.findElements(By.cssSelector("div[class='main-content'] > [class^='main']"));
        List<String> actualMainHeadersElementsTexts = mainHeadersElements.stream().map(WebElement::getText).collect(Collectors.toList());
        List<String> expectedMainHeadersElementsTexts = asList(
                "EPAM FRAMEWORK WISHES…",
                "LOREM IPSUM DOLOR SIT AMET, CONSECTETUR ADIPISICING ELIT, SED DO EIUSMOD TEMPOR INCIDIDUNT UT LABORE ET DOLORE MAGNA ALIQUA. " +
                        "UT ENIM AD MINIM VENIAM, QUIS NOSTRUD EXERCITATION ULLAMCO LABORIS NISI UT ALIQUIP EX EA COMMODO CONSEQUAT DUIS AUTE IRURE DOLOR " +
                        "IN REPREHENDERIT IN VOLUPTATE VELIT ESSE CILLUM DOLORE EU FUGIAT NULLA PARIATUR."
        );
        //splited assertion:
        assertTrue(mainHeadersElements.stream().allMatch(WebElement::isDisplayed));
        assertEquals(actualMainHeadersElementsTexts.toArray(), expectedMainHeadersElementsTexts.toArray());
        //OR the same assertion joined:
        assertTrue(
                mainHeadersElements.stream().allMatch(WebElement::isDisplayed)
                        && actualMainHeadersElementsTexts.equals(expectedMainHeadersElementsTexts)
        );

        //10    Assert that there is the iframe in the center of page
        WebElement iframe = driver.findElement(By.cssSelector("iframe"));
        assertTrue(iframe.isDisplayed());

        //11    Switch to the iframe and check that there is Epam logo in the left top conner of iframe
        driver.switchTo().frame(iframe);
        assertTrue(driver.findElement(By.cssSelector("nav img#epam_logo")).isDisplayed());

        //12    Switch to original window back
        driver.switchTo().defaultContent();

        //13    Assert a text of the sub header	JDI GITHUB
        String subHeaderXpath = "//div[@class='main-content']/h3/following::h3";
        WebElement subHeader = driver.findElement(By.xpath(subHeaderXpath));
        assertEquals(subHeader.getText(), "JDI GITHUB");

        //14    Assert that JDI GITHUB is a link and has a proper URL https://github.com/epam/JDI
        assertEquals(driver.findElement(By.linkText("JDI GITHUB")).getAttribute("href"), "https://github.com/epam/JDI");

        //15    Assert that there is Left Section
        List<WebElement> mainContainer = driver.findElements(By.cssSelector("body > div > div"));
        //check if there are more than one block and one of them is located on the left
        assertTrue(
                mainContainer.size() > 1
                        && mainContainer.stream().anyMatch(e -> e.getLocation().x == 0 && e.isDisplayed())
        );

        //16    Assert that there is Footer
        assertTrue(driver.findElement(By.cssSelector("footer")).isDisplayed());

        //17    Close Browser
        driver.close();
    }

    @Test(groups = "Smoke")
    public void simpleTestWithHardAsserts13() {

        //set driver properties
        setProperty("webdriver.chrome.driver", "src/main/resources/driver/chromedriver.exe");

        //Open Chrome browser
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        //1	   Open test site by URL	https://epam.github.io/JDI/
        driver.get("https://epam.github.io/JDI/");

        //2	   Assert Browser title is "Home Page"
        assertEquals(driver.getTitle(), "Home Page");

        //3    Perform login "username: epam, pass: 1234"
        driver.findElement(By.className("profile-photo")).click();
        driver.findElement(By.id("name")).sendKeys("epam");
        driver.findElement(By.id("password")).sendKeys("1234");
        driver.findElement(By.id("login-button")).click();

        //4    Assert User name in the left-top side of screen that user is loggined "PITER CHAILOVSKII"
        assertEquals(driver.findElement(By.id("user-name")).getText(), "PITER CHAILOVSKII");

        //5	   Assert Browser title "Home Page"
        assertEquals(driver.getTitle(), "Home Page");

        //6	   Assert that there are 4 items on the header section are displayed and they have proper texts "HOME", "CONTACT FORM", "SERVICE", "METALS & COLORS"
        List<WebElement> navBarElements = driver.findElements(By.cssSelector(".uui-navigation.nav > li > a"));
        List<String> actualNavBarElementsTexts = navBarElements.stream().map(WebElement::getText).collect(Collectors.toList());
        List<String> expectedNavBarElementsTexts = asList("HOME", "CONTACT FORM", "SERVICE", "METALS & COLORS");
        //splited assertion:
        assertEquals(navBarElements.size(), 4);
        assertTrue(navBarElements.stream().allMatch(WebElement::isDisplayed));
        assertEquals(actualNavBarElementsTexts.toArray(), expectedNavBarElementsTexts.toArray());
        //OR the same assertion joined:
        assertTrue(
                navBarElements.size() == 4
                        && navBarElements.stream().allMatch(WebElement::isDisplayed)
                        && actualNavBarElementsTexts.equals(expectedNavBarElementsTexts)
        );

        //7	   Assert that there are 4 images on the Index Page and they are displayed	4 images
        String benefitIconsClass = "benefit-icon";
        List<WebElement> benefitIcons = driver.findElements(By.className(benefitIconsClass));
        //splited assertion:
        assertEquals(benefitIcons.size(), 4);
        assertTrue(benefitIcons.stream().allMatch(WebElement::isDisplayed));
        //OR the same assertion joined:
        assertTrue(benefitIcons.size() == 4 && benefitIcons.stream().allMatch(WebElement::isDisplayed));

        //8	   Assert that there are 4 texts on the Index Page under icons and they have proper text
        List<WebElement> underBenefitIconsElements = driver.findElements(By.xpath("//div[@class='" + benefitIconsClass + "']/following::span[@class='benefit-txt']"));
        List<String> actualUnderBenefitIconsElementsTexts = underBenefitIconsElements.stream().map(WebElement::getText).collect(Collectors.toList());
        List<String> expectedUnderBenefitIconsElementsTexts = asList(
                "To include good practices\nand ideas from successful\nEPAM project",
                "To be flexible and\ncustomizable",
                "To be multiplatform",
                "Already have good base\n(about 20 internal and\nsome external projects),\nwish to get more…"
        );
        //splited assertion:
        assertEquals(underBenefitIconsElements.size(), 4);
        assertTrue(underBenefitIconsElements.stream().allMatch(WebElement::isDisplayed));
        assertEquals(actualUnderBenefitIconsElementsTexts.toArray(), expectedUnderBenefitIconsElementsTexts.toArray());
        //OR the same assertion joined:
        assertTrue(
                underBenefitIconsElements.size() == 4
                        && underBenefitIconsElements.stream().allMatch(WebElement::isDisplayed)
                        && actualUnderBenefitIconsElementsTexts.equals(expectedUnderBenefitIconsElementsTexts)
        );

        //9	   Assert a text of the main headers "EPAM FRAMEWORK WISHES…" and "LOREM IPSUM..."
        List<WebElement> mainHeadersElements = driver.findElements(By.cssSelector("div[class='main-content'] > [class^='main']"));
        List<String> actualMainHeadersElementsTexts = mainHeadersElements.stream().map(WebElement::getText).collect(Collectors.toList());
        List<String> expectedMainHeadersElementsTexts = asList(
                "EPAM FRAMEWORK WISHES…",
                "LOREM IPSUM DOLOR SIT AMET, CONSECTETUR ADIPISICING ELIT, SED DO EIUSMOD TEMPOR INCIDIDUNT UT LABORE ET DOLORE MAGNA ALIQUA. " +
                        "UT ENIM AD MINIM VENIAM, QUIS NOSTRUD EXERCITATION ULLAMCO LABORIS NISI UT ALIQUIP EX EA COMMODO CONSEQUAT DUIS AUTE IRURE DOLOR " +
                        "IN REPREHENDERIT IN VOLUPTATE VELIT ESSE CILLUM DOLORE EU FUGIAT NULLA PARIATUR."
        );
        //splited assertion:
        assertTrue(mainHeadersElements.stream().allMatch(WebElement::isDisplayed));
        assertEquals(actualMainHeadersElementsTexts.toArray(), expectedMainHeadersElementsTexts.toArray());
        //OR the same assertion joined:
        assertTrue(
                mainHeadersElements.stream().allMatch(WebElement::isDisplayed)
                        && actualMainHeadersElementsTexts.equals(expectedMainHeadersElementsTexts)
        );

        //10    Assert that there is the iframe in the center of page
        WebElement iframe = driver.findElement(By.cssSelector("iframe"));
        assertTrue(iframe.isDisplayed());

        //11    Switch to the iframe and check that there is Epam logo in the left top conner of iframe
        driver.switchTo().frame(iframe);
        assertTrue(driver.findElement(By.cssSelector("nav img#epam_logo")).isDisplayed());

        //12    Switch to original window back
        driver.switchTo().defaultContent();

        //13    Assert a text of the sub header	JDI GITHUB
        String subHeaderXpath = "//div[@class='main-content']/h3/following::h3";
        WebElement subHeader = driver.findElement(By.xpath(subHeaderXpath));
        assertEquals(subHeader.getText(), "JDI GITHUB");

        //14    Assert that JDI GITHUB is a link and has a proper URL https://github.com/epam/JDI
        assertEquals(driver.findElement(By.linkText("JDI GITHUB")).getAttribute("href"), "https://github.com/epam/JDI");

        //15    Assert that there is Left Section
        List<WebElement> mainContainer = driver.findElements(By.cssSelector("body > div > div"));
        //check if there are more than one block and one of them is located on the left
        assertTrue(
                mainContainer.size() > 1
                        && mainContainer.stream().anyMatch(e -> e.getLocation().x == 0 && e.isDisplayed())
        );

        //16    Assert that there is Footer
        assertTrue(driver.findElement(By.cssSelector("footer")).isDisplayed());

        //17    Close Browser
        driver.close();
    }

}
