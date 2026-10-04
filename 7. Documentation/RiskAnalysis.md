# RISK ANALYSIS – Phân tích rủi ro dự án kiểm thử MobileCity
**Dự án:** Kiểm thử tự động website MobileCity bằng Selenium  
**Phiên bản:** 1.0  
**Ngày tạo:** 2026-09-23  

---

## 1. Phân loại rủi ro

### Mức độ rủi ro
| Mức | Ký hiệu | Mô tả |
|-----|---------|-------|
| Cao | 🔴 HIGH | Có thể gây dừng dự án hoặc mất dữ liệu nghiêm trọng |
| Trung bình | 🟡 MEDIUM | Ảnh hưởng đáng kể đến kết quả nhưng có thể xử lý |
| Thấp | 🟢 LOW | Ảnh hưởng nhỏ, dễ xử lý |

---

## 2. Ma trận rủi ro kỹ thuật

| ID | Rủi ro | Khả năng | Tác động | Mức | Giảm thiểu |
|----|--------|----------|----------|-----|-----------|
| RT-01 | Website MobileCity thay đổi UI/DOM | Cao | Cao | 🔴 HIGH | Phân tích DOM trước mỗi test run; dùng selector ổn định (id, name, href) |
| RT-02 | CAPTCHA xuất hiện trong luồng automation | Trung bình | Cao | 🟡 MEDIUM | Đánh dấu BLOCKED; giải thích trong report |
| RT-03 | Selector không ổn định (dùng index) | Cao | Trung bình | 🔴 HIGH | Ưu tiên id > name > CSS class ổn định > XPath cụ thể |
| RT-04 | Dynamic element chưa load khi assert | Cao | Trung bình | 🔴 HIGH | Dùng WebDriverWait thay vì Thread.sleep |
| RT-05 | Search result khác nhau theo thời gian | Trung bình | Thấp | 🟢 LOW | Assert theo pattern, không hard-code giá trị cụ thể |
| RT-06 | Session timeout trong quá trình test | Thấp | Trung bình | 🟢 LOW | Khởi tạo lại session, login trước mỗi test cần auth |
| RT-07 | Chrome version conflict với ChromeDriver | Thấp | Cao | 🟡 MEDIUM | Dùng Selenium Manager (tự quản lý driver version) |
| RT-08 | Giá sản phẩm thay đổi theo location | Cao | Thấp | 🟢 LOW | Không assert giá trị cụ thể, chỉ assert sự tồn tại |
| RT-09 | Website trả về lỗi HTTP 5xx | Thấp | Cao | 🟡 MEDIUM | Retry logic, ghi nhận environment error |
| RT-10 | Font encoding tiếng Việt trong assertion | Trung bình | Trung bình | 🟡 MEDIUM | Dùng UTF-8, kiểm tra encoding trong assert |

---

## 3. Rủi ro quy trình

| ID | Rủi ro | Khả năng | Tác động | Mức | Giảm thiểu |
|----|--------|----------|----------|-----|-----------|
| RP-01 | Không có tài khoản test → Login tests BLOCKED | Cao | Cao | 🔴 HIGH | Tạo tài khoản test trước Phase 4 |
| RP-02 | Không thể Fix Defect → Retest không có kết quả | Cao | Trung bình | 🟡 MEDIUM | Phương án B: ghi nhận limitation; Retest khi website tự sửa |
| RP-03 | Số lượng defect ít → không đủ để làm DefectReport | Thấp | Thấp | 🟢 LOW | Tập trung negative testing để tìm defect |
| RP-04 | Test result không nhất quán giữa lần chạy | Trung bình | Trung bình | 🟡 MEDIUM | Chạy lại và ghi nhận; sử dụng Explicit Wait |
| RP-05 | Screenshot không lưu được khi FAIL | Thấp | Thấp | 🟢 LOW | Kiểm tra cấu hình screenshot path trước khi chạy |

---

## 4. Rủi ro đạo đức và pháp lý

