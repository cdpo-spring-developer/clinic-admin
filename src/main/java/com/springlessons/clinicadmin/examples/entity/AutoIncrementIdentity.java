package com.springlessons.clinicadmin.examples.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

// для генерации геттеров и сеттеров
@Setter
@Getter
// класс может быть родителем для entity классов,
// но сам entity классом не является.
// Поле id будет присутсвовать во всех наследниках,
// как и столбец в соответствующих таблицах
@MappedSuperclass
public class AutoIncrementIdentity {
    // Уникальный идентификатор сущности.
    // В таблице будет присутствовать столбец id - первичный ключ
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
}
