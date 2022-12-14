/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package QLKHView;

import QLKHController.PhieuXuatController;
import QLKHModel.PhieuXuatModel;
import QLKHModel.SanPhamModel;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author VTTDung
 */
public class QLPhieuXuat_ChiTietForm extends javax.swing.JFrame {

    /**
     * Creates new form QLPhieuXuat_ChiTietForm
     */
    public static boolean check;
    final static String header[] = {"Mã sản phẩm","Số lượng"};
    final static DefaultTableModel tb = new DefaultTableModel(header, 0);
    
    public QLPhieuXuat_ChiTietForm(String MaPhieuXuat) {
        initComponents();
        PhieuXuatModel px = PhieuXuatController.listChiTietPhieuXuat(MaPhieuXuat,"","");
        txtMaPhieuXuat.setText(MaPhieuXuat);
        txtMaPhieuXuat.setEnabled(false);
        if("Da xuat".equals(QLPhieuXuatForm.cboTrangThai.getSelectedItem().toString())){
            btnThem.setEnabled(false);
            btnXoa.setEnabled(false);
            btnSua.setEnabled(false);
        }
        loadTableChiTietPhieuXuat(px.getDanhSachSP()); 
    }
    public static void loadTableChiTietPhieuXuat(ArrayList<SanPhamModel> ctpx){
        tb.getDataVector().removeAllElements();
        for(int i=0; i < ctpx.size(); i++){
            String[] px = {ctpx.get(i).getMaSP(), Integer.toString(ctpx.get(i).getSoLuong())};
            tb.addRow(px);
            tbChiTietPhieuXuat.setModel(tb);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblChiTietPhieuXuat = new javax.swing.JLabel();
        txtMaPhieuXuat = new javax.swing.JTextField();
        txtMaSanPham = new QLKHView.swing.TextField();
        spnSoLuong = new QLKHView.swing.Spinner();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbChiTietPhieuXuat = new javax.swing.JTable(){
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        btnDong = new QLKHView.swing.Button();
        btnThem = new QLKHView.swing.Button();
        btnTim = new QLKHView.swing.Button();
        btnSua = new QLKHView.swing.Button();
        btnXoa = new QLKHView.swing.Button();
        btnLamMoi = new QLKHView.swing.Button();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblChiTietPhieuXuat.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        lblChiTietPhieuXuat.setText("CHI TIẾT PHIẾU XUẤT");
        jPanel1.add(lblChiTietPhieuXuat, new org.netbeans.lib.awtextra.AbsoluteConstraints(421, 39, -1, -1));

        txtMaPhieuXuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaPhieuXuatActionPerformed(evt);
            }
        });
        jPanel1.add(txtMaPhieuXuat, new org.netbeans.lib.awtextra.AbsoluteConstraints(543, 105, 90, -1));

