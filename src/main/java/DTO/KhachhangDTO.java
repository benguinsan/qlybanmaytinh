/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author mrben
 */
public class KhachhangDTO {

    private int ma_khach_hang;
    private String ho_ten;
    private String dia_chi;
    private int dien_thoai;
    private int ma_tai_khoan;
    
    public KhachhangDTO() {

    }
    
    public KhachhangDTO(int ma_khach_hang, String ho_ten, String dia_chi, int dien_thoai, int ma_tai_khoan) {
        this.ma_khach_hang = ma_khach_hang;
        this.ho_ten = ho_ten;
        this.dia_chi = dia_chi;
        this.dien_thoai = dien_thoai;
        this.ma_tai_khoan = ma_tai_khoan;
    }
    
    public int getMa_khach_hang() {
        return ma_khach_hang;
    }

    public void setMa_khach_hang(int ma_khach_hang) {
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

    public int getDien_thoai() {
        return dien_thoai;
    }

    public void setDien_thoai(int dien_thoai) {
        this.dien_thoai = dien_thoai;
    }

    public int getMa_tai_khoan() {
        return ma_tai_khoan;
    }

    public void setMa_tai_khoan(int ma_tai_khoan) {
        this.ma_tai_khoan = ma_tai_khoan;
    }
            
}
