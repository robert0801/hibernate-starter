package com.dmdev.util;

import com.dmdev.converter.BirthdayConvertor;
import com.dmdev.entity.Company;
import com.dmdev.entity.User;
import lombok.experimental.UtilityClass;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@UtilityClass
public class HibernateUtil {

    public static SessionFactory buildSessionFactory() {
        Configuration configuration = new Configuration();
        // нужно добавлять, чтобы Hibernate зарегистрировать сущность User.class
        configuration.addAnnotatedClass(User.class);
        configuration.addAnnotatedClass(Company.class);
        configuration.addAttributeConverter(new BirthdayConvertor(), true); // указываем конвернет на уровне конфигурации
        configuration.configure();

        return configuration.buildSessionFactory();
    }
}
