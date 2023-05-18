package com.attech.repository;

import com.attech.model.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Roles, Long> {
    Roles findByName(String name);

    boolean existsByName(String name);

    @Query(value = "select * from roles r where upper(r.role_name) like upper(concat('%',:keySearch,'%'))"
            ,nativeQuery = true)
    List<Roles> findByKeySearch(@Param(value = "keySearch") String keySearch);

}
