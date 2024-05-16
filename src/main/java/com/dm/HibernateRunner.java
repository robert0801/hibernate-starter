package com.dm;

import com.dm.entity.User;
import com.dm.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HibernateRunner {

    private static final Logger log = LoggerFactory.getLogger(HibernateRunner.class);

    public static void main(String[] args) {
        User user = User.builder()
                .username("ivan9@gmail.com")
                .firstname("Ivan")
                .lastname("Ivanov")
                .build();
        log.info("User entity is in transient state, object: {}", user );

        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory()) {
            Session session1 = sessionFactory.openSession();
            try (session1) {
                Transaction transaction = session1.beginTransaction();
                log.trace("Transaction is created, {}", transaction);

                session1.saveOrUpdate(user);

                session1.getTransaction().commit();
                log.trace("User entity is in persistence state: {}, session : {}", user, session1);
            }
            log.warn("User is in detached state: {}, session is close {}", user, session1);
        } catch (Exception e) {
            log.error("Exception occurred", e);
            throw e;
        }
    }
}
