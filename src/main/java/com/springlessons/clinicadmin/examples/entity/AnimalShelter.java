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

// Хранение информации о приюте для животных

// Для генерации геттеров и сеттеров
@Setter
@Getter
// Обязательная аннотация для того, чтобы с классом была ассоциирована таблица в БД
@Entity
// Необязательная аннотация, позволяющая указать имя таблицы, описать индексы
@Table(name = "shelter")
public class AnimalShelter extends AutoIncrementIdentity {
    // со всеми не transient полями класса будут ассоциированы столбцы в таблице

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
            columnDefinition = "BOOLEAN NOT NULL DEFAULT TRUE", // описание столбца sql синтаксисом (нвсегда начинается с типа данных).
            // Можно указать тип данных, дефолтное знавение, check условия и т.п
            insertable = false) // столбец не будет фигурировать в формируемых spring data INSERT запросах
    private boolean isActive;

    @CreatedDate // заполняется датой создания сущности
    @Column(name = "created_at", // название столбца
            updatable = false) // столбец не будет фигурировать в формируемых spring data UPDATE запросах
    private LocalDateTime createdAt;


    @UpdateTimestamp// обновляется при обновлении сущности
    @Column(name = "updated_at",// название столбца
            insertable = false) // столбец не будет фигурировать в формируемых spring data INSERT запросах
    private LocalDateTime updatedAt;

    // Пользователь может быть зарегистрирован в нескольких приютах и
    // в одном приюте может быть зарегистрировано несколько пользователей.
    // Связь многие ко многим всегда выражается отдельной таблицей на 2 столбца,
    // где будут хранится идентификаторы пользователей и приютов.
    // Таблица описывается только с одной стороны
    // Связь является двунаправленной, так как описана со стороны
    // класса User и со стороны класса AnimalShelter.
    // Связь не обязана быть двунапрвленной, достаточно описание связь в одном классе,
    // необходимоть определяется логикой приложения
    @ManyToMany(mappedBy = "shelters") // связь с полем shelters в классе User
    private List<User> users = new ArrayList<>(); // Список должен быть инициализирован

}
