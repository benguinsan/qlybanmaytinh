/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.HoaDonDTO;
import Database.DBConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author mrben
 */
public class HoaDonDAO {
    private DBConnection db;

    public HoaDonDAO() {
        db = new DBConnection();
    }

    public ArrayList<HoaDonDTO> getAllHoaDon() {
        ArrayList<HoaDonDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM hoa_don";

        try {
            ResultSet rs = db.executeQuery(sql);

            while (rs.next()) {
                HoaDonDTO hd = new HoaDonDTO();
                hd.setMa_hd(rs.getString("ma_hd"));
                hd.setMa_nhan_vien(rs.getString("ma_nhan_vien"));
                hd.setMa_khach_hang(rs.getString("ma_khach_hang"));
                hd.setNgay_lap(rs.getDate("ngay_lap"));
                hd.setTong_tien(rs.getDouble("tong_tien"));

                list.add(hd);
            }
        } catch (SQLException e) {
            System.out.println("Error in file: HoaDonDAO.java");
            e.printStackTrace();
        }

        return list;
    }

    public HoaDonDTO getHoaDonById(String ma_hd) {
        HoaDonDTO hd = null;
        String sql = "SELECT * FROM hoa_don WHERE ma_hd = '" + ma_hd + "'";

        try {
            ResultSet rs = db.executeQuery(sql);

            if (rs.next()) {
                hd = new HoaDonDTO();
                hd.setMa_hd(rs.getString("ma_hd"));
                hd.setMa_nhan_vien(rs.getString("ma_nhan_vien"));
                hd.setMa_khach_hang(rs.getString("ma_khach_hang"));
                hd.setNgay_lap(rs.getDate("ngay_lap"));
                hd.setTong_tien(rs.getDouble("tong_tien"));
            }
        } catch (SQLException e) {
            System.out.println("Error in file: HoaDonDAO.java");
            e.printStackTrace();
        }

        return hd;
    }

    public boolean add(HoaDonDTO hd) {
        try {
            String sql = "INSERT INTO hoa_don(ma_hd, ma_nhan_vien, ma_khach_hang, ngay_lap, tong_tien) VALUES (";
            sql += "'" + hd.getMa_hd() + "',";
            sql += "'" + hd.getMa_nhan_vien() + "',";
            sql += "'" + hd.getMa_khach_hang() + "',";
            sql += "'" + new java.sql.Date(hd.getNgay_lap().getTime()) + "',";
            sql += hd.getTong_tien() + ")";

            db.executeUpdate(sql);
            return true;
        } catch (Exception e) {
            System.out.println("Error in file: HoaDonDAO.java");
            e.printStackTrace();
            return false;
        }
    }

    public boolean update(HoaDonDTO hd) {
        try {
            String sql = "UPDATE hoa_don SET ";
            sql += "ma_nhan_vien = '" + hd.getMa_nhan_vien() + "',";
            sql += "ma_khach_hang = '" + hd.getMa_khach_hang() + "',";
            sql += "ngay_lap = '" + new java.sql.Date(hd.getNgay_lap().getTime()) + "',";
            sql += "tong_tien = " + hd.getTong_tien();
            sql += " WHERE ma_hd = '" + hd.getMa_hd() + "'";

            db.executeUpdate(sql);
            return true;
        } catch (Exception e) {
            System.out.println("Error in file: HoaDonDAO.java");
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(String ma_hd) {
        try {
            String sql = "DELETE FROM hoa_don WHERE ma_hd = '" + ma_hd + "'";
            db.executeUpdate(sql);
            return true;
        } catch (Exception e) {
            System.out.println("Error in file: HoaDonDAO.java");
            e.printStackTrace();
            return false;
        }
    }

    // Generate a new invoice ID
    public String generateNewMaHD() {
        String prefix = "HD";
        String newId = prefix + "001";

        String sql = "SELECT ma_hd FROM hoa_don ORDER BY ma_hd DESC LIMIT 1";

        try {
            ResultSet rs = db.executeQuery(sql);

            if (rs.next()) {
                String lastId = rs.getString("ma_hd");
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
            System.out.println("Error in file: HoaDonDAO.java");
            e.printStackTrace();
        }

        return newId;
    }

}
