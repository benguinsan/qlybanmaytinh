/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DTO.PhieunhapDTO;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Random;
import DAO.PhieunhapDAO;

/**
 *
 * @author mrben
 */
public class PhieunhapBUS {
    private ArrayList<PhieunhapDTO> listPhieuNhap;
    private PhieunhapDAO phieunhapDAO;

    public PhieunhapBUS() {
        phieunhapDAO = new PhieunhapDAO();
        listPhieuNhap = phieunhapDAO.getAllPhieuNhap();
    }

    public ArrayList<PhieunhapDTO> getList() {
        return listPhieuNhap;
    }

    public PhieunhapDTO getPhieuNhapByMaPN(String ma_pn) {
        for (PhieunhapDTO pn : listPhieuNhap) {
            if (pn.getMa_pn().equals(ma_pn)) {
                return pn;
            }
        }
        return null;
    }

    public boolean addPhieuNhap(String ma_pn, String ma_nv, String ma_ncc, Date ngay_nhap, double tongTien) {
        try {
            PhieunhapDTO phieunhap = new PhieunhapDTO();
            phieunhap.setMa_pn(ma_pn);
            phieunhap.setMa_nhan_vien(ma_nv);
            phieunhap.setMa_ncc(ma_ncc);
            phieunhap.setNgayNhap(ngay_nhap);
            phieunhap.setTongTien(tongTien);

            // Thêm vào database
            boolean result = phieunhapDAO.add(phieunhap);

            // Nếu thành công, thêm vào danh sách
            if (result) {
                listPhieuNhap.add(phieunhap);
                System.out.println("Đã thêm phiếu nhập: " + ma_pn + " với tổng tiền: " + tongTien);
            } else {
                System.out.println("Thêm phiếu nhập thất bại: " + ma_pn);
            }

            return result;
        } catch (Exception e) {
            System.out.println("Lỗi trong PhieunhapBUS.addPhieuNhap: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // Keep the old method for backward compatibility
    public boolean addPhieuNhap(String ma_pn, String ma_nv, String ma_ncc, Date ngay_nhap) {
        return addPhieuNhap(ma_pn, ma_nv, ma_ncc, ngay_nhap, 0);
    }

    public boolean updatePhieuNhap(PhieunhapDTO phieunhap) {
        try {
            // Update in database
            boolean result = phieunhapDAO.update(phieunhap);

            // If successful, update in the list
            if (result) {
                for (int i = 0; i < listPhieuNhap.size(); i++) {
                    if (listPhieuNhap.get(i).getMa_pn().equals(phieunhap.getMa_pn())) {
                        listPhieuNhap.set(i, phieunhap);
                        break;
                    }
                }
            }

            return result;
        } catch (Exception e) {
            System.out.println("Error in PhieunhapBUS.updatePhieuNhap: " + e.getMessage());
            return false;
        }
    }

    public boolean deletePhieuNhap(String ma_pn) {
        try {
            // Delete from database
            boolean result = phieunhapDAO.delete(ma_pn);

            // If successful, remove from the list
            if (result) {
                listPhieuNhap.removeIf(pn -> pn.getMa_pn().equals(ma_pn));
            }

            return result;
        } catch (Exception e) {
            System.out.println("Error in PhieunhapBUS.deletePhieuNhap: " + e.getMessage());
            return false;
        }
    }

    public String generateNewMaPN() {
        // Format: PN + current date (yyyyMMdd) + random 3-digit number
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String dateStr = dateFormat.format(new Date());

        // Generate random 3-digit number
        Random random = new Random();
        int randomNum = random.nextInt(900) + 100; // Generates a number between 100 and 999

        // Combine to create receipt ID
        String receiptID = "PN" + dateStr + randomNum;

        return receiptID;
    }
}
