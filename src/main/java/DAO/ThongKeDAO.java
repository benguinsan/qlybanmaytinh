package DAO;

import DTO.*;
import Database.DBConnection;
import Utility.CustomLogger;
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;

public class ThongKeDAO {
    
    private static ThongKeDAO instance;
    private final DBConnection dbConnection;
    
    public static ThongKeDAO getInstance() {
        if (instance == null) {
            instance = new ThongKeDAO();
        }
        return instance;
    }
    
    private ThongKeDAO() {
        dbConnection = new DBConnection();
    }
    
    // Thống kê doanh thu theo năm, tháng hoặc ngày
  public ArrayList<ThongKeDoanhThuDTO> getThongKeDoanhThu(String type, int value) {
        ArrayList<ThongKeDoanhThuDTO> result = new ArrayList<>();
        String sql;
        
        switch (type.toLowerCase()) {
            case "year":
                sql = "SELECT YEAR(hd.ngay_lap) as thoigian, " +
                      "(SELECT COALESCE(SUM(pn.TongTien), 0) FROM phieu_nhap pn WHERE YEAR(pn.NgayNhap) = YEAR(hd.ngay_lap)) as von, " +
                      "SUM(hd.tong_tien) as doanhthu, " +
                      "SUM(hd.tong_tien) - (SELECT COALESCE(SUM(pn.TongTien), 0) FROM phieu_nhap pn WHERE YEAR(pn.NgayNhap) = YEAR(hd.ngay_lap)) as loinhuan " +
                      "FROM hoa_don hd " +
                      "WHERE YEAR(hd.ngay_lap) = ? " +
                      "GROUP BY YEAR(hd.ngay_lap)";
                break;
            case "month":
                sql = "SELECT MONTH(hd.ngay_lap) as thoigian, " +
                      "(SELECT COALESCE(SUM(pn.TongTien), 0) FROM phieu_nhap pn WHERE MONTH(pn.NgayNhap) = MONTH(hd.ngay_lap) AND YEAR(pn.NgayNhap) = YEAR(hd.ngay_lap)) as von, " +
                      "SUM(hd.tong_tien) as doanhthu, " +
                      "SUM(hd.tong_tien) - (SELECT COALESCE(SUM(pn.TongTien), 0) FROM phieu_nhap pn WHERE MONTH(pn.NgayNhap) = MONTH(hd.ngay_lap) AND YEAR(pn.NgayNhap) = YEAR(hd.ngay_lap)) as loinhuan " +
                      "FROM hoa_don hd " +
                      "WHERE YEAR(hd.ngay_lap) = ? " +
                      "GROUP BY MONTH(hd.ngay_lap)";
                break;
            case "day":
                sql = "SELECT DAY(hd.ngay_lap) as thoigian, " +
                      "(SELECT COALESCE(SUM(pn.TongTien), 0) FROM phieu_nhap pn WHERE DATE(pn.NgayNhap) = DATE(hd.ngay_lap)) as von, " +
                      "SUM(hd.tong_tien) as doanhthu, " +
                      "SUM(hd.tong_tien) - (SELECT COALESCE(SUM(pn.TongTien), 0) FROM phieu_nhap pn WHERE DATE(pn.NgayNhap) = DATE(hd.ngay_lap)) as loinhuan " +
                      "FROM hoa_don hd " +
                      "WHERE MONTH(hd.ngay_lap) = ? AND YEAR(hd.ngay_lap) = ? " +
                      "GROUP BY DAY(hd.ngay_lap)";
                break;
            default:
                throw new IllegalArgumentException("Invalid type: " + type);
        }
        
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            if (type.equalsIgnoreCase("day")) {
                pst.setInt(1, value); // month
                pst.setInt(2, Calendar.getInstance().get(Calendar.YEAR)); // current year
            } else {
                pst.setInt(1, value);
            }
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    result.add(new ThongKeDoanhThuDTO(
                        rs.getInt("thoigian"),
                        rs.getLong("von"),
                        rs.getLong("doanhthu"),
                        rs.getLong("loinhuan")
                    ));
                }
            }
        } catch (SQLException ex) {
            CustomLogger.CustomLogger(ThongKeDAO.class.getName(), ex.getMessage(), Level.SEVERE);
        }
        return result;
    }
    
    // Thống kê theo khoảng thời gian
    public ThongKeDoanhThuDTO getThongKeTheoKhoangThoiGian(java.util.Date from, java.util.Date to) {
        ThongKeDoanhThuDTO result = null;
        String sql = "SELECT " +
                     "(SELECT COALESCE(SUM(pn.TongTien), 0) FROM phieu_nhap pn WHERE pn.NgayNhap BETWEEN ? AND ?) as von, " +
                     "(SELECT COALESCE(SUM(hd.tong_tien), 0) FROM hoa_don hd WHERE hd.ngay_lap BETWEEN ? AND ?) as doanhthu, " +
                     "(SELECT COALESCE(SUM(hd.tong_tien), 0) FROM hoa_don hd WHERE hd.ngay_lap BETWEEN ? AND ?) - " +
                     "(SELECT COALESCE(SUM(pn.TongTien), 0) FROM phieu_nhap pn WHERE pn.NgayNhap BETWEEN ? AND ?) as loinhuan";
        
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            java.sql.Date sqlFrom = new java.sql.Date(from.getTime());
            java.sql.Date sqlTo = new java.sql.Date(to.getTime());
            
            pst.setDate(1, sqlFrom);
            pst.setDate(2, sqlTo);
            pst.setDate(3, sqlFrom);
            pst.setDate(4, sqlTo);
            pst.setDate(5, sqlFrom);
            pst.setDate(6, sqlTo);
            pst.setDate(7, sqlFrom);
            pst.setDate(8, sqlTo);
            
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    result = new ThongKeDoanhThuDTO(
                        0, // không có thời gian cụ thể
                        rs.getLong("von"),
                        rs.getLong("doanhthu"),
                        rs.getLong("loinhuan")
                    );
                }
            }
        } catch (SQLException ex) {
            CustomLogger.CustomLogger(ThongKeDAO.class.getName(), ex.getMessage(), Level.SEVERE);
        }
        return result;
    }
    
    // Thống kê theo tháng
    public ArrayList<ThongKeTheoThangDTO> getThongKeTheoThang(int year) {
        ArrayList<ThongKeTheoThangDTO> result = new ArrayList<>();
        String sql = "SELECT MONTH(hd.ngay_lap) as thang, " +
                     "(SELECT COALESCE(SUM(pn.TongTien), 0) FROM phieu_nhap pn WHERE MONTH(pn.NgayNhap) = MONTH(hd.ngay_lap) AND YEAR(pn.NgayNhap) = ?) as chiphi, " +
                     "SUM(hd.tong_tien) as doanhthu, " +
                     "SUM(hd.tong_tien) - (SELECT COALESCE(SUM(pn.TongTien), 0) FROM phieu_nhap pn WHERE MONTH(pn.NgayNhap) = MONTH(hd.ngay_lap) AND YEAR(pn.NgayNhap) = ?) as loinhuan " +
                     "FROM hoa_don hd " +
                     "WHERE YEAR(hd.ngay_lap) = ? " +
                     "GROUP BY MONTH(hd.ngay_lap) ORDER BY thang";
        
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, year);
            pst.setInt(2, year);
            pst.setInt(3, year);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    result.add(new ThongKeTheoThangDTO(
                        rs.getInt("thang"),
                        (int)rs.getLong("chiphi"),
                        (int)rs.getLong("doanhthu"),
                        (int)rs.getLong("loinhuan")
                    ));
                }
            }
        } catch (SQLException ex) {
            CustomLogger.CustomLogger(ThongKeDAO.class.getName(), ex.getMessage(), Level.SEVERE);
        }
        return result;
    }
    
    // Thống kê khách hàng mua nhiều nhất
   public ArrayList<ThongKeKhachHangDTO> getThongKeKhachHang(java.util.Date from, java.util.Date to) {
    ArrayList<ThongKeKhachHangDTO> result = new ArrayList<>();
    String sql = "SELECT kh.ma_khach_hang, kh.ho_ten, COUNT(hd.ma_hd) as soluongphieu, " +
                 "SUM(hd.tong_tien) as tongtien FROM khach_hang kh " +
                 "JOIN hoa_don hd ON kh.ma_khach_hang = hd.ma_khach_hang " +
                 "WHERE hd.ngay_lap BETWEEN ? AND ? " +
                 "GROUP BY kh.ma_khach_hang, kh.ho_ten ORDER BY tongtien DESC";
    
    try (Connection conn = dbConnection.getConnection();
         PreparedStatement pst = conn.prepareStatement(sql)) {
        pst.setDate(1, new java.sql.Date(from.getTime()));
        pst.setDate(2, new java.sql.Date(to.getTime()));
        try (ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                result.add(new ThongKeKhachHangDTO(
                    rs.getString("ma_khach_hang"),  // Get as String
                    rs.getString("ho_ten"),
                    rs.getInt("soluongphieu"),
                    rs.getLong("tongtien")
                ));
            }
        }
    } catch (SQLException ex) {
        CustomLogger.CustomLogger(ThongKeDAO.class.getName(), ex.getMessage(), Level.SEVERE);
    }
    return result;
}

