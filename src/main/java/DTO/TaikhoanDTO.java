/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;
import java.time.LocalDateTime;

/**
 *
 * @author mrben
 */
public class TaikhoanDTO {


    private int ma_tai_khoan;
    private String email;
    private String password;
    private int loaitaikhoan;
    private int trangthai;
    private LocalDateTime ngaytao;
    
    public TaikhoanDTO() {

    }
    
    public TaikhoanDTO(int ma_tai_khoan, String email, String password, int loaitaikhoan, int trangthai, LocalDateTime ngaytao) {
        this.ma_tai_khoan = ma_tai_khoan;
        this.email = email;
        this.password = password;
        this.loaitaikhoan = loaitaikhoan;
        this.trangthai = trangthai;
        this.ngaytao = ngaytao;
    }
    
     public int getMa_tai_khoan() {
        return ma_tai_khoan;
    }

    public void setMa_tai_khoan(int ma_tai_khoan) {
        this.ma_tai_khoan = ma_tai_khoan;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getLoaitaikhoan() {
        return loaitaikhoan;
    }

    public void setLoaitaikhoan(int loaitaikhoan) {
        this.loaitaikhoan = loaitaikhoan;
    }

    public int getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(int trangthai) {
        this.trangthai = trangthai;
    }

    public LocalDateTime getNgaytao() {
        return ngaytao;
    }

    public void setNgaytao(LocalDateTime ngaytao) {
        this.ngaytao = ngaytao;
    }
}
