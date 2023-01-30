package driver;

import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import static utils.JsonUtilities.jsonFileToStringList;

public class SingletonWait {
    private static WebDriverWait wait = null;

    public static WebDriverWait getWait() {
        if (wait == null) {
            initializeWait();
        }
        return wait;


    }

    public static void initializeWait() {
        if (wait == null) {
            File file = new File("waitSetting.json");
            try {
                List<String> waitTime = jsonFileToStringList(file);
                wait = new WebDriverWait(SingletonDriver.getDriver(), Duration.ofSeconds(Long.parseLong(waitTime.get(0))));

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }


    }


}
