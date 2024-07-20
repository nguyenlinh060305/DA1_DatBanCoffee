/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import Entity.HoaDon;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Linh Nguyen
 */
public class HoaDonRepo {
    public List<HoaDon> lichSu() {
        List<HoaDon> lst = new ArrayList<>();
        try {
            Connection conn = DBConnect.getConnection();
            String sql = "SELECT \n"
                    + "	HDBN.MAHDBH, HDBN.MANV,\n"
                    + "	HDBN.NGAYHDBH, HDBN.TONGTIEN \n"
                    + "FROM KHACHHANG KH\n"
                    + "INNER JOIN HOADONBANHANG HDBN ON HDBN.MAKH = KH.MAKH;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.execute();
            ResultSet rs = ps.getResultSet();
            while (rs.next() == true) {
                int maHD = rs.getInt("MAHDBH");
                String maNV = rs.getString("MANV");
                Date ngayHD = rs.getDate("NGAYHDBH");
                int tongTien = rs.getInt("TONGTIEN");                
                HoaDon hd = new HoaDon(maHD, maNV, ngayHD, tongTien);
                lst.add(hd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lst;
    }
}
