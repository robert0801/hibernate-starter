package com.dmdev;

import com.dmdev.entity.Birthday;
import com.dmdev.entity.PersonalInfo;
import com.dmdev.entity.User;
import com.dmdev.util.HibernateUtil;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.time.LocalDate;

@Slf4j
public class HibernateRunner {

    public static void main(String[] args) {
        User user = User.builder()
                .username("petr3@gmail.com")
                .personalInfo(PersonalInfo.builder()
                        .firstname("Petr")
                        .lastname("Petrov")
                        .birthDate(new Birthday(LocalDate.of(2000, 1, 2))).build())
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
            try (Session session2 = sessionFactory.openSession()) {
                PersonalInfo key = PersonalInfo.builder()
                        .firstname("Petr")
                        .lastname("Petrov")
                        .birthDate(new Birthday(LocalDate.of(2000, 1, 2))).build();
                User user2 = session2.get(User.class, key);
                System.out.println();
            }
        } catch (Exception e) {
            log.error("Exception occurred", e);
            throw e;
        }
    }
}
