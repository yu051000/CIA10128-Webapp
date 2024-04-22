package com.ni.rental.service;

import com.ni.rental.vo.RentalVO;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface RentalService_Interface {

    RentalVO addRental(String rName, BigDecimal rPrice, Integer rSize, String rColor, String rInfo, Byte rStat, Integer rCatNo);

    RentalVO updateRental(Integer rNo, String rName,BigDecimal rPrice, Integer rSize, String rColor, String rInfo, Byte rStat, Integer rCatNo);

    void deleteRental(Integer rNo);

    RentalVO getOneRental(Integer rNo);//單筆查詢

    List<RentalVO> getAll();  //萬用複合查詢

    List<RentalVO> getAllRentals(int currentPage); //查詢當前頁面

    List<RentalVO> getByCompositeQuery(Map<String, String[]> map); //複合查詢

    int getPageTotal();
}
