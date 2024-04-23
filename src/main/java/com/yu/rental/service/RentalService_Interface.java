package com.yu.rental.service;

import com.yu.rental.model.RentalVO;
import java.util.List;
import java.util.Map;

public interface RentalService_Interface {

    RentalVO addRental(RentalVO rentalVO);

    RentalVO updateRental(RentalVO rentalVO);

    void deleteRental(Integer rNo);

    RentalVO getOneRental(Integer rNo);//單筆查詢

    List<RentalVO> getAll();  //萬用複合查詢

    List<RentalVO> getAllRentals(int currentPage); //查詢當前頁面

    List<RentalVO> getByCompositeQuery(Map<String, String[]> map); //複合查詢

    long getPageTotal();
}
