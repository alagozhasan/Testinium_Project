package Pages;

import Utilities.BaseDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class BeymenHomePage extends Parent {
    public BeymenHomePage() {
        PageFactory.initElements(BaseDriver.getDriver(), this);
    }


    @FindBy(className = "o-productList__item")
    private List<WebElement> productList;

    @FindBy(css = "div[class='m-variation']>span:not([class*='-disabled'])")
    private WebElement size;


    @FindBy(id = "quantitySelect0-key-0")
    private WebElement selectQuantity;

    @FindBy(css = "div[role='combobox']>input")
    private WebElement searchInput;

    @FindBy(css = "a[title='Beymen']")
    private WebElement siteLogo;

    @FindBy(id = "onetrust-accept-btn-handler")
    private WebElement acceptCookies;

    @FindBy(css = "[class='o-modal__header']>button")
    private WebElement selectGender;

    @FindBy(id = "productList")
    private WebElement productDiv;

    @FindBy(id = "addBasket")
    private WebElement addToBasket;

    @FindBy(css = "a[href='/cart']")
    private WebElement cart;

    @FindBy(id = "priceNew")
    private WebElement price;

    @FindBy(className = "m-productPrice")
    private WebElement cartPrice;

    @FindBy(id = "removeCartItemBtn0-key-0")
    private WebElement removeProduct;

    @FindBy(css = "strong[class='m-empty__messageTitle']")
    private WebElement emptyCartMessage;


    public WebElement getWebElement(String byName) {
        switch (byName) {
            case "searchInput":
                return searchInput;
            case "siteLogo":
                return siteLogo;
            case "acceptCookies":
                return acceptCookies;
            case "selectGender":
                return selectGender;
            case "productDiv":
                return productDiv;
            case "addToBasket":
                return addToBasket;
            case "cart":
                return cart;
            case "price":
                return price;
            case "cartPrice":
                return cartPrice;
            case "size":
                return size;
            case "removeProduct":
                return removeProduct;
            case "emptyCartMessage":
                return emptyCartMessage;
            case "selectQuantity":return selectQuantity;
        }


        return null;
    }


    public List<WebElement> getProductList() {
        waitUntilVisible(productDiv);
        return productList;
    }


    public Select getSelectQuantity() {
        Select quantityList;
        quantityList = new Select(selectQuantity);
        return quantityList;
    }
}