// Similarly update getThongKeNhaCungCap method
public ArrayList<ThongKeNhaCungCapDTO> getThongKeNhaCungCap(java.util.Date from, java.util.Date to) {
    ArrayList<ThongKeNhaCungCapDTO> result = new ArrayList<>();
    String sql = "SELECT ncc.ma_ncc, ncc.ten_ncc, COUNT(pn.ma_pn) as soluong, " +
                 "SUM(pn.TongTien) as tongtien FROM nha_cung_cap ncc " +
                 "JOIN phieu_nhap pn ON ncc.ma_ncc = pn.ma_ncc " +
                 "WHERE pn.NgayNhap BETWEEN ? AND ? " +
                 "GROUP BY ncc.ma_ncc, ncc.ten_ncc ORDER BY tongtien DESC";
    
    try (Connection conn = dbConnection.getConnection();
         PreparedStatement pst = conn.prepareStatement(sql)) {
        pst.setDate(1, new java.sql.Date(from.getTime()));
        pst.setDate(2, new java.sql.Date(to.getTime()));
        try (ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                result.add(new ThongKeNhaCungCapDTO(
                    rs.getString("ma_ncc"),  // Get as String
                    rs.getString("ten_ncc"),
                    rs.getInt("soluong"),
                    rs.getLong("tongtien")
                ));
            }
        }
    } catch (SQLException ex) {
        CustomLogger.CustomLogger(ThongKeDAO.class.getName(), ex.getMessage(), Level.SEVERE);
    }
    return result;
}
    // Thống kê tồn kho
    public ArrayList<ThongKeTonKhoDTO> getThongKeTonKho(java.util.Date from, java.util.Date to) {
        ArrayList<ThongKeTonKhoDTO> result = new ArrayList<>();
        String sql = "SELECT sp.ma_sp, ctsp.ma_chi_tiet, sp.TenSP as tensanpham, " +
                     "ctsp.ram, ctsp.chip, ctsp.card, " +
                     "(SELECT COALESCE(SUM(ctpn.SoLuong), 0) " +
                     "FROM chi_tiet_phieu_nhap ctpn JOIN phieu_nhap pn ON ctpn.ma_pn = pn.ma_pn " +
                     "WHERE ctpn.ma_sp = sp.ma_sp AND pn.NgayNhap BETWEEN ? AND ?) as nhaptrongky, " +
                     "(SELECT COALESCE(SUM(cthd.so_luong), 0) " +
                     "FROM chi_tiet_hoa_don cthd JOIN hoa_don hd ON cthd.ma_hd = hd.ma_hd " +
                     "WHERE cthd.ma_sp = sp.ma_sp AND hd.ngay_lap BETWEEN ? AND ?) as xuattrongky, " +
                     "ctsp.so_luong_ton as toncuoiky " +
                     "FROM san_pham sp " +
                     "JOIN chi_tiet_san_pham ctsp ON sp.ma_sp = ctsp.ma_sp " +
                     "GROUP BY sp.ma_sp, ctsp.ma_chi_tiet, sp.TenSP, ctsp.ram, ctsp.chip, ctsp.card, ctsp.so_luong_ton";
        
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            java.sql.Date sqlFrom = new java.sql.Date(from.getTime());
            java.sql.Date sqlTo = new java.sql.Date(to.getTime());
            
            pst.setDate(1, sqlFrom);
            pst.setDate(2, sqlTo);
            pst.setDate(3, sqlFrom);
            pst.setDate(4, sqlTo);
            
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    result.add(new ThongKeTonKhoDTO(
                        rs.getString("ma_sp"), // Changed to String
                        rs.getString("ma_chi_tiet"),
                        rs.getString("tensanpham"),
                        rs.getString("ram"),
                        rs.getString("chip"),
                        rs.getString("card"),
                        rs.getInt("nhaptrongky"),
                        rs.getInt("xuattrongky"),
                        rs.getInt("toncuoiky")
                    ));
                }
            }
        } catch (SQLException ex) {
            CustomLogger.CustomLogger(ThongKeDAO.class.getName(), ex.getMessage(), Level.SEVERE);
        }
        return result;
    }
    
    // Lấy danh sách năm có dữ liệu
   public ArrayList<Integer> getDanhSachNam() {
        ArrayList<Integer> result = new ArrayList<>();
        String sql = "SELECT DISTINCT YEAR(ngay_lap) as nam FROM hoa_don WHERE ngay_lap IS NOT NULL " +
                     "UNION " +
                     "SELECT DISTINCT YEAR(NgayNhap) as nam FROM phieu_nhap WHERE NgayNhap IS NOT NULL " +
                     "ORDER BY nam DESC";
        
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {
            
            while (rs.next()) {
                int year = rs.getInt("nam");
                if (!result.contains(year)) {
                    result.add(year);
                }
            }
            
        } catch (SQLException ex) {
            CustomLogger.CustomLogger(ThongKeDAO.class.getName(), 
                "Lỗi khi lấy danh sách năm. SQL: " + sql + " | Lỗi: " + ex.getMessage(), 
                Level.SEVERE);
        }
        
        if (result.isEmpty()) {
            int currentYear = Calendar.getInstance().get(Calendar.YEAR);
            result.add(currentYear);
            result.add(currentYear - 1);
        }
        
        return result;
    }
  public ArrayList<ThongKeTungNgayTrongThangDTO> getThongKeTungNgayTrongThang(int month, int year) {
    ArrayList<ThongKeTungNgayTrongThangDTO> result = new ArrayList<>();
    String sql = "SELECT DATE(hd.ngay_lap) as ngay, " +
                 "(SELECT COALESCE(SUM(pn.TongTien), 0) FROM phieu_nhap pn WHERE DATE(pn.NgayNhap) = DATE(hd.ngay_lap)) as chiphi, " +
                 "SUM(hd.tong_tien) as doanhthu, " +
                 "SUM(hd.tong_tien) - (SELECT COALESCE(SUM(pn.TongTien), 0) FROM phieu_nhap pn WHERE DATE(pn.NgayNhap) = DATE(hd.ngay_lap))) as loinhuan " +
                 "FROM hoa_don hd " +
                 "WHERE MONTH(hd.ngay_lap) = ? AND YEAR(hd.ngay_lap) = ? " +
                 "GROUP BY DATE(hd.ngay_lap) ORDER BY ngay";
    
    try (Connection conn = dbConnection.getConnection();
         PreparedStatement pst = conn.prepareStatement(sql)) {
        pst.setInt(1, month);
        pst.setInt(2, year);
        try (ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                result.add(new ThongKeTungNgayTrongThangDTO(
                    rs.getDate("ngay"),
                    (int)rs.getLong("chiphi"),
                    (int)rs.getLong("doanhthu"),
                    (int)rs.getLong("loinhuan")
                ));
            }
        }
    } catch (SQLException ex) {
        CustomLogger.CustomLogger(ThongKeDAO.class.getName(), ex.getMessage(), Level.SEVERE);
    }
    return result;
}
}