package mobilecity.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * CartPage: Page Object dai dien cho trang Gio hang cua MobileCity
 * Dua tren phan tich thuc te:
 * - Khi chua dang nhap, he thong MobileCity bao ve gio hang bang cach chuyen huong ve /login
 * - Khi da dang nhap, gio hang chua bang san pham (.cart-area table) va tong tien
 */
public class CartPage extends BasePage {

    private final By cartArea = By.cssSelector(".cart-area, .cart");
    private final By cartItems = By.cssSelector(".cart table tbody tr, .invoice-item");
    private final By emptyCartMessage = By.cssSelector(".empty-cart, .alert-info");
    private final By checkoutButton = By.cssSelector(".cart-footer .v2-btn, .btn-checkout");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public void navigateToCart() {
        driver.get("https://mobilecity.vn/gio-hang");
    }

    public boolean isRedirectedToLogin() {
        return driver.getCurrentUrl().contains("/login");
    }

    public boolean isCartPageDisplayed() {
        return isDisplayed(cartArea);
    }

    public int getCartItemCount() {
        try {
            List<WebElement> list = driver.findElements(cartItems);
            return list.size();
        } catch (Exception e) {
            return 0;
        }
    }
}
