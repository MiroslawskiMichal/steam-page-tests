package pages;

import driver.SingletonDriver;
import driver.SingletonWait;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.BrowserUtils;
import utils.JsonUtilities;

import java.io.File;
import java.io.IOException;

public class MainPage {

    @FindBy(id = "footer_text")
    private WebElement footerText;

    @FindBy(id = "acceptAllButton")
    private WebElement acceptAllCookies;

    public MainPage() {
        PageFactory.initElements(SingletonDriver.getDriver(), this);
    }

    public void getToMP() {
        try {
            BrowserUtils.navigateToURL(JsonUtilities.jsonFileToStringList(new File("url.json")).get(0));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void moveToAndClickPrivacyBtn() {
        WebElement privacyBtn = footerText.findElement(By.linkText("Privacy Policy"));
        BrowserUtils.moveToElement(privacyBtn);
        SingletonWait.getWait().until(ExpectedConditions.elementToBeClickable(privacyBtn));
        privacyBtn.click();
    }

    public void resolveCookies() {
        SingletonWait.getWait().until(ExpectedConditions.visibilityOf(acceptAllCookies));
        acceptAllCookies.click();
    }

}
