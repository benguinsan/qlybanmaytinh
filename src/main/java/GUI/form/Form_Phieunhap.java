/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI.form;

import BUS.PhieunhapBUS;
import BUS.NhanvienBUS;
import BUS.NhacungcapBUS;
import DTO.PhieunhapDTO;
import DTO.NhanvienDTO;
import DTO.NhacungcapDTO;
import GUI.component.PhieuNhap.AddPhieuNhap;
// import GUI.component.PhieuNhap.ViewPhieuNhapDetail;
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

/**
 *
 * @author mrben
 */
public class Form_Phieunhap extends javax.swing.JPanel {
        private PhieunhapBUS phieunhapBUS;
        private NhanvienBUS nhanvienBUS;
        private NhacungcapBUS nhacungcapBUS;
        private String selectedMaPhieuNhap;

        /**
         * Creates new form Form_Phieunhap
         */
        public Form_Phieunhap() {
                initComponents();
                phieunhapBUS = new PhieunhapBUS();
                nhanvienBUS = new NhanvienBUS();
                nhacungcapBUS = new NhacungcapBUS();

                // Khởi tạo combobox
                initComboBoxes();

                // Load dữ liệu vào bảng
                loadPhieuNhapTable();

                // Thêm sự kiện cho bảng
                tablePhieuNhap.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                tablePhieuNhapClicked(evt);
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

                btnHuyPhieu.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnHuyPhieuActionPerformed(evt);
                        }
                });

                btnXuatExcel.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnXuatExcelActionPerformed(evt);
                        }
                });
        }

        private void initComboBoxes() {
                // Khởi tạo combobox nhà cung cấp
                cboNhaCungCap.removeAllItems();
                cboNhaCungCap.addItem("Tất cả");

                ArrayList<NhacungcapDTO> listNCC = nhacungcapBUS.getAllNhacungcap();
                for (NhacungcapDTO ncc : listNCC) {
                        cboNhaCungCap.addItem(ncc.getMa_ncc() + " - " + ncc.getTen_ncc());
                }

                // Khởi tạo combobox nhân viên
                cboNhanVien.removeAllItems();
                cboNhanVien.addItem("Tất cả");

                ArrayList<NhanvienDTO> listNV = nhanvienBUS.getList();
                for (NhanvienDTO nv : listNV) {
                        cboNhanVien.addItem(nv.getMa_nhan_vien() + " - " + nv.getHo_ten());
                }
        }

        private void loadPhieuNhapTable() {
                // Lấy danh sách phiếu nhập
                ArrayList<PhieunhapDTO> listPhieuNhap = phieunhapBUS.getList();

                // Xóa dữ liệu cũ trong bảng
                DefaultTableModel model = (DefaultTableModel) tablePhieuNhap.getModel();
                model.setRowCount(0);

                // Định dạng ngày tháng
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

                // Thêm dữ liệu vào bảng
                int stt = 1;
                for (PhieunhapDTO pn : listPhieuNhap) {
                        // Lấy thông tin nhà cung cấp
                        NhacungcapDTO ncc = nhacungcapBUS.getNhacungcapByMa(pn.getMa_ncc());
                        String tenNCC = ncc != null ? ncc.getTen_ncc() : pn.getMa_ncc();

                        // Lấy thông tin nhân viên
                        NhanvienDTO nv = nhanvienBUS.getNhanvienByMaNV(pn.getMa_nhan_vien());
                        String tenNV = nv != null ? nv.getHo_ten() : pn.getMa_nhan_vien();

                        // Thêm dòng mới vào bảng
                        Object[] row = new Object[6];
                        row[0] = stt++;
                        row[1] = pn.getMa_pn();
                        row[2] = tenNCC;
                        row[3] = tenNV;
                        row[4] = dateFormat.format(pn.getNgayNhap());
                        row[5] = pn.getTongTien();

                        model.addRow(row);
                }

                // Tạo một DefaultTableCellRenderer để căn giữa các cột
                DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
                centerRenderer.setHorizontalAlignment(JLabel.CENTER);

                // Áp dụng renderer cho các cột cần căn giữa
                tablePhieuNhap.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
                tablePhieuNhap.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
                tablePhieuNhap.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
                tablePhieuNhap.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
                tablePhieuNhap.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
                tablePhieuNhap.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
                    
        }

        private void tablePhieuNhapClicked(java.awt.event.MouseEvent evt) {
                int selectedRow = tablePhieuNhap.getSelectedRow();
                if (selectedRow >= 0) {
                        // Lấy mã phiếu nhập từ cột thứ 1 (index 1)
                        selectedMaPhieuNhap = tablePhieuNhap.getValueAt(selectedRow, 1).toString();
                        System.out.println("Đã chọn phiếu nhập: " + selectedMaPhieuNhap);
                }
        }

        private void btnTimKiemActionPerformed(java.awt.event.ActionEvent evt) {
                // Lấy các điều kiện lọc
                String maNCC = "Tất cả";
                if (cboNhaCungCap.getSelectedIndex() > 0) {
                        maNCC = cboNhaCungCap.getSelectedItem().toString().split(" - ")[0];
                }

                String maNV = "Tất cả";
                if (cboNhanVien.getSelectedIndex() > 0) {
                        maNV = cboNhanVien.getSelectedItem().toString().split(" - ")[0];
                }

                Date ngayNhap = dateNgayNhap.getDate();

                String tuKhoa = txtTimKiem.getText().trim();

                // Lấy danh sách phiếu nhập
                ArrayList<PhieunhapDTO> listPhieuNhap = phieunhapBUS.getList();
                ArrayList<PhieunhapDTO> listFiltered = new ArrayList<>();

                // Lọc theo điều kiện
                for (PhieunhapDTO pn : listPhieuNhap) {
                        boolean match = true;

                        // Lọc theo nhà cung cấp
                        if (!maNCC.equals("Tất cả") && !pn.getMa_ncc().equals(maNCC)) {
                                match = false;
                        }

                        // Lọc theo nhân viên
                        if (match && !maNV.equals("Tất cả") && !pn.getMa_nhan_vien().equals(maNV)) {
                                match = false;
                        }

                        // Lọc theo ngày nhập
                        if (match && ngayNhap != null) {
                                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                                String ngayNhapStr = sdf.format(ngayNhap);
                                String ngayPhieuStr = sdf.format(pn.getNgayNhap());

                                if (!ngayPhieuStr.equals(ngayNhapStr)) {
                                        match = false;
                                }
                        }

                        // Lọc theo từ khóa
                        if (match && !tuKhoa.isEmpty()) {
                                if (!pn.getMa_pn().toLowerCase().contains(tuKhoa.toLowerCase())) {
                                        // Kiểm tra tên nhà cung cấp
                                        NhacungcapDTO ncc = nhacungcapBUS.getNhacungcapByMa(pn.getMa_ncc());
                                        String tenNCC = ncc != null ? ncc.getTen_ncc() : "";

                                        if (!tenNCC.toLowerCase().contains(tuKhoa.toLowerCase())) {
                                                match = false;
                                        }
                                }
                        }

                        if (match) {
                                listFiltered.add(pn);
                        }
                }

                // Hiển thị kết quả lọc
                displayFilteredResults(listFiltered);
        }

        private void displayFilteredResults(ArrayList<PhieunhapDTO> listPhieuNhap) {
                // Xóa dữ liệu cũ trong bảng
                DefaultTableModel model = (DefaultTableModel) tablePhieuNhap.getModel();
                model.setRowCount(0);

                // Định dạng ngày tháng
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

                // Thêm dữ liệu vào bảng
                int stt = 1;
                for (PhieunhapDTO pn : listPhieuNhap) {
                        // Lấy thông tin nhà cung cấp
                        NhacungcapDTO ncc = nhacungcapBUS.getNhacungcapByMa(pn.getMa_ncc());
                        String tenNCC = ncc != null ? ncc.getTen_ncc() : pn.getMa_ncc();

                        // Lấy thông tin nhân viên
                        NhanvienDTO nv = nhanvienBUS.getNhanvienByMaNV(pn.getMa_nhan_vien());
                        String tenNV = nv != null ? nv.getHo_ten() : pn.getMa_nhan_vien();

                        // Thêm dòng mới vào bảng
                        Object[] row = new Object[6];
                        row[0] = stt++;
                        row[1] = pn.getMa_pn();
                        row[2] = tenNCC;
                        row[3] = tenNV;
                        row[4] = dateFormat.format(pn.getNgayNhap());
                        row[5] = pn.getTongTien();

                        model.addRow(row);
                }
        }


        private void btnChiTietActionPerformed(java.awt.event.ActionEvent evt) {
                // if (selectedMaPhieuNhap != null) {
                // ViewPhieuNhapDetail dialog = new ViewPhieuNhapDetail(selectedMaPhieuNhap);
                // dialog.setVisible(true);
                // } else {
                // JOptionPane.showMessageDialog(
                // this,
                // "Vui lòng chọn phiếu nhập để xem chi tiết!",
                // "Thông báo",
                // JOptionPane.WARNING_MESSAGE);
                // }
        }

        private void btnHuyPhieuActionPerformed(java.awt.event.ActionEvent evt) {
                if (selectedMaPhieuNhap != null) {
                        // Hiển thị hộp thoại xác nhận
                        int confirm = JOptionPane.showConfirmDialog(
                                        this,
                                        "Bạn có chắc chắn muốn hủy phiếu nhập này?",
                                        "Xác nhận hủy",
                                        JOptionPane.YES_NO_OPTION);

                        if (confirm == JOptionPane.YES_OPTION) {
                                // Xóa phiếu nhập thông qua BUS
                                boolean success = phieunhapBUS.deletePhieuNhap(selectedMaPhieuNhap);

                                if (success) {
                                        JOptionPane.showMessageDialog(
                                                        this,
                                                        "Hủy phiếu nhập thành công!",
                                                        "Thông báo",
                                                        JOptionPane.INFORMATION_MESSAGE);

                                        // Cập nhật lại bảng
                                        loadPhieuNhapTable();

                                        // Reset biến lưu mã phiếu nhập
                                        selectedMaPhieuNhap = null;

                                } else {
                                        JOptionPane.showMessageDialog(
                                                        this,
                                                        "Hủy phiếu nhập thất bại!",
                                                        "Lỗi",
                                                        JOptionPane.ERROR_MESSAGE);
                                }
                        }
                } else {
                        JOptionPane.showMessageDialog(
                                        this,
                                        "Vui lòng chọn phiếu nhập cần hủy!",
                                        "Thông báo",
                                        JOptionPane.WARNING_MESSAGE);
                }
        }

        private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {
                // Tạo dialog để hiển thị form thêm phiếu nhập
                JDialog dialog = new JDialog();
                dialog.setTitle("Thêm phiếu nhập");
                dialog.setModal(true);
                dialog.setResizable(false);

                // Tạo panel AddPhieuNhap và thêm vào dialog
                AddPhieuNhap addPhieuNhapPanel = new AddPhieuNhap();
                dialog.getContentPane().add(addPhieuNhapPanel);

                // Thiết lập thuộc tính cho dialog
                dialog.pack();
                dialog.setLocationRelativeTo(this);

                // Hiển thị dialog
                dialog.setVisible(true);

                // Sau khi dialog đóng, cập nhật lại bảng
                loadPhieuNhapTable();
        }

        /**
         * This method is called from within the constructor to initialize the form.
         * WARNING: Do NOT modify this code. The content of this method is always
         * regenerated by the Form Editor.
         */
        @SuppressWarnings("unchecked")
        // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        headerPanel = new javax.swing.JPanel();
        titleLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablePhieuNhap = new javax.swing.JTable();
        filterPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cboNhaCungCap = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        cboNhanVien = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        dateNgayNhap = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        txtTimKiem = new javax.swing.JTextField();
        btnTimKiem = new javax.swing.JButton();
        btnLamMoi = new javax.swing.JButton();
        btnThem = new javax.swing.JButton();
        btnChiTiet = new javax.swing.JButton();
        btnHuyPhieu = new javax.swing.JButton();
        btnXuatExcel = new javax.swing.JButton();

        mainPanel.setBackground(new java.awt.Color(255, 255, 255));
        mainPanel.setPreferredSize(new java.awt.Dimension(752, 448));

        headerPanel.setBackground(new java.awt.Color(8, 122, 230));
        headerPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));

        titleLabel.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        titleLabel.setForeground(new java.awt.Color(255, 255, 255));
        titleLabel.setText("PHIẾU NHẬP");

        javax.swing.GroupLayout headerPanelLayout = new javax.swing.GroupLayout(headerPanel);
        headerPanel.setLayout(headerPanelLayout);
        headerPanelLayout.setHorizontalGroup(
            headerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(titleLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        headerPanelLayout.setVerticalGroup(
            headerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(titleLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tablePhieuNhap.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tablePhieuNhap.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã phiếu nhập", "Nhà cung cấp", "Nhân viên nhập", "Thời gian", "Tổng tiền"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablePhieuNhap.setRowHeight(25);
        tablePhieuNhap.setSelectionBackground(new java.awt.Color(8, 122, 230));
        tablePhieuNhap.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tablePhieuNhap);

        filterPanel.setBackground(new java.awt.Color(255, 255, 255));
        filterPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Lọc phiếu nhập", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(8, 122, 230))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Nhà cung cấp:");

        cboNhaCungCap.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cboNhaCungCap.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả" }));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Nhân viên nhập:");

        cboNhanVien.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cboNhanVien.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả" }));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Ngày nhập:");

        dateNgayNhap.setDateFormatString("dd/MM/yyyy");
        dateNgayNhap.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

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
                .addGroup(filterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(filterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cboNhaCungCap, 0, 180, Short.MAX_VALUE)
                    .addComponent(cboNhanVien, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(30, 30, 30)
                .addGroup(filterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(filterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                    .addComponent(dateNgayNhap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(filterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnTimKiem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnLamMoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(20, 20, 20))
        );
        filterPanelLayout.setVerticalGroup(
            filterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(filterPanelLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(filterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(filterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(cboNhaCungCap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3))
                    .addComponent(dateNgayNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTimKiem))
                .addGap(15, 15, 15)
                .addGroup(filterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cboNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLamMoi))
                .addContainerGap(15, Short.MAX_VALUE))
        );

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

        btnHuyPhieu.setBackground(new java.awt.Color(8, 122, 230));
        btnHuyPhieu.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnHuyPhieu.setForeground(new java.awt.Color(255, 255, 255));
        btnHuyPhieu.setText("HỦY PHIẾU");
        btnHuyPhieu.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

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

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(headerPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addComponent(btnThem)
                        .addGap(18, 18, 18)
                        .addComponent(btnChiTiet)
                        .addGap(18, 18, 18)
                        .addComponent(btnHuyPhieu)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnXuatExcel))
                    .addComponent(filterPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(headerPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem)
                    .addComponent(btnChiTiet)
                    .addComponent(btnHuyPhieu)
                    .addComponent(btnXuatExcel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(filterPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 297, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 756, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 562, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
         // Reset các điều kiện lọc
         cboNhaCungCap.setSelectedIndex(0);
         cboNhanVien.setSelectedIndex(0);
         dateNgayNhap.setDate(null);
         txtTimKiem.setText("");

                // Load lại dữ liệu
         loadPhieuNhapTable();
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void btnXuatExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatExcelActionPerformed
        
    }//GEN-LAST:event_btnXuatExcelActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChiTiet;
    private javax.swing.JButton btnHuyPhieu;
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnTimKiem;
    private javax.swing.JButton btnXuatExcel;
    private javax.swing.JComboBox<String> cboNhaCungCap;
    private javax.swing.JComboBox<String> cboNhanVien;
    private com.toedter.calendar.JDateChooser dateNgayNhap;
    private javax.swing.JPanel filterPanel;
    private javax.swing.JPanel headerPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JTable tablePhieuNhap;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
