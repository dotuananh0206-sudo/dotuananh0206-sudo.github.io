package mobilecity.tests;

import mobilecity.base.BaseTest;
import mobilecity.pages.HomePage;
import mobilecity.pages.LoginPage;
import mobilecity.pages.ProductListPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * MODULE: HOMEPAGE, NAVIGATION & LOCATION
 * Anh xa chinh xac 1:1 voi cac Test Case ID trong TestCases.xlsx:
 * - TC-HOME-001 den TC-HOME-005
 * - TC-NAV-001 den TC-NAV-004
 * - TC-LOC-001 den TC-LOC-002
 */
public class HomeTest extends BaseTest {

    // ===== NHOM HOMEPAGE =====

    @Test
    @DisplayName("TC-HOME-001 - Kiem tra trang chu tai thanh cong")
    void TC_HOME_001_PageLoad() {
        logTestHeader("TC-HOME-001", "Kiem tra trang chu tai thanh cong");
        HomePage homePage = new HomePage(driver);

        logTestStep(1, "Kiem tra URL hien tai");
        String currentUrl = homePage.getCurrentUrl();
        System.out.println("  [Actual URL]: " + currentUrl);
        assertTrue(currentUrl.contains("mobilecity.vn"), 
                "Loi: URL khong thuoc mien mobilecity.vn!");
    }

    @Test
    @DisplayName("TC-HOME-002 - Kiem tra tieu de trang chu dung")
    void TC_HOME_002_PageTitle() {
        logTestHeader("TC-HOME-002", "Kiem tra tieu de trang chu dung");
        HomePage homePage = new HomePage(driver);

        logTestStep(1, "Lay tieu de trang chu");
        String pageTitle = homePage.getPageTitle();
        System.out.println("  [Actual Title]: " + pageTitle);
        assertNotNull(pageTitle, "Loi: Title bi null!");
        assertFalse(pageTitle.trim().isEmpty(), "Loi: Title bi rong!");
        assertTrue(pageTitle.toLowerCase().contains("giá rẻ") || 
                   pageTitle.toLowerCase().contains("điện thoại") || 
                   pageTitle.toLowerCase().contains("uy tín") ||
                   pageTitle.toLowerCase().contains("mobilecity"),
                "Loi: Tieu de khong phan anh noi dung MobileCity!");
    }

    @Test
    @DisplayName("TC-HOME-003 - Kiem tra logo MobileCity hien thi tren trang chu")
    void TC_HOME_003_LogoDisplay() {
        logTestHeader("TC-HOME-003", "Kiem tra logo MobileCity hien thi tren trang chu");
        HomePage homePage = new HomePage(driver);

        logTestStep(1, "Kiem tra logo hien thi");
        assertTrue(homePage.isLogoDisplayed(), "Loi: Logo khong hien thi tren Header!");
    }

    @Test
    @DisplayName("TC-HOME-004 - Kiem tra thanh tim kiem hien thi tren trang chu")
    void TC_HOME_004_SearchBar() {
        logTestHeader("TC-HOME-004", "Kiem tra thanh tim kiem hien thi tren trang chu");
        HomePage homePage = new HomePage(driver);

        logTestStep(1, "Kiem tra o nhap tim kiem hien thi");
        assertTrue(homePage.isSearchBoxDisplayed(), "Loi: O tim kiem khong hien thi!");
    }

    @Test
    @DisplayName("TC-HOME-005 - Kiem tra menu dieu huong hien thi day du")
    void TC_HOME_005_NavigationMenu() {
        logTestHeader("TC-HOME-005", "Kiem tra menu dieu huong hien thi day du");
        HomePage homePage = new HomePage(driver);

        logTestStep(1, "Kiem tra menu danh muc dien thoai hien thi");
        assertTrue(homePage.isPhoneMenuDisplayed(), "Loi: Menu danh muc dien thoai khong hien thi!");
    }

    // ===== NHOM NAVIGATION =====

