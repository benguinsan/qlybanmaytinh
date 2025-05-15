/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI.form;

import BUS.LoaisanphamBUS;
import DTO.LoaisanphamDTO;
import java.awt.Color;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mrben
 */
public class Form_Loaisp extends javax.swing.JPanel {

    private LoaisanphamBUS loaispBUS;
    private DefaultTableModel tableModel;
    private SimpleDateFormat dateFormat;

    /**
     * Creates new form Form_Loaisp
     */
    public Form_Loaisp() {
        initComponents();
        loaispBUS = new LoaisanphamBUS();
        dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        // Khởi tạo bảng và load dữ liệu
        initTable();
        loadLoaisanphamData();

        // Thêm placeholder cho ô tìm kiếm
        addPlaceHolderStyle(jtf_search);
    }

    /**
     * Khởi tạo bảng loại sản phẩm
     */
    private void initTable() {
        tableModel = (DefaultTableModel) phanloaiTbl.getModel();
        tableModel.setColumnIdentifiers(new String[] { "Mã loại", "Tên loại", "Mô tả", "Ngày tạo" });
    }

    /**
     * Load dữ liệu loại sản phẩm vào bảng
     */
    private void loadLoaisanphamData() {
        // Xóa dữ liệu cũ
        tableModel.setRowCount(0);

        // Lấy danh sách loại sản phẩm từ BUS
        List<LoaisanphamDTO> listLoaisp = loaispBUS.getAllLoaisanpham();

        // Thêm dữ liệu vào bảng
        for (LoaisanphamDTO loaisp : listLoaisp) {
            String ngayTao = loaisp.getCreated_at() != null ? dateFormat.format(loaisp.getCreated_at()) : "";
            tableModel.addRow(new Object[] {
                    loaisp.getMa_loai(),
                    loaisp.getTen_loai(),
                    loaisp.getMo_ta(),
                    ngayTao
            });
        }

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        phanloaiTbl.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        phanloaiTbl.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        phanloaiTbl.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        phanloaiTbl.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        

    }

    /**
     * Tìm kiếm loại sản phẩm theo mã hoặc tên
     */
    private void searchLoaisanpham() {
        String keyword = jtf_search.getText().trim();

        // Nếu từ khóa là placeholder hoặc rỗng, load lại tất cả dữ liệu
        if (keyword.equals("Search by ID/name") || keyword.isEmpty()) {
            loadLoaisanphamData();
            return;
        }

        // Xóa dữ liệu cũ
        tableModel.setRowCount(0);

        // Tìm theo mã
        LoaisanphamDTO loaispByMa = loaispBUS.getLoaisanphamByMa(keyword);
        if (loaispByMa != null) {
            String ngayTao = loaispByMa.getCreated_at() != null ? dateFormat.format(loaispByMa.getCreated_at()) : "";
            tableModel.addRow(new Object[] {
                    loaispByMa.getMa_loai(),
                    loaispByMa.getTen_loai(),
                    loaispByMa.getMo_ta(),
                    ngayTao
            });
        }

        // Tìm theo tên
        // List<LoaisanphamDTO> listByTen = loaispBUS.searchLoaisanphamByTen(keyword);
        // for (LoaisanphamDTO loaisp : listByTen) {
        //     // Kiểm tra nếu đã thêm vào bảng rồi thì bỏ qua
        //     if (loaispByMa != null && loaisp.getMa_loai().equals(loaispByMa.getMa_loai())) {
        //         continue;
        //     }

        //     String ngayTao = loaisp.getCreated_at() != null ? dateFormat.format(loaisp.getCreated_at()) : "";
        //     tableModel.addRow(new Object[] {
        //             loaisp.getMa_loai(),
        //             loaisp.getTen_loai(),
        //             loaisp.getMo_ta(),
        //             ngayTao
        //     });
        // }
    }

