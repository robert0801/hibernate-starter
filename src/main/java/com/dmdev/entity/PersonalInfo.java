package com.dmdev.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Embeddable // указываем что это ВСТРАЕМЫЙ компонент
public class PersonalInfo {

    private String firstname;
    private String lastname;
    //    @Convert(converter = BirthdayConvertor.class) // явно указываем класс-конвертер
    @Column(name = "birth_date")
    private Birthday birthDate;
}
