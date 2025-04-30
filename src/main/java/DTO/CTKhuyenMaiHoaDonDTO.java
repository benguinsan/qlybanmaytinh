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

    private String ma_kmhd;
    private String ma_ctkm;
    private double SoTienToiThieu;
    private double PhanTramGiamGia;
    private double SoTienGiamToiDa;

    public CTKhuyenMaiHoaDonDTO() {

    }

    public CTKhuyenMaiHoaDonDTO(String ma_kmhd, String ma_ctkm, double SoTienToiThieu, double PhanTramGiamGia,
            double SoTienGiamToiDa) {
        this.ma_kmhd = ma_kmhd;
        this.ma_ctkm = ma_ctkm;
        this.SoTienToiThieu = SoTienToiThieu;
        this.PhanTramGiamGia = PhanTramGiamGia;
        this.SoTienGiamToiDa = SoTienGiamToiDa;
    }

    public String getMa_kmhd() {
        return ma_kmhd;
    }

    public void setMa_kmhd(String ma_kmhd) {
        this.ma_kmhd = ma_kmhd;
    }

    public String getMa_ctkm() {
        return ma_ctkm;
    }

    public void setMa_ctkm(String ma_ctkm) {
        this.ma_ctkm = ma_ctkm;
    }

    public double getSoTienToiThieu() {
        return SoTienToiThieu;
    }

    public void setSoTienToiThieu(double SoTienToiThieu) {
        this.SoTienToiThieu = SoTienToiThieu;
    }

    public double getPhanTramGiamGia() {
        return PhanTramGiamGia;
    }

    public void setPhanTramGiamGia(double PhanTramGiamGia) {
        this.PhanTramGiamGia = PhanTramGiamGia;
    }

    public double getSoTienGiamToiDa() {
        return SoTienGiamToiDa;
    }

    public void setSoTienGiamToiDa(double SoTienGiamToiDa) {
        this.SoTienGiamToiDa = SoTienGiamToiDa;
    }

}
