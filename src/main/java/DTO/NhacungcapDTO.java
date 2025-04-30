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
public class NhacungcapDTO {

    private String ma_ncc;
    private String ten_ncc;
    private String dia_chi;
    private String dien_thoai;
    private String email;
    private Date created_at;

    public NhacungcapDTO() {

    }

    public NhacungcapDTO(String ma_ncc, String ten_ncc, String dia_chi, String dien_thoai, String email,
            Date created_at) {
        this.ma_ncc = ma_ncc;
        this.ten_ncc = ten_ncc;
        this.dia_chi = dia_chi;
        this.dien_thoai = dien_thoai;
        this.email = email;
        this.created_at = created_at;
    }

    public String getMa_ncc() {
        return ma_ncc;
    }

    public void setMa_ncc(String ma_ncc) {
        this.ma_ncc = ma_ncc;
    }

    public String getTen_ncc() {
        return ten_ncc;
    }

    public void setTen_ncc(String ten_ncc) {
        this.ten_ncc = ten_ncc;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public java.util.Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }
}
