/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import java.util.Date;

/**
 *
 * @author Linh Nguyen
 */
public class HoaDon {
    public int maHoaDon;
    public String maNV;
    public Date ngayHDBH;
    public int tongTien;

    public HoaDon() {
    }

    public HoaDon(int maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public HoaDon(int maHoaDon, String maNV, Date ngayHDBH, int tongTien) {
        this.maHoaDon = maHoaDon;
        this.maNV = maNV;
        this.ngayHDBH = ngayHDBH;
        this.tongTien = tongTien;
    }

    public int getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(int maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public Date getNgayHDBH() {
        return ngayHDBH;
    }

    public void setNgayHDBH(Date ngayHDBH) {
        this.ngayHDBH = ngayHDBH;
    }

    public int getTongTien() {
        return tongTien;
    }

    public void setTongTien(int tongTien) {
        this.tongTien = tongTien;
    }
     
    
}
