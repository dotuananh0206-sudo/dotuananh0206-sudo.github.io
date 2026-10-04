# FUNCTIONAL ANALYSIS – Phân tích chức năng MobileCity
**Dự án:** Kiểm thử tự động website MobileCity bằng Selenium  
**Phiên bản:** 1.0  
**Ngày phân tích:** 2026-09-23  
**Phân tích dựa trên:** DOM thực tế từ https://mobilecity.vn (truy cập 2026-09-23)

---

## 1. Cấu trúc Header (đã xác nhận)

### 1.1. Header Row 1 – Top Header

```
[Logo MobileCity]  [Location Dropdown: Hà Nội / TP.HCM / Đà Nẵng]  
[Search Input + Button]  
[Đăng nhập button]  [Đăng ký button]  
[Tin tức | Events | Tra cứu BH]
```

**Selectors đã xác minh:**
- Logo: `img.v3_img-logo` (src: cdn.mobilecity.vn/...logo-mobilecity-1.png.webp)
- Location select: `select.v3_location_name` (option values: 1=Hà Nội, 2=TP.HCM, 3=Đà Nẵng)
- Search input: `input#keyword` (name="keyword", placeholder="Tìm kiếm sản phẩm ...")
- Search form: `form#form_submit` (action="https://mobilecity.vn/tim-kiem", method="post")
- Login button: `a[href="/login"]` bên trong `.v3_btn-action`
- Register button: `a[href="/register"]` bên trong `.v3_btn-action`

### 1.2. Header Row 2 – Category Navigation

```
[Điện thoại ▼]  [Máy tính bảng ▼]  [Phụ kiện ▼]  [Đồng hồ ▼]  ...
```

**Sub-menu Điện thoại:**
- iPhone Chính hãng VN/A
- Điện thoại Cũ
- iPhone Cũ 99%
- Apple Watch
- Samsung Cũ
- Samsung Chính hãng
- (và nhiều hãng khác)

---

## 2. Module F01 – Homepage

### 2.1. Chức năng chính
- Hiển thị banner/slider sản phẩm nổi bật
- Hiển thị danh mục sản phẩm
- Hiển thị sản phẩm khuyến mãi
- Hiển thị sản phẩm mới nhất
- Footer với thông tin liên hệ, chính sách

### 2.2. Test points
| ID | Điểm kiểm thử | Loại |
|----|--------------|------|
| HP-01 | Tiêu đề trang đúng | UI |
| HP-02 | Logo hiển thị | UI |
| HP-03 | Search bar hiển thị | UI |
| HP-04 | Navigation menu hiển thị | UI |
| HP-05 | Trang load không lỗi HTTP | Functional |
| HP-06 | URL canonical đúng | UI |

---

## 3. Module F02 – Navigation

### 3.1. Chức năng
- Click vào menu item → điều hướng đến trang đúng
- Hover vào menu → hiển thị sub-menu
- Logo click → về homepage
- Location dropdown → thay đổi hiển thị giá/tồn kho

### 3.2. Test points
| ID | Điểm kiểm thử | URL đích | Loại |
|----|--------------|---------|------|
| NAV-01 | Click "Điện thoại" | /dien-thoai | Functional |
| NAV-02 | Click Logo → Homepage | / | Functional |
| NAV-03 | Click "Đăng nhập" | /login | Functional |
| NAV-04 | Click "Đăng ký" | /register | Functional |
| NAV-05 | Location chọn "TP. Hồ Chí Minh" | giá thay đổi | Functional |
| NAV-06 | Click "Tin tức" | /tin-tuc | Functional |

---

## 4. Module F03 – Search

### 4.1. Chức năng
- Form: `action="/tim-kiem"`, method POST
- Input: `#keyword`, required
- Có tính năng suggest search (AJAX)
- Kết quả search → trang listing sản phẩm

### 4.2. Selectors xác minh
```html
<form action="https://mobilecity.vn/tim-kiem" method="post" id="form_submit">
    <input type="hidden" name="_token" value="...">
    <input type="text" name="keyword" id="keyword" 
           autocomplete="off" placeholder="Tìm kiếm sản phẩm ..." required>
    <button type="submit" name="submit" class="btn-search">
```

### 4.3. Test points
| ID | Điểm kiểm thử | Test Data | Loại |
|----|--------------|-----------|------|
| SRCH-01 | Tìm kiếm từ khóa hợp lệ | "iPhone 15" | Positive |
| SRCH-02 | Tìm kiếm từ khóa không tồn tại | "xyzabcnotexist123" | Negative |
| SRCH-03 | Tìm kiếm ký tự đặc biệt | "iPhone@#$%" | Boundary |
| SRCH-04 | Tìm kiếm chuỗi dài | 255+ ký tự | Boundary |
| SRCH-05 | Tìm kiếm để trống | "" | Negative |
| SRCH-06 | Suggest search hiển thị | "iphone" | Functional |

---

## 5. Module F04 – Product Category

### 5.1. Chức năng
- URL: `/dien-thoai`
- Hiển thị danh sách sản phẩm (dạng grid)
- Có filter (theo hãng, giá, tình trạng...)
- Có sort (theo giá tăng/giảm, mới nhất...)
- Pagination

### 5.2. Test points
| ID | Điểm kiểm thử | Loại |
|----|--------------|------|
| CAT-01 | Trang category load đúng | Functional |
| CAT-02 | Tiêu đề trang đúng | UI |
| CAT-03 | Sản phẩm hiển thị có tên | UI |
| CAT-04 | Sản phẩm hiển thị có giá | UI |
| CAT-05 | Sản phẩm hiển thị có ảnh | UI |
| CAT-06 | Click sản phẩm → trang detail | Functional |

