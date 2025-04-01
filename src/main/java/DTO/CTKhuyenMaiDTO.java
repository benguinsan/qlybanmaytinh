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
public class CTKhuyenMaiDTO {

   
    private int ma_ctkm;
    private String ten_ctkm;
    private String mota;
    private LocalDateTime ngay_bat_dau;
    private LocalDateTime ngay_ket_thuc;
    private int trangthai;
    
    public CTKhuyenMaiDTO() {
       
    }
    
    public CTKhuyenMaiDTO(int ma_ctkm, String ten_ctkm, String mota, LocalDateTime ngay_bat_dau, LocalDateTime ngay_ket_thuc, int trangthai) {
        this.ma_ctkm = ma_ctkm;
        this.ten_ctkm = ten_ctkm;
        this.mota = mota;
        this.ngay_bat_dau = ngay_bat_dau;
        this.ngay_ket_thuc = ngay_ket_thuc;
        this.trangthai = trangthai;
    }
    
    public int getMa_ctkm() {
        return ma_ctkm;
    }

    public void setMa_ctkm(int ma_ctkm) {
        this.ma_ctkm = ma_ctkm;
    }

    public String getTen_ctkm() {
        return ten_ctkm;
    }

    public void setTen_ctkm(String ten_ctkm) {
        this.ten_ctkm = ten_ctkm;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public LocalDateTime getNgay_bat_dau() {
        return ngay_bat_dau;
    }

    public void setNgay_bat_dau(LocalDateTime ngay_bat_dau) {
        this.ngay_bat_dau = ngay_bat_dau;
    }

    public LocalDateTime getNgay_ket_thuc() {
        return ngay_ket_thuc;
    }

    public void setNgay_ket_thuc(LocalDateTime ngay_ket_thuc) {
        this.ngay_ket_thuc = ngay_ket_thuc;
    }

    public int getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(int trangthai) {
        this.trangthai = trangthai;
    }
    
}
