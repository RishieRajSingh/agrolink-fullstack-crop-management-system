package com.examly.springapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.examly.springapp.model.AgroChemical;

/**
 * @author Team AgroVisionaries
 * Repository interface for managing `AgroChemical` entity operations.
 * 
 * Extends `JpaRepository` to inherit basic CRUD and JPA-specific operations.
 */
@Repository
public interface AgroChemicalRepo extends JpaRepository<AgroChemical, Long> {

    /**
     * Finds agroChemical based on provided name and brand.
     *
     * @return a `AgroChemical` entity matching name and brand.
     */
    @Query(value="select * from agro_chemical where name=?1 and brand=?2", nativeQuery = true)
    Optional<AgroChemical> getAgroChemicalByNameAndBrand(String name, String brand);

    /**
     * Finds agroChemical based on provided name.
     *
     * @return a `AgroChemical` entity matching name.
     */
    @Query(value="select * from agro_chemical where name=?1", nativeQuery = true)
    Optional<AgroChemical> getAgroChemicalByName(String name);

    /**
     * Finds agroChemical based on provided brand.
     *
     * @return a `AgroChemical` entity matching brand.
     */
    @Query(value="select * from agro_chemical where brand=?1", nativeQuery = true)
    Optional<AgroChemical> getAgroChemicalByBrand(String brand);
}
