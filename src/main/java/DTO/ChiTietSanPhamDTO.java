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
    private String chip;
    private String ram;
    private String o_cung;
    private String hang;
    private String card;
    private String mota;
    private int ma_sp;
    
    public ChiTietSanPhamDTO() {

    }
    
    public ChiTietSanPhamDTO(int ma_chi_tiet, String chip, String ram, String o_cung, String hang, String card, String mota, int ma_sp) {
        this.ma_chi_tiet = ma_chi_tiet;
        this.chip = chip;
        this.ram = ram;
        this.o_cung = o_cung;
        this.hang = hang;
        this.card = card;
        this.mota = mota;
        this.ma_sp = ma_sp;
    }
    
    public int getMa_chi_tiet() {
        return ma_chi_tiet;
    }

    public void setMa_chi_tiet(int ma_chi_tiet) {
        this.ma_chi_tiet = ma_chi_tiet;
    }

    public String getChip() {
        return chip;
    }

    public void setChip(String chip) {
        this.chip = chip;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getO_cung() {
        return o_cung;
    }

    public void setO_cung(String o_cung) {
        this.o_cung = o_cung;
    }

    public String getHang() {
        return hang;
    }

    public void setHang(String hang) {
        this.hang = hang;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public int getMa_sp() {
        return ma_sp;
    }

    public void setMa_sp(int ma_sp) {
        this.ma_sp = ma_sp;
    }
}
