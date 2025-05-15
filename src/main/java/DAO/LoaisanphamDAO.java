/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.LoaisanphamDTO;
import Database.DBConnection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author mrben
 */
public class LoaisanphamDAO {
    private DBConnection db;

    public LoaisanphamDAO() {
        db = new DBConnection();
    }

    public ArrayList<LoaisanphamDTO> getAllLoaisanpham() {
        ArrayList<LoaisanphamDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM phan_loai ORDER BY created_at ASC";
        try {
            ResultSet rs = db.executeQuery(sql);
            LoaisanphamDTO loai;
            while (rs.next()) {
                loai = new LoaisanphamDTO();
                loai.setMa_loai(rs.getString("ma_loai"));
                loai.setTen_loai(rs.getString("ten_loai"));
                loai.setMo_ta(rs.getString("mo_ta"));
                loai.setCreated_at(rs.getTimestamp("created_at"));
                list.add(loai);
            }
        } catch (Exception e) {
            System.out.println("Error in file: LoaisanphamDAO.java");
        }
        return list;
    }

    public boolean Add(LoaisanphamDTO loai) {
        try {
            String sql = "INSERT INTO phan_loai(ma_loai, ten_loai, mo_ta, created_at) VALUES (";
            sql += "'" + loai.getMa_loai() + "',";
            sql += "'" + loai.getTen_loai() + "',";
            sql += "'" + loai.getMo_ta() + "',";
            sql += "'" + new java.sql.Timestamp(loai.getCreated_at().getTime()) + "')";
            db.executeUpdate(sql);
            return true;
        } catch (Exception e) {
            System.out.println("Error in file: LoaisanphamDAO.java");
            return false;
        }
    }

    public boolean Set(LoaisanphamDTO loai) {
        try {
            String sql = "UPDATE phan_loai SET ";
            sql += "ten_loai = '" + loai.getTen_loai() + "',";
            sql += "mo_ta = '" + loai.getMo_ta() + "',";
            sql += "created_at = '" + new java.sql.Timestamp(loai.getCreated_at().getTime()) + "'";
            sql += " WHERE ma_loai = '" + loai.getMa_loai() + "'";
            db.executeUpdate(sql);
            return true;
        } catch (Exception e) {
            System.out.println("Error in file: LoaisanphamDAO.java");
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
