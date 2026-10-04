# PHÂN TÍCH NGUYÊN NHÂN VÀ ĐỀ XUẤT GIẢI PHÁP SỬA LỖI (DEFECT ANALYSIS & PROPOSED FIX STRATEGY)
**Dự án:** KIỂM THỬ PHẦN MỀM TỰ ĐỘNG CHO WEBSITE MOBILECITY BẰNG SELENIUM  
**Vị trí tài liệu:** `Team/7. Documentation/DefectAnalysis_And_FixStrategy.md`  
**Tuân thủ nguyên tắc:** Áp dụng **Phương án B** – Ghi nhận defect thực tế, phân tích nguyên nhân gốc rễ và đề xuất giải pháp kỹ thuật, KHÔNG tự ý tuyên bố đã sửa đổi website production MobileCity.

---

## 1. TỔNG QUAN VỀ PHƯƠNG ÁN XỬ LÝ (PHƯƠNG ÁN B)

Website đối tượng kiểm thử là **MobileCity** (`https://mobilecity.vn`), một hệ thống thương mại điện tử thật bên ngoài do Công ty MobileCity sở hữu và vận hành.
- Nhóm kiểm thử đóng vai trò người dùng cuối (Black-box Testing).
- Nhóm **không có quyền truy cập server, database hoặc repository mã nguồn backend** của MobileCity.
- Do đó, đối với các lỗi phát hiện qua kiểm thử tự động:
  1. Ghi nhận trung thực kết quả `FAIL` và tạo `DefectReport`.
  2. Lưu trữ bằng chứng hình ảnh (screenshot) và log lỗi Surefire.
  3. Tiến hành phân tích kỹ thuật nguyên nhân gốc rễ (Root Cause Analysis - RCA).
  4. Đề xuất đoạn mã khắc phục chuẩn (Proposed Code Fix) từ góc độ Developer.
  5. Khi tiến hành Retest (Phase 8), ghi nhận trung thực kết quả `FAIL (Reopen) / Not Fixed` vì server production chưa áp dụng bản vá.

---

## 2. CHI TIẾT DEFECT VÀ PHÂN TÍCH KỸ THUẬT

### 2.1. DEFECT 01: `DEF-001` – Tìm kiếm với từ khóa tiếng Việt có dấu trả về 0 kết quả
- **Mã liên kết:** `TC-SEARCH-006` (SearchTest)
- **Mức độ:** Severity: `Medium` | Priority: `P2`
- **Ảnh chụp bằng chứng:** `Team/6. Evidence/defect-evidence/TC_SEARCH_006_VietnameseKeyword_20261004_215632_FAIL.png`

#### A. Mô tả hiện tượng lỗi
Khi người dùng nhập từ khóa tìm kiếm tiếng Việt có dấu như `"điện thoại Samsung"`, hệ thống gửi HTTP POST tới endpoint `https://mobilecity.vn/tim-kiem` với tham số `keyword=điện thoại Samsung`. Kết quả trả về là 0 sản phẩm, mặc dù khi tìm kiếm với từ khóa không dấu `"Samsung"` hoặc `"dien thoai"` thì website trả về rất nhiều sản phẩm tương ứng.

#### B. Phân tích nguyên nhân gốc rễ (Root Cause Analysis)
1. **Mã hóa chuỗi (Encoding Mismatch):** Form tìm kiếm gửi chuỗi UTF-8 nhưng backend PHP/Laravel xử lý qua truy vấn SQL `LIKE '%điện thoại Samsung%'` mà không qua bước chuẩn hóa bảng mã Unicode (NFC vs NFD) hoặc không loại bỏ dấu thanh khi so khớp.
2. **Collation cơ sở dữ liệu:** Cột `product_name` trong cơ sở dữ liệu MySQL sử dụng collation phân biệt dấu (accent-sensitive như `utf8mb4_bin` hoặc `utf8mb4_unicode_520_ci` chưa cấu hình bộ lọc unaccent), khiến từ khóa chứa cụm từ `"điện thoại"` không khớp với tên sản phẩm vốn chỉ chứa tên model (ví dụ: `"Samsung Galaxy S24 Ultra"`).
3. **Thiếu cơ chế tách từ (Tokenization / Full-text search):** Hệ thống đang tìm kiếm chuỗi tuyệt đối thay vì tách từ khóa thành các token riêng biệt `['điện', 'thoại', 'Samsung']` và loại bỏ các stopwords như `"điện thoại"` để tập trung vào từ khóa thương hiệu `"Samsung"`.

#### C. Đề xuất giải pháp sửa lỗi (Proposed Technical Fix)
Trong mã nguồn Controller phía Backend (Laravel/PHP), áp dụng phương pháp chuẩn hóa chuỗi và tách từ khóa:

