/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QLKHController;
import QLKHModel.*;
import QLKHView.*;
import java.util.ArrayList;
/**
 *
 * @author 
 */
public class DayController {
    private static QLDayForm dview;
    private static QLDay_ChiTietForm dctview;
 
    public DayController(QLDayForm dview) {
        DayController.dview = dview;
    }
    
    public DayController(QLDay_ChiTietForm dctview) {
        DayController.dctview = dctview;
    }
    
    public static void QLDayPage(){
        QLDayForm.runQLDay();
    }
    
    public void QLDay_ChiTietPage(String MaDay){
        if(QLDayForm.check == false){
            dview.showMessage("Vui lòng chọn thông tin cần xem \n", false);
        } else QLDay_ChiTietForm.runViewDetailD(MaDay);
    }
    
    public static DayModel listChiTietDay(String MaDay, String MaSP, String key){
        String query;
        if(key.isEmpty()){
            if(MaSP.isEmpty()){
                query = "select ma_sp, so_luong, ghi_chu, ma_day from ChiTietDay where ma_day = '" + MaDay + "'"; 
            } else query = "select ma_sp, so_luong, ghi_chu, ma_day from ChiTietDay where ma_day = '" + MaDay + "' and ma_sp = '" + MaSP + "'"; 
        } else query = "select ma_sp, so_luong, ghi_chu, ma_day from ChiTietDay " + key;
//        System.out.println(query);
        return DayModel.getChiTietDay(query);
    }
    
    public static ArrayList<DayModel> listDay(String key) {
        String query;
        if ("".equals(key)){
            query = "select ma_day, suc_chua, khu, suc_chua-(select sum(so_luong) as trong from ChiTietDay where ChiTietDay.ma_day = DaySP.ma_day group by ma_day) from DaySP ";
        } else {
            query = "select ma_day, suc_chua, khu, suc_chua-(select sum(so_luong) as trong from ChiTietDay where ChiTietDay.ma_day = DaySP.ma_day group by ma_day) from DaySP " + key;
        }
        return DayModel.getDanhSachDay(query);
    }
    
    public void doAddDay(){
        if(QLDayForm.check == true){
            dview.showMessage("Vui lòng \"Làm mới\" và nhập thông tin \n", false);
        } else {
            DayModel d = QLDayForm.getDayInput();
            if(d.getMaDay().equals("") || d.getKhu().equals("") || d.getSucChua() <= 0 ){
                dview.showMessage("Vui lòng điền đủ thông tin \n", false);
            } else {
                String key = "where ma_day ='" + d.getMaDay()+"'";
//              System.out.println(key);
                if(listDay(key) == null || listDay(key).isEmpty()){
                    if(DayModel.addDay(d)){
                        QLDayForm.loadTableDay(listDay(""));
                        dview.showMessage("Thêm dãy thành công \n", true);
                    } else {
                        dview.showMessage("Thêm dãy thất bại \n", false);
                    }
                } else dview.showMessage("Mã dãy đã tồn tại \n", false);
            }
        }
    }
    
    public void doSearchDay(){
        String key = "";    
        DayModel d = QLDayForm.getDayInput();
        if (!"".equals(d.getMaDay())){
            if("".equals(key)){
                key = key + "ma_day like " + "'%" + d.getMaDay()+ "%'";
            } else {
                key = key + " and ma_day like " + "'%" + d.getMaDay()+ "%'";
            }      
        }
        if (!"*".equals(d.getKhu())){
            if("".equals(key)){
                key = key + "khu like " + "'%" + d.getKhu()+ "%'";
            } else {
                key = key + " and khu like " + "'%" + d.getKhu()+ "%'";
            }  
        }
        if (d.getSucChua() > 0){
            if("".equals(key)){
                key = key + "suc_chua <= " + d.getSucChua();
            } else{
                key = key + " and suc_chua <= " + d.getSucChua();
            }
        }
//        System.out.println(key);
        if ("".equals(key)){
            dview.showMessage("Vui lòng nhập thông tin tìm kiếm \n", false);
            QLDayForm.loadTableDay(listDay(""));
        } else {
            key = " where " + key;
            ArrayList<DayModel> ds = listDay(key);
            if(ds.isEmpty()){
                dview.showMessage("Không tìm thấy thông tin phù hợp \n", false);
                QLDayForm.loadTableDay(listDay(""));
            } else QLDayForm.loadTableDay(ds);
        }  
    }    
    
    
    public void doUpdateDay(){
        DayModel d = QLDayForm.getDayInput();
        if(QLDayForm.check == false){
            dview.showMessage("Vui lòng chọn thông tin cần sửa \n", false);
        } else {
            if(d.getMaDay().equals("") || d.getKhu().equals("*") || d.getSucChua() <= 0){
                dview.showMessage("Vui lòng điền đủ thông tin \n", false);
            }
            else {
                if(DayModel.updateDay(d)){
                    QLDayForm.loadTableDay(listDay(""));
                    dview.showMessage("Sửa sản phẩm thành công \n",true );
                } else {
                    dview.showMessage("Sửa sản phẩm thất bại \n", false);
                }  
            } 
        } 
    }
    
