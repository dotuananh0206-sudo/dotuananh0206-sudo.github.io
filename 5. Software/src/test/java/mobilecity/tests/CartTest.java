package mobilecity.tests;

import mobilecity.base.BaseTest;
import mobilecity.pages.CartPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * MODULE: CART
 * Anh xa chinh xac 1:1 voi cac Test Case ID trong TestCases.xlsx:
 * - TC-CART-001
 * - TC-CART-002
 * (Tuan thu tuyet doi quy tac khong thuc hien giao dich that, khong thanh toan that tren website production).
 */
public class CartTest extends BaseTest {

    @Test
    @DisplayName("TC-CART-001 - Xem gio hang khi chua co san pham -> hien thi thong bao phu hop")
    void TC_CART_001_EmptyCart() {
        logTestHeader("TC-CART-001", "Xem gio hang khi chua co san pham");
        CartPage cartPage = new CartPage(driver);

        logTestStep(1, "Truy cap URL gio hang");
        cartPage.navigateToCart();

        logTestStep(2, "Xac thuc he thong yeu cau login hoac hien thi gio hang trong");
        String currentUrl = cartPage.getCurrentUrl();
        System.out.println("  [Cart URL]: " + currentUrl);
        assertTrue(currentUrl.contains("gio-hang") || currentUrl.contains("login"),
                "Loi: Trang gio hang khong hien thi dung co che bao ve hoac thong bao!");
    }

    @Test
    @DisplayName("TC-CART-002 - Trang gio hang tai thanh cong")
    void TC_CART_002_CartPageLoad() {
        logTestHeader("TC-CART-002", "Trang gio hang tai thanh cong");
        CartPage cartPage = new CartPage(driver);

        logTestStep(1, "Truy cap URL gio hang");
        cartPage.navigateToCart();

        logTestStep(2, "Xac thuc trang load khong loi 500/crash");
        assertFalse(driver.getTitle().contains("500") || driver.getTitle().contains("Error"),
                "Loi: Trang gio hang bi crash loi server 500!");
    }
}
