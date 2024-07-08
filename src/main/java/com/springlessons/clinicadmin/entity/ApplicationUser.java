package com.springlessons.clinicadmin.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.Set;

// class User{}
// org.springframework.security.core.userdetails.User

@Entity
@Table(name = "application_user")
public class ApplicationUser {

    @Id
    @GeneratedValue
    private long id;

    @NotNull
    @NotEmpty
    private String email;// login | email | phone

    @NotNull
    @NotEmpty
    private String password;

    @ManyToMany
    @JoinColumn(name = "role_id")
    private Set<UserRole> userRoles;

    public ApplicationUser() {
    }

    public ApplicationUser(long id, String email, String password, Set<UserRole> userRoles) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.userRoles = userRoles;
    }

    // другие свойства

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return email;
    }

    public void setUsername(String username) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<UserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Set<UserRole> userRoles) {
        this.userRoles = userRoles;
    }

    public void setUserRoles(UserRole userRole) {
    }
}
