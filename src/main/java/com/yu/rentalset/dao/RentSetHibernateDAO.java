package com.yu.rentalset.dao;

import com.yu.rentalset.model.RentSetVO;

import java.util.List;
import java.util.Map;

public interface RentSetHibernateDAO {

        int add(RentSetVO rentSetVO);  //若是使用Boolean，即可判斷是否有新增成功

        int update(RentSetVO rentSetVO); //修改

        int delete(Integer rOrdNo); //刪除

    RentSetVO getByPK(Integer rOrdNo); //使用PK去搜尋處理

//    List<RentSetVO> getByName(String rCatName);

        List<RentSetVO> getAll(); //萬用複合查詢

        List<RentSetVO> getByCompositeQuery(Map<String, String> map); //複合查詢

        List<RentSetVO> getAllRentalSets(int currentPage); //查詢當前頁面

        int getPageTotal();
    }
