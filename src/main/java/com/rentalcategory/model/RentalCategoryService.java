package com.rentalcategory.model;

import java.math.BigDecimal;
import java.util.List;

public class RentalCategoryService {

    private RentalCategoryDAO_interface dao;

    public RentalCategoryService() {
        dao = new RentalCategoryJDBCDAO();
//		dao = new RentalCategoryDAO();
    }

    public RentalCategoryVO addRentalCategory(String rCatName, Integer rStockQty, Integer rRentedQty, BigDecimal rDesPrice) {

        RentalCategoryVO rentalCategoryVO = new RentalCategoryVO();
        rentalCategoryVO.setrCatName(rCatName);
        rentalCategoryVO.setrStockQty(rStockQty);
        rentalCategoryVO.setrStockQty(rRentedQty);
        rentalCategoryVO.setrDesPrice(rDesPrice);

        dao.insert(rentalCategoryVO);
        return rentalCategoryVO;
    }

    //修改
    public RentalCategoryVO updateRentalCategory(Integer rCatNo, String rCatName, Integer rStockQty, Integer rRentedQty, BigDecimal rDesPrice) {

        RentalCategoryVO rentalCategoryVO = new RentalCategoryVO();
        rentalCategoryVO.setrCatNo(rCatNo);
        rentalCategoryVO.setrCatName(rCatName);
        rentalCategoryVO.setrStockQty(rStockQty);
        rentalCategoryVO.setrRentedQty(rRentedQty);
        rentalCategoryVO.setrDesPrice(rDesPrice);

        dao.update(rentalCategoryVO);
        return rentalCategoryVO;
    }

    public void deleteRentalCategory(Integer rCatNo) {
        dao.delete(rCatNo);
    }

    //單筆取得
    public RentalCategoryVO getOneRentalCategory(Integer rCatNo) {
        return dao.findByPrimaryKey(rCatNo);
    }

    //整筆取得
    public List<RentalCategoryVO> getAll() {
        return dao.getAll();
    }
}