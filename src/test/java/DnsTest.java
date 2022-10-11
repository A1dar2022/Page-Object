import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import other.TestProperties;
import other.Trash;
import pages.BasketPage;
import pages.MainPage;
import pages.ProductPage;
import pages.ResultsPage;
import java.util.concurrent.TimeUnit;

public class DnsTest {
    WebDriver driver;

    @Before
    public void init(){
        System.setProperty("webdriver.chrome.driver", TestProperties.getInstance().getProperty("path.chrome"));
        driver = new ChromeDriver();
        driver.get(TestProperties.getInstance().getProperty("url"));
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        Trash.driver = driver;

    }


    @Test
    public void checkDnsShop(){
        MainPage mainPage = new MainPage();

        ResultsPage resultsPage = mainPage.search("playstation");
        resultsPage.chooseProduct(TestProperties.getInstance().getProperty("product"));

        ProductPage productPage = new ProductPage();
        productPage.savePriceOfCurrentProduct();
        productPage.addGaranty(TestProperties.getInstance().getProperty("guarantee"));
        productPage.refreshPrice();
        productPage.addToBasket();

        productPage.search(TestProperties.getInstance().getProperty("game"));
        ProductPage gameResultPage = new ProductPage();
        gameResultPage.savePriceOfCurrentProduct();
        gameResultPage.addToBasket();
        gameResultPage.checkTotalPriceIs();

        BasketPage basketPage = gameResultPage.goToBasket();
        basketPage.checkGuarantee(TestProperties.getInstance().getProperty("product"));
        basketPage.checkEveryPriceAndSum();
        /*

        BasketPage basketPage = productCard.goToBasket();
        basketPage.checkTotalPriceIs(Trash.get("product macbook"));*/

    }

    @After
    public void stopTest() throws Exception {
        //driver.quit();
    }
}

