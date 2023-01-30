package utils;

import driver.SingletonDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class BrowserUtils {
    public static String getNewTabTitle() {
        List<String> browserTabs = new ArrayList<String>(SingletonDriver.getDriver().getWindowHandles());
        SingletonDriver.getDriver().switchTo().window(browserTabs.get(browserTabs.size() - 1));
        String titlePrivacyPage = SingletonDriver.getDriver().getTitle();


        return titlePrivacyPage;

    }

    public static void moveToElement(WebElement e) {
        JavascriptExecutor js = (JavascriptExecutor) SingletonDriver.getDriver();
        js.executeScript("arguments[0].scrollIntoView(true);", e);
    }

    public static void navigateToURL(String url) {
        SingletonDriver.getDriver().get(url);
    }


}

