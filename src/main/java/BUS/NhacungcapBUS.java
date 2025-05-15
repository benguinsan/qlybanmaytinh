/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.NhacungcapDAO;
import DTO.NhacungcapDTO;

import java.util.Date;
import java.util.List;

/**
 *
 * @author mrben
 */
public class NhacungcapBUS {
    private NhacungcapDAO nhacungcapDAO;

    public NhacungcapBUS() {
        nhacungcapDAO = new NhacungcapDAO();
    }

    public List<NhacungcapDTO> getAllNhacungcap() {
        return nhacungcapDAO.getAllNhacungcap();
    }

    public NhacungcapDTO getNhacungcapByMa(String maNcc) {
        return nhacungcapDAO.getNhacungcapByMa(maNcc);
    }

    public boolean addNhacungcap(String maNcc, String tenNcc, String diaChi, String dienThoai, String email) {
        if (maNcc == null || maNcc.trim().isEmpty()) {
            return false;
        }
        if (tenNcc == null || tenNcc.trim().isEmpty()) {
            return false;
        }
        if (diaChi == null || diaChi.trim().isEmpty()) {
            return false;
        }
        if (dienThoai == null || dienThoai.trim().isEmpty() || !dienThoai.matches("^[0-9]{10}$")) {
            return false;
        }   
        if (email == null || email.trim().isEmpty() || !email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
            return false;
        }
        
        NhacungcapDTO ncc = new NhacungcapDTO();
        ncc.setMa_ncc(maNcc);
        ncc.setTen_ncc(tenNcc);
        ncc.setDia_chi(diaChi);
        ncc.setDien_thoai(dienThoai);
        ncc.setEmail(email);
        ncc.setCreated_at(new Date());

        // Gọi DAO để thêm vào database
        return nhacungcapDAO.Add(ncc);

    }
    
    public boolean updateNhacungcap(String maNcc, String tenNcc, String diaChi, String dienThoai, String email) {
    
        if (tenNcc == null || tenNcc.trim().isEmpty()) {
            return false;
        }
        
        if (diaChi == null || diaChi.trim().isEmpty()) {
            return false;
        }

        if (dienThoai == null || dienThoai.trim().isEmpty() || !dienThoai.matches("^[0-9]{10}$")) {
            return false;
        }

        if (email == null || email.trim().isEmpty() || !email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
            return false;
        }   
        
        NhacungcapDTO existingNcc = nhacungcapDAO.getNhacungcapByMa(maNcc);
        if (existingNcc == null) {
            return false;
        }

        // cập nhật thông tin
        existingNcc.setTen_ncc(tenNcc);
        existingNcc.setDia_chi(diaChi);
        existingNcc.setDien_thoai(dienThoai);
        existingNcc.setEmail(email);

        return nhacungcapDAO.Set(existingNcc);
    }   
    

    public boolean deleteNhacungcap(String maNcc) {
        
        NhacungcapDTO existingNcc = nhacungcapDAO.getNhacungcapByMa(maNcc);
        if (existingNcc == null) {
            return false;
        }

        return nhacungcapDAO.Delete(maNcc);
    }
}
