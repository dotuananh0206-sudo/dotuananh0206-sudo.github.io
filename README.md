# ĐỒ ÁN KIỂM THỬ PHẦN MỀM TỰ ĐỘNG CHO WEBSITE MOBILECITY BẰNG SELENIUM

**Đề tài:** KIỂM THỬ PHẦN MỀM TỰ ĐỘNG CHO WEBSITE MOBILECITY BẰNG SELENIUM WEBDRIVER  
**Website đối tượng kiểm thử (SUT):** [https://mobilecity.vn](https://mobilecity.vn)  
**Nhóm thực hiện:** Nhóm 2  
**Học phần:** Kiểm thử phần mềm (Software Testing)  

---

## 📌 1. TỔNG QUAN HỒ SƠ DỰ ÁN

Toàn bộ sản phẩm kiểm thử của đồ án được tổ chức và quản lý thống nhất:

```text
Team/
│
├── 1. TestPlan.docx               # Kế hoạch kiểm thử toàn diện (17 bảng biểu, 35 mục chuẩn IEEE/ISTQB)
├── 2. TestCases.xlsx              # 36 Test Cases thực tế (4 Sheets: TestCases, TestSummary, TestData, ExecutionResult)
├── 3. DefectReport.xlsx           # Báo cáo 2 lỗi thực tế phát hiện được (4 Sheets: Defects, DefectSummary, Retest, Regression)
├── 4. TestReport.pdf              # Báo cáo tổng kết nghiệm thu kiểm thử (Phase 10)
│
├── 5. Software/                   # Dự án Maven kiểm thử tự động Selenium độc lập
│   ├── pom.xml                    # Cấu hình Java 17+, Selenium 4.21, JUnit 5.10, Surefire
│   ├── README.md                  # Hướng dẫn chi tiết 9 mục chạy test độc lập
│   ├── src/main/                  # Cấu trúc chuẩn Maven
│   ├── src/test/java/mobilecity/  # Source code POM (base, pages, tests, utils)
│   ├── screenshots/               # Ảnh chụp màn hình bằng chứng khi test FAIL
│   ├── reports/                   # Báo cáo HTML trực quan (surefire-report.html)
│   └── test-results/              # Log chi tiết XML/TXT
│
├── 6. Evidence/                   # Kho lưu trữ bằng chứng kiểm thử thực tế
│   ├── screenshots/               # Ảnh chụp bằng chứng các lần chạy
│   ├── test-execution/            # Log thực thi chi tiết
│   └── defect-evidence/           # Ảnh chụp bằng chứng lỗi DEF-001 & DEF-002
│
├── 7. Documentation/              # Tài liệu phân tích và bảo vệ đồ án
│   ├── PROJECT_STATUS.md          # Theo dõi tiến độ từng Phase
│   ├── Scope.md                   # Phân tích phạm vi kiểm thử chi tiết
│   ├── FunctionalAnalysis.md      # Phân tích chức năng và DOM selector thực tế
│   ├── RiskAnalysis.md            # Ma trận rủi ro và chiến lược giảm thiểu
│   ├── DefectAnalysis_And_FixStrategy.md # Phân tích nguyên nhân gốc (RCA) & giải pháp kỹ thuật
│   └── DEFENSE_QUESTIONS.md       # 25 câu hỏi & câu trả lời bảo vệ trước giảng viên
│
└── 8. Final/                      # Nghiệm thu và bàn giao
    └── FINAL_CHECKLIST.md         # Bảng kiểm tra chất lượng nghiệm thu dự án
```

---

## 🚀 2. CÔNG NGHỆ ÁP DỤNG

| Thành phần | Công nghệ / Thư viện |
|-----------|------------------------|
| **Ngôn ngữ** | Java 17 LTS |
| **Build Tool** | Apache Maven 3.9+ |
| **Automation Tool** | Selenium WebDriver 4.21.0 |
| **Test Framework** | JUnit 5 (Jupiter 5.10.2) |
| **Design Pattern** | Page Object Model (POM) |
| **Driver Management** | Selenium Manager (tự động phát hiện Chrome) |
| **Wait Strategy** | Explicit Wait (`WebDriverWait` + `ExpectedConditions`) |
| **Evidence Capture** | `ScreenshotUtil` + `TestListener` (JUnit 5 TestWatcher + AfterTestExecutionCallback) |
| **Reporting** | Maven Surefire Plugin + Surefire Report Plugin HTML |

---

## 📊 3. KẾT QUẢ KIỂM THỬ THỰC TẾ TRÊN MÔI TRƯỜNG PRODUCTION

- **Tổng số Test Cases:** 36 kịch bản kiểm thử tự động hóa 100%.
- **Số kịch bản PASS:** **34 / 36 (94.4%)**.
- **Số kịch bản FAIL (Phát hiện lỗi thật):** **2 / 36 (5.6%)**.
  1. **`DEF-001` (Medium / P2):** Tìm kiếm với từ khóa tiếng Việt có dấu *"điện thoại Samsung"* trả về 0 kết quả (Lỗi xử lý bảng mã Unicode / tokenization backend).
  2. **`DEF-002` (Low / P3):** Form đăng ký tài khoản không có trường nhập liệu email theo chuẩn web (chỉ dùng số điện thoại và SMS OTP).
- **Trạng thái Retest (Phase 8):** Ghi nhận trung thực `FAIL (Reopen)` do website MobileCity bên ngoài chưa áp dụng bản vá trên server production (**Tuân thủ nghiêm ngặt Phương án B**).

---

## 🛠️ 4. HƯỚNG DẪN CHẠY KIỂM THỬ TỰ ĐỘNG

Di chuyển vào thư mục `5. Software/` và chạy lệnh:

```bash
# Chạy toàn bộ 36 Test Cases
mvn clean test

# Xem báo cáo HTML
mvn surefire-report:report
# Mở file: target/site/surefire-report.html (hoặc reports/surefire-report.html)
```

Chi tiết xem tại [5. Software/README.md](5.%20Software/README.md).
