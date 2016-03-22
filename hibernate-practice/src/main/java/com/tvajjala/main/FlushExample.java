package com.tvajjala.main;

import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.tvajjala.domain.User;
import com.tvajjala.util.JavaHibernateUtil;

public class FlushExample {

    public static void main(String[] args) {

        final SessionFactory sessionFactory = JavaHibernateUtil.getSessionFactory();
        final Session session = sessionFactory.openSession();
        session.setFlushMode(FlushMode.MANUAL);
        final Transaction transaction = session.beginTransaction();
        session.persist(new User("ManualFlush"));
        // session.flush();// enable this to see record in db
        transaction.commit();
        session.close();
        sessionFactory.close();
    }
}
