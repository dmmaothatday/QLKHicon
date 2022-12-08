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
public class NguoiDungModel {

    private String MaND;
    private String TenND;
    private String ChucVu;
    private String MatKhau;
    private String SdtND;
    private String DiaChiND;
    private String NgaySinh;
    private String GioiTinh;
    private String CanCuoc;

    public NguoiDungModel() {
    }

    public NguoiDungModel(String MaND, String TenND, String ChucVu, String MatKhau, String SdtND, String DiaChiND, String NgaySinh, String GioiTinh, String CanCuoc) {
        this.MaND = MaND;
        this.TenND = TenND;
        this.ChucVu = ChucVu;
        this.MatKhau = MatKhau;
        this.SdtND = SdtND;
        this.DiaChiND = DiaChiND;
        this.NgaySinh = NgaySinh;
        this.GioiTinh = GioiTinh;
        this.CanCuoc = CanCuoc;
    }

    public String getMaND() {
        return MaND;
    }

    public void setMaND(String MaND) {
        this.MaND = MaND;
    }

    public String getTenND() {
        return TenND;
    }

    public void setTenND(String TenND) {
        this.TenND = TenND;
    }

    public String getChucVu() {
        return ChucVu;
    }

    public void setChucVu(String ChucVu) {
        this.ChucVu = ChucVu;
    }

    public String getMatKhau() {
        return MatKhau;
    }

    public void setMatKhau(String MatKhau) {
        this.MatKhau = MatKhau;
    }

    public String getSdtND() {
        return SdtND;
    }

    public void setSdtND(String SdtND) {
        this.SdtND = SdtND;
    }

    public String getDiaChiND() {
        return DiaChiND;
    }

    public void setDiaChiND(String DiaChiND) {
        this.DiaChiND = DiaChiND;
    }

    public String getNgaySinh() {
        return NgaySinh;
    }

    public void setNgaySinh(String NgaySinh) {
        this.NgaySinh = NgaySinh;
    }

    public String getGioiTinh() {
        return GioiTinh;
    }

    public void setGioiTinh(String GioiTinh) {
        this.GioiTinh = GioiTinh;
    }

    public String getCanCuoc() {
        return CanCuoc;
    }

    public void setCanCuoc(String CanCuoc) {
        this.CanCuoc = CanCuoc;
    }
    
    

    public static NguoiDungModel checkLogin(String maNd, String matKhau) {
        NguoiDungModel nd = new NguoiDungModel();
        try ( Connection conn = BaseModel.getConnection()) {
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery("SELECT ma_nd, chuc_vu from NguoiDung where ma_nd = '" + maNd + "' and mat_khau='" + matKhau + "'");
            if (!result.next()) {
            } else {
                nd.setMaND(result.getString(1));
                nd.setChucVu(result.getString(2));
                return nd;
            }
            return nd;
        } catch (SQLException e) {
        }
        return nd;
    }

    public static ArrayList<NguoiDungModel> getDanhSachNguoiDung(String query) {
        Connection conn = BaseModel.getConnection();
        ArrayList<NguoiDungModel> listND = new ArrayList<>();
        try {
 //           String query = "select * from NguoiDung";
 //           System.out.println(query);
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                NguoiDungModel nd = new NguoiDungModel();
                nd.setMaND(rs.getString(1));
                nd.setChucVu(rs.getString(2));
                nd.setMatKhau(rs.getString(3));
                nd.setTenND(rs.getString(4));
                nd.setSdtND(rs.getString(5));
                nd.setDiaChiND(rs.getString(6));
                nd.setNgaySinh(rs.getDate(7).toString());
                if (rs.getBoolean(8)){
                    nd.setGioiTinh("Nam");
                } else nd.setGioiTinh("Nữ");
                nd.setCanCuoc(rs.getString(9));
                listND.add(nd);
            }
            return listND;
        } catch (SQLException e) {
        }
        return listND;
    }

  
    public static boolean addNguoiDung(NguoiDungModel nd) {
            Connection conn = BaseModel.getConnection();
            int gt;
            if(null == nd.getGioiTinh())return false; else switch (nd.getGioiTinh()) {
                case "Nam" -> gt = 1;
                case "Nữ" -> gt = 0;
                default -> {
                    return false;
                }
            }
            try {
                String query = "INSERT INTO NguoiDung(ma_nd, chuc_vu, mat_khau, ten_nd, sdt, dia_chi, ngay_sinh, gioi_tinh, can_cuoc) VALUES('" + nd.getMaND() + "','" + nd.getChucVu() + "','" + nd.getMatKhau() + "','" + nd.getTenND() + "','" + nd.getSdtND() + "','" + nd.getDiaChiND() + "','" + nd.getNgaySinh() + "','" + gt + "','" + nd.getCanCuoc()+ "')";
                //System.out.println(query);
                //System.exit(0);
                Statement st = conn.createStatement();
                st.executeUpdate(query);
                return true;
            } catch (SQLException e) {
            }
            return false;
    }
    
    public static boolean updateNguoiDung(NguoiDungModel nd){
        Connection conn = BaseModel.getConnection();
        int gt;
        if(null == nd.getGioiTinh())return false; else switch (nd.getGioiTinh()) {
                case "Nam" -> gt = 1;
                case "Nữ" -> gt = 0;
                default -> {
                    return false;
                }
            }
        try {
            
            String query = "update NguoiDung set ten_nd = '" + nd.getTenND() +"', chuc_vu = '" + nd.getChucVu() + "', mat_khau = '" + nd.getMatKhau() + "', dia_chi = '" + nd.getDiaChiND()+ "', ngay_sinh = '" + nd.getNgaySinh() + "', sdt = '" + nd.getSdtND() + "', can_cuoc = '" + nd.getCanCuoc() + "', gioi_tinh = '" + gt + "' where ma_nd = '" + nd.getMaND() + "'";
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            return true;
        } catch (SQLException e) {
        }
        return false;
    }
    
    public static boolean deleteNguoiDung(String MaND){
        Connection conn = BaseModel.getConnection();
        try {
            String query = "delete from NguoiDung where ma_nd = '" + MaND + "'";
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            return true;
        } catch (SQLException e) {
        }
        return false;
    }

}
