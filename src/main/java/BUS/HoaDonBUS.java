/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.HoaDonDAO;
import DTO.HoaDonDTO;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;

/**
 *
 * @author mrben
 */
public class HoaDonBUS {
    private ArrayList<HoaDonDTO> listHoaDon;
    private HoaDonDAO hoaDonDAO;

    public HoaDonBUS() {
        hoaDonDAO = new HoaDonDAO();
        listHoaDon = hoaDonDAO.getAllHoaDon();
    }

    public ArrayList<HoaDonDTO> getListHoaDon() {
        return listHoaDon;
    }

    public HoaDonDTO getHoaDonById(String maHD) {
        for (HoaDonDTO hd : listHoaDon) {
            if (hd.getMa_hd().equals(maHD)) {
                return hd;
            }
        }
        return null;
    }

    /**
     * Adds a new invoice with the specified details
     * 
     * @param maHoaDon   The invoice ID
     * @param maNV       The employee ID
     * @param maKH       The customer ID
     * @param ngayLapStr The date in string format (yyyy-MM-dd)
     * @param tongTien   The total amount
     * @return true if successful, false otherwise
     */
    public boolean addHoaDon(String maHoaDon, String maNV, String maKH, String ngayLapStr, float tongTien) {
        try {
            // Create a new invoice object
            HoaDonDTO hd = new HoaDonDTO();

            // Set the invoice details
            hd.setMa_hd(maHoaDon);
            hd.setMa_nhan_vien(maNV);
            hd.setMa_khach_hang(maKH);

            // Parse the date string to a Date object
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date ngayLap = sdf.parse(ngayLapStr);
            hd.setNgay_lap(ngayLap);

            hd.setTong_tien(tongTien);

            // Add to database
            boolean result = hoaDonDAO.add(hd);

            // If successful, add to the list
            if (result) {
                listHoaDon.add(hd);
            }

            return result;
        } catch (ParseException e) {
            System.out.println("Error parsing date in HoaDonBUS.addHoaDon: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Error in HoaDonBUS.addHoaDon: " + e.getMessage());
            return false;
        }
    }

    // Keep the existing addHoaDon() method for backward compatibility
    public boolean addHoaDon() {
        try {
            // Generate a new invoice ID
            String maHD = hoaDonDAO.generateNewMaHD();

            // Create a new invoice object with default values
            HoaDonDTO hd = new HoaDonDTO();
            hd.setMa_hd(maHD);
            hd.setMa_nhan_vien("NV001"); // Default employee
            hd.setMa_khach_hang("KH001"); // Default customer
            hd.setNgay_lap(new Date()); // Current date
            hd.setTong_tien(0); // Default total

            // Add to database
            boolean result = hoaDonDAO.add(hd);

            // If successful, add to the list
            if (result) {
                listHoaDon.add(hd);
            }

            return result;
        } catch (Exception e) {
            System.out.println("Error in HoaDonBUS.addHoaDon: " + e.getMessage());
            return false;
        }
    }

    public boolean updateHoaDon(String maHD, String maNV, String maKH, Date ngayLap, double tongTien) {
        try {
            // Tìm hóa đơn cần cập nhật
            HoaDonDTO hd = null;
            for (HoaDonDTO item : listHoaDon) {
                if (item.getMa_hd().equals(maHD)) {
                    hd = item;
                    break;
                }
            }

            if (hd == null) {
                return false;
            }

            // Cập nhật thông tin
            hd.setMa_nhan_vien(maNV);
            hd.setMa_khach_hang(maKH);
            hd.setNgay_lap(ngayLap);
            hd.setTong_tien(tongTien);

            // Cập nhật vào database
            return hoaDonDAO.update(hd);
        } catch (Exception e) {
            System.out.println("Error in HoaDonBUS.updateHoaDon: " + e.getMessage());
            return false;
        }
    }

    public boolean deleteHoaDon(String maHD) {
        try {
            // Xóa khỏi database
            boolean result = hoaDonDAO.delete(maHD);

            // Nếu thành công, xóa khỏi danh sách
            if (result) {
                listHoaDon.removeIf(hd -> hd.getMa_hd().equals(maHD));
            }

            return result;
        } catch (Exception e) {
            System.out.println("Error in HoaDonBUS.deleteHoaDon: " + e.getMessage());
            return false;
        }
    }

    public String generateNewMaHD() {
        return hoaDonDAO.generateNewMaHD();
    }

    public void refreshData() {
        listHoaDon = hoaDonDAO.getAllHoaDon();
    }

    // Các phương thức lọc dữ liệu
    public ArrayList<HoaDonDTO> searchHoaDonByMaHD(String maHD) {
        ArrayList<HoaDonDTO> result = new ArrayList<>();

        for (HoaDonDTO hd : listHoaDon) {
            if (hd.getMa_hd().toLowerCase().contains(maHD.toLowerCase())) {
                result.add(hd);
            }
        }

        return result;
    }

    public ArrayList<HoaDonDTO> searchHoaDonByMaNV(String maNV) {
        ArrayList<HoaDonDTO> result = new ArrayList<>();

        for (HoaDonDTO hd : listHoaDon) {
            if (hd.getMa_nhan_vien().toLowerCase().contains(maNV.toLowerCase())) {
                result.add(hd);
            }
        }

        return result;
    }

    public ArrayList<HoaDonDTO> searchHoaDonByMaKH(String maKH) {
        ArrayList<HoaDonDTO> result = new ArrayList<>();

        for (HoaDonDTO hd : listHoaDon) {
            if (hd.getMa_khach_hang().toLowerCase().contains(maKH.toLowerCase())) {
                result.add(hd);
            }
        }

        return result;
    }

    public ArrayList<HoaDonDTO> searchHoaDonByDate(Date fromDate, Date toDate) {
        ArrayList<HoaDonDTO> result = new ArrayList<>();

        for (HoaDonDTO hd : listHoaDon) {
            Date ngayLap = hd.getNgay_lap();
            if (ngayLap.compareTo(fromDate) >= 0 && ngayLap.compareTo(toDate) <= 0) {
                result.add(hd);
            }
        }

        return result;
    }
}
