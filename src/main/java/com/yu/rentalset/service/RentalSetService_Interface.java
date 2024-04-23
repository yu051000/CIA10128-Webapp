package com.yu.rentalset.service;

import com.yu.rentalset.model.RentSetVO;

import java.util.List;
import java.util.Map;

    public interface RentalSetService_Interface {

        RentSetVO addRentalSet(String rSetName, Byte rSetDays);

        RentSetVO updateRentalSet(String rSetName, Byte rSetDays);

        void deleteRentalSet(Integer rOrdNo);

        RentSetVO getOneRentalSet(Integer rOrdNo);//單筆查詢

        List<RentSetVO> getAll();  //萬用複合查詢

        List<RentSetVO> getAllRentalSets(int currentPage); //查詢當前頁面

        List<RentSetVO> getByCompositeQuery(Map<String, String[]> map); //複合查詢

        int getPageTotal();
    }
