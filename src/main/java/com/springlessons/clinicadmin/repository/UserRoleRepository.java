package com.springlessons.clinicadmin.repository;


import com.springlessons.clinicadmin.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {
    Optional<UserRole> findByRoleType(UserRole.RoleType roleType);
}
