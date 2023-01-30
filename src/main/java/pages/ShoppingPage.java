package pages;

import driver.SingletonDriver;
import driver.SingletonWait;
import models.Platforms;
import models.SteamGame;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class ShoppingPage {

    @FindBy(id = "store_nav_search_term")
    private WebElement shopSearchTF;

    @FindBy(id = "term")
    private WebElement headerShopSearchTF;

    @FindBy(xpath = "//span[@class='label' and contains(text(),'Dota 2')]")
    private WebElement shopTag;

    @FindBy(id = "search_resultsRows")
    private WebElement resultsDataPanel;

    public ShoppingPage() {
        PageFactory.initElements(SingletonDriver.getDriver(), this);
    }

    public Boolean isShopTagDisplayed() {
        return shopTag.isDisplayed();
    }

    public String getShopSearchTFValue() {
        SingletonWait.getWait().until(ExpectedConditions.visibilityOf(shopTag));
        return headerShopSearchTF.getAttribute("value");
    }

    public List<SteamGame> getSearchResultsAsArrayList() {
        List<WebElement> childrenResultsDataPanel = resultsDataPanel.findElements(By.className("responsive_search_name_combined"));
        List<SteamGame> resultsGames = new ArrayList<>();

        for (int i = 0; i < 2; i++) {

            SteamGame game = new SteamGame();

            String title = childrenResultsDataPanel.get(i).findElement(By.className("title")).getText();
            String date = childrenResultsDataPanel.get(i).findElement(By.className("search_released")).getText();
            String price = childrenResultsDataPanel.get(i).findElement(By.className("search_price_discount_combined")).getText();

            List<WebElement> review = childrenResultsDataPanel.get(i).findElements(By.className("search_review_summary"));
            if (review.size() > 0) {
                game.setName(title);
                game.setReleaseDate(date);
                game.setReviewSummaryResult(review.get(0).getAttribute("data-tooltip-html"));
                game.setPrice(price);

            } else {
                game.setName(title);
                game.setReleaseDate(date);
                game.setPrice(price);
            }

            List<WebElement> platforms = childrenResultsDataPanel.get(i).findElements(By.className("platform_img"));
            if (platforms.size() > 0) {
                Platforms platforms1 = new Platforms();
                for (WebElement elementPlatform : platforms)

                    if (elementPlatform.getAttribute("class").contains("win")) {
                        platforms1.setIsWindows(true);
                        ;
                    } else if (elementPlatform.getAttribute("class").contains("mac")) {
                        platforms1.setIsMac(true);

                    } else if (elementPlatform.getAttribute("class").contains("linux")) {
                        platforms1.setIsLinux(true);

                    } else if (elementPlatform.getAttribute("class").contains("music")) {
                        platforms1.setIsMusic(true);

                    } else if (elementPlatform.getAttribute("class").contains("streamingvideo")) {
                        platforms1.setIsMovie(true);

                    }
                game.setPlatforms(platforms1);
                resultsGames.add(game);

            }


        }

        return resultsGames;
    }

    public void searchItem(String myInput) {
        shopSearchTF.clear();
        shopSearchTF.sendKeys(myInput);
        shopSearchTF.sendKeys(Keys.RETURN);
        SingletonWait.getWait().until(ExpectedConditions.visibilityOf(shopTag));
    }
}
