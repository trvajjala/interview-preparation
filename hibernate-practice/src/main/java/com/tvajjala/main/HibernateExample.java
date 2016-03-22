package com.tvajjala.main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.tvajjala.domain.User;
import com.tvajjala.util.JavaHibernateUtil;

public class HibernateExample {

    public static void main(String[] args) {

        final SessionFactory sessionFactory = JavaHibernateUtil.getSessionFactory();
        final Session session = sessionFactory.openSession();
        final Transaction transaction = session.getTransaction();
        transaction.begin();
        final User user = new User();
        user.setUsername("Myname is");
        session.persist(user);
        transaction.commit();
        session.close();
        sessionFactory.close();
    }
}
