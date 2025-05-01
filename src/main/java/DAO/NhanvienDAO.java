/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.NhanvienDTO;
import Database.DBConnection;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author mrben
 */
public class NhanvienDAO {
    private DBConnection db;

    public NhanvienDAO() {
        db = new DBConnection();
    }

    public ArrayList<NhanvienDTO> getAllNhanvien() {
        ArrayList<NhanvienDTO> listTmp = new ArrayList<NhanvienDTO>();
        String sql = "SELECT * FROM nhan_vien WHERE trang_thai = 1 ORDER BY created_at ASC";
        try {
            ResultSet rs = db.executeQuery(sql);
            NhanvienDTO tmp;
            while (rs.next()) {
                tmp = new NhanvienDTO();
                tmp.setMa_nhan_vien(rs.getString("ma_nhan_vien"));
                tmp.setHo_ten(rs.getString("ho_ten"));
                tmp.setGioi_tinh(rs.getString("gioi_tinh"));
                tmp.setNgay_sinh(rs.getDate("ngay_sinh"));
                tmp.setDia_chi(rs.getString("dia_chi"));
                tmp.setDien_thoai(rs.getString("dien_thoai"));
                tmp.setEmail(rs.getString("email"));
                tmp.setCreated_at(rs.getDate("created_at"));
                tmp.setTrang_thai(rs.getInt("trang_thai"));
                listTmp.add(tmp);
            }
        } catch (Exception ex) {
            System.out.println("Error in file: NhanvienDAO.java");
        }
        return listTmp;
    }

    public boolean Add(NhanvienDTO nv) {
        try {
            String sql = "INSERT INTO nhan_vien(ma_nhan_vien, ho_ten, gioi_tinh, ngay_sinh, dia_chi, dien_thoai, email, created_at, trang_thai) VALUES (";
            sql += "'" + nv.getMa_nhan_vien() + "',";
            sql += "'" + nv.getHo_ten() + "',";
            sql += "'" + nv.getGioi_tinh() + "',";
            sql += "'" + new java.sql.Date(nv.getNgay_sinh().getTime()) + "',";
            sql += "'" + nv.getDia_chi() + "',";
            sql += "'" + nv.getDien_thoai() + "',";
            sql += "'" + nv.getEmail() + "',";
            sql += "'" + new java.sql.Date(nv.getCreated_at().getTime()) + "',";
            sql += "1)";
            db.executeUpdate(sql);
            return true;
        } catch (Exception e) {
            System.out.println("Error in file: NhanvienDAO.java");
            return false;
        }
    }

    public boolean Set(NhanvienDTO nv) {
        try {
            String sql = "UPDATE nhan_vien SET ";
            sql += "ho_ten = '" + nv.getHo_ten() + "',";
            sql += "gioi_tinh = '" + nv.getGioi_tinh() + "',";
            sql += "ngay_sinh = '" + new java.sql.Date(nv.getNgay_sinh().getTime()) + "',";
            sql += "dia_chi = '" + nv.getDia_chi() + "',";
            sql += "dien_thoai = '" + nv.getDien_thoai() + "',";
            sql += "email = '" + nv.getEmail() + "',";
            sql += "created_at = '" + new java.sql.Date(nv.getCreated_at().getTime()) + "',";
            sql += "trang_thai = " + nv.getTrang_thai();
            sql += " WHERE ma_nhan_vien = '" + nv.getMa_nhan_vien() + "'";
            db.executeUpdate(sql);
            return true;
        } catch (Exception e) {
            System.out.println("Error in file: NhanvienDAO.java");
            return false;
        }
    }

    public boolean Delete(String ma_nhan_vien) {
        try {
            String sql = "UPDATE nhan_vien SET trang_thai = 0 WHERE ma_nhan_vien = '" + ma_nhan_vien + "'";
            db.executeUpdate(sql);
            return true;
        } catch (Exception e) {
            System.out.println("Error in file: NhanvienDAO.java");
            return false;
        }
    }
}
