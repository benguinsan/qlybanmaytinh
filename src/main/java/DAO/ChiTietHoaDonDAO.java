/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.ChiTietHoaDonDTO;
import Database.DBConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author mrben
 */
public class ChiTietHoaDonDAO {
    private DBConnection db;

    public ChiTietHoaDonDAO() {
        db = new DBConnection();
    }

    public ArrayList<ChiTietHoaDonDTO> getAllChiTietHoaDon() {
        ArrayList<ChiTietHoaDonDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM chi_tiet_hoa_don";

        try {
            ResultSet rs = db.executeQuery(sql);

            while (rs.next()) {
                ChiTietHoaDonDTO cthd = new ChiTietHoaDonDTO();
                cthd.setMa_ct_hd(rs.getInt("ma_ct_hd"));
                cthd.setMa_hd(rs.getString("ma_hd"));
                cthd.setMa_sp(rs.getString("ma_sp"));
                cthd.setSo_luong(rs.getInt("so_luong"));
                cthd.setDon_gia(rs.getFloat("don_gia"));
                cthd.setThanh_tien(rs.getFloat("thanh_tien"));

                list.add(cthd);
            }
        } catch (SQLException e) {
            System.out.println("Error in file: ChiTietHoaDonDAO.java");
            e.printStackTrace();
        }

        return list;
    }

    public ArrayList<ChiTietHoaDonDTO> getChiTietHoaDonByMaHD(int ma_hd) {
        ArrayList<ChiTietHoaDonDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM chi_tiet_hoa_don WHERE ma_hd = " + ma_hd;

        try {
            ResultSet rs = db.executeQuery(sql);

            while (rs.next()) {
                ChiTietHoaDonDTO cthd = new ChiTietHoaDonDTO();
                cthd.setMa_ct_hd(rs.getInt("ma_ct_hd"));
                cthd.setMa_hd(rs.getString("ma_hd"));
                cthd.setMa_sp(rs.getString("ma_sp"));
                cthd.setSo_luong(rs.getInt("so_luong"));
                cthd.setDon_gia(rs.getFloat("don_gia"));
                cthd.setThanh_tien(rs.getFloat("thanh_tien"));

                list.add(cthd);
            }
        } catch (SQLException e) {
            System.out.println("Error in file: ChiTietHoaDonDAO.java");
            e.printStackTrace();
        }

        return list;
    }

    public ChiTietHoaDonDTO getChiTietHoaDonById(int ma_ct_hd) {
        ChiTietHoaDonDTO cthd = null;
        String sql = "SELECT * FROM chi_tiet_hoa_don WHERE ma_ct_hd = " + ma_ct_hd;

        try {
            ResultSet rs = db.executeQuery(sql);

            if (rs.next()) {
                cthd = new ChiTietHoaDonDTO();
                cthd.setMa_ct_hd(rs.getInt("ma_ct_hd"));
                cthd.setMa_hd(rs.getString("ma_hd"));
                cthd.setMa_sp(rs.getString("ma_sp"));
                cthd.setSo_luong(rs.getInt("so_luong"));
                cthd.setDon_gia(rs.getFloat("don_gia"));
                cthd.setThanh_tien(rs.getFloat("thanh_tien"));
            }
        } catch (SQLException e) {
            System.out.println("Error in file: ChiTietHoaDonDAO.java");
            e.printStackTrace();
        }

        return cthd;
    }

    public boolean add(ChiTietHoaDonDTO cthd) {
        try {
            String sql = "INSERT INTO chi_tiet_hoa_don(ma_hd, ma_sp, so_luong, don_gia) VALUES (";
            sql += "'" + cthd.getMa_hd() + "',";
            sql += "'" + cthd.getMa_sp() + "',";
            sql += cthd.getSo_luong() + ",";
            sql += cthd.getDon_gia() + ")";

            db.executeUpdate(sql);

            // Lấy ID được tạo tự động
            String getIdSql = "SELECT LAST_INSERT_ID() as id";
            ResultSet rs = db.executeQuery(getIdSql);
            if (rs.next()) {
                cthd.setMa_ct_hd(rs.getInt("id"));
            }

            // Cập nhật số lượng tồn kho (giảm đi)
            updateProductInventory(cthd.getMa_sp(), -cthd.getSo_luong());

            return true;
        } catch (Exception e) {
            System.out.println("Error in file: ChiTietHoaDonDAO.java");
            e.printStackTrace();
            return false;
        }
    }

