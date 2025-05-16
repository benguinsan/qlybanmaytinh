/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.PhieunhapDTO;
import Database.DBConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author mrben
 */
public class PhieunhapDAO {
    private DBConnection db;

    public PhieunhapDAO() {
        db = new DBConnection();
    }

    public ArrayList<PhieunhapDTO> getAllPhieuNhap() {
        ArrayList<PhieunhapDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM phieu_nhap";

        try {
            ResultSet rs = db.executeQuery(sql);

            while (rs.next()) {
                PhieunhapDTO pn = new PhieunhapDTO();
                pn.setMa_pn(rs.getString("ma_pn"));
                pn.setMa_nhan_vien(rs.getString("ma_nhan_vien"));
                pn.setMa_ncc(rs.getString("ma_ncc"));
                pn.setNgayNhap(rs.getDate("NgayNhap"));
                pn.setTongTien(rs.getDouble("TongTien"));

                list.add(pn);
            }
        } catch (SQLException e) {
            System.out.println("Error in file: PhieunhapDAO.java");
            e.printStackTrace();
        }

        return list;
    }

    public PhieunhapDTO getPhieuNhapById(String ma_pn) {
        PhieunhapDTO pn = null;
        String sql = "SELECT * FROM phieu_nhap WHERE ma_pn = '" + ma_pn + "'";

        try {
            ResultSet rs = db.executeQuery(sql);

            if (rs.next()) {
                pn = new PhieunhapDTO();
                pn.setMa_pn(rs.getString("ma_pn"));
                pn.setMa_nhan_vien(rs.getString("ma_nhan_vien"));
                pn.setMa_ncc(rs.getString("ma_ncc"));
                pn.setNgayNhap(rs.getDate("NgayNhap"));
                pn.setTongTien(rs.getDouble("TongTien"));
            }
        } catch (SQLException e) {
            System.out.println("Error in file: PhieunhapDAO.java");
            e.printStackTrace();
        }

        return pn;
    }

    public boolean add(PhieunhapDTO pn) {
        try {
            String sql = "INSERT INTO phieu_nhap(ma_pn, ma_nhan_vien, ma_ncc, NgayNhap, TongTien) VALUES (";
            sql += "'" + pn.getMa_pn() + "',";
            sql += "'" + pn.getMa_nhan_vien() + "',";
            sql += "'" + pn.getMa_ncc() + "',";
            sql += "'" + new java.sql.Date(pn.getNgayNhap().getTime()) + "',";
            sql += pn.getTongTien() + ")";

            db.executeUpdate(sql);
            return true;
        } catch (Exception e) {
            System.out.println("Error in file: PhieunhapDAO.java");
            e.printStackTrace();
            return false;
        }
    }

    public boolean update(PhieunhapDTO pn) {
        try {
            String sql = "UPDATE phieu_nhap SET ";
            sql += "ma_nhan_vien = '" + pn.getMa_nhan_vien() + "',";
            sql += "ma_ncc = '" + pn.getMa_ncc() + "',";
            sql += "NgayNhap = '" + new java.sql.Date(pn.getNgayNhap().getTime()) + "',";
            sql += "TongTien = " + pn.getTongTien();
            sql += " WHERE ma_pn = '" + pn.getMa_pn() + "'";

            db.executeUpdate(sql);
            return true;
        } catch (Exception e) {
            System.out.println("Error in file: PhieunhapDAO.java");
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(String ma_pn) {
        try {
            String sql = "DELETE FROM phieu_nhap WHERE ma_pn = '" + ma_pn + "'";
            db.executeUpdate(sql);
            return true;
        } catch (Exception e) {
            System.out.println("Error in file: PhieunhapDAO.java");
            e.printStackTrace();
            return false;
        }
    }

    // Generate a new receipt ID
    public String generateNewMaPN() {
        String prefix = "PN";
        String newId = prefix + "001";

        String sql = "SELECT ma_pn FROM phieu_nhap ORDER BY ma_pn DESC LIMIT 1";

        try {
            ResultSet rs = db.executeQuery(sql);

            if (rs.next()) {
                String lastId = rs.getString("ma_pn");
                if (lastId != null && lastId.startsWith(prefix)) {
                    String numStr = lastId.substring(prefix.length());
                    try {
                        int num = Integer.parseInt(numStr);
                        num++;
                        newId = prefix + String.format("%03d", num);
                    } catch (NumberFormatException e) {
                        // If parsing fails, use the default
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Error in file: PhieunhapDAO.java");
            e.printStackTrace();
        }

        return newId;
    }
}
