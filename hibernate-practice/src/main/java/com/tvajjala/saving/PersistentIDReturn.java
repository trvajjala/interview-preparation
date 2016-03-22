package com.tvajjala.saving;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tvajjala.domain.IdentityBean;
import com.tvajjala.util.JavaHibernateUtil;

public class PersistentIDReturn {

    public static void main(String[] args) {

        final SessionFactory sessionFactory = JavaHibernateUtil.getSessionFactory();
        final Session session = sessionFactory.openSession();

        final IdentityBean transitentBean = new IdentityBean("SomeUser");
        session.beginTransaction();

        session.persist(transitentBean);

        System.out.println("ID After persistent state :  " + transitentBean.getId());

        session.getTransaction().commit();
        session.close();
        sessionFactory.close();

    }
}
