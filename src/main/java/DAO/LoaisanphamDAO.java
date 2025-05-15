/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Database.DBConnection;
import DTO.LoaisanphamDTO;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;

/**
 *
 * @author mrben
 */
public class LoaisanphamDAO {
    private DBConnection db;

    public LoaisanphamDAO() {
        db = new DBConnection();
    }

    public ArrayList<LoaisanphamDTO> getAllLoaiSanPham() {
        ArrayList<LoaisanphamDTO> listTmp = new ArrayList<LoaisanphamDTO>();
        String sql = "SELECT * FROM phan_loai ORDER BY created_at ASC";
        try {
            ResultSet rs = db.executeQuery(sql);
            LoaisanphamDTO tmp;
            while (rs.next()) {
                tmp = new LoaisanphamDTO();
                tmp.setMa_loai(rs.getString("ma_loai"));
                tmp.setTen_loai(rs.getString("ten_loai"));
                tmp.setMo_ta(rs.getString("mo_ta"));
                tmp.setCreated_at(rs.getDate("created_at"));
                listTmp.add(tmp);
            }
        } catch (Exception ex) {
            System.out.println("Error in file: LoaisanphamDAO.java");
        }
        return listTmp;
    }

    public LoaisanphamDTO getLoaisanphamByMa(String maLoai) {
        String sql = "SELECT * FROM phan_loai WHERE ma_loai = '" + maLoai + "'";
        try {
            ResultSet rs = db.executeQuery(sql);
            if (rs.next()) {
                LoaisanphamDTO loai = new LoaisanphamDTO();
                loai.setMa_loai(rs.getString("ma_loai"));
                loai.setTen_loai(rs.getString("ten_loai"));
                loai.setMo_ta(rs.getString("mo_ta"));
                loai.setCreated_at(rs.getDate("created_at"));
                return loai;
            }
        } catch (Exception ex) {
            System.out.println("Error in file: LoaisanphamDAO.java - getLoaisanphamByMa");
        }
        return null;
    }

    public boolean Add(LoaisanphamDTO lp) {
        try {
            String sql = "INSERT INTO phan_loai(ma_loai, ten_loai, mo_ta, created_at) VALUES (";
            sql += "'" + lp.getMa_loai() + "',";
            sql += "'" + lp.getTen_loai() + "',";
            sql += "'" + lp.getMo_ta() + "',";
            sql += "'" + new java.sql.Date(lp.getCreated_at().getTime()) + "')";

            db.executeUpdate(sql);
            return true;
        } catch (Exception e) {
            System.out.println("Error in file: LoaisanphamDAO.java: " + e.getMessage());
            return false;
        }
    }

    public boolean Set(LoaisanphamDTO lp) {
        try {
            String sql = "UPDATE phan_loai SET ";
            sql += "ten_loai = '" + lp.getTen_loai() + "',";
            sql += "mo_ta = '" + lp.getMo_ta() + "',";
            sql += "created_at = '" + new java.sql.Date(lp.getCreated_at().getTime()) + "'";
            sql += " WHERE ma_loai = '" + lp.getMa_loai() + "'";

            db.executeUpdate(sql);
            return true;
        } catch (Exception e) {
            System.out.println("Error in file: LoaisanphamDAO.java: " + e.getMessage());
            return false;
        }
    }

    public boolean Delete(String ma_loai) {
        try {
            String sql = "DELETE FROM phan_loai WHERE ma_loai = '" + ma_loai + "'";
            db.executeUpdate(sql);
            return true;
        } catch (Exception e) {
            System.out.println("Error in file: LoaisanphamDAO.java");
            return false;
        }
    }

}
