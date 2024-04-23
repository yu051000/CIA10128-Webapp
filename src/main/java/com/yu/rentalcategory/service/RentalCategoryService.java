//package com.yu.rentalcategory.service;
//
//import com.yu.util.HibernateUtil;
//import com.yu.rentalcategory.dao.DAO;
//import com.yu.rentalcategory.dao.RentalCategoryDAO_interface;
//import com.yu.rentalcategory.model.RentalCategoryVO;
//
//import java.math.BigDecimal;
//import java.util.*;
//
//public class RentalCategoryService {
//
//    private RentalCategoryDAO_interface dao;
//
//    public RentalCategoryService() {
////        dao = new RentalCategoryJDBCDAO();
//        dao = new DAO();
//    }
//
//    // 新增商品(將前端request值放入VO由DAO執行SQL語法，並返回VO由Controller轉給View)
//    public RentalCategoryVO addRentalCategory(String rCatName, Integer rStockQty, Integer rRentedQty, BigDecimal rDesPrice) {
//
//        RentalCategoryVO rentalCategoryVO = new RentalCategoryVO();
//
//        rentalCategoryVO.setrCatName(rCatName);
//        rentalCategoryVO.setrStockQty(rStockQty);
//        rentalCategoryVO.setrRentedQty(rRentedQty);
//        rentalCategoryVO.setrDesPrice(rDesPrice);
//        dao.insert(rentalCategoryVO);// 將VO放入DAO的方法內執行資料庫操作
//
//        return rentalCategoryVO;
//    }
//
//    //修改
//    public RentalCategoryVO updateRentalCategory(Integer rCatNo,String rCatName, Integer rStockQty, Integer rRentedQty, BigDecimal rDesPrice) {
//
//        RentalCategoryVO rentalCategoryVO = new RentalCategoryVO();
//
//        rentalCategoryVO.setrCatNo(rCatNo);
//        rentalCategoryVO.setrCatName(rCatName);
//        rentalCategoryVO.setrStockQty(rStockQty);
//        rentalCategoryVO.setrRentedQty(rRentedQty);
//        rentalCategoryVO.setrDesPrice(rDesPrice);
//
//        return dao.findByPrimaryKey(rCatNo);
//    }
//
//    //刪除
//    public void deleteRentalCategory(Integer rCatNo) {
//        dao.delete(rCatNo);
//    }
//
//    //單筆查詢(PK)
//    public RentalCategoryVO getOneRentalCategory(Integer rCatNo) {
//        return dao.findByPrimaryKey(rCatNo);
//    }
//
//    //查詢全部
//    public List<RentalCategoryVO> getAll() {
//        return dao.getAll();
//    }
//}