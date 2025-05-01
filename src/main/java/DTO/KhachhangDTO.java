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
public class KhachhangDTO {

    private String ma_khach_hang;
    private String ho_ten;
    private String dia_chi;
    private String dien_thoai;
    private Date created_at;
    private int trang_thai;

    public KhachhangDTO() {

    }

    public KhachhangDTO(String ma_khach_hang, String ho_ten, String dia_chi, String dien_thoai, Date created_at,
            int trang_thai) {
        this.ma_khach_hang = ma_khach_hang;
        this.ho_ten = ho_ten;
        this.dia_chi = dia_chi;
        this.dien_thoai = dien_thoai;
        this.created_at = created_at;
        this.trang_thai = trang_thai;
    }

    public String getMa_khach_hang() {
        return ma_khach_hang;
    }

    public void setMa_khach_hang(String ma_khach_hang) {
        this.ma_khach_hang = ma_khach_hang;
    }

    public String getHo_ten() {
        return ho_ten;
    }

    public void setHo_ten(String ho_ten) {
        this.ho_ten = ho_ten;
    }

    public String getDia_chi() {
        return dia_chi;
    }

    public void setDia_chi(String dia_chi) {
        this.dia_chi = dia_chi;
    }

    public String getDien_thoai() {
        return dien_thoai;
    }

    public void setDien_thoai(String dien_thoai) {
        this.dien_thoai = dien_thoai;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public int getTrang_thai() {
        return trang_thai;
    }

    public void setTrang_thai(int trang_thai) {
        this.trang_thai = trang_thai;
    }

}
