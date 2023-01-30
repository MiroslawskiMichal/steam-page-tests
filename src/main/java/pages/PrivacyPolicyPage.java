package pages;

import driver.SingletonDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class PrivacyPolicyPage {


    @FindBy(id = "languages")
    private WebElement languageBox;

    @FindBy(xpath = "//i[contains(text(),'Revision Date: ')]")
    private WebElement revisionDateWebE;

    public PrivacyPolicyPage() {
        PageFactory.initElements(SingletonDriver.getDriver(), this);
    }

    public Boolean isLanguageBoxDisplayed() {
        return languageBox.isDisplayed();
    }

    private String parseRevisionDate(WebElement revisionDateWeb) {
        String revisionDateString = (String) revisionDateWeb.getText().subSequence(revisionDateWeb.getText().length() - 4, revisionDateWeb.getText().length());
        return revisionDateString;
    }

    public String getRevisionDate() {
        String revisionDateString = parseRevisionDate(revisionDateWebE);
        return revisionDateString;
    }


    public List<String> getAvailableLanguages() {
        List<WebElement> childrenElementsLanguageBox = languageBox.findElements(By.xpath("./child::*"));
        List<String> hrefs = new ArrayList<>();

        for (WebElement i : childrenElementsLanguageBox) {

            hrefs.add(i.getAttribute("href").replace("https://store.steampowered.com/privacy_agreement/", ""));
            String edit = hrefs.get(hrefs.size() - 1).replace("/", "");
            hrefs.set(hrefs.size() - 1, edit);
        }

        return hrefs;
    }


}
