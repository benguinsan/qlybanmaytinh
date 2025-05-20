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

    private int ma_ct_hd;
    private int so_luong;
    private float don_gia;
    private float thanh_tien;
    private String ma_hd;
    private String ma_sp;

    public ChiTietHoaDonDTO() {

    }

    public ChiTietHoaDonDTO(int ma_ct_hd, int so_luong, float don_gia, float thanh_tien, String ma_hd,
            String ma_sp) {
        this.ma_ct_hd = ma_ct_hd;
        this.so_luong = so_luong;
        this.don_gia = don_gia;
        this.thanh_tien = thanh_tien;
        this.ma_hd = ma_hd;
        this.ma_sp = ma_sp;
    }

    public int getMa_ct_hd() {
        return ma_ct_hd;
    }

    public void setMa_ct_hd(int ma_ct_hd) {
        this.ma_ct_hd = ma_ct_hd;
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

    public String getMa_hd() {
        return ma_hd;
    }

    public void setMa_hd(String ma_hd) {
        this.ma_hd = ma_hd;
    }

    public String getMa_sp() {
        return ma_sp;
    }

    public void setMa_sp(String ma_sp) {
        this.ma_sp = ma_sp;
    }
}
