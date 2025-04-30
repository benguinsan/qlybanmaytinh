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
     
     public KhachhangDAO(){
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
}
