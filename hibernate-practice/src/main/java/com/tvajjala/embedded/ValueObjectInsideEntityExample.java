package com.tvajjala.embedded;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tvajjala.domain.VOAddress;
import com.tvajjala.domain.VOStudent;
import com.tvajjala.util.JavaHibernateUtil;

public class ValueObjectInsideEntityExample {

    public static void main(String[] args) {

        final SessionFactory sessionFactory = JavaHibernateUtil.getSessionFactory();
        //

        final VOAddress address = new VOAddress();
        address.setCity("City");
        address.setState("Telnagna");

        final VOStudent student = new VOStudent();
        student.setVoAddress(address);
        student.setName("SOMENAME");

        final Session session = sessionFactory.openSession();

        session.save(student);

        sessionFactory.close();
    }

}
