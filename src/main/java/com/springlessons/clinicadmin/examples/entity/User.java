package com.springlessons.clinicadmin.examples.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
//@Entity
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

    // Пользователь может быть зарегистрирован в нескольких приютах.
    // В одном приюте может быть зарегистрировано несколько пользователей.
    // Связь многие ко многим всегда выражается отдельной таблицей на 2 столбца,
    // где будут хранится идентификаторы пользователей и приютов.
    // При необходимости хранить более подробную информацию вместо связи ManyToMany
    // необходимо создавать класс ShelterUser и использовать аннотации OneToMany / ManyToOne
    // Связь является двунаправленной, так как описана со стороны
    // класса Shelter как @ManyToMany private List<User> users;
    // Список должен быть инициализирован
    @ManyToMany
    // Позволяет описать таблицу связей
    @JoinTable(name = "user_shelter", // название таблицы
            joinColumns = @JoinColumn(name = "user_id"),  // столбец для хранения идентификаторов пользователей
            inverseJoinColumns = @JoinColumn(name = "shelter_id") // столбец для хранения идентификаторов приютов
    )
    private List<AnimalShelter> shelters = new ArrayList<>();


    // Принадлежность животных пользователю
    // Связь двунаправленная, так так выражена аннотацией ManyToOne в классе Animal
    // На уровне БД выражается только столбцом owner_id в таблице animal (класс Animal)
    @OneToMany(mappedBy = "ownerId") // связь с полем ownerId в классе Animal
    private List<Animal> animals = new ArrayList<>();
}
