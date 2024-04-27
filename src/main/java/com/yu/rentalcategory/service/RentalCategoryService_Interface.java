package com.yu.rentalcategory.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import com.yu.rentalcategory.model.RentalCategoryVO;

public interface RentalCategoryService_Interface {

    RentalCategoryVO addRentalCat(String rCatName, Integer rStockQty, Integer rRentedQty, BigDecimal rDesPrice);

    RentalCategoryVO updateRentalCat(Integer rCatNo,String rCatName, Integer rStockQty, Integer rRentedQty, BigDecimal rDesPrice);

    void deleteRentalCat(Integer rCatNo);

    RentalCategoryVO getOneRentalCat(Integer rCatNo);//單筆查詢(PK)

    RentalCategoryVO getOneRentalrCatName(String rCatName); //單筆查詢(類別名稱)

    RentalCategoryVO getOneRentalrDesPrice(BigDecimal rDesPrice); //單筆查詢(押金)

    List<RentalCategoryVO> getAll();  //萬用複合查詢

    List<RentalCategoryVO> getAllRentalCats(int currentPage); //查詢當前頁面

    List<RentalCategoryVO> getByCompositeQuery(Map<String, String[]> map); //複合查詢



    int getPageTotal();
}
