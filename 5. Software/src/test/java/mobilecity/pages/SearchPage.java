package mobilecity.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

/**
 * SearchPage: Page Object quan ly trang ket qua tim kiem tren MobileCity
 */
public class SearchPage extends BasePage {

    private final By productItems = By.cssSelector(".product-list-item");
    private final By productNames = By.cssSelector(".product-list-item .product-item-left .name a");
    private final By productPrices = By.cssSelector(".product-list-item .product-item-left .price");
    private final By emptyAlertMessage = By.cssSelector(".alert_message, .product-list:empty");

    public SearchPage(WebDriver driver) {
        super(driver);
    }

    public int getResultCount() {
        try {
            List<WebElement> items = driver.findElements(productItems);
            return items.size();
        } catch (Exception e) {
            return 0;
        }
    }

    public boolean hasResults() {
        return getResultCount() > 0;
    }

    public String getFirstResultTitle() {
        if (hasResults()) {
            return getText(productNames);
        }
        return "";
    }

    public List<String> getAllResultTitles() {
        List<String> titles = new ArrayList<>();
        List<WebElement> elements = driver.findElements(productNames);
        for (WebElement el : elements) {
            titles.add(el.getText().trim());
        }
        return titles;
    }

    public ProductDetailPage clickFirstResult() {
        click(productNames);
        return new ProductDetailPage(driver);
    }

    public boolean isNoResultState() {
        return getResultCount() == 0;
    }
}
