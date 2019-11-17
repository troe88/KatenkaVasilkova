package utils;

import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver.Window;
import org.openqa.selenium.WebElement;

import java.util.Arrays;

public class LocationHelper {

    public static WhereIsWebElement whereIsWebElementInTheWindow(WebElement we, Window window) {

        Point windowCentre = new Point(
                window.getPosition().x + window.getSize().width / 2,
                window.getPosition().y + window.getSize().height / 2);

        return whereIsWebElementRelativeToPoint(we, windowCentre);
    }

    public static WhereIsWebElement whereIsWebElementInTheWebElement(WebElement weInner, WebElement weOuter) {

        Point weOuterCentre = new Point(
                weOuter.getLocation().x + weOuter.getSize().width / 2,
                weOuter.getLocation().y + weOuter.getSize().height / 2);

        return whereIsWebElementRelativeToPoint(weInner, weOuterCentre);
    }

    private static WhereIsWebElement whereIsWebElementRelativeToPoint(WebElement we, Point point) {

        if (we.isDisplayed()) {

            Point weLeftTopCorner = we.getLocation();
            Point weRightBottomCorner = new Point(weLeftTopCorner.x + we.getSize().width, weLeftTopCorner.y + we.getSize().height);

            WhereIsPoint[] weLeftTopCornerRelativeToCenter = point2DRelativeLocation(weLeftTopCorner, point);
            WhereIsPoint[] weRightBottomCornerRelativeToCenter = point2DRelativeLocation(weRightBottomCorner, point);

            StringBuffer string = new StringBuffer();

            if (weLeftTopCornerRelativeToCenter[0] == weRightBottomCornerRelativeToCenter[0]) {
                string.append(weLeftTopCornerRelativeToCenter[0].toString() + "_");
            } else if (weLeftTopCornerRelativeToCenter[0] == WhereIsPoint.CENTER) {
                string.append(weRightBottomCornerRelativeToCenter[0].toString() + "_");
            } else if (weRightBottomCornerRelativeToCenter[0] == WhereIsPoint.CENTER) {
                string.append(weLeftTopCornerRelativeToCenter[0].toString() + "_");
            } else string.append(WhereIsPoint.CENTER + "_");

            if (weLeftTopCornerRelativeToCenter[1] == weRightBottomCornerRelativeToCenter[1]) {
                string.append(weLeftTopCornerRelativeToCenter[1].toString());
            } else if (weLeftTopCornerRelativeToCenter[1] == WhereIsPoint.CENTER) {
                string.append(weRightBottomCornerRelativeToCenter[1].toString());
            } else if (weRightBottomCornerRelativeToCenter[1] == WhereIsPoint.CENTER) {
                string.append(weLeftTopCornerRelativeToCenter[1].toString());
            } else string.append(WhereIsPoint.CENTER);

            if (string.equals(WhereIsWebElement.CENTER + "_" + WhereIsWebElement.CENTER)) {
                return WhereIsWebElement.CENTER;
            } else {
                return Arrays.stream(WhereIsWebElement.values()).filter(e -> e.toString().equals(string.toString())).findFirst().get();
            }
        }
        return WhereIsWebElement.NOT_DISPLAYED;
    }

    private static WhereIsPoint[] point2DRelativeLocation(Point point, Point bechmark) {

        int px = point.x;
        int py = point.y;
        int bx = bechmark.x;
        int by = bechmark.y;

        return new WhereIsPoint[]{point1DRelativeLocation(px, bx, Axis.X), point1DRelativeLocation(py, by, Axis.Y)};
    }

    private static WhereIsPoint point1DRelativeLocation(int point, int benchmark, Axis axis) {
        return (point < benchmark) ? (axis == Axis.X ? WhereIsPoint.LEFT : WhereIsPoint.TOP) :
                (point > benchmark) ? (axis == Axis.X ? WhereIsPoint.RIGHT : WhereIsPoint.BOTTOM) :
                        WhereIsPoint.CENTER;
    }

    public enum WhereIsWebElement {
        CENTER,
        LEFT_TOP,
        LEFT_BOTTOM,
        LEFT_CENTER,
        RIGHT_TOP,
        RIGHT_CENTER,
        RIGHT_BOTTOM,
        CENTER_TOP,
        CENTER_BOTTOM,
        NOT_DISPLAYED;
    }

    private enum WhereIsPoint {
        TOP,
        BOTTOM,
        LEFT,
        RIGHT,
        CENTER
    }

    private enum Axis {
        X,
        Y
    }
}
