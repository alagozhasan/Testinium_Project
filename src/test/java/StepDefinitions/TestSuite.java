package StepDefinitions;

import Pages.BeymenHomePage;
import Pages.Parent;
import Utilities.BaseDriver;
import Utilities.DataUtilities;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;


public class TestSuite extends Parent {
    private static final Logger logger = Logger.getLogger(TestSuite.class);
    BeymenHomePage homePage = new BeymenHomePage();

    @Test
    public void SearchTest() {
        GoHomePage();
        clickFunction(homePage.getWebElement("acceptCookies"));
        clickFunction(homePage.getWebElement("selectGender"));


        String path = "src\\test\\java\\Resources\\Datas.xlsx";
        ArrayList<ArrayList<String>> datas = DataUtilities.getData(path, "Sheet1", 2);
        String short_ = datas.get(0).get(0);
        String shirt_ = datas.get(0).get(1);
        logger.info("Excelden,veriler çekildi.");

        searchSteps(short_, shirt_);

        int choice = Hooks.generateRandomNumber(homePage.getProductList().size());

        WebElement randomProduct = homePage.getProductList().get(choice);

        DataUtilities.writeToTxt(randomProduct.getText());
        clickFunction(randomProduct);
        logger.info("Ürün seçildi src/output/output-gg-aa-yyyy hh-mm.txt");
        float ProductPrice = Hooks.converterFloat(homePage.getWebElement("price").getText());
        logger.info("Ürün fiyatı alındı : " + ProductPrice);
        addToCart();

        float cartPrice = Hooks.converterFloat(homePage.getWebElement("cartPrice").getText());

        Assertions.assertEquals(cartPrice, ProductPrice, "Ürün fiyatı ve sepette gözüken fiyatı farklı");

        assertQuantity();
        removeItemAndAssert();
    }

    void searchSteps(String short_, String shirt_) {
        sendKeysFunction(homePage.getWebElement("searchInput"), short_);
        sendKeysFunction(homePage.getWebElement("searchInput"), shirt_);
        homePage.getWebElement("searchInput").sendKeys(Keys.ENTER);
        logger.info(shirt_ + " arandı,bekleniyor");
    }

    void addToCart() {
        clickFunction(homePage.getWebElement("size"));
        try {
            clickFunction(homePage.getWebElement("addToBasket"));

            clickFunction(homePage.getWebElement("cart"));
            logger.info("Ürün sepete eklendi.");
            waitUntilVisible(homePage.getWebElement("cartPrice"));

        } catch (Exception e) {
            logger.error("Sepete ürün ekleme işlemi başarısız,bildirim gelse de sepet boş.\n" + e.getMessage());
            Assertions.fail("Test başarısız,tekrar run etmeniz gerek.");
//            SearchTest();//opsiyonel
        }
    }


    void assertQuantity() {
        try {
            waitUntilClickable(homePage.getWebElement("selectQuantity"));

            homePage.getSelectQuantity().selectByValue("2");

            String selectedText = homePage.getSelectQuantity().getFirstSelectedOption().getText();

            Assertions.assertTrue(selectedText.contains("2"));
        } catch (Exception e) {
            logger.error("Bu ürün için adet 1 den fazla değil.");
            logger.info("Sonraki adıma geçildi. ");
        }
    }

    void removeItemAndAssert() {
        clickFunction(homePage.getWebElement("removeProduct"));

        Assertions.assertTrue(homePage.getWebElement("emptyCartMessage").isDisplayed());
        logger.info("Sepet tamamen boş.");
    }

    void GoHomePage() {
        WebDriver driver = BaseDriver.getDriver();
        driver.get("https://www.beymen.com/");

        WebElement logo = homePage.getWebElement("siteLogo");

        Assertions.assertTrue(logo.isDisplayed());
        logger.info("Ana sayfa açıldı.");

    }
    @AfterEach
    public void closeDriver() {
        BaseDriver.quitDriver();
    }


}
