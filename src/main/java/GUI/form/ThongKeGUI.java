package GUI.form;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;

import BUS.ThongKeBUS;
import GUI.thongke.ThongKeDoanhThuPanel;
import GUI.thongke.ThongKeKhachHangPanel;
import GUI.thongke.ThongKeNhaCungCapPanel;
import GUI.thongke.ThongKeTonKhoPanel;
import javax.swing.JPanel;

public class ThongKeGUI extends JPanel {
    private ThongKeBUS thongKeBUS;
    private JTabbedPane tabbedPane;

    public ThongKeGUI() {
        thongKeBUS = new ThongKeBUS();
        initComponents();
    }

    private void initComponents() {
        setLayout(new java.awt.BorderLayout());
        tabbedPane = new JTabbedPane();
        
        // Thêm các tab thống kê
        tabbedPane.addTab("Doanh Thu", new ThongKeDoanhThuPanel(thongKeBUS));
        tabbedPane.addTab("Khách Hàng", new ThongKeKhachHangPanel(thongKeBUS));
           tabbedPane.addTab("Nhà Cung Cấp", new ThongKeNhaCungCapPanel(thongKeBUS));
        tabbedPane.addTab("Tồn Kho", new ThongKeTonKhoPanel(thongKeBUS));
        
        add(tabbedPane);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ThongKeGUI().setVisible(true);
        });
    }
}