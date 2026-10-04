# BỘ CÂU HỎI VÀ TRẢ LỜI BẢO VỆ ĐỒ ÁN KIỂM THỬ PHẦN MỀM
**Đề tài:** KIỂM THỬ PHẦN MỀM TỰ ĐỘNG CHO WEBSITE MOBILECITY BẰNG SELENIUM  
**Vị trí tài liệu:** `Team/7. Documentation/DEFENSE_QUESTIONS.md`  

---

### 1. Selenium là gì?
Selenium là một bộ công cụ kiểm thử tự động mã nguồn mở phổ biến nhất hiện nay dành cho các ứng dụng web trên nhiều trình duyệt và nền tảng hệ điều hành khác nhau.

### 2. Vì sao sử dụng Selenium?
- Hỗ trợ đa trình duyệt (Chrome, Firefox, Edge, Safari).
- Hỗ trợ đa ngôn ngữ lập trình (Java, Python, C#, JavaScript).
- Cộng đồng lớn, tích hợp mạnh mẽ với các build tool (Maven, Gradle) và test framework (JUnit 5, TestNG).
- Miễn phí và mã nguồn mở.

### 3. WebDriver là gì?
WebDriver là thành phần cốt lõi của Selenium, cung cấp giao diện lập trình hướng đối tượng (API) để tương tác trực tiếp với trình duyệt thông qua giao thức W3C WebDriver Specification (điều khiển trình duyệt như một người dùng thực sự).

### 4. Selenium Manager là gì?
Selenium Manager là tính năng được tích hợp sẵn từ Selenium 4.6+, có nhiệm vụ tự động phát hiện phiên bản trình duyệt đã cài đặt trên máy và tự động tải/cấu hình driver (ví dụ: ChromeDriver) phù hợp mà không cần tải thủ công hay sử dụng thư viện bên ngoài như WebDriverManager.

### 5. Page Object Model (POM) là gì?
Page Object Model là một Design Pattern trong kiểm thử tự động, trong đó mỗi trang web (hoặc một thành phần giao diện) được đóng gói thành một Class Java riêng biệt. Các phần tử (elements) và các hành vi (methods) của trang được định nghĩa trong class này, tách biệt hoàn toàn với logic kiểm thử (Test Class).

### 6. Vì sao sử dụng POM?
- **Tái sử dụng mã nguồn (Reusability):** Một phương thức hoặc locator chỉ cần khai báo một lần.
- **Dễ bảo trì (Maintainability):** Khi website thay đổi giao diện/selector, chỉ cần sửa tại Page Class tương ứng mà không cần sửa hàng chục test script.
- **Code rõ ràng, dễ đọc (Readability):** Test script ngắn gọn, thể hiện đúng luồng nghiệp vụ.

### 7. Explicit Wait là gì?
Explicit Wait (sử dụng `WebDriverWait` và `ExpectedConditions`) là cơ chế chờ có điều kiện trong Selenium, yêu cầu WebDriver chờ đợi một điều kiện cụ thể xảy ra (như element có thể click được, element hiển thị trong DOM, URL thay đổi...) trong một khoảng thời hạn tối đa trước khi ném ra ngoại lệ `TimeoutException`.

### 8. Vì sao không nên dùng Thread.sleep?
- `Thread.sleep` dừng cứng luồng thực thi mà không quan tâm element đã sẵn sàng hay chưa, gây lãng phí thời gian và làm chậm toàn bộ suite test.
- Nếu trang tải chậm hơn thời gian sleep thì test vẫn bị lỗi `NoSuchElementException`.
- Ngược lại, `WebDriverWait` sẽ tiếp tục ngay khi điều kiện được thỏa mãn, giúp test chạy nhanh và ổn định hơn.

### 9. Assertion là gì?
Assertion là câu lệnh dùng để so sánh giữa kết quả thực tế (`Actual Result`) thu được từ hệ thống và kết quả mong đợi (`Expected Result`). Nếu hai giá trị khớp nhau, test case đạt `PASS`; nếu khác nhau, Assertion sẽ ném ra lỗi và đánh dấu test case là `FAIL`.

### 10. Test Case là gì?
Test Case là một tập hợp các điều kiện tiên quyết, dữ liệu đầu vào, các bước thực hiện và kết quả mong đợi được thiết kế để xác minh một tính năng cụ thể của phần mềm có đáp ứng yêu cầu hay không.

### 11. Test Scenario là gì?
Test Scenario (kịch bản kiểm thử) là một phân loại hoặc mô tả tổng quát mức cao về những gì cần kiểm thử trên một chức năng, thường bao gồm nhiều Test Case chi tiết bên trong.

### 12. Test Plan là gì?
Test Plan là tài liệu quản lý kiểm thử mang tính chiến lược, xác định rõ phạm vi, mục tiêu, nguồn lực, môi trường, lịch trình, tiêu chí nghiệm thu (Entry/Exit Criteria) và phương thức quản lý rủi ro của toàn bộ hoạt động kiểm thử trong dự án.

### 13. Defect là gì?
Defect (lỗi/bug) là sự sai khác giữa hành vi thực tế của phần mềm (`Actual Result`) so với yêu cầu hoặc kết quả mong đợi (`Expected Result`).

### 14. Severity khác Priority thế nào?
- **Severity (Mức độ nghiêm trọng):** Đánh giá mức độ tác động kỹ thuật của lỗi đối với hệ thống (Critical, High, Medium, Low).
- **Priority (Mức độ ưu tiên):** Xác định thứ tự mức độ khẩn cấp cần sửa lỗi từ góc độ kinh doanh/dự án (P1, P2, P3, P4).
- *Ví dụ:* Logo công ty trên trang chủ bị hiển thị sai chính tả: Severity Low (không ảnh hưởng chức năng), nhưng Priority P1 (ảnh hưởng hình ảnh thương hiệu).

### 15. Retest là gì?
Retest (kiểm thử lại) là việc thực thi lại chính xác Test Case đã từng bị FAIL sau khi Developer thông báo đã sửa lỗi, nhằm xác minh lỗi đó đã thực sự được khắc phục hay chưa.

### 16. Regression Test là gì?
Regression Test (kiểm thử hồi quy) là việc chạy lại các Test Case ở những module liên quan sau khi có sự thay đổi mã nguồn hoặc sửa lỗi, nhằm đảm bảo các tính năng cũ không bị ảnh hưởng hay phát sinh lỗi mới.

### 17. PASS khác BLOCKED thế nào?
- **PASS:** Test Case được thực thi hoàn chỉnh và kết quả thực tế trùng khớp kết quả mong đợi.
- **BLOCKED:** Test Case không thể tiến hành thực thi được do gặp phải vật cản môi trường, lỗi chặn luồng (như CAPTCHA, server 502, tính năng phụ thuộc chưa hoàn thành...).

### 18. Vì sao không thể kết luận “phần mềm không còn lỗi” chỉ từ test?
Theo nguyên lý nền tảng của kiểm thử phần mềm (Testing shows presence of defects, not their absence): Kiểm thử chỉ chứng minh được sự tồn tại của lỗi, chứ không thể chứng minh phần mềm 100% không còn lỗi nào. Kết quả kiểm thử chỉ khẳng định hệ thống đạt yêu cầu trong phạm vi các kịch bản và dữ liệu đã kiểm thử.

### 19. Vì sao cần Automation Testing?
- Giảm thời gian và chi phí thực thi kiểm thử lặp đi lặp lại (nhất là Regression Testing).
- Tăng độ chính xác, hạn chế sai sót do yếu tố con người.
- Hỗ trợ kiểm thử với khối lượng dữ liệu lớn và chạy tự động trong quy trình CI/CD.

### 20. Hạn chế của Selenium là gì?
- Chỉ hỗ trợ ứng dụng web, không hỗ trợ trực tiếp desktop hay mobile native app.
- Không thể tự giải quyết CAPTCHA / reCAPTCHA.
- Không có hệ thống báo cáo tích hợp sẵn (phải phụ thuộc JUnit, Allure, ExtentReports).
- Đòi hỏi kỹ năng lập trình vững chắc để xây dựng framework ổn định.

### 21. CAPTCHA xử lý thế nào trong kiểm thử tự động?
- Trong môi trường thực tế production bên ngoài: Đánh dấu test case là `BLOCKED`, ghi nhận bằng chứng.
- Trong môi trường phát triển nội bộ: Đề xuất Developer tắt CAPTCHA trên môi trường test, hoặc cấu hình mã bypass/whitelist IP cho automation.

### 22. Dynamic Element là gì?
Dynamic Element là phần tử HTML có thuộc tính (id, class, text...) thay đổi động theo từng phiên làm việc, thời gian hoặc tương tác JavaScript AJAX.

### 23. Selector nào ổn định?
Thứ tự ưu tiên selector ổn định:
1. `id` (nếu là static id, không sinh ngẫu nhiên).
2. `name` / `data-testid` / `data-qa`.
3. `css selector` theo cấu trúc class rõ ràng.
4. `xpath` tương đối theo text hoặc thuộc tính ổn định.
*Tránh dùng:* XPath tuyệt đối (`/html/body/...`) hoặc XPath phụ thuộc index (`div[3]/span[2]`).

### 24. Nếu website thay đổi giao diện thì làm gì?
Nhờ áp dụng Page Object Model, ta chỉ cần vào đúng Page Class đại diện cho trang đó, cập nhật lại locator của phần tử bị thay đổi. Toàn bộ các Test Class gọi phương thức của Page đó sẽ hoạt động bình thường mà không cần sửa code kiểm thử.

### 25. Nếu Selenium test PASS nhưng người dùng vẫn gặp lỗi thì sao?
- Test script có thể chưa bao quát hết các trường hợp biên hoặc luồng thao tác thực tế của người dùng.
- Dữ liệu kiểm thử (`Test Data`) chưa đa dạng hoặc khác biệt với dữ liệu thật.
- Sự khác biệt về môi trường, cấu hình phần cứng, độ phân giải màn hình hoặc tốc độ mạng của người dùng.
- Do đó, cần kết hợp Automation Testing với Exploratory Testing và bổ sung Test Case dựa trên phản hồi của người dùng.
