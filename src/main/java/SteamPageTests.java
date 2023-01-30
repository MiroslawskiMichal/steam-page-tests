import driver.SingletonDriver;
import models.SteamGame;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.MainPage;
import pages.PrivacyPolicyPage;
import pages.ShoppingPage;
import utils.BrowserUtils;
import utils.JsonUtilities;

import java.io.File;
import java.io.IOException;
import java.time.Year;
import java.util.List;


public class SteamPageTests {

    static MainPage mainPage = new MainPage();
    static Capabilities capabilities;

    @BeforeTest
    public  void setupSteamPageTestSuite() {
        capabilities= ((RemoteWebDriver) SingletonDriver.getDriver()).getCapabilities();
        mainPage.getToMP();
        mainPage.resolveCookies();
    }

    @BeforeMethod
    public  void setupSteamPageTest() {

        mainPage.getToMP();
    }

    @AfterTest
    public  void closeAll() {
        SingletonDriver.closeDriver();
    }


    @DataProvider(name = "policy_page")
    public static Object[][] setUpPolicyPageData() throws IOException {
        File file = new File("languages.json");

        List<String> Languages = JsonUtilities.jsonFileToStringList(file);

        return new Object[][]{
                new Object[]{Languages}
        };
    }


    @DataProvider(name = "shopping_page")
    public static Object[][] setUpShoppingPageData() throws IOException {
        File file = new File("games_Dataset.json");

        List<String> gamesDataset = JsonUtilities.jsonFileToStringList(file);

        return new Object[][]{
                new Object[]{gamesDataset}
        };
    }

    @Test(dataProvider = "policy_page")
    public static void privacyPolicyPageTest(List<String> Languages) throws InterruptedException {
        PrivacyPolicyPage pp = new PrivacyPolicyPage();

            mainPage.moveToAndClickPrivacyBtn();

           if (capabilities.getBrowserName().equals("firefox")) {
               Thread.sleep(3000);
           }

            Assert.assertEquals(BrowserUtils.getNewTabTitle(), "Privacy Policy Agreement", "opened tab isn't privacy policy page");
            Assert.assertTrue(pp.isLanguageBoxDisplayed(), "langauge options arent displayed");



        for (String desiredLanguage : Languages) {
            Assert.assertTrue(pp.getAvailableLanguages().contains(desiredLanguage), "doesn't contain " + desiredLanguage);
        }

        Assert.assertEquals(Year.now().toString(), pp.getRevisionDate(), "revision date doesnt equal current date");
    }

    @Test(dataProvider = "shopping_page")
    public static void shoppingPageTest(List<String> gamesDataset) throws InterruptedException {

        ShoppingPage sp =new ShoppingPage();

        sp.searchItem(gamesDataset.get(0));

        Assert.assertTrue(sp.isShopTagDisplayed(), "result page didn't open");

        Assert.assertEquals(sp.getShopSearchTFValue(), gamesDataset.get(0), "search field doesnt contain searched name");

        List<SteamGame> firstSearchResultsGames = sp.getSearchResultsAsArrayList();

        Assert.assertEquals(firstSearchResultsGames.get(0).getName(), gamesDataset.get(0), "First result's name doesnt equal searched name");

        sp.searchItem(firstSearchResultsGames.get(1).getName());

        if (capabilities.getBrowserName().equals("firefox")) {
            Thread.sleep(3000);
        }

        Assert.assertEquals(sp.getShopSearchTFValue(), firstSearchResultsGames.get(1).getName(), "search field doesnt equal the second search");

        List<SteamGame> secondSearchResultsGames = sp.getSearchResultsAsArrayList();


        for (SteamGame game : secondSearchResultsGames) {
            Assert.assertTrue(firstSearchResultsGames.contains(game), "Old search results don't contain " + game.getName());
        }


    }
}
