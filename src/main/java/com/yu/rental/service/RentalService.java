//package com.ni.rental.service;
//
//import com.ni.rental.dao.RentalDAO_interface;
//import com.ni.rental.dao.RentalJDBCDAO;
//import com.ni.rental.vo.RentalVO;
//
//import java.math.BigDecimal;
//import java.util.*;
//
//public class RentalService {
//
//	private RentalDAO_interface dao;
//
//	public RentalService() {
//		dao = new RentalJDBCDAO();
////		dao = new RentalDAO();
//	}
//
//	public RentalVO addRental(String rName, BigDecimal rPrice, Integer rSize, String rColor, String rInfo, Byte rStat, Integer rCatNo) {
//
//		RentalVO rentalVO = new RentalVO();
//		rentalVO.setrName(rName);
//		rentalVO.setrPrice(rPrice);
//		rentalVO.setrSize(rSize);
//		rentalVO.setrColor(rColor);
//		rentalVO.setrInfo(rInfo);
//		rentalVO.setrStat(rStat);
//		rentalVO.setrCatNo(rCatNo);
//
//		dao.insert(rentalVO);
//		return rentalVO;
//	}
//
//	//修改
//	public RentalVO updateRental(Integer rNo, String rName,BigDecimal rPrice, Integer rSize, String rColor, String rInfo, Byte rStat, Integer rCatNo) {
//
//		RentalVO rentalVO = new RentalVO();
//		rentalVO.setrNo(rNo);
//		rentalVO.setrName(rName);
//		rentalVO.setrPrice(rPrice);
//		rentalVO.setrSize(rSize);
//		rentalVO.setrColor(rColor);
//		rentalVO.setrInfo(rInfo);
//		rentalVO.setrStat(rStat);
//		rentalVO.setrCatNo(rCatNo);
//
//		dao.update(rentalVO);
//		return rentalVO;
//	}
//
//	//刪除
//	public void deleteRental(Integer rNo) {
//		dao.delete(rNo);
//	}
//
//	//單筆查詢(PK)
//	public RentalVO getOneRental(Integer rNo) {
//		return dao.findByPrimaryKey(rNo);
//	}
//
//	//整筆查詢
//	public List<RentalVO> getAll() {
//		return dao.getAll();
//	}
//
//	//查詢某類別的租借品(一對多)(回傳 Set)
//	public Set<RentalVO> getRentalsByrCatNo(Integer rCatNo) {
//		return dao.getRentalsByrCatNo(rCatNo);
//	}
//}