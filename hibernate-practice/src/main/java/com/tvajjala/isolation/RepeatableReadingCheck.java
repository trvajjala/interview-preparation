package com.tvajjala.isolation;

import java.sql.Connection;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.tvajjala.domain.User;

/**
 * within the transaction if you read multiple time it should return same row of data. <br>
 * READ_COMMITTED ALSO GIVING same row data (with MySQL and HIBERNATE driver managed sessionFactory)
 *
 * @author ThirupathiReddy V
 *
 */
public class RepeatableReadingCheck {

    public static void main(String[] args) {

        final SessionFactory sessionFactory = IsolationLevelTestSessionFactory.getSessionFactory(Connection.TRANSACTION_REPEATABLE_READ);

        final User user = insertRecord(sessionFactory, new User("FirstTxCommittedData"));

        final Session session = sessionFactory.openSession();
        final Transaction tx = session.beginTransaction();

        List<?> listOfUsers = session.createCriteria(User.class).list();
        for (final Object obj : listOfUsers) {
            System.out.println(">>>Firsttime reading  " + obj);
        }

        user.setUsername("UpdatedValueWithAnotherTransaction");
        updateRecord(sessionFactory, user);
        user.setUsername("UpdatedValueWithAnotherTransactionSecondTime");
        updateRecord(sessionFactory, user);

        // same transaction reading the second time
        listOfUsers = session.createCriteria(User.class).list();
        for (final Object obj : listOfUsers) {
            System.out.println(">>>Secondtime reading  " + obj);
        }

        tx.commit();
        sessionFactory.close();
    }

    static User insertRecord(SessionFactory sessionFactory, User data) {

        final Session session1 = sessionFactory.openSession();
        final Transaction tx1 = session1.getTransaction();
        tx1.begin();
        final User user = (User) session1.merge(data);
        session1.flush();
        tx1.commit();
        return user;
    }

    static User updateRecord(SessionFactory sessionFactory, User data) {

        final Session session1 = sessionFactory.openSession();
        final Transaction tx1 = session1.getTransaction();
        tx1.begin();
        final User user = (User) session1.merge(data);
        session1.flush();
        tx1.commit();

        return user;
    }

}
