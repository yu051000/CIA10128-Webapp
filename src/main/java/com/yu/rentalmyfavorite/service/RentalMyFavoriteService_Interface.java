package com.yu.rentalmyfavorite.service;

import com.yu.rentalmyfavorite.model.RentalMyFavoriteVO;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

    public interface RentalMyFavoriteService_Interface {

        RentalMyFavoriteVO addRentalFav(Integer rNo, Integer memNo, Timestamp rFavTime);

        RentalMyFavoriteVO updateRentalFav(Integer rNo, Integer memNo, Timestamp rFavTime);

        void deleteRentalFav(Integer rNo);

        RentalMyFavoriteVO getOneRentalFav(Integer rNo);//單筆查詢

        List<RentalMyFavoriteVO> getAll();  //萬用複合查詢

        List<RentalMyFavoriteVO> getAllRentalFavs(int currentPage); //查詢當前頁面

        List<RentalMyFavoriteVO> getByCompositeQuery(Map<String, String[]> map); //複合查詢

        int getPageTotal();
    }
