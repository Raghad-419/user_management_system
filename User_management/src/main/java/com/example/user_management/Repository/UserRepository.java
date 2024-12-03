package com.example.user_management.Repository;

import com.example.user_management.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    User findUserById(Integer id);

    User findUserByUsernameAndPassword(String username ,String password);

    @Query("select u from User u where u.username=?1 AND u.password=?2")
    User giveUserByUsernameAndPassword(String username ,String password);

    @Query("select u from User u where u.email=?1")
    User giveUserByEmail(String email);

    List<User> findUserByRole(String role);

    List<User> findUserByAgeGreaterThanEqual(Integer age);

    @Query("select u from User u where u.age>=?1")
    List<User> giveAgeOrAbove(Integer age);

}
