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
public class PhieuNhapController {
    private static QLPhieuNhapForm pnview;
    private static QLPhieuNhap_ChiTietForm pnctview;
 
    public PhieuNhapController(QLPhieuNhapForm pnview) {
        PhieuNhapController.pnview = pnview;
    }
    
    public PhieuNhapController(QLPhieuNhap_ChiTietForm pnctview) {
        PhieuNhapController.pnctview = pnctview;
    }
    
    public static void QLPhieuNhapPage(){
        QLPhieuNhapForm.runQLPhieuNhap();
    }
    
    public void QLPhieuNhap_ChiTietPage(String MaPhieuNhap){
        if(QLPhieuNhapForm.check == false){
            pnview.showMessage("Vui lòng chọn thông tin cần xem \n", false);
        } else QLPhieuNhap_ChiTietForm.runViewDetailD(MaPhieuNhap);
    }
    
    public static PhieuNhapModel listChiTietPhieuNhap(String MaPhieuNhap, String MaSP, String key){
        String query;
        if(key.isEmpty()){
            if(MaSP.isEmpty()){
                query = "select ma_sp, so_luong, ma_phieu_nhap from ChiTietPhieuNhap where ma_phieu_nhap = '" + MaPhieuNhap + "'"; 
            } else query = "select ma_sp, so_luong, ma_phieu_nhap from ChiTietPhieuNhap where ma_phieu_nhap = '" + MaPhieuNhap + "' and ma_sp = '" + MaSP + "'"; 
        } else query = "select ma_sp, so_luong, ma_phieu_nhap from ChiTietPhieuNhap where ma_phieu_nhap = '" + MaPhieuNhap + "'" + key;
//        System.out.println(query);
        return PhieuNhapModel.getChiTietPhieuNhap(query);
    }
    
    public static ArrayList<PhieuNhapModel> listPhieuNhap(String key) {
        String query;
        if ("".equals(key)){
            query = "select PhieuNhap.ma_phieu_nhap, ngay_tao, ma_ncc, ma_nd, trang_thai, ngay_nhap, (select sum(so_luong*don_gia) from ChiTietPhieuNhap join SanPham on ChiTietPhieuNhap.ma_sp=SanPham.ma_sp where ma_phieu_nhap=PhieuNhap.ma_phieu_nhap) from PhieuNhap ";
        } else {
            query = "select PhieuNhap.ma_phieu_nhap, ngay_tao, ma_ncc, ma_nd, trang_thai, ngay_nhap, (select sum(so_luong*don_gia) from ChiTietPhieuNhap join SanPham on ChiTietPhieuNhap.ma_sp=SanPham.ma_sp where ma_phieu_nhap=PhieuNhap.ma_phieu_nhap) from PhieuNhap " + key;
        }
        return PhieuNhapModel.getDanhSachPhieuNhap(query);
    }
    
    public void doAddPhieuNhap(){
        if(QLPhieuNhapForm.check == true){
            pnview.showMessage("Vui lòng \"Làm mới\" và nhập thông tin \n", false);
        } else {
            PhieuNhapModel pn = QLPhieuNhapForm.getPhieuNhapInput();
            if(pn.getMaPhieuNhap().equals("") || pn.getMaNCC().equals("*")){
                pnview.showMessage("Vui lòng điền đủ thông tin \n", false);
            } else {
                String key = "where ma_phieu_nhap ='" + pn.getMaPhieuNhap() + "'";
//              System.out.println(key);
                if(listPhieuNhap(key) == null || listPhieuNhap(key).isEmpty()){
                    if(PhieuNhapModel.addPhieuNhap(pn)){
                        QLPhieuNhapForm.loadTablePhieuNhap(listPhieuNhap(""));
                        pnview.showMessage("Thêm phiếu xuất thành công \n", true);
                    } else {
                        pnview.showMessage("Thêm phiếu xuất thất bại \n", false);
                    }
                } else pnview.showMessage("Mã phiếu xuất đã tồn tại \n", false);
            }
        }
    }
    
