//package com.yu.rental.dao;
//
//import com.yu.rental.model.RentalVO;
//
//import java.util.*;
//
//public interface RentalDAO_interface {
//    public void insert(RentalVO rentalVO); //前端請求
//
//    public void update(RentalVO rentalVO); //前端請求
//
//    public void delete(Integer rNo);
//
//    public RentalVO findByPrimaryKey(Integer rNo);
//
//    public List<RentalVO> getAll(); //萬用複合查詢(傳入參數型態Map)(回傳 List)
//
//    public Set<RentalVO> getRentalsByrCatNo(Integer rCatNo);  //查詢某類別的租借品(一對多)(回傳 Set)
//}