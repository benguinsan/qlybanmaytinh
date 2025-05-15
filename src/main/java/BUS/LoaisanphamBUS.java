package BUS;

import DTO.LoaisanphamDTO;
import DAO.LoaisanphamDAO;
import java.util.ArrayList;

public class LoaisanphamBUS {
    private ArrayList<LoaisanphamDTO> listLoaisanpham;
    private LoaisanphamDAO loaisanphamDAO;

    public LoaisanphamBUS() {
        loaisanphamDAO = new LoaisanphamDAO();
        ListLoaisanpham();
    }

    // Load danh sách loại sản phẩm từ DB
    public void ListLoaisanpham() {
        listLoaisanpham = new ArrayList<>();
        listLoaisanpham = loaisanphamDAO.getAllLoaisanpham();
    }

    // Lấy danh sách loại sản phẩm
    public ArrayList<LoaisanphamDTO> getList() {
        return this.listLoaisanpham;
    }

    // Thêm loại sản phẩm
    public boolean AddLoaisanpham(LoaisanphamDTO loai) {
        if (loaisanphamDAO.Add(loai)) {
            this.listLoaisanpham.add(loai);
            return true;
        }
        return false;
    }

    // Cập nhật loại sản phẩm
    public boolean SetLoaisanpham(LoaisanphamDTO loai) {
        if (loaisanphamDAO.Set(loai)) {
            for (int i = 0; i < this.listLoaisanpham.size(); i++) {
                if (this.listLoaisanpham.get(i).getMa_loai().equals(loai.getMa_loai())) {
                    this.listLoaisanpham.set(i, loai);
                    return true;
                }
            }
        }
        return false;
    }

    // Xóa loại sản phẩm
    public boolean DeleteLoaisanpham(String ma_loai) {
        if (loaisanphamDAO.Delete(ma_loai)) {
            for (int i = 0; i < this.listLoaisanpham.size(); i++) {
                if (this.listLoaisanpham.get(i).getMa_loai().equals(ma_loai)) {
                    this.listLoaisanpham.remove(i);
                    return true;
                }
            }
        }
        return false;
    }

    // Tìm loại sản phẩm theo mã
    public LoaisanphamDTO getLoaisanphamByMaLoai(String ma_loai) {
        for (LoaisanphamDTO loai : this.listLoaisanpham) {
            if (loai.getMa_loai().equals(ma_loai)) {
                return loai;
            }
        }
        return null;
    }
}
