package com.yu.rentalcategory.dao;

import com.yu.rentalcategory.model.RentalCategoryVO;

import java.util.*;

public interface RentalCategoryDAO_interface {
    public void insert(RentalCategoryVO rentalCategoryVO); //新增

    public void update(RentalCategoryVO rentalCategoryVO); //修改

    public void delete(Integer rCatNo); //刪除

    public RentalCategoryVO findByPrimaryKey(Integer rCatNo); //查詢

    public List<RentalCategoryVO> getAll(); //萬用複合查詢(傳入參數型態Map)(回傳 List)
}