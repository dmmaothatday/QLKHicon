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
public class NhaCungCapController {
    
    private static QLNhaCungCapForm nccview;
    
    public NhaCungCapController(QLNhaCungCapForm nccview) {
        NhaCungCapController.nccview = nccview;
    }
    
    public static void QLNhaCungCapPage(){
        QLNhaCungCapForm.runQLNhaCungCap();
    }
    
    public static ArrayList<NhaCungCapModel> listNhaCungCap(String key) {
        String query;
        if ("".equals(key)){
            query = "select * from NhaCungCap";
        } else {
            query = "select * from NhaCungCap " + key;
        }
        return NhaCungCapModel.getDanhSachNhaCungCap(query);
    }
    
    public void doAddNhaCungCap(){
        if(QLNhaCungCapForm.check == true){
            nccview.showMessage("Vui lòng \"Làm mới\" và nhập thông tin \n", false);
        } else {
            NhaCungCapModel ncc = QLNhaCungCapForm.getNhaCungCapInput();
            if(ncc.getMaNCC().equals("") || ncc.getDiaChiNCC().equals("")|| ncc.getSdtNCC().equals("") || ncc.getTenNCC().equals("")){
                nccview.showMessage("Vui lòng điền đủ thông tin \n", false);
            }
            else {
                String key = "where ma_ncc ='" + ncc.getMaNCC()+ "' or sdt = '" + ncc.getSdtNCC()+"'";
                if(listNhaCungCap(key) == null || listNhaCungCap(key).isEmpty()){
                        if(NhaCungCapModel.addNhaCungCap(ncc)){
                            QLNhaCungCapForm.loadTableNhaCungCap(listNhaCungCap(""));
                            nccview.showMessage("Thêm nhà cung cấp thành công \n", true);
                        } else {
                            nccview.showMessage("Thêm nhà cung cấp thất bại \n", false);
                        }
                } else {
                    nccview.showMessage("Mã nhà cung cấp, số điện thoại đã tồn tại \n", false);
                }
            } 
        }
    }
    
    public void doSearchNhaCungCap(){
        String key = "";    
        NhaCungCapModel ncc = QLNhaCungCapForm.getNhaCungCapInput();
        if (!"".equals(ncc.getMaNCC())){
            if("".equals(key)){
                key = key + "ma_ncc like " + "'%" + ncc.getMaNCC() + "%'";
            } else {
                key = key + " and ma_ncc like " + "'%" + ncc.getMaNCC() + "%'";
            }      
        }
        if (!"".equals(ncc.getDiaChiNCC())){
            if("".equals(key)){
                key = key + "dia_chi like " + "'%" + ncc.getDiaChiNCC() + "%'";
            } else {
                key = key + " and dia_chi like " + "'%" + ncc.getDiaChiNCC() + "%'";
            }  
        }
       
        if (!"".equals(ncc.getSdtNCC())){
            if("".equals(key)){
                key = key + "sdt = " + "'" + ncc.getSdtNCC() + "'";
            } else{
                key = key + " and sdt = " + "'" + ncc.getSdtNCC() + "'";
            }
        }
        if (!"".equals(ncc.getTenNCC())){
            if("".equals(key)){
                key = key + "ten_ncc like " +  "'%" + ncc.getTenNCC() + "%'";
            } else {
                key = key + " and ten_ncc like " +  "'%" + ncc.getTenNCC() + "%'";
            }
        }
       
//        System.out.println(key);
        if ("".equals(key)){
            nccview.showMessage("Vui lòng nhập thông tin tìm kiếm \n", false);
            QLNhaCungCapForm.loadTableNhaCungCap(listNhaCungCap(""));
        } else {
            key = " where " + key;
            ArrayList<NhaCungCapModel> nccs = listNhaCungCap(key);
            if(nccs.isEmpty()){
                nccview.showMessage("Không tìm thấy thông tin phù hợp \n", false);
                QLNhaCungCapForm.loadTableNhaCungCap(listNhaCungCap(""));
            } else QLNhaCungCapForm.loadTableNhaCungCap(nccs);
        }  
    }  
    
    public void doUpdateNhaCungCap(){
        NhaCungCapModel ncc = QLNhaCungCapForm.getNhaCungCapInput();
        if(QLNhaCungCapForm.check == false){
            nccview.showMessage("Vui lòng chọn thông tin cần sửa \n", false);
        } else {
            if(ncc.getDiaChiNCC().equals("") || ncc.getSdtNCC().equals("") || ncc.getTenNCC().equals("")){
                nccview.showMessage("Vui lòng điền đủ thông tin \n", false);
            }
            else {
                String key = "where sdt = '" + ncc.getSdtNCC() + "' and ma_ncc <> '" + ncc.getMaNCC()+ "'";
//                System.out.println(key);
                if(listNhaCungCap(key) == null || listNhaCungCap(key).isEmpty()){
                        if(NhaCungCapModel.updateNhaCungCap(ncc)){
                            QLNhaCungCapForm.loadTableNhaCungCap(listNhaCungCap(""));
                            nccview.showMessage("Sửa thành công \n",true );
                        } else {
                            nccview.showMessage("Sửa thất bại \n", false);
                        }
                } else {
                    nccview.showMessage("Số điện thoại đã tồn tại \n", false);
                }
            } 
        }
        
    }
    
    public void doDeleteNhaCungCap(){
        if (QLNhaCungCapForm.check == true){
            if (QLNhaCungCapForm.xacNhanXoa()){
                if(NhaCungCapModel.deleteNhaCungCap(QLNhaCungCapForm.getNhaCungCapInput().getMaNCC())){
                    QLNhaCungCapForm.loadTableNhaCungCap(listNhaCungCap(""));
                    nccview.showMessage("Xóa thành công \n", true);               
                } else {
                    nccview.showMessage("Xóa thất bại \n", false);
                }
            }
        } else nccview.showMessage("Vui lòng chọn thông tin cần xóa \n", false);
    }
}
