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
import java.util.Calendar;
import java.util.Date;

public class ThongKeDoanhThuPanel extends JPanel {
    private ThongKeBUS thongKeBUS;
    private JTabbedPane tabbedPane;

    public ThongKeDoanhThuPanel(ThongKeBUS thongKeBUS) {
        this.thongKeBUS = thongKeBUS;
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout());
        
        tabbedPane = new JTabbedPane();
        
        // Add tabs for different statistics views
        tabbedPane.addTab("Theo Năm", new YearlyStatsPanel(thongKeBUS));
        tabbedPane.addTab("Theo Tháng", new MonthlyStatsPanel(thongKeBUS));
        tabbedPane.addTab("Khoảng Thời Gian", new DateRangeStatsPanel(thongKeBUS));
        
        add(tabbedPane, BorderLayout.CENTER);
    }

    // Panel for yearly statistics
    class YearlyStatsPanel extends JPanel {
        private JComboBox<Integer> cbYear;
        private JButton btnShowAll, btnShowSelected;
        private JTable tblResult;
        private DefaultTableModel tableModel;

        public YearlyStatsPanel(ThongKeBUS thongKeBUS) {
            setLayout(new BorderLayout());
            
            // Control panel
            JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            
            // Year selection
            controlPanel.add(new JLabel("Năm:"));
            cbYear = new JComboBox<>();
            ArrayList<Integer> years = thongKeBUS.getDanhSachNam();
            for (Integer year : years) {
                cbYear.addItem(year);
            }
            controlPanel.add(cbYear);
            
            // Buttons
            btnShowSelected = new JButton("Xem năm được chọn");
            btnShowSelected.addActionListener(e -> showYearlyStats((int) cbYear.getSelectedItem()));
            controlPanel.add(btnShowSelected);
            
            btnShowAll = new JButton("Xem tất cả các năm");
            btnShowAll.addActionListener(e -> showAllYearlyStats());
            controlPanel.add(btnShowAll);
            
            add(controlPanel, BorderLayout.NORTH);
            
            // Results table
            tableModel = new DefaultTableModel(
                new Object[]{"Năm", "Vốn", "Doanh thu", "Lợi nhuận"}, 0
            );
            tblResult = new JTable(tableModel);
            add(new JScrollPane(tblResult), BorderLayout.CENTER);
        }

        private void showYearlyStats(int year) {
            ArrayList<ThongKeDoanhThuDTO> stats = thongKeBUS.getThongKeDoanhThu("year", year);
            updateTable(stats);
        }

        private void showAllYearlyStats() {
            ArrayList<ThongKeDoanhThuDTO> allStats = new ArrayList<>();
            for (int i = 0; i < cbYear.getItemCount(); i++) {
                int year = cbYear.getItemAt(i);
                allStats.addAll(thongKeBUS.getThongKeDoanhThu("year", year));
            }
            updateTable(allStats);
        }

        private void updateTable(ArrayList<ThongKeDoanhThuDTO> stats) {
            tableModel.setRowCount(0);
            for (ThongKeDoanhThuDTO stat : stats) {
                tableModel.addRow(new Object[]{
                    stat.getThoigian(),
                    stat.getVon(),
                    stat.getDoanhthu(),
                    stat.getLoinhuan()
                });
            }
        }
    }

    // Panel for monthly statistics
   class MonthlyStatsPanel extends JPanel {
    private JComboBox<Integer> cbYear;
    private JButton btnShowStats;
    private JTable tblResult;
    private DefaultTableModel tableModel;
    private JLabel lblTotalRevenue, lblTotalProfit;
    private JPanel summaryPanel;

    public MonthlyStatsPanel(ThongKeBUS thongKeBUS) {
        setLayout(new BorderLayout());
        
        // Control panel
        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        
        // Year selection
        controlPanel.add(new JLabel("Năm:"));
        cbYear = new JComboBox<>();
        ArrayList<Integer> years = thongKeBUS.getDanhSachNam();
        for (Integer year : years) {
            cbYear.addItem(year);
        }
        controlPanel.add(cbYear);
        
        // Button to show stats
        btnShowStats = new JButton("Xem thống kê");
        btnShowStats.addActionListener(e -> showMonthlyStats());
        controlPanel.add(btnShowStats);
        
        add(controlPanel, BorderLayout.NORTH);
        
        // Results table
        tableModel = new DefaultTableModel(
            new Object[]{"Tháng", "Vốn", "Doanh thu", "Lợi nhuận"}, 0
        ) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 0) return Integer.class; // Tháng
                return Long.class; // Các cột số liệu
            }
        };
        
        tblResult = new JTable(tableModel);
        tblResult.setAutoCreateRowSorter(true);
        JScrollPane scrollPane = new JScrollPane(tblResult);
        add(scrollPane, BorderLayout.CENTER);
        
        // Summary panel at bottom
        summaryPanel = new JPanel(new GridLayout(1, 2));
        lblTotalRevenue = new JLabel("Tổng doanh thu: 0");
        lblTotalProfit = new JLabel("Tổng lợi nhuận: 0");
        summaryPanel.add(lblTotalRevenue);
        summaryPanel.add(lblTotalProfit);
        add(summaryPanel, BorderLayout.SOUTH);
        
        // Show current year stats by default
        cbYear.setSelectedItem(Calendar.getInstance().get(Calendar.YEAR));
        showMonthlyStats();
    }

    private void showMonthlyStats() {
        try {
            int selectedYear = (int) cbYear.getSelectedItem();
            ArrayList<ThongKeTheoThangDTO> stats = thongKeBUS.getThongKeTheoThang(selectedYear);
            
            // Update table
            tableModel.setRowCount(0);
            long totalRevenue = 0;
            long totalProfit = 0;
            
            // Fill data for all 12 months
            for (int month = 1; month <= 12; month++) {
                boolean found = false;
                
                // Find data for this month
                for (ThongKeTheoThangDTO stat : stats) {
                    if (stat.getThang() == month) {
                        tableModel.addRow(new Object[]{
                            month,
                            stat.getChiphi(),
                            stat.getDoanhthu(),
                            stat.getLoinhuan()
                        });
                        totalRevenue += stat.getDoanhthu();
                        totalProfit += stat.getLoinhuan();
                        found = true;
                        break;
                    }
                }
                
                // Add empty row if no data for this month
                if (!found) {
                    tableModel.addRow(new Object[]{month, 0, 0, 0});
                }
            }
            
            // Update summary
            lblTotalRevenue.setText(String.format("Tổng doanh thu: %,d", totalRevenue));
            lblTotalProfit.setText(String.format("Tổng lợi nhuận: %,d", totalProfit));
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, 
                "Lỗi khi tải dữ liệu: " + ex.getMessage(), 
                "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
}
    // Panel for date range statistics
    class DateRangeStatsPanel extends JPanel {
        private JFormattedTextField txtFromDate, txtToDate;
        private JButton btnShowStats;
        private JTable tblResult;
        private DefaultTableModel tableModel;

        public DateRangeStatsPanel(ThongKeBUS thongKeBUS) {
            setLayout(new BorderLayout());
            
            // Control panel
            JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            
            // Date selection
            controlPanel.add(new JLabel("Từ ngày:"));
            txtFromDate = new JFormattedTextField(new SimpleDateFormat("dd/MM/yyyy"));
            txtFromDate.setColumns(10);
            txtFromDate.setValue(new Date());
            controlPanel.add(txtFromDate);
            
            controlPanel.add(new JLabel("Đến ngày:"));
            txtToDate = new JFormattedTextField(new SimpleDateFormat("dd/MM/yyyy"));
            txtToDate.setColumns(10);
            txtToDate.setValue(new Date());
            controlPanel.add(txtToDate);
            
            // Button
            btnShowStats = new JButton("Thống kê");
            btnShowStats.addActionListener(e -> showDateRangeStats());
            controlPanel.add(btnShowStats);
            
            add(controlPanel, BorderLayout.NORTH);
            
            // Results table
            tableModel = new DefaultTableModel(
                new Object[]{"Vốn", "Doanh thu", "Lợi nhuận"}, 0
            );
            tblResult = new JTable(tableModel);
            add(new JScrollPane(tblResult), BorderLayout.CENTER);
        }

        private void showDateRangeStats() {
            try {
                Date fromDate = (Date) txtFromDate.getValue();
                Date toDate = (Date) txtToDate.getValue();
                
                ThongKeDoanhThuDTO stats = thongKeBUS.getThongKeTheoKhoangThoiGian(fromDate, toDate);
                
                tableModel.setRowCount(0);
                tableModel.addRow(new Object[]{
                    stats.getVon(),
                    stats.getDoanhthu(),
                    stats.getLoinhuan()
                });
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, 
                    "Lỗi khi thống kê: " + ex.getMessage(), 
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}