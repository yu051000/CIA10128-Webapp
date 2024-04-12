package com.rental.model;

import java.math.BigDecimal;
import java.util.List;

public class RentalService {

	private RentalDAO_interface dao;

	public RentalService() {
		dao = new RentalJDBCDAO();
//		dao = new RentalDAO();
	}

	public RentalVO addRental(String rName,BigDecimal rPrice, Integer rSize, String rColor, String rInfo, Byte rStat, Integer rCatNo) {

		RentalVO rentalVO = new RentalVO();
		rentalVO.setrName(rName);
		rentalVO.setrPrice(rPrice);
		rentalVO.setrSize(rSize);
		rentalVO.setrColor(rColor);
		rentalVO.setrInfo(rInfo);
		rentalVO.setrStat(rStat);
		rentalVO.setrCatNo(rCatNo);
		dao.insert(rentalVO);

		return rentalVO;
	}

//	//新增 (Spring MVC用)
//	public void addRental(RentalVO rentalVO) {
//		dao.insert(rentalVO);
//	}
	
	//修改
	public RentalVO updateRental(Integer rNo, String rName,BigDecimal rPrice, Integer rSize, String rColor, String rInfo, Byte rStat, Integer rCatNo) {

		RentalVO rentalVO = new RentalVO();

		rentalVO.setrNo(rNo);
		rentalVO.setrName(rName);
		rentalVO.setrPrice(rPrice);
		rentalVO.setrSize(rSize);
		rentalVO.setrColor(rColor);
		rentalVO.setrInfo(rInfo);
		rentalVO.setrStat(rStat);
		rentalVO.setrCatNo(rCatNo);
		dao.update(rentalVO);

		return dao.findByPrimaryKey(rNo);
	}
	
	//單筆取得
	public RentalVO getOneRental(Integer rNo) {
		return dao.findByPrimaryKey(rNo);
	}
	
	//整筆取得
	public List<RentalVO> getAll() {
		return dao.getAll();
	}
}