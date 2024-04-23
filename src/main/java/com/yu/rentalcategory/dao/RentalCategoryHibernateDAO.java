package com.yu.rentalcategory.dao;

import java.math.BigDecimal;
import java.util.Map;
import java.util.List;
import com.yu.rentalcategory.model.RentalCategoryVO;

public interface RentalCategoryHibernateDAO {

    int add(RentalCategoryVO rentalCategoryVO);  //若是使用Boolean，即可判斷是否有新增成功

    int update(RentalCategoryVO rentalCategoryVO); //修改

    int delete(Integer rCatNo); //刪除

    RentalCategoryVO getByPK(Integer rCatNo); //使用PK去搜尋處理

    RentalCategoryVO getByName(String rCatName); //搜尋類別名稱

    RentalCategoryVO getByDesPrice(BigDecimal rDesPrice); //搜尋押金

    List<RentalCategoryVO> getAll(); //萬用複合查詢

    List<RentalCategoryVO> getByCompositeQuery(Map<String, String> map); //複合查詢

    List<RentalCategoryVO> getAllRentalCats(int currentPage); //查詢當前頁面

    int getPageTotal();
}