package mobilecity.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * ProductDetailPage: Page Object quan ly Trang chi tiet san pham cua MobileCity
 */
public class ProductDetailPage extends BasePage {

    private final By productHeading = By.cssSelector("h1.product-title, h1, .product-content-box h1");
    private final By productPrice = By.cssSelector(".price-box .price, .product-price-box .price, .product-item-info .price, .price");
    private final By inStockStatus = By.cssSelector(".status-box, .status-instock");
    private final By colorItems = By.cssSelector(".color-list .color-item");
    private final By warrantyItems = By.cssSelector(".warranty-item");
    private final By installmentBtn = By.cssSelector("a.installment-btn");
    private final By promotionBox = By.cssSelector(".sale-box, .v2-promotion");
    private final By productImage = By.cssSelector(".v2-product-image img, .product_image img, .img-detail, .owl-item.active img, img");

    public ProductDetailPage(WebDriver driver) {
        super(driver);
    }

    public String getProductName() {
        return getText(productHeading);
    }

    public String getProductPrice() {
        return getText(productPrice);
    }

    public boolean isProductImageDisplayed() {
        return isDisplayed(productImage);
    }

    public boolean isStockStatusDisplayed() {
        return isDisplayed(inStockStatus);
    }

    public String getStockStatusText() {
        if (isStockStatusDisplayed()) {
            return getText(inStockStatus);
        }
        return "";
    }

    public boolean hasColorOptions() {
        try {
            List<WebElement> colors = driver.findElements(colorItems);
            return !colors.isEmpty();
        } catch (Exception e) {
            return false;
        }
    }

    public void selectFirstColorOption() {
        if (hasColorOptions()) {
            click(colorItems);
        }
    }

    public boolean hasWarrantyOptions() {
        try {
            List<WebElement> warranties = driver.findElements(warrantyItems);
            return !warranties.isEmpty();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isInstallmentButtonDisplayed() {
        return isDisplayed(installmentBtn);
    }

    public boolean isPromotionSectionDisplayed() {
        return isDisplayed(promotionBox);
    }
}
