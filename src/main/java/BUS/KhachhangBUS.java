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
    public KhachhangDTO findByMa(String ma_khach_hang) {
        for (KhachhangDTO kh : this.listKhachhang) {
            if (kh.getMa_khach_hang().equals(ma_khach_hang)) {
                return kh;
            }
        }
        return null;
    }

    // Tìm khách hàng theo tên
    public ArrayList<KhachhangDTO> findByName(String ten) {
        ArrayList<KhachhangDTO> result = new ArrayList<>();
        for (KhachhangDTO kh : this.listKhachhang) {
            if (kh.getHo_ten().toLowerCase().contains(ten.toLowerCase())) {
                result.add(kh);
            }
        }
        return result;
    }

    // Tìm khách hàng theo số điện thoại
    public ArrayList<KhachhangDTO> findByPhone(String phone) {
        ArrayList<KhachhangDTO> result = new ArrayList<>();
        for (KhachhangDTO kh : this.listKhachhang) {
            if (kh.getDien_thoai().contains(phone)) {
                result.add(kh);
            }
        }
        return result;
    }

    // Tìm khách hàng theo mã khách hàng
    public KhachhangDTO getKhachhangByMaKH(String maKH) {
        for (KhachhangDTO kh : this.listKhachhang) {
            if (kh.getMa_khach_hang().equals(maKH)) {
                return kh;
            }
        }
        return null;
    }
}
