package com.pietrakpasek.medicinedata.repositories;

import com.pietrakpasek.medicinedata.model.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findRoleByName(String name);
}
