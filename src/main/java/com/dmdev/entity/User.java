package com.dmdev.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(of = "username")
@ToString(exclude = {"company", "profile", "usersChats"})
@Builder
@Entity
@Table(name = "users", schema = "public")
public class User implements Comparable<User> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private PersonalInfo personalInfo;

    @Column(unique = true)
    private String username;

    @Enumerated(EnumType.STRING) // эта аннотация говорит, что этот enum будет храниться в таблицк как строка, а не как число (не 1, 2 и т.п.)
    private Role role;

//    @JdbcTypeCode(SqlTypes.JSON) // сохраняем поле в базу в JSON формате
//    private String info;

    @ManyToOne(fetch = FetchType.LAZY) // FetchType.LAZY позволяет получать элементы не сразу, а только по запросу
    @JoinColumn(name = "company_id") // Название колонки в таблице User, которая используется для мапинга
    private Company company;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    private Profile profile;

    @Builder.Default
    @OneToMany(mappedBy = "user")
    private List<UsersChat> usersChats = new ArrayList<>();

    @Override
    public int compareTo(User o) {
        return username.compareTo(o.username);
    }
}
