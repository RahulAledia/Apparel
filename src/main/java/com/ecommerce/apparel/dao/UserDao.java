package com.ecommerce.apparel.dao;

import com.ecommerce.apparel.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Repository
public interface UserDao extends JpaRepository<User,Integer> {

    @Query("SELECT u FROM User u WHERE u.email = :email")
    User findByEmail(String email);

    @Query("SELECT u.email FROM User u")
    List<String> returnAllUsername();
}
