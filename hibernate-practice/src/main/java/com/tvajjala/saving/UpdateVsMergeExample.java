package com.tvajjala.saving;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tvajjala.domain.IdentityBean;
import com.tvajjala.util.JavaHibernateUtil;

public class UpdateVsMergeExample {

    public static void main(String[] args) {

        final SessionFactory sessionFactory = JavaHibernateUtil.getSessionFactory();

        final Session session = sessionFactory.openSession();
        final Long id = (Long) session.save(new IdentityBean("NEWOBJECT"));

        final IdentityBean identityBean = (IdentityBean) session.get(IdentityBean.class, id);
        session.beginTransaction();
        // identityBean = new IdentityBean("sss");
        // identityBean.setId(id);
        identityBean.setUsername("UpdatingUsername");
        session.update(identityBean);
        session.getTransaction().commit();
        session.close();
        sessionFactory.close();
    }
}
