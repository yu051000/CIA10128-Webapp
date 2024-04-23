package com.yu.rental.dao;

import com.yu.rental.model.RentalVO;

import java.util.List;
import java.util.Map;

public interface RentalHibernateDAO {

    int insert(RentalVO rentalVO);  //若是使用Boolean，即可判斷是否有新增成功

    int update(RentalVO rentalVO); //修改

    int delete(Integer rNo); //刪除

    RentalVO getByPK(Integer rNo); //使用PK去搜尋處理

//    List<RentalVO> getByName(String rCatName);

    List<RentalVO> getAll(); //萬用複合查詢

    List<RentalVO> getByCompositeQuery(Map<String, String> map); //複合查詢

    List<RentalVO> getAllRentals(int currentPage); //查詢當前頁面

    long getPageTotal();
}
