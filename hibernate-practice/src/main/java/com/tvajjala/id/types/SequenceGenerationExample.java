package com.tvajjala.id.types;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.tvajjala.domain.SequenceGen;
import com.tvajjala.util.JavaHibernateUtil;

public class SequenceGenerationExample {

    public static void main(String[] args) {
        final SessionFactory sessionFactory = JavaHibernateUtil.getSessionFactory();
        final Session session = sessionFactory.openSession();
        final Transaction tx = session.getTransaction();
        tx.begin();
        for (int i = 0; i < 10; i++) {
            session.persist(new SequenceGen());
        }

        tx.commit();

        sessionFactory.close();

    }

}
