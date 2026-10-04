# PROJECT STATUS
**Dự án:** Kiểm thử phần mềm tự động cho website MobileCity bằng Selenium  
**Cập nhật lần cuối:** 2026-09-23  
**Thư mục gốc duy nhất:** `Team/`  
**Phase hiện tại:** Chuẩn bị bước vào PHASE 4 – XÂY DỰNG SELENIUM AUTOMATION  
**Trạng thái:** ✅ Phase 0, 1, 2, 3 đã hoàn thành xuất sắc – Cấu trúc thư mục đã chuẩn hóa

---

## 1. Project Objective

Xây dựng bộ hồ sơ kiểm thử hoàn chỉnh cho website MobileCity (https://mobilecity.vn), bao gồm:
- Tài liệu kiểm thử thực tế (`1. TestPlan.docx`, `2. TestCases.xlsx`, `3. DefectReport.xlsx`, `4. TestReport.pdf`)
- Dự án Selenium automation (`5. Software/`: Java 17+, Maven, JUnit 5, POM, Selenium Manager)
- Bằng chứng kiểm thử thực tế (`6. Evidence/`)
- Tài liệu phân tích & câu hỏi bảo vệ (`7. Documentation/`)
- Bảng kiểm tra chất lượng nghiệm thu cuối cùng (`8. Final/FINAL_CHECKLIST.md`)
- **Đóng gói bàn giao cuối cùng:** Toàn bộ thư mục `Team/` phải được đóng gói thành file `Team.zip` (chứa đầy đủ 100% cấu trúc, source code, report, evidence, docs, không bỏ sót bất kỳ thư mục con nào).

Mục tiêu học thuật: Nộp bài, thuyết trình và bảo vệ xuất sắc trước hội đồng giảng viên.

---

## 2. Chuẩn hóa cấu trúc thư mục dự án (Team/)

```
Team/
│
├── 1. TestPlan.docx
├── 2. TestCases.xlsx
├── 3. DefectReport.xlsx
├── 4. TestReport.pdf
│
├── 5. Software/
│   ├── pom.xml
│   ├── README.md
│   ├── src/
│   │   └── test/java/ (base, pages, tests, utils)
│   ├── screenshots/
│   ├── reports/
│   └── test-results/
│
├── 6. Evidence/
│   ├── screenshots/
│   ├── test-execution/
│   └── defect-evidence/
│
├── 7. Documentation/
│   ├── PROJECT_STATUS.md
│   ├── Scope.md
│   ├── FunctionalAnalysis.md
│   ├── RiskAnalysis.md
│   └── DEFENSE_QUESTIONS.md
│
└── 8. Final/
    └── FINAL_CHECKLIST.md
```

---

## 3. Technology Stack

| Thành phần | Công nghệ / Phiên bản |
|-----------|------------------------|
| Ngôn ngữ | Java 17 LTS |
| Build tool | Apache Maven 3.9+ |
| Test framework | JUnit 5 (Jupiter 5.10+) |
| Automation framework | Selenium WebDriver 4.x |
| Trình duyệt | Google Chrome |
| Driver Management | Selenium Manager (tự động) |
| Design Pattern | Page Object Model (POM) |
| Wait Mechanism | WebDriverWait + ExpectedConditions (Explicit Wait) |
| Assertion | JUnit 5 Assertions |
| Reporting | JUnit XML, HTML Report, Evidence Logging |

---

## 4. Current Status – Phase Checklist

| Phase | Tên Phase | Trạng thái | Sản phẩm chính |
|-------|-----------|------------|----------------|
| **PHASE 0** | Tiếp nhận dự án | ✅ Hoàn thành | PROJECT_STATUS.md |
| **PHASE 1** | Phân tích website MobileCity | ✅ Hoàn thành | Scope.md, FunctionalAnalysis.md, RiskAnalysis.md |
| **PHASE 2** | Test Plan | ✅ Hoàn thành | `Team/1. TestPlan.docx` (43 KB, 17 bảng biểu, chuẩn mực) |
| **PHASE 3** | Thiết kế Test Case | ✅ Hoàn thành | `Team/2. TestCases.xlsx` (36 TCs, 4 sheets hoàn chỉnh) |
| **PHASE 4** | Xây dựng Selenium Automation | ✅ Hoàn thành | `Team/5. Software/` (16 source files, POM, 1:1 TC alignment, build success) |
| **PHASE 5** | Thực thi Automation Test | ✅ Hoàn thành | Đã chạy 36/36 TCs, phát hiện 2 lỗi thật, sinh báo cáo HTML |
| **PHASE 6** | Defect Report | ✅ Hoàn thành | `Team/3. DefectReport.xlsx` (4 sheets: Defects, DefectSummary, Retest, Regression) |
| **PHASE 7** | Fix Defect (Phương án B / Test Env) | ✅ Hoàn thành | Phân tích RCA & giải pháp kỹ thuật tại `Team/7. Documentation/DefectAnalysis_And_FixStrategy.md` |
| **PHASE 8** | Retest | ✅ Hoàn thành | Thực thi Retest thật trên Chrome, ghi nhận FAIL (Reopen) & screenshot mới |
| **PHASE 9** | Regression | ⬜ Chưa bắt đầu | Sheet Regression trong DefectReport |
| **PHASE 10**| Test Report | ⬜ Chưa bắt đầu | `Team/4. TestReport.pdf` |
| **PHASE 11**| Kết luận chuẩn chuyên môn | ⬜ Chưa bắt đầu | Kết luận dựa trên số liệu thực tế |
| **PHASE 12**| Kiểm tra tính nhất quán | ⬜ Chưa bắt đầu | Cross-check toàn bộ tài liệu |
| **PHASE 13**| Kiểm tra chất lượng file | ⬜ Chưa bắt đầu | DOCX, XLSX, PDF, code |
| **PHASE 14**| README dự án | ⬜ Chưa bắt đầu | `Team/5. Software/README.md` |
| **PHASE 15**| Final Review & Checklist | ⬜ Chưa bắt đầu | `Team/8. Final/FINAL_CHECKLIST.md`, DEFENSE_QUESTIONS.md |

---

## 5. Danh mục file hiện có trong `Team/`

1. `Team/1. TestPlan.docx` (File Word hoàn chỉnh)
2. `Team/2. TestCases.xlsx` (File Excel 36 test cases, 4 sheets: TestCases, TestSummary, TestData, ExecutionResult)
3. `Team/7. Documentation/Scope.md`
4. `Team/7. Documentation/FunctionalAnalysis.md`
5. `Team/7. Documentation/RiskAnalysis.md`
6. `Team/7. Documentation/PROJECT_STATUS.md`

Tất cả các file tạm bên ngoài đã được thu hồi và dọn dẹp sạch sẽ.
