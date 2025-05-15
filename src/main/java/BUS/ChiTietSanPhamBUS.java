/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DTO.ChiTietSanPhamDTO;
import DAO.ChiTietSanPhamDAO;
import java.util.ArrayList;

/**
 *
 * @author mrben
 */
public class ChiTietSanPhamBUS {
    private ArrayList<ChiTietSanPhamDTO> listCTSP;
    private ChiTietSanPhamDAO ctspDAO;

    public ChiTietSanPhamBUS() {
        ctspDAO = new ChiTietSanPhamDAO();
        ListChiTietSanpham();
    }

    public void ListChiTietSanpham() {
        listCTSP = new ArrayList<>();
        listCTSP = ctspDAO.getAllChitietSanpham();
    }

    public ArrayList<ChiTietSanPhamDTO> getList() {
        return this.listCTSP;
    }

    public boolean AddChiTietSanpham(ChiTietSanPhamDTO ctsp) {
        if (ctspDAO.Add(ctsp)) {
            this.listCTSP.add(ctsp);
            return true;
        }
        return false;
    }

    public boolean SetChiTietSanpham(ChiTietSanPhamDTO ctsp) {
        if (ctspDAO.Set(ctsp)) {
            for (int i = 0; i < this.listCTSP.size(); i++) {
                if (this.listCTSP.get(i).getMa_chi_tiet() == ctsp.getMa_chi_tiet()) {
                    this.listCTSP.set(i, ctsp);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean DeleteChiTietSanpham(int ma_chi_tiet) {
        if (ctspDAO.Delete(ma_chi_tiet)) {
            for (int i = 0; i < this.listCTSP.size(); i++) {
                if (this.listCTSP.get(i).getMa_chi_tiet() == ma_chi_tiet) {
                    this.listCTSP.remove(i);
                    return true;
                }
            }
        }
        return false;
    }

    public ChiTietSanPhamDTO getCTSPByID(int ma_chi_tiet) {
        for (ChiTietSanPhamDTO ctsp : this.listCTSP) {
            if (ctsp.getMa_chi_tiet() == ma_chi_tiet) {
                return ctsp;
            }
        }
        return null;
    }

    public ChiTietSanPhamDTO getCTSPByMaSP(String ma_sp) {
        for (ChiTietSanPhamDTO ctsp : listCTSP) {
            if (ctsp.getMa_sp().equals(ma_sp)) {
                return ctsp;
            }
        }
        return null;
    }
}
