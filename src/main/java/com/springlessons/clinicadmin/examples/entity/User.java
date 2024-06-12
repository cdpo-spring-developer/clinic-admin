package com.springlessons.clinicadmin.examples.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
// @Entity // Обязательная аннотация для того, чтобы с классом была ассоциирована таблица в БД
@Table(name = "app_user")
public class User extends AutoIncrementIdentity {
    @Column(name = "login", nullable = false, unique = true)
    private String username;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column(name = "email", nullable = false)
    private String email;

    // Пользователь может быть зарегистрирован в нескольких приютах и
    // в одном приюте может быть зарегистрировано несколько пользователей.
    // Связь многие ко многим всегда выражается отдельной таблицей на 2 столбца,
    // где будут хранится идентификаторы пользователей и приютов.

    // При необходимости хранить более подробную информацию (не только связь из 2х идентификаторов)
    // вместо связи ManyToMany необходимо создавать класс ShelterUser, перечилить в нем все необходимы поля,
    // и использовать аннотации OneToMany / ManyToOne для связью с классами User и AnimalShelter

    // Связь является двунаправленной, так как описана со стороны
    // класса User и со стороны класса AnimalShelter.
    // Связь не обязана быть двунапрвленной, достаточно описание связь в одном классе,
    // необходимоть определяется логикой приложения
    @ManyToMany
    // Позволяет описать таблицу связей. Таблица описывается только с одной стороны
    @JoinTable(name = "user_shelter", // название создаваемой таблицы
            joinColumns = @JoinColumn(name = "user_id"),  // столбец (внешний ключ) для хранения идентификаторов пользователей
            inverseJoinColumns = @JoinColumn(name = "shelter_id") // столбец (внешний ключ) для хранения идентификаторов приютов
    )
    private List<AnimalShelter> shelters = new ArrayList<>(); // Список должен быть инициализирован


    // Принадлежность животных пользователю. У животного может быть один владелец,
    // у каждого владельца может быть несколько животных
    // Связь двунаправленная, так так выражена аннотацией в обоих классах: Animal -> @ManyToOne и User -> @OneToMany
    // Связь не обязана быть двунапрвленной, достаточно описание связи аннотацией ManyToOne,
    // необходимоть определяется логикой приложения.
    // На уровне БД выражается столбцом owner_id в таблице animal (класс Animal),
    // данные, которые попадут в список храняться в таблице animal
    @OneToMany(mappedBy = "ownerId") // связь с полем ownerId в классе Animal для правильного формирования списка
    private List<Animal> animals = new ArrayList<>();
}