    @Test
    @DisplayName("TC-NAV-001 - Click menu 'Dien thoai' -> chuyen den trang danh muc")
    void TC_NAV_001_CategoryLink() {
        logTestHeader("TC-NAV-001", "Click menu 'Dien thoai' -> chuyen den trang danh muc");
        HomePage homePage = new HomePage(driver);

        logTestStep(1, "Click vao menu 'Dien thoai'");
        ProductListPage productListPage = homePage.openPhoneCategory();

        logTestStep(2, "Xac thuc URL da chuyen den trang danh muc");
        assertTrue(productListPage.getCurrentUrl().contains("/dien-thoai"),
                "Loi: Khong chuyen huong dung ve /dien-thoai!");
    }

    @Test
    @DisplayName("TC-NAV-002 - Click logo -> ve trang chu")
    void TC_NAV_002_LogoClick() {
        logTestHeader("TC-NAV-002", "Click logo -> ve trang chu");
        HomePage homePage = new HomePage(driver);

        logTestStep(1, "Chuyen den trang /dien-thoai truoc");
        homePage.openPhoneCategory();

        logTestStep(2, "Click logo de quay ve trang chu");
        homePage.clickLogo();

        logTestStep(3, "Xac thuc URL da ve trang chu");
        String currentUrl = homePage.getCurrentUrl();
        assertTrue(currentUrl.equals("https://mobilecity.vn/") || currentUrl.equals("https://mobilecity.vn"),
                "Loi: Click logo khong dua nguoi dung ve trang chu!");
    }

    @Test
    @DisplayName("TC-NAV-003 - Click 'Dang nhap' -> chuyen den trang login")
    void TC_NAV_003_LoginLink() {
        logTestHeader("TC-NAV-003", "Click 'Dang nhap' -> chuyen den trang login");
        HomePage homePage = new HomePage(driver);

        logTestStep(1, "Click nut Dang nhap");
        LoginPage loginPage = homePage.openLoginPage();

        logTestStep(2, "Xac thuc URL chua /login");
        assertTrue(loginPage.getCurrentUrl().contains("/login"),
                "Loi: Khong chuyen huong dung ve trang /login!");
    }

    @Test
    @DisplayName("TC-NAV-004 - Click 'Dang ky' -> chuyen den trang register")
    void TC_NAV_004_RegisterLink() {
        logTestHeader("TC-NAV-004", "Click 'Dang ky' -> chuyen den trang register");
        HomePage homePage = new HomePage(driver);

        logTestStep(1, "Click nut Dang ky");
        homePage.openRegisterPage();

        logTestStep(2, "Xac thuc URL chua /register");
        assertTrue(homePage.getCurrentUrl().contains("/register"),
                "Loi: Khong chuyen huong dung ve trang /register!");
    }

    // ===== NHOM LOCATION =====

    @Test
    @DisplayName("TC-LOC-001 - Dropdown location hien thi mac dinh la Ha Noi")
    void TC_LOC_001_DefaultLocation() {
        logTestHeader("TC-LOC-001", "Dropdown location hien thi mac dinh la Ha Noi");
        HomePage homePage = new HomePage(driver);

        logTestStep(1, "Kiem tra Dropdown Location hien thi");
        assertTrue(homePage.isLocationDropdownDisplayed(), "Loi: Dropdown Location khong hien thi!");

        logTestStep(2, "Kiem tra gia tri mac dinh");
        String defaultLocation = homePage.getSelectedLocation();
        System.out.println("  [Default Location]: " + defaultLocation);
        assertEquals("Hà Nội", defaultLocation, "Loi: Gia tri mac dinh khong phai la Ha Noi!");
    }

    @Test
    @DisplayName("TC-LOC-002 - Thay doi location sang TP.HCM -> trang cap nhat")
    void TC_LOC_002_ChangeLocation() {
        logTestHeader("TC-LOC-002", "Thay doi location sang TP.HCM -> trang cap nhat");
        HomePage homePage = new HomePage(driver);

        logTestStep(1, "Chon location: TP. Hồ Chí Minh");
        homePage.selectLocationByText("TP. Hồ Chí Minh");

        logTestStep(2, "Xac thuc gia tri duoc cap nhat");
        String selected = homePage.getSelectedLocation();
        assertEquals("TP. Hồ Chí Minh", selected, "Loi: Location chua duoc cap nhat sang TP.HCM!");
    }
}
