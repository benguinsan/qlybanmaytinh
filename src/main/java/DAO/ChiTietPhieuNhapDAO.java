/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.ChiTietPhieuNhapDTO;
import Database.DBConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author mrben
 */
public class ChiTietPhieuNhapDAO {
    private DBConnection db;

    public ChiTietPhieuNhapDAO() {
        db = new DBConnection();
    }

    public ArrayList<ChiTietPhieuNhapDTO> getAllChiTietPhieuNhap() {
        ArrayList<ChiTietPhieuNhapDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM chi_tiet_phieu_nhap";

        try {
            ResultSet rs = db.executeQuery(sql);

            while (rs.next()) {
                ChiTietPhieuNhapDTO ctpn = new ChiTietPhieuNhapDTO();
                ctpn.setMa_ctpn(rs.getInt("ma_ctpn"));
                ctpn.setMa_pn(rs.getString("ma_pn"));
                ctpn.setMa_sp(rs.getString("ma_sp"));
                ctpn.setSoLuong(rs.getInt("SoLuong"));
                ctpn.setDonGia(rs.getDouble("DonGia"));

                list.add(ctpn);
            }
        } catch (SQLException e) {
            System.out.println("Error in file: ChiTietPhieuNhapDAO.java");
            e.printStackTrace();
        }

        return list;
    }

    public ArrayList<ChiTietPhieuNhapDTO> getChiTietPhieuNhapByMaPN(String ma_pn) {
        ArrayList<ChiTietPhieuNhapDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM chi_tiet_phieu_nhap WHERE ma_pn = '" + ma_pn + "'";

        try {
            ResultSet rs = db.executeQuery(sql);

            while (rs.next()) {
                ChiTietPhieuNhapDTO ctpn = new ChiTietPhieuNhapDTO();
                ctpn.setMa_ctpn(rs.getInt("ma_ctpn"));
                ctpn.setMa_pn(rs.getString("ma_pn"));
                ctpn.setMa_sp(rs.getString("ma_sp"));
                ctpn.setSoLuong(rs.getInt("SoLuong"));
                ctpn.setDonGia(rs.getDouble("DonGia"));

                list.add(ctpn);
            }
        } catch (Exception e) {
            System.out.println("Error in file: ChiTietPhieuNhapDAO.java");
            e.printStackTrace();
        }

        return list;
    }

    public boolean add(ChiTietPhieuNhapDTO ctpn) {
        try {
            String sql = "INSERT INTO chi_tiet_phieu_nhap (ma_pn, ma_sp, SoLuong, DonGia) VALUES (";
            sql += "'" + ctpn.getMa_pn() + "',";
            sql += "'" + ctpn.getMa_sp() + "',";
            sql += ctpn.getSoLuong() + ",";
            sql += ctpn.getDonGia() + ")";

            db.executeUpdate(sql);

            // Get the generated ID
            String getIdSql = "SELECT LAST_INSERT_ID() as id";
            ResultSet rs = db.executeQuery(getIdSql);
            if (rs.next()) {
                ctpn.setMa_ctpn(rs.getInt("id"));
            }

            // Update product inventory
            updateProductInventory(ctpn.getMa_sp(), ctpn.getSoLuong());

            return true;
        } catch (Exception e) {
            System.out.println("Error in file: ChiTietPhieuNhapDAO.java");
            e.printStackTrace();
            return false;
        }
    }

    public boolean update(ChiTietPhieuNhapDTO ctpn) {
        // First, get the old quantity to adjust inventory
        int oldQuantity = 0;
        try {
            String selectSql = "SELECT SoLuong FROM chi_tiet_phieu_nhap WHERE ma_ctpn = " + ctpn.getMa_ctpn();
            ResultSet rs = db.executeQuery(selectSql);

            if (rs.next()) {
                oldQuantity = rs.getInt("SoLuong");
            }
        } catch (Exception e) {
            System.out.println("Error in file: ChiTietPhieuNhapDAO.java");
            e.printStackTrace();
            return false;
        }

        // Now update the record
        try {
            String sql = "UPDATE chi_tiet_phieu_nhap SET ";
            sql += "ma_pn = '" + ctpn.getMa_pn() + "',";
            sql += "ma_sp = '" + ctpn.getMa_sp() + "',";
            sql += "SoLuong = " + ctpn.getSoLuong() + ",";
            sql += "DonGia = " + ctpn.getDonGia();
            sql += " WHERE ma_ctpn = " + ctpn.getMa_ctpn();

            db.executeUpdate(sql);

            // Update product inventory (adjust by the difference)
            int quantityDifference = ctpn.getSoLuong() - oldQuantity;
            if (quantityDifference != 0) {
                updateProductInventory(ctpn.getMa_sp(), quantityDifference);
            }

            return true;
        } catch (Exception e) {
            System.out.println("Error in file: ChiTietPhieuNhapDAO.java");
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(int ma_ctpn) {
        // First, get the details to adjust inventory
        ChiTietPhieuNhapDTO ctpn = null;
        try {
            String selectSql = "SELECT * FROM chi_tiet_phieu_nhap WHERE ma_ctpn = " + ma_ctpn;
            ResultSet rs = db.executeQuery(selectSql);

            if (rs.next()) {
                ctpn = new ChiTietPhieuNhapDTO();
                ctpn.setMa_ctpn(rs.getInt("ma_ctpn"));
                ctpn.setMa_pn(rs.getString("ma_pn"));
                ctpn.setMa_sp(rs.getString("ma_sp"));
                ctpn.setSoLuong(rs.getInt("SoLuong"));
                ctpn.setDonGia(rs.getDouble("DonGia"));
            }
        } catch (Exception e) {
            System.out.println("Error in file: ChiTietPhieuNhapDAO.java");
            e.printStackTrace();
            return false;
        }

        if (ctpn == null) {
            return false;
        }

        // Now delete the record
        try {
            String sql = "DELETE FROM chi_tiet_phieu_nhap WHERE ma_ctpn = " + ma_ctpn;
            db.executeUpdate(sql);

            // Update product inventory (subtract the quantity)
            updateProductInventory(ctpn.getMa_sp(), -ctpn.getSoLuong());

            return true;
        } catch (Exception e) {
            System.out.println("Error in file: ChiTietPhieuNhapDAO.java");
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteByMaPN(String ma_pn) {
        // First, get all details to adjust inventory
        ArrayList<ChiTietPhieuNhapDTO> listCTPN = getChiTietPhieuNhapByMaPN(ma_pn);

        // Now delete the records
        try {
            String sql = "DELETE FROM chi_tiet_phieu_nhap WHERE ma_pn = '" + ma_pn + "'";
            db.executeUpdate(sql);

            // Update product inventory for each item
            for (ChiTietPhieuNhapDTO ctpn : listCTPN) {
                updateProductInventory(ctpn.getMa_sp(), -ctpn.getSoLuong());
            }

            return true;
        } catch (Exception e) {
            System.out.println("Error in file: ChiTietPhieuNhapDAO.java");
            e.printStackTrace();
            return false;
        }
    }

    // Helper method to update product inventory
    private void updateProductInventory(String ma_sp, int quantityChange) {
        if (quantityChange == 0) {
            return;
        }

        try {
            String sql = "UPDATE san_pham SET so_luong_ton = so_luong_ton + " + quantityChange + " WHERE ma_sp = '"
                    + ma_sp + "'";
            db.executeUpdate(sql);
        } catch (Exception e) {
            System.out.println("Error in file: ChiTietPhieuNhapDAO.java");
            e.printStackTrace();
        }
    }
}
