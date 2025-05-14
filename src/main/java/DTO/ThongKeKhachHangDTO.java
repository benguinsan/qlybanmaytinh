/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author andin
 */
public class ThongKeKhachHangDTO {
    private String maKhachHang;  // Changed from int to String
    private String tenKhachHang;
    private int soLuongPhieu;
    private long tongTien;


    public ThongKeKhachHangDTO() {
    }

    public ThongKeKhachHangDTO(String makh, String tenkh, int soluongphieu, long tongtien) {
        this.maKhachHang = makh;
        this.tenKhachHang = tenkh;
        this.soLuongPhieu = soluongphieu;
        this.tongTien = tongtien;
    }

    public String getMaKhachHang() {
        return maKhachHang;
    }

    public void setMaKhachHang(String makh) {
        this.maKhachHang = makh;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public void setTenKhachHang(String tenkh) {
        this.tenKhachHang = tenkh;
    }

    public int getSoLuongPhieu() {
        return soLuongPhieu;
    }

    public void setSoLuongPhieu(int soluongphieu) {
        this.soLuongPhieu = soluongphieu;
    }

    public long getTongTien() {
        return tongTien;
    }

    public void setTongtien(long tongtien) {
        this.tongTien = tongtien;
    }
    @Override
    public String toString() {
        return "ThongKeKhachHangDTO{" + "makh=" + maKhachHang + ", tenkh=" + tenKhachHang + ", soluongphieu=" + soLuongPhieu + ", tongtien=" + tongTien + '}';
    }

    
}
