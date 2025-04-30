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
public class SanphamDTO {

    private String ma_sp;
    private String TenSP;
    private String ma_loai;
    private double DonGia;
    private Date created_at;

    public SanphamDTO() {

    }

    public SanphamDTO(String ma_sp, String TenSP, String ma_loai, double DonGia, Date created_at) {
        this.ma_sp = ma_sp;
        this.TenSP = TenSP;
        this.ma_loai = ma_loai;
        this.DonGia = DonGia;
        this.created_at = created_at;
    }

    public String getMa_sp() {
        return ma_sp;
    }

    public void setMa_sp(String ma_sp) {
        this.ma_sp = ma_sp;
    }

    public String getTenSP() {
        return TenSP;
    }

    public void setTenSP(String TenSP) {
        this.TenSP = TenSP;
    }

    public String getMa_loai() {
        return ma_loai;
    }

    public void setMa_loai(String ma_loai) {
        this.ma_loai = ma_loai;
    }

    public double getDonGia() {
        return DonGia;
    }

    public void setDonGia(double DonGia) {
        this.DonGia = DonGia;
    }

    public java.util.Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }
}
