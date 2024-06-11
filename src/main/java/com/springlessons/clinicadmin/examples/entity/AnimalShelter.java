package com.springlessons.clinicadmin.examples.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import lombok.Getter;
import lombok.Setter;


// для генерации геттеров и сеттеров
@Setter
@Getter
// Обязательная аннотация.
// С классом будет ассоциирована таблица в БД
//@Entity
// Необязательная аннотация.
// Позволяет указать имя таблицы, описать индексы
@Table(name = "shelter")
public class AnimalShelter extends AutoIncrementIdentity {
    // с не transient полями класса будут ассоциированы столбцы в таблице

    // аннотация позволяет описывать столбцы в таблице
    @Column(name = "name",// название столбца
            length = 50, // длина строки для строковых типов
            nullable = false) // значение по столбцу не м.б. null
    private String name;

    @Column(name = "code_en",// название столбца
            length = 50, // длина строки для строковых типов
            nullable = false) // значение по столбцу не м.б. null
    private String code;

    @Column(name = "is_active",// название столбца
            columnDefinition = "BOOLEAN NOT NULL DEFAULT TRUE", // описание столбца на синтаксисе sql (нвсегда начинается с типа данных).
            // Можно указать тип данных, дефолтное знавение, check условия и т.п
            insertable = false) // столбец не будет фигурировать в формируемых spring data INSERT запросах
    private boolean isActive;

    @CreatedDate // заполняется даной создания сущности
    @Column(name = "created_at",// название столбца
            updatable = false) // столбец не будет фигурировать в формируемых spring data UPDATE запросах
    private LocalDateTime createdAt;


    @UpdateTimestamp// обновляется при обновлении сущности
    @Column(name = "updated_at",// название столбца
            insertable = false) // столбец не будет фигурировать в формируемых spring data INSERT запросах
    private LocalDateTime updatedAt;

    // Пользователь может быть зарегистрирован в нескольких приютах.
    // В одном приюте может быть зарегистрировано несколько пользователей.
    // Связь многие ко многим всегда выражается отдельной таблицей на 2 столбца,
    // где будут хранится идентификаторы пользователей и приютов.
    // Таблица писывается только с одной стороны
    // Связь является двунаправленной, так как описана со стороны
    // класса Shelter как @ManyToMany private List<AnimalShelter> shelters;
    // На уровне БД выражена только стольцом в таблице app_user (класс User)
    // Список должен быть инициализирован
    @ManyToMany(mappedBy = "shelters") // связь с полем shelters в классе User
    private List<User> users = new ArrayList<>();

}
