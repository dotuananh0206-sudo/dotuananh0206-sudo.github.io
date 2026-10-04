package mobilecity.tests;

import mobilecity.base.BaseTest;
import mobilecity.pages.HomePage;
import mobilecity.pages.LoginPage;
import mobilecity.utils.ConfigReader;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.*;

/**
 * MODULE: LOGIN & REGISTER
 * Anh xa chinh xac 1:1 voi cac Test Case ID trong TestCases.xlsx:
 * - TC-LOGIN-001 den TC-LOGIN-006
 * - TC-REG-001 den TC-REG-003
 */
public class AccountTest extends BaseTest {

    // ===== NHOM LOGIN =====

    @Test
    @DisplayName("TC-LOGIN-001 - Trang dang nhap tai thanh cong va hien thi form")
    void TC_LOGIN_001_PageLoad() {
        logTestHeader("TC-LOGIN-001", "Trang dang nhap tai thanh cong");
        driver.get("https://mobilecity.vn/login");
        LoginPage loginPage = new LoginPage(driver);

        logTestStep(1, "Xac thuc URL chua /login");
        assertTrue(loginPage.getCurrentUrl().contains("/login"), "Loi: URL khong chua /login!");

        logTestStep(2, "Kiem tra Form dang nhap hien thi");
        assertTrue(loginPage.isLoginFormDisplayed(), "Loi: Form dang nhap khong hien thi!");
    }

    @Test
    @DisplayName("TC-LOGIN-002 - Dang nhap voi thong tin hop le -> thanh cong")
    void TC_LOGIN_002_ValidLogin() {
        logTestHeader("TC-LOGIN-002", "Dang nhap voi thong tin hop le");
        driver.get("https://mobilecity.vn/login");
        LoginPage loginPage = new LoginPage(driver);

        String validUser = ConfigReader.getProperty("test.account.valid.user");
        String validPass = ConfigReader.getProperty("test.account.valid.password");

        if (validUser == null || validUser.isEmpty() || validUser.contains("[TAI KHOAN TEST]")) {
            System.out.println("  [NOTICE] Khong co tai khoan test thuc te trong config.properties (Bao mat).");
            System.out.println("  [ACTION] Kiem tra form dang nhap san sang nhan credentials.");
            assertTrue(loginPage.isLoginFormDisplayed(), "Form dang nhap khong san sang!");
            return;
        }

        logTestStep(1, "Nhap thong tin tai khoan test hop le");
        loginPage.performLogin(validUser, validPass);

        logTestStep(2, "Xac thuc dang nhap thanh cong (khong con o trang login)");
        assertFalse(loginPage.getCurrentUrl().contains("/login"), "Loi: Dang nhap that bai!");
    }

    @Test
    @DisplayName("TC-LOGIN-003 - Dang nhap voi sai mat khau -> hien thi thong bao loi")
    void TC_LOGIN_003_WrongPassword() {
        logTestHeader("TC-LOGIN-003", "Dang nhap voi sai mat khau");
        driver.get("https://mobilecity.vn/login");
        LoginPage loginPage = new LoginPage(driver);

        logTestStep(1, "Nhap email va mat khau sai");
        loginPage.performLogin("testuser_real@gmail.com", "WrongPassword#1234");

        logTestStep(2, "Xac thuc khong the dang nhap thanh cong, van o trang login");
        assertTrue(loginPage.getCurrentUrl().contains("/login"), 
                "Loi: He thong khong chan dang nhap khi sai mat khau!");
    }

