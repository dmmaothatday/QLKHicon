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
public class NhaCungCapModel {
    private String MaNCC;
    private String TenNCC;
    private String DiaChiNCC;
    private String SdtNCC;

    public NhaCungCapModel() {
    }

    public NhaCungCapModel(String MaNCC, String TenNCC, String DiaChiNCC, String SdtNCC) {
        this.MaNCC = MaNCC;
        this.TenNCC = TenNCC;
        this.DiaChiNCC = DiaChiNCC;
        this.SdtNCC = SdtNCC;
    }

    public String getMaNCC() {
        return MaNCC;
    }

    public void setMaNCC(String MaNCC) {
        this.MaNCC = MaNCC;
    }

    public String getTenNCC() {
        return TenNCC;
    }

    public void setTenNCC(String TenNCC) {
        this.TenNCC = TenNCC;
    }

    public String getDiaChiNCC() {
        return DiaChiNCC;
    }

    public void setDiaChiNCC(String DiaChiNCC) {
        this.DiaChiNCC = DiaChiNCC;
    }

    public String getSdtNCC() {
        return SdtNCC;
    }

    public void setSdtNCC(String SdtNCC) {
        this.SdtNCC = SdtNCC;
    }
    
    
    public static ArrayList<NhaCungCapModel> getDanhSachNhaCungCap(String query) {
        Connection conn = BaseModel.getConnection();
        ArrayList<NhaCungCapModel> listNCC = new ArrayList<>(); 
        try{
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query); 
            while (rs.next()) {
                NhaCungCapModel ncc = new NhaCungCapModel();
                ncc.setMaNCC(rs.getString(1));
                ncc.setTenNCC(rs.getString(2));
                ncc.setSdtNCC(rs.getString(3));
                ncc.setDiaChiNCC(rs.getString(4));
                listNCC.add(ncc); 
            }
            return listNCC;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listNCC;  
    }
    
    public static boolean addNhaCungCap(NhaCungCapModel ncc) {
            Connection conn = BaseModel.getConnection();
            try {
                String query = "INSERT INTO NhaCungCap(ma_ncc, ten_ncc, sdt, dia_chi) VALUES('" + ncc.getMaNCC() + "','" + ncc.getTenNCC() + "','" + ncc.getSdtNCC() + "','" + ncc.getDiaChiNCC() + "')";
                //System.out.println(query);
                //System.exit(0);
                Statement st = conn.createStatement();
                st.executeUpdate(query);
                return true;
            } catch (SQLException e) {
            }
            return false;
    }
    
    public static boolean updateNhaCungCap(NhaCungCapModel ncc){
        Connection conn = BaseModel.getConnection();
        try {
            
            String query = "update NhaCungCap set ten_ncc = '" + ncc.getTenNCC() +"', dia_chi = '" + ncc.getDiaChiNCC()+ "', sdt = '" + ncc.getSdtNCC() + "' where ma_ncc = '" + ncc.getMaNCC() + "'";
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            return true;
        } catch (SQLException e) {
        }
        return false;
    }
    
    public static boolean deleteNhaCungCap(String MaNCC){
        Connection conn = BaseModel.getConnection();
        try {
            String query = "delete from NhaCungCap where ma_ncc = '" + MaNCC + "'";
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            return true;
        } catch (SQLException e) {
        }
        return false;
    }
}
