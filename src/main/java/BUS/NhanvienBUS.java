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
    private NhanvienDAO nhanvienDAO;

    public NhanvienBUS() {
        nhanvienDAO = new NhanvienDAO();
        ListNhanvien();
    }

    public void ListNhanvien() {
        listNhanvien = new ArrayList<>();
        listNhanvien = nhanvienDAO.getAllNhanvien();
    }

    // Get list nhan vien
    public ArrayList<NhanvienDTO> getList() {
        return this.listNhanvien;
    }

    // Add nhan vien
    public boolean AddNhanVien(NhanvienDTO nv) {
        if (nhanvienDAO.Add(nv)) {
            this.listNhanvien.add(nv);
            return true;
        }
        return false;
    }

    // Set nhan vien
    public boolean SetNhanVien(NhanvienDTO nv) {
        if (nhanvienDAO.Set(nv)) {
            for (int i = 0; i < this.listNhanvien.size(); i++) {
                if (this.listNhanvien.get(i).getMa_nhan_vien() == nv.getMa_nhan_vien()) {
                    this.listNhanvien.set(i, nv);
                    return true;
                }
            }
        }
        return false;
    }

    // Delete nhan vien
    public boolean DeleteNhanVien(String ma_nhan_vien) {
        if (nhanvienDAO.Delete(ma_nhan_vien)) {
            for (int i = 0; i < this.listNhanvien.size(); i++) {
                if (this.listNhanvien.get(i).getMa_nhan_vien().equals(ma_nhan_vien)) {
                    this.listNhanvien.remove(i);
                    return true;
                }
            }
        }
        return false;
    }

    // Tìm nhân viên theo mã
    public NhanvienDTO getNhanvienByMaNV(String ma_nhan_vien) {
        for (NhanvienDTO nv : this.listNhanvien) {
            if (nv.getMa_nhan_vien().equals(ma_nhan_vien)) {
                return nv;
            }
        }
        return null;
    }

    public ArrayList<NhanvienDTO> searchNhanvien(String keyword) {
        ArrayList<NhanvienDTO> searchResults = new ArrayList<>();

        // Chuyển từ khóa về chữ thường để tìm kiếm không phân biệt hoa thường
        String lowerKeyword = keyword.toLowerCase();

        for (NhanvienDTO nv : listNhanvien) {
            // Tìm theo mã khách hàng
            if (nv.getMa_nhan_vien().toLowerCase().contains(lowerKeyword)) {
                searchResults.add(nv);
                continue;
            }

            // Tìm theo tên khách hàng
            if (nv.getHo_ten().toLowerCase().contains(lowerKeyword)) {
                searchResults.add(nv);
                continue;
            }

            // Tìm theo số điện thoại
            if (nv.getDien_thoai().contains(keyword)) {
                searchResults.add(nv);
                continue;
            }

            // Tìm theo email
            if (nv.getEmail().contains(keyword)) {
                searchResults.add(nv);
                continue;
            }
        }

        return searchResults;
    }
}
