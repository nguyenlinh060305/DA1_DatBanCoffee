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
import java.sql.*;

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

    public double fillTongTien(String maBan) throws SQLException {
        double tongTien = 0;
        String sql = "SELECT SUM(CT.SOLUONG * HH.GIASP) AS TONGTIEN "
                + "FROM HOADONBANHANG HD "
                + "JOIN CHITIETBANHANG CT ON HD.MAHDBH = CT.MAHDBH "
                + "JOIN HANGHOA HH ON CT.MAHH = HH.MAHH "
                + "WHERE HD.MABAN = ?";
        try (Connection conn = ConnectDB.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, maBan);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    tongTien = rs.getDouble("TONGTIEN");
                }
            }
        }
        return tongTien;
    }

    public List<BanHang> selectByBan(String maBan) {
        List<BanHang> list = new ArrayList<>();
        String sql = "SELECT hdbh.NGAYHDBH,hdbh.MAHDBH, hh.TENHH, cthd.SOLUONG,hh.GIASP, hh.GIASP * cthd.SOLUONG AS \"THANHTIEN\" \n"
                + "FROM HOADONBANHANG hdbh\n"
                + "JOIN CHITIETBANHANG cthd ON hdbh.MAHDBH = cthd.MAHDBH\n"
                + "JOIN HANGHOA hh ON cthd.MAHH = hh.MAHH\n"
                + "WHERE hdbh.MABAN = ?";
        try (PreparedStatement ps = ConnectDB.getConnection().prepareStatement(sql)) {
            ps.setString(1, maBan);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                BanHang bh = new BanHang();
                bh.setMaHD(rs.getInt("MaHDBH"));
                bh.setTenHang(rs.getString("TENHH"));
                bh.setGia(rs.getInt("GIASP"));
                bh.setSoLuong(rs.getInt("SOLUONG"));
                bh.setThanhTien(rs.getInt("THANHTIEN"));
                bh.setNgayHDBH(rs.getDate("NGAYHDBH"));
                list.add(bh);
            }
            rs.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public boolean checkHoaDon(String maBan) throws SQLException {
        String sql = "SELECT COUNT(*) FROM HOADONBANHANG HD "
                + "JOIN CHITIETBANHANG CT ON HD.MAHDBH = CT.MAHDBH "
                + "WHERE HD.MABAN = ?";
        try (Connection conn = ConnectDB.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, maBan);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }

    public String getMaHHByTenHangHoa(String tenHangHoa) throws SQLException {
        String sql = "SELECT MAHH FROM HANGHOA WHERE TENHH = ?";
        try (PreparedStatement ps = ConnectDB.getConnection().prepareStatement(sql)) {
            ps.setString(1, tenHangHoa);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("MAHH");
                }
            }
        }
        return null;
    }

    public int generateMaHDBH() throws SQLException {
        String sql = "SELECT MAX(MAHDBH) AS maxID FROM HOADONBANHANG";
        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt("maxID") + 1;
            }
        }
        return 1; // Trả về 1 nếu không có hóa đơn nào trước đó
    }

    public int getMaHDByMaBan(String maBan) throws SQLException {
        int maHD = -1;
        String sql = "SELECT MAHDBH FROM HOADONBANHANG WHERE MABAN = ?";
        try (Connection conn = ConnectDB.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, maBan);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                maHD = rs.getInt("MAHDBH");
            }
        }
        return maHD;
    }

    public void resetTableStatus(String maBan) throws SQLException {
        String sql = "UPDATE BAN SET THUOCTINH = N'Trống' WHERE MABAN = ?";
        try (Connection conn = ConnectDB.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, maBan);
            stmt.executeUpdate();
        }
    }

    // Hàm chèn hóa đơn bán hàng vào database
    public void insertHDBHIntoDatabase(int maHDBH, String maBan, String maNV, String ngayHDBH) throws SQLException {
        String sql = "INSERT INTO HOADONBANHANG (MAHDBH, MABAN, MANV, NGAYHDBH, TONGTIEN) VALUES (?, ?, ?, ?, 0)";
        try (Connection conn = ConnectDB.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, maHDBH);
            stmt.setString(2, maBan);
            stmt.setString(3, maNV);
            stmt.setString(4, ngayHDBH);
            stmt.executeUpdate();
        }
    }

    public void insertCTBHIntoDatabase(int maHDBH, String maHH, int soLuong) throws SQLException {
        String sql = "INSERT INTO CHITIETBANHANG (MAHDBH, MAHH, SOLUONG) VALUES (?, ?, ?)";
        try (Connection conn = ConnectDB.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, maHDBH);
            stmt.setString(2, maHH);
            stmt.setInt(3, soLuong);
            stmt.executeUpdate();
        }
    }

    // Hàm cập nhật tổng tiền hóa đơn
    public void deleteOrderItem(int maHDBH, String maHH) throws SQLException {
        String deleteSQL = "DELETE FROM CHITIETBANHANG WHERE MAHDBH = ? AND MAHH = ?";
        try (Connection conn = ConnectDB.getConnection(); PreparedStatement stmt = conn.prepareStatement(deleteSQL)) {
            stmt.setInt(1, maHDBH);
            stmt.setString(2, maHH);
            stmt.executeUpdate();
        }

        // Kiểm tra nếu không còn món nào trong hóa đơn
        String checkItemsSQL = "SELECT COUNT(*) FROM CHITIETBANHANG WHERE MAHDBH = ?";
        try (Connection conn = ConnectDB.getConnection(); PreparedStatement stmt = conn.prepareStatement(checkItemsSQL)) {
            stmt.setInt(1, maHDBH);
            ResultSet rs = stmt.executeQuery();
            if (rs.next() && rs.getInt(1) == 0) {
                // Không còn món nào, cập nhật TONGTIEN thành 0
                updateTongTien(maHDBH, 0);
            } else {
                updateTongTien(maHDBH); // Cập nhật lại tổng tiền nếu vẫn còn món
            }
        }
    }

// Hàm updateTongTien với tham số tổng tiền
    public void updateTongTien(int maHDBH, int tongTien) throws SQLException {
        String updateSQL = "UPDATE HOADONBANHANG SET TONGTIEN = ? WHERE MAHDBH = ?";
        try (Connection conn = ConnectDB.getConnection(); PreparedStatement stmt = conn.prepareStatement(updateSQL)) {
            stmt.setInt(1, tongTien);
            stmt.setInt(2, maHDBH);
            stmt.executeUpdate();
        }
    }

// Hàm updateTongTien mặc định
    public void updateTongTien(int maHDBH) throws SQLException {
        String updateSQL = "UPDATE HOADONBANHANG SET TONGTIEN = (SELECT SUM(CTBH.SOLUONG * HH.GIASP) FROM CHITIETBANHANG CTBH JOIN HANGHOA HH ON CTBH.MAHH = HH.MAHH WHERE CTBH.MAHDBH = ?) WHERE MAHDBH = ?";
        try (Connection conn = ConnectDB.getConnection(); PreparedStatement stmt = conn.prepareStatement(updateSQL)) {
            stmt.setInt(1, maHDBH);
            stmt.setInt(2, maHDBH);
            stmt.executeUpdate();
        }
    }

    public void updateTableStatus(String maBan, String trangThai) throws SQLException {
        String sql = "UPDATE BAN SET THUOCTINH = ? WHERE MABAN = ?";
        try (Connection conn = ConnectDB.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, trangThai);
            stmt.setString(2, maBan);
            stmt.executeUpdate();
        }
    }
}
