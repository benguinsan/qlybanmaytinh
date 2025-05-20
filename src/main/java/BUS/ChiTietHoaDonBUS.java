/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.ChiTietHoaDonDAO;
import DTO.ChiTietHoaDonDTO;
import DTO.SanphamDTO;
import java.util.ArrayList;

/**
 *
 * @author mrben
 */
public class ChiTietHoaDonBUS {
    private ArrayList<ChiTietHoaDonDTO> listChiTietHoaDon;
    private ChiTietHoaDonDAO chiTietHoaDonDAO;
    private SanphamBUS sanphamBUS;

    public ChiTietHoaDonBUS() {
        chiTietHoaDonDAO = new ChiTietHoaDonDAO();
        sanphamBUS = new SanphamBUS();
        listChiTietHoaDon = chiTietHoaDonDAO.getAllChiTietHoaDon();
    }

    public ArrayList<ChiTietHoaDonDTO> getListChiTietHoaDon() {
        return listChiTietHoaDon;
    }

    public ArrayList<ChiTietHoaDonDTO> getChiTietHoaDonByMaHD(String maHD) {
        ArrayList<ChiTietHoaDonDTO> result = new ArrayList<>();

        for (ChiTietHoaDonDTO cthd : listChiTietHoaDon) {
            if (cthd.getMa_hd().equals(maHD)) {
                result.add(cthd);
            }
        }

        return result;
    }

    public boolean addChiTietHoaDon(String maHD, String maSP, int soLuong, float donGia) {
        try {
            // Kiểm tra số lượng tồn kho trước khi thêm
            SanphamDTO sanPham = sanphamBUS.getSanphamById(maSP);
            if (sanPham == null) {
                throw new Exception("Không tìm thấy sản phẩm với mã: " + maSP);
            }

            // Kiểm tra nếu sản phẩm hết hàng
            if (sanPham.getSo_luong_ton() <= 0) {
                throw new Exception("Sản phẩm " + sanPham.getTenSP() + " đã hết hàng!");
            }

            // Kiểm tra nếu số lượng yêu cầu lớn hơn số lượng tồn kho
            if (sanPham.getSo_luong_ton() < soLuong) {
                throw new Exception("Sản phẩm " + sanPham.getTenSP() + " chỉ còn " + sanPham.getSo_luong_ton()
                        + " sản phẩm trong kho!");
            }

            // Tính thành tiền
            float thanhTien = soLuong * donGia;

            // Tạo đối tượng chi tiết hóa đơn mới
            ChiTietHoaDonDTO cthd = new ChiTietHoaDonDTO();
            cthd.setMa_ct_hd(chiTietHoaDonDAO.getNextMaCTHD());
            cthd.setMa_hd(maHD);
            cthd.setMa_sp(maSP);
            cthd.setSo_luong(soLuong);
            cthd.setDon_gia(donGia);
            cthd.setThanh_tien(thanhTien);

            // Thêm vào database (việc cập nhật số lượng tồn kho được thực hiện trong DAO)
            boolean result = chiTietHoaDonDAO.add(cthd);

            // Nếu thành công, thêm vào danh sách
            if (result) {
                listChiTietHoaDon.add(cthd);
            }

            return result;
        } catch (Exception e) {
            System.out.println("Error in ChiTietHoaDonBUS.addChiTietHoaDon: " + e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    public boolean updateChiTietHoaDon(int maCTHD, String maHD, String maSP, int soLuong, float donGia) {
        try {
            // Tìm chi tiết hóa đơn cần cập nhật
            ChiTietHoaDonDTO cthd = null;
            for (ChiTietHoaDonDTO item : listChiTietHoaDon) {
                if (item.getMa_ct_hd() == maCTHD) {
                    cthd = item;
                    break;
                }
            }

            if (cthd == null) {
                return false;
            }

            // Tính thành tiền mới
            float thanhTien = soLuong * donGia;

            // Cập nhật thông tin
            cthd.setMa_hd(maHD);
            cthd.setMa_sp(maSP);
            cthd.setSo_luong(soLuong);
            cthd.setDon_gia(donGia);
            cthd.setThanh_tien(thanhTien);

            // Cập nhật vào database (việc cập nhật số lượng tồn kho được thực hiện trong
            // DAO)
            return chiTietHoaDonDAO.update(cthd);
        } catch (Exception e) {
            System.out.println("Error in ChiTietHoaDonBUS.updateChiTietHoaDon: " + e.getMessage());
            return false;
        }
    }

    public boolean deleteChiTietHoaDon(int maCTHD) {
        try {
            // Xóa khỏi database (việc cập nhật số lượng tồn kho được thực hiện trong DAO)
            boolean result = chiTietHoaDonDAO.delete(maCTHD);

            // Nếu thành công, xóa khỏi danh sách
            if (result) {
                listChiTietHoaDon.removeIf(item -> item.getMa_ct_hd() == maCTHD);
            }

            return result;
        } catch (Exception e) {
            System.out.println("Error in ChiTietHoaDonBUS.deleteChiTietHoaDon: " + e.getMessage());
            return false;
        }
    }

    public boolean deleteByMaHD(int maHD) {
        try {
            // Xóa khỏi database (việc cập nhật số lượng tồn kho được thực hiện trong DAO)
            boolean result = chiTietHoaDonDAO.deleteByMaHD(maHD);

            // Nếu thành công, xóa khỏi danh sách
            if (result) {
                listChiTietHoaDon.removeIf(cthd -> cthd.getMa_hd().equals(maHD));
            }

            return result;
        } catch (Exception e) {
            System.out.println("Error in ChiTietHoaDonBUS.deleteByMaHD: " + e.getMessage());
            return false;
        }
    }

    public float tinhTongTien(int maHD) {
        float tongTien = 0;

        for (ChiTietHoaDonDTO cthd : listChiTietHoaDon) {
            if (cthd.getMa_hd().equals(maHD)) {
                tongTien += cthd.getThanh_tien();
            }
        }

        return tongTien;
    }

    public void refreshData() {
        listChiTietHoaDon = chiTietHoaDonDAO.getAllChiTietHoaDon();
    }
}
