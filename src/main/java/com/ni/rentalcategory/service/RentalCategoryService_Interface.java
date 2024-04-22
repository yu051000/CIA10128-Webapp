package com.ni.rentalcategory.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import com.ni.rentalcategory.vo.RentalCategoryVO;
import com.ni.util.Constants;

public interface RentalCategoryService_Interface {

    RentalCategoryVO addRentalCat(RentalCategoryVO rentalCategoryVO);

    RentalCategoryVO updateRentalCat(RentalCategoryVO rentalCategoryVO);

    void deleteRentalCat(Integer rCatNo);

    RentalCategoryVO getByPK(Integer rCatNo); //使用PK去搜尋處理

    List<RentalCategoryVO> getAll();  //萬用複合查詢

    List<RentalCategoryVO> getAllRentalCats(int currentPage); //查詢當前頁面

    List<RentalCategoryVO> getByCompositeQuery(Map<String, String[]> map); //複合查詢

    int getPageTotal();
}
