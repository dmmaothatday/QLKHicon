/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QLKHModel;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author trinh
 */
public class SanPhamModel {
    private String MaSP;
    private String TenSP;
    private String Loai;
    private String Hang;
    private String KieuDang;
    private String MauSac;
    private int DonGia;
    private String DonVi;
    private String ChatLieu;
    private int SoLuong;
    private String GhiChu;

    public SanPhamModel() {
    }

    public SanPhamModel(String MaSP, String TenSP, String Loai, String Hang, String KieuDang, String MauSac, int DonGia, String DonVi, String ChatLieu, int SoLuong) {
        this.MaSP = MaSP;
        this.TenSP = TenSP;
        this.Loai = Loai;
        this.Hang = Hang;
        this.KieuDang = KieuDang;
        this.MauSac = MauSac;
        this.DonGia = DonGia;
        this.DonVi = DonVi;
        this.ChatLieu = ChatLieu;
        this.SoLuong = SoLuong;
    }

    public String getMaSP() {
        return MaSP;
    }

    public void setMaSP(String MaSP) {
        this.MaSP = MaSP;
    }

    public String getTenSP() {
        return TenSP;
    }

    public void setTenSP(String TenSP) {
        this.TenSP = TenSP;
    }

    public String getLoai() {
        return Loai;
    }

    public void setLoai(String Loai) {
        this.Loai = Loai;
    }

    public String getHang() {
        return Hang;
    }

    public void setHang(String Hang) {
        this.Hang = Hang;
    }

    public String getKieuDang() {
        return KieuDang;
    }

    public void setKieuDang(String KieuDang) {
        this.KieuDang = KieuDang;
    }

    public String getMauSac() {
        return MauSac;
    }

    public void setMauSac(String MauSac) {
        this.MauSac = MauSac;
    }

    public int getDonGia() {
        return DonGia;
    }

    public void setDonGia(int DonGia) {
        this.DonGia = DonGia;
    }

    public String getDonVi() {
        return DonVi;
    }

    public void setDonVi(String DonVi) {
        this.DonVi = DonVi;
    }

    public String getChatLieu() {
        return ChatLieu;
    }

    public void setChatLieu(String ChatLieu) {
        this.ChatLieu = ChatLieu;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int SoLuong) {
        this.SoLuong = SoLuong;
    }

    public String getGhiChu() {
        return GhiChu;
    }

    public void setGhiChu(String GhiChu) {
        this.GhiChu = GhiChu;
    }
    
    
    
    public static ArrayList<SanPhamModel> getDanhSachSanPham(String query) {
        Connection conn = BaseModel.getConnection();
        ArrayList<SanPhamModel> listSP = new ArrayList<>(); 
        try{
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query); 
            while (rs.next()) {
                SanPhamModel sp = new SanPhamModel();
                sp.setMaSP(rs.getString(1));
                sp.setTenSP(rs.getString(2));
                sp.setLoai(rs.getString(3));
                sp.setHang(rs.getString(4));
                sp.setMauSac(rs.getString(5));
                sp.setKieuDang(rs.getString(6));
                sp.setChatLieu(rs.getString(7));
                sp.setDonGia(rs.getInt(8));
                sp.setDonVi(rs.getString(9));
                sp.setSoLuong(rs.getInt(10));
                listSP.add(sp); 
            }
            return listSP;  
        } catch (SQLException e) {
        }
        return listSP;  
    }
    
    public static boolean addSanPham(SanPhamModel sp) {
            Connection conn = BaseModel.getConnection();
            try {
                String query = "INSERT INTO SanPham(ma_sp, ten_sp, loai_sp, hang, mau_sac, kieu_dang, chat_lieu, don_gia, don_vi) VALUES('" + sp.getMaSP()+ "','" + sp.getTenSP()+ "','" + sp.getLoai()+ "','" + sp.getHang()+ "','" + sp.getMauSac()+ "','" + sp.getKieuDang()+ "','" + sp.getChatLieu() + "','" + sp.getDonGia() + "','" + sp.getDonVi()+ "')";
                //System.out.println(query);
                //System.exit(0);
                Statement st = conn.createStatement();
                st.executeUpdate(query);
                return true;
            } catch (SQLException e) {
            }
            return false;
    }
    
    public static boolean deleteSanPham(String MaSP){
        Connection conn = BaseModel.getConnection();
        try {
            String query = "delete from SanPham where ma_sp = '" + MaSP + "'";
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            return true;
        } catch (SQLException e) {
        }
        return false;
    }
    
    public static boolean updateSanPham(SanPhamModel sp){
        Connection conn = BaseModel.getConnection();
        try {
            String query = "update SanPham set ten_sp = '" + sp.getTenSP() +"', loai_sp = '" + sp.getLoai() + "', hang = '" + sp.getHang() + "', don_vi = '" + sp.getDonVi() + "', don_gia = '" + sp.getDonGia() + "', chat_lieu = '" + sp.getChatLieu() + "', kieu_dang = '" + sp.getKieuDang() + "', mau_sac = '" + sp.getMauSac() + "' where ma_sp = '" + sp.getMaSP() + "'";
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            return true;
        } catch (SQLException e) {
        }
        return false;
    }
   
}
