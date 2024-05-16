package com.dm;

import com.dm.entity.User;
import com.dm.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class HibernateRunner {

    public static void main(String[] args) {
        User user = User.builder()
                .username("ivan9@gmail.com")
                .firstname("Ivan")
                .lastname("Ivanov")
                .build();
        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory()) {
            try (Session session1 = sessionFactory.openSession()) {
                session1.beginTransaction();

                session1.saveOrUpdate(user);

                session1.getTransaction().commit();
            }

            try (Session session2 = sessionFactory.openSession()) {
                session2.beginTransaction();

                user.setFirstname("Sveta");
//                session2.refresh(user); // обновляем user данными из БД
                session2.merge(user); // сетаем данные в базу данными из user (противоположно REFRESH

                session2.getTransaction().commit();
            }
        }
    }
}
