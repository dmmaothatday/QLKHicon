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
public class PhieuXuatController {
    private static QLPhieuXuatForm pxview;
    private static QLPhieuXuat_ChiTietForm pxctview;
 
    public PhieuXuatController(QLPhieuXuatForm pxview) {
        PhieuXuatController.pxview = pxview;
    }
    
    public PhieuXuatController(QLPhieuXuat_ChiTietForm pxctview) {
        PhieuXuatController.pxctview = pxctview;
    }
    
    public static void QLPhieuXuatPage(){
        QLPhieuXuatForm.runQLPhieuXuat();
    }
    
    public void QLPhieuXuat_ChiTietPage(String MaPhieuXuat){
        if(QLPhieuXuatForm.check == false){
            pxview.showMessage("Vui lòng chọn thông tin cần xem \n", false);
        } else QLPhieuXuat_ChiTietForm.runViewDetailD(MaPhieuXuat);
    }
    
    public static PhieuXuatModel listChiTietPhieuXuat(String MaPhieuXuat, String MaSP, String key){
        String query;
        if(key.isEmpty()){
            if(MaSP.isEmpty()){
                query = "select ma_sp, so_luong, ma_phieu_xuat from ChiTietPhieuXuat where ma_phieu_xuat = '" + MaPhieuXuat + "'"; 
            } else query = "select ma_sp, so_luong, ma_phieu_xuat from ChiTietPhieuXuat where ma_phieu_xuat = '" + MaPhieuXuat + "' and ma_sp = '" + MaSP + "'"; 
        } else query = "select ma_sp, so_luong, ma_phieu_xuat from ChiTietPhieuXuat where ma_phieu_xuat = '" + MaPhieuXuat + "'" + key;
//        System.out.println(query);
        return PhieuXuatModel.getChiTietPhieuXuat(query);
    }
    
    public static ArrayList<PhieuXuatModel> listPhieuXuat(String key) {
        String query;
        if ("".equals(key)){
            query = "select ma_phieu_xuat, ngay_tao, ma_ch, ma_nd, trang_thai, ngay_xuat from PhieuXuat ";
        } else {
            query = "select ma_phieu_xuat, ngay_tao, ma_ch, ma_nd, trang_thai, ngay_xuat from PhieuXuat " + key;
        }
        return PhieuXuatModel.getDanhSachPhieuXuat(query);
    }
    
    public void doAddPhieuXuat(){
        if(QLPhieuXuatForm.check == true){
            pxview.showMessage("Vui lòng \"Làm mới\" và nhập thông tin \n", false);
        } else {
            PhieuXuatModel px = QLPhieuXuatForm.getPhieuXuatInput();
            if(px.getMaPhieuXuat().equals("") || px.getMaCH().equals("*")){
                pxview.showMessage("Vui lòng điền đủ thông tin \n", false);
            } else {
                String key = "where ma_phieu_xuat ='" + px.getMaPhieuXuat() + "'";
//              System.out.println(key);
                if(listPhieuXuat(key) == null || listPhieuXuat(key).isEmpty()){
                    if(PhieuXuatModel.addPhieuXuat(px)){
                        QLPhieuXuatForm.loadTablePhieuXuat(listPhieuXuat(""));
                        pxview.showMessage("Thêm phiếu xuất thành công \n", true);
                    } else {
                        pxview.showMessage("Thêm phiếu xuất thất bại \n", false);
                    }
                } else pxview.showMessage("Mã phiếu xuất đã tồn tại \n", false);
            }
        }
    }
    
