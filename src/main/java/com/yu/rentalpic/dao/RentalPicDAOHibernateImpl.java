package com.yu.rentalpic.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.yu.rentalpic.model.RentalPicVO;
import org.hibernate.Session;
import com.yu.util.HibernateUtil;
import org.hibernate.query.NativeQuery;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import static com.yu.util.Constants.PAGE_MAX_RESULT;
import static java.lang.Byte.valueOf;

    public class RentalPicDAOHibernateImpl implements RentalPicHibernateDAO {  //Impl是實作類別 (企業常見)

        @Override
        public int add(RentalPicVO rentalPicVO) {

            //建立SessionFactory，且使用getCurrentSession()取得當前的Session並綁定該Thread(執行續)
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();

            try {
                session.beginTransaction();
                Integer id = (Integer) session.save(rentalPicVO);
                session.getTransaction().commit();
                return id;
            } catch (Exception e) {
                e.printStackTrace();
                session.getTransaction().rollback();
            }
            return -1;
        }

        @Override
        public int update(RentalPicVO rentalPicVO) {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            try {
                session.beginTransaction();
                session.update(rentalPicVO);
                session.getTransaction().commit();
                return 1;

            } catch (Exception e) {
                e.printStackTrace();
                session.getTransaction().rollback();
            }
            return -1;
        }

        @Override
        public int delete(Integer rPicNo) {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            try {
                session.beginTransaction();
                RentalPicVO rentalPicVO = session.get(RentalPicVO.class, rPicNo);
                if (rentalPicVO != null) {
                    session.delete(rentalPicVO);
                }
                session.getTransaction().commit();
                return 1;

            } catch (Exception e) {
                e.printStackTrace();
                session.getTransaction().rollback();
            }
            return -1;
        }

        @Override
        public RentalPicVO getByPK(Integer rPicNo) {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            try {
                session.beginTransaction();
                RentalPicVO rentalPicVO= session.get(RentalPicVO.class, rPicNo);
                session.getTransaction().commit();
                return rentalPicVO;

            } catch (Exception e) {
                e.printStackTrace();
                session.getTransaction().rollback();
            }
            return null;
        }

        @Override //查詢全部(回傳List集合)
        public List<RentalPicVO> getAll() {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            try {
                session.beginTransaction();
                List<RentalPicVO> list = session.createQuery("from RentalPicVO", RentalPicVO.class).list();// 在 Hibernate中編寫查詢而轉換的SQL， ("from DB的類名", RentalPicVO.class")
                session.getTransaction().commit();
                return list;

            } catch (Exception e) {
                e.printStackTrace();
                session.getTransaction().rollback();
            }
            return null;
        }


        // 複合查詢
        @Override
        public List<RentalPicVO> getByCompositeQuery(Map<String, String> map) {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            try {
                session.beginTransaction();
                if (map.size() == 0) { //如果陣列為空
                    return getAll(); //回傳所有租借品類別的資料
                }

                // 使用CriteriaQuery(標準查詢)，將查詢條件封裝成一個物件//
                CriteriaBuilder builder = session.getCriteriaBuilder(); // 建立Session 物件後，再CriteriaBuilder建立查詢條件
                CriteriaQuery<RentalPicVO> criteria = builder.createQuery(RentalPicVO.class); // 創建一個新的CriteriaQuery，並指定返回的實體類型為<RentalPicVO>
                Root<RentalPicVO> root = criteria.from(RentalPicVO.class); //Root是查詢的實體型別，並指定查詢的起始位置(RentalPicVO.class)

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
                criteria.orderBy(builder.asc(root.get("rPicNo"))); // 按照rPicNo由小到大排列

                //把上述的內容(criteria) 傳入TypedQuery取得結果
                TypedQuery<RentalPicVO> query = session.createQuery(criteria);

                List<RentalPicVO> list = query.getResultList(); //使用"分頁"方法

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

        @Override
        public int getPageTotal() {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            try {
                session.beginTransaction();

                NativeQuery<RentalPicVO> query = session.createNativeQuery("select count(*) from RentalPicVO");
                query.addEntity(RentalPicVO.class); // 使用addEntity()，可指定list回傳的是原本的資料型別
                List<RentalPicVO> list = query.list();

                session.getTransaction().commit();

            } catch (Exception e) {
                e.printStackTrace();
                session.getTransaction().rollback();
            } finally {
                HibernateUtil.shutdown();
            }
            return -1;
        }

        @Override
        public List<RentalPicVO> getAllRentalPics(int currentPage) {  //設定分頁
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            int first = (currentPage - 1) * PAGE_MAX_RESULT;

            try {
                session.beginTransaction();
                List<RentalPicVO> result = session.createQuery("from RentalPicVO", RentalPicVO.class)
                        .setFirstResult(first)
                        .setMaxResults(PAGE_MAX_RESULT)
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
//======================= 尚待新增 =======================================//
        //    public List<String> getUserNames() {
//        try (Session session = sessionFactory.openSession()) {
//            String hql = "select distinct u.name from User u where u.created > '2020-01-01'";
//            Query<String> query = session.createQuery(hql , String.class);
//            return query.list();
//        }
//    }


    }


