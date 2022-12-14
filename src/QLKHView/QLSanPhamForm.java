/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package QLKHView;
import QLKHController.*;
import QLKHController.SanPhamController;
import QLKHModel.SanPhamModel;
import static QLKHView.indexFormQL.temp;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author VTTDung
 */
public class QLSanPhamForm extends javax.swing.JFrame {

    /**
     * Creates new form QLSanPhamForm
     */
    public static boolean check;
    final static String header[] = {"Mã SP","Tên SP","Loại","Hãng","Màu","Kiểu dáng","Chất liệu","Đơn giá","Đơn vị","Số lượng"};
    final static DefaultTableModel tb = new DefaultTableModel(header, 0);
    public QLSanPhamForm() {
        initComponents();
        loadTableSanPham(SanPhamController.listSanPham(""));
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
        lblSanPham = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbSanPham = new javax.swing.JTable(){
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        txtMaSanPham = new QLKHView.swing.TextField();
        txtTenSanPham = new QLKHView.swing.TextField();
        cboLoai = new QLKHView.swing.Combobox();
        txtHang = new QLKHView.swing.TextField();
        spnDonGia = new QLKHView.swing.Spinner();
        cboDonVi = new QLKHView.swing.Combobox();
        txtMauSac = new QLKHView.swing.TextField();
        txtKieuDang = new QLKHView.swing.TextField();
        txtChatLieu = new QLKHView.swing.TextField();
        btnQuayLai = new QLKHView.swing.Button();
        btnThem = new QLKHView.swing.Button();
        btnTim = new QLKHView.swing.Button();
        btnSua = new QLKHView.swing.Button();
        btnXoa = new QLKHView.swing.Button();
        btnLamMoi = new QLKHView.swing.Button();
        btnXemChiTiet = new QLKHView.swing.Button();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblSanPham.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        lblSanPham.setText("QUẢN LÝ SẢN PHẨM");
        jPanel1.add(lblSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(422, 34, -1, -1));

        tbSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã SP", "Tên SP", "Loại", "Hãng", "Màu", "Kiểu dáng", "Chất liệu", "Đơn giá", "Đơn vị", "Số lượng"
            }
        ));
        tbSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbSanPhamMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbSanPham);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 412, 1210, 190));

        txtMaSanPham.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtMaSanPham.setLabelText("Mã sản phẩm");
        jPanel1.add(txtMaSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 130, 249, -1));

        txtTenSanPham.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtTenSanPham.setLabelText("Tên sản phẩm");
        jPanel1.add(txtTenSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 200, 249, -1));

        cboLoai.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "*", "Xe dap", "Phu tung" }));
        cboLoai.setLabeText("Loại");
        jPanel1.add(cboLoai, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 280, 250, 61));

        txtHang.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtHang.setLabelText("Hãng");
        jPanel1.add(txtHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 130, 249, -1));

        spnDonGia.setLabelText("Đơn giá");
        jPanel1.add(spnDonGia, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 200, 249, -1));

        cboDonVi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "* ", "Chiec", "Thung" }));
        cboDonVi.setLabeText("Đơn vị");
        jPanel1.add(cboDonVi, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 280, 249, 61));

        txtMauSac.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtMauSac.setLabelText("Màu sắc");
        jPanel1.add(txtMauSac, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 130, 250, -1));

        txtKieuDang.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtKieuDang.setLabelText("Kiểu dáng");
        jPanel1.add(txtKieuDang, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 200, 250, -1));

        txtChatLieu.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtChatLieu.setLabelText("Chất liệu");
        jPanel1.add(txtChatLieu, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 280, 250, 58));

        btnQuayLai.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/return.png"))); // NOI18N
        btnQuayLai.setText("Quay lại");
        btnQuayLai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuayLaiActionPerformed(evt);
            }
        });
        jPanel1.add(btnQuayLai, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 50, 80, -1));

        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/plus.png"))); // NOI18N
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });
        jPanel1.add(btnThem, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 370, 80, -1));

        btnTim.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/find.png"))); // NOI18N
        btnTim.setText("Tìm");
        btnTim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimActionPerformed(evt);
            }
        });
        jPanel1.add(btnTim, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 370, 80, -1));

        btnSua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/edit.png"))); // NOI18N
        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });
        jPanel1.add(btnSua, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 370, 80, -1));

        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/clear.png"))); // NOI18N
        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed1(evt);
            }
        });
        jPanel1.add(btnXoa, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 370, 80, -1));

        btnLamMoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/refresh.png"))); // NOI18N
        btnLamMoi.setText("Làm mới");
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });
        jPanel1.add(btnLamMoi, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 370, 80, -1));

        btnXemChiTiet.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/detail.png"))); // NOI18N
        btnXemChiTiet.setText("Xem chi tiết");
        btnXemChiTiet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXemChiTietActionPerformed(evt);
            }
        });
        jPanel1.add(btnXemChiTiet, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 370, 100, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QLKHView/image/milad-fakurian-wxvYVpjWxg4-unsplash.jpg"))); // NOI18N
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -50, 1200, 600));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1204, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tbSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbSanPhamMouseClicked
        // TODO add your handling code here:
        boolean checkClick = tbSanPham.isEditing();
        if (checkClick == false){
            JTable target = (JTable) evt.getSource();
            int rowIndex = target.getSelectedRow();
            int col = 0;
            txtMaSanPham.setText((String)tbSanPham.getValueAt(rowIndex,col));
            txtTenSanPham.setText((String)tbSanPham.getValueAt(rowIndex,col+1));
            cboLoai.setSelectedItem((String)tbSanPham.getValueAt(rowIndex,col+2));
            txtHang.setText((String)tbSanPham.getValueAt(rowIndex,col+3));
            spnDonGia.setValue(Integer.valueOf((String)tbSanPham.getValueAt(rowIndex,col+7)));
            cboDonVi.setSelectedItem((String)tbSanPham.getValueAt(rowIndex,col+8));
            txtMauSac.setText((String)tbSanPham.getValueAt(rowIndex,col+4));
            txtKieuDang.setText((String)tbSanPham.getValueAt(rowIndex,col+5));
            txtChatLieu.setText((String)tbSanPham.getValueAt(rowIndex,col+6));
            txtMaSanPham.setEnabled(false);
            check = true;
        }
    }//GEN-LAST:event_tbSanPhamMouseClicked

    private void btnQuayLaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuayLaiActionPerformed
        // TODO add your handling code here:
        QLSanPhamForm.check = false;
        this.dispose();
        if(temp != null){
            temp.setVisible(true);
            temp = null;
        }
        else indexFormNVKK.runIndexNVKK();
    }//GEN-LAST:event_btnQuayLaiActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        new SanPhamController(this).doAddSanPham();
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnTimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimActionPerformed
        // TODO add your handling code here:
        new SanPhamController(this).doSearchSanPham();
    }//GEN-LAST:event_btnTimActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
        new SanPhamController(this).doUpdateSanPham();
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaActionPerformed1(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed1
        // TODO add your handling code here:
        new SanPhamController(this).doDeleteSanPham();
        btnLamMoiActionPerformed(evt);
    }//GEN-LAST:event_btnXoaActionPerformed1

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        // TODO add your handling code here:
        txtMaSanPham.setEnabled(true);
        check = false;
        txtChatLieu.setText("");
        txtHang.setText("");
        txtKieuDang.setText("");
        txtMaSanPham.setText("");
        txtMauSac.setText("");
        txtTenSanPham.setText("");
        cboDonVi.setSelectedIndex(0);
        cboLoai.setSelectedIndex(0);
        spnDonGia.setValue(0);
        tb.getDataVector().removeAllElements();
        loadTableSanPham(SanPhamController.listSanPham(""));
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void btnXemChiTietActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXemChiTietActionPerformed
        // TODO add your handling code here:
        new SanPhamController(this).doViewDetailSP(txtMaSanPham.getText());
    }//GEN-LAST:event_btnXemChiTietActionPerformed
    public static void loadTableSanPham(ArrayList<SanPhamModel> lsp){
        tb.getDataVector().removeAllElements();
        for(int i=0; i<lsp.size(); i++){
            String[] sp = {lsp.get(i).getMaSP(), lsp.get(i).getTenSP(), lsp.get(i).getLoai(), lsp.get(i).getHang(), lsp.get(i).getMauSac(), lsp.get(i).getKieuDang(), lsp.get(i).getChatLieu(), Integer.toString(lsp.get(i).getDonGia()), lsp.get(i).getDonVi(), Integer.toString(lsp.get(i).getSoLuong())};
            tb.addRow(sp);
            tbSanPham.setModel(tb);
        }
    }
    public static boolean xacNhanXoa(){
        int input = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa không?","Thông báo",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
        return input == JOptionPane.YES_OPTION;
    }
    
    public void showMessage(String msg, boolean checkMsg){
        if(checkMsg){
            JOptionPane.showMessageDialog(this, msg,"Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } else JOptionPane.showMessageDialog(this, msg,"Thông báo", JOptionPane.ERROR_MESSAGE);
    }
    
    public static SanPhamModel getSanPhamInput(){
       SanPhamModel sp = new SanPhamModel();
       sp.setMaSP(txtMaSanPham.getText());
       sp.setTenSP(txtTenSanPham.getText());
       sp.setLoai(cboLoai.getSelectedItem().toString());
       sp.setHang(txtHang.getText());
       sp.setDonGia((int) spnDonGia.getValue());
       sp.setDonVi(cboDonVi.getSelectedItem().toString());
       sp.setMauSac(txtMauSac.getText());
       sp.setKieuDang(txtKieuDang.getText());
       sp.setChatLieu(txtChatLieu.getText());
       return sp;
    }
    
    /**
     */
    public static void runQLSanPham() {
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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QLSanPhamForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new QLSanPhamForm().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private QLKHView.swing.Button btnLamMoi;
    private QLKHView.swing.Button btnQuayLai;
    private QLKHView.swing.Button btnSua;
    private QLKHView.swing.Button btnThem;
    private QLKHView.swing.Button btnTim;
    private QLKHView.swing.Button btnXemChiTiet;
    private QLKHView.swing.Button btnXoa;
    protected static QLKHView.swing.Combobox cboDonVi;
    protected static QLKHView.swing.Combobox cboLoai;
    private javax.swing.JLabel jLabel2;
    protected static javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblSanPham;
    protected static QLKHView.swing.Spinner spnDonGia;
    protected static javax.swing.JTable tbSanPham;
    protected static QLKHView.swing.TextField txtChatLieu;
    protected static QLKHView.swing.TextField txtHang;
    protected static QLKHView.swing.TextField txtKieuDang;
    protected static QLKHView.swing.TextField txtMaSanPham;
    protected static QLKHView.swing.TextField txtMauSac;
    protected static QLKHView.swing.TextField txtTenSanPham;
    // End of variables declaration//GEN-END:variables
}
