package com.yu.rental.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import com.yu.rental.model.RentalVO;
import com.yu.util.HibernateUtil;
import org.hibernate.SessionFactory;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import static com.yu.util.Constants.PAGE_MAX_RESULT;
import static java.lang.Byte.valueOf;

public class RentalDAOHibernateImpl implements RentalHibernateDAO {  //Impl是實作類別 (企業常見)

    // SessionFactory 為 thread-safe，可宣告為屬性讓請求執行緒們共用
    private SessionFactory factory;

    public RentalDAOHibernateImpl() {
        factory = HibernateUtil.getSessionFactory();
    }

    // Session 為 not thread-safe，所以此方法在各個增刪改查方法裡呼叫
    // 以避免請求執行緒共用了同個 Session
    private Session getSession() {
        return factory.getCurrentSession();
    }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// add
    @Override
    public int insert(RentalVO rentalVO) {
        // 回傳給 service 剛新增成功的自增主鍵值
        return (Integer) getSession().save(rentalVO);
    }
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// update
    @Override
    public int update(RentalVO rentalVO) {
        try {
            getSession().update(rentalVO);
            return 1;
        } catch (Exception e) {
            return -1;
        }
    }
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// delete
    @Override
    public int delete(Integer rNo) {
        RentalVO rentalVO = getSession().get(RentalVO.class, rNo);
        if (rentalVO != null) {
            getSession().delete(rentalVO);

            return 1; // 回傳給 service，1代表刪除成功
        } else {
            return -1; // 回傳給 service，-1代表刪除失敗
        }
    }
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// getByPK
    @Override
    public RentalVO getByPK(Integer rNo) {
            return getSession().get(RentalVO.class, rNo);
        }
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// getAll
    @Override //查詢全部(回傳List集合)
    public List<RentalVO> getAll() {
        return getSession().createQuery("from RentalVO", RentalVO.class).list();
    }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// getByCompositeQuery
    // 複合查詢
    @Override
    public List<RentalVO> getByCompositeQuery(Map<String, String> map) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            if (map.size() == 0) { //如果陣列為空
                return getAll(); //回傳所有租借品類別的資料
            }

            // 使用CriteriaQuery(標準查詢)，將查詢條件封裝成一個物件//
            CriteriaBuilder builder = session.getCriteriaBuilder(); // 建立Session 物件後，再CriteriaBuilder建立查詢條件
            CriteriaQuery<RentalVO> criteria = builder.createQuery(RentalVO.class); // 創建一個新的CriteriaQuery，並指定返回的實體類型為<RentalVO>
            Root<RentalVO> root = criteria.from(RentalVO.class); //Root是查詢的實體型別，並指定查詢的起始位置(RentalVO.class)

            List<Predicate> predicates = new ArrayList<>(); //Predicate是JPA套件。創建存儲查詢條件的空list，條件將添加到 predicates 中

            // 使用for-each迴圈，檢查map集合中的每一個鍵值
            // map.entrySet()返回Set<Map.Entry<String, String>>的值。透過getKey & getValue取得鍵&值
            for (Map.Entry<String, String> row : map.entrySet()) {
                if ("rCatNo".equals(row.getKey())) { //租借品類別編號
                    predicates.add(builder.equal(root.get("rStockQty"), row.getValue()));
                }

                if ("rName".equals(row.getKey())) { //租借品名稱
                    predicates.add(builder.like(root.get("rCatName"), "%" + row.getValue() + "%")); //使用like方法來建立模糊查詢
                }

                if ("rPrice".equals(row.getKey())) { //租借品單價
                    if (!map.containsKey("rPrice"))
                        predicates.add(builder.lessThanOrEqualTo(root.get("rPrice"), new BigDecimal(row.getValue())));
                }

                if ("rSize".equals(row.getKey())) { //尺寸
                    predicates.add(builder.equal(root.get("rSize"), row.getValue()));
                }

                if ("rColor".equals(row.getKey())) { //顏色
                    predicates.add(builder.like(root.get("rColor"), "%" + row.getValue() + "%")); //使用like方法來建立模糊查詢
                }

                if ("rInfo".equals(row.getKey())) { //租借品資訊
                    predicates.add(builder.like(root.get("rInfo"), "%" + row.getValue() + "%")); //使用like方法來建立模糊查詢
                }

                if ("rStat".equals(row.getKey())) { //租借品狀態
                    predicates.add(builder.equal(root.get("rStat"), valueOf(row.getValue()))); //valueOf轉成byte物件
                }
            }

            // 將前面建立的條件列表 predicates 轉換為 Predicate 陣列，並將它們用 AND 邏輯連接起來，作為查詢的 WHERE 條件。
            criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
            criteria.orderBy(builder.asc(root.get("rNo"))); // 按照rNo由小到大排列

            //把上述的內容(criteria) 傳入TypedQuery取得結果
            TypedQuery<RentalVO> query = session.createQuery(criteria);

            List<RentalVO> list = query.getResultList(); //使用"分頁"方法

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
    public long getPageTotal() {
        return getSession().createQuery("select count(*) from RentalVO", Long.class).uniqueResult();
    }
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// getAllRentals
    @Override
    public List<RentalVO> getAllRentals(int currentPage) {  //設定分頁
        int first = (currentPage - 1) * PAGE_MAX_RESULT;
        return getSession().createQuery("from RentalVO", RentalVO.class)
                .setFirstResult(first)
                .setMaxResults(PAGE_MAX_RESULT)
                .list();
    }

//======================= 尚待新增 =======================================//
    //    public List<String> getUserNames() {
//        try (Session session = sessionFactory.openSession()) {
//            String hql = "select distinct u.name from User u where u.created > '2020-01-01'";
//            Query<String> query = session.createQuery(hql , String.class);
//            return query.list();
//        }
//    }


}