    public void doSearchPhieuNhap(){
        String key = "";    
        PhieuNhapModel px = QLPhieuNhapForm.getPhieuNhapInput();
        if ("Da nhap".equals(px.getTrangThai()) && QLPhieuNhapForm.checkTrangThai.isSelected()) {
            key = key + "trang_thai = 1";
        } else if ("Chua nhap".equals(px.getTrangThai()) && QLPhieuNhapForm.checkTrangThai.isSelected()){
            key = key + "trang_thai = 0";
        }
        if (!"".equals(px.getMaPhieuNhap()) && QLPhieuNhapForm.checkMaPN.isSelected()){
            if("".equals(key)){
                key = key + "ma_phieu_nhap like " + "'%" + px.getMaPhieuNhap()+ "%'";
            } else {
                key = key + " and ma_phieu_nhap like " + "'%" + px.getMaPhieuNhap()+ "%'";
            }      
        }
        if (!"".equals(px.getMaNCC()) && QLPhieuNhapForm.checkMaNCC.isSelected()){
            if("".equals(key)){
                key = key + "ma_ncc like " + "'%" + px.getMaNCC()+ "%'";
            } else {
                key = key + " and ma_ncc like " + "'%" + px.getMaNCC()+ "%'";
            }      
        }
         if (!"".equals(px.getMaND())  && QLPhieuNhapForm.checkNguoiLap.isSelected()){
            if("".equals(key)){
                key = key + "ma_nd like " + "'%" + px.getMaND()+ "%'";
            } else {
                key = key + " and ma_nd like " + "'%" + px.getMaND()+ "%'";
            }      
        }
          if (!"".equals(px.getNgayTao())  && QLPhieuNhapForm.checkNgayTao.isSelected()){
            if("".equals(key)){
                key = key + "ngay_tao = " + "'" + px.getNgayTao()+ "'";
            } else {
                key = key + " and ngay_tao = " + "'" + px.getNgayTao()+ "'";
            }      
        }
           if (!"".equals(px.getNgayNhap())  && QLPhieuNhapForm.checkNgayNhap.isSelected()){
            if("".equals(key)){
                key = key + "ngay_nhap = " + "'" + px.getNgayNhap()+ "'";
            } else {
                key = key + " and ngay_nhap = " + "'" + px.getNgayNhap()+ "'";
            }      
        }

//        System.out.println(key);
        if ("".equals(key)){
            pnview.showMessage("Vui lòng chọn và nhập thông tin tìm kiếm \n", false);
            QLPhieuNhapForm.loadTablePhieuNhap(listPhieuNhap(""));
        } else {
            key = " where " + key ;
            ArrayList<PhieuNhapModel> pxs = listPhieuNhap(key);
            if(pxs.isEmpty()){
                pnview.showMessage("Không tìm thấy thông tin phù hợp \n", false);
                QLPhieuNhapForm.loadTablePhieuNhap(listPhieuNhap(""));
            } else QLPhieuNhapForm.loadTablePhieuNhap(pxs);
        }  
    }    
    
    public void doUpdatePhieuNhap(){
        PhieuNhapModel px = QLPhieuNhapForm.getPhieuNhapInput();
        if(QLPhieuNhapForm.check == false){
            pnview.showMessage("Vui lòng chọn thông tin cần sửa \n", false);
        } else {
            if(px.getMaPhieuNhap().equals("") || px.getMaNCC().equals("") || px.getMaND().equals("")){
                pnview.showMessage("Vui lòng điền đủ thông tin \n", false);
            }
            else {
                if(PhieuNhapModel.updatePhieuNhap(px)){
                    QLPhieuNhapForm.loadTablePhieuNhap(listPhieuNhap(""));
                    pnview.showMessage("Sửa sản phẩm thành công \n",true );
                } else {
                    pnview.showMessage("Sửa sản phẩm thất bại \n", false);
                }  
            } 
        } 
    }
    
    public void doDeletePhieuNhap(){
        if (QLPhieuNhapForm.check == true){
            if (QLPhieuNhapForm.xacNhanXoa()){
                if(PhieuNhapModel.deletePhieuNhap(QLPhieuNhapForm.getPhieuNhapInput().getMaPhieuNhap())){
                    QLPhieuNhapForm.loadTablePhieuNhap(listPhieuNhap(""));
                    pnview.showMessage("Xóa thành công \n", true);               
                } else {
                    pnview.showMessage("Xóa thất bại \n", false);
                }
            }
        } else pnview.showMessage("Vui lòng chọn thông tin cần xóa \n", false); 
    }
    