    /**
     * Hiển thị dialog thêm loại sản phẩm mới
     */
    private void showAddLoaisanphamDialog() {
        // Tạo các trường nhập liệu
        JTextField maLoaiField = new JTextField();
        JTextField tenLoaiField = new JTextField();
        JTextField moTaField = new JTextField();

        // Tạo panel chứa các trường nhập liệu
        Object[] fields = {
                "Mã loại:", maLoaiField,
                "Tên loại:", tenLoaiField,
                "Mô tả:", moTaField
        };

        // Hiển thị dialog
        int result = JOptionPane.showConfirmDialog(this, fields, "Thêm loại sản phẩm mới",
                JOptionPane.OK_CANCEL_OPTION);

        // Xử lý khi người dùng nhấn OK
        if (result == JOptionPane.OK_OPTION) {
            String maLoai = maLoaiField.getText().trim();
            String tenLoai = tenLoaiField.getText().trim();
            String moTa = moTaField.getText().trim();

            // Kiểm tra dữ liệu đầu vào
            if (maLoai.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Mã loại không được để trống!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (tenLoai.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Tên loại không được để trống!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Kiểm tra mã loại đã tồn tại chưa
            if (loaispBUS.isLoaisanphamExist(maLoai)) {
                JOptionPane.showMessageDialog(this, "Mã loại đã tồn tại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Thêm loại sản phẩm mới
            boolean success = loaispBUS.addLoaisanpham(maLoai, tenLoai, moTa);

            if (success) {
                JOptionPane.showMessageDialog(this, "Thêm loại sản phẩm thành công!", "Thông báo",
                        JOptionPane.INFORMATION_MESSAGE);
                loadLoaisanphamData(); // Cập nhật lại bảng
            } else {
                JOptionPane.showMessageDialog(this, "Thêm loại sản phẩm thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Hiển thị dialog cập nhật loại sản phẩm
     */
    private void showUpdateLoaisanphamDialog() {
        // Lấy dòng được chọn
        int selectedRow = phanloaiTbl.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn loại sản phẩm cần cập nhật!", "Thông báo",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Lấy thông tin loại sản phẩm từ dòng được chọn
        String maLoai = phanloaiTbl.getValueAt(selectedRow, 0).toString();
        LoaisanphamDTO loaisp = loaispBUS.getLoaisanphamByMa(maLoai);

        if (loaisp == null) {
            JOptionPane.showMessageDialog(this, "Không tìm thấy thông tin loại sản phẩm!", "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Tạo các trường nhập liệu
        JTextField tenLoaiField = new JTextField(loaisp.getTen_loai());
        JTextField moTaField = new JTextField(loaisp.getMo_ta());

        // Tạo panel chứa các trường nhập liệu
        Object[] fields = {
                "Mã loại:", maLoai + " (không thể thay đổi)",
                "Tên loại:", tenLoaiField,
                "Mô tả:", moTaField
        };

        // Hiển thị dialog
        int result = JOptionPane.showConfirmDialog(this, fields, "Cập nhật loại sản phẩm",
                JOptionPane.OK_CANCEL_OPTION);

        // Xử lý khi người dùng nhấn OK
        if (result == JOptionPane.OK_OPTION) {
            String tenLoai = tenLoaiField.getText().trim();
            String moTa = moTaField.getText().trim();

            // Kiểm tra dữ liệu đầu vào
            if (tenLoai.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Tên loại không được để trống!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Cập nhật loại sản phẩm
            boolean success = loaispBUS.updateLoaisanpham(maLoai, tenLoai, moTa);

            if (success) {
                JOptionPane.showMessageDialog(this, "Cập nhật loại sản phẩm thành công!", "Thông báo",
                        JOptionPane.INFORMATION_MESSAGE);
                loadLoaisanphamData(); // Cập nhật lại bảng
            } else {
                JOptionPane.showMessageDialog(this, "Cập nhật loại sản phẩm thất bại!", "Lỗi",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Xóa loại sản phẩm
     */
    private void deleteLoaisanpham() {
        // Lấy dòng được chọn
        int selectedRow = phanloaiTbl.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn loại sản phẩm cần xóa!", "Thông báo",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Lấy mã loại sản phẩm từ dòng được chọn
        String maLoai = phanloaiTbl.getValueAt(selectedRow, 0).toString();

        // Hiển thị dialog xác nhận
        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa loại sản phẩm này?",
                "Xác nhận xóa", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            // Xóa loại sản phẩm
            boolean success = loaispBUS.deleteLoaisanpham(maLoai);

            if (success) {
                JOptionPane.showMessageDialog(this, "Xóa loại sản phẩm thành công!", "Thông báo",
                        JOptionPane.INFORMATION_MESSAGE);
                loadLoaisanphamData(); // Cập nhật lại bảng
            } else {
                JOptionPane.showMessageDialog(this, "Xóa loại sản phẩm thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Hiển thị thông tin chi tiết loại sản phẩm
     */
    private void showLoaisanphamInfo() {
        // Lấy dòng được chọn
        int selectedRow = phanloaiTbl.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn loại sản phẩm để xem thông tin!", "Thông báo",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Lấy thông tin loại sản phẩm từ dòng được chọn
        String maLoai = phanloaiTbl.getValueAt(selectedRow, 0).toString();
        LoaisanphamDTO loaisp = loaispBUS.getLoaisanphamByMa(maLoai);

        if (loaisp == null) {
            JOptionPane.showMessageDialog(this, "Không tìm thấy thông tin loại sản phẩm!", "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Hiển thị thông tin chi tiết
        String ngayTao = loaisp.getCreated_at() != null ? dateFormat.format(loaisp.getCreated_at()) : "Không có";
        String info = "Mã loại: " + loaisp.getMa_loai() + "\n"
                + "Tên loại: " + loaisp.getTen_loai() + "\n"
                + "Mô tả: " + loaisp.getMo_ta() + "\n"
                + "Ngày tạo: " + ngayTao;

        JOptionPane.showMessageDialog(this, info, "Thông tin loại sản phẩm", JOptionPane.INFORMATION_MESSAGE);
    }

    public void addPlaceHolderStyle(JTextField jtf) {
        Font f = jtf.getFont();
        f = f.deriveFont(Font.ITALIC);
        jtf.setFont(f);
        jtf.setForeground(Color.gray);
    }

    public void removePlaceHolderStyle(JTextField jtf) {
        Font f = jtf.getFont();
        f = f.deriveFont(Font.PLAIN | Font.BOLD);
        jtf.setFont(f);
        jtf.setForeground(Color.black);
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

        jPanel1 = new javax.swing.JPanel();
        jtf_search = new javax.swing.JTextField();
        reloadBtn = new javax.swing.JButton();
        addBtn = new javax.swing.JButton();
        updateBtn = new javax.swing.JButton();
        deleteBtn = new javax.swing.JButton();
        infoBtn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        phanloaiTbl = new javax.swing.JTable();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jtf_search.setText("Search by ID/name");
        jtf_search.setPreferredSize(new java.awt.Dimension(300, 22));
        jtf_search.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtf_searchFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtf_searchFocusLost(evt);
            }
        });
        jtf_search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtf_searchActionPerformed(evt);
            }
        });

        reloadBtn.setBackground(new java.awt.Color(153, 153, 153));
        reloadBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/icon/icons8-reload-30.png"))); // NOI18N
        reloadBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        reloadBtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        reloadBtn.setPreferredSize(new java.awt.Dimension(40, 40));
        reloadBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                reloadBtnMouseClicked(evt);
            }
        });

        addBtn.setBackground(new java.awt.Color(204, 204, 204));
        addBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/icon/plus.png"))); // NOI18N
        addBtn.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        addBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        addBtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        addBtn.setPreferredSize(new java.awt.Dimension(40, 40));
        addBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addBtnMouseClicked(evt);
            }
        });

        updateBtn.setBackground(new java.awt.Color(204, 204, 204));
        updateBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/icon/office-material.png"))); // NOI18N
        updateBtn.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        updateBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        updateBtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        updateBtn.setPreferredSize(new java.awt.Dimension(40, 40));
        updateBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                updateBtnMouseClicked(evt);
            }
        });

        deleteBtn.setBackground(new java.awt.Color(204, 204, 204));
        deleteBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/icon/delete.png"))); // NOI18N
        deleteBtn.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        deleteBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        deleteBtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        deleteBtn.setPreferredSize(new java.awt.Dimension(40, 40));
        deleteBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                deleteBtnMouseClicked(evt);
            }
        });

        infoBtn.setBackground(new java.awt.Color(204, 204, 204));
        infoBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/icon/information.png"))); // NOI18N
        infoBtn.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        infoBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        infoBtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        infoBtn.setPreferredSize(new java.awt.Dimension(40, 40));
        infoBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                infoBtnMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(addBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(updateBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(deleteBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(infoBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jtf_search, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(reloadBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(infoBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(updateBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(deleteBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(addBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(reloadBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jtf_search, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        phanloaiTbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã loại", "Tên loại", "Mô tả", "Ngày tạo"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(phanloaiTbl);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jtf_searchFocusGained(java.awt.event.FocusEvent evt) {// GEN-FIRST:event_jtf_searchFocusGained
        if (jtf_search.getText().equals("Search by ID/name")) {
            jtf_search.setText(null);
            jtf_search.requestFocus();
            removePlaceHolderStyle(jtf_search);
        }
    }// GEN-LAST:event_jtf_searchFocusGained

    private void jtf_searchFocusLost(java.awt.event.FocusEvent evt) {// GEN-FIRST:event_jtf_searchFocusLost
        if (jtf_search.getText().length() == 0) {
            addPlaceHolderStyle(jtf_search);
            jtf_search.setText("Search by ID/name");
        }
    }// GEN-LAST:event_jtf_searchFocusLost

    private void jtf_searchActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jtf_searchActionPerformed
        searchLoaisanpham();
    }// GEN-LAST:event_jtf_searchActionPerformed

    private void reloadBtnMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_reloadBtnMouseClicked
        loadLoaisanphamData();
        jtf_search.setText("Search by ID/name");
        addPlaceHolderStyle(jtf_search);
    }// GEN-LAST:event_reloadBtnMouseClicked

    private void addBtnMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_addBtnMouseClicked
        showAddLoaisanphamDialog();
    }// GEN-LAST:event_addBtnMouseClicked

    private void updateBtnMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_updateBtnMouseClicked
        showUpdateLoaisanphamDialog();
    }// GEN-LAST:event_updateBtnMouseClicked

    private void deleteBtnMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_deleteBtnMouseClicked
        deleteLoaisanpham();
    }// GEN-LAST:event_deleteBtnMouseClicked

    private void infoBtnMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_infoBtnMouseClicked
        showLoaisanphamInfo();
    }// GEN-LAST:event_infoBtnMouseClicked

    private void nhanvienTblClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_nhanvienTblClicked
        // TODO add your handling code here:
    }// GEN-LAST:event_nhanvienTblClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addBtn;
    private javax.swing.JButton deleteBtn;
    private javax.swing.JButton infoBtn;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jtf_search;
    private javax.swing.JTable phanloaiTbl;
    private javax.swing.JButton reloadBtn;
    private javax.swing.JButton updateBtn;
    // End of variables declaration//GEN-END:variables
}
