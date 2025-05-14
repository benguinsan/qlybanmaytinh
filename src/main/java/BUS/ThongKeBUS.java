package BUS;

import DAO.ThongKeDAO;
import DTO.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Calendar;

public class ThongKeBUS {
    private final ThongKeDAO thongKeDAO;
    
    public ThongKeBUS() {
        this.thongKeDAO = ThongKeDAO.getInstance();
    }
    
    //region Thống kê doanh thu
    public ArrayList<ThongKeDoanhThuDTO> getThongKeDoanhThu(String type, int value) {
        try {
            validateTypeParameter(type);
            validateTimeValue(type, value);
            
            ArrayList<ThongKeDoanhThuDTO> result = thongKeDAO.getThongKeDoanhThu(type, value);
            
            if (result == null || result.isEmpty()) {
                throw new Exception("Không có dữ liệu thống kê cho " + type + ": " + value);
            }
            
            return result;
        } catch (IllegalArgumentException e) {
            System.err.println("Lỗi tham số: " + e.getMessage());
            return new ArrayList<>();
        } catch (Exception e) {
            System.err.println("Lỗi khi thống kê doanh thu: " + e.getMessage());
            return new ArrayList<>();
        }
    }
    
    public ThongKeDoanhThuDTO getThongKeTheoKhoangThoiGian(Date from, Date to) {
        try {
            validateDateRange(from, to);
            
            ThongKeDoanhThuDTO result = thongKeDAO.getThongKeTheoKhoangThoiGian(from, to);
            
            if (result == null) {
                throw new Exception("Không có dữ liệu thống kê trong khoảng thời gian được chọn");
            }
            
            return result;
        } catch (IllegalArgumentException e) {
            System.err.println("[Lỗi tham số] Khoảng thời gian không hợp lệ: " + e.getMessage());
            return new ThongKeDoanhThuDTO(0, 0L, 0L, 0L);
        } catch (Exception e) {
            System.err.println("[Lỗi xử lý] Không thể thống kê theo khoảng thời gian: " + e.getMessage());
            return new ThongKeDoanhThuDTO(0, 0L, 0L, 0L);
        }
    }

    public ArrayList<ThongKeTheoThangDTO> getThongKeTheoThang(int year) {
        try {
            validateYear(year);
            
            ArrayList<ThongKeTheoThangDTO> result = thongKeDAO.getThongKeTheoThang(year);
            
            if (result == null || result.isEmpty()) {
                throw new Exception("Không có dữ liệu thống kê cho năm " + year);
            }
            
            return result;
        } catch (IllegalArgumentException e) {
            System.err.println("Lỗi tham số: " + e.getMessage());
            return new ArrayList<>();
        } catch (Exception e) {
            System.err.println("Lỗi khi thống kê theo tháng: " + e.getMessage());
            return new ArrayList<>();
        }
    }
    
    public ArrayList<ThongKeTungNgayTrongThangDTO> getThongKeTungNgayTrongThang(int month, int year) {
        try {
            validateMonth(month);
            validateYear(year);
            
            ArrayList<ThongKeTungNgayTrongThangDTO> result = thongKeDAO.getThongKeTungNgayTrongThang(month, year);
            
            if (result == null || result.isEmpty()) {
                throw new Exception("Không có dữ liệu thống kê cho tháng " + month + "/" + year);
            }
            
            return result;
        } catch (IllegalArgumentException e) {
            System.err.println("Lỗi tham số: " + e.getMessage());
            return new ArrayList<>();
        } catch (Exception e) {
            System.err.println("Lỗi khi thống kê từng ngày trong tháng: " + e.getMessage());
            return new ArrayList<>();
        }
    }
    //endregion
    
    //region Thống kê khách hàng và nhà cung cấp
    public ArrayList<ThongKeKhachHangDTO> getThongKeKhachHang(Date from, Date to) {
        try {
            validateDateRange(from, to);
            
            ArrayList<ThongKeKhachHangDTO> result = thongKeDAO.getThongKeKhachHang(from, to);
            
            if (result == null || result.isEmpty()) {
                throw new Exception("Không có dữ liệu khách hàng trong khoảng thời gian được chọn");
            }
            
            return result;
        } catch (IllegalArgumentException e) {
            System.err.println("Lỗi tham số: " + e.getMessage());
            return new ArrayList<>();
        } catch (Exception e) {
            System.err.println("Lỗi khi thống kê khách hàng: " + e.getMessage());
            return new ArrayList<>();
        }
    }
    
    public ArrayList<ThongKeNhaCungCapDTO> getThongKeNhaCungCap(Date from, Date to) {
        try {
            validateDateRange(from, to);
            
            ArrayList<ThongKeNhaCungCapDTO> result = thongKeDAO.getThongKeNhaCungCap(from, to);
            
            if (result == null || result.isEmpty()) {
                throw new Exception("Không có dữ liệu nhà cung cấp trong khoảng thời gian được chọn");
            }
            
            return result;
        } catch (IllegalArgumentException e) {
            System.err.println("Lỗi tham số: " + e.getMessage());
            return new ArrayList<>();
        } catch (Exception e) {
            System.err.println("Lỗi khi thống kê nhà cung cấp: " + e.getMessage());
            return new ArrayList<>();
        }
    }
    //endregion
    
