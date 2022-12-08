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
 * @author VTTDung
 */
public class CuaHangController {
    private static QLCuaHangForm chview;
    
    public CuaHangController(QLCuaHangForm chview) {
        CuaHangController.chview = chview;
    }
    
    public static void QLCuaHangPage(){
        QLCuaHangForm.runQLCuaHang();
    }
    public static ArrayList<CuaHangModel> listCuaHang(String key) {
        String query;
        if ("".equals(key)){
            query = "select * from CuaHang";
        } else {
            query = "select * from CuaHang " + key;
        }
        return CuaHangModel.getDanhSachCuaHang(query);
    }
    
    public void doAddCuaHang(){
        if(QLCuaHangForm.check == true){
            chview.showMessage("Vui lòng \"Làm mới\" và nhập thông tin \n", false);
        } else {
            CuaHangModel ch = QLCuaHangForm.getCuaHangInput();
            if(ch.getMaCH().equals("") || ch.getDiaChiCH().equals("")|| ch.getSdtCH().equals("") || ch.getTenCH().equals("")){
                chview.showMessage("Vui lòng điền đủ thông tin \n", false);
            }
            else {
                String key = "where ma_ch ='" + ch.getMaCH()+ "' or sdt = '" + ch.getSdtCH()+"'";
                if(listCuaHang(key) == null || listCuaHang(key).isEmpty()){
                        if(CuaHangModel.addCuaHang(ch)){
                            QLCuaHangForm.loadTableCuaHang(listCuaHang(""));
                            chview.showMessage("Thêm cửa hàng thành công \n", true);
                        } else {
                            chview.showMessage("Thêm cửa hàng thất bại \n", false);
                        }
                } else {
                    chview.showMessage("Mã cửa hàng, số điện thoại đã tồn tại \n", false);
                }
            } 
        }
    }
    
    public void doSearchCuaHang(){
        String key = "";    
        CuaHangModel ch = QLCuaHangForm.getCuaHangInput();
        if (!"".equals(ch.getMaCH())){
            if("".equals(key)){
                key = key + "ma_ch like " + "'%" + ch.getMaCH() + "%'";
            } else {
                key = key + " and ma_ch like " + "'%" + ch.getMaCH() + "%'";
            }      
        }
        if (!"".equals(ch.getDiaChiCH())){
            if("".equals(key)){
                key = key + "dia_chi like " + "'%" + ch.getDiaChiCH() + "%'";
            } else {
                key = key + " and dia_chi like " + "'%" + ch.getDiaChiCH() + "%'";
            }  
        }
       
        if (!"".equals(ch.getSdtCH())){
            if("".equals(key)){
                key = key + "sdt = " + "'" + ch.getSdtCH() + "'";
            } else{
                key = key + " and sdt = " + "'" + ch.getSdtCH() + "'";
            }
        }
        if (!"".equals(ch.getTenCH())){
            if("".equals(key)){
                key = key + "ten_ch like " +  "'%" + ch.getTenCH() + "%'";
            } else {
                key = key + " and ten_ch like " +  "'%" + ch.getTenCH() + "%'";
            }
        }
       
//        System.out.println(key);
        if ("".equals(key)){
            chview.showMessage("Vui lòng nhập thông tin tìm kiếm \n", false);
            QLCuaHangForm.loadTableCuaHang(listCuaHang(""));
        } else {
            key = " where " + key;
            ArrayList<CuaHangModel> chs = listCuaHang(key);
            if(chs.isEmpty()){
                chview.showMessage("Không tìm thấy thông tin phù hợp \n", false);
                QLCuaHangForm.loadTableCuaHang(listCuaHang(""));
            } else QLCuaHangForm.loadTableCuaHang(chs);
        }  
    }  
    
    public void doUpdateCuaHang(){
        CuaHangModel ch = QLCuaHangForm.getCuaHangInput();
        if(QLCuaHangForm.check == false){
            chview.showMessage("Vui lòng chọn thông tin cần sửa \n", false);
        } else {
            if(ch.getDiaChiCH().equals("") || ch.getSdtCH().equals("") || ch.getTenCH().equals("")){
                chview.showMessage("Vui lòng điền đủ thông tin \n", false);
            }
            else {
                String key = "where sdt = '" + ch.getSdtCH() + "' and ma_ch <> '" + ch.getMaCH()+ "'";
//                System.out.println(key);
                if(listCuaHang(key) == null || listCuaHang(key).isEmpty()){
                        if(CuaHangModel.updateCuaHang(ch)){
                            QLCuaHangForm.loadTableCuaHang(listCuaHang(""));
                            chview.showMessage("Sửa cửa hàng thành công \n",true );
                        } else {
                            chview.showMessage("Sửa cửa hàng thất bại \n", false);
                        }
                } else {
                    chview.showMessage("Số điện thoại đã tồn tại \n", false);
                }
            } 
        }
        
    }
    
    public void doDeleteCuaHang(){
        if (QLCuaHangForm.check == true){
            if (QLCuaHangForm.xacNhanXoa()){
                if(CuaHangModel.deleteCuaHang(QLCuaHangForm.getCuaHangInput().getMaCH())){
                    QLCuaHangForm.loadTableCuaHang(listCuaHang(""));
                    chview.showMessage("Xóa thành công \n", true);               
                } else {
                    chview.showMessage("Xóa thất bại \n", false);
                }
            }
        } else chview.showMessage("Vui lòng chọn thông tin cần xóa \n", false);
    }
}
