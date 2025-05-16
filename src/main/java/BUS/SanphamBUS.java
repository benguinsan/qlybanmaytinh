/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DTO.SanphamDTO;
import DAO.SanphamDAO;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author mrben
 */
public class SanphamBUS {
    private ArrayList<SanphamDTO> listSanpham;
    private SanphamDAO sanphamDAO;

    public SanphamBUS() {
        sanphamDAO = new SanphamDAO();
        ListSanpham();
    }

    public void ListSanpham() {
        listSanpham = new ArrayList<>();
        listSanpham = sanphamDAO.getAllSanpham();
    }

    public ArrayList<SanphamDTO> getList() {
        return this.listSanpham;
    }

    public boolean AddSanpham(SanphamDTO sp) {

        if (sp.getMa_sp().trim().equals("") ||
                sp.getTenSP().trim().equals("") ||
                sp.getMa_loai().trim().equals("")) {
            return false;
        }

        // Kiểm tra mã sản phẩm đã tồn tại chưa
        if (sanphamDAO.getSanphamById(sp.getMa_sp()) != null) {
            return false;
        }

        // Đặt ngày tạo là ngày hiện tại nếu chưa có
        if (sp.getCreated_at() == null) {
            sp.setCreated_at(new Date());
        }

        if (sanphamDAO.Add(sp)) {
            this.listSanpham.add(sp);
            return true;
        }
        return false;
    }

    public SanphamDTO getSanphamById(String ma_sp) {
        for (SanphamDTO sp : this.listSanpham) {
            if (sp.getMa_sp().equals(ma_sp)) {
                return sp;
            }
        }
        return null;
    }

    public boolean SetSanpham(SanphamDTO sp) {
        if (sp.getMa_sp().trim().equals("") ||
                sp.getTenSP().trim().equals("") ||
                sp.getMa_loai().trim().equals("")) {
            return false;
        }

        if (sanphamDAO.Set(sp)) {
            for (int i = 0; i < this.listSanpham.size(); i++) {
                if (this.listSanpham.get(i).getMa_sp().equals(sp.getMa_sp())) {
                    this.listSanpham.set(i, sp);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean DeleteSanpham(String ma_sp) {
        if (sanphamDAO.Delete(ma_sp)) {
            for (int i = 0; i < this.listSanpham.size(); i++) {
                if (this.listSanpham.get(i).getMa_sp().equals(ma_sp)) {
                    this.listSanpham.remove(i);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Updates the inventory quantity of a product
     * 
     * @param ma_sp          The product ID
     * @param quantityChange The quantity to add (positive) or subtract (negative)
     * @return true if successful, false otherwise
     */
    public boolean updateInventory(String ma_sp, int quantityChange) {
        try {
            // Find the product
            SanphamDTO product = null;
            for (SanphamDTO sp : listSanpham) {
                if (sp.getMa_sp().equals(ma_sp)) {
                    product = sp;
                    break;
                }
            }

            if (product == null) {
                System.out.println("Product not found: " + ma_sp);
                return false;
            }

            // Update the quantity
            int currentQuantity = product.getSo_luong_ton();
            int newQuantity = currentQuantity + quantityChange;

            // Ensure quantity doesn't go below zero
            if (newQuantity < 0) {
                System.out.println("Error: Inventory would go below zero for product " + ma_sp);
                return false;
            }

            // Update the product in memory
            product.setSo_luong_ton(newQuantity);

            // Update the product in database
            boolean result = sanphamDAO.Set(product);

            if (result) {
                System.out.println("Updated inventory for " + ma_sp + ": " + currentQuantity + " -> " + newQuantity);
            }

            return result;
        } catch (Exception e) {
            System.out.println("Error in SanphamBUS.updateInventory: " + e.getMessage());
            return false;
        }
    }

}
