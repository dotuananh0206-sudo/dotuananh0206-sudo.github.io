package mobilecity.tests;

import mobilecity.base.BaseTest;
import mobilecity.pages.HomePage;
import mobilecity.pages.ProductDetailPage;
import mobilecity.pages.ProductListPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * MODULE: CATEGORY, PRODUCT DETAIL & WARRANTY
 * Anh xa chinh xac 1:1 voi cac Test Case ID trong TestCases.xlsx:
 * - TC-CAT-001 den TC-CAT-004
 * - TC-PROD-001 den TC-PROD-003
 * - TC-WAR-001
 */
public class ProductTest extends BaseTest {

    // ===== NHOM CATEGORY =====

    @Test
    @DisplayName("TC-CAT-001 - Trang danh muc dien thoai tai thanh cong")
    void TC_CAT_001_CategoryPageLoad() {
        logTestHeader("TC-CAT-001", "Trang danh muc dien thoai tai thanh cong");
        driver.get("https://mobilecity.vn/dien-thoai");
        ProductListPage productListPage = new ProductListPage(driver);

        logTestStep(1, "Kiem tra tieu de trang danh muc");
        String heading = productListPage.getCategoryTitle();
        System.out.println("  [Category Heading]: " + heading);
        assertTrue(heading.contains("Điện thoại") || driver.getTitle().contains("điện thoại"), 
                "Loi: Trang danh muc khong tai dung!");
    }

    @Test
    @DisplayName("TC-CAT-002 - San pham trong danh muc hien thi du thong tin")
    void TC_CAT_002_ProductDisplay() {
        logTestHeader("TC-CAT-002", "San pham trong danh muc hien thi du thong tin");
        driver.get("https://mobilecity.vn/dien-thoai");
        ProductListPage productListPage = new ProductListPage(driver);

        logTestStep(1, "Dem so luong san pham hien thi");
        int count = productListPage.getProductCount();
        System.out.println("  [So luong san pham]: " + count);
        assertTrue(count > 0, "Loi: Khong co san pham nao hien thi tren trang danh muc!");

        logTestStep(2, "Kiem tra ten va gia san pham dau tien");
        String name = productListPage.getFirstProductName();
        String price = productListPage.getFirstProductPrice();
        System.out.println("  [Item 1]: " + name + " | " + price);
        assertNotNull(name, "Loi: Ten san pham bi null!");
        assertFalse(name.trim().isEmpty(), "Loi: Ten san pham bi rong!");
    }

    @Test
    @DisplayName("TC-CAT-003 - Click vao san pham -> chuyen den trang chi tiet")
    void TC_CAT_003_ProductLink() {
        logTestHeader("TC-CAT-003", "Click vao san pham -> chuyen den trang chi tiet");
        driver.get("https://mobilecity.vn/dien-thoai");
        ProductListPage productListPage = new ProductListPage(driver);

        logTestStep(1, "Click vao san pham dau tien");
        ProductDetailPage detailPage = productListPage.openFirstProductDetail();

        logTestStep(2, "Xac thuc URL da chuyen sang trang chi tiet san pham");
        String currentUrl = detailPage.getCurrentUrl();
        System.out.println("  [Detail URL]: " + currentUrl);
        assertTrue(currentUrl.contains(".html") || currentUrl.contains("mobilecity.vn/"),
                "Loi: Khong chuyen huong den trang chi tiet san pham!");
    }

    @Test
    @DisplayName("TC-CAT-004 - Truy cap danh muc iPhone chinh hang")
    void TC_CAT_004_SubCategoryIPhone() {
        logTestHeader("TC-CAT-004", "Truy cap danh muc iPhone chinh hang");
        driver.get("https://mobilecity.vn/dien-thoai-iphone-chinh-hang-vna");

        logTestStep(1, "Xac thuc URL va Title");
        assertTrue(driver.getCurrentUrl().contains("iphone"), "Loi: URL khong chua tu khoa iphone!");
        assertTrue(driver.getTitle().toLowerCase().contains("iphone") || driver.getPageSource().toLowerCase().contains("iphone"),
                "Loi: Trang khong phai danh muc iPhone!");
    }

    // ===== NHOM PRODUCT DETAIL =====

    @Test
    @DisplayName("TC-PROD-001 - Trang chi tiet san pham hien thi ten san pham")
    void TC_PROD_001_ProductNameDisplay() {
        logTestHeader("TC-PROD-001", "Trang chi tiet hien thi ten san pham");
        driver.get("https://mobilecity.vn/dien-thoai");
        ProductListPage productListPage = new ProductListPage(driver);
        ProductDetailPage detailPage = productListPage.openFirstProductDetail();

        logTestStep(1, "Lay ten san pham tren trang chi tiet");
        String productName = detailPage.getProductName();
        System.out.println("  [Product Name]: " + productName);
        assertNotNull(productName, "Loi: Ten san pham bi null!");
        assertFalse(productName.trim().isEmpty(), "Loi: Ten san pham bi rong tren trang chi tiet!");
    }

    @Test
    @DisplayName("TC-PROD-002 - Trang chi tiet san pham hien thi gia ban")
    void TC_PROD_002_ProductPriceDisplay() {
        logTestHeader("TC-PROD-002", "Trang chi tiet hien thi gia ban");
        driver.get("https://mobilecity.vn/dien-thoai");
        ProductListPage productListPage = new ProductListPage(driver);
        ProductDetailPage detailPage = productListPage.openFirstProductDetail();

        logTestStep(1, "Lay gia san pham");
        String price = detailPage.getProductPrice();
        System.out.println("  [Product Price]: " + price);
        assertNotNull(price, "Loi: Gia san pham bi null!");
        assertFalse(price.trim().isEmpty(), "Loi: Gia san pham bi rong tren trang chi tiet!");
    }

    @Test
    @DisplayName("TC-PROD-003 - Trang chi tiet san pham hien thi anh san pham")
    void TC_PROD_003_ProductImageDisplay() {
        logTestHeader("TC-PROD-003", "Trang chi tiet hien thi anh san pham");
        driver.get("https://mobilecity.vn/dien-thoai");
        ProductListPage productListPage = new ProductListPage(driver);
        ProductDetailPage detailPage = productListPage.openFirstProductDetail();

        logTestStep(1, "Kiem tra hinh anh san pham hien thi");
        assertTrue(detailPage.isProductImageDisplayed(), "Loi: Anh san pham khong hien thi tren trang chi tiet!");
    }

    // ===== NHOM WARRANTY =====

    @Test
    @DisplayName("TC-WAR-001 - Trang tra cuu bao hanh tai thanh cong")
    void TC_WAR_001_WarrantyPageLoad() {
        logTestHeader("TC-WAR-001", "Trang tra cuu bao hanh tai thanh cong");
        driver.get("https://mobilecity.vn/tra-cuu-bao-hanh");

        logTestStep(1, "Xac thuc URL va Title trang tra cuu bao hanh");
        assertTrue(driver.getCurrentUrl().contains("tra-cuu-bao-hanh"),
                "Loi: URL khong chuyen ve /tra-cuu-bao-hanh!");
        assertTrue(driver.getTitle().toLowerCase().contains("bảo hành") || driver.getTitle().toLowerCase().contains("tra cứu"),
                "Loi: Tieu de trang khong chua tu khoa bao hanh!");
    }
}
