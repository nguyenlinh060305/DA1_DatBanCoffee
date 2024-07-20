/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

/**
 *
 * @author Linh Nguyen
 */
public class KhachHang {
    public int maKhachHang;
    public String maLoaiKhachHang, tenKhachHang, diaChi, sdt;
    public int diemTichLuy;

    public KhachHang() {
    }

    public KhachHang(int maKhachHang, String maLoaiKhachHang, String tenKhachHang, String diaChi, String sdt, int diemTichLuy) {
        this.maKhachHang = maKhachHang;
        this.maLoaiKhachHang = maLoaiKhachHang;
        this.tenKhachHang = tenKhachHang;
        this.diaChi = diaChi;
        this.sdt = sdt;
        this.diemTichLuy = diemTichLuy;
    }

    public int getMaKhachHang() {
        return maKhachHang;
    }

    public void setMaKhachHang(int maKhachHang) {
        this.maKhachHang = maKhachHang;
    }

    public String getMaLoaiKhachHang() {
        return maLoaiKhachHang;
    }

    public void setMaLoaiKhachHang(String maLoaiKhachHang) {
        this.maLoaiKhachHang = maLoaiKhachHang;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public int getDiemTichLuy() {
        return diemTichLuy;
    }

    public void setDiemTichLuy(int diemTichLuy) {
        this.diemTichLuy = diemTichLuy;
    }
    
    
}
