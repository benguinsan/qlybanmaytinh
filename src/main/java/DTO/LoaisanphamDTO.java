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
public class LoaisanphamDTO {
    private String ma_loai;
    private String ten_loai;
    private String mo_ta;
    private Date created_at;

    public LoaisanphamDTO() {

    }

    public LoaisanphamDTO(String ma_loai, String ten_loai, String mo_ta, Date created_at) {
        this.ma_loai = ma_loai;
        this.ten_loai = ten_loai;
        this.mo_ta = mo_ta;
        this.created_at = created_at;
    }

    public String getMa_loai() {
        return ma_loai;
    }

    public void setMa_loai(String ma_loai) {
        this.ma_loai = ma_loai;
    }

    public String getTen_loai() {
        return ten_loai;
    }

    public void setTen_loai(String ten_loai) {
        this.ten_loai = ten_loai;
    }

    public String getMo_ta() {
        return mo_ta;
    }

    public void setMo_ta(String mo_ta) {
        this.mo_ta = mo_ta;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }
}
