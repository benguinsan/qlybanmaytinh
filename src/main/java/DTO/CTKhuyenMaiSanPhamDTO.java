/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author mrben
 */
public class CTKhuyenMaiSanPhamDTO {
    private String ma_kmsp;
    private String ma_ctkm;
    private String ma_sp;
    private double PhanTramGiamGia;

    public CTKhuyenMaiSanPhamDTO() {

    }

    public CTKhuyenMaiSanPhamDTO(String ma_kmsp, String ma_ctkm, String ma_sp, double PhanTramGiamGia) {
        this.ma_kmsp = ma_kmsp;
        this.ma_ctkm = ma_ctkm;
        this.ma_sp = ma_sp;
        this.PhanTramGiamGia = PhanTramGiamGia;
    }

    public String getMa_kmsp() {
        return ma_kmsp;
    }

    public void setMa_kmsp(String ma_kmsp) {
        this.ma_kmsp = ma_kmsp;
    }

    public String getMa_ctkm() {
        return ma_ctkm;
    }

    public void setMa_ctkm(String ma_ctkm) {
        this.ma_ctkm = ma_ctkm;
    }

    public String getMa_sp() {
        return ma_sp;
    }

    public void setMa_sp(String ma_sp) {
        this.ma_sp = ma_sp;
    }

    public double getPhanTramGiamGia() {
        return PhanTramGiamGia;
    }

    public void setPhanTramGiamGia(double PhanTramGiamGia) {
        this.PhanTramGiamGia = PhanTramGiamGia;
    }
}
