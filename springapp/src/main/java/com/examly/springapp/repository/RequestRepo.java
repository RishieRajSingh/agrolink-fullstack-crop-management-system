package com.examly.springapp.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.examly.springapp.model.Request;

@Repository
public interface RequestRepo extends JpaRepository<Request,Long> {

    @Query(value="select * from request where user_id =?1",nativeQuery = true)
    List<Request> findAllByUserId(long userId);

    @Query(value="select * from crop where user_id = ?1 ", nativeQuery = true)
    boolean existsByCropUserUserId(long userId);

}
