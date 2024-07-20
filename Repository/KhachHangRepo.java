/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import Entity.KhachHang;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Linh Nguyen
 */
public class KhachHangRepo {

    public List<KhachHang> getDB() {
        List<KhachHang> lst = new ArrayList<>();
        try {
            Connection conn = DBConnect.getConnection();
            String sql = "SELECT * FROM KHACHHANG";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.execute();
            ResultSet rs = ps.getResultSet();
            while (rs.next() == true) {
                int maKhachHang = rs.getInt("MAKH");
                String maLoaiKhachHang = rs.getString("MALKH");
                String tenKhachHang = rs.getString("TENKH");
                String diaChi = rs.getString("DIACHI");
                String sdt = rs.getString("SDT");
                int diemTL = rs.getInt("DIEMTL");

                KhachHang kh = new KhachHang(maKhachHang, maLoaiKhachHang, tenKhachHang, diaChi, sdt, diemTL);
                lst.add(kh);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lst;
    }

    public void insert(KhachHang kh) {
        try {
            Connection conn = DBConnect.getConnection();
            String sql = "INSERT INTO KHACHHANG (MAKH, MALKH, TENKH, DIACHI, SDT, DIEMTL)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, kh.getMaKhachHang());
            ps.setString(2, kh.getMaLoaiKhachHang());
            ps.setString(3, kh.getTenKhachHang());
            ps.setString(4, kh.getDiaChi());
            ps.setString(5, kh.getSdt());
            ps.setInt(6, kh.getDiemTichLuy());
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(KhachHang kh, int maKhachHang) {
        try {
            Connection conn = DBConnect.getConnection();
            String sql = "UPDATE KHACHHANG SET MALKH = ?, TENKH = ?, DIACHI = ?, SDT = ?, DIEMTL = ? "
                    + "WHERE MAKH = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, kh.getMaLoaiKhachHang());
            ps.setString(2, kh.getTenKhachHang());
            ps.setString(3, kh.getDiaChi());
            ps.setString(4, kh.getSdt());
            ps.setInt(5, kh.getDiemTichLuy());
            ps.setInt(6, kh.getMaKhachHang());
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(int maKhachHang) {
        try {
            Connection conn = DBConnect.getConnection();
            String sql = "DELETE KHACHHANG WHERE MAKH = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, maKhachHang);
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<KhachHang> timKiem(int maKhachHang, String tenKH) {
        List<KhachHang> lst = new ArrayList<>();
        try {
            Connection conn = DBConnect.getConnection();
            String sql = "SELECT * FROM WHERE MAKH = ? OR TENKH LIKE ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, maKhachHang);
            ps.setString(2, "%" + tenKH + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next() == true) {
                int maKH = rs.getInt("MAKH");
                String maLKH = rs.getString("MALKH");
                String tenkh = rs.getString("TENKH");
                String diaChi = rs.getString("DIACHI");
                String sdt = rs.getString("SDT");
                int diemTL = rs.getInt("DIEMTL");

                KhachHang kh = new KhachHang(maKhachHang, maLKH, tenkh, diaChi, sdt, diemTL);
                lst.add(kh);
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lst;
    }
}
