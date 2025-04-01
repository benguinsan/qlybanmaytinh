/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;
import DTO.NhanvienDTO;
import DAO.NhanvienDAO;
import java.util.ArrayList;

/**
 *
 * @author mrben
 */
public class NhanvienBUS {
    private ArrayList<NhanvienDTO> listNhanvien;

    public NhanvienBUS() {
        ListNhanvien();
    }

    public void ListNhanvien() {
        NhanvienDAO NhanvienDAO = new NhanvienDAO();
        listNhanvien = new ArrayList<>();
        listNhanvien = NhanvienDAO.getAllNhanvien();
    }

    public ArrayList<NhanvienDTO> getList() {
        return this.listNhanvien;
    }
}
