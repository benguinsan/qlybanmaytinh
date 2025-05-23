/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI.component.Khachhang;

import BUS.KhachhangBUS;
import DTO.KhachhangDTO;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author mrben
 */

public class AddUpdateKHForm extends javax.swing.JFrame {
    public KhachhangBUS khachhangBUS;

    // Biến để xác định form đang ở chế độ thêm mới hay cập nhật
    private boolean isUpdateMode = false;
    private String maKhachHangForUpdate = null;

    /**
     * Creates new form AddUpdateKHForm
     */

    public AddUpdateKHForm() {
        initComponents();
        khachhangBUS = new KhachhangBUS();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        information_panel = new javax.swing.JPanel();
        txt_tenkh = new javax.swing.JTextField();
        txt_diachi = new javax.swing.JTextField();
        txt_sdt = new javax.swing.JTextField();
        txt_makh = new javax.swing.JTextField();
        saveBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Khách hàng");
        setResizable(false);

        information_panel.setBackground(new java.awt.Color(255, 255, 255));
        information_panel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Information",
                javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION,
                new java.awt.Font("Segoe UI", 1, 12))); // NOI18N
        information_panel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        information_panel.setName(""); // NOI18N

        txt_tenkh.setBorder(javax.swing.BorderFactory.createTitledBorder("Họ tên"));
        txt_tenkh.setPreferredSize(new java.awt.Dimension(69, 40));
        txt_tenkh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_tenkhActionPerformed(evt);
            }
        });

        txt_diachi.setBorder(javax.swing.BorderFactory.createTitledBorder("Địa chỉ"));
        txt_diachi.setPreferredSize(new java.awt.Dimension(69, 40));
        txt_diachi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_diachiActionPerformed(evt);
            }
        });

        txt_sdt.setBorder(javax.swing.BorderFactory.createTitledBorder("Số điện thoại"));
        txt_sdt.setPreferredSize(new java.awt.Dimension(69, 40));
        txt_sdt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_sdtActionPerformed(evt);
            }
        });

        txt_makh.setBorder(javax.swing.BorderFactory.createTitledBorder("Mã khách hàng"));
        txt_makh.setPreferredSize(new java.awt.Dimension(69, 40));
        txt_makh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_makhActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout information_panelLayout = new javax.swing.GroupLayout(information_panel);
        information_panel.setLayout(information_panelLayout);
        information_panelLayout.setHorizontalGroup(
                information_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, information_panelLayout
                                .createSequentialGroup()
                                .addContainerGap()
                                .addGroup(information_panelLayout
                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(txt_diachi, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, information_panelLayout
                                                .createSequentialGroup()
                                                .addGap(0, 2, Short.MAX_VALUE)
                                                .addComponent(txt_makh, javax.swing.GroupLayout.PREFERRED_SIZE, 124,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(txt_tenkh, javax.swing.GroupLayout.PREFERRED_SIZE, 240,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(txt_sdt, javax.swing.GroupLayout.Alignment.LEADING,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap()));
        information_panelLayout.setVerticalGroup(
                information_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(information_panelLayout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addGroup(information_panelLayout
                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txt_tenkh, javax.swing.GroupLayout.PREFERRED_SIZE, 45,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txt_makh, javax.swing.GroupLayout.PREFERRED_SIZE, 45,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(txt_diachi, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12)
                                .addComponent(txt_sdt, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(17, Short.MAX_VALUE)));

        saveBtn.setBackground(new java.awt.Color(0, 204, 51));
        saveBtn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        saveBtn.setForeground(new java.awt.Color(255, 255, 255));
        saveBtn.setText("SAVE");
        saveBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(information_panel, javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(saveBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 100,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(147, 147, 147)));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(information_panel, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(saveBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 50,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap()));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txt_tenkhActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_txt_tenkhActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_txt_tenkhActionPerformed

    private void txt_diachiActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_txt_diachiActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_txt_diachiActionPerformed

    private void txt_sdtActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_txt_sdtActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_txt_sdtActionPerformed

    /**
     * Đặt dữ liệu cho form khi ở chế độ cập nhật
     * 
     * @param kh Đối tượng KhachhangDTO chứa thông tin khách hàng cần cập nhật
     */
    public void setDataForUpdate(KhachhangDTO kh) {
        if (kh != null) {
            isUpdateMode = true;
            maKhachHangForUpdate = kh.getMa_khach_hang();

            // Đổ dữ liệu vào các trường
            txt_makh.setText(kh.getMa_khach_hang());
            txt_tenkh.setText(kh.getHo_ten());
            txt_diachi.setText(kh.getDia_chi());
            txt_sdt.setText(kh.getDien_thoai());
        }
    }

    /**
     * Vô hiệu hóa trường mã khách hàng
     */
    public void disableFieldForUpdate() {
        txt_makh.setEditable(false);
        txt_makh.setBackground(new java.awt.Color(240, 240, 240));
    }

    // Cập nhật phương thức saveBtnActionPerformed để xử lý cả thêm mới và cập nhật
    private void saveBtnActionPerformed(java.awt.event.ActionEvent evt) {
        // Lấy dữ liệu từ form
        String maKH = txt_makh.getText().trim();
        String tenKH = txt_tenkh.getText().trim();
        String diaChi = txt_diachi.getText().trim();
        String sdt = txt_sdt.getText().trim();

        // Kiểm tra dữ liệu
        if (maKH.isEmpty() || tenKH.isEmpty() || sdt.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin bắt buộc!", "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Kiểm tra định dạng mã khách hàng (chỉ cần 5 ký tự) - chỉ kiểm tra khi thêm
        if (!isUpdateMode && maKH.length() != 5) {
            JOptionPane.showMessageDialog(this, "Mã khách hàng phải có đúng 5 ký tự!", "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Kiểm tra định dạng số điện thoại
        if (!sdt.matches("\\d{10,11}")) {
            JOptionPane.showMessageDialog(this, "Số điện thoại không hợp lệ! Vui lòng nhập 10-11 chữ số.", "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            boolean success;

            if (isUpdateMode) {
                // Cập nhật khách hàng
                // Lấy thông tin hiện tại của khách hàng để giữ nguyên created_at và trang_thai
                KhachhangDTO khCurrent = khachhangBUS.getKhachhangByMaKH(maKH);

                if (khCurrent != null) {
                    // Chỉ cập nhật các trường thông tin cơ bản, giữ nguyên created_at và trang_thai
                    khCurrent.setHo_ten(tenKH);
                    khCurrent.setDia_chi(diaChi);
                    khCurrent.setDien_thoai(sdt);

                    // Gọi phương thức Set trong BUS
                    success = khachhangBUS.SetKhachhang(khCurrent);

                    if (success) {
                        JOptionPane.showMessageDialog(
                                this,
                                "Cập nhật khách hàng thành công!",
                                "Thông báo",
                                JOptionPane.INFORMATION_MESSAGE);

                    } else {
                        JOptionPane.showMessageDialog(
                                this,
                                "Cập nhật khách hàng thất bại!",
                                "Lỗi",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(
                            this,
                            "Không tìm thấy thông tin khách hàng!",
                            "Lỗi",
                            JOptionPane.ERROR_MESSAGE);
                }
            } else {
                // Thêm mới khách hàng
                KhachhangDTO kh = new KhachhangDTO();
                kh.setMa_khach_hang(maKH);
                kh.setHo_ten(tenKH);
                kh.setDia_chi(diaChi);
                kh.setDien_thoai(sdt);
                kh.setCreated_at(new Date()); // Ngày tạo
                kh.setTrang_thai(1); // Mặc định là hoạt động

                success = khachhangBUS.AddKhachhang(kh);

                if (success) {
                    JOptionPane.showMessageDialog(
                            this,
                            "Thêm khách hàng thành công!",
                            "Thông báo",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(
                            this,
                            "Thêm khách hàng thất bại!",
                            "Lỗi",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                    this,
                    "Lỗi: " + e.getMessage(),
                    "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
            e.printStackTrace(); // In ra lỗi chi tiết trong console để debug
        }
    }

    private void txt_makhActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_txt_makhActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_txt_makhActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        // <editor-fold defaultstate="collapsed" desc=" Look and feel setting code
        // (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the default
         * look and feel.
         * For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AddUpdateKHForm.class.getName()).log(java.util.logging.Level.SEVERE,
                    null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddUpdateKHForm.class.getName()).log(java.util.logging.Level.SEVERE,
                    null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddUpdateKHForm.class.getName()).log(java.util.logging.Level.SEVERE,
                    null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddUpdateKHForm.class.getName()).log(java.util.logging.Level.SEVERE,
                    null, ex);
        }
        // </editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddUpdateKHForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel information_panel;
    private javax.swing.JButton saveBtn;
    private javax.swing.JTextField txt_diachi;
    private javax.swing.JTextField txt_makh;
    private javax.swing.JTextField txt_sdt;
    private javax.swing.JTextField txt_tenkh;
    // End of variables declaration//GEN-END:variables
}
