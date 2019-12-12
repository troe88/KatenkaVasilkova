package utils;

import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class ElementsHelper {

    public static boolean areAllElementsDisplayed(List<WebElement> webElementList) {
        return webElementList.stream().allMatch(WebElement::isDisplayed);
    }

    public static List<String> getElementsText(List<WebElement> webElementList) {
        return webElementList.stream().map(WebElement::getText).collect(Collectors.toList());
    }

}