    public void doSearchPhieuXuat(){
        String key = "";    
        PhieuXuatModel px = QLPhieuXuatForm.getPhieuXuatInput();
        if ("Da xuat".equals(px.getTrangThai()) && QLPhieuXuatForm.checkTrangThai.isSelected()) {
            key = key + "trang_thai = 1";
        } else if ("Chua xuat".equals(px.getTrangThai()) && QLPhieuXuatForm.checkTrangThai.isSelected()){
            key = key + "trang_thai = 0";
        }
        if (!"".equals(px.getMaPhieuXuat()) && QLPhieuXuatForm.checkMaPX.isSelected()){
            if("".equals(key)){
                key = key + "ma_phieu_xuat like " + "'%" + px.getMaPhieuXuat()+ "%'";
            } else {
                key = key + " and ma_phieu_xuat like " + "'%" + px.getMaPhieuXuat()+ "%'";
            }      
        }
        if (!"*".equals(px.getMaCH())  && QLPhieuXuatForm.checkMaCH.isSelected()){
            if("".equals(key)){
                key = key + "ma_ch like " + "'%" + px.getMaCH()+ "%'";
            } else {
                key = key + " and ma_ch like " + "'%" + px.getMaCH()+ "%'";
            }      
        }
        if (!"".equals(px.getMaND())  && QLPhieuXuatForm.checkNguoiLap.isSelected()){
            if("".equals(key)){
                key = key + "ma_nd like " + "'%" + px.getMaND()+ "%'";
            } else {
                key = key + " and ma_nd like " + "'%" + px.getMaND()+ "%'";
            }      
        }
        if (!"".equals(px.getNgayTao())  && QLPhieuXuatForm.checkNgayTao.isSelected()){
            if("".equals(key)){
                key = key + "ngay_tao = " + "'" + px.getNgayTao()+ "'";
            } else {
                key = key + " and ngay_tao = " + "'" + px.getNgayTao()+ "'"; 
            }      
        }
        if (!"".equals(px.getNgayXuat()) && QLPhieuXuatForm.checkNgayXuat.isSelected()){
            if("".equals(key)){
                key = key + "ngay_xuat = " + "'" + px.getNgayXuat()+ "'";
            } else {
                key = key + " and ngay_xuat = " + "'" + px.getNgayXuat()+ "'";
            }      
        }
        if ("".equals(key)){
            pxview.showMessage("Vui lòng chọn và nhập thông tin tìm kiếm \n", false);
            QLPhieuXuatForm.loadTablePhieuXuat(listPhieuXuat(""));
        } else {
            key = " where " + key ;
            ArrayList<PhieuXuatModel> pxs = listPhieuXuat(key);
            if(pxs.isEmpty()){
                pxview.showMessage("Không tìm thấy thông tin phù hợp \n", false);
                QLPhieuXuatForm.loadTablePhieuXuat(listPhieuXuat(""));
            } else QLPhieuXuatForm.loadTablePhieuXuat(pxs);
        }  
    }    
    
    public void doUpdatePhieuXuat(){
        PhieuXuatModel px = QLPhieuXuatForm.getPhieuXuatInput();
        if(QLPhieuXuatForm.check == false){
            pxview.showMessage("Vui lòng chọn thông tin cần sửa \n", false);
        } else {
            if(px.getMaPhieuXuat().equals("") || px.getMaCH().equals("") || px.getMaND().equals("")){
                pxview.showMessage("Vui lòng điền đủ thông tin \n", false);
            }
            else {
                if(PhieuXuatModel.updatePhieuXuat(px)){
                    QLPhieuXuatForm.loadTablePhieuXuat(listPhieuXuat(""));
                    pxview.showMessage("Sửa sản phẩm thành công \n",true );
                } else {
                    pxview.showMessage("Sửa sản phẩm thất bại \n", false);
                }  
            } 
        } 
    }
    
    public void doDeletePhieuXuat(){
        if (QLPhieuXuatForm.check == true){
            if (QLPhieuXuatForm.xacNhanXoa()){
                if(PhieuXuatModel.deletePhieuXuat(QLPhieuXuatForm.getPhieuXuatInput().getMaPhieuXuat())){
                    QLPhieuXuatForm.loadTablePhieuXuat(listPhieuXuat(""));
                    pxview.showMessage("Xóa thành công \n", true);               
                } else {
                    pxview.showMessage("Xóa thất bại \n", false);
                }
            }
        } else pxview.showMessage("Vui lòng chọn thông tin cần xóa \n", false); 
    }
    
    public void doAddChiTietPhieuXuat(String MaPhieuXuat){
        if(QLPhieuXuat_ChiTietForm.check == true){
            pxctview.showMessage("Vui lòng \"Làm mới\" và nhập thông tin \n", false);
        } else {
            SanPhamModel sp = QLPhieuXuat_ChiTietForm.getChiTietPhieuXuatInput();
            if(sp.getMaSP().equals("") || sp.getSoLuong() <= 0 ){
                pxctview.showMessage("Vui lòng điền đủ thông tin \n", false);
            } else {
//              System.out.println(key);
                if(null == listChiTietPhieuXuat(MaPhieuXuat,sp.getMaSP(),"").getMaPhieuXuat()){
                    if(PhieuXuatModel.addChiTietPhieuXuat(sp, MaPhieuXuat)){
                        QLPhieuXuat_ChiTietForm.loadTableChiTietPhieuXuat(listChiTietPhieuXuat(MaPhieuXuat,"","").getDanhSachSP());
                        QLPhieuXuatForm.loadTablePhieuXuat(listPhieuXuat(""));
                        pxctview.showMessage("Thêm chi tiết phiếu xuất thành công \n", true);
                    } else {
                        pxctview.showMessage("Thêm chi tiết phiếu xuất thất bại \n", false);
                    }
                } else pxctview.showMessage("Sản phẩm đã tồn tại trong phiếu xuất \n", false);
            }
        }
    }
    
