package pageObjects;

import enums.NavBarItems;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.LocationHelper;
import utils.LocationHelper.WhereIsWebElement;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static driver.DriverFactory.getDriver;

// TODO This is a bit weird inheritance, read comments below within "INH" mark.
public abstract class AbstractPage {

    @FindBy(css = "div.uui-header")
    protected WebElement header;

    @FindBy(css = "img#epam_logo")
    protected WebElement epamLogoImage;

    @FindBy(css = ".uui-navigation.navbar-nav.nav > li")
    protected List<WebElement> navBarElements;

    @FindBy(css = "body > div > div")
    protected WebElement leftSection;

    @FindBy(id = "user-name")
    protected WebElement userName;

    // TODO INH Only home page contains this elements;
    @FindBy(css = ".benefit-icon")
    protected List<WebElement> benefitIcons;

    // TODO INH same like 34 line
    @FindBy(xpath = "//div[@class='benefit-icon']/following::span[@class='benefit-txt']")
    protected List<WebElement> underBenefitIconsElements;

    @FindBy(css = "div.footer-bg")
    protected WebElement footer;


    protected AbstractPage() {
        PageFactory.initElements(getDriver(), this);
    }

    protected abstract String getUrl();

    public void open() {
        getDriver().get(getUrl());
    }

    // TODO You should not push useless method in master branch.
    public void openIfIsNot() {
        if (!isOpen()) {
            getDriver().get(getUrl());
        }
    }

    public boolean isOpen() {
        return (getDriver().getCurrentUrl().equals(getUrl()));
    }

    public String getUserName() {
        return userName.getText();
    }

    public List<String> getActualNavBarElementsTexts() {
        return getElementsText(navBarElements);
    }

    public List<String> getExpectedNavBarElementsTexts() {
        return Arrays.stream(NavBarItems.values()).map(NavBarItems::getName).collect(Collectors.toList());
    }

    public int getNavBarElementsSize() {
        return getSize(navBarElements);
    }

    public boolean allNavBarElementsAreDisplayed() {
        return allElementsAreDisplayed(navBarElements);
    }

    public WhereIsWebElement getLeftSectionLocationInTheWindow() {
        return getElementLocationInTheWindow(leftSection);
    }

    public boolean isFooterDisplayed() {
        return footer.isDisplayed();
    }

    // TODO This 4 methods should not belong PO at all. It looks like utils/helpers/whatever methods.
    protected WhereIsWebElement getElementLocationInTheWindow(WebElement webElement) {
        return LocationHelper.whereIsWebElementInTheWindow(webElement, getDriver().manage().window());
    }

    // TODO Boolean methods should be named with 'is' prefix.
    protected boolean allElementsAreDisplayed(List<WebElement> webElementList) {
        return webElementList.stream().allMatch(WebElement::isDisplayed);
    }

    // TODO Is that really make a difference to create such wrapper ? How can it helps you ?
    protected int getSize(List<WebElement> webElementList) {
        return webElementList.size();
    }

    protected List<String> getElementsText(List<WebElement> webElementList) {
        return webElementList.stream().map(WebElement::getText).collect(Collectors.toList());
    }

}
