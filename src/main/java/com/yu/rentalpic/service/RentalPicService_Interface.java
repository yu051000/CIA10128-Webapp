package com.yu.rentalpic.service;

import com.yu.rentalpic.model.RentalPicVO;

import java.util.List;
import java.util.Map;

    public interface RentalPicService_Interface {

        RentalPicVO addRentalPic(Integer rNo, byte[] rPic);

        RentalPicVO updateRentalPic(Integer rNo, byte[] rPic);

        void deleteRentalPic(Integer rPicNo);

        RentalPicVO getOneRentalPic(Integer rPicNo);//單筆查詢

        List<RentalPicVO> getAll();  //萬用複合查詢

        List<RentalPicVO> getAllRentalPics(int currentPage); //查詢當前頁面

        List<RentalPicVO> getByCompositeQuery(Map<String, String[]> map); //複合查詢

        int getPageTotal();
    }