/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 *
 * @author mrben
 */
public class PhieunhapDTO {

    private int ma_phieu_nhap;
    private int ma_nv;
    private int ma_ncc;
    private LocalDateTime ngay_nhap;
    private float tong_tien;
    
    public PhieunhapDTO() {
        
    }
    
    public PhieunhapDTO(int ma_phieu_nhap, int ma_nv, int ma_ncc, LocalDateTime ngay_nhap, float tong_tien) {
        this.ma_phieu_nhap = ma_phieu_nhap;
        this.ma_nv = ma_nv;
        this.ma_ncc = ma_ncc;
        this.ngay_nhap = ngay_nhap;
        this.tong_tien = tong_tien;
    }
    
    public int getMa_phieu_nhap() {
        return ma_phieu_nhap;
    }

    public void setMa_phieu_nhap(int ma_phieu_nhap) {
        this.ma_phieu_nhap = ma_phieu_nhap;
    }

    public int getMa_nv() {
        return ma_nv;
    }

    public void setMa_nv(int ma_nv) {
        this.ma_nv = ma_nv;
    }

    public int getMa_ncc() {
        return ma_ncc;
    }

    public void setMa_ncc(int ma_ncc) {
        this.ma_ncc = ma_ncc;
    }

    public LocalDateTime getNgay_nhap() {
        return ngay_nhap;
    }

    public void setNgay_nhap(LocalDateTime ngay_nhap) {
        this.ngay_nhap = ngay_nhap;
    }

    public float getTong_tien() {
        return tong_tien;
    }

    public void setTong_tien(float tong_tien) {
        this.tong_tien = tong_tien;
    }
}
