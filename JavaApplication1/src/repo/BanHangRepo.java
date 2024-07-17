/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.BanHang;

import utils.ConnectDB;

/**
 *
 * @author hoangminhsang
 */
public class BanHangRepo {

    public List<BanHang> selectBan() {
        String sql = "SELECT KV.TENKV, BAN.MABAN, BAN.TENBAN, BAN.THUOCTINH FROM BAN JOIN KHUVUC KV ON BAN.MAKV = KV.MAKV";
        return select(sql);
    }

    public List<BanHang> select(String sql, Object... args) {
        List<BanHang> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = ConnectDB.executeQuery(sql, args);
                while (rs.next()) {
                    BanHang bhb = new BanHang();
                    bhb.setMaBan(rs.getString("MABAN"));
                    bhb.setViTri(rs.getString("TENKV"));
                    bhb.setTenBan(rs.getString("TENBAN"));
                    bhb.setTrangThai(rs.getString("THUOCTINH"));
                    list.add(bhb);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public List<BanHang> selectLH() {
        List<BanHang> list = new ArrayList<>();
        String sql = "SELECT TENLH FROM LOAIHANG";
        try {
            ResultSet rs = null;
            rs = ConnectDB.executeQuery(sql);
            while (rs.next()) {
                BanHang bh = new BanHang();
                bh.setTenLoaiHang(rs.getString("TENLH"));
                list.add(bh);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public List<BanHang> selectByLH(String maLh) {
        List<BanHang> list = new ArrayList<>();
        String sql = "SELECT LOAIHANG.TENLH, HANGHOA.TENHH, HANGHOA.GIASP "
                + "FROM HANGHOA "
                + "JOIN LOAIHANG ON HANGHOA.MALH = LOAIHANG.MALH "
                + "WHERE LOAIHANG.TENLH = ?";
        try (PreparedStatement ps = ConnectDB.getConnection().prepareStatement(sql)) {
            ps.setString(1, maLh);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                BanHang bh = new BanHang();
                bh.setTenHangHoa(rs.getString("TENHH"));
                bh.setGiaSp(rs.getInt("GIASP"));
                list.add(bh);
            }
            rs.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public List<BanHang> selectByBan(String maBan) {
        List<BanHang> list = new ArrayList<>();
        String sql = "SELECT hh.TENHH, cthd.SOLUONG,hh.GIASP, hh.GIASP * cthd.SOLUONG AS \"THANHTIEN\" \n"
                + "FROM HOADONBANHANG hdbh\n"
                + "JOIN CHITIETBANHANG cthd ON hdbh.MAHDBH = cthd.MAHDBH\n"
                + "JOIN HANGHOA hh ON cthd.MAHH = hh.MAHH\n"
                + "WHERE hdbh.MABAN = ?";
        try (PreparedStatement ps = ConnectDB.getConnection().prepareStatement(sql)) {
            ps.setString(1, maBan);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                BanHang bh = new BanHang();
                bh.setTenHang(rs.getString("TENHH"));
                bh.setGia(rs.getInt("GIASP"));
                bh.setSoLuong(rs.getInt("SOLUONG"));
                bh.setThanhTien(rs.getInt("THANHTIEN"));
                list.add(bh);
            }
            rs.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }
}
