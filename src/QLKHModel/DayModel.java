/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QLKHModel;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author trinh
 */
public class DayModel {
    private String MaDay;
    private int SucChua;
    private String Khu;
    private int Trong;
    private ArrayList<SanPhamModel> DanhSachSP = new ArrayList<>();

    public DayModel(String MaDay, int SucChua, String Khu, int Trong) {
        this.MaDay = MaDay;
        this.SucChua = SucChua;
        this.Khu = Khu;
        this.Trong = Trong;
        DanhSachSP = new ArrayList<>();
    }

    public DayModel() {
        DanhSachSP = new ArrayList<>();
    }

    public String getMaDay() {
        return MaDay;
    }

    public void setMaDay(String MaDay) {
        this.MaDay = MaDay;
    }

    public int getSucChua() {
        return SucChua;
    }

    public void setSucChua(int SucChua) {
        this.SucChua = SucChua;
    }

    public String getKhu() {
        return Khu;
    }

    public void setKhu(String Khu) {
        this.Khu = Khu;
    }

    public int getTrong() {
        return Trong;
    }

    public void setTrong(int Trong) {
        this.Trong = Trong;
    }

    public ArrayList<SanPhamModel> getDanhSachSP() {
        return DanhSachSP;
    }
 
    public static ArrayList<DayModel> getDanhSachDay(String query) {
        Connection conn = BaseModel.getConnection();
        ArrayList<DayModel> listDay = new ArrayList<>(); 
        try{
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query); 
            while (rs.next()) {
                DayModel d = new DayModel();
                d.setMaDay(rs.getString(1));
                d.setSucChua(rs.getInt(2));
                d.setKhu(rs.getString(3));
                if(rs.getString(4) == null){
                    d.setTrong(rs.getInt(2));
                } else d.setTrong(rs.getInt(4));
                
                listDay.add(d); 
            }
            return listDay;  
        } catch (SQLException e) {
        }
        return listDay;  
    }
    
    public static DayModel getChiTietDay(String query){
        DayModel d = new DayModel();
        Connection conn = BaseModel.getConnection();
        try{
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                SanPhamModel sp = new SanPhamModel();                
                sp.setMaSP(rs.getString(1));
                sp.setSoLuong(rs.getInt(2));
                sp.setGhiChu(rs.getString(3));
                d.setMaDay(rs.getString(4));
                d.DanhSachSP.add(sp);
            }
            return d;
        } catch (SQLException e) {
        }
        return null;
    }
    
    public static ArrayList<DayModel> getChiTietSP(String MaSP){
        ArrayList<DayModel> ld = new ArrayList<>();
        Connection conn = BaseModel.getConnection();
        try{
            String query = "select ma_day, so_luong, ghi_chu from ChiTietDay where ma_sp = '" + MaSP + "'"; 
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                DayModel d = new DayModel();
                SanPhamModel sp = new SanPhamModel();                
                d.setMaDay(rs.getString(1));
                sp.setMaSP(MaSP);
                sp.setSoLuong(rs.getInt(2));
                sp.setGhiChu(rs.getString(3));
                d.DanhSachSP.add(sp);
                ld.add(d);
            }
            return ld;
        } catch (SQLException e) {
        }
        return null;
    }
    
    public static boolean addDay(DayModel d) {
        Connection conn = BaseModel.getConnection();
        try {
            String query = "INSERT INTO DaySP(ma_day, suc_chua, khu) VALUES('" + d.getMaDay()+ "','" + d.getSucChua()+ "','" + d.getKhu()+ "')";
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            return true;
        } catch (SQLException e) {
        }
        return false;
    }
    
    public static boolean updateDay(DayModel d){
        Connection conn = BaseModel.getConnection();
        try {
            String query = "update DaySP set khu = '" + d.getKhu()+"', suc_chua = " + d.getSucChua()+ " where ma_day = '" + d.getMaDay()+ "'";
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            return true;
        } catch (SQLException e) {
        }
        return false;
    }
    
    public static boolean deleteDay(String MaDay){
        Connection conn = BaseModel.getConnection();
        try {
            String query = "delete from DaySP where ma_day = '" + MaDay + "'";
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            return true;
        } catch (SQLException e) {
        }
        return false;
    }
    
    public static boolean addChiTietDay(SanPhamModel sp, String MaDay) {
        Connection conn = BaseModel.getConnection();
        try {
            String query = "INSERT INTO ChiTietDay VALUES('" + sp.getMaSP()+ "','" + MaDay + "'," + sp.getSoLuong() + ",'" + sp.getGhiChu()+ "')";
            System.out.println(query);
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            return true;
        } catch (SQLException e) {
        }
        return false;
    }
    
    public static boolean updateChiTietDay(SanPhamModel sp, String MaDay){
        Connection conn = BaseModel.getConnection();
        try {
            String query = "update ChiTietDay set so_luong = " + sp.getSoLuong() + ", ghi_chu = '" + sp.getGhiChu() + "' where ma_day = '" + MaDay + "' and ma_sp = '" + sp.getMaSP()+ "'";
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            return true;
        } catch (SQLException e) {
        }
        return false;
    }
    
    public static boolean deleteChiTietDay(String MaSP, String MaDay){
        Connection conn = BaseModel.getConnection();
        try {
            String query = "delete from ChiTietDay where ma_day = '" + MaDay + "' and ma_sp = '" + MaSP + "'";
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            return true;
        } catch (SQLException e) {
        }
        return false;
    }
    
}
