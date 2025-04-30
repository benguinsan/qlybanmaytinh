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
    private String ma_pn;
    private String ma_sp;
    private int SoLuong;
    private double DonGia;

    public ChiTietPhieuNhapDTO() {

    }

    public ChiTietPhieuNhapDTO(int ma_ctpn, String ma_pn, String ma_sp, int SoLuong, double DonGia) {
        this.ma_ctpn = ma_ctpn;
        this.ma_pn = ma_pn;
        this.ma_sp = ma_sp;
        this.SoLuong = SoLuong;
        this.DonGia = DonGia;
    }

    public int getMa_ctpn() {
        return ma_ctpn;
    }

    public void setMa_ctpn(int ma_ctpn) {
        this.ma_ctpn = ma_ctpn;
    }

    public String getMa_pn() {
        return ma_pn;
    }

    public void setMa_pn(String ma_pn) {
        this.ma_pn = ma_pn;
    }

    public String getMa_sp() {
        return ma_sp;
    }

    public void setMa_sp(String ma_sp) {
        this.ma_sp = ma_sp;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int SoLuong) {
        this.SoLuong = SoLuong;
    }

    public double getDonGia() {
        return DonGia;
    }

    public void setDonGia(double DonGia) {
        this.DonGia = DonGia;
    }
    
}
