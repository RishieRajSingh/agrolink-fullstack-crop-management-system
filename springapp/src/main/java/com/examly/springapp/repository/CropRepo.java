package com.examly.springapp.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.examly.springapp.model.Crop;

/**
 * Repository interface for Crop entity.
 * Extends JpaRepository to provide CRUD operations for Crop entity.
 */
@Repository
public interface CropRepo extends JpaRepository<Crop, Long> {

    @Query("SELECT c FROM Crop c WHERE c.user.userId = :userId")
    List<Crop> getByUserId(int userId);

    @Query("SELECT c FROM Crop c WHERE c.user.userId = :userId AND c.cropId = :cropId")
    Crop getByUserAndCropId(int userId, long cropId);

    boolean existsByCropName(String cropName);
    boolean existsByUser_UserId(int userId);

}

