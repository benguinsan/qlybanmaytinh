/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.LoaisanphamDAO;
import DTO.LoaisanphamDTO;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author mrben
 */
public class LoaisanphamBUS {
    private LoaisanphamDAO loaisanphamDAO;

    public LoaisanphamBUS() {
        loaisanphamDAO = new LoaisanphamDAO();
    }

    public ArrayList<LoaisanphamDTO> getAllLoaisanpham() {
        return loaisanphamDAO.getAllLoaiSanPham();
    }

    public LoaisanphamDTO getLoaisanphamByMa(String maLoai) {
        return loaisanphamDAO.getLoaisanphamByMa(maLoai);
    }

    public boolean addLoaisanpham(String maLoai, String tenLoai, String moTa) {
        // Kiểm tra dữ liệu đầu vào
        if (maLoai == null || maLoai.trim().isEmpty()) {
            return false;
        }
        if (tenLoai == null || tenLoai.trim().isEmpty()) {
            return false;
        }

        // Kiểm tra mã loại đã tồn tại chưa
        if (loaisanphamDAO.getLoaisanphamByMa(maLoai) != null) {
            return false;
        }

        // Tạo đối tượng DTO mới
        LoaisanphamDTO loai = new LoaisanphamDTO();
        loai.setMa_loai(maLoai);
        loai.setTen_loai(tenLoai);
        loai.setMo_ta(moTa);
        loai.setCreated_at(new Date());

        // Gọi DAO để thêm vào database
        return loaisanphamDAO.Add(loai);
    }


    public boolean updateLoaisanpham(String maLoai, String tenLoai, String moTa) {
        // Kiểm tra dữ liệu đầu vào
        if (maLoai == null || maLoai.trim().isEmpty()) {
            return false;
        }
        if (tenLoai == null || tenLoai.trim().isEmpty()) {
            return false;
        }

        // Kiểm tra mã loại có tồn tại không
        LoaisanphamDTO existingLoai = loaisanphamDAO.getLoaisanphamByMa(maLoai);
        if (existingLoai == null) {
            return false;
        }

        // Cập nhật thông tin
        existingLoai.setTen_loai(tenLoai);
        existingLoai.setMo_ta(moTa);

        // Gọi DAO để cập nhật vào database
        return loaisanphamDAO.Set(existingLoai);
    }


    public boolean deleteLoaisanpham(String maLoai) {
       
        // Kiểm tra mã loại có tồn tại không
        LoaisanphamDTO existingLoai = loaisanphamDAO.getLoaisanphamByMa(maLoai);
        if (existingLoai == null) {
            return false;
        }

        // Gọi DAO để xóa khỏi database
        return loaisanphamDAO.Delete(maLoai);
    }

    public boolean isLoaisanphamExist(String maLoai) {
        return loaisanphamDAO.getLoaisanphamByMa(maLoai) != null;
    }

    
}
