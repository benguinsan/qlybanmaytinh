/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author mrben
 */
public class CTKhuyenMaiHoaDonDTO {

    private int ma_kmhd;
    private float so_tien;
    private float phan_tram_giam;
    private int ma_ctkm;
    
    public CTKhuyenMaiHoaDonDTO() {
       
    }

    
    public CTKhuyenMaiHoaDonDTO(int ma_kmhd, float so_tien, float phan_tram_giam, int ma_ctkm) {
        this.ma_kmhd = ma_kmhd;
        this.so_tien = so_tien;
        this.phan_tram_giam = phan_tram_giam;
        this.ma_ctkm = ma_ctkm;
    }

    
    public int getMa_kmhd() {
        return ma_kmhd;
    }

    public void setMa_kmhd(int ma_kmhd) {
        this.ma_kmhd = ma_kmhd;
    }

    public float getSo_tien() {
        return so_tien;
    }

    public void setSo_tien(float so_tien) {
        this.so_tien = so_tien;
    }

    public float getPhan_tram_giam() {
        return phan_tram_giam;
    }

    public void setPhan_tram_giam(float phan_tram_giam) {
        this.phan_tram_giam = phan_tram_giam;
    }

    public int getMa_ctkm() {
        return ma_ctkm;
    }

    public void setMa_ctkm(int ma_ctkm) {
        this.ma_ctkm = ma_ctkm;
    }
    
}
