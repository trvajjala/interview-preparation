package com.tvajjala.main;

import org.hibernate.SessionFactory;
import org.hibernate.StatelessSession;
import org.hibernate.Transaction;

import com.tvajjala.domain.User;
import com.tvajjala.util.JavaHibernateUtil;

/**
 * stateless session can be a good fit in certain situations, for example where we are loading bulk data into database and we don’t want hibernate session to
 * hold huge data in first-level cache memory.
 *
 * @author ThirupathiReddy V
 *
 */
public class StatelessSessionExample {

    public static void main(String[] args) {

        final SessionFactory sessionFactory = JavaHibernateUtil.getSessionFactory();
        final StatelessSession session = sessionFactory.openStatelessSession();
        final Transaction transaction = session.getTransaction();
        transaction.begin();
        final User user = new User();
        user.setUsername("Stateless Session creation");
        session.insert(user);
        transaction.commit();
        // session.close();
        sessionFactory.close();
    }
}
