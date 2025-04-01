/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author mrben
 */
public class LoaisanphamDTO {
    private int ma_loai;
    private String ten_loai;

    public LoaisanphamDTO() {

    }
    
    public LoaisanphamDTO(int ma_loai, String ten_loai) {
        this.ma_loai = ma_loai;
        this.ten_loai = ten_loai;
    }
    
    public int getMa_loai() {
        return ma_loai;
    }

    public void setMa_loai(int ma_loai) {
        this.ma_loai = ma_loai;
    }

    public String getTen_loai() {
        return ten_loai;
    }

    public void setTen_loai(String ten_loai) {
        this.ten_loai = ten_loai;
    }
    
}
