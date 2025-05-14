/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author robot
 */
public class ThongKeTheoThangDTO {
    private int thang;
    private int chiphi;
    private int doanhthu;
    private int loinhuan;
    
    public ThongKeTheoThangDTO(){
        
    }
    
    public ThongKeTheoThangDTO(int thang, int chiphi, int doanhthu, int loinhuan){
        this.thang = thang;
        this.chiphi = chiphi;
        this.doanhthu = doanhthu;
        this.loinhuan = loinhuan;
    }

    public int getThang() {
        return thang;
    }

    public void setThang(int thang) {
        this.thang = thang;
    }

    public int getChiphi() {
        return chiphi;
    }

    public void setChiphi(int chiphi) {
        this.chiphi = chiphi;
    }

    public int getDoanhthu() {
        return doanhthu;
    }

    public void setDoanhthu(int doanhthu) {
        this.doanhthu = doanhthu;
    }

    public int getLoinhuan() {
        return loinhuan;
    }

    public void setLoinhuan(int loinhuan) {
        this.loinhuan = loinhuan;
    }

    @Override
    public String toString() {
        return "ThongKeTheoThangDTO{" + "thang=" + thang + ", chiphi=" + chiphi + ", doanhthu=" + doanhthu + ", loinhuan=" + loinhuan + '}';
    }
    
}
