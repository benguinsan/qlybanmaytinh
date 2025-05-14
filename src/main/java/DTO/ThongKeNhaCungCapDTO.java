
package DTO;

import java.util.Objects;

public class ThongKeNhaCungCapDTO {
  private String maNhaCungCap;  // Changed from int to String
    private String tenNhaCungCap;
    private int soLuong;
    private long tongTien;
    
    public ThongKeNhaCungCapDTO(){}

    public ThongKeNhaCungCapDTO(String mancc, String tenncc, int soluong, long tongtien) {
        this.maNhaCungCap = mancc;
        this.tenNhaCungCap = tenncc;
        this.soLuong = soluong;
        this.tongTien = tongtien;
    }

    public String getMaNhaCungCap() {
        return this.maNhaCungCap;
    }

    public void setMaNhaCungCap(String mancc) {
        this.maNhaCungCap = mancc;
    }

    public String getTenNhaCungCap() {
        return tenNhaCungCap;
    }

    public void setTenncc(String tenncc) {
        this.tenNhaCungCap = tenncc;
    }

    public int getSoluong() {
        return soLuong;
    }

    public void setSoluong(int soluong) {
        this.soLuong = soluong;
    }

    public long getTongtien() {
        return tongTien;
    }

    public void setTongtien(long tongtien) {
        this.tongTien = tongtien;
    }

    @Override
    public String toString() {
        return "ThongKeNhaCungCapDTO{" + "mancc=" + maNhaCungCap + ", tenncc=" + tenNhaCungCap + ", soluong=" + soLuong + ", tongtien=" + tongTien + '}';
    }
    
    
    
}