    public void doDeleteDay(){
        if (QLDayForm.check == true){
            if (QLDayForm.xacNhanXoa()){
                if(DayModel.deleteDay(QLDayForm.getDayInput().getMaDay())){
                    QLDayForm.loadTableDay(listDay(""));
                    dview.showMessage("Xóa thành công \n", true);               
                } else {
                    dview.showMessage("Xóa thất bại \n", false);
                }
            }
        } else dview.showMessage("Vui lòng chọn thông tin cần xóa \n", false); 
    }
    
    public void doAddChiTietDay(String MaDay){
        if(QLDay_ChiTietForm.check == true){
            dctview.showMessage("Vui lòng \"Làm mới\" và nhập thông tin \n", false);
        } else {
            SanPhamModel sp = QLDay_ChiTietForm.getChiTietDayInput();
            if(sp.getMaSP().equals("") || sp.getSoLuong() <= 0 ){
                dctview.showMessage("Vui lòng điền đủ thông tin \n", false);
            } else {
//              System.out.println(key);
                if(null == listChiTietDay(MaDay,sp.getMaSP(),"").getMaDay()){
                    if(DayModel.addChiTietDay(sp, MaDay)){
                        QLDay_ChiTietForm.loadTableChiTietDay(listChiTietDay(MaDay,"","").getDanhSachSP());
                        QLDayForm.loadTableDay(listDay(""));
                        dctview.showMessage("Thêm chi tiết dãy thành công \n", true);
                    } else {
                        dctview.showMessage("Thêm chi tiết dãy thất bại \n", false);
                    }
                } else dctview.showMessage("Sản phẩm đã tồn tại trong dãy \n", false);
            }
        }
    }
    
    public void doUpdateChiTietDay(String MaDay){
        SanPhamModel sp = QLDay_ChiTietForm.getChiTietDayInput();
        if(QLDay_ChiTietForm.check == false){
            dctview.showMessage("Vui lòng chọn thông tin cần sửa \n", false);
        } else {
            if(sp.getSoLuong() <= 0){
                dctview.showMessage("Vui lòng điền số lượng \n", false);
            }
            else {
                if (sp.getGhiChu().isEmpty()) sp.setGhiChu(".");
                if(DayModel.updateChiTietDay(sp, MaDay)){
                    QLDay_ChiTietForm.loadTableChiTietDay(listChiTietDay(MaDay,"","").getDanhSachSP());
                    QLDayForm.loadTableDay(listDay(""));
                    dctview.showMessage("Sửa thành công \n",true );
                } else {
                    dctview.showMessage("Sửa thất bại \n", false);
                }  
            } 
        }  
    }
    
    public void doSearchChiTietDay(String MaDay){
        String key = "";    
        SanPhamModel sp = QLDay_ChiTietForm.getChiTietDayInput();
        if (!"".equals(sp.getMaSP())){
            if("".equals(key)){
                key = key + "ma_sp like " + "'%" + sp.getMaSP()+ "%'";
            } else {
                key = key + " and ma_sp like " + "'%" + sp.getMaSP()+ "%'";
            }      
        }
        if (sp.getSoLuong() >= 0){
            if("".equals(key)){
                key = key + "so_luong <= " + sp.getSoLuong();
            } else {
                key = key + " or so_luong <= " + sp.getSoLuong();
            } 
        }
        if (!"".equals(sp.getGhiChu())){
            if("".equals(key)){
                key = key + "ghi_chu like " + "'%" + sp.getGhiChu() + "%'";
            } else{
                key = key + " and ghi_chu like " + "'%" + sp.getGhiChu() + "%'";
            }
        }
//        System.out.println(key);
        if ("".equals(key)){
            dctview.showMessage("Vui lòng nhập thông tin tìm kiếm \n", false);
            QLDay_ChiTietForm.loadTableChiTietDay(listChiTietDay(MaDay,"","").getDanhSachSP());
        } else {
            key = " where ma_day = '" + MaDay + "' and (" + key + ")";
            ArrayList<SanPhamModel> sps = listChiTietDay(MaDay, MaDay,key).getDanhSachSP();
            if(sps.isEmpty()){
                dctview.showMessage("Không tìm thấy thông tin phù hợp \n", false);
                QLDay_ChiTietForm.loadTableChiTietDay(listChiTietDay(MaDay,"","").getDanhSachSP());
            } else QLDay_ChiTietForm.loadTableChiTietDay(sps);
        }  
    } 
    
    public void doDeleteChiTietDay(String MaDay){
        if (QLDay_ChiTietForm.check == true){
            if (QLDay_ChiTietForm.xacNhanXoa()){
                if(DayModel.deleteChiTietDay(QLDay_ChiTietForm.getChiTietDayInput().getMaSP(), MaDay)){
                    QLDay_ChiTietForm.loadTableChiTietDay(listChiTietDay(MaDay,"","").getDanhSachSP());
                    QLDayForm.loadTableDay(listDay(""));
                    dctview.showMessage("Xóa thành công \n", true);               
                } else {
                    dctview.showMessage("Xóa thất bại \n", false);
                }
            }
        } else dctview.showMessage("Vui lòng chọn thông tin cần xóa \n", false); 
    }
    
}
