/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Database.DBConnection;
import DTO.SanphamDTO;
import java.sql.ResultSet;
import java.util.ArrayList;

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
        ArrayList<SanphamDTO> listTmp = new ArrayList<SanphamDTO>();
        String sql = "SELECT * FROM san_pham WHERE trang_thai = 1 ORDER BY created_at ASC";
        try {
            ResultSet rs = db.executeQuery(sql);
            SanphamDTO tmp;
            while (rs.next()) {
                tmp = new SanphamDTO();
                tmp.setMa_sp(rs.getString("ma_sp"));
                tmp.setTenSP(rs.getString("TenSP"));
                tmp.setMa_loai(rs.getString("ma_loai"));
                tmp.setChip(rs.getString("chip"));
                tmp.setCard(rs.getString("card"));
                tmp.setRam(rs.getString("ram"));
                tmp.setGia_ban(rs.getDouble("gia_ban"));
                tmp.setSo_luong_ton(rs.getInt("so_luong_ton"));
                tmp.setCreated_at(rs.getDate("created_at"));
                tmp.setTrang_thai(rs.getInt("trang_thai"));
                listTmp.add(tmp);
            }

            // In ra thông tin các sản phẩm trong console
            System.out.println("Danh sách sản phẩm:");
            for (SanphamDTO sp : listTmp) {
                System.out.println("Mã SP: " + sp.getMa_sp() +
                        ", Tên: " + sp.getTenSP() +
                        ", Giá: " + sp.getGia_ban() +
                        ", Số lượng: " + sp.getSo_luong_ton());
            }
            System.out.println("Tổng số sản phẩm: " + listTmp.size());

        } catch (Exception ex) {
            System.out.println("Error in file: SanphamDAO.java");
            ex.printStackTrace(); // In ra chi tiết lỗi nếu có
        }
        return listTmp;
    }

    public SanphamDTO getSanphamById(String ma_sp) {
        String sql = "SELECT * FROM san_pham WHERE ma_sp = '" + ma_sp + "'";
        try {
            ResultSet rs = db.executeQuery(sql);
            if (rs.next()) {
                SanphamDTO sp = new SanphamDTO();
                sp.setMa_sp(rs.getString("ma_sp"));
                sp.setTenSP(rs.getString("TenSP"));
                sp.setMa_loai(rs.getString("ma_loai"));
                sp.setChip(rs.getString("chip"));
                sp.setCard(rs.getString("card"));
                sp.setRam(rs.getString("ram"));
                sp.setGia_ban(rs.getDouble("gia_ban"));
                sp.setSo_luong_ton(rs.getInt("so_luong_ton"));
                sp.setCreated_at(rs.getDate("created_at"));
                sp.setTrang_thai(rs.getInt("trang_thai"));
                return sp;
            }
        } catch (Exception e) {
            System.out.println("Error in file: SanphamDAO.java");
            System.out.println(e.toString());
        }
        return null;
    }

    public boolean Add(SanphamDTO sp) {
        try {
            String sql = "INSERT INTO san_pham (ma_sp, TenSP, ma_loai, chip, card, ram, gia_ban, so_luong_ton, created_at, trang_thai) VALUES (";
            sql += "'" + sp.getMa_sp() + "',";
            sql += "'" + sp.getTenSP() + "',";
            sql += "'" + sp.getMa_loai() + "',";
            sql += "'" + sp.getChip() + "',";
            sql += "'" + sp.getCard() + "',";
            sql += "'" + sp.getRam() + "',";
            sql += "'" + sp.getGia_ban() + "',";
            sql += "0,";
            sql += "'" + new java.sql.Date(sp.getCreated_at().getTime()) + "',";
            sql += "1)";
            db.executeUpdate(sql);
            return true;
        } catch (Exception e) {
            System.out.println("Error in file: SanphamDAO.java");
            System.out.println(e.toString());
        }
        return false;
    }

    public boolean Set(SanphamDTO sp) {
        try {
            String sql = "UPDATE san_pham SET ";
            sql += "TenSP = '" + sp.getTenSP() + "',";
            sql += "ma_loai = '" + sp.getMa_loai() + "',";
            sql += "chip = '" + sp.getChip() + "',";
            sql += "card = '" + sp.getCard() + "',";
            sql += "ram = '" + sp.getRam() + "',";
            sql += "gia_ban = " + sp.getGia_ban() + " "; 
            sql += "WHERE ma_sp = '" + sp.getMa_sp() + "'";
    
            db.executeUpdate(sql);
            return true;
        } catch (Exception e) {
            System.out.println("Error in file: SanphamDAO.java");
            System.out.println(e.toString());
        }
        return false;
    }
    public boolean Delete(String ma_sp) {
        try {
            String sql = "UPDATE san_pham SET trang_thai = 0 WHERE ma_sp = '" + ma_sp + "'";
            db.executeUpdate(sql);
            return true;
        } catch (Exception e) {
            System.out.println("Error in file: SanphamDAO.java");
            return false;
        }
    }

}
