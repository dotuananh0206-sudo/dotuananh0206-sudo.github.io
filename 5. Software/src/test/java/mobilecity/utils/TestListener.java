package mobilecity.utils;

import mobilecity.base.BaseTest;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.WebDriver;

import java.util.Optional;

/**
 * JUnit 5 TestWatcher va AfterTestExecutionCallback de lang nghe trang thai thuc thi cua tung Test Case:
 * - afterTestExecution chay TRUOC @AfterEach, dam bao WebDriver con song de chup anh man hinh!
 * - Khi PASS: Ghi log xac nhan
 * - Khi FAIL: Tu dong goi ScreenshotUtil de chup anh man hinh luu vao screenshots/ va 6. Evidence/screenshots/
 */
public class TestListener implements TestWatcher, AfterTestExecutionCallback {

    @Override
    public void afterTestExecution(ExtensionContext context) {
        if (context.getExecutionException().isPresent()) {
            WebDriver driver = BaseTest.getDriver();
            if (driver != null) {
                String testMethodName = context.getRequiredTestMethod().getName();
                System.out.println(">> [LISTENER] Phat hien test FAIL: " + testMethodName + " -> Tien hanh chup anh man hinh...");
                ScreenshotUtil.captureScreenshot(driver, testMethodName);
            } else {
                System.err.println(">> [LISTENER] WebDriver da bi null truoc khi chup anh!");
            }
        }
    }

    @Override
    public void testSuccessful(ExtensionContext context) {
        System.out.println("==================================================");
        System.out.println("KET QUA: [PASS] - Test case: " + context.getDisplayName());
        System.out.println("==================================================");
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        System.err.println("==================================================");
        System.err.println("KET QUA: [FAIL] - Test case: " + context.getDisplayName());
        System.err.println("Ly do loi: " + cause.getMessage());
        System.err.println("==================================================");
    }

    @Override
    public void testDisabled(ExtensionContext context, Optional<String> reason) {
        System.out.println("KET QUA: [SKIPPED] - Test case: " + context.getDisplayName() 
                + " (Ly do: " + reason.orElse("Khong ro") + ")");
    }

    @Override
    public void testAborted(ExtensionContext context, Throwable cause) {
        System.out.println("KET QUA: [ABORTED] - Test case: " + context.getDisplayName());
    }
}
