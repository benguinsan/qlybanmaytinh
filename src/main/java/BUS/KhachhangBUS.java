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
    private KhachhangDAO khachhangDAO;

    public KhachhangBUS() {
        khachhangDAO = new KhachhangDAO();
        ListKhachhang();
    }

    public void ListKhachhang() {
        listKhachhang = new ArrayList<>();
        listKhachhang = khachhangDAO.getAllKhachhang();
    }

    public ArrayList<KhachhangDTO> getList() {
        return this.listKhachhang;
    }

    // Thêm khách hàng
    public boolean AddKhachhang(KhachhangDTO kh) {
        if (khachhangDAO.Add(kh)) {
            this.listKhachhang.add(kh);
            return true;
        }
        return false;
    }

    // Cập nhật thông tin khách hàng
    public boolean SetKhachhang(KhachhangDTO kh) {
        if (khachhangDAO.Set(kh)) {
            for (int i = 0; i < this.listKhachhang.size(); i++) {
                if (this.listKhachhang.get(i).getMa_khach_hang().equals(kh.getMa_khach_hang())) {
                    this.listKhachhang.set(i, kh);
                    return true;
                }
            }
        }
        return false;
    }

    // Xóa khách hàng
    public boolean DeleteKhachhang(String ma_khach_hang) {
        if (khachhangDAO.Delete(ma_khach_hang)) {
            for (int i = 0; i < this.listKhachhang.size(); i++) {
                if (this.listKhachhang.get(i).getMa_khach_hang().equals(ma_khach_hang)) {
                    this.listKhachhang.remove(i);
                    return true;
                }
            }
        }
        return false;
    }

    // Tìm khách hàng theo mã
    public KhachhangDTO getKhachhangByMaKH(String ma_khach_hang) {
        for (KhachhangDTO kh : this.listKhachhang) {
            if (kh.getMa_khach_hang().equals(ma_khach_hang)) {
                return kh;
            }
        }
        return null;
    }


    public ArrayList<KhachhangDTO> searchKhachhang(String keyword) {
        ArrayList<KhachhangDTO> searchResults = new ArrayList<>();

        // Chuyển từ khóa về chữ thường để tìm kiếm không phân biệt hoa thường
        String lowerKeyword = keyword.toLowerCase();

        for (KhachhangDTO kh : listKhachhang) {
            // Tìm theo mã khách hàng
            if (kh.getMa_khach_hang().toLowerCase().contains(lowerKeyword)) {
                searchResults.add(kh);
                continue;
            }

            // Tìm theo tên khách hàng
            if (kh.getHo_ten().toLowerCase().contains(lowerKeyword)) {
                searchResults.add(kh);
                continue;
            }

            // Tìm theo số điện thoại
            if (kh.getDien_thoai().contains(keyword)) {
                searchResults.add(kh);
                continue;
            }
        }

        return searchResults;
    }

}
