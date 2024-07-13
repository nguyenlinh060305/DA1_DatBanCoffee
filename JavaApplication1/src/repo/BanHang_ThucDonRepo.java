package repo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.BanHang_ThucDon;
import utils.ConnectDB;

/**
 *
 * @author sangh
 */
public class BanHang_ThucDonRepo {

    public List<BanHang_ThucDon> selectLH() {
        List<BanHang_ThucDon> list = new ArrayList<>();
        String sql = "SELECT TENLH FROM LOAIHANG";
        try {
            ResultSet rs = null;
            rs = ConnectDB.executeQuery(sql);
            while (rs.next()) {
                BanHang_ThucDon bh = new BanHang_ThucDon();
                bh.setTenLoaiHang(rs.getString("TENLH"));
                list.add(bh);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public List<BanHang_ThucDon> selectByLH(String maLh) {
        List<BanHang_ThucDon> list = new ArrayList<>();
        String sql = "SELECT LOAIHANG.TENLH, HANGHOA.TENHH, HANGHOA.GIASP " +
                 "FROM HANGHOA " +
                 "JOIN LOAIHANG ON HANGHOA.MALH = LOAIHANG.MALH " +
                 "WHERE LOAIHANG.TENLH = ?";
        try (PreparedStatement ps = ConnectDB.getConnection().prepareStatement(sql)) {
            ps.setString(1, maLh);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                BanHang_ThucDon bh = new BanHang_ThucDon();
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

}
