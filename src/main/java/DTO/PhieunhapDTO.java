/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author mrben
 */
public class PhieunhapDTO {

    private String ma_pn;
    private String ma_nhan_vien;
    private String ma_ncc;
    private Date NgayNhap;
    private double TongTien;
    
    public PhieunhapDTO() {
        
    }
    
    public PhieunhapDTO(String ma_pn, String ma_nhan_vien, String ma_ncc, Date NgayNhap, double TongTien) {
        this.ma_pn = ma_pn;
        this.ma_nhan_vien = ma_nhan_vien;
        this.ma_ncc = ma_ncc;
        this.NgayNhap = NgayNhap;
        this.TongTien = TongTien;
    }
    
    public String getMa_pn() {
        return ma_pn;
    }

    public void setMa_pn(String ma_pn) {
        this.ma_pn = ma_pn;
    }

    public String getMa_nhan_vien() {
        return ma_nhan_vien;
    }

    public void setMa_nhan_vien(String ma_nhan_vien) {
        this.ma_nhan_vien = ma_nhan_vien;
    }

    public String getMa_ncc() {
        return ma_ncc;
    }

    public void setMa_ncc(String ma_ncc) {
        this.ma_ncc = ma_ncc;
    }

    public Date getNgayNhap() {
        return NgayNhap;
    }

    public void setNgayNhap(Date NgayNhap) {
        this.NgayNhap = NgayNhap;
    }

    public double getTongTien() {
        return TongTien;
    }

    public void setTongTien(double TongTien) {
        this.TongTien = TongTien;
    }
}
