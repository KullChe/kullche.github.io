package com.attech.repository;

import com.attech.model.entity.Avatar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AvatarRepository extends JpaRepository<Avatar,Long> {

    Optional<Avatar> findByUserID(Long id);

    boolean existsByAvaName(String name);
}
