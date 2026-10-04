package mobilecity.tests;

import mobilecity.base.BaseTest;
import mobilecity.pages.HomePage;
import mobilecity.pages.SearchPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * MODULE: SEARCH
 * Anh xa chinh xac 1:1 voi cac Test Case ID trong TestCases.xlsx:
 * - TC-SEARCH-001 den TC-SEARCH-006
 */
public class SearchTest extends BaseTest {

    @Test
    @DisplayName("TC-SEARCH-001 - Tim kiem san pham voi tu khoa hop le tra ve ket qua")
    void TC_SEARCH_001_PositiveSearch() {
        logTestHeader("TC-SEARCH-001", "Tim kiem san pham hop le: iPhone 15");
        HomePage homePage = new HomePage(driver);
        String keyword = "iPhone 15";

        logTestStep(1, "Nhap tu khoa: " + keyword);
        homePage.enterSearchKeyword(keyword);

        logTestStep(2, "Click Tim kiem");
        homePage.clickSearchButton();

        logTestStep(3, "Kiem tra ket qua tra ve");
        SearchPage searchPage = new SearchPage(driver);
        assertTrue(searchPage.hasResults(), "Loi: Khong tim thay san pham nao cho tu khoa " + keyword);

        String firstTitle = searchPage.getFirstResultTitle();
        System.out.println("  [San pham tim thay]: " + firstTitle);
        assertTrue(firstTitle.toLowerCase().contains("iphone"), 
                "Loi: San pham dau tien khong chua tu khoa iPhone!");
    }

    @Test
    @DisplayName("TC-SEARCH-002 - Tim kiem tu khoa khong ton tai tra ve thong bao phu hop")
    void TC_SEARCH_002_NoResultSearch() {
        logTestHeader("TC-SEARCH-002", "Tim kiem tu khoa khong ton tai");
        HomePage homePage = new HomePage(driver);
        String invalidKeyword = "xyzabcnotexist123";

        logTestStep(1, "Nhap tu khoa khong ton tai: " + invalidKeyword);
        homePage.enterSearchKeyword(invalidKeyword);

        logTestStep(2, "Click Tim kiem");
        homePage.clickSearchButton();

        logTestStep(3, "Kiem tra he thong khong bi loi va khong co san pham hop le");
        SearchPage searchPage = new SearchPage(driver);
        assertTrue(searchPage.isNoResultState() || searchPage.getResultCount() == 0,
                "Loi: Tu khoa vo nghia nhung van tra ve san pham!");
    }

    @Test
    @DisplayName("TC-SEARCH-003 - Tim kiem voi ky tu dac biet khong gay loi server")
    void TC_SEARCH_003_SpecialCharacterSearch() {
        logTestHeader("TC-SEARCH-003", "Tim kiem voi ky tu dac biet: iPhone@#$%");
        HomePage homePage = new HomePage(driver);
        String specialKeyword = "iPhone@#$%";

        logTestStep(1, "Nhap tu khoa ky tu dac biet");
        homePage.enterSearchKeyword(specialKeyword);

        logTestStep(2, "Click Tim kiem");
        homePage.clickSearchButton();

        logTestStep(3, "Xac nhan website khong bi crash hoac loi 500");
        String currentUrl = homePage.getCurrentUrl();
        assertTrue(currentUrl.contains("mobilecity.vn"), "Loi: Website bi mat ket noi!");
        assertFalse(homePage.getPageTitle().contains("500") || homePage.getPageTitle().contains("Error"),
                "Loi: Website nem ra loi Server 500 khi tim kiem ky tu dac biet!");
    }

    @Test
    @DisplayName("TC-SEARCH-004 - Tim kiem o trong khong duoc submit")
    void TC_SEARCH_004_EmptySearch() {
        logTestHeader("TC-SEARCH-004", "Tim kiem o trong khong duoc submit");
        HomePage homePage = new HomePage(driver);

        logTestStep(1, "De trong o tim kiem va click nut Tim kiem");
        homePage.clickSearchButton();

        logTestStep(2, "Xac thuc URL van o trang hien tai do thuoc tinh required chan lai");
        String currentUrl = homePage.getCurrentUrl();
        assertTrue(currentUrl.equals("https://mobilecity.vn/") || currentUrl.equals("https://mobilecity.vn"),
                "Loi: O tim kiem bi de trong nhung form van submit di!");
    }

    @Test
    @DisplayName("TC-SEARCH-005 - Tim kiem voi chuoi qua dai (256 ky tu)")
    void TC_SEARCH_005_LongStringSearch() {
        logTestHeader("TC-SEARCH-005", "Tim kiem voi chuoi 256 ky tu");
        HomePage homePage = new HomePage(driver);
        String longKeyword = "a".repeat(256);

        logTestStep(1, "Nhap chuoi 256 ky tu vao o tim kiem");
        homePage.enterSearchKeyword(longKeyword);

        logTestStep(2, "Submit tim kiem");
        homePage.clickSearchButton();

        logTestStep(3, "Xac thuc website xu ly an toan, khong bi crash 500");
        assertFalse(homePage.getPageTitle().contains("500") || homePage.getPageTitle().contains("Error"),
                "Loi: He thong bi crash khi tim kiem chuoi 256 ky tu!");
    }

    @Test
    @DisplayName("TC-SEARCH-006 - Tim kiem bang tieng Viet co dau tra ve ket qua")
    void TC_SEARCH_006_VietnameseKeyword() {
        logTestHeader("TC-SEARCH-006", "Tim kiem tieng Viet co dau: dien thoai Samsung");
        HomePage homePage = new HomePage(driver);
        String vnKeyword = "điện thoại Samsung";

        logTestStep(1, "Nhap tu khoa tieng Viet");
        homePage.enterSearchKeyword(vnKeyword);

        logTestStep(2, "Click Tim kiem");
        homePage.clickSearchButton();

        logTestStep(3, "Kiem tra co san pham tra ve");
        SearchPage searchPage = new SearchPage(driver);
        assertTrue(searchPage.hasResults(), "Loi: Khong tim thay san pham nao cho tu khoa tieng Viet co dau!");
    }
}
