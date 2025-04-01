/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.time.LocalDateTime;

/**
 *
 * @author mrben
 */
public class HoaDonDTO {

  
    private int ma_hoa_don;
    private LocalDateTime ngay_lap;
    private float tong_tien;
    private int ma_nv;
    private int ma_khach_hang;
    
    public HoaDonDTO() {
     
    }
    
    public HoaDonDTO(int ma_hoa_don, LocalDateTime ngay_lap, float tong_tien, int ma_nv, int ma_khach_hang) {
        this.ma_hoa_don = ma_hoa_don;
        this.ngay_lap = ngay_lap;
        this.tong_tien = tong_tien;
        this.ma_nv = ma_nv;
        this.ma_khach_hang = ma_khach_hang;
    }
    
    public int getMa_hoa_don() {
        return ma_hoa_don;
    }

    public void setMa_hoa_don(int ma_hoa_don) {
        this.ma_hoa_don = ma_hoa_don;
    }

    public LocalDateTime getNgay_lap() {
        return ngay_lap;
    }

    public void setNgay_lap(LocalDateTime ngay_lap) {
        this.ngay_lap = ngay_lap;
    }

    public float getTong_tien() {
        return tong_tien;
    }

    public void setTong_tien(float tong_tien) {
        this.tong_tien = tong_tien;
    }

    public int getMa_nv() {
        return ma_nv;
    }

    public void setMa_nv(int ma_nv) {
        this.ma_nv = ma_nv;
    }

    public int getMa_khach_hang() {
        return ma_khach_hang;
    }

    public void setMa_khach_hang(int ma_khach_hang) {
        this.ma_khach_hang = ma_khach_hang;
    }
}