        txtMaSanPham.setLabelText("Mã sản phẩm");
        jPanel1.add(txtMaSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 200, 211, -1));

        spnSoLuong.setLabelText("Số lượng");
        jPanel1.add(spnSoLuong, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 200, 211, -1));

        tbChiTietPhieuXuat.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Sản phẩm", "Số lượng"
            }
        ));
        tbChiTietPhieuXuat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbChiTietPhieuXuatMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbChiTietPhieuXuat);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 390, 1200, 210));

        btnDong.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/close.png"))); // NOI18N
        btnDong.setText("Đóng");
        btnDong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDongActionPerformed(evt);
            }
        });
        jPanel1.add(btnDong, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 60, 80, -1));

        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/plus.png"))); // NOI18N
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });
        jPanel1.add(btnThem, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 330, 80, 30));

        btnTim.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/find.png"))); // NOI18N
        btnTim.setText("Tìm");
        btnTim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimActionPerformed(evt);
            }
        });
        jPanel1.add(btnTim, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 330, 80, 30));

        btnSua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/edit.png"))); // NOI18N
        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });
        jPanel1.add(btnSua, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 330, 80, 30));

        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/clear.png"))); // NOI18N
        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });
        jPanel1.add(btnXoa, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 330, 80, 30));

        btnLamMoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/refresh.png"))); // NOI18N
        btnLamMoi.setText("Làm mới");
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });
        jPanel1.add(btnLamMoi, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 330, 80, 30));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QLKHView/image/milad-fakurian-wxvYVpjWxg4-unsplash.jpg"))); // NOI18N
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1200, 600));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        // TODO add your handling code here:
        txtMaSanPham.setEnabled(true);
        check = false;
        tb.getDataVector().removeAllElements();
        loadTableChiTietPhieuXuat(PhieuXuatController.listChiTietPhieuXuat(txtMaPhieuXuat.getText(),"","").getDanhSachSP());
        txtMaSanPham.setText("");
        spnSoLuong.setValue(0);
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        new PhieuXuatController(this).doDeleteChiTietPhieuXuat(txtMaPhieuXuat.getText());
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
        new PhieuXuatController(this).doUpdateChiTietPhieuXuat(txtMaPhieuXuat.getText());
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnTimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimActionPerformed
        // TODO add your handling code here:
        new PhieuXuatController(this).doSearchChiTietPhieuXuat(txtMaPhieuXuat.getText());
    }//GEN-LAST:event_btnTimActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        new PhieuXuatController(this).doAddChiTietPhieuXuat(txtMaPhieuXuat.getText());
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnDongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDongActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnDongActionPerformed

    private void tbChiTietPhieuXuatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbChiTietPhieuXuatMouseClicked
        // TODO add your handling code here:
        boolean checkClick = tbChiTietPhieuXuat.isEditing();
        if (checkClick == false){
            JTable target = (JTable) evt.getSource();
            int rowIndex = target.getSelectedRow();
            txtMaSanPham.setText((String)tbChiTietPhieuXuat.getValueAt(rowIndex,0));
            spnSoLuong.setValue(Integer.valueOf((String)tbChiTietPhieuXuat.getValueAt(rowIndex,1)));
            txtMaSanPham.setEnabled(false);
            check = true;
        }
    }//GEN-LAST:event_tbChiTietPhieuXuatMouseClicked

    private void txtMaPhieuXuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaPhieuXuatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaPhieuXuatActionPerformed
    public static boolean xacNhanXoa(){
        int input = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa không?","Thông báo",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
        return input == JOptionPane.YES_OPTION;
    }
    
    public void showMessage(String msg, boolean checkMsg){
        if(checkMsg){
            JOptionPane.showMessageDialog(this, msg,"Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } else JOptionPane.showMessageDialog(this, msg,"Thông báo", JOptionPane.ERROR_MESSAGE);
    }    public static SanPhamModel getChiTietPhieuXuatInput(){
       SanPhamModel sp = new SanPhamModel();
       sp.setMaSP(txtMaSanPham.getText());
       sp.setSoLuong((int)spnSoLuong.getValue());
       return sp;
    }
    /**
     * @param args the command line arguments
     */
    public static void runViewDetailD(String MaPhieuXuat) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(QLDay_ChiTietForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QLDay_ChiTietForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QLDay_ChiTietForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QLDay_ChiTietForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new QLPhieuXuat_ChiTietForm(MaPhieuXuat).setVisible(true);
        });
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private QLKHView.swing.Button btnDong;
    private QLKHView.swing.Button btnLamMoi;
    private QLKHView.swing.Button btnSua;
    private QLKHView.swing.Button btnThem;
    private QLKHView.swing.Button btnTim;
    private QLKHView.swing.Button btnXoa;
    private javax.swing.JLabel jLabel2;
    private static javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblChiTietPhieuXuat;
    protected static QLKHView.swing.Spinner spnSoLuong;
    private static javax.swing.JTable tbChiTietPhieuXuat;
    private javax.swing.JTextField txtMaPhieuXuat;
    protected static QLKHView.swing.TextField txtMaSanPham;
    // End of variables declaration//GEN-END:variables
}
