package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.ElementsHelper;

import java.util.List;

import static driver.DriverFactory.getDriver;
import static java.util.Arrays.asList;

public class HomePage extends AbstractPage {

    public static final String HOME_PAGE_URL = "https://epam.github.io/JDI/index.html";
    public static final String EXPECTED_TITLE = "Home Page";

    public static final List<String> EXPECTED_UNDER_BENEFIT_ICONS_ELEMENTS_TEXTS = asList(
            "To include good practices\nand ideas from successful\nEPAM project",
            "To be flexible and\ncustomizable",
            "To be multiplatform",
            "Already have good base\n(about 20 internal and\nsome external projects),\nwish to get more…"
    );

    public static final List<String> EXPECTED_MAIN_HEADERS_ELEMENTS_TEXTS = asList(
            "EPAM FRAMEWORK WISHES…",
            "LOREM IPSUM DOLOR SIT AMET, CONSECTETUR ADIPISICING ELIT, SED DO EIUSMOD TEMPOR INCIDIDUNT UT LABORE ET DOLORE MAGNA ALIQUA. " +
                    "UT ENIM AD MINIM VENIAM, QUIS NOSTRUD EXERCITATION ULLAMCO LABORIS NISI UT ALIQUIP EX EA COMMODO CONSEQUAT DUIS AUTE IRURE DOLOR " +
                    "IN REPREHENDERIT IN VOLUPTATE VELIT ESSE CILLUM DOLORE EU FUGIAT NULLA PARIATUR."
    );

    @FindBy(css = ".main-content .text-center[class*='main']")
    private List<WebElement> mainHeaders;

    @FindBy(css = ".main-content h3:nth-of-type(2) [ui='link']")
    private WebElement subHeader;

    @FindBy(css = ".uui-profile-menu .dropdown-toggle")
    private WebElement openLoginFormButton;

    @FindBy(xpath = "//*[text()='Login']/following::input[@id='name']")
    private WebElement loginTextField;

    @FindBy(xpath = "//*[text()='Password']/following::input[@id='password']")
    private WebElement passwordTextField;

    @FindBy(id = "login-button")
    private WebElement submitLoginFormButton;

    @FindBy(id = "iframe")
    private WebElement iframe;

    @FindBy(css = ".benefit-icon")
    protected List<WebElement> benefitIcons;

    @FindBy(xpath = "//div[@class='benefit-icon']/following::span[@class='benefit-txt']")
    protected List<WebElement> underBenefitIconsElements;

    @Override
    protected String getUrl() {
        return HOME_PAGE_URL;
    }

    public void loginAs(String login, String password) {
        openLoginFormButton.click();
        loginTextField.sendKeys(login);
        passwordTextField.sendKeys(password);
        submitLoginFormButton.click();
    }

    public void switchToIframe() {
        getDriver().switchTo().frame(iframe);
    }

    public int getBenefitIconsSize() {
        return benefitIcons.size();
    }

    public boolean allBenefitIconsAreDisplayed() {
        return ElementsHelper.areAllElementsDisplayed(benefitIcons);
    }

    public List<String> getActualUnderBenefitIconsElementsTexts() {
        return ElementsHelper.getElementsText(underBenefitIconsElements);
    }

    public List<String> getActualMainHeadersTexts() {
        return ElementsHelper.getElementsText(mainHeaders);
    }

    public String getSubHeaderText() {
        return subHeader.getText();
    }

    public String getSubHeaderLink() {
        return subHeader.getAttribute("href");
    }

    public boolean isIframeDisplayed() {
        return iframe.isDisplayed();
    }

}
