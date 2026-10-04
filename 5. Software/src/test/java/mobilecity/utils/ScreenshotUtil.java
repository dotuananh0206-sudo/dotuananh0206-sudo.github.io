package mobilecity.utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Utility chup anh man hinh (Screenshot) khi test case bi FAIL hoac khi can luu vet.
 * Luu tru dong thoi tai:
 * - 5. Software/screenshots/
 * - 6. Evidence/screenshots/
 */
public class ScreenshotUtil {
    private static final String SOFTWARE_SCREENSHOT_DIR = "screenshots";
    private static final String EVIDENCE_SCREENSHOT_DIR = "../6. Evidence/screenshots";

    public static String captureScreenshot(WebDriver driver, String testName) {
        if (driver == null) {
            System.err.println("Driver dang null, khong the chup anh man hinh!");
            return null;
        }

        try {
            File swDir = new File(SOFTWARE_SCREENSHOT_DIR);
            if (!swDir.exists()) {
                swDir.mkdirs();
            }

            File evDir = new File(EVIDENCE_SCREENSHOT_DIR);
            if (!evDir.exists()) {
                evDir.mkdirs();
            }

            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String fileName = testName + "_" + timestamp + "_FAIL.png";
            
            File destinationFile = new File(swDir, fileName);
            File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(sourceFile, destinationFile);

            // Copy sang Evidence folder neu ton tai
            try {
                File evFile = new File(evDir, fileName);
                FileUtils.copyFile(sourceFile, evFile);
            } catch (Exception ignored) {
            }

            System.out.println(">> DA LUU SCREENSHOT TEST THAT BAI TAI: " + destinationFile.getAbsolutePath());
            return destinationFile.getAbsolutePath();
        } catch (IOException e) {
            System.err.println("Loi khi luu screenshot: " + e.getMessage());
            return null;
        }
    }
}
