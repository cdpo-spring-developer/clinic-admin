package com.springlessons.clinicadmin.examples.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.List;

@Getter
@Setter
//@Entity
@Table(name = "animal")
public class Animal extends AutoIncrementIdentity{
    @Column(length = 50, nullable = false)
    private String name;
    @Column(length = 20, nullable = false)
    private String color;

    @JdbcTypeCode(SqlTypes.ARRAY) // необходимо явно указать на тип в SQL
    @Column(name = "habits", columnDefinition = "text[]") // массив строк на уровне БД
    private List<String> habits; // список привычек животного


    @Enumerated(EnumType.STRING) // enum будет хранится строкой
    @Column(name = "type", nullable = false)
    private AnimalType animalType;

    // Животное может быть зарегистрировано только в одном приюте
    // В одном приюте можно зерегистрировать несколько животных
    // На уровне БД будет создана связь - Приют - Животное,
    // в таблице animal будет создан столбец shelter_id -
    // внешний ключ для хранения идентификатора (@Id) Приюта
    // Связь является однонаправленной, так как НЕ описана со стороны
    // класса Shelter как @OneToMany private List<Animal> animals;
    @ManyToOne
    // аннотация для описания столбца - внешнего ключа
    @JoinColumn(name = "shelter_id", nullable = false)
    private AnimalShelter animalShelter;

    // Если вместо ссылки на объект в коде нужен только его идентификатор
    // в аннотации ManyToOne необходимо указать targetEntity
    // с сылкой на связанный класс
    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    // аннотация для описания столбца - внешнего ключа
    @JoinColumn(name = "owner_id", nullable = false)
    private int ownerId;

    public enum AnimalType {
        DOG, CAT
    }
}
