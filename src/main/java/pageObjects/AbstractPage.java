package pageObjects;

import enums.NavBarItems;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.ElementsHelper;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static driver.DriverFactory.getDriver;

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

    @FindBy(css = "div.footer-bg")
    protected WebElement footer;

    protected AbstractPage() {
        PageFactory.initElements(getDriver(), this);
    }

    protected abstract String getUrl();

    public void open() {
        getDriver().get(getUrl());
    }

    public boolean isOpen() {
        return (getDriver().getCurrentUrl().equals(getUrl()));
    }

    public String getUserName() {
        return userName.getText();
    }

    public List<String> getActualNavBarElementsTexts() {
        return ElementsHelper.getElementsText(navBarElements);
    }

    public List<String> getExpectedNavBarElementsTexts() {
        return Arrays.stream(NavBarItems.values()).map(NavBarItems::getName).collect(Collectors.toList());
    }

    public int getNavBarElementsSize() {
        return navBarElements.size();
    }

    public boolean allNavBarElementsAreDisplayed() {
        return ElementsHelper.areAllElementsDisplayed(navBarElements);
    }

    public boolean isFooterDisplayed() {
        return footer.isDisplayed();
    }

    public boolean isEpamLogoImageDisplayed() {
        return epamLogoImage.isDisplayed();
    }

    public boolean isLeftSectionDisplayed() {
        return leftSection.isDisplayed();
    }

}
