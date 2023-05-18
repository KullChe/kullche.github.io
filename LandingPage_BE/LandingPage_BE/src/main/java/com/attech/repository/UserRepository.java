package com.attech.repository;

import com.attech.model.dto.user.ResponseUser;
import com.attech.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

    @Query(value = "select * from `user` u", nativeQuery = true)
    List<User> findAll();

    boolean existsByUserNameOrEmail(String userName, String email);

    boolean existsByUserNameAndIdNot(String userName, Long id);

    boolean existsByEmailAndIdNot(String email, Long id);

    boolean existsByPhoneAndIdNot(String phone, Long id);

    User findByUserName(String userName);
}