---

## 6. Module F05 – Product Detail

### 6.1. Chức năng
- URL: `/[slug-san-pham]`
- Hiển thị tên, giá, ảnh, mô tả
- Chọn màu sắc, phiên bản
- Nút "Thêm vào giỏ hàng"
- Thông tin bảo hành
- Sản phẩm liên quan

### 6.2. Test points
| ID | Điểm kiểm thử | Loại |
|----|--------------|------|
| PROD-01 | Tên sản phẩm hiển thị | UI |
| PROD-02 | Giá sản phẩm hiển thị | UI |
| PROD-03 | Ảnh sản phẩm hiển thị | UI |
| PROD-04 | Nút mua/giỏ hàng tồn tại | UI |
| PROD-05 | Thông tin sản phẩm đầy đủ | Functional |

---

## 7. Module F06 – Login

### 7.1. Chức năng
- URL: `/login`
- Form đăng nhập (phone/email + password)
- Đăng nhập bằng Google (SSO)
- Link "Quên mật khẩu"
- Link đến trang Đăng ký

### 7.2. Phân tích form (dựa trên DOM trang /login)
- Form có CSRF token (`name="_token"`)
- Đăng nhập bằng Google One Tap (`credential_picker_container`)
- Title: "Đăng nhập | MobileCity"

### 7.3. Test points
| ID | Điểm kiểm thử | Test Data | Loại |
|----|--------------|-----------|------|
| LOGIN-01 | Đăng nhập đúng credentials | test account | Positive |
| LOGIN-02 | Sai mật khẩu | email đúng, pass sai | Negative |
| LOGIN-03 | Sai email/phone | email không tồn tại | Negative |
| LOGIN-04 | Để trống username | "" | Negative |
| LOGIN-05 | Để trống password | "" | Negative |
| LOGIN-06 | Sau đăng nhập → redirect | N/A | Functional |

---

## 8. Module F07 – Register

### 8.1. Chức năng
- URL: `/register`
- Form đăng ký (họ tên, phone, email, password...)

### 8.2. Test points
> ⚠️ **Lưu ý:** Chỉ kiểm thử UI validation, KHÔNG submit để tạo tài khoản thật trừ khi bắt buộc.

| ID | Điểm kiểm thử | Loại |
|----|--------------|------|
| REG-01 | Trang register load đúng | UI |
| REG-02 | Form validation - để trống | Negative |
| REG-03 | Email sai định dạng | Negative |
| REG-04 | Password quá ngắn (< min length) | Boundary |

---

## 9. Module F08 – Cart (Giỏ hàng)

### 9.1. Chức năng
- URL: `/gio-hang`
- Xem giỏ hàng
- Khi giỏ hàng trống → hiển thị thông báo
- Khi có sản phẩm → hiển thị danh sách + tổng tiền
- Xóa sản phẩm
- Nút Thanh toán (OUT OF SCOPE)

### 9.2. Test points
| ID | Điểm kiểm thử | Loại |
|----|--------------|------|
| CART-01 | Trang giỏ hàng load đúng | Functional |
| CART-02 | Giỏ hàng trống → thông báo phù hợp | UI |
| CART-03 | Cart icon trên header hiển thị | UI |

---

## 10. Module F09 – Form Validation

**Ưu tiên kiểm thử:**
- Search input (required field)
- Login form (required fields)
- Register form (required fields, format check)

---

## 11. Module F10 – Location Selector

### 11.1. Chức năng
```html
<select class="v3_location_name location_name">
    <option value="1" selected>Hà Nội</option>
    <option value="2">TP. Hồ Chí Minh</option>
    <option value="3">Đà Nẵng</option>
</select>
```

### 11.2. Test points
| ID | Điểm kiểm thử | Loại |
|----|--------------|------|
| LOC-01 | Dropdown hiển thị 3 location | UI |
| LOC-02 | Mặc định = Hà Nội | UI |
| LOC-03 | Chọn TP.HCM → giá thay đổi | Functional |

---

## 12. Tổng hợp Test Points theo Module

| Module | Test Points | Automation | Manual |
|--------|-------------|-----------|--------|
| Homepage | 6 | 5 | 1 |
| Navigation | 6 | 6 | 0 |
| Search | 6 | 5 | 1 |
| Category | 6 | 5 | 1 |
| Product Detail | 5 | 4 | 1 |
| Login | 6 | 5 | 1 |
| Register | 4 | 3 | 1 |
| Cart | 3 | 3 | 0 |
| Form Validation | - | Tích hợp vào Login/Register | - |
| Location | 3 | 3 | 0 |
| **TỔNG** | **45** | **39** | **6** |

---

## 13. Ghi chú kỹ thuật

### 13.1. Rủi ro selector
- Website dùng CSS classes có prefix `v3_` – có thể thay đổi khi website cập nhật version
- CSRF token thay đổi mỗi request → Selenium không cần xử lý (browser tự gửi)
- `id="keyword"` và `id="form_submit"` → selector ổn định

### 13.2. Dynamic elements cần chú ý
- Suggest search list: `ul.suggest-search-list` – dynamic, cần Explicit Wait
- Sub-menu: ẩn mặc định, chỉ hiện khi hover
- Product price: thay đổi theo Location Selector → cần test sau khi load trang

### 13.3. Yêu cầu tài khoản test
Để thực thi module Login:
- Cần 1 tài khoản hợp lệ do nhóm tạo trên mobilecity.vn
- Thông tin tài khoản cần được lưu an toàn, không hard-code trong source code
