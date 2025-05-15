/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.ChiTietSanPhamDTO;
import Database.DBConnection;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author mrben
 */
public class ChiTietSanPhamDAO {
    private DBConnection db;

    public ChiTietSanPhamDAO() {
        db = new DBConnection();
    }

    public ArrayList<ChiTietSanPhamDTO> getAllChitietSanpham() {
        ArrayList<ChiTietSanPhamDTO> listTmp = new ArrayList<>();
        String sql = "SELECT * FROM chi_tiet_san_pham ORDER BY ma_chi_tiet ASC";
        try {
            ResultSet rs = db.executeQuery(sql);
            ChiTietSanPhamDTO tmp;
            while (rs.next()) {
                tmp = new ChiTietSanPhamDTO();
                tmp.setMa_chi_tiet(rs.getInt("ma_chi_tiet"));
                tmp.setMa_sp(rs.getString("ma_sp"));
                tmp.setChip(rs.getString("chip"));
                tmp.setCard(rs.getString("card"));
                tmp.setRam(rs.getString("ram"));
                tmp.setGia_ban(rs.getDouble("gia_ban"));
                tmp.setSo_luong_ton(rs.getInt("so_luong_ton"));
                listTmp.add(tmp);
            }
        } catch (Exception ex) {
            System.out.println("Error in file: ChitietSanphamDAO.java");
        }
        return listTmp;
    }

    public boolean Add(ChiTietSanPhamDTO ctsp) {
        try {
            String sql = "INSERT INTO chi_tiet_san_pham(ma_chi_tiet, ma_sp, chip, card, ram, gia_ban, so_luong_ton) VALUES (";
            sql += ctsp.getMa_chi_tiet() + ",";
            sql += "'" + ctsp.getMa_sp() + "',";
            sql += (ctsp.getChip() != null ? "'" + ctsp.getChip() + "'" : "NULL") + ",";
            sql += (ctsp.getCard() != null ? "'" + ctsp.getCard() + "'" : "NULL") + ",";
            sql += (ctsp.getRam() != null ? "'" + ctsp.getRam() + "'" : "NULL") + ",";
            sql += ctsp.getGia_ban() + ",";
            sql += ctsp.getSo_luong_ton() + ")";
            db.executeUpdate(sql);
            return true;
        } catch (Exception e) {
            System.out.println("Error in file: ChitietSanphamDAO.java");
            return false;
        }
    }

      public boolean Set(ChiTietSanPhamDTO ctsp) {
        try {
            String sql = "UPDATE chi_tiet_san_pham SET ";
            sql += "chip = " + (ctsp.getChip() != null ? "'" + ctsp.getChip() + "'" : "NULL") + ",";
            sql += "card = " + (ctsp.getCard() != null ? "'" + ctsp.getCard() + "'" : "NULL") + ",";
            sql += "ram = " + (ctsp.getRam() != null ? "'" + ctsp.getRam() + "'" : "NULL") + ",";
            sql += "gia_ban = " + ctsp.getGia_ban() + ",";
            sql += "so_luong_ton = " + ctsp.getSo_luong_ton();
            sql += " WHERE ma_sp = '" + ctsp.getMa_sp() + "'";
            db.executeUpdate(sql);
            return true;
        } catch (Exception e) {
            System.out.println("Error in file: ChitietSanphamDAO.java");
            return false;
        }
    }

    public boolean Delete(int ma_chi_tiet) {
        try {
            String sql = "DELETE FROM chi_tiet_san_pham WHERE ma_chi_tiet = " + ma_chi_tiet;
            db.executeUpdate(sql);
            return true;
        } catch (Exception e) {
            System.out.println("Error in file: ChitietSanphamDAO.java");
            return false;
        }
    }
}
