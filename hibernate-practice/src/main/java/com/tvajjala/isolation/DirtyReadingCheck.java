package com.tvajjala.isolation;

import java.sql.Connection;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.tvajjala.domain.User;

public class DirtyReadingCheck {

    public static void main(String[] args) {

        // Change Isolation Level to TRANSACTION_READ_COMMITTED to overcome dirty reading...
        final SessionFactory sessionFactory = IsolationLevelTestSessionFactory.getSessionFactory(Connection.TRANSACTION_READ_UNCOMMITTED);

        insertRecord(sessionFactory, "FirstTransactionUncommttedData");

        readRecords(sessionFactory);
        sessionFactory.close();

    }

    static void insertRecord(SessionFactory sessionFactory, String data) {

        final Session session1 = sessionFactory.openSession();
        final Transaction tx1 = session1.getTransaction();
        tx1.begin();
        session1.persist(new User(data));
        session1.flush();
        // not committing

    }

    static void readRecords(SessionFactory sessionFactory) {

        final Session session2 = sessionFactory.openSession();

        final Transaction tx1 = session2.getTransaction();
        tx1.begin();

        final List<?> listOfUsers = session2.createCriteria(User.class).list();

        if (listOfUsers.isEmpty()) {
            System.out.println("  ###############  THERE IS NO DIRTY DATA  ##################  ");
        } else {
            System.out.println("  ###############  DIRTY  DATA  ##################  ");
            for (final Object user : listOfUsers) {
                System.out.println(user);
            }
        }

    }

}
