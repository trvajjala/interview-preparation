package com.tvajjala.object.states;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tvajjala.domain.IdentityBean;
import com.tvajjala.util.JavaHibernateUtil;

public class HBMObjectStates {

    public static void main(String[] args) {

        final SessionFactory sessionFactory = JavaHibernateUtil.getSessionFactory();

        final IdentityBean identityBean = new IdentityBean("WithoutAnyTransaction?AutoCommit2 ");
        // now bean is in transient state

        final Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(identityBean);
        identityBean.setUsername("UpdatedUser");
        session.getTransaction().commit();

        identityBean.setUsername("UpdatedUser2");
        session.flush();

        session.close();
        // Now Bean is in persistent state
        // System.out.println(" id before session close " + identityBean.getId());
        // session.close();

        // System.out.println(identityBean.getId());
        // now bean is in detached state since session was closed in the above line

        // sessionFactory.close();

    }
}
