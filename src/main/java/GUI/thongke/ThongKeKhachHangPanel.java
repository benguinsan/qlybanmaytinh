package GUI.thongke;

import BUS.ThongKeBUS;
import DTO.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ThongKeKhachHangPanel extends JPanel {
    private ThongKeBUS thongKeBUS;
    private JButton btnThongKe;
    private JTable tblKetQua;
    private JFormattedTextField txtTuNgay, txtDenNgay;

    public ThongKeKhachHangPanel(ThongKeBUS thongKeBUS) {
        this.thongKeBUS = thongKeBUS;
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout());
        
        // Panel chọn ngày
        JPanel panelChon = new JPanel(new FlowLayout());
        panelChon.add(new JLabel("Từ ngày:"));
        
        txtTuNgay = new JFormattedTextField(new SimpleDateFormat("dd/MM/yyyy"));
        txtTuNgay.setColumns(10);
        txtTuNgay.setValue(new Date());
        panelChon.add(txtTuNgay);
        
        panelChon.add(new JLabel("Đến ngày:"));
        
        txtDenNgay = new JFormattedTextField(new SimpleDateFormat("dd/MM/yyyy"));
        txtDenNgay.setColumns(10);
        txtDenNgay.setValue(new Date());
        panelChon.add(txtDenNgay);
        
        btnThongKe = new JButton("Thống kê");
        btnThongKe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                thongKeKhachHang();
            }
        });
        panelChon.add(btnThongKe);
        
        add(panelChon, BorderLayout.NORTH);
        
        // Bảng kết quả
        tblKetQua = new JTable();
        JScrollPane scrollPane = new JScrollPane(tblKetQua);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void thongKeKhachHang() {
        try {
            Date tuNgay = (Date) txtTuNgay.getValue();
            Date denNgay = (Date) txtDenNgay.getValue();
            
            ArrayList<ThongKeKhachHangDTO> dsThongKe = thongKeBUS.getThongKeKhachHang(tuNgay, denNgay);
            
            // Tạo model cho bảng
            String[] columnNames = {"Mã KH", "Tên KH", "Số Lượng Phiếu", "Tổng Tiền"};
            Object[][] data = new Object[dsThongKe.size()][4];
            
            for (int i = 0; i < dsThongKe.size(); i++) {
                ThongKeKhachHangDTO tk = dsThongKe.get(i);
                data[i][0] = tk.getMaKhachHang();
                data[i][1] = tk.getTenKhachHang();
                data[i][2] = tk.getSoLuongPhieu();
                data[i][3] = tk.getTongTien();
            }
            
            tblKetQua.setModel(new javax.swing.table.DefaultTableModel(
                data, columnNames
            ));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Lỗi: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
}