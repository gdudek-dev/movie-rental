package com.gdudek.movieRental.repository;

import com.gdudek.movieRental.enums.RoleType;
import com.gdudek.movieRental.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {

    Role findByRoleType(RoleType roleType);
}
