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
public class PhieuNhapModel {
    private String MaPhieuNhap;
    private String MaNCC;
    private String MaND;
    private String NgayTao;
    private String TrangThai;
    private String NgayNhap;
    private long TongTien;
    ArrayList<SanPhamModel> DanhSachSP = new ArrayList<>();

    public PhieuNhapModel() {
        DanhSachSP = new ArrayList<>();
    }

    public PhieuNhapModel(String MaPhieuNhap, String MaNCC, String MaND, String NgayTao, String TrangThai, String NgayNhap, long TongTien) {
        this.MaPhieuNhap = MaPhieuNhap;
        this.MaNCC = MaNCC;
        this.MaND = MaND;
        this.NgayTao = NgayTao;
        this.TrangThai = TrangThai;
        this.NgayNhap = NgayNhap;
        this.TongTien = TongTien;
    }

    public String getMaPhieuNhap() {
        return MaPhieuNhap;
    }

    public void setMaPhieuNhap(String MaPhieuNhap) {
        this.MaPhieuNhap = MaPhieuNhap;
    }

    public String getMaNCC() {
        return MaNCC;
    }

    public void setMaNCC(String MaNCC) {
        this.MaNCC = MaNCC;
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

    public String getNgayNhap() {
        return NgayNhap;
    }

    public void setNgayNhap(String NgayNhap) {
        this.NgayNhap = NgayNhap;
    }

    public ArrayList<SanPhamModel> getDanhSachSP() {
        return DanhSachSP;
    }

    public void setDanhSachSP(ArrayList<SanPhamModel> DanhSachSP) {
        this.DanhSachSP = DanhSachSP;
    }

    public long getTongTien() {
        return TongTien;
    }

    public void setTongTien(long TongTien) {
        this.TongTien = TongTien;
    }

    
    public static ArrayList<PhieuNhapModel> getDanhSachPhieuNhap(String query) {
        Connection conn = BaseModel.getConnection();
        ArrayList<PhieuNhapModel> listPN = new ArrayList<>(); 
        try{
            //String query = "select * from PhieuNhap"; 
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query); 
            while (rs.next()) {
                PhieuNhapModel pn = new PhieuNhapModel();
                
                pn.setMaPhieuNhap(rs.getString(1));
                pn.setNgayTao(rs.getDate(2).toString());
                pn.setMaNCC(rs.getString(3));
                pn.setMaND(rs.getString(4));
                if(rs.getBoolean(5)) {
                    pn.setTrangThai("Da nhap");
                }
                else pn.setTrangThai("Chua nhap");
                if(rs.getDate(6) == null) {
                    pn.setNgayNhap("");
                } else pn.setNgayNhap(rs.getDate(6).toString());
                pn.setTongTien(rs.getLong(7));
                listPN.add(pn); 
            }
            return listPN; 
        } catch (SQLException e) {
        }
        return listPN; 
    }
    
    public static PhieuNhapModel getChiTietPhieuNhap(String query){
        PhieuNhapModel px = new PhieuNhapModel();
        Connection conn = BaseModel.getConnection();
        try{
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                SanPhamModel sp = new SanPhamModel();                
                sp.setMaSP(rs.getString(1));
                sp.setSoLuong(rs.getInt(2));
                px.setMaPhieuNhap(rs.getString(3));
                px.DanhSachSP.add(sp);
            }
            return px;
        } catch (SQLException e) {
        }
        return null;
    }
    
    public static ArrayList<PhieuNhapModel> getChiTietSP(String MaSP){
        ArrayList<PhieuNhapModel> lpx = new ArrayList<>();
        Connection conn = BaseModel.getConnection();
        try{
            String query = "select ma_phieu_nhap, so_luong from ChiTietPhieuNhap where ma_sp = '" + MaSP + "'"; 
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                PhieuNhapModel px = new PhieuNhapModel();
                SanPhamModel sp = new SanPhamModel();                
                px.setMaPhieuNhap(rs.getString(1));
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
    
    public static boolean addPhieuNhap(PhieuNhapModel px) {
        Connection conn = BaseModel.getConnection();
        try {
            String query = "INSERT INTO PhieuNhap(ma_phieu_nhap, ma_ncc, ma_nd, ngay_tao) VALUES('" + px.getMaPhieuNhap()+ "','" + px.getMaNCC()+ "','" + px.getMaND()+"',default)";
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            return true;
        } catch (SQLException e) {
        }
        return false;
    }
    
    public static boolean updatePhieuNhap(PhieuNhapModel px){
        Connection conn = BaseModel.getConnection();
        int tt;
        if(null == px.getTrangThai())return false; else switch (px.getTrangThai()) {
                case "Da nhap" -> tt = 1;
                case "Chua nhap" -> tt = 0;
                default -> {
                    return false;
                }
            }
        try {
            String query;
            if(tt == 0) {
               query = "update PhieuNhap set ma_ncc = '" + px.getMaNCC()+"', ma_nd = '" + px.getMaND()+"', trang_thai = '" + tt + "' where ma_phieu_nhap = '" + px.getMaPhieuNhap()+ "'";
            } else {
                 query = "update PhieuNhap set ma_ncc = '" + px.getMaNCC()+"', ma_nd = '" + px.getMaND()+"', trang_thai = '" + tt +"', ngay_nhap = '"+ LocalDate.now() +"'  where ma_phieu_nhap = '" + px.getMaPhieuNhap()+ "'";
            }
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            return true;
        } catch (SQLException e) {
        }
        return false;
    }
    
    public static boolean deletePhieuNhap(String MaPhieuNhap){
        Connection conn = BaseModel.getConnection();
        try {
            String query = "delete from PhieuNhap where ma_phieu_nhap = '" + MaPhieuNhap + "'";
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            return true;
        } catch (SQLException e) {
        }
        return false;
    }
    
    public static boolean addChiTietPhieuNhap(SanPhamModel sp, String MaPhieuNhap) {
        Connection conn = BaseModel.getConnection();
        try {
            String query = "INSERT INTO ChiTietPhieuNhap VALUES('" + MaPhieuNhap + "','" + sp.getMaSP() + "','" + sp.getSoLuong() +  "')";
            System.out.println(query);
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            return true;
        } catch (SQLException e) {
        }
        return false;
    }
    
    public static boolean updateChiTietPhieuNhap(SanPhamModel sp, String MaPhieuNhap){
        Connection conn = BaseModel.getConnection();
        try {
            String query = "update ChiTietPhieuNhap set so_luong = " + sp.getSoLuong() + " where ma_phieu_nhap = '" + MaPhieuNhap + "' and ma_sp = '" + sp.getMaSP()+ "'";
            System.out.println(query);
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            return true;
        } catch (SQLException e) {
        }
        return false;
    }
    
    public static boolean deleteChiTietPhieuNhap(String MaSP, String MaPhieuNhap){
        Connection conn = BaseModel.getConnection();
        try {
            String query = "delete from ChiTietPhieuNhap where ma_phieu_nhap = '" + MaPhieuNhap + "' and ma_sp = '" + MaSP + "'";
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            return true;
        } catch (SQLException e) {
        }
        return false;
    }
}
