package utils;

import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

/*
TODO
Basically, it is prohibited to be able to inherit from 'Helper' class kinds and create an instance of it.
Could you please change it in order to match this rules.
 */
public class ElementsHelper {

    public static boolean areAllElementsDisplayed(List<WebElement> webElementList) {
        return webElementList.stream().allMatch(WebElement::isDisplayed);
    }

    public static List<String> getElementsText(List<WebElement> webElementList) {
        return webElementList.stream().map(WebElement::getText).collect(Collectors.toList());
    }

}
