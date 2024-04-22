package com.ni.rentalcategory.service;

import static com.ni.util.Constants.PAGE_MAX_RESULT;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.ni.rentalcategory.dao.RentalCategoryHibernateDAO;
import com.ni.rentalcategory.dao.RentalCategoryDAOHibernateImpl;
import com.ni.rentalcategory.vo.RentalCategoryVO;
import com.ni.rentalcategory.service.RentalCategoryService_Interface;
import com.ni.util.HibernateUtil;
import com.ni.util.Constants;


// 搭配 JSP / Thymeleaf 後端渲染畫面，將交易動作至於 view filter
    public class RentalCategoryServiceImpl implements RentalCategoryService_Interface {

        // 一個 service 實體對應一個 dao 實體
        private RentalCategoryHibernateDAO dao;

        public RentalCategoryServiceImpl() {
            dao = new RentalCategoryDAOHibernateImpl();
        }

        @Override
        public RentalCategoryVO addRentalCat(RentalCategoryVO rentalCategoryVO) {

            dao.add(rentalCategoryVO);// 將VO放入DAO的方法內執行資料庫操作
            return rentalCategoryVO;
        }

        @Override
        public RentalCategoryVO updateRentalCat(RentalCategoryVO rentalCategoryVO) {

            dao.update(rentalCategoryVO);
            return rentalCategoryVO;
        }

        @Override
        public void deleteRentalCat(Integer rCatNo) {
            dao.delete(rCatNo);
        }

        @Override
        public RentalCategoryVO getByPK(Integer rCatNo) {
            return dao.getByPK(rCatNo);
        }

        @Override   //萬用複合查詢
        public List<RentalCategoryVO> getAll() {
            return dao.getAll();
        }

        @Override
        public List<RentalCategoryVO> getAllRentalCats(int currentPage) {
            return dao.getAllRentalCats(currentPage);
        }

        @Override
        public List<RentalCategoryVO> getByCompositeQuery(Map<String, String[]> map) {
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

