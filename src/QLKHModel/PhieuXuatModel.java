/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QLKHModel;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author VTTDung
 * 
 */
public class PhieuXuatModel {
    private String MaPhieuXuat;
    private String MaCH;
    private String MaND;
    private String NgayTao;
    private String TrangThai;
    private String NgayXuat;
    ArrayList<SanPhamModel> DanhSachSP = new ArrayList<>();

    public PhieuXuatModel() {
        DanhSachSP = new ArrayList<>();
    }

    public PhieuXuatModel(String MaPhieuXuat, String MaCH, String MaND, String NgayTao, String TrangThai, String NgayXuat) {
        this.MaPhieuXuat = MaPhieuXuat;
        this.MaCH = MaCH;
        this.MaND = MaND;
        this.NgayTao = NgayTao;
        this.TrangThai = TrangThai;
        this.NgayXuat = NgayXuat;
    }

    public String getMaPhieuXuat() {
        return MaPhieuXuat;
    }

    public void setMaPhieuXuat(String MaPhieuXuat) {
        this.MaPhieuXuat = MaPhieuXuat;
    }

    public String getMaCH() {
        return MaCH;
    }

    public void setMaCH(String MaCH) {
        this.MaCH = MaCH;
    }

    public String getMaND() {
        return MaND;
    }

    public void setMaND(String MaND) {
        this.MaND = MaND;
    }

    public String getNgayTao() {
        return NgayTao;
    }

    public void setNgayTao(String NgayTao) {
        this.NgayTao = NgayTao;
    }

    public String getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(String TrangThai) {
        this.TrangThai = TrangThai;
    }

    public String getNgayXuat() {
        return NgayXuat;
    }

    public void setNgayXuat(String NgayXuat) {
        this.NgayXuat = NgayXuat;
    }

    public ArrayList<SanPhamModel> getDanhSachSP() {
        return DanhSachSP;
    }

    public void setDanhSachSP(ArrayList<SanPhamModel> DanhSachSP) {
        this.DanhSachSP = DanhSachSP;
    }

    
    
    public static ArrayList<PhieuXuatModel> getDanhSachPhieuXuat(String query) {
        Connection conn = BaseModel.getConnection();
        ArrayList<PhieuXuatModel> listPX = new ArrayList<>(); 
        try{
            //String query = "select * from PhieuXuat"; 
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query); 
            while (rs.next()) {
                PhieuXuatModel px = new PhieuXuatModel();
                
                px.setMaPhieuXuat(rs.getString(1));
                px.setNgayTao(rs.getDate(2).toString());
                px.setMaCH(rs.getString(3));
                px.setMaND(rs.getString(4));
                if(rs.getBoolean(5)) {
                    px.setTrangThai("Da xuat");
                }
                else px.setTrangThai("Chua xuat");
                if(rs.getDate(6) == null) {
                    px.setNgayXuat("");
                 } else px.setNgayXuat(rs.getDate(6).toString());
                listPX.add(px); 
            }
            return listPX; 
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listPX; 
    }
    
    public static PhieuXuatModel getChiTietPhieuXuat(String query){
        PhieuXuatModel px = new PhieuXuatModel();
        Connection conn = BaseModel.getConnection();
        try{
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                SanPhamModel sp = new SanPhamModel();                
                sp.setMaSP(rs.getString(1));
                sp.setSoLuong(rs.getInt(2));
                px.setMaPhieuXuat(rs.getString(3));
                px.DanhSachSP.add(sp);
            }
            return px;
        } catch (SQLException e) {
        }
        return null;
    }
    
    public static ArrayList<PhieuXuatModel> getChiTietSP(String MaSP){
        ArrayList<PhieuXuatModel> lpx = new ArrayList<>();
        Connection conn = BaseModel.getConnection();
        try{
            String query = "select ma_phieu_xuat, so_luong from ChiTietPhieuXuat where ma_sp = '" + MaSP + "'"; 
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                PhieuXuatModel px = new PhieuXuatModel();
                SanPhamModel sp = new SanPhamModel();                
                px.setMaPhieuXuat(rs.getString(1));
                sp.setMaSP(MaSP);
                sp.setSoLuong(rs.getInt(2));
                px.DanhSachSP.add(sp);
                lpx.add(px);
            }
            return lpx;
        } catch (SQLException e) {
        }
        return null;
    }
    
    public static boolean addPhieuXuat(PhieuXuatModel px) {
        Connection conn = BaseModel.getConnection();
        try {
            String query = "INSERT INTO PhieuXuat(ma_phieu_xuat, ma_ch, ma_nd,ngay_tao) VALUES('" + px.getMaPhieuXuat()+ "','" + px.getMaCH()+ "','" + px.getMaND()+"',default)";
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            return true;
        } catch (SQLException e) {
        }
        return false;
    }
    
    public static boolean updatePhieuXuat(PhieuXuatModel px){
        Connection conn = BaseModel.getConnection();
        int tt;
        if(null == px.getTrangThai())return false; else switch (px.getTrangThai()) {
                case "Da xuat" -> tt = 1;
                case "Chua xuat" -> tt = 0;
                default -> {
                    return false;
                }
            }
        try {
            String query;
            if(tt == 0) {
               query = "update PhieuXuat set ma_ch = '" + px.getMaCH()+"', ma_nd = '" + px.getMaND()+"', trang_thai = '" + tt + "' where ma_phieu_xuat = '" + px.getMaPhieuXuat()+ "'";
            } else {
                 query = "update PhieuXuat set ma_ch = '" + px.getMaCH()+"', ma_nd = '" + px.getMaND()+"', trang_thai = '" + tt +"', ngay_xuat = '"+ LocalDate.now() +"'  where ma_phieu_xuat = '" + px.getMaPhieuXuat()+ "'";
            }
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            return true;
        } catch (SQLException e) {
        }
        return false;
    }
    
    public static boolean deletePhieuXuat(String MaPhieuXuat){
        Connection conn = BaseModel.getConnection();
        try {
            String query = "delete from PhieuXuat where ma_phieu_xuat = '" + MaPhieuXuat + "'";
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            return true;
        } catch (SQLException e) {
        }
        return false;
    }
    
    public static boolean addChiTietPhieuXuat(SanPhamModel sp, String MaPhieuXuat) {
        Connection conn = BaseModel.getConnection();
        try {
            String query = "INSERT INTO ChiTietPhieuXuat VALUES('" + MaPhieuXuat + "','" + sp.getMaSP() + "','" + sp.getSoLuong() +  "')";
            System.out.println(query);
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            return true;
        } catch (SQLException e) {
        }
        return false;
    }
    
    public static boolean updateChiTietPhieuXuat(SanPhamModel sp, String MaPhieuXuat){
        Connection conn = BaseModel.getConnection();
        try {
            String query = "update ChiTietPhieuXuat set so_luong = " + sp.getSoLuong() + " where ma_phieu_xuat = '" + MaPhieuXuat + "' and ma_sp = '" + sp.getMaSP()+ "'";
            System.out.println(query);
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            return true;
        } catch (SQLException e) {
        }
        return false;
    }
    
    public static boolean deleteChiTietPhieuXuat(String MaSP, String MaPhieuXuat){
        Connection conn = BaseModel.getConnection();
        try {
            String query = "delete from ChiTietPhieuXuat where ma_phieu_xuat = '" + MaPhieuXuat + "' and ma_sp = '" + MaSP + "'";
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            return true;
        } catch (SQLException e) {
        }
        return false;
    }
}
