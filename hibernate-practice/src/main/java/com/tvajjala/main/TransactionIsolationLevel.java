package com.tvajjala.main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Environment;

import com.tvajjala.util.JavaHibernateUtil;

public class TransactionIsolationLevel {

    public static void main(String[] args) {

        final SessionFactory sessionFactory = JavaHibernateUtil.getSessionFactory();

        final Session session = sessionFactory.openSession();

        session.doWork(connection -> {
            System.out.println(Environment.isolationLevelToString(connection.getTransactionIsolation()));
        });

        sessionFactory.close();

    }
}