    public boolean update(ChiTietHoaDonDTO cthd) {
        // Lấy số lượng cũ để điều chỉnh tồn kho
        int oldQuantity = 0;
        try {
            String selectSql = "SELECT so_luong FROM chi_tiet_hoa_don WHERE ma_ct_hd = " + cthd.getMa_ct_hd();
            ResultSet rs = db.executeQuery(selectSql);

            if (rs.next()) {
                oldQuantity = rs.getInt("so_luong");
            }
        } catch (Exception e) {
            System.out.println("Error in file: ChiTietHoaDonDAO.java");
            e.printStackTrace();
            return false;
        }

        // Cập nhật bản ghi
        try {
            String sql = "UPDATE chi_tiet_hoa_don SET ";
            sql += "ma_hd = '" + cthd.getMa_hd() + "', ";
            sql += "ma_sp = '" + cthd.getMa_sp() + "', ";
            sql += "so_luong = " + cthd.getSo_luong() + ", ";
            sql += "don_gia = " + cthd.getDon_gia() + " ";
            sql += "WHERE ma_ct_hd = " + cthd.getMa_ct_hd();

            db.executeUpdate(sql);

            // Cập nhật số lượng tồn kho (điều chỉnh theo sự chênh lệch)
            int quantityDifference = cthd.getSo_luong() - oldQuantity;
            if (quantityDifference != 0) {
                updateProductInventory(cthd.getMa_sp(), -quantityDifference);
            }

            return true;
        } catch (Exception e) {
            System.out.println("Error in file: ChiTietHoaDonDAO.java");
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(int ma_ct_hd) {
        // Lấy thông tin chi tiết để điều chỉnh tồn kho
        ChiTietHoaDonDTO cthd = null;
        try {
            String selectSql = "SELECT * FROM chi_tiet_hoa_don WHERE ma_ct_hd = " + ma_ct_hd;
            ResultSet rs = db.executeQuery(selectSql);

            if (rs.next()) {
                cthd = new ChiTietHoaDonDTO();
                cthd.setMa_ct_hd(rs.getInt("ma_ct_hd"));
                cthd.setMa_hd(rs.getString("ma_hd"));
                cthd.setMa_sp(rs.getString("ma_sp"));
                cthd.setSo_luong(rs.getInt("so_luong"));
                cthd.setDon_gia(rs.getFloat("don_gia"));
                cthd.setThanh_tien(rs.getFloat("thanh_tien"));
            }
        } catch (Exception e) {
            System.out.println("Error in file: ChiTietHoaDonDAO.java");
            e.printStackTrace();
            return false;
        }

        if (cthd == null) {
            return false;
        }

        // Xóa bản ghi
        try {
            String sql = "DELETE FROM chi_tiet_hoa_don WHERE ma_ct_hd = " + ma_ct_hd;
            db.executeUpdate(sql);

            // Cập nhật số lượng tồn kho (tăng lại)
            updateProductInventory(cthd.getMa_sp(), cthd.getSo_luong());

            return true;
        } catch (Exception e) {
            System.out.println("Error in file: ChiTietHoaDonDAO.java");
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteByMaHD(int ma_hd) {
        // Lấy tất cả chi tiết để điều chỉnh tồn kho
        ArrayList<ChiTietHoaDonDTO> listCTHD = getChiTietHoaDonByMaHD(ma_hd);

        // Xóa các bản ghi
        try {
            String sql = "DELETE FROM chi_tiet_hoa_don WHERE ma_hd = " + ma_hd;
            db.executeUpdate(sql);

            // Cập nhật số lượng tồn kho cho từng sản phẩm
            for (ChiTietHoaDonDTO cthd : listCTHD) {
                updateProductInventory(cthd.getMa_sp(), cthd.getSo_luong());
            }

            return true;
        } catch (Exception e) {
            System.out.println("Error in file: ChiTietHoaDonDAO.java");
            e.printStackTrace();
            return false;
        }
    }

    // Phương thức hỗ trợ để cập nhật số lượng tồn kho
    private void updateProductInventory(String ma_sp, int quantityChange) {
        if (quantityChange == 0) {
            return;
        }

        try {
            String sql = "UPDATE san_pham SET so_luong_ton = so_luong_ton + " + quantityChange +
                    " WHERE ma_sp = '" + ma_sp + "'";
            db.executeUpdate(sql);
        } catch (Exception e) {
            System.out.println("Error in file: ChiTietHoaDonDAO.java");
            e.printStackTrace();
        }
    }

    public int getNextMaCTHD() {
        int nextId = 1;
        String sql = "SELECT MAX(ma_ct_hd) AS max_id FROM chi_tiet_hoa_don";

        try {
            ResultSet rs = db.executeQuery(sql);
            if (rs.next()) {
                nextId = rs.getInt("max_id") + 1;
            }
        } catch (SQLException e) {
            System.out.println("Error in file: ChiTietHoaDonDAO.java");
            e.printStackTrace();
        }

        return nextId;
    }
}
