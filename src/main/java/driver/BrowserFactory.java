package driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static utils.JsonUtilities.jsonFileToStringList;

public class BrowserFactory {


    public static WebDriver createInstance() {
        WebDriver driver = null;
        String browserType;

        try {
            browserType = jsonFileToStringList(new File("browserType.json")).get(0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (browserType.equals("CHROME")) {
            File file = new File("browserSettings.json");
            List<String> optionsString = null;
            try {
                optionsString = jsonFileToStringList(file);
                ChromeOptions options = new ChromeOptions();
                options.addArguments(optionsString);
                return driver = WebDriverManager.chromedriver().capabilities(options).create();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
        if (browserType.equals("FIREFOX")) {
            FirefoxProfile profile = new FirefoxProfile();
            profile.setPreference("intl.accept_languages", "eng");
            profile.setPreference("javascript.enabled", true);

            FirefoxOptions firefoxOptions = new FirefoxOptions();
            firefoxOptions.addArguments("-private");

            firefoxOptions.setProfile(profile);

            driver = WebDriverManager.firefoxdriver().capabilities(firefoxOptions).create();
            driver.manage().window().maximize();
            return driver;
        }

        return driver;
    }


}