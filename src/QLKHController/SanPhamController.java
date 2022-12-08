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
public class SanPhamController {
    private QLSanPhamForm spview;
 
    public SanPhamController(QLSanPhamForm spview) {
        this.spview = spview;
    }
    
    public static void QLSanPhamPage(){
        QLSanPhamForm.runQLSanPham();
    }
    
    public static ArrayList<SanPhamModel> listSanPham(String key) {
        String query;
        if ("".equals(key)){
            query = "select ma_sp, ten_sp, loai_sp, hang, mau_sac, kieu_dang, chat_lieu, don_gia, don_vi,(select sum(so_luong) from ChiTietDay where ChiTietDay.ma_sp = SanPham.ma_sp group by ma_sp) as so_luong from SanPham order by so_luong DESC";
        } else {
            query = "select ma_sp, ten_sp, loai_sp, hang, mau_sac, kieu_dang, chat_lieu, don_gia, don_vi,(select sum(so_luong) from ChiTietDay where ChiTietDay.ma_sp = SanPham.ma_sp group by ma_sp) as so_luong from SanPham " + key + " order by so_luong DESC";
        }
        return SanPhamModel.getDanhSachSanPham(query);
    }
    
    public void doAddSanPham(){
        if(QLSanPhamForm.check == true){
            spview.showMessage("Vui lòng \"Làm mới\" và nhập thông tin \n", false);
        } else {
            SanPhamModel sp = QLSanPhamForm.getSanPhamInput();
            if(sp.getMaSP().equals("") || sp.getTenSP().equals("") || sp.getLoai().equals("*") || sp.getHang().equals("") || "".equals(sp.getDonGia()) || sp.getDonVi().equals("*")){
                spview.showMessage("Vui lòng điền đủ thông tin \n", false);
            }
            else {
                String key = "where ma_sp ='" + sp.getMaSP() + "'";
//                System.out.println(key);
                if(listSanPham(key) == null || listSanPham(key).isEmpty()){
                    if(SanPhamModel.addSanPham(sp)){
                        QLSanPhamForm.loadTableSanPham(listSanPham(""));
                        spview.showMessage("Thêm sản phẩm thành công \n", true);
                    } else {
                        spview.showMessage("Thêm sản phẩm thất bại \n", false);
                    } 
                } else {
                    spview.showMessage("Mã mã sản phẩm đã tồn tại \n", false);
                }
            } 
        }
    }
    
    public void doDeleteSanPham(){
        if (QLSanPhamForm.check == true){
            if (QLSanPhamForm.xacNhanXoa()){
                if(SanPhamModel.deleteSanPham(QLSanPhamForm.getSanPhamInput().getMaSP())){
                    QLSanPhamForm.loadTableSanPham(listSanPham(""));
                    spview.showMessage("Xóa thành công \n", true);               
                } else {
                    spview.showMessage("Xóa thất bại \n", false);
                }
            }
        } else spview.showMessage("Vui lòng chọn thông tin cần xóa \n", false);
    }
    
    public void doUpdateSanPham(){
        SanPhamModel sp = QLSanPhamForm.getSanPhamInput();
        if(QLSanPhamForm.check == false){
            spview.showMessage("Vui lòng chọn thông tin cần sửa \n", false);
        } else {
            if(sp.getMaSP().equals("") || sp.getTenSP().equals("") || sp.getLoai().equals("*") || sp.getHang().equals("") || sp.getDonVi().equals("*") || sp.getDonGia() <= 0){
                spview.showMessage("Vui lòng điền đủ thông tin \n", false);
            }
            else {
                if(SanPhamModel.updateSanPham(sp)){
                    QLSanPhamForm.loadTableSanPham(listSanPham(""));
                    spview.showMessage("Sửa sản phẩm thành công \n",true );
                } else {
                    spview.showMessage("Sửa sản phẩm thất bại \n", false);
                }  
            } 
        } 
    }
    
    public void doViewDetailSP(String MaSP){
        if(QLSanPhamForm.check == true){
            QLSanPham_ChiTietForm.runViewDetailSP(DayModel.getChiTietSP(MaSP));
        } else spview.showMessage("Vui lòng chọn thông tin cần xem \n", false);
    }
    
    public void doSearchSanPham(){
        String key = "";    
        SanPhamModel sp = QLSanPhamForm.getSanPhamInput();
        if (!"".equals(sp.getMaSP())){
            if("".equals(key)){
                key = key + "ma_sp like " + "'%" + sp.getMaSP() + "%'";
            } else {
                key = key + " and ma_sp like " + "'%" + sp.getMaSP() + "%'";
            }      
        }
        if (!"".equals(sp.getTenSP())){
            if("".equals(key)){
                key = key + "ten_sp like " + "'%" + sp.getTenSP() + "%'";
            } else {
                key = key + " and ten_sp like " + "'%" + sp.getTenSP() + "%'";
            }  
        }
        if (!"*".equals(sp.getLoai())){
            if("".equals(key)){
                key = key + "loai_sp = " + "'" + sp.getLoai() + "'";
            } else{
                key = key + " and loai_sp = " + "'" + sp.getLoai() + "'";
            }
        }
        if (!"".equals(sp.getHang())){
            if("".equals(key)){
                key = key + "hang = " + "'" + sp.getHang() + "'";
            } else{
                key = key + " and hang = " + "'" + sp.getHang() + "'";
            }
        }
        if (sp.getDonGia() > 0){
            if("".equals(key)){
                key = key + " don_gia <= " + sp.getDonGia();
            } else {
                key = key + " and don_gia <= " + sp.getDonGia();
            }
        }
        if (!"*".equals(sp.getDonVi())){
            if("".equals(key)){
                key = key + "don_vi = " + "'" + sp.getDonVi() + "'";
            } else {
                key = key + " and don_vi = " + "'" + sp.getDonVi() + "'";
            } 
        }
        
        if (!"".equals(sp.getMauSac())){
            if("".equals(key)){
                key = key + "mau_sac = " + "'" + sp.getMauSac() + "'";
            } else{
                key = key + " and mau_sac = " + "'" + sp.getMauSac() + "'";
            }
        }
        if (!"".equals(sp.getKieuDang())){
            if("".equals(key)){
                key = key + "kieu_dang = " + "'" + sp.getKieuDang() + "'";
            } else{
                key = key + " and kieu_dang = " + "'" + sp.getKieuDang() + "'";
            }
        }
        if (!"".equals(sp.getChatLieu())){
            if("".equals(key)){
                key = key + "chat_lieu = " + "'" + sp.getChatLieu() + "'";
            } else{
                key = key + " and chat_lieu = " + "'" + sp.getChatLieu() + "'";
            }
        }
//        System.out.println(key);
        if ("".equals(key)){
            spview.showMessage("Vui lòng nhập thông tin tìm kiếm \n", false);
            QLSanPhamForm.loadTableSanPham(listSanPham(""));
        } else {
            key = " where " + key;
            ArrayList<SanPhamModel> sps = listSanPham(key);
            if(sps.isEmpty()){
                spview.showMessage("Không tìm thấy thông tin phù hợp \n", false);
                QLSanPhamForm.loadTableSanPham(listSanPham(""));
            } else QLSanPhamForm.loadTableSanPham(sps);
        }  
    }    
}