    public void doAddChiTietPhieuNhap(String MaPhieuNhap){
        if(QLPhieuNhap_ChiTietForm.check == true){
            pnctview.showMessage("Vui lòng \"Làm mới\" và nhập thông tin \n", false);
        } else {
            SanPhamModel sp = QLPhieuNhap_ChiTietForm.getChiTietPhieuNhapInput();
            if(sp.getMaSP().equals("") || sp.getSoLuong() <= 0 ){
                pnctview.showMessage("Vui lòng điền đủ thông tin \n", false);
            } else {
//              System.out.println(key);
                if(null == listChiTietPhieuNhap(MaPhieuNhap,sp.getMaSP(),"").getMaPhieuNhap()){
                    if(PhieuNhapModel.addChiTietPhieuNhap(sp, MaPhieuNhap)){
                        QLPhieuNhap_ChiTietForm.loadTableChiTietPhieuNhap(listChiTietPhieuNhap(MaPhieuNhap,"","").getDanhSachSP());
                        QLPhieuNhapForm.loadTablePhieuNhap(listPhieuNhap(""));
                        pnctview.showMessage("Thêm chi tiết phiếu xuất thành công \n", true);
                    } else {
                        pnctview.showMessage("Thêm chi tiết phiếu xuất thất bại \n", false);
                    }
                } else pnctview.showMessage("Sản phẩm đã tồn tại trong phiếu xuất \n", false);
            }
        }
    }
    
    public void doUpdateChiTietPhieuNhap(String MaPhieuNhap){
        SanPhamModel sp = QLPhieuNhap_ChiTietForm.getChiTietPhieuNhapInput();
        if(QLPhieuNhap_ChiTietForm.check == false){
            pnctview.showMessage("Vui lòng chọn thông tin cần sửa \n", false);
        } else {
            if(sp.getSoLuong() <= 0){
                pnctview.showMessage("Vui lòng điền số lượng \n", false);
            }
            else {
                if(PhieuNhapModel.updateChiTietPhieuNhap(sp, MaPhieuNhap)){
                    QLPhieuNhap_ChiTietForm.loadTableChiTietPhieuNhap(listChiTietPhieuNhap(MaPhieuNhap,"","").getDanhSachSP());
                    QLPhieuNhapForm.loadTablePhieuNhap(listPhieuNhap(""));
                    pnctview.showMessage("Sửa thành công \n",true );
                } else {
                    pnctview.showMessage("Sửa thất bại \n", false);
                }  
            } 
        }  
    }
    
    public void doSearchChiTietPhieuNhap(String MaPhieuNhap){
        String key = "";    
        SanPhamModel sp = QLPhieuNhap_ChiTietForm.getChiTietPhieuNhapInput();
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
            pnctview.showMessage("Vui lòng nhập thông tin tìm kiếm \n", false);
            QLPhieuNhap_ChiTietForm.loadTableChiTietPhieuNhap(listChiTietPhieuNhap(MaPhieuNhap,"","").getDanhSachSP());
        } else {
            key = " and (" + key + ")";
            ArrayList<SanPhamModel> sps = listChiTietPhieuNhap(MaPhieuNhap, MaPhieuNhap,key).getDanhSachSP();
            if(sps.isEmpty()){
                pnctview.showMessage("Không tìm thấy thông tin phù hợp \n", false);
                QLPhieuNhap_ChiTietForm.loadTableChiTietPhieuNhap(listChiTietPhieuNhap(MaPhieuNhap,"","").getDanhSachSP());
            } else QLPhieuNhap_ChiTietForm.loadTableChiTietPhieuNhap(sps);
        }  
    } 
    
    public void doDeleteChiTietPhieuNhap(String MaPhieuNhap){
        if (QLPhieuNhap_ChiTietForm.check == true){
            if (QLPhieuNhap_ChiTietForm.xacNhanXoa()){
                if(PhieuNhapModel.deleteChiTietPhieuNhap(QLPhieuNhap_ChiTietForm.getChiTietPhieuNhapInput().getMaSP(), MaPhieuNhap)){
                    QLPhieuNhap_ChiTietForm.loadTableChiTietPhieuNhap(listChiTietPhieuNhap(MaPhieuNhap,"","").getDanhSachSP());
                    QLPhieuNhapForm.loadTablePhieuNhap(listPhieuNhap(""));
                    pnctview.showMessage("Xóa thành công \n", true);               
                } else {
                    pnctview.showMessage("Xóa thất bại \n", false);
                }
            }
        } else pnctview.showMessage("Vui lòng chọn thông tin cần xóa \n", false); 
    }
}
