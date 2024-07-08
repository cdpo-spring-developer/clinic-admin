package com.springlessons.clinicadmin.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "user_role")
public class UserRole {
    @Id
    @GeneratedValue
    private int id;

    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public RoleType getRoleType() {
        return roleType;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }

    public enum RoleType {
        // Контент менеджер может регистрировать врачей и добавлять специализации.
        ROLE_CONT_MANAGER,
        // Контент менеджер может регистрировать врачей и добавлять специализации.
        // может просматривать отзывы и отмечать их неактивными
        ROLE_MODERATOR // может создавать задачи,
    }
}
/*
Задача на вторник:
Контент менеджер может регистрировать врачей и добавлять специализации.
Контент менеджер с ролью модератора дополнительно
может просматривать отзывы и отмечать их неактивными.

Реализовать механизм регистрации контент менеджера через форму.
Реализовать механизм аутентификации контент менеджера через форму.

Данные для регистрации и аутентификации: email и пароль.*/
