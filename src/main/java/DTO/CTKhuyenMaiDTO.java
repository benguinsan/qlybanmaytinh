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
public class CTKhuyenMaiDTO {

    private String ma_ctkm;
    private String TenChuongTrinh;
    private Date NgayBatDau;
    private Date NgayKetThuc;
    private String MoTa;
    private int TrangThai;

    public CTKhuyenMaiDTO() {

    }

    public CTKhuyenMaiDTO(String ma_ctkm, String TenChuongTrinh, Date NgayBatDau, Date NgayKetThuc,
            String MoTa, int TrangThai) {
        this.ma_ctkm = ma_ctkm;
        this.TenChuongTrinh = TenChuongTrinh;
        this.NgayBatDau = NgayBatDau;
        this.NgayKetThuc = NgayKetThuc;
        this.MoTa = MoTa;
        this.TrangThai = TrangThai;
    }

    public String getMa_ctkm() {
        return ma_ctkm;
    }

    public void setMa_ctkm(String ma_ctkm) {
        this.ma_ctkm = ma_ctkm;
    }

    public String getTenChuongTrinh() {
        return TenChuongTrinh;
    }

    public void setTenChuongTrinh(String TenChuongTrinh) {
        this.TenChuongTrinh = TenChuongTrinh;
    }

    public Date getNgayBatDau() {
        return NgayBatDau;
    }

    public void setNgayBatDau(Date NgayBatDau) {
        this.NgayBatDau = NgayBatDau;
    }

    public Date getNgayKetThuc() {
        return NgayKetThuc;
    }

    public void setNgayKetThuc(Date NgayKetThuc) {
        this.NgayKetThuc = NgayKetThuc;
    }

    public String getMoTa() {
        return MoTa;
    }

    public void setMoTa(String MoTa) {
        this.MoTa = MoTa;
    }

    public int getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(int TrangThai) {
        this.TrangThai = TrangThai;
    }
}
