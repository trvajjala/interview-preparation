package com.tvajjala.saving;

import java.io.Serializable;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.tvajjala.domain.IdentityBean;
import com.tvajjala.util.JavaHibernateUtil;

public class SaveVsPersisteExample {

    public static void main(String[] args) {
        final SessionFactory sessionFactory = JavaHibernateUtil.getSessionFactory();

        final Session session = sessionFactory.openSession();
        final Transaction transaction = session.beginTransaction();
        final IdentityBean transientBean = new IdentityBean("SavinUser33");
        final Serializable id = session.save(transientBean);
        transientBean.setUsername("PersistentBean...");
        System.out.println(id);

        final IdentityBean identityBean = (IdentityBean) session.get(IdentityBean.class, id);
        transaction.commit();
        session.close();

        final Session session2 = sessionFactory.openSession();
        session2.beginTransaction();

        identityBean.setUsername("ChangingDetachedAttribute33");
        identityBean.setId(333l);// though we add new id it still says detached?
        session2.persist(identityBean);// change this method to save and see the DB . it creates new record for Generated Ids
        session2.getTransaction().commit();
        session2.close();
        sessionFactory.close();
    }
}
