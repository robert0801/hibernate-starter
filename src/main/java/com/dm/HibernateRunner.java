package com.dm;

import com.dm.converter.BirthdayConvertor;
import com.dm.entity.Birthday;
import com.dm.entity.Role;
import com.dm.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.time.LocalDate;

public class HibernateRunner {

    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        // нужно добавлять, чтобы Hibernate зарегистрировать сущность User.class
        configuration.addAnnotatedClass(User.class);
        configuration.addAttributeConverter(new BirthdayConvertor(), true); // указываем конвернет на уровне конфигурации
        configuration.configure();

        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            User user = User.builder()
                    .username("ivan1@gmail.com")
                    .firstname("Ivan")
                    .lastname("Ivanov")
                    .birthDate(new Birthday(LocalDate.of(2000, 1, 19)))
                    .role(Role.ADMIN)
                    .info("""
                            { "name" : "Ivan",
                            "id" : 25 }
                            """)
                    .build();
            session.persist(user);

            session.getTransaction().commit();
            System.out.println();
        }

    }
}