    //region Thống kê tồn kho
    public ArrayList<ThongKeTonKhoDTO> getThongKeTonKho(Date from, Date to) {
        try {
            validateDateRange(from, to);
            
            ArrayList<ThongKeTonKhoDTO> result = thongKeDAO.getThongKeTonKho(from, to);
            
            if (result == null || result.isEmpty()) {
                throw new Exception("Không có dữ liệu tồn kho trong khoảng thời gian được chọn");
            }
            
            return result;
        } catch (IllegalArgumentException e) {
            System.err.println("Lỗi tham số: " + e.getMessage());
            return new ArrayList<>();
        } catch (Exception e) {
            System.err.println("Lỗi khi thống kê tồn kho: " + e.getMessage());
            return new ArrayList<>();
        }
    }
    //endregion
    
    //region Các phương thức tiện ích
    public ArrayList<Integer> getDanhSachNam() {
        try {
            ArrayList<Integer> result = thongKeDAO.getDanhSachNam();
            
            if (result == null || result.isEmpty()) {
                int currentYear = Calendar.getInstance().get(Calendar.YEAR);
                result = new ArrayList<>();
                result.add(currentYear);
                result.add(currentYear - 1);
                throw new Exception("Không có dữ liệu năm, sử dụng năm mặc định");
            }
            
            return result;
        } catch (Exception e) {
            System.err.println("Lỗi khi lấy danh sách năm: " + e.getMessage());
            
            // Return default years even if there's an error
            ArrayList<Integer> defaultYears = new ArrayList<>();
            int currentYear = Calendar.getInstance().get(Calendar.YEAR);
            defaultYears.add(currentYear);
            defaultYears.add(currentYear - 1);
            return defaultYears;
        }
    }
    
    public long tinhTongDoanhThu(List<ThongKeDoanhThuDTO> danhSach) {
        if (danhSach == null || danhSach.isEmpty()) {
            return 0;
        }
        return danhSach.stream()
                .mapToLong(ThongKeDoanhThuDTO::getDoanhthu)
                .sum();
    }
    
    public long tinhTongLoiNhuan(List<ThongKeDoanhThuDTO> danhSach) {
        if (danhSach == null || danhSach.isEmpty()) {
            return 0;
        }
        return danhSach.stream()
                .mapToLong(ThongKeDoanhThuDTO::getLoinhuan)
                .sum();
    }
    
    public List<ThongKeKhachHangDTO> locTopKhachHang(List<ThongKeKhachHangDTO> danhSach, int top) {
        if (danhSach == null || danhSach.isEmpty()) {
            return new ArrayList<>();
        }
        return danhSach.stream()
                .sorted((a, b) -> Long.compare(b.getTongTien(), a.getTongTien()))
                .limit(top > 0 ? top : 5)
                .collect(Collectors.toList());
    }
    
    public List<ThongKeNhaCungCapDTO> locTopNhaCungCap(List<ThongKeNhaCungCapDTO> danhSach, int top) {
        if (danhSach == null || danhSach.isEmpty()) {
            return new ArrayList<>();
        }
        return danhSach.stream()
                .sorted((a, b) -> Long.compare(b.getTongtien(), a.getTongtien()))
                .limit(top > 0 ? top : 5)
                .collect(Collectors.toList());
    }
    
    public List<ThongKeTonKhoDTO> locSanPhamTonNhieuNhat(List<ThongKeTonKhoDTO> danhSach, int top) {
        if (danhSach == null || danhSach.isEmpty()) {
            return new ArrayList<>();
        }
        return danhSach.stream()
                .sorted((a, b) -> Integer.compare(b.getToncuoiky(), a.getToncuoiky()))
                .limit(top > 0 ? top : 5)
                .collect(Collectors.toList());
    }
    
    public List<ThongKeTonKhoDTO> locSanPhamBanChayNhat(List<ThongKeTonKhoDTO> danhSach, int top) {
        if (danhSach == null || danhSach.isEmpty()) {
            return new ArrayList<>();
        }
        return danhSach.stream()
                .sorted((a, b) -> Integer.compare(b.getXuattrongky(), a.getXuattrongky()))
                .limit(top > 0 ? top : 5)
                .collect(Collectors.toList());
    }
    //endregion
    
    //region Validation methods
    private void validateTypeParameter(String type) {
        if (type == null || (!"year".equalsIgnoreCase(type) 
                && !"month".equalsIgnoreCase(type) 
                && !"day".equalsIgnoreCase(type))) {
            throw new IllegalArgumentException("Loại thống kê không hợp lệ. Chỉ chấp nhận 'year', 'month' hoặc 'day'");
        }
    }
    
    private void validateTimeValue(String type, int value) {
        switch (type.toLowerCase()) {
            case "year":
                validateYear(value);
                break;
            case "month":
                validateMonth(value);
                break;
            case "day":
                if (value < 1 || value > 31) {
                    throw new IllegalArgumentException("Ngày phải nằm trong khoảng 1-31");
                }
                break;
        }
    }
    
    private void validateDateRange(Date from, Date to) {
        if (from == null || to == null) {
            throw new IllegalArgumentException("Ngày bắt đầu và kết thúc không được null");
        }
        if (from.after(to)) {
            throw new IllegalArgumentException("Ngày bắt đầu phải trước ngày kết thúc");
        }
        
        // Validate date not in the future
        Date currentDate = new Date();
        if (from.after(currentDate) || to.after(currentDate)) {
            throw new IllegalArgumentException("Ngày không được lớn hơn ngày hiện tại");
        }
    }
    
    private void validateYear(int year) {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        if (year < 2000 || year > currentYear + 1) {
            throw new IllegalArgumentException("Năm phải nằm trong khoảng 2000 đến " + (currentYear + 1));
        }
    }
    
    private void validateMonth(int month) {
        if (month < 1 || month > 12) {
            throw new IllegalArgumentException("Tháng phải nằm trong khoảng 1-12");
        }
    }
    //endregion
}