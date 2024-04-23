package com.yu.rentalmyfavorite.dao;

import com.yu.rentalmyfavorite.model.RentalMyFavoriteVO;

import java.util.List;
import java.util.Map;

public interface RentalMyFavoriteHibernateDAO {

    int add(RentalMyFavoriteVO rentalMyFavoriteVO);  //若是使用Boolean，即可判斷是否有新增成功

    int update(RentalMyFavoriteVO rentalMyFavoriteVO); //修改

    int delete(Integer rNo, Integer memNo); //刪除

    RentalMyFavoriteVO getByPK(Integer rNo, Integer memNo); //使用PK去搜尋處理

//    List<RentalMyFavoriteVO> getByName(String rCatName);

    List<RentalMyFavoriteVO> getAll(); //萬用複合查詢

    List<RentalMyFavoriteVO> getByCompositeQuery(Map<String, String> map); //複合查詢

    List<RentalMyFavoriteVO> getAllRentalFavs(int currentPage); //查詢當前頁面

    int getPageTotal();
}
