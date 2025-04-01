/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author mrben
 */
public class SanphamDTO {

    private int ma_sp;
    private String ten_sp;
    private int ma_loai;
    private float don_gia;
    
    public SanphamDTO() {

    }

    public SanphamDTO(int ma_sp, String ten_sp, int ma_loai, float don_gia) {
        this.ma_sp = ma_sp;
        this.ten_sp = ten_sp;
        this.ma_loai = ma_loai;
        this.don_gia = don_gia;
    }

    public int getMa_sp() {
        return ma_sp;
    }

    public void setMa_sp(int ma_sp) {
        this.ma_sp = ma_sp;
    }

    public String getTen_sp() {
        return ten_sp;
    }

    public void setTen_sp(String ten_sp) {
        this.ten_sp = ten_sp;
    }

    public int getMa_loai() {
        return ma_loai;
    }

    public void setMa_loai(int ma_loai) {
        this.ma_loai = ma_loai;
    }

    public float getDon_gia() {
        return don_gia;
    }

    public void setDon_gia(float don_gia) {
        this.don_gia = don_gia;
    }
}
