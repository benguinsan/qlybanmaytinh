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
    private String chip;
    private String card;
    private String ram;
    private double gia_ban;
    private int so_luong_ton;
    private Date created_at;
    private int trang_thai; 

    public SanphamDTO() {
    }

    public SanphamDTO(String ma_sp, String TenSP, String ma_loai, String chip,
            String card, String ram, double gia_ban, int so_luong_ton,
            Date created_at, int trang_thai) {
        this.ma_sp = ma_sp;
        this.TenSP = TenSP;
        this.ma_loai = ma_loai;
        this.chip = chip;
        this.card = card;
        this.ram = ram;
        this.gia_ban = gia_ban;
        this.so_luong_ton = so_luong_ton;
        this.created_at = created_at;
        this.trang_thai = trang_thai;
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

    public String getChip() {
        return chip;
    }

    public void setChip(String chip) {
        this.chip = chip;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public double getGia_ban() {
        return gia_ban;
    }

    public void setGia_ban(double gia_ban) {
        this.gia_ban = gia_ban;
    }

    public int getSo_luong_ton() {
        return so_luong_ton;
    }

    public void setSo_luong_ton(int so_luong_ton) {
        this.so_luong_ton = so_luong_ton;
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

    // // Phương thức tiện ích để kiểm tra trạng thái
    // public boolean isActive() {
    //     return trang_thai == 1;
    // }

    // // Phương thức tiện ích để lấy chuỗi mô tả trạng thái
    // public String getTrangThaiText() {
    //     return trang_thai == 1 ? "Đang kinh doanh" : "Ngừng kinh doanh";
    // }
}
