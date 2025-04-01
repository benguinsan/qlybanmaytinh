/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author mrben
 */
public class ChiTietHoaDonDTO {

    private int ma_cthd;
    private int so_luong;
    private float don_gia;
    private float thanh_tien;
    private int ma_hoa_don;
    private int ma_sp;
    
    public ChiTietHoaDonDTO() {

    }

    public ChiTietHoaDonDTO(int ma_cthd, int so_luong, float don_gia, float thanh_tien, int ma_hoa_don, int ma_sp) {
        this.ma_cthd = ma_cthd;
        this.so_luong = so_luong;
        this.don_gia = don_gia;
        this.thanh_tien = thanh_tien;
        this.ma_hoa_don = ma_hoa_don;
        this.ma_sp = ma_sp;
    }

     public int getMa_cthd() {
        return ma_cthd;
    }

    public void setMa_cthd(int ma_cthd) {
        this.ma_cthd = ma_cthd;
    }

    public int getSo_luong() {
        return so_luong;
    }

    public void setSo_luong(int so_luong) {
        this.so_luong = so_luong;
    }

    public float getDon_gia() {
        return don_gia;
    }

    public void setDon_gia(float don_gia) {
        this.don_gia = don_gia;
    }

    public float getThanh_tien() {
        return thanh_tien;
    }

    public void setThanh_tien(float thanh_tien) {
        this.thanh_tien = thanh_tien;
    }

    public int getMa_hoa_don() {
        return ma_hoa_don;
    }

    public void setMa_hoa_don(int ma_hoa_don) {
        this.ma_hoa_don = ma_hoa_don;
    }

    public int getMa_sp() {
        return ma_sp;
    }

    public void setMa_sp(int ma_sp) {
        this.ma_sp = ma_sp;
    }
}
