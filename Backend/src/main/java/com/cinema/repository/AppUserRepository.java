package com.cinema.repository;

import com.cinema.entity.AppRole;
import com.cinema.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Integer> {

    AppUser findByLogin(String login);

    @Query("select distinct r from AppRole r join AppUser u on u.userRole.roleId = r.roleId")
    List<AppRole> findAllRole();

}
