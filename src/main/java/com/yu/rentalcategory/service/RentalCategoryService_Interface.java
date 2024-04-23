package com.ni.rentalcategory.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import com.ni.rentalcategory.vo.RentalCategoryVO;

public interface RentalCategoryService_Interface {

    RentalCategoryVO addRentalCat(String rCatName, Integer rStockQty, Integer rRentedQty, BigDecimal rDesPrice);

    RentalCategoryVO updateRentalCat(Integer rCatNo,String rCatName, Integer rStockQty, Integer rRentedQty, BigDecimal rDesPrice);

    void deleteRentalCat(Integer rCatNo);

    RentalCategoryVO getOneRentalCat(Integer rCatNo);//單筆查詢

    List<RentalCategoryVO> getAll();  //萬用複合查詢

    List<RentalCategoryVO> getAllRentalCats(int currentPage); //查詢當前頁面

    List<RentalCategoryVO> getByCompositeQuery(Map<String, String[]> map); //複合查詢

    int getPageTotal();
}
