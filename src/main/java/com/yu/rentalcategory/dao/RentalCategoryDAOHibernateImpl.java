package com.yu.rentalcategory.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import com.yu.rentalcategory.model.RentalCategoryVO;
import com.yu.util.HibernateUtil;
import org.hibernate.query.NativeQuery;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import static com.yu.util.Constants.PAGE_MAX_RESULT;
import static java.lang.Byte.valueOf;

public class RentalCategoryDAOHibernateImpl implements RentalCategoryHibernateDAO {  //Impl是實作類別 (企業常見)

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// add
    @Override
    public int add(RentalCategoryVO rentalCategoryVO) {

        //建立SessionFactory，且使用getCurrentSession()取得當前的Session並綁定該Thread(執行續)
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        try {
            session.beginTransaction();
            Integer id = (Integer) session.save(rentalCategoryVO);
            session.getTransaction().commit();
            return id;
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        return -1;
    }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// update
    @Override
    public int update(RentalCategoryVO rentalCategoryVO) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            session.update(rentalCategoryVO);
            session.getTransaction().commit();
            return 1;

        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        return -1;
    }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// delete
    @Override
    public int delete(Integer rCatNo) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            RentalCategoryVO rentalCategoryVO = session.get(RentalCategoryVO.class, rCatNo);
            if (rentalCategoryVO != null) {
                session.delete(rentalCategoryVO);
            }
            session.getTransaction().commit();
            return 1;

        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        return -1;
    }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// getByPK
    @Override
    public RentalCategoryVO getByPK(Integer rCatNo) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            RentalCategoryVO rentalCategoryVO= session.get(RentalCategoryVO.class, rCatNo);
            session.getTransaction().commit();
            return rentalCategoryVO;

        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        return null;
    }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// getAll
    @Override //查詢全部(回傳List集合)
    public List<RentalCategoryVO> getAll() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            List<RentalCategoryVO> list = session.createQuery("from RentalCategoryVO", RentalCategoryVO.class).list();// 在 Hibernate中編寫查詢而轉換的SQL， ("from DB的類名", RentalCategoryVO.class")
            session.getTransaction().commit();
            return list;

        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        return null;
    }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// getByCompositeQuery
    // 複合查詢
    @Override
    public List<RentalCategoryVO> getByCompositeQuery(Map<String, String> map) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            if (map.size() == 0) { //如果陣列為空
                return getAll(); //回傳所有租借品類別的資料
            }

            // 使用CriteriaQuery(標準查詢)，將查詢條件封裝成一個物件//
            CriteriaBuilder builder = session.getCriteriaBuilder(); // 建立Session 物件後，再CriteriaBuilder建立查詢條件
            CriteriaQuery<RentalCategoryVO> criteria = builder.createQuery(RentalCategoryVO.class); // 創建一個新的CriteriaQuery，並指定返回的實體類型為<RentalCategoryVO>
            Root<RentalCategoryVO> root = criteria.from(RentalCategoryVO.class); //Root是查詢的實體型別，並指定查詢的起始位置(RentalCategoryVO.class)

            List<Predicate> predicates = new ArrayList<>(); //Predicate是JPA套件。創建存儲查詢條件的空list，條件將添加到 predicates 中

            // 使用for-each迴圈，檢查map集合中的每一個鍵值
            // map.entrySet()返回Set<Map.Entry<String, String>>的值。透過getKey & getValue取得鍵&值
            for (Map.Entry<String, String> row : map.entrySet()) {
                if ("rNo".equals(row.getKey())) { //庫存數量
                    predicates.add(builder.equal(root.get("rNo"), row.getValue()));
                }

                if ("rPic".equals(row.getKey())) { //租借品狀態
                    predicates.add(builder.equal(root.get("rPic"), valueOf(row.getValue()))); //valueOf轉成byte物件
                }

            }
            // 將前面建立的條件列表 predicates 轉換為 Predicate 陣列，並將它們用 AND 邏輯連接起來，作為查詢的 WHERE 條件。
            criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
            criteria.orderBy(builder.asc(root.get("rCatNo"))); // 按照rCatNo由小到大排列

            //把上述的內容(criteria) 傳入TypedQuery取得結果
            TypedQuery<RentalCategoryVO> query = session.createQuery(criteria);

            List<RentalCategoryVO> list = query.getResultList(); //使用"分頁"方法

            session.getTransaction().commit();
            return list;

        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            HibernateUtil.shutdown();
        }
        return null;
    }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// getPageTotal
    @Override
    public int getPageTotal() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();

            NativeQuery<RentalCategoryVO> query = session.createNativeQuery("select count(*) from RentalCategoryVO");
            query.addEntity(RentalCategoryVO.class); // 使用addEntity()，可指定list回傳的是原本的資料型別
            List<RentalCategoryVO> list = query.list();

            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            HibernateUtil.shutdown();
        }
        return -1;
    }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// getAllRentalCats
    @Override
    public List<RentalCategoryVO> getAllRentalCats(int currentPage) {  //設定分頁
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        int first = (currentPage - 1) * PAGE_MAX_RESULT;

        try {
            session.beginTransaction();

            //根據 currentPage 和 PAGE_MAX_RESULT（每頁最大結果數量）計算了分頁查詢時的第一條結果在數據庫中的索引位置 first
            List<RentalCategoryVO> result = session.createQuery("from RentalCategoryVO", RentalCategoryVO.class)
                    .setFirstResult(first) //設置查詢的起始索引
                    .setMaxResults(PAGE_MAX_RESULT) //設置每頁顯示的最大結果數量
                    .list();
            session.getTransaction().commit();
            return result;

        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            HibernateUtil.shutdown();
        }
        return null;
    }
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// getByName
    @Override
    public RentalCategoryVO getByName(String rCatName) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            RentalCategoryVO rentalCategoryVO= session.get(RentalCategoryVO.class, rCatName);
            session.getTransaction().commit();
            return rentalCategoryVO;

        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        return null;
    }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// getByDesPrice
    @Override
    public RentalCategoryVO getByDesPrice(BigDecimal rDesPrice) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            RentalCategoryVO rentalCategoryVO= session.get(RentalCategoryVO.class, rDesPrice);
            session.getTransaction().commit();
            return rentalCategoryVO;

        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        return null;
    }

}

