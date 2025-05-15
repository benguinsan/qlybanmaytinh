/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DTO.SanphamDTO;
import DAO.SanphamDAO;
import java.util.ArrayList;

/**
 *
 * @author mrben
 */
public class SanphamBUS {
    private ArrayList<SanphamDTO> listSanpham;
    private SanphamDAO sanphamDAO;

    public SanphamBUS() {
        sanphamDAO = new SanphamDAO();
        ListSanpham();
    }

    public void ListSanpham() {
        listSanpham = new ArrayList<>();
        listSanpham = sanphamDAO.getAllSanpham();
    }

    public ArrayList<SanphamDTO> getList() {
        return this.listSanpham;
    }

    public boolean AddSanpham(SanphamDTO sp) {
        if (sanphamDAO.Add(sp)) {
            this.listSanpham.add(sp);
            return true;
        }
        return false;
    }

    public boolean SetSanpham(SanphamDTO sp) {
        if (sanphamDAO.Set(sp)) {
            for (int i = 0; i < this.listSanpham.size(); i++) {
                if (this.listSanpham.get(i).getMa_sp().equals(sp.getMa_sp())) {
                    this.listSanpham.set(i, sp);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean DeleteSanpham(String ma_sp) {
        if (sanphamDAO.Delete(ma_sp)) {
            for (int i = 0; i < this.listSanpham.size(); i++) {
                if (this.listSanpham.get(i).getMa_sp().equals(ma_sp)) {
                    this.listSanpham.remove(i);
                    return true;
                }
            }
        }
        return false;
    }

    public SanphamDTO getSanphamByMaSP(String ma_sp) {
        for (SanphamDTO sp : this.listSanpham) {
            if (sp.getMa_sp().equals(ma_sp)) {
                return sp;
            }
        }
        return null;
    }

    public ArrayList<SanphamDTO> searchSanpham(String keyword) {
        ArrayList<SanphamDTO> searchResults = new ArrayList<>();
        String lowerKeyword = keyword.toLowerCase();

        for (SanphamDTO sp : listSanpham) {
            if (sp.getMa_sp().toLowerCase().contains(lowerKeyword)
                    || sp.getTenSP().toLowerCase().contains(lowerKeyword)) {
                searchResults.add(sp);
            }
        }

        return searchResults;
    }
}
