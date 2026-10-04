# SCOPE.MD – Phạm vi kiểm thử MobileCity
**Dự án:** Kiểm thử tự động website MobileCity bằng Selenium  
**Phiên bản:** 1.0  
**Ngày tạo:** 2026-09-23  
**Người tạo:** Test Manager  

---

## 1. Thông tin website

| Thuộc tính | Giá trị |
|-----------|---------|
| Website | MobileCity |
| URL chính | https://mobilecity.vn |
| URL danh mục điện thoại | https://mobilecity.vn/dien-thoai |
| URL tìm kiếm | https://mobilecity.vn/tim-kiem |
| URL đăng nhập | https://mobilecity.vn/login |
| URL đăng ký | https://mobilecity.vn/register |
| URL giỏ hàng | https://mobilecity.vn/gio-hang |
| Ngôn ngữ | Tiếng Việt |
| Framework backend | Laravel (có CSRF token) |
| Charset | UTF-8 |

---

## 2. Các trang/module đã xác nhận tồn tại

Dựa trên phân tích DOM thực tế ngày 2026-09-23:

| # | Module | URL | Trạng thái |
|---|--------|-----|-----------|
| 1 | Homepage | https://mobilecity.vn | ✅ Hoạt động |
| 2 | Điện thoại | https://mobilecity.vn/dien-thoai | ✅ Hoạt động |
| 3 | Tìm kiếm | https://mobilecity.vn/tim-kiem | ✅ Hoạt động |
| 4 | Đăng nhập | https://mobilecity.vn/login | ✅ Hoạt động |
| 5 | Đăng ký | https://mobilecity.vn/register | ✅ Hoạt động |
| 6 | Giỏ hàng | https://mobilecity.vn/gio-hang | ✅ Hoạt động |
| 7 | Tin tức | https://mobilecity.vn/tin-tuc | ✅ Hoạt động |
| 8 | Events | https://mobilecity.vn/event | ✅ Hoạt động |
| 9 | Tra cứu bảo hành | https://mobilecity.vn/tra-cuu-bao-hanh | ✅ Hoạt động |
| 10 | iPhone Chính hãng VN/A | https://mobilecity.vn/dien-thoai-iphone-chinh-hang-vna | ✅ Hoạt động |
| 11 | Điện thoại cũ | https://mobilecity.vn/dien-thoai-kho-may-cu | ✅ Hoạt động |
| 12 | Samsung chính hãng | https://mobilecity.vn/dien-thoai-samsung-chinh-hang | ✅ Hoạt động |

---

## 3. IN SCOPE – Phạm vi kiểm thử

### 3.1. Chức năng được chọn kiểm thử

| # | Module | Lý do chọn |
|---|--------|-----------|
| F01 | Homepage | Điểm vào chính, nhiều UI element |
| F02 | Navigation / Menu | Header navigation quan trọng |
| F03 | Search | Chức năng cốt lõi – người dùng thường dùng |
| F04 | Product Category (Điện thoại) | Trang listing sản phẩm chính |
| F05 | Product Detail | Trang chi tiết sản phẩm |
| F06 | Login | Chức năng xác thực người dùng |
| F07 | Register | Tạo tài khoản mới |
| F08 | Cart (Giỏ hàng) | Xem trạng thái giỏ hàng |
| F09 | Form Validation | Kiểm tra validation input |
| F10 | Location Selector | Chọn khu vực xem giá/tồn kho |

### 3.2. Loại kiểm thử

| Loại kiểm thử | Áp dụng |
|--------------|---------|
| Functional Testing | ✅ |
| UI/UX Testing | ✅ |
| Input Validation Testing | ✅ |
| Navigation Testing | ✅ |
| Negative Testing | ✅ |
| Boundary Value Testing | ✅ (áp dụng cho search, form) |
| Smoke Testing | ✅ |
| Regression Testing | ✅ (sau khi có fix) |
| Performance Testing | ❌ |
| Security Testing | ❌ |
| API Testing | ❌ |
| Mobile App Testing | ❌ |

---

## 4. OUT OF SCOPE – Ngoài phạm vi

| # | Module/Chức năng | Lý do loại trừ |
|---|-----------------|---------------|
| X01 | Thanh toán (Checkout) | Không được thực hiện giao dịch tài chính thật |
| X02 | Nhập thông tin thẻ | Bảo mật – không nhập dữ liệu thật |
| X03 | Trả góp | Liên quan đến giao dịch tài chính |
| X04 | Vượt CAPTCHA | Vi phạm điều khoản sử dụng website |
| X05 | API internal | Không có quyền truy cập |
| X06 | Admin panel | Không có quyền truy cập |
| X07 | Performance testing | Ngoài phạm vi môn học |
| X08 | Security penetration | Ngoài phạm vi môn học |
| X09 | SMS OTP verification | Cần số điện thoại thật, có chi phí |
| X10 | Fix source code | Không có quyền chỉnh sửa website MobileCity |
| X11 | Tạo hàng loạt tài khoản test | Vi phạm điều khoản sử dụng |

---

## 5. Selectors thực tế đã xác định (từ DOM)

| Element | Selector | Ghi chú |
|---------|----------|---------|
| Search input | `#keyword` | id ổn định |
| Search button | `.btn-search` | class |
| Search form | `#form_submit` | id ổn định |
| Location dropdown | `select.v3_location_name` | select element |
| Logo link | `.v3_logo a` | class |
| Login link | `a[href="https://mobilecity.vn/login"]` | href attribute |
| Register link | `a[href="https://mobilecity.vn/register"]` | href attribute |
| Cart link | `.v3_cart a` | class |
| Menu - Điện thoại | `a[href="https://mobilecity.vn/dien-thoai"]` | href attribute |

---

## 6. Giả định cho phạm vi này

1. **Tài khoản test:** Nhóm sẽ tạo một tài khoản test hợp lệ trên MobileCity trước khi thực thi test đăng nhập.
2. **Fix Defect:** Phương án B – chỉ ghi nhận defect và ghi limitation trong TestReport vì không có source code.
3. **CAPTCHA:** Nếu gặp CAPTCHA trong quá trình automation → đánh dấu BLOCKED.
4. **Tên thành viên:** Sử dụng placeholder cho đến khi có thông tin.
5. **Deadline:** Chưa xác định – không ảnh hưởng đến nội dung kỹ thuật.

---

## 7. Công cụ automation mapping

| Module | Tự động hóa được? | Lý do |
|--------|------------------|-------|
| Homepage | ✅ | Load URL, kiểm tra title/elements |
| Navigation | ✅ | Click link, kiểm tra URL |
| Search | ✅ | Nhập từ khóa, submit, kiểm tra kết quả |
| Product Category | ✅ | Load trang, đếm sản phẩm |
| Product Detail | ✅ | Click sản phẩm, kiểm tra thông tin |
| Login - valid | ✅ | Nếu có tài khoản test |
| Login - invalid | ✅ | Dữ liệu sai, kiểm tra thông báo lỗi |
| Register | ⚠️ BLOCKED | Không được tạo hàng loạt tài khoản |
| Cart (view) | ✅ | Xem giỏ hàng trống |
| Form Validation | ✅ | Nhập dữ liệu sai, kiểm tra error message |
| Checkout | ❌ OUT OF SCOPE | Giao dịch tài chính |
| Location Selector | ✅ | Chọn option trong dropdown |
