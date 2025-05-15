/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.SanphamDTO;
import Database.DBConnection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Locale;
import java.sql.Date;
import java.sql.Timestamp;

/**
 *
 * @author mrben
 */
public class SanphamDAO {
    private DBConnection db;

    public SanphamDAO() {
        db = new DBConnection();
    }

    public ArrayList<SanphamDTO> getAllSanpham() {
        ArrayList<SanphamDTO> listTmp = new ArrayList<>();
        String sql = "SELECT * FROM san_pham ORDER BY created_at ASC";
        try {
            ResultSet rs = db.executeQuery(sql);
            SanphamDTO tmp;
            while (rs.next()) {
                tmp = new SanphamDTO();
                tmp.setMa_sp(rs.getString("ma_sp"));
                tmp.setTenSP(rs.getString("TenSP"));
                tmp.setDonGia(rs.getDouble("DonGia"));
                tmp.setMa_loai(rs.getString("ma_loai"));
                tmp.setCreated_at(rs.getTimestamp("created_at"));
                listTmp.add(tmp);
                
            }
        } catch (Exception ex) {
            System.out.println("Error in file: SanphamDAO.java");
            ex.printStackTrace();
        }
        return listTmp;
    }

    public boolean Add(SanphamDTO sp) {
        try {
            Timestamp createdAt = sp.getCreated_at() != null
                ? new Timestamp(sp.getCreated_at().getTime())
                : new Timestamp(System.currentTimeMillis());

            String sql = "INSERT INTO san_pham(ma_sp, TenSP, DonGia, ma_loai, created_at) VALUES (";
            sql += "'" + sp.getMa_sp() + "',";
            sql += "'" + sp.getTenSP() + "',";
            sql += String.format(Locale.US, "%.2f", sp.getDonGia()) + ",";
            sql += (sp.getMa_loai() != null ? "'" + sp.getMa_loai() + "'" : "NULL") + ",";
            sql += "'" + createdAt + "')";
            db.executeUpdate(sql);
            return true;
        } catch (Exception e) {
            System.out.println("Error in file: SanphamDAO.java");
            e.printStackTrace();
            return false;
        }
    }


    public boolean Set(SanphamDTO sp) {
        try {
            String sql = "UPDATE san_pham SET ";
            sql += "TenSP = '" + sp.getTenSP() + "',";
            sql += "DonGia = " + sp.getDonGia() + ",";
            sql += "ma_loai = " + (sp.getMa_loai() != null ? "'" + sp.getMa_loai() + "'" : "NULL") + ",";
            sql += "created_at = '" + new Timestamp(sp.getCreated_at().getTime()) + "'";
            sql += " WHERE ma_sp = '" + sp.getMa_sp() + "'";
            db.executeUpdate(sql);
            return true;
        } catch (Exception e) {
            System.out.println("Error in file: SanphamDAO.java");
            e.printStackTrace();
            return false;
        }
    }

    public boolean Delete(String ma_sp) {
        try {
            String sql = "DELETE FROM san_pham WHERE ma_sp = '" + ma_sp + "'";
            db.executeUpdate(sql);
            return true;
        } catch (Exception e) {
            System.out.println("Error in file: SanphamDAO.java");
            e.printStackTrace();
            return false;
        }
    }
}
