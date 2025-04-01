/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author mrben
 */
public class ChiTietPhieuNhapDTO {

    private int ma_ctpn;
    private int so_luong;
    private float don_gia_nhap;
    private float thanhtien;

    public ChiTietPhieuNhapDTO() {
     
    }
    
    public ChiTietPhieuNhapDTO(int ma_ctpn, int so_luong, float don_gia_nhap, float thanhtien) {
        this.ma_ctpn = ma_ctpn;
        this.so_luong = so_luong;
        this.don_gia_nhap = don_gia_nhap;
        this.thanhtien = thanhtien;
    }
    
    public int getMa_ctpn() {
        return ma_ctpn;
    }

    public void setMa_ctpn(int ma_ctpn) {
        this.ma_ctpn = ma_ctpn;
    }

    public int getSo_luong() {
        return so_luong;
    }

    public void setSo_luong(int so_luong) {
        this.so_luong = so_luong;
    }

    public float getDon_gia_nhap() {
        return don_gia_nhap;
    }

    public void setDon_gia_nhap(float don_gia_nhap) {
        this.don_gia_nhap = don_gia_nhap;
    }

    public float getThanhtien() {
        return thanhtien;
    }

    public void setThanhtien(float thanhtien) {
        this.thanhtien = thanhtien;
    }
    
}
