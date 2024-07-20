/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Entity.HoaDon;
import Repository.HoaDonRepo;
import java.util.List;

/**
 *
 * @author Linh Nguyen
 */
public class HoaDonService {
    HoaDonRepo hdRepo = new HoaDonRepo();
    public List<HoaDon> getHoaDon() {
        return hdRepo.lichSu();
    }
}
