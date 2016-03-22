package com.tvajjala.collection;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tvajjala.domain.CAddress;
import com.tvajjala.domain.CCustomer;
import com.tvajjala.util.JavaHibernateUtil;

public class CollectionElementWIthEmbedded {

    public static void main(String[] args) {

        final SessionFactory sessionFactory = JavaHibernateUtil.getSessionFactory();
        final Session session = sessionFactory.openSession();

        session.beginTransaction();
        CCustomer cCustomer = new CCustomer();

        cCustomer.setName("AREYOU ELIGILE FOR WHAT U POSSESEDf");
        final CAddress address = new CAddress();

        address.setCity("CityOne");
        address.setState("StateOne");
        cCustomer.getAddressList().add(address);

        final CAddress address2 = new CAddress();

        address2.setCity("CityOne");
        address2.setState("StateOne");
        cCustomer.getAddressList().add(address2);

        session.save(cCustomer);

        session.getTransaction().commit();

        session.clear();
        cCustomer = (CCustomer) session.get(CCustomer.class, cCustomer.getId());

        System.out.println(" ----- ");
        for (final CAddress add : cCustomer.getAddressList()) {
            System.out.println(add.getCity());
        }
        // sessionFactory.close();

    }

}
