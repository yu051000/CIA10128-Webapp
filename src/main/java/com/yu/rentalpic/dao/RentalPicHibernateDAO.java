package com.yu.rentalpic.dao;

import com.yu.rentalpic.model.RentalPicVO;

import java.util.List;
import java.util.Map;

public interface RentalPicHibernateDAO {

        int add(RentalPicVO rentalPicVO);  //若是使用Boolean，即可判斷是否有新增成功

        int update(RentalPicVO rentalPicVO); //修改

        int delete(Integer rPicNo); //刪除

    RentalPicVO getByPK(Integer rPicNo); //使用PK去搜尋處理

        List<RentalPicVO> getAll(); //萬用複合查詢

        List<RentalPicVO> getByCompositeQuery(Map<String, String> map); //複合查詢

        List<RentalPicVO> getAllRentalPics(int currentPage); //查詢當前頁面

        int getPageTotal();
    }
