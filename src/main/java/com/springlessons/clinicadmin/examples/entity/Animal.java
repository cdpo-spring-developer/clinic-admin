package com.springlessons.clinicadmin.examples.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.springframework.data.jpa.repository.EntityGraph;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity // Обязательная аннотация для того, чтобы с классом была ассоциирована таблица в БД
@Table(name = "animal")
public class Animal /*extends AutoIncrementIdentity*/ {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;
    @NotNull
    @Size(min = 2, max = 50)
    @Column(length = 50, nullable = false)
    private String name;
    @NotNull
    @Size(min = 3, max = 20)
    @Column(length = 20, nullable = false)
    private String color;


    @JdbcTypeCode(SqlTypes.ARRAY) // необходимо явно указать на тип в SQL
    @Column(name = "habits", columnDefinition = "text[]") // массив строк на уровне БД
    private List<@NotNull @NotEmpty @NotEmpty String> habits = new ArrayList<>(); // список привычек животного


    @NotNull
    @Enumerated(EnumType.STRING) // enum будет хранится строкой
    @Column(name = "type", nullable = false)
    private AnimalType animalType;

    // Животное может быть зарегистрировано только в одном приюте,
    // при этом в одном приюте можно зерегистрировать несколько животных.
    // Связь однонаправленная, так так выражена только аннотацией @ManyToOne в классе Animal.
    // В таблице animal будет создан столбец shelter_id - внешний ключ для хранения идентификатора (@Id) Приюта.
    @ManyToOne
    @JoinColumn(name = "shelter_id", nullable = false) // аннотация для описания столбца - внешнего ключа
    private AnimalShelter animalShelter;

    // Владелец животного. У животного может быть один владелец,
    // у каждого владельца может быть несколько животных
    // Связь двунаправленная, так так выражена аннотацией в обоих классах: Animal -> @ManyToOne и User -> @OneToMany
    // Связь не обязана быть двунапрвленной, достаточно описание связи аннотацией ManyToOne,
    // необходимоть определяется логикой приложения.
    // На уровне БД выражается столбцом owner_id в таблице animal (класс Animal) - внешний ключ для хранения идентификатора (@Id) Пользователя
    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id") // аннотация для описания столбца - внешнего ключа
    private User ownerId;

    public enum AnimalType {
        DOG, CAT
    }
}
