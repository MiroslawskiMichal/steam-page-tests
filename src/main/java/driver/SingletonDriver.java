package driver;

import org.openqa.selenium.WebDriver;

public class SingletonDriver {

    private static WebDriver driver = null;

    private SingletonDriver() {
        driver = BrowserFactory.createInstance();
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            return driver = BrowserFactory.createInstance();
        }
        return driver;

    }

    public static void closeDriver() {
        driver.quit();
        driver = null;
    }


}
