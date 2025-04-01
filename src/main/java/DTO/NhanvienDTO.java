/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author mrben
 */
public class NhanvienDTO {

    private int ma_nv;
    private String ho_ten;
    private float  luong;
    private int ma_tai_khoan;
    
    public NhanvienDTO() {

    }
    
    public NhanvienDTO(int ma_nv, String ho_ten, float luong, int ma_tai_khoan) {
        this.ma_nv = ma_nv;
        this.ho_ten = ho_ten;
        this.luong = luong;
        this.ma_tai_khoan = ma_tai_khoan;
    }
    
    public int getMa_nv() {
        return ma_nv;
    }

    public void setMa_nv(int ma_nv) {
        this.ma_nv = ma_nv;
    }

    public String getHo_ten() {
        return ho_ten;
    }

    public void setHo_ten(String ho_ten) {
        this.ho_ten = ho_ten;
    }

    public float getLuong() {
        return luong;
    }

    public void setLuong(float luong) {
        this.luong = luong;
    }

    public int getMa_tai_khoan() {
        return ma_tai_khoan;
    }

    public void setMa_tai_khoan(int ma_tai_khoan) {
        this.ma_tai_khoan = ma_tai_khoan;
    }
}
