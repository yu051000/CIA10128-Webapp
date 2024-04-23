package com.ren.filter;

import com.ren.util.HibernateUtil;
import org.hibernate.SessionFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter("/*")
public class OpenSessionInViewFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        try {
            System.out.println("filter open transaction");
            factory.getCurrentSession().beginTransaction();
            chain.doFilter(request, response);
            factory.getCurrentSession().getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            chain.doFilter(request, response);
        }
    }
}
