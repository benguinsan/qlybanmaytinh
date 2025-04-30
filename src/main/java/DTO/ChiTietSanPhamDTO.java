/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author mrben
 */
public class ChiTietSanPhamDTO {

    private int ma_chi_tiet;
    private String ma_sp;
    private String chip;
    private String card;
    private String ram;
    private double gia_ban;
    private int so_luong_ton;

    public ChiTietSanPhamDTO() {

    }

    public ChiTietSanPhamDTO(int ma_chi_tiet, String ma_sp, String chip, String card, String ram, double gia_ban,
            int so_luong_ton) {
        this.ma_chi_tiet = ma_chi_tiet;
        this.ma_sp = ma_sp;
        this.chip = chip;
        this.card = card;
        this.ram = ram;
        this.gia_ban = gia_ban;
        this.so_luong_ton = so_luong_ton;
    }

    public int getMa_chi_tiet() {
        return ma_chi_tiet;
    }

    public void setMa_chi_tiet(int ma_chi_tiet) {
        this.ma_chi_tiet = ma_chi_tiet;
    }

    public String getMa_sp() {
        return ma_sp;
    }

    public void setMa_sp(String ma_sp) {
        this.ma_sp = ma_sp;
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
}
