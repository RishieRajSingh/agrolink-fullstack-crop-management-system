package com.examly.springapp.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.examly.springapp.model.User;

@Repository
public interface UserRepo extends JpaRepository<User,Integer> {
    @Query("select u from User u where u.email = :email")
    User getByEmail(@Param("email")String email);

    boolean existsByUserId(int userId);

    User findByUserId(int userId);
}