| ID | Rủi ro | Mức | Biện pháp |
|----|--------|-----|----------|
| RE-01 | Tạo quá nhiều request → ảnh hưởng server MobileCity | 🔴 HIGH | Chỉ chạy test cần thiết; không stress/load test |
| RE-02 | Thực hiện giao dịch thật | 🔴 HIGH | TUYỆT ĐỐI KHÔNG – Out of Scope |
| RE-03 | Tạo spam tài khoản | 🔴 HIGH | Chỉ tạo 1 tài khoản test duy nhất |
| RE-04 | Vượt CAPTCHA bằng công cụ | 🔴 HIGH | Không thực hiện – đánh dấu BLOCKED |
| RE-05 | Tự nhận đã fix code MobileCity | 🔴 HIGH | Không được phép – ghi rõ limitation |

---

## 5. Rủi ro môi trường

| ID | Rủi ro | Mức | Biện pháp |
|----|--------|-----|----------|
| RM-01 | Mạng không ổn định | 🟡 MEDIUM | Chạy test khi mạng ổn định; timeout hợp lý |
| RM-02 | Java version không tương thích | 🟢 LOW | Dùng Java 17 LTS |
| RM-03 | Maven dependency resolve thất bại | 🟢 LOW | Chạy `mvn dependency:resolve` trước |
| RM-04 | ChromeDriver không tương thích | 🟢 LOW | Selenium Manager tự động xử lý |
| RM-05 | Màn hình resolution ảnh hưởng đến element visibility | 🟡 MEDIUM | Maximize browser window trong setup |

---

## 6. Chiến lược xử lý rủi ro theo mức độ

### 🔴 HIGH – Xử lý ngay trước khi bắt đầu test
1. **RT-01:** Cập nhật tất cả selector dựa trên DOM phân tích ngày 2026-09-23
2. **RT-03:** Sử dụng hierarchy selector: `id > name > data-attribute > CSS > XPath`
3. **RT-04:** Implement `WebDriverWait` với timeout 10-15 giây cho tất cả dynamic elements
4. **RP-01:** Tạo tài khoản test MobileCity trước Phase 4
5. **RE-01 đến RE-05:** Tuân thủ nghiêm ngặt quy tắc kiểm thử an toàn

### 🟡 MEDIUM – Có kế hoạch dự phòng
1. **RT-02:** Khi gặp CAPTCHA → ghi BLOCKED, capture screenshot, ghi lý do
2. **RT-07:** Đảm bảo Selenium Manager trong pom.xml (selenium-java 4.x+)
3. **RP-02:** Ghi rõ trong TestReport: "Defect không có Fix vì không có source code"
4. **RP-04:** Nếu test flaky → chạy lại 2-3 lần, ghi nhận kết quả ổn định nhất

### 🟢 LOW – Monitor
1. Các rủi ro LOW → theo dõi, xử lý khi phát sinh

---

## 7. Risk Register – Theo dõi rủi ro

| ID | Rủi ro | Trạng thái | Ngày cập nhật | Kết quả |
|----|--------|-----------|--------------|---------|
| RT-01 | Selector thay đổi | 🔵 MONITORING | 2026-09-23 | DOM đã phân tích, selector đã ghi nhận |
| RT-02 | CAPTCHA | 🔵 MONITORING | 2026-09-23 | Chờ test execution |
| RP-01 | Tài khoản test | ⚠️ OPEN | 2026-09-23 | Chưa có tài khoản test |

---

## 8. Dependency Map

```
[Tài khoản Test] ──────────────────────────► [Login Tests]
        │                                          │
        └──────────────────────────────────────► [Cart Tests (cần auth)]
                                                   │
[MobileCity DOM ổn định] ─────────────────────► [Tất cả Automation Tests]
        │
[Chrome + Selenium Manager] ───────────────────► [Driver setup]
        │
[Mạng ổn định] ────────────────────────────────► [Test Execution]
```

---

## 9. Tổng kết rủi ro theo phase

| Phase | Rủi ro chính | Mức |
|-------|-------------|-----|
| Phase 1 (Phân tích) | Website thay đổi sau phân tích | 🟡 |
| Phase 3 (Test Case) | Selector bị thay đổi | 🟡 |
| Phase 4 (Automation) | Dynamic elements, CAPTCHA | 🔴 |
| Phase 5 (Execution) | Flaky tests, network | 🟡 |
| Phase 6 (Defect) | Ít defect do website hoạt động tốt | 🟢 |
| Phase 7 (Fix) | Không có source code | 🔴 |
| Phase 8 (Retest) | Defect chưa được fix | 🔴 |
| Phase 10 (Report) | Dữ liệu không nhất quán | 🟡 |
