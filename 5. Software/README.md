# DỰ ÁN KIỂM THỬ TỰ ĐỘNG WEBSITE MOBILECITY BẰNG SELENIUM WEBDRIVER

Dự án kiểm thử phần mềm tự động độc lập dành cho Website thương mại điện tử **MobileCity** (https://mobilecity.vn), áp dụng mô hình **Page Object Model (POM)** kết hợp giữa **Java 17+**, **Selenium WebDriver 4.x**, **JUnit 5** và **Maven**.

---

## 1. YÊU CẦU HỆ THỐNG & CÀI ĐẶT

### 1.1. Cài đặt Java JDK
- Yêu cầu: **JDK 17 LTS** hoặc phiên bản mới hơn (dự án hỗ trợ JDK 17, 21, 26).
- Kiểm tra cài đặt:
  ```powershell
  java -version
  ```
- Cấu hình biến môi trường:
  - `JAVA_HOME`: Trỏ đến thư mục cài đặt JDK (ví dụ: `C:\Program Files\Java\jdk-17` hoặc `C:\Program Files\Java\jdk-26.0.1`).
  - Thêm `%JAVA_HOME%\bin` vào biến `PATH`.

### 1.2. Cài đặt Apache Maven
- Yêu cầu: **Maven 3.9+**.
- Kiểm tra cài đặt:
  ```powershell
  mvn -version
  ```
- Cấu hình biến môi trường:
  - `MAVEN_HOME` / `M2_HOME`: Trỏ đến thư mục giải nén Maven (ví dụ: `C:\Apache NetBeans\java\maven` hoặc `C:\Program Files\apache-maven-3.9.x`).
  - Thêm `%MAVEN_HOME%\bin` vào biến `PATH`.

### 1.3. Cài đặt & Cấu hình Trình duyệt Google Chrome
- Cài đặt trình duyệt Google Chrome phiên bản mới nhất từ trang chủ Google.
- **Selenium Manager:** Dự án sử dụng Selenium 4.21+ tích hợp sẵn Selenium Manager, **tự động phát hiện phiên bản Chrome** và tải `chromedriver.exe` tương thích. Người dùng **không cần tải thủ công ChromeDriver**.
- Cấu hình chế độ chạy trong file `src/test/resources/config.properties`:
  - `headless=true`: Chạy ngầm, không mở cửa sổ (tối ưu tốc độ, chạy trên server/CI).
  - `headless=false`: Mở cửa sổ trình duyệt trực quan để quan sát thao tác test.

---

## 2. HƯỚNG DẪN MỞ PROJECT

### 2.1. Sử dụng IntelliJ IDEA
1. Mở IntelliJ IDEA -> Chọn **File** -> **Open...**
2. Trỏ đến thư mục `Team/5. Software/` -> Chọn file `pom.xml`.
3. Chọn **Open as Project**.
4. Chờ Maven tải các dependencies và index hoàn tất.

### 2.2. Sử dụng Eclipse IDE
1. Chọn **File** -> **Import...** -> **Existing Maven Projects**.
2. Chọn thư mục `Team/5. Software/` -> Nhấn **Finish**.

### 2.3. Sử dụng Visual Studio Code
1. Cài đặt Extension: *Extension Pack for Java*.
2. Mở thư mục `Team/5. Software/`.

---

## 3. CẤU TRÚC MÃ NGUỒN (PROJECT STRUCTURE)

```
5. Software/
├── pom.xml                                   # Cấu hình thư viện Maven & build plugins
├── README.md                                 # Hướng dẫn chi tiết sử dụng project
│
├── src/
│   ├── main/
│   │   ├── java/                             # Source code ứng dụng (nếu có module hỗ trợ)
│   │   └── resources/
│   │
│   └── test/
│       ├── java/mobilecity/
│       │   ├── base/
│       │   │   └── BaseTest.java             # Quản lý WebDriver setup/teardown, wait
│       │   ├── pages/
│       │   │   ├── BasePage.java             # Explicit Wait wrappers, safe click/type
│       │   │   ├── HomePage.java             # Page Object: Trang chủ
│       │   │   ├── SearchPage.java           # Page Object: Trang kết quả tìm kiếm
│       │   │   ├── ProductListPage.java      # Page Object: Danh mục sản phẩm
│       │   │   ├── ProductDetailPage.java    # Page Object: Chi tiết sản phẩm
│       │   │   ├── LoginPage.java            # Page Object: Đăng nhập
│       │   │   └── CartPage.java             # Page Object: Giỏ hàng
│       │   ├── tests/
│       │   │   ├── HomeTest.java             # Kịch bản test Trang chủ & Điều hướng
│       │   │   ├── SearchTest.java           # Kịch bản test Tìm kiếm
│       │   │   ├── ProductTest.java          # Kịch bản test Danh mục & Sản phẩm
│       │   │   ├── AccountTest.java          # Kịch bản test Tài khoản & Xác thực
│       │   │   └── CartTest.java             # Kịch bản test Giỏ hàng an toàn
│       │   └── utils/
│       │       ├── ConfigReader.java         # Đọc file properties cấu hình
│       │       ├── ScreenshotUtil.java       # Chụp ảnh màn hình tự động khi FAIL
│       │       └── TestListener.java         # Bắt sự kiện test từ JUnit 5
│       └── resources/
│           └── config.properties             # File tham số cấu hình
│
├── screenshots/                              # Nơi lưu ảnh chụp màn hình khi test thất bại
├── reports/                                  # Báo cáo kết quả kiểm thử
└── test-results/                             # Kết quả dạng XML/Surefire
```

---

## 4. HƯỚNG DẪN THỰC THI KIỂM THỬ (HOW TO RUN TESTS)

Mở terminal/cmd tại thư mục `Team/5. Software/`:

### 4.1. Chạy toàn bộ Test Suites
```bash
mvn clean test
```
*Lưu ý:* Cấu hình Surefire plugin đã đặt `testFailureIgnore=true` để Maven chạy hết toàn bộ các test suites ngay cả khi có test case phát hiện Defect (FAIL).

### 4.2. Chạy từng Test Class riêng biệt
- **Kiểm thử Trang chủ & Điều hướng:**
  ```bash
  mvn test -Dtest=HomeTest
  ```
- **Kiểm thử Chức năng Tìm kiếm:**
  ```bash
  mvn test -Dtest=SearchTest
  ```
- **Kiểm thử Danh mục & Chi tiết Sản phẩm:**
  ```bash
  mvn test -Dtest=ProductTest
  ```
- **Kiểm thử Tài khoản & Xác thực:**
  ```bash
  mvn test -Dtest=AccountTest
  ```
- **Kiểm thử Giỏ hàng an toàn:**
  ```bash
  mvn test -Dtest=CartTest
  ```

### 4.3. Chạy từng Test Method đơn lẻ (chính xác theo Test Case ID)
```bash
mvn test -Dtest=SearchTest#TC_SEARCH_001_PositiveSearch
mvn test -Dtest=SearchTest#TC_SEARCH_006_VietnameseKeyword
mvn test -Dtest=AccountTest#TC_LOGIN_001_PageLoad
mvn test -Dtest=ProductTest#TC_CAT_001_CategoryPageLoad
```

---

## 5. XEM KẾT QUẢ, SCREENSHOT & TEST REPORT

### 5.1. Xem kết quả thực thi trên Console
Mỗi test case khi thực thi sẽ in định dạng rõ ràng:
```text
==================================================
TEST CASE: TC-SEARCH-001 - Tim kiem san pham hop le: iPhone 15
==================================================
  [Step 1] Nhap tu khoa: iPhone 15
  [Step 2] Click Tim kiem
  [Step 3] Kiem tra ket qua tra ve
==================================================
KET QUA: [PASS] - Test case: TC-SEARCH-001 - Tim kiem san pham hop le
==================================================
```

### 5.2. Xem ảnh chụp màn hình bằng chứng (Screenshots)
Khi có bất kỳ test case nào bị `FAIL`, hệ thống thông qua `TestListener` và `ScreenshotUtil` sẽ tự động chụp ảnh màn hình trình duyệt tại đúng thời điểm lỗi và lưu tại 2 vị trí:
1. `Team/5. Software/screenshots/`
2. `Team/6. Evidence/screenshots/`

Tên file ảnh được đặt theo định dạng: `[TestMethodName]_[yyyyMMdd_HHmmss]_FAIL.png`.

### 5.3. Tạo và xem Báo cáo HTML (Surefire Report)
Để sinh báo cáo kiểm thử dạng HTML trực quan:
```bash
mvn surefire-report:report
```
Sau đó mở file báo cáo bằng trình duyệt:
`target/site/surefire-report.html`

---

## 6. LƯU Ý BẢO MẬT & QUY TẮC KIỂM THỬ

1. **Website bên ngoài (Production):** Tuyệt đối không thực hiện giao dịch thanh toán thật, không nhập thông tin thẻ ngân hàng thật.
2. **Bảo mật tài khoản:** Không lưu trữ mật khẩu thật trong mã nguồn hay file cấu hình.
3. **An toàn hệ thống:** Không spam request, không tạo hàng loạt tài khoản rác, không vượt cơ chế CAPTCHA.
