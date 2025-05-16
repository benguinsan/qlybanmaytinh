/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DTO.ChiTietPhieuNhapDTO;
import java.util.ArrayList;
import DAO.ChiTietPhieuNhapDAO;

/**
 *
 * @author mrben
 */
public class ChiTietPhieuNhapBUS {
    private ArrayList<ChiTietPhieuNhapDTO> listChiTietPhieuNhap;
    private ChiTietPhieuNhapDAO chiTietPhieuNhapDAO;
    private SanphamBUS sanphamBUS;

    public ChiTietPhieuNhapBUS() {
        chiTietPhieuNhapDAO = new ChiTietPhieuNhapDAO();
        sanphamBUS = new SanphamBUS();
        listChiTietPhieuNhap = chiTietPhieuNhapDAO.getAllChiTietPhieuNhap();
    }

    public ArrayList<ChiTietPhieuNhapDTO> getList() {
        return listChiTietPhieuNhap;
    }

    public ArrayList<ChiTietPhieuNhapDTO> getChiTietPhieuNhapByMaPN(String ma_pn) {
        ArrayList<ChiTietPhieuNhapDTO> result = new ArrayList<>();

        for (ChiTietPhieuNhapDTO ctpn : listChiTietPhieuNhap) {
            if (ctpn.getMa_pn().equals(ma_pn)) {
                result.add(ctpn);
            }
        }

        return result;
    }

    public boolean addChiTietPhieuNhap(String ma_pn, String ma_sp, int soLuong, double donGia) {
        try {
            ChiTietPhieuNhapDTO ctpn = new ChiTietPhieuNhapDTO();
            ctpn.setMa_pn(ma_pn);
            ctpn.setMa_sp(ma_sp);
            ctpn.setSoLuong(soLuong);
            ctpn.setDonGia(donGia);

            // Add to database
            boolean result = chiTietPhieuNhapDAO.add(ctpn);

            // If successful, add to the list
            if (result) {
                listChiTietPhieuNhap.add(ctpn);

                // Update product inventory
                sanphamBUS.updateInventory(ma_sp, soLuong);

                System.out.println("Added chi tiet phieu nhap: " + ma_pn + ", " + ma_sp + ", " + soLuong);
            }

            return result;
        } catch (Exception e) {
            System.out.println("Error in ChiTietPhieuNhapBUS.addChiTietPhieuNhap: " + e.getMessage());
            return false;
        }
    }

    public boolean updateChiTietPhieuNhap(ChiTietPhieuNhapDTO ctpn) {
        try {
            // Get the old quantity to adjust inventory
            int oldQuantity = 0;
            for (ChiTietPhieuNhapDTO item : listChiTietPhieuNhap) {
                if (item.getMa_ctpn() == ctpn.getMa_ctpn()) {
                    oldQuantity = item.getSoLuong();
                    break;
                }
            }

            // Update in database
            boolean result = chiTietPhieuNhapDAO.update(ctpn);

            // If successful, update in the list and adjust inventory
            if (result) {
                for (int i = 0; i < listChiTietPhieuNhap.size(); i++) {
                    if (listChiTietPhieuNhap.get(i).getMa_ctpn() == ctpn.getMa_ctpn()) {
                        listChiTietPhieuNhap.set(i, ctpn);

                        // Update product inventory if quantity changed
                        int quantityDiff = ctpn.getSoLuong() - oldQuantity;
                        if (quantityDiff != 0) {
                            sanphamBUS.updateInventory(ctpn.getMa_sp(), quantityDiff);
                        }

                        break;
                    }
                }
            }

            return result;
        } catch (Exception e) {
            System.out.println("Error in ChiTietPhieuNhapBUS.updateChiTietPhieuNhap: " + e.getMessage());
            return false;
        }
    }

    public boolean deleteChiTietPhieuNhap(int ma_ctpn) {
        try {
            // Get the details to adjust inventory
            ChiTietPhieuNhapDTO ctpn = null;
            for (ChiTietPhieuNhapDTO item : listChiTietPhieuNhap) {
                if (item.getMa_ctpn() == ma_ctpn) {
                    ctpn = item;
                    break;
                }
            }

            if (ctpn == null) {
                return false;
            }

            // Delete from database
            boolean result = chiTietPhieuNhapDAO.delete(ma_ctpn);

            // If successful, remove from the list and adjust inventory
            if (result) {
                listChiTietPhieuNhap.removeIf(item -> item.getMa_ctpn() == ma_ctpn);

                // Update product inventory (negative to decrease)
                sanphamBUS.updateInventory(ctpn.getMa_sp(), -ctpn.getSoLuong());
            }

            return result;
        } catch (Exception e) {
            System.out.println("Error in ChiTietPhieuNhapBUS.deleteChiTietPhieuNhap: " + e.getMessage());
            return false;
        }
    }

    public boolean deleteByMaPN(String ma_pn) {
        try {
            // Get all details to adjust inventory
            ArrayList<ChiTietPhieuNhapDTO> toRemove = getChiTietPhieuNhapByMaPN(ma_pn);

            // Delete from database
            boolean result = chiTietPhieuNhapDAO.deleteByMaPN(ma_pn);

            // If successful, remove from the list and adjust inventory
            if (result) {
                // Update inventory for all items being removed
                for (ChiTietPhieuNhapDTO ctpn : toRemove) {
                    sanphamBUS.updateInventory(ctpn.getMa_sp(), -ctpn.getSoLuong());
                }

                // Remove all items with the given ma_pn
                listChiTietPhieuNhap.removeIf(ctpn -> ctpn.getMa_pn().equals(ma_pn));
            }

            return result;
        } catch (Exception e) {
            System.out.println("Error in ChiTietPhieuNhapBUS.deleteByMaPN: " + e.getMessage());
            return false;
        }
    }
}
