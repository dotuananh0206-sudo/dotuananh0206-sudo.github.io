package mobilecity.base;

import mobilecity.utils.ConfigReader;
import mobilecity.utils.TestListener;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * BaseTest: Lop co so cho moi Test Class trong project.
 * - Khoi tao ChromeDriver su dung Selenium Manager tu dong.
 * - Cau hinh cac options (khoi dong toi da cua so, tat thong bao, v.v.)
 * - Dieu huong den URL he thong MobileCity tu config.properties.
 * - Khoi tao doi tuong WebDriverWait quan ly thoi gian cho Explicit Wait.
 * - Dong trinh duyet sau moi test case (@AfterEach) de dam bao tinh doc lap (Test Independence).
 * - Tich hop TestListener de tu dong chup anh man hinh khi test case bi FAIL.
 */
@ExtendWith(TestListener.class)
public abstract class BaseTest {
    protected static WebDriver currentDriver;
    protected WebDriver driver;
    protected WebDriverWait wait;

    public static WebDriver getDriver() {
        return currentDriver;
    }

    @BeforeEach
    public void setUp() {
        System.out.println("\n--------------------------------------------------");
        System.out.println(">> [SETUP] Khoi tao trinh duyet va mo he thong MobileCity...");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-extensions");

        if (ConfigReader.isHeadless()) {
            options.addArguments("--headless=new");
            options.addArguments("--window-size=1920,1080");
        }

        // Selenium Manager tu dong phat hien Google Chrome tren may va tai/quan ly chromedriver phu hop
        driver = new ChromeDriver(options);
        currentDriver = driver;

        // Thiet lap WebDriverWait tap trung
        wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.getTimeout()));

        // Mo website MobileCity
        String url = ConfigReader.getBaseUrl();
        System.out.println(">> [NAVIGATE] Dang truy cap: " + url);
        driver.get(url);
    }

    @AfterEach
    public void tearDown() {
        System.out.println(">> [TEARDOWN] Dong trinh duyet va giai phong tai nguyen...");
        if (driver != null) {
            try {
                driver.quit();
            } catch (Exception e) {
                System.err.println("Loi khi dong trinh duyet: " + e.getMessage());
            } finally {
                driver = null;
                currentDriver = null;
            }
        }
        System.out.println("--------------------------------------------------\n");
    }

    /**
     * Helper in log dinh dang dep mat trong console
     */
    protected void logTestHeader(String testId, String testName) {
        System.out.println("==================================================");
        System.out.println("TEST CASE: " + testId + " - " + testName);
        System.out.println("==================================================");
    }

    protected void logTestStep(int stepNumber, String action) {
        System.out.println("  [Step " + stepNumber + "] " + action);
    }
}