    public void doUpdateChiTietPhieuXuat(String MaPhieuXuat){
        SanPhamModel sp = QLPhieuXuat_ChiTietForm.getChiTietPhieuXuatInput();
        if(QLPhieuXuat_ChiTietForm.check == false){
            pxctview.showMessage("Vui lòng chọn thông tin cần sửa \n", false);
        } else {
            if(sp.getSoLuong() <= 0){
                pxctview.showMessage("Vui lòng điền số lượng \n", false);
            }
            else {
                if(PhieuXuatModel.updateChiTietPhieuXuat(sp, MaPhieuXuat)){
                    QLPhieuXuat_ChiTietForm.loadTableChiTietPhieuXuat(listChiTietPhieuXuat(MaPhieuXuat,"","").getDanhSachSP());
                    QLPhieuXuatForm.loadTablePhieuXuat(listPhieuXuat(""));
                    pxctview.showMessage("Sửa thành công \n",true );
                } else {
                    pxctview.showMessage("Sửa thất bại \n", false);
                }  
            } 
        }  
    }
    
    public void doSearchChiTietPhieuXuat(String MaPhieuXuat){
        String key = "";    
        SanPhamModel sp = QLPhieuXuat_ChiTietForm.getChiTietPhieuXuatInput();
        if (!"".equals(sp.getMaSP())){
            if("".equals(key)){
                key = key + "ma_sp like " + "'%" + sp.getMaSP()+ "%'";
            } else {
                key = key + " and ma_sp like " + "'%" + sp.getMaSP()+ "%'";
            }      
        }
        if (sp.getSoLuong() > 0){
            if("".equals(key)){
                key = key + "so_luong = " + sp.getSoLuong();
            } else {
                key = key + " and so_luong = " + sp.getSoLuong();
            } 
        }
//        System.out.println(key);
        if ("".equals(key)){
            pxctview.showMessage("Vui lòng nhập thông tin tìm kiếm \n", false);
            QLPhieuXuat_ChiTietForm.loadTableChiTietPhieuXuat(listChiTietPhieuXuat(MaPhieuXuat,"","").getDanhSachSP());
        } else {
            key = " and (" + key + ")";
            ArrayList<SanPhamModel> sps = listChiTietPhieuXuat(MaPhieuXuat, MaPhieuXuat,key).getDanhSachSP();
            if(sps.isEmpty()){
                pxctview.showMessage("Không tìm thấy thông tin phù hợp \n", false);
                QLPhieuXuat_ChiTietForm.loadTableChiTietPhieuXuat(listChiTietPhieuXuat(MaPhieuXuat,"","").getDanhSachSP());
            } else QLPhieuXuat_ChiTietForm.loadTableChiTietPhieuXuat(sps);
        }  
    } 
    
    public void doDeleteChiTietPhieuXuat(String MaPhieuXuat){
        if (QLPhieuXuat_ChiTietForm.check == true){
            if (QLPhieuXuat_ChiTietForm.xacNhanXoa()){
                if(PhieuXuatModel.deleteChiTietPhieuXuat(QLPhieuXuat_ChiTietForm.getChiTietPhieuXuatInput().getMaSP(), MaPhieuXuat)){
                    QLPhieuXuat_ChiTietForm.loadTableChiTietPhieuXuat(listChiTietPhieuXuat(MaPhieuXuat,"","").getDanhSachSP());
                    QLPhieuXuatForm.loadTablePhieuXuat(listPhieuXuat(""));
                    pxctview.showMessage("Xóa thành công \n", true);               
                } else {
                    pxctview.showMessage("Xóa thất bại \n", false);
                }
            }
        } else pxctview.showMessage("Vui lòng chọn thông tin cần xóa \n", false); 
    }
}
