/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Database.DBConnection;
import DTO.NhacungcapDTO;
import java.util.ArrayList;
import java.sql.ResultSet;

/**
 *
 * @author mrben
 */
public class NhacungcapDAO {
    private DBConnection db;

    public NhacungcapDAO() {
        db = new DBConnection();
    }

    public ArrayList<NhacungcapDTO> getAllNhacungcap() {
        ArrayList<NhacungcapDTO> listTmp = new ArrayList<NhacungcapDTO>();
        String sql = "SELECT * FROM nha_cung_cap ORDER BY created_at ASC";
        try {
            ResultSet rs = db.executeQuery(sql);
            while (rs.next()) {
                NhacungcapDTO ncc = new NhacungcapDTO();
                ncc.setMa_ncc(rs.getString("ma_ncc"));
                ncc.setTen_ncc(rs.getString("ten_ncc"));
                ncc.setDia_chi(rs.getString("dia_chi"));
                ncc.setDien_thoai(rs.getString("dien_thoai"));
                ncc.setEmail(rs.getString("email"));
                ncc.setCreated_at(rs.getDate("created_at"));
                listTmp.add(ncc);
            }
        } catch (Exception e) {
            System.out.println("Error in file: NhacungcapDAO.java: " + e.getMessage());
        }
        return listTmp;
    }

    public boolean Add(NhacungcapDTO ncc) {
        try {
            String sql = "INSERT INTO nha_cung_cap(ma_ncc, ten_ncc, dia_chi, dien_thoai, email, created_at) VALUES (";
            sql += "'" + ncc.getMa_ncc() + "',";
            sql += "'" + ncc.getTen_ncc() + "',";
            sql += "'" + ncc.getDia_chi() + "',";
            sql += "'" + ncc.getDien_thoai() + "',";
            sql += "'" + ncc.getEmail() + "',";
            sql += "'" + new java.sql.Date(ncc.getCreated_at().getTime()) + "')";

            db.executeUpdate(sql);
            return true;
        } catch (Exception e) {
            System.out.println("Error in file: NhacungcapDAO.java: " + e.getMessage());
            return false;
        }
    }

    public boolean Set(NhacungcapDTO ncc) {
        try {
            String sql = "UPDATE nha_cung_cap SET ";
            sql += "ten_ncc = '" + ncc.getTen_ncc() + "',";
            sql += "dia_chi = '" + ncc.getDia_chi() + "',";
            sql += "dien_thoai = '" + ncc.getDien_thoai() + "',";
            sql += "email = '" + ncc.getEmail() + "',";
            sql += "created_at = '" + new java.sql.Date(ncc.getCreated_at().getTime()) + "'";
            sql += " WHERE ma_ncc = '" + ncc.getMa_ncc() + "'";

            db.executeUpdate(sql);
            return true;
        } catch (Exception e) {
            System.out.println("Error in file: LoaisanphamDAO.java: " + e.getMessage());
            return false;
        }
    }

    public boolean Delete(String ma_ncc) {
        try {
            String sql = "DELETE FROM nha_cung_cap WHERE ma_ncc = '" + ma_ncc + "'";
            db.executeUpdate(sql);
            return true;
        } catch (Exception e) {
            System.out.println("Error in file: NhacungcapDAO.java: " + e.getMessage());
            return false;
        }
    }

    public NhacungcapDTO getNhacungcapByMa(String ma_ncc) {
        String sql = "SELECT * FROM nha_cung_cap WHERE ma_ncc = '" + ma_ncc + "'";
        try {
            ResultSet rs = db.executeQuery(sql);
            if (rs.next()) {
                NhacungcapDTO ncc = new NhacungcapDTO();
                ncc.setMa_ncc(rs.getString("ma_ncc"));
                ncc.setTen_ncc(rs.getString("ten_ncc"));
                ncc.setDia_chi(rs.getString("dia_chi"));
                ncc.setDien_thoai(rs.getString("dien_thoai"));
                ncc.setEmail(rs.getString("email"));
                ncc.setCreated_at(rs.getDate("created_at"));
                return ncc;
            }
        } catch (Exception e) {
            System.out.println("Error in file: NhacungcapDAO.java: " + e.getMessage());
        }
        return null;
    }

    
}
