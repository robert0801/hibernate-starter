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

    @EmbeddedId // аннотация, которая указыает, что Embedded элемент будет первичным ключом
    private PersonalInfo personalInfo;

    @Column(unique = true)
    private String username;

    @Enumerated(EnumType.STRING) // эта аннотация говорит, что этот enum будет храниться в таблицк как строка, а не как число (не 1, 2 и т.п.)
    private Role role;

    @JdbcTypeCode(SqlTypes.JSON) // сохраняем поле в базу в JSON формате
    private String info;
}
