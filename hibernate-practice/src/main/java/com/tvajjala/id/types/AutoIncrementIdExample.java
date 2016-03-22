package com.tvajjala.id.types;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.tvajjala.domain.AutoIncrement;
import com.tvajjala.util.JavaHibernateUtil;

public class AutoIncrementIdExample {

    public static void main(String[] args) {
        final SessionFactory sessionFactory = JavaHibernateUtil.getSessionFactory();
        final Session session = sessionFactory.openSession();

        final Transaction tx = session.getTransaction();
        tx.begin();
        session.persist(new AutoIncrement());
        tx.commit();

        sessionFactory.close();

    }

}
