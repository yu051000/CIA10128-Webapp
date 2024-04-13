package com.rentalcategory.model;

import com.rental.model.RentalVO;
import java.util.*;

public interface RentalCategoryDAO_interface {
    public void insert(RentalCategoryVO rentalCategoryVO); //前端請求

    public void update(RentalCategoryVO rentalCategoryVO); //前端請求

    public void delete(Integer rCatNo);

    public RentalCategoryVO findByPrimaryKey(Integer rCatNo);

    public List<RentalCategoryVO> getAll(); //萬用複合查詢(傳入參數型態Map)(回傳 List)
}
