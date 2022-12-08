/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QLKHController;
import QLKHModel.*;
import QLKHView.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 *
 * @author 
 */
public class NguoiDungController {
    private static DangNhapForm dnview;
    private static QLNguoiDungForm ndview;
    public static NguoiDungModel ndOn;

    public NguoiDungController(DangNhapForm view) {
        NguoiDungController.dnview = view;
    }
    
    public NguoiDungController(QLNguoiDungForm view) {
        NguoiDungController.ndview = view;
    }
    
    public static void loginPage(){
        DangNhapForm.runLogin();
    }
    
    public static void QLNguoiDungpage(){
        QLNguoiDungForm.runQLNguoiDung();
    }
    
    public void doLogin(){  
        NguoiDungModel nd = DangNhapForm.getUserLogin();
        if (nd.getMaND().equals("") || nd.getMatKhau().equals("")){
            dnview.showMessageLogin("Vui lòng điền đầy đủ thông tin \n");
        } else {
            NguoiDungModel ndc = NguoiDungModel.checkLogin(nd.getMaND(),nd.getMatKhau());
            if(ndc.getChucVu() != null){
                switch (ndc.getChucVu()) {
                    case "Quan ly" -> indexFormQL.runIndexQL();
                    case "Nhan vien tiep nhan" -> indexFormNVTN.runIndexNVTN();
                    case "Nhan vien kiem ke" -> indexFormNVKK.runIndexNVKK();
                    default -> { DangNhapForm.runLogin();
                    }
                }
                ndOn = ndc;
                dnview.dispose();
            } else dnview.showMessageLogin("Sai thông tin đăng nhập \n");   
        }
    }
    
    public static ArrayList<NguoiDungModel> listNguoiDung(String key) {
        String query;
        if ("".equals(key)){
            query = "select * from NguoiDung";
        } else {
            query = "select * from NguoiDung " + key;
        }
        return NguoiDungModel.getDanhSachNguoiDung(query);
    }
    
    public void doAddNguoiDung(){
        if(QLNguoiDungForm.check == true){
            ndview.showMessage("Vui lòng \"Làm mới\" và nhập thông tin \n", false);
        } else {
            NguoiDungModel nd = QLNguoiDungForm.getNguoiDungInput();
            if(nd.getMaND().equals("") || nd.getDiaChiND().equals("") || nd.getChucVu().equals("*") || nd.getGioiTinh().equals("*") || nd.getMatKhau().equals("") || nd.getNgaySinh().equals("") || nd.getSdtND().equals("") || nd.getTenND().equals("") || nd.getCanCuoc().equals("")){
                ndview.showMessage("Vui lòng điền đủ thông tin \n", false);
            }
            else {
                String key = "where ma_nd ='" + nd.getMaND()+ "' or sdt = '" + nd.getSdtND()+ "' or can_cuoc = '" + nd.getCanCuoc()+"'";
//                System.out.println(key);
                if(listNguoiDung(key) == null || listNguoiDung(key).isEmpty()){
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    LocalDate ns = LocalDate.parse(nd.getNgaySinh(),formatter);
                    boolean check = false;
                    int diffYear = LocalDate.now().getYear() - ns.getYear();
                    if (diffYear > 18){
                        check = true;
                    } else if (diffYear == 18){
                        if (LocalDate.now().getMonthValue() > ns.getMonthValue()){
                            check = true;
                        }
                    }
                    if(check){
                        if(NguoiDungModel.addNguoiDung(nd)){
                            QLNguoiDungForm.loadTableNguoiDung(listNguoiDung(""));
                            ndview.showMessage("Thêm người dùng thành công \n", true);
                        } else {
                            ndview.showMessage("Thêm người dùng thất bại \n", false);
                        }
                    } else {
                        ndview.showMessage("Quản lý, Nhân viên phải trên 18 tuổi \n", false);
                    }   
                } else {
                    ndview.showMessage("Mã người dùng, số điện thoại hoặc căn cước đã tồn tại \n", false);
                }
            } 
        }
    }
    
