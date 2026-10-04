package mobilecity.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;
import java.util.List;

/**
 * ProductListPage: Page Object quan ly trang danh muc san pham (Vi du: /dien-thoai, /may-tinh-bang)
 */
public class ProductListPage extends BasePage {

    private final By categoryTitle = By.cssSelector("h1.intro-category-title");
    private final By filterBox = By.cssSelector(".product-fillter-box");
    private final By sortDropdownTrigger = By.cssSelector(".product-fillter-box .fillter-item > a");
    private final By sortLowToHigh = By.cssSelector(".sort-price a[value='thap-den-cao']");
    private final By sortHighToLow = By.cssSelector(".sort-price a[value='cao-den-thap']");
    
    private final By productCards = By.cssSelector(".product-list-item");
    private final By productNames = By.cssSelector(".product-list-item .product-item-left .name a");
    private final By productPrices = By.cssSelector(".product-list-item .product-item-left .price");
    private final By buyButtons = By.cssSelector(".product-list-item .product-item-right .buy");

    public ProductListPage(WebDriver driver) {
        super(driver);
    }

    public String getCategoryTitle() {
        return getText(categoryTitle);
    }

    public int getProductCount() {
        try {
            List<WebElement> list = driver.findElements(productCards);
            return list.size();
        } catch (Exception e) {
            return 0;
        }
    }

    public String getFirstProductName() {
        return getText(productNames);
    }

    public String getFirstProductPrice() {
        return getText(productPrices);
    }

    public List<String> getAllProductNames() {
        List<String> names = new ArrayList<>();
        List<WebElement> elements = driver.findElements(productNames);
        for (WebElement el : elements) {
            names.add(el.getText().trim());
        }
        return names;
    }

    public ProductDetailPage openFirstProductDetail() {
        click(productNames);
        return new ProductDetailPage(driver);
    }

    public ProductDetailPage clickFirstBuyButton() {
        click(buyButtons);
        return new ProductDetailPage(driver);
    }

    public void selectSortLowToHigh() {
        Actions actions = new Actions(driver);
        WebElement trigger = waitForVisibility(sortDropdownTrigger);
        actions.moveToElement(trigger).perform();
        click(sortLowToHigh);
    }

    public void selectSortHighToLow() {
        Actions actions = new Actions(driver);
        WebElement trigger = waitForVisibility(sortDropdownTrigger);
        actions.moveToElement(trigger).perform();
        click(sortHighToLow);
    }

    public boolean isFilterBoxDisplayed() {
        return isDisplayed(filterBox);
    }
}
