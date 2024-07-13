/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author sangh
 */
public class BanHang_ThucDon {
    private String tenLoaiHang;
    private String tenHangHoa;
    private int giaSp;

    public BanHang_ThucDon() {
    }

    public BanHang_ThucDon(String tenLoaiHang, String tenHangHoa, int giaSp) {
        this.tenLoaiHang = tenLoaiHang;
        this.tenHangHoa = tenHangHoa;
        this.giaSp = giaSp;
    }

    public String getTenLoaiHang() {
        return tenLoaiHang;
    }

    public void setTenLoaiHang(String tenLoaiHang) {
        this.tenLoaiHang = tenLoaiHang;
    }

    public String getTenHangHoa() {
        return tenHangHoa;
    }

    public void setTenHangHoa(String tenHangHoa) {
        this.tenHangHoa = tenHangHoa;
    }

    public int getGiaSp() {
        return giaSp;
    }

    public void setGiaSp(int giaSp) {
        this.giaSp = giaSp;
    }

    @Override
    public String toString() {
        return this.tenLoaiHang;
    }
    
    
}
