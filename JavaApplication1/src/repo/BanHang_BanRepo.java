/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repo;

import java.util.ArrayList;
import java.util.List;
import model.BanHang_Ban;
import java.sql.*;
import utils.ConnectDB;

/**
 *
 * @author sangh
 */
public class BanHang_BanRepo {

    public List<BanHang_Ban> select() {
        String sql = "SELECT KV.TENKV, BAN.TENBAN, BAN.THUOCTINH FROM BAN jOIN KHUVUC KV ON BAN.MAKV = KV.MAKV";
        return select(sql);
    }

    public List<BanHang_Ban> select(String sql, Object... args) {
        List<BanHang_Ban> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = ConnectDB.executeQuery(sql, args);
                while (rs.next()) {
                    BanHang_Ban bhb = new BanHang_Ban();
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
//    public static void main(String[] args) {
//        BanHang_BanRepo bhr = new BanHang_BanRepo();
//        List<BanHang_Ban> list = bhr.select();
//        for(BanHang_Ban bh: list) {
//            System.out.println("Vị trí:" +bh.getViTri() + "TenBan:" + bh.getTenBan());
//        }
//    }
}
