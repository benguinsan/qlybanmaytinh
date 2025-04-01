/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import java.util.ArrayList;
import DTO.KhachhangDTO;
import DAO.KhachhangDAO;

/**
 *
 * @author mrben
 */
public class KhachhangBUS {
    private ArrayList<KhachhangDTO> listKhachhang;

    public KhachhangBUS() {
        ListKhachhang();
    }

    public void ListKhachhang() {
        KhachhangDAO KhachhangDAO = new KhachhangDAO();
        listKhachhang = new ArrayList<>();
        listKhachhang = KhachhangDAO.getAllKhachhang();
    }

    public ArrayList<KhachhangDTO> getList() {
        return this.listKhachhang;
    }
}
