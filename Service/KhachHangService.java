/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Entity.KhachHang;
import Repository.KhachHangRepo;
import java.util.List;

/**
 *
 * @author Linh Nguyen
 */
public class KhachHangService {
    KhachHangRepo khRepo = new KhachHangRepo();
    public List<KhachHang> getList() {
        return khRepo.getDB();
    }
    
    public void them(KhachHang kh) {
        khRepo.insert(kh);
    }
    
    public void sua(KhachHang kh, int maKhachHang) {
        khRepo.update(kh, maKhachHang);
    }
    
    public void xoa(int maKhachHang) {
        khRepo.delete(maKhachHang);
    }
    
    public List<KhachHang> getTimKiem(int maKhachHang, String tenKH) {
        return khRepo.timKiem(maKhachHang, tenKH);
    }
}