    public void doSearchNguoiDung(){
        String key = "";    
        NguoiDungModel nd = QLNguoiDungForm.getNguoiDungInput();
        if ("Nam".equals(nd.getGioiTinh())) {
            key = key + "gioi_tinh = 1";
        } else if ("Nữ".equals(nd.getGioiTinh())){
            key = key + "gioi_tinh = 0";
        }
        if (!"".equals(nd.getMaND())){
            if("".equals(key)){
                key = key + "ma_nd like " + "'%" + nd.getMaND() + "%'";
            } else {
                key = key + " and ma_nd like " + "'%" + nd.getMaND() + "%'";
            }      
        }
        if (!"".equals(nd.getDiaChiND())){
            if("".equals(key)){
                key = key + "dia_chi like " + "'%" + nd.getDiaChiND() + "%'";
            } else {
                key = key + " and dia_chi like " + "'%" + nd.getDiaChiND() + "%'";
            }  
        }
        if (!"".equals(nd.getNgaySinh())){
            if("".equals(key)){
                key = key + "ngay_sinh = " + "'" + nd.getNgaySinh() + "'";
            } else{
                key = key + " and ngay_sinh = " + "'" + nd.getNgaySinh() + "'";
            }
        }
        if (!"".equals(nd.getSdtND())){
            if("".equals(key)){
                key = key + "sdt = " + "'" + nd.getSdtND() + "'";
            } else{
                key = key + " and sdt = " + "'" + nd.getSdtND() + "'";
            }
        }
        if (!"".equals(nd.getTenND())){
            if("".equals(key)){
                key = key + "ten_nd like " +  "'%" + nd.getTenND() + "%'";
            } else {
                key = key + " and ten_nd like " +  "'%" + nd.getTenND() + "%'";
            }
        }
        if (!"*".equals(nd.getChucVu())){
            if("".equals(key)){
                key = key + "chuc_vu = " + "'" + nd.getChucVu() + "'";
            } else {
                key = key + " and chuc_vu = " + "'" + nd.getChucVu() + "'";
            } 
        }
        
        if (!"".equals(nd.getCanCuoc())){
            if("".equals(key)){
                key = key + "can_cuoc = " + "'" + nd.getCanCuoc() + "'";
            } else{
                key = key + " and can_cuoc = " + "'" + nd.getCanCuoc() + "'";
            }
        }
//        System.out.println(key);
        if ("".equals(key)){
            ndview.showMessage("Vui lòng nhập thông tin tìm kiếm \n", false);
            QLNguoiDungForm.loadTableNguoiDung(listNguoiDung(""));
        } else {
            key = " where " + key;
            ArrayList<NguoiDungModel> nds = listNguoiDung(key);
            if(nds.isEmpty()){
                ndview.showMessage("Không tìm thấy thông tin phù hợp \n", false);
                QLNguoiDungForm.loadTableNguoiDung(listNguoiDung(""));
            } else QLNguoiDungForm.loadTableNguoiDung(nds);
        }  
    }  
    
    public void doUpdateNguoiDung(){
        NguoiDungModel nd = QLNguoiDungForm.getNguoiDungInput();
        if(QLNguoiDungForm.check == false){
            ndview.showMessage("Vui lòng chọn thông tin cần sửa \n", false);
        } else {
            if(nd.getDiaChiND().equals("") || nd.getChucVu().equals("*") || nd.getGioiTinh().equals("*") || nd.getMatKhau().equals("") || nd.getNgaySinh().equals("") || nd.getSdtND().equals("") || nd.getTenND().equals("") || nd.getCanCuoc().equals("")){
                ndview.showMessage("Vui lòng điền đủ thông tin \n", false);
            }
            else {
                String key = "where (sdt = '" + nd.getSdtND() + "' or can_cuoc = '" + nd.getCanCuoc() + "') and ma_nd <> '" + nd.getMaND()+ "'";
//                System.out.println(key);
                if(listNguoiDung(key) == null || listNguoiDung(key).isEmpty()){
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    LocalDate ns = LocalDate.parse(nd.getNgaySinh(),formatter);
                    boolean check = false;
                    int diffYear = LocalDate.now().getYear() - ns.getYear();
                    if (diffYear > 18){
                        check = true;
                    } else if (diffYear == 18){
                        if (LocalDate.now().getMonthValue() > ns.getMonthValue()){
                            check = true;
                        }
                    }
                    if(check){
                        if(NguoiDungModel.updateNguoiDung(nd)){
                            QLNguoiDungForm.loadTableNguoiDung(listNguoiDung(""));
                            ndview.showMessage("Sửa người dùng thành công \n",true );
                        } else {
                            ndview.showMessage("Sửa người dùng thất bại \n", false);
                        }
                    } else {
                        ndview.showMessage("Quản lý, Nhân viên phải trên 18 tuổi \n", false);
                    }   
                } else {
                    ndview.showMessage("Số điện thoại hoặc căn cước đã tồn tại \n", false);
                }
            } 
        }
        
    }
    
    public void doDeleteNguoiDung(){
        if (QLNguoiDungForm.check == true){
            if (QLNguoiDungForm.xacNhanXoa()){
                if(NguoiDungModel.deleteNguoiDung(QLNguoiDungForm.getNguoiDungInput().getMaND())){
                    QLNguoiDungForm.loadTableNguoiDung(listNguoiDung(""));
                    ndview.showMessage("Xóa thành công \n", true);               
                } else {
                    ndview.showMessage("Xóa thất bại \n", false);
                }
            }
        } else ndview.showMessage("Vui lòng chọn thông tin cần xóa \n", false);
    }
}