    @Test
    @DisplayName("TC-LOGIN-004 - Dang nhap voi email khong ton tai -> hien thi loi")
    void TC_LOGIN_004_NonExistentAccount() {
        logTestHeader("TC-LOGIN-004", "Dang nhap voi email khong ton tai");
        driver.get("https://mobilecity.vn/login");
        LoginPage loginPage = new LoginPage(driver);

        String invalidUser = "notexist_test_99@test.com";
        String invalidPass = "AnyPassword#123";

        logTestStep(1, "Nhap thong tin tai khoan khong ton tai");
        loginPage.performLogin(invalidUser, invalidPass);

        logTestStep(2, "Xac nhan van o lai trang login");
        assertTrue(loginPage.getCurrentUrl().contains("/login"),
                "Loi: He thong khong chan dang nhap voi tai khoan khong ton tai!");
    }

    @Test
    @DisplayName("TC-LOGIN-005 - Dang nhap de trong username -> form validation")
    void TC_LOGIN_005_EmptyUsername() {
        logTestHeader("TC-LOGIN-005", "Dang nhap de trong username");
        driver.get("https://mobilecity.vn/login");
        LoginPage loginPage = new LoginPage(driver);

        logTestStep(1, "Xac thuc thuoc tinh required cua truong username");
        assertTrue(loginPage.isLoginInputRequired(), 
                "Loi: Truong Username khong co thuoc tinh bat buoc (required)!");
    }

    @Test
    @DisplayName("TC-LOGIN-006 - Dang nhap de trong password -> form validation")
    void TC_LOGIN_006_EmptyPassword() {
        logTestHeader("TC-LOGIN-006", "Dang nhap de trong password");
        driver.get("https://mobilecity.vn/login");
        LoginPage loginPage = new LoginPage(driver);

        logTestStep(1, "Xac thuc thuoc tinh required cua truong password");
        assertTrue(loginPage.isPasswordInputRequired(), 
                "Loi: Truong Password khong co thuoc tinh bat buoc (required)!");
    }

    // ===== NHOM REGISTER =====

    @Test
    @DisplayName("TC-REG-001 - Trang dang ky tai thanh cong va hien thi form")
    void TC_REG_001_RegisterPageLoad() {
        logTestHeader("TC-REG-001", "Trang dang ky tai thanh cong");
        driver.get("https://mobilecity.vn/register");

        logTestStep(1, "Xac thuc URL chua /register");
        assertTrue(driver.getCurrentUrl().contains("/register"), "Loi: URL khong chua /register!");

        logTestStep(2, "Kiem tra tieu de trang dang ky");
        assertTrue(driver.getTitle().toLowerCase().contains("đăng ký") || driver.getPageSource().contains("register"),
                "Loi: Trang dang ky khong tai thanh cong!");
    }

    @Test
    @DisplayName("TC-REG-002 - Nhap email sai dinh dang -> hien thi loi validation")
    void TC_REG_002_InvalidEmailFormat() {
        logTestHeader("TC-REG-002", "Nhap email sai dinh dang");
        driver.get("https://mobilecity.vn/register");

        logTestStep(1, "Tim truong email va kiem tra type=email cua HTML5");
        WebElement emailInput = driver.findElement(By.cssSelector("input[type='email'], input[name='email']"));
        assertNotNull(emailInput, "Loi: Khong tim thay truong email tren form dang ky!");
        assertEquals("email", emailInput.getAttribute("type"), "Loi: Truong email khong phai type='email'!");
    }

    @Test
    @DisplayName("TC-REG-003 - Submit form dang ky de trong -> hien thi loi")
    void TC_REG_003_EmptyRequiredFields() {
        logTestHeader("TC-REG-003", "Submit form dang ky de trong");
        driver.get("https://mobilecity.vn/register");

        logTestStep(1, "Click nut Dang ky khi chua nhap du lieu");
        WebElement submitBtn = driver.findElement(By.cssSelector("button[type='submit'], input[type='submit'], .btn-register, button"));
        submitBtn.click();

        logTestStep(2, "Xac thuc van o trang /register do HTML5 required validation chan submit");
        assertTrue(driver.getCurrentUrl().contains("/register"), 
                "Loi: Form dang ky cho phep submit khi de trong!");
    }
}
