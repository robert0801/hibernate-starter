package com.dmdev.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "users", schema = "public")
public class User {

    @Id
    private String username;

    @Embedded // опционально. Указыаем, что объект встраиваемый
    private PersonalInfo personalInfo;
    @Enumerated(EnumType.STRING) // эта аннотация говорит, что этот enum будет храниться в таблицк как строка, а не как число (не 1, 2 и т.п.)
    private Role role;
    @JdbcTypeCode(SqlTypes.JSON) // сохраняем поле в базу в JSON формате
    private String info;
}
