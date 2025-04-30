/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.KhachhangDTO;
import Database.DBConnection;
import java.sql.ResultSet;
import java.util.ArrayList;


/**
 *
 * @author mrben
 */
public class KhachhangDAO {
    private DBConnection db;

    public KhachhangDAO() {
        db = new DBConnection();
    }

    public ArrayList<KhachhangDTO> getAllKhachhang() {
        ArrayList<KhachhangDTO> listTmp = new ArrayList<KhachhangDTO>();
        String sql = "SELECT * FROM khach_hang";
        try {
            ResultSet rs = db.executeQuery(sql);
            KhachhangDTO tmp;
            while (rs.next()) {
                tmp = new KhachhangDTO();
                tmp.setMa_khach_hang(rs.getString("ma_khach_hang"));
                tmp.setHo_ten(rs.getString("ho_ten"));
                tmp.setDien_thoai(rs.getString("dien_thoai"));
                tmp.setDia_chi(rs.getString("dia_chi"));
                tmp.setCreated_at(rs.getDate("created_at"));
                listTmp.add(tmp);
            }
        } catch (Exception ex) {
            System.out.println("Error in file: KhachHangDAO.java");
        }
        return listTmp;
    }

    public boolean Add(KhachhangDTO kh) {
        try {
            String sql = "INSERT INTO khach_hang(ma_khach_hang, ho_ten, dien_thoai, dia_chi, created_at) VALUES (";
            sql += "'" + kh.getMa_khach_hang() + "',";
            sql += "'" + kh.getHo_ten() + "',";
            sql += "'" + kh.getDien_thoai() + "',";
            sql += "'" + kh.getDia_chi() + "',";
            sql += "'" + new java.sql.Date(kh.getCreated_at().getTime()) + "')";
            db.executeUpdate(sql);
            return true;
        } catch (Exception e) {
            System.out.println("Error in file: KhachHangDAO.java");
            return false;
        }
    }

    public boolean Set(KhachhangDTO kh) {
        try {
            String sql = "UPDATE khach_hang SET ";
            sql += "ho_ten = '" + kh.getHo_ten() + "',";
            sql += "dien_thoai = '" + kh.getDien_thoai() + "',";
            sql += "dia_chi = '" + kh.getDia_chi() + "',";
            sql += "created_at = '" + new java.sql.Date(kh.getCreated_at().getTime()) + "'";
            sql += " WHERE ma_khach_hang = '" + kh.getMa_khach_hang() + "'";
            db.executeUpdate(sql);
            return true;
        } catch (Exception e) {
            System.out.println("Error in file: KhachHangDAO.java");
            return false;
        }
    }

    public boolean Delete(String ma_khach_hang) {
        try {
            String sql = "DELETE FROM khach_hang WHERE ma_khach_hang = '" + ma_khach_hang + "'";
            db.executeUpdate(sql);
            return true;
        } catch (Exception e) {
            System.out.println("Error in file: KhachHangDAO.java");
            return false;
        }
    }
}
