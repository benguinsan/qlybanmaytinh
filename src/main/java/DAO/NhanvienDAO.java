/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.NhanvienDTO;
import Database.DBConnection;
import Utility.CustomLogger;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Level;

/**
 *
 * @author mrben
 */
public class NhanvienDAO {
    private DBConnection db;
     
    public NhanvienDAO(){
        db = new DBConnection();
    }
    
    public ArrayList<NhanvienDTO> getAllNhanvien() {
        ArrayList<NhanvienDTO> listTmp = new ArrayList<NhanvienDTO>();
        String sql = "SELECT * FROM nhan_vien";
        try {
            ResultSet rs = db.executeQuery(sql);
            NhanvienDTO tmp;
            while (rs.next()) {
                tmp = new NhanvienDTO();
                tmp.setMa_nv(rs.getInt("ma_nv"));
                tmp.setHo_ten(rs.getString("ho_ten"));
                tmp.setMa_tai_khoan(rs.getInt("ma_tai_khoan"));
                tmp.setLuong(rs.getFloat("luong"));
                listTmp.add(tmp);
            }
        } catch (Exception ex) {
            System.out.println("Error in file: NhanvienDAO.java");
        }
        return listTmp;
    }
}
