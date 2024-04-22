package com.ni.rental.service;

import static com.ni.util.Constants.PAGE_MAX_RESULT;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.ni.rental.dao.RentalHibernateDAO;
import com.ni.rental.dao.RentalDAOHibernateImpl;
import com.ni.rental.vo.RentalVO;
import com.ni.rental.service.RentalService_Interface;
import com.ni.util.HibernateUtil;
import com.ni.util.Constants;


    // 搭配 JSP / Thymeleaf 後端渲染畫面，將交易動作至於 view filter
    public class RentalServiceImpl implements RentalService_Interface {

        // 一個 service 實體對應一個 dao 實體
        private RentalHibernateDAO dao;

        public RentalServiceImpl() {
            dao = new RentalDAOHibernateImpl();
        }
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// addRentalCat
        @Override
        public RentalVO addRental(String rName, BigDecimal rPrice, Integer rSize, String rColor, String rInfo, Byte rStat, Integer rCatNo) {

            RentalVO rentalVO = new RentalVO();
            rentalVO.setrName(rName);
            rentalVO.setrPrice(rPrice);
            rentalVO.setrSize(rSize);
            rentalVO.setrColor(rColor);
            rentalVO.setrInfo(rInfo);
            rentalVO.setrStat(rStat);
            rentalVO.setrCatNo(rCatNo);
            dao.add(rentalVO);// 將VO放入DAO的方法內執行資料庫操作

            return rentalVO;
        }
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// updateRentalCat
        @Override
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
            return rentalVO;
        }

        @Override
        public void deleteRental(Integer rNo) {
            dao.delete(rNo);
        }


        @Override //單筆查詢(PK)
        public RentalVO getOneRental(Integer rNo) {
            return dao.getByPK(rNo);
        }

        @Override   //萬用複合查詢
        public List<RentalVO> getAll() {
            return dao.getAll();
        }

        @Override
        public List<RentalVO> getAllRentals(int currentPage) {
            return dao.getAllRentals(currentPage);
        }

        @Override
        public List<RentalVO> getByCompositeQuery(Map<String, String[]> map) {
            Map<String, String> query = new HashMap<>();
            // Map.Entry即代表一組key-value
            Set<Map.Entry<String, String[]>> entry = map.entrySet();

            for (Map.Entry<String, String[]> row : entry) {
                String key = row.getKey();
                // 因為請求參數裡包含了action，做個去除動作
                if ("action".equals(key)) {
                    continue;
                }
                // 若是value為空即代表沒有查詢條件，做個去除動作
                String value = row.getValue()[0]; // getValue拿到一個String陣列, 接著[0]取得第一個元素檢查
                if (value == null || value.isEmpty()) {
                    continue;
                }
                query.put(key, value);
            }

            System.out.println(query);

            return dao.getByCompositeQuery(query);
        }

        @Override
        public int getPageTotal() {
            long total = dao.getPageTotal();
//            // 計算Emp數量每頁3筆的話總共有幾頁
            int pageQty = (int)(total % PAGE_MAX_RESULT == 0 ? (total / PAGE_MAX_RESULT) : (total / PAGE_MAX_RESULT + 1));
            return pageQty;
        }

    }

