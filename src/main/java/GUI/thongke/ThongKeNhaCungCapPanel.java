package GUI.thongke;

import BUS.ThongKeBUS;
import DTO.ThongKeNhaCungCapDTO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.SwingConstants;

public class ThongKeNhaCungCapPanel extends JPanel {
    private ThongKeBUS thongKeBUS;
    private JButton btnThongKe;
    private JTable tblKetQua;
    private JFormattedTextField txtTuNgay, txtDenNgay;
    private JComboBox<String> cbSapXep;
    private JCheckBox chkTop10;

    public ThongKeNhaCungCapPanel(ThongKeBUS thongKeBUS) {
        this.thongKeBUS = thongKeBUS;
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout());
        
        // Panel chọn điều kiện
        JPanel panelChon = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        
        // Dòng 1: Chọn khoảng thời gian
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelChon.add(new JLabel("Từ ngày:"), gbc);
        
        gbc.gridx = 1;
        txtTuNgay = new JFormattedTextField(new SimpleDateFormat("dd/MM/yyyy"));
        txtTuNgay.setColumns(15);
        txtTuNgay.setValue(new Date());
        panelChon.add(txtTuNgay, gbc);
        
        gbc.gridx = 2;
        panelChon.add(new JLabel("Đến ngày:"), gbc);
        
        gbc.gridx = 3;
        txtDenNgay = new JFormattedTextField(new SimpleDateFormat("dd/MM/yyyy"));
        txtDenNgay.setColumns(15);
        txtDenNgay.setValue(new Date());
        panelChon.add(txtDenNgay, gbc);
        
        // Dòng 2: Các tùy chọn
        gbc.gridx = 0;
        gbc.gridy = 1;
        panelChon.add(new JLabel("Sắp xếp theo:"), gbc);
        
        gbc.gridx = 1;
        cbSapXep = new JComboBox<>(new String[]{"Tổng tiền (giảm dần)", "Số lượng (giảm dần)", "Tên NCC (A-Z)"});
        panelChon.add(cbSapXep, gbc);
        
        gbc.gridx = 2;
        chkTop10 = new JCheckBox("Chỉ hiển thị top 10");
        panelChon.add(chkTop10, gbc);
        
        gbc.gridx = 3;
        btnThongKe = new JButton("Thống kê");
        btnThongKe.addActionListener(this::thongKeNhaCungCap);
        panelChon.add(btnThongKe, gbc);
        
        add(panelChon, BorderLayout.NORTH);
        
        // Bảng kết quả
        tblKetQua = new JTable();
        JScrollPane scrollPane = new JScrollPane(tblKetQua);
        add(scrollPane, BorderLayout.CENTER);
        
        // Nút xuất Excel
       
        
        JPanel panelSouth = new JPanel(new FlowLayout(FlowLayout.RIGHT));
       
        add(panelSouth, BorderLayout.SOUTH);
    }

   private void thongKeNhaCungCap(ActionEvent e) {
    try {
        Date tuNgay = (Date) txtTuNgay.getValue();
        Date denNgay = (Date) txtDenNgay.getValue();
        
        ArrayList<ThongKeNhaCungCapDTO> dsThongKe = thongKeBUS.getThongKeNhaCungCap(tuNgay, denNgay);
        
        // Sắp xếp dữ liệu
        switch (cbSapXep.getSelectedIndex()) {
            case 0: // Tổng tiền giảm dần
                dsThongKe.sort((a, b) -> Long.compare(b.getTongtien(), a.getTongtien()));
                break;
            case 1: // Số lượng giảm dần
                dsThongKe.sort((a, b) -> Integer.compare(b.getSoluong(), a.getSoluong()));
                break;
            case 2: // Tên A-Z
                dsThongKe.sort((a, b) -> a.getTenNhaCungCap().compareToIgnoreCase(b.getTenNhaCungCap()));
                break;
        }
        
        // Lọc top 10 nếu được chọn
        if (chkTop10.isSelected() && dsThongKe.size() > 10) {
            dsThongKe = new ArrayList<>(dsThongKe.subList(0, 10));
        }
        
        // Tạo model cho bảng
        String[] columnNames = {"Mã NCC", "Tên NCC", "Số Lượng Đơn", "Tổng Tiền"};
        Object[][] data = new Object[dsThongKe.size()][4];
        
        for (int i = 0; i < dsThongKe.size(); i++) {
            ThongKeNhaCungCapDTO tk = dsThongKe.get(i);
            data[i][0] = tk.getMaNhaCungCap();
            data[i][1] = tk.getTenNhaCungCap();
            data[i][2] = tk.getSoluong();
            data[i][3] = tk.getTongtien();
        }
        
        tblKetQua.setModel(new javax.swing.table.DefaultTableModel(
            data, columnNames
        ) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnIndex == 0 ? Integer.class : 
                       columnIndex == 2 ? Integer.class : 
                       columnIndex == 3 ? Long.class : String.class;
            }
        });
        
        // Sửa lỗi: Tạo renderer căn phải cho cột số
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
        
        tblKetQua.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);
        tblKetQua.getColumnModel().getColumn(3).setCellRenderer(rightRenderer);
        
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(this, "Lỗi: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
    }
}

    private void xuatExcel(ActionEvent e) {
        // Triển khai chức năng xuất Excel tại đây
        JOptionPane.showMessageDialog(this, "Chức năng xuất Excel sẽ được triển khai sau");
    }
    
    // Lớp để căn phải số
    private static class RightAlignRenderer extends DefaultTableCellRenderer {
        public RightAlignRenderer() {
            setHorizontalAlignment(SwingConstants.RIGHT);
        }
    }
}