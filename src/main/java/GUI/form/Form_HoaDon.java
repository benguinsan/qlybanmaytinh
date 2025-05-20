/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI.form;

import BUS.HoaDonBUS;
import BUS.NhanvienBUS;
import BUS.KhachhangBUS;
import DTO.HoaDonDTO;
import DTO.NhanvienDTO;
import DTO.KhachhangDTO;
import GUI.component.HoaDon.AddHoaDon;
import java.awt.Color;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;

/**
 *
 * @author mrben
 */
public class Form_HoaDon extends javax.swing.JPanel {
        private HoaDonBUS hoaDonBUS;
        private NhanvienBUS nhanvienBUS;
        private KhachhangBUS khachhangBUS;
        private String selectedMaHoaDon;

        /**
         * Creates new form Form_HoaDon
         */
        public Form_HoaDon() {
                initComponents();
                hoaDonBUS = new HoaDonBUS();
                nhanvienBUS = new NhanvienBUS();
                khachhangBUS = new KhachhangBUS();

                // Khởi tạo combobox
                initComboBoxes();

                // Load dữ liệu vào bảng
                loadHoaDonTable();

                // Thêm sự kiện cho bảng
                tableHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                tableHoaDonClicked(evt);
                        }
                });

                // Thêm sự kiện cho các nút
                btnTimKiem.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnTimKiemActionPerformed(evt);
                        }
                });

                btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnLamMoiActionPerformed(evt);
                        }
                });

                btnChiTiet.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnChiTietActionPerformed(evt);
                        }
                });

                btnXuatExcel.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnXuatExcelActionPerformed(evt);
                        }
                });
        }

        private void initComboBoxes() {
                // Khởi tạo combobox khách hàng
                cboKhachHang.removeAllItems();
                cboKhachHang.addItem("Tất cả");

                ArrayList<KhachhangDTO> listKH = khachhangBUS.getList();
                for (KhachhangDTO kh : listKH) {
                        cboKhachHang.addItem(kh.getMa_khach_hang() + " - " + kh.getHo_ten());
                }

                // Khởi tạo combobox nhân viên
                cboNhanVien.removeAllItems();
                cboNhanVien.addItem("Tất cả");

                ArrayList<NhanvienDTO> listNV = nhanvienBUS.getList();
                for (NhanvienDTO nv : listNV) {
                        cboNhanVien.addItem(nv.getMa_nhan_vien() + " - " + nv.getHo_ten());
                }
        }

        private void tableHoaDonClicked(java.awt.event.MouseEvent evt) {
                int selectedRow = tableHoaDon.getSelectedRow();
                if (selectedRow >= 0) {
                        // Lấy mã hóa đơn từ cột thứ 1 (index 1)
                        selectedMaHoaDon = tableHoaDon.getValueAt(selectedRow, 1).toString();
                        System.out.println("Đã chọn hóa đơn: " + selectedMaHoaDon);
                }
        }

        private void btnTimKiemActionPerformed(java.awt.event.ActionEvent evt) {
                String searchText = txtTimKiem.getText().trim();
                Date searchDate = dateNgayLap.getDate();
                String selectedKhachHang = cboKhachHang.getSelectedItem().toString();
                String selectedNhanVien = cboNhanVien.getSelectedItem().toString();

                // Lấy mã khách hàng và nhân viên từ combobox (nếu có chọn)
                String maKhachHang = null;
                String maNhanVien = null;

                if (!selectedKhachHang.equals("Tất cả")) {
                        maKhachHang = selectedKhachHang.split(" - ")[0];
                }

                if (!selectedNhanVien.equals("Tất cả")) {
                        maNhanVien = selectedNhanVien.split(" - ")[0];
                }

                // Tạo danh sách hóa đơn đã lọc
                ArrayList<HoaDonDTO> filteredList = new ArrayList<>();
                ArrayList<HoaDonDTO> allHoaDon = hoaDonBUS.getListHoaDon();

                // Lọc theo các tiêu chí
                for (HoaDonDTO hoaDon : allHoaDon) {
                        boolean match = true;

                        // Lọc theo từ khóa (tên nhân viên hoặc khách hàng)
                        if (!searchText.isEmpty()) {
                                boolean foundMatch = false;

                                // Tìm theo mã hóa đơn
                                if (hoaDon.getMa_hd().toLowerCase().contains(searchText.toLowerCase())) {
                                        foundMatch = true;
                                }

                                // Tìm theo tên khách hàng
                                if (!foundMatch) {
                                        KhachhangDTO khachHang = khachhangBUS
                                                        .getKhachhangByMaKH(hoaDon.getMa_khach_hang());
                                        if (khachHang != null && khachHang.getHo_ten().toLowerCase()
                                                        .contains(searchText.toLowerCase())) {
                                                foundMatch = true;
                                        }
                                }

                                // Tìm theo tên nhân viên
                                if (!foundMatch) {
                                        NhanvienDTO nhanVien = nhanvienBUS.getNhanvienByMaNV(hoaDon.getMa_nhan_vien());
                                        if (nhanVien != null && nhanVien.getHo_ten().toLowerCase()
                                                        .contains(searchText.toLowerCase())) {
                                                foundMatch = true;
                                        }
                                }

                                if (!foundMatch) {
                                        match = false;
                                }
                        }

                        // Lọc theo ngày lập
                        if (match && searchDate != null) {
                                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                                String searchDateStr = sdf.format(searchDate);
                                String hoaDonDateStr = sdf.format(hoaDon.getNgay_lap());

                                if (!hoaDonDateStr.equals(searchDateStr)) {
                                        match = false;
                                }
                        }

                        // Lọc theo khách hàng
                        if (match && maKhachHang != null && !hoaDon.getMa_khach_hang().equals(maKhachHang)) {
                                match = false;
                        }

                        // Lọc theo nhân viên
                        if (match && maNhanVien != null && !hoaDon.getMa_nhan_vien().equals(maNhanVien)) {
                                match = false;
                        }

                        // Nếu hóa đơn phù hợp với tất cả các tiêu chí, thêm vào danh sách đã lọc
                        if (match) {
                                filteredList.add(hoaDon);
                        }
                }

                // Hiển thị kết quả lọc
                loadHoaDonTable(filteredList);

                // Thông báo kết quả tìm kiếm
                if (filteredList.isEmpty()) {
                        JOptionPane.showMessageDialog(this, "Không tìm thấy hóa đơn nào phù hợp với tiêu chí tìm kiếm",
                                        "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                }
        }

        private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {
                // Reset các trường tìm kiếm
                cboKhachHang.setSelectedIndex(0);
                cboNhanVien.setSelectedIndex(0);
                dateNgayLap.setDate(null);
                txtTimKiem.setText("");

                // Load lại dữ liệu
                hoaDonBUS = new HoaDonBUS();
                loadHoaDonTable();
        }

        private void btnChiTietActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnChiTietActionPerformed
                // Kiểm tra xem đã chọn hóa đơn chưa
                if (selectedMaHoaDon == null || selectedMaHoaDon.isEmpty()) {
                        JOptionPane.showMessageDialog(this, "Vui lòng chọn một hóa đơn để xem chi tiết", "Thông báo",
                                        JOptionPane.WARNING_MESSAGE);
                        return;
                }

                // Lấy thông tin hóa đơn từ BUS
                HoaDonDTO hoaDon = hoaDonBUS.getHoaDonById(selectedMaHoaDon);

                if (hoaDon == null) {
                        JOptionPane.showMessageDialog(this, "Không tìm thấy thông tin hóa đơn", "Lỗi",
                                        JOptionPane.ERROR_MESSAGE);
                        return;
                }

                // Tạo dialog để hiển thị chi tiết hóa đơn
                JDialog dialog = new JDialog();
                dialog.setTitle("Chi tiết hóa đơn: " + selectedMaHoaDon);
                dialog.setModal(true);
                dialog.setResizable(false);

                // Tạo panel chi tiết hóa đơn và thêm vào dialog
                // Giả sử có một class ChiTietHoaDon trong package GUI.component.HoaDon
                GUI.component.HoaDon.ChiTietHoaDon chiTietPanel = new GUI.component.HoaDon.ChiTietHoaDon(
                                selectedMaHoaDon);
                dialog.getContentPane().add(chiTietPanel);

                // Thiết lập thuộc tính cho dialog
                dialog.pack();
                dialog.setLocationRelativeTo(this);

                // Hiển thị dialog
                dialog.setVisible(true);
        }// GEN-LAST:event_btnChiTietActionPerformed

        private void btnHuyHoaDonActionPerformed(java.awt.event.ActionEvent evt) {
                // TODO: Implement cancel invoice functionality
        }

        private void btnXuatExcelActionPerformed(java.awt.event.ActionEvent evt) {
                try {
                        // Tạo workbook mới
                        XSSFWorkbook workbook = new XSSFWorkbook();
                        XSSFSheet sheet = workbook.createSheet("Danh sách hóa đơn");

                        // Tạo font cho header
                        XSSFFont headerFont = workbook.createFont();
                        headerFont.setBold(true);
                        headerFont.setFontHeightInPoints((short) 14);

                        // Tạo cell style cho header
                        XSSFCellStyle headerStyle = workbook.createCellStyle();
                        headerStyle.setFont(headerFont);
                        headerStyle.setAlignment(HorizontalAlignment.CENTER);
                        headerStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
                        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                        headerStyle.setBorderTop(BorderStyle.THIN);
                        headerStyle.setBorderBottom(BorderStyle.THIN);
                        headerStyle.setBorderLeft(BorderStyle.THIN);
                        headerStyle.setBorderRight(BorderStyle.THIN);

                        // Tạo cell style cho dữ liệu
                        XSSFCellStyle dataStyle = workbook.createCellStyle();
                        dataStyle.setBorderTop(BorderStyle.THIN);
                        dataStyle.setBorderBottom(BorderStyle.THIN);
                        dataStyle.setBorderLeft(BorderStyle.THIN);
                        dataStyle.setBorderRight(BorderStyle.THIN);

                        // Tạo cell style cho số tiền
                        XSSFCellStyle currencyStyle = workbook.createCellStyle();
                        currencyStyle.setBorderTop(BorderStyle.THIN);
                        currencyStyle.setBorderBottom(BorderStyle.THIN);
                        currencyStyle.setBorderLeft(BorderStyle.THIN);
                        currencyStyle.setBorderRight(BorderStyle.THIN);
                        currencyStyle.setDataFormat(workbook.createDataFormat().getFormat("#,##0"));

                        // Tạo cell style cho ngày tháng
                        XSSFCellStyle dateStyle = workbook.createCellStyle();
                        dateStyle.setBorderTop(BorderStyle.THIN);
                        dateStyle.setBorderBottom(BorderStyle.THIN);
                        dateStyle.setBorderLeft(BorderStyle.THIN);
                        dateStyle.setBorderRight(BorderStyle.THIN);
                        dateStyle.setDataFormat(workbook.createDataFormat().getFormat("dd/MM/yyyy HH:mm:ss"));

                        // Lấy model của bảng
                        DefaultTableModel model = (DefaultTableModel) tableHoaDon.getModel();

                        // Lấy tên cột từ model
                        int columnCount = model.getColumnCount();
                        String[] columns = new String[columnCount];
                        for (int i = 0; i < columnCount; i++) {
                                columns[i] = model.getColumnName(i);
                        }

                        // Tạo header row
                        Row headerRow = sheet.createRow(0);

                        // Tạo các cell cho header
                        for (int i = 0; i < columns.length; i++) {
                                Cell cell = headerRow.createCell(i);
                                cell.setCellValue(columns[i]);
                                cell.setCellStyle(headerStyle);
                        }

                        // Lấy số lượng dòng từ model
                        int rowCount = model.getRowCount();

                        // Thêm dữ liệu vào sheet
                        for (int i = 0; i < rowCount; i++) {
                                Row row = sheet.createRow(i + 1);

                                for (int j = 0; j < columnCount; j++) {
                                        Cell cell = row.createCell(j);
                                        Object value = model.getValueAt(i, j);

                                        if (value != null) {
                                                // Xử lý các loại dữ liệu khác nhau
                                                if (value instanceof Number) {
                                                        cell.setCellValue(((Number) value).doubleValue());
                                                        if (columns[j].contains("Tổng tiền")
                                                                        || columns[j].contains("Giá")) {
                                                                cell.setCellStyle(currencyStyle);
                                                        } else {
                                                                cell.setCellStyle(dataStyle);
                                                        }
                                                } else if (value instanceof Date) {
                                                        cell.setCellValue((Date) value);
                                                        cell.setCellStyle(dateStyle);
                                                } else {
                                                        String strValue = value.toString();
                                                        // Kiểm tra nếu là cột tiền
                                                        if (columns[j].contains("Tổng tiền")
                                                                        || columns[j].contains("Giá")) {
                                                                try {
                                                                        // Loại bỏ các ký tự không phải số
                                                                        String numericValue = strValue
                                                                                        .replaceAll("[^\\d]", "");
                                                                        if (!numericValue.isEmpty()) {
                                                                                double numValue = Double.parseDouble(
                                                                                                numericValue);
                                                                                cell.setCellValue(numValue);
                                                                                cell.setCellStyle(currencyStyle);
                                                                        } else {
                                                                                cell.setCellValue(strValue);
                                                                                cell.setCellStyle(dataStyle);
                                                                        }
                                                                } catch (NumberFormatException e) {
                                                                        cell.setCellValue(strValue);
                                                                        cell.setCellStyle(dataStyle);
                                                                }
                                                        } else if (columns[j].contains("Ngày")
                                                                        || columns[j].contains("Thời gian")) {
                                                                cell.setCellValue(strValue);
                                                                cell.setCellStyle(dateStyle);
                                                        } else {
                                                                cell.setCellValue(strValue);
                                                                cell.setCellStyle(dataStyle);
                                                        }
                                                }
                                        } else {
                                                cell.setCellValue("");
                                                cell.setCellStyle(dataStyle);
                                        }
                                }
                        }

                        // Auto size các cột
                        for (int i = 0; i < columnCount; i++) {
                                sheet.autoSizeColumn(i);
                        }

                        // Hiển thị hộp thoại chọn nơi lưu file
                        JFileChooser fileChooser = new JFileChooser();
                        fileChooser.setDialogTitle("Lưu file Excel");

                        // Đặt bộ lọc file để chỉ hiển thị file Excel
                        FileNameExtensionFilter filter = new FileNameExtensionFilter("Excel files (*.xlsx)", "xlsx");
                        fileChooser.setFileFilter(filter);

                        // Đặt tên file mặc định
                        fileChooser.setSelectedFile(new File("DanhSachHoaDon.xlsx"));

                        int userSelection = fileChooser.showSaveDialog(this);

                        if (userSelection == JFileChooser.APPROVE_OPTION) {
                                File fileToSave = fileChooser.getSelectedFile();
                                String filePath = fileToSave.getAbsolutePath();

                                // Đảm bảo file có đuôi .xlsx
                                if (!filePath.endsWith(".xlsx")) {
                                        filePath += ".xlsx";
                                        fileToSave = new File(filePath);
                                }

                                // Ghi file
                                try (FileOutputStream outputStream = new FileOutputStream(fileToSave)) {
                                        workbook.write(outputStream);
                                }

                                JOptionPane.showMessageDialog(
                                                this,
                                                "Xuất Excel thành công!\nFile được lưu tại: " + filePath,
                                                "Thông báo",
                                                JOptionPane.INFORMATION_MESSAGE);
                        }

                        // Đóng workbook
                        workbook.close();

                } catch (Exception e) {
                        JOptionPane.showMessageDialog(
                                        this,
                                        "Lỗi khi xuất Excel: " + e.getMessage(),
                                        "Lỗi",
                                        JOptionPane.ERROR_MESSAGE);
                        e.printStackTrace();
                }
        }

        private void loadHoaDonTable(ArrayList<HoaDonDTO> hoaDonList) {
                // Xóa dữ liệu cũ
                DefaultTableModel model = (DefaultTableModel) tableHoaDon.getModel();
                model.setRowCount(0);

                // Định dạng ngày tháng
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

                // Thêm dữ liệu mới
                int stt = 1;
                for (HoaDonDTO hoaDon : hoaDonList) {
                        // Lấy thông tin khách hàng và nhân viên
                        KhachhangDTO khachHang = khachhangBUS.getKhachhangByMaKH(hoaDon.getMa_khach_hang());
                        NhanvienDTO nhanVien = nhanvienBUS.getNhanvienByMaNV(hoaDon.getMa_nhan_vien());

                        String tenKhachHang = (khachHang != null) ? khachHang.getHo_ten() : "Không xác định";
                        String tenNhanVien = (nhanVien != null) ? nhanVien.getHo_ten() : "Không xác định";

                        // Thêm dòng mới vào bảng
                        model.addRow(new Object[] {
                                        Integer.valueOf(stt++), // STT - số nguyên
                                        hoaDon.getMa_hd(), // Mã hóa đơn - chuỗi
                                        hoaDon.getMa_khach_hang() + " - " + tenKhachHang, // Khách hàng - chuỗi
                                        hoaDon.getMa_nhan_vien() + " - " + tenNhanVien, // Nhân viên - chuỗi
                                        dateFormat.format(hoaDon.getNgay_lap()), // Ngày lập - chuỗi đã định dạng
                                        Double.valueOf(hoaDon.getTong_tien()) // Tổng tiền - số thực
                        });
                }

                // Thiết lập renderer cho cột tổng tiền
                DefaultTableCellRenderer currencyRenderer = new DefaultTableCellRenderer() {
                        private final DecimalFormat formatter = new DecimalFormat("#,### VNĐ");

                        @Override
                        public void setValue(Object value) {
                                if (value instanceof Number) {
                                        value = formatter.format(((Number) value).doubleValue());
                                }
                                super.setValue(value);
                        }
                };
                currencyRenderer.setHorizontalAlignment(JLabel.RIGHT);
                tableHoaDon.getColumnModel().getColumn(5).setCellRenderer(currencyRenderer);
        }

        private void loadHoaDonTable() {
                loadHoaDonTable(hoaDonBUS.getListHoaDon());
        }

        /**
         * This method is called from within the constructor to initialize the form.
         * WARNING: Do NOT modify this code. The content of this method is always
         * regenerated by the Form Editor.
         */
        @SuppressWarnings("unchecked")
        // <editor-fold defaultstate="collapsed" desc="Generated
        // <editor-fold defaultstate="collapsed" desc="Generated
        // <editor-fold defaultstate="collapsed" desc="Generated
        // Code">//GEN-BEGIN:initComponents
        private void initComponents() {

                mainPanel2 = new javax.swing.JPanel();
                headerPanel2 = new javax.swing.JPanel();
                titleLabel2 = new javax.swing.JLabel();
                jScrollPane1 = new javax.swing.JScrollPane();
                tableHoaDon = new javax.swing.JTable();
                filterPanel = new javax.swing.JPanel();
                jLabel1 = new javax.swing.JLabel();
                cboKhachHang = new javax.swing.JComboBox<>();
                jLabel2 = new javax.swing.JLabel();
                cboNhanVien = new javax.swing.JComboBox<>();
                jLabel3 = new javax.swing.JLabel();
                dateNgayLap = new com.toedter.calendar.JDateChooser();
                jLabel4 = new javax.swing.JLabel();
                txtTimKiem = new javax.swing.JTextField();
                btnTimKiem = new javax.swing.JButton();
                btnLamMoi = new javax.swing.JButton();
                btnThem = new javax.swing.JButton();
                btnChiTiet = new javax.swing.JButton();
                btnXuatExcel = new javax.swing.JButton();

                mainPanel2.setBackground(new java.awt.Color(255, 255, 255));
                mainPanel2.setPreferredSize(new java.awt.Dimension(752, 448));

                headerPanel2.setBackground(new java.awt.Color(8, 122, 230));
                headerPanel2.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));

                titleLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
                titleLabel2.setForeground(new java.awt.Color(255, 255, 255));
                titleLabel2.setText("HÓA ĐƠN");

                javax.swing.GroupLayout headerPanel2Layout = new javax.swing.GroupLayout(headerPanel2);
                headerPanel2.setLayout(headerPanel2Layout);
                headerPanel2Layout.setHorizontalGroup(
                                headerPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(headerPanel2Layout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addComponent(titleLabel2)
                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)));
                headerPanel2Layout.setVerticalGroup(
                                headerPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(headerPanel2Layout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addComponent(titleLabel2)
                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)));

                tableHoaDon.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                tableHoaDon.setModel(new javax.swing.table.DefaultTableModel(
                                new Object[][] {

                                },
                                new String[] {
                                                "STT", "Mã hóa đơn", "Khách hàng", "Nhân viên bán", "Thời gian",
                                                "Tổng tiền"
                                }) {
                        Class[] types = new Class[] {
                                        java.lang.Integer.class, java.lang.String.class, java.lang.String.class,
                                        java.lang.String.class, java.lang.String.class, java.lang.Double.class
                        };
                        boolean[] canEdit = new boolean[] {
                                        false, false, false, false, false, false
                        };

                        public Class getColumnClass(int columnIndex) {
                                return types[columnIndex];
                        }

                        public boolean isCellEditable(int rowIndex, int columnIndex) {
                                return canEdit[columnIndex];
                        }
                });
                tableHoaDon.setRowHeight(25);
                tableHoaDon.setSelectionBackground(new java.awt.Color(8, 122, 230));
                tableHoaDon.getTableHeader().setReorderingAllowed(false);
                jScrollPane1.setViewportView(tableHoaDon);

                filterPanel.setBackground(new java.awt.Color(255, 255, 255));
                filterPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Lọc hóa đơn",
                                javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
                                javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14),
                                new java.awt.Color(8, 122, 230))); // NOI18N

                jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel1.setText("Khách hàng:");

                cboKhachHang.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                cboKhachHang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả" }));

                jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel2.setText("Nhân viên bán:");

                cboNhanVien.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                cboNhanVien.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả" }));

                jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel3.setText("Ngày lập:");

                dateNgayLap.setDateFormatString("dd/MM/yyyy");
                dateNgayLap.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

                jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel4.setText("Từ khóa:");

                txtTimKiem.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

                btnTimKiem.setBackground(new java.awt.Color(8, 122, 230));
                btnTimKiem.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
                btnTimKiem.setForeground(new java.awt.Color(255, 255, 255));
                btnTimKiem.setText("Tìm kiếm");
                btnTimKiem.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

                btnLamMoi.setBackground(new java.awt.Color(8, 122, 230));
                btnLamMoi.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
                btnLamMoi.setForeground(new java.awt.Color(255, 255, 255));
                btnLamMoi.setText("Làm mới");
                btnLamMoi.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnLamMoiActionPerformed(evt);
                        }
                });

                javax.swing.GroupLayout filterPanelLayout = new javax.swing.GroupLayout(filterPanel);
                filterPanel.setLayout(filterPanelLayout);
                filterPanelLayout.setHorizontalGroup(
                                filterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(filterPanelLayout.createSequentialGroup()
                                                                .addGap(20, 20, 20)
                                                                .addGroup(filterPanelLayout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addComponent(jLabel1)
                                                                                .addComponent(jLabel2))
                                                                .addGap(18, 18, 18)
                                                                .addGroup(filterPanelLayout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING,
                                                                                false)
                                                                                .addComponent(cboKhachHang, 0, 180,
                                                                                                Short.MAX_VALUE)
                                                                                .addComponent(cboNhanVien, 0,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                Short.MAX_VALUE))
                                                                .addGap(30, 30, 30)
                                                                .addGroup(filterPanelLayout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addComponent(jLabel3)
                                                                                .addComponent(jLabel4))
                                                                .addGap(18, 18, 18)
                                                                .addGroup(filterPanelLayout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING,
                                                                                false)
                                                                                .addComponent(txtTimKiem,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                180, Short.MAX_VALUE)
                                                                                .addComponent(dateNgayLap,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                Short.MAX_VALUE))
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                                22, Short.MAX_VALUE)
                                                                .addGroup(filterPanelLayout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING,
                                                                                false)
                                                                                .addComponent(btnTimKiem,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                Short.MAX_VALUE)
                                                                                .addComponent(btnLamMoi,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                Short.MAX_VALUE))
                                                                .addGap(20, 20, 20)));
                filterPanelLayout.setVerticalGroup(
                                filterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(filterPanelLayout.createSequentialGroup()
                                                                .addGap(15, 15, 15)
                                                                .addGroup(filterPanelLayout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addGroup(filterPanelLayout
                                                                                                .createParallelGroup(
                                                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                                .addComponent(jLabel1)
                                                                                                .addComponent(cboKhachHang,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addComponent(jLabel3))
                                                                                .addComponent(dateNgayLap,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(btnTimKiem))
                                                                .addGap(15, 15, 15)
                                                                .addGroup(filterPanelLayout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(jLabel2)
                                                                                .addComponent(cboNhanVien,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(jLabel4)
                                                                                .addComponent(txtTimKiem,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(btnLamMoi))
                                                                .addContainerGap(15, Short.MAX_VALUE)));

                btnThem.setBackground(new java.awt.Color(8, 122, 230));
                btnThem.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
                btnThem.setForeground(new java.awt.Color(255, 255, 255));
                btnThem.setText("THÊM");
                btnThem.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                btnThem.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnThemActionPerformed(evt);
                        }
                });

                btnChiTiet.setBackground(new java.awt.Color(8, 122, 230));
                btnChiTiet.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
                btnChiTiet.setForeground(new java.awt.Color(255, 255, 255));
                btnChiTiet.setText("CHI TIẾT");
                btnChiTiet.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                btnChiTiet.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnChiTietActionPerformed(evt);
                        }
                });

                btnXuatExcel.setBackground(new java.awt.Color(8, 122, 230));
                btnXuatExcel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
                btnXuatExcel.setForeground(new java.awt.Color(255, 255, 255));
                btnXuatExcel.setText("XUẤT EXCEL");
                btnXuatExcel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                btnXuatExcel.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnXuatExcelActionPerformed(evt);
                        }
                });

                javax.swing.GroupLayout mainPanel2Layout = new javax.swing.GroupLayout(mainPanel2);
                mainPanel2.setLayout(mainPanel2Layout);
                mainPanel2Layout.setHorizontalGroup(
                                mainPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(mainPanel2Layout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addGroup(mainPanel2Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addComponent(headerPanel2,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                Short.MAX_VALUE)
                                                                                .addComponent(jScrollPane1)
                                                                                .addGroup(mainPanel2Layout
                                                                                                .createSequentialGroup()
                                                                                                .addGap(6, 6, 6)
                                                                                                .addComponent(btnThem)
                                                                                                .addGap(18, 18, 18)
                                                                                                .addComponent(btnChiTiet)
                                                                                                .addPreferredGap(
                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                Short.MAX_VALUE)
                                                                                                .addComponent(btnXuatExcel))
                                                                                .addComponent(filterPanel,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                Short.MAX_VALUE))
                                                                .addContainerGap()));
                mainPanel2Layout.setVerticalGroup(
                                mainPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(mainPanel2Layout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addComponent(headerPanel2,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addGroup(mainPanel2Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(btnThem)
                                                                                .addComponent(btnChiTiet)
                                                                                .addComponent(btnXuatExcel))
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(filterPanel,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(jScrollPane1,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                297, Short.MAX_VALUE)
                                                                .addContainerGap()));

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
                this.setLayout(layout);
                layout.setHorizontalGroup(
                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(mainPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 756,
                                                                Short.MAX_VALUE));
                layout.setVerticalGroup(
                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(mainPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 562,
                                                                Short.MAX_VALUE));
        }// </editor-fold>//GEN-END:initComponents

        private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnThemActionPerformed
                // Tạo dialog để hiển thị form thêm hóa đơn
                JDialog dialog = new JDialog();
                dialog.setTitle("Thêm hóa đơn");
                dialog.setModal(true);
                dialog.setResizable(false);

                // Tạo panel AddHoaDon và thêm vào dialog
                AddHoaDon addHoaDonPanel = new AddHoaDon();
                dialog.getContentPane().add(addHoaDonPanel);

                // Thiết lập thuộc tính cho dialog
                dialog.pack();
                dialog.setLocationRelativeTo(this);

                // Hiển thị dialog
                dialog.setVisible(true);

                // Sau khi dialog đóng, cập nhật lại bảng
                loadHoaDonTable();
        }// GEN-LAST:event_btnThemActionPerformed

        // Variables declaration - do not modify//GEN-BEGIN:variables
        private javax.swing.JButton btnChiTiet;
        private javax.swing.JButton btnLamMoi;
        private javax.swing.JButton btnThem;
        private javax.swing.JButton btnTimKiem;
        private javax.swing.JButton btnXuatExcel;
        private javax.swing.JComboBox<String> cboKhachHang;
        private javax.swing.JComboBox<String> cboNhanVien;
        private com.toedter.calendar.JDateChooser dateNgayLap;
        private javax.swing.JPanel filterPanel;
        private javax.swing.JPanel headerPanel2;
        private javax.swing.JLabel jLabel1;
        private javax.swing.JLabel jLabel2;
        private javax.swing.JLabel jLabel3;
        private javax.swing.JLabel jLabel4;
        private javax.swing.JScrollPane jScrollPane1;
        private javax.swing.JPanel mainPanel2;
        private javax.swing.JTable tableHoaDon;
        private javax.swing.JLabel titleLabel2;
        private javax.swing.JTextField txtTimKiem;
        // End of variables declaration//GEN-END:variables
}
