package GUI.thongke;

import BUS.ThongKeBUS;
import DTO.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ThongKeTonKhoPanel extends JPanel {
    private ThongKeBUS thongKeBUS;
    private JButton btnThongKe, btnReset;
    private JTable tblKetQua;
    private JFormattedTextField txtTuNgay, txtDenNgay;
    private DefaultTableModel tableModel;

    public ThongKeTonKhoPanel(ThongKeBUS thongKeBUS) {
        this.thongKeBUS = thongKeBUS;
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout());
        
        // Panel chọn ngày
        JPanel panelChon = new JPanel(new FlowLayout(FlowLayout.LEFT));
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
        
        btnThongKe = new JButton("Thống kê", new ImageIcon("images/search.png"));
        btnThongKe.addActionListener(this::thongKeTonKho);
        panelChon.add(btnThongKe);
        
        btnReset = new JButton("Làm mới", new ImageIcon("images/refresh.png"));
        btnReset.addActionListener(this::resetPanel);
        panelChon.add(btnReset);
        
        add(panelChon, BorderLayout.NORTH);
        
        // Bảng kết quả
        String[] columnNames = {"Mã SP", "Phiên Bản", "Tên SP", "RAM", "Chip", "Card", 
                              "Nhập Trong Kỳ", "Xuất Trong Kỳ", "Tồn Cuối Kỳ"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnIndex >= 6 ? Integer.class : String.class;
            }
        };
        
        tblKetQua = new JTable(tableModel);
        tblKetQua.setAutoCreateRowSorter(true);
        JScrollPane scrollPane = new JScrollPane(tblKetQua);
        add(scrollPane, BorderLayout.CENTER);
        
        // Summary panel
        JPanel summaryPanel = new JPanel(new GridLayout(1, 3));
        summaryPanel.add(new JLabel("Tổng số sản phẩm: 0"));
        summaryPanel.add(new JLabel("Tổng nhập: 0"));
        summaryPanel.add(new JLabel("Tổng xuất: 0"));
        add(summaryPanel, BorderLayout.SOUTH);
    }

    private void thongKeTonKho(ActionEvent e) {
        try {
            Date tuNgay = (Date) txtTuNgay.getValue();
            Date denNgay = (Date) txtDenNgay.getValue();
            
            if (tuNgay.after(denNgay)) {
                JOptionPane.showMessageDialog(this, 
                    "Ngày bắt đầu phải trước ngày kết thúc", 
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            ArrayList<ThongKeTonKhoDTO> dsThongKe = thongKeBUS.getThongKeTonKho(tuNgay, denNgay);
            
            // Clear existing data
            tableModel.setRowCount(0);
            
            // Add new data
            int totalImport = 0;
            int totalExport = 0;
            
            for (ThongKeTonKhoDTO tk : dsThongKe) {
                tableModel.addRow(new Object[]{
                    tk.getMasp(),
                    tk.getMaphienbansp(),
                    tk.getTensanpham(),
                    tk.getRam(),
                    tk.getChip(),
                    tk.getCard(),
                    tk.getNhaptrongky(),
                    tk.getXuattrongky(),
                    tk.getToncuoiky()
                });
                
                totalImport += tk.getNhaptrongky();
                totalExport += tk.getXuattrongky();
            }
            
            // Update summary
            JPanel summaryPanel = (JPanel) getComponent(2);
            ((JLabel)summaryPanel.getComponent(0)).setText("Tổng số sản phẩm: " + dsThongKe.size());
            ((JLabel)summaryPanel.getComponent(1)).setText("Tổng nhập: " + totalImport);
            ((JLabel)summaryPanel.getComponent(2)).setText("Tổng xuất: " + totalExport);
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, 
                "Lỗi: " + ex.getMessage(), 
                "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void resetPanel(ActionEvent e) {
        // Reset date fields to current date
        txtTuNgay.setValue(new Date());
        txtDenNgay.setValue(new Date());
        
        // Clear table data
        tableModel.setRowCount(0);
        
        // Reset summary
        JPanel summaryPanel = (JPanel) getComponent(2);
        ((JLabel)summaryPanel.getComponent(0)).setText("Tổng số sản phẩm: 0");
        ((JLabel)summaryPanel.getComponent(1)).setText("Tổng nhập: 0");
        ((JLabel)summaryPanel.getComponent(2)).setText("Tổng xuất: 0");
    }
}