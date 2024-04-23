package com.yu.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {

    private static StandardServiceRegistry registry;
    //呼叫createSessionFactory()可回傳SessionFactory物件
    private static final SessionFactory sessionFactory = createSessionFactory();


    //使用getter static方法，之後可藉由類別名稱呼叫取得唯一的SessionFactory
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    //宣告私有的static方法，主要用來初始化並回傳SessionFactory
    private static SessionFactory createSessionFactory() {
        try {
            registry = new StandardServiceRegistryBuilder()
                    .configure()
                    .build();

            SessionFactory sessionFactory = new MetadataSources(registry)
                    .buildMetadata()
                    .buildSessionFactory();

            return sessionFactory;

        } catch (Exception e) {
            e.printStackTrace();
            throw new ExceptionInInitializerError(e);
        }

    }

    public static void shutdown() {
        if (registry != null)
            StandardServiceRegistryBuilder.destroy(registry);
    }

}