```php
<?php
namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Models\Product;

class SearchController extends Controller
{
    public function search(Request $request)
    {
        $rawKeyword = trim($request->input('keyword', ''));
        
        if (empty($rawKeyword)) {
            return redirect()->back();
        }

        // 1. Chuẩn hóa Unicode về chuẩn NFC
        $keyword = normalizer_normalize($rawKeyword, \Normalizer::FORM_C);

        // 2. Danh sách từ dừng phổ biến (stopwords) cần loại bỏ khi tìm kiếm
        $stopWords = ['điện thoại', 'máy tính', 'chính hãng', 'giá rẻ'];
        $cleanKeyword = str_ireplace($stopWords, '', $keyword);
        $cleanKeyword = trim(preg_replace('/\s+/', ' ', $cleanKeyword));

        // 3. Truy vấn linh hoạt bằng Full-text Search hoặc MATCH AGAINST / LIKE
        $searchTerm = !empty($cleanKeyword) ? $cleanKeyword : $keyword;
        
        $products = Product::where('status', 1)
            ->where(function($query) use ($searchTerm, $keyword) {
                $query->where('name', 'LIKE', '%' . $searchTerm . '%')
                      ->orWhere('name', 'LIKE', '%' . $keyword . '%')
                      ->orWhere('description', 'LIKE', '%' . $searchTerm . '%');
            })
            ->paginate(20);

        return view('pages.search_result', compact('products', 'keyword'));
    }
}
```

---

### 2.2. DEFECT 02: `DEF-002` – Form đăng ký thiếu trường nhập Email theo chuẩn web
- **Mã liên kết:** `TC-REG-002` (AccountTest)
- **Mức độ:** Severity: `Low` | Priority: `P3`
- **Ảnh chụp bằng chứng:** `Team/6. Evidence/defect-evidence/TC_REG_002_InvalidEmailFormat_20261004_214754_FAIL.png`

#### A. Mô tả hiện tượng lỗi
Tại trang đăng ký tài khoản `https://mobilecity.vn/register`, giao diện chỉ cung cấp trường nhập Số điện thoại và Mật khẩu (để gửi mã OTP xác minh qua SMS), không cung cấp trường nhập Email (`input[type='email']`). Điều này khiến kịch bản kiểm thử validation định dạng email thất bại và hạn chế đối tượng khách hàng muốn đăng ký bằng email hoặc chưa có số điện thoại tại Việt Nam.

#### B. Phân tích nguyên nhân gốc rễ (Root Cause Analysis)
Website MobileCity thiết kế quy trình định danh người dùng (KYC) gắn chặt với số điện thoại để phòng chống spam đơn hàng và kích hoạt bảo hành điện tử qua SMS. Tuy nhiên, việc loại bỏ hoàn toàn tùy chọn đăng ký bằng Email làm giảm tính tiện dụng (Usability) và không tuân thủ chuẩn form đăng ký e-commerce quốc tế.

#### C. Đề xuất giải pháp sửa lỗi (Proposed Technical Fix)
Bổ sung trường Email tùy chọn trên giao diện Blade/HTML của form đăng ký:

```html
<!-- Bổ sung trường Email trên form đăng ký -->
<div class="form-group mb-3">
    <label for="email" class="form-label">Địa chỉ Email (tùy chọn để nhận hóa đơn và thông báo):</label>
    <div class="input-group">
        <span class="input-group-text"><i class="fa fa-envelope"></i></span>
        <input type="email" 
               name="email" 
               id="email" 
               class="form-control" 
               placeholder="Nhập địa chỉ email hợp lệ (ví dụ: user@example.com)"
               pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$"
               title="Vui lòng nhập đúng định dạng email">
    </div>
    <div class="invalid-feedback">
        Định dạng email không hợp lệ. Vui lòng kiểm tra lại ký tự @ và tên miền.
    </div>
</div>
```

Đồng thời trong Controller xử lý đăng ký (`RegisterController.php`), bổ sung rule validation:
```php
$request->validate([
    'phone' => 'required|regex:/^[0-9]{10}$/|unique:users,phone',
    'password' => 'required|min:6',
    'email' => 'nullable|email|max:255|unique:users,email'
]);
```

---

## 3. KẾT LUẬN VÀ BÀN GIAO CHO PHASE 8 & 9

1. Cả 2 Defect đã được phân tích nguyên nhân kỹ thuật chi tiết và lập phương án khắc phục hoàn chỉnh.
2. Tài liệu này được lưu trữ chính thức tại `Team/7. Documentation/DefectAnalysis_And_FixStrategy.md`.
3. Bàn giao danh sách cho **Phase 8 (Retest)** và **Phase 9 (Regression)** để kiểm thử lại trên môi trường thực tế và xác nhận trạng thái.
