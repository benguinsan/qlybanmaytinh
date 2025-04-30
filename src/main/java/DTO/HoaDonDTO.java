/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.util.Date;

/**
 *
 * @author mrben
 */
public class HoaDonDTO {

    private String ma_hd;
    private Date ngay_lap;
    private String ma_nhan_vien;
    private String ma_khach_hang;
    private double tong_tien;

    public HoaDonDTO() {

    }

    public HoaDonDTO(String ma_hd, Date ngay_lap, String ma_nhan_vien, String ma_khach_hang, double tong_tien) {
        this.ma_hd = ma_hd;
        this.ngay_lap = ngay_lap;
        this.ma_nhan_vien = ma_nhan_vien;
        this.ma_khach_hang = ma_khach_hang;
        this.tong_tien = tong_tien;
    }

    public String getMa_hd() {
        return ma_hd;
    }

    public void setMa_hd(String ma_hd) {
        this.ma_hd = ma_hd;
    }

    public Date getNgay_lap() {
        return ngay_lap;
    }

    public void setNgay_lap(Date ngay_lap) {
        this.ngay_lap = ngay_lap;
    }

    public String getMa_nhan_vien() {
        return ma_nhan_vien;
    }

    public void setMa_nhan_vien(String ma_nhan_vien) {
        this.ma_nhan_vien = ma_nhan_vien;
    }

    public String getMa_khach_hang() {
        return ma_khach_hang;
    }

    public void setMa_khach_hang(String ma_khach_hang) {
        this.ma_khach_hang = ma_khach_hang;
    }

    public double getTong_tien() {
        return tong_tien;
    }

    public void setTong_tien(double tong_tien) {
        this.tong_tien = tong_tien;
    }

}
