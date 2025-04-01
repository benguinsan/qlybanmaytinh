/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author mrben
 */
public class CTKhuyenMaiSanPhamDTO {
    private int ma_kmsp;
    private float phan_tram_giam_gia;
    private int ma_ctkm;
    private int ma_sp;
    
    public CTKhuyenMaiSanPhamDTO() {

    }
    
    public CTKhuyenMaiSanPhamDTO(int ma_kmsp, float phan_tram_giam_gia, int ma_ctkm, int ma_sp) {
        this.ma_kmsp = ma_kmsp;
        this.phan_tram_giam_gia = phan_tram_giam_gia;
        this.ma_ctkm = ma_ctkm;
        this.ma_sp = ma_sp;
    }
    
     public int getMa_kmsp() {
        return ma_kmsp;
    }

    public void setMa_kmsp(int ma_kmsp) {
        this.ma_kmsp = ma_kmsp;
    }

    public float getPhan_tram_giam_gia() {
        return phan_tram_giam_gia;
    }

    public void setPhan_tram_giam_gia(float phan_tram_giam_gia) {
        this.phan_tram_giam_gia = phan_tram_giam_gia;
    }

    public int getMa_ctkm() {
        return ma_ctkm;
    }

    public void setMa_ctkm(int ma_ctkm) {
        this.ma_ctkm = ma_ctkm;
    }

    public int getMa_sp() {
        return ma_sp;
    }

    public void setMa_sp(int ma_sp) {
        this.ma_sp = ma_sp;
    }
}
