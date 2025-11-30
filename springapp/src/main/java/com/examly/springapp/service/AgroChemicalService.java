package com.examly.springapp.service;
import java.util.List;
import com.examly.springapp.exception.AgroChemicalNotFoundException;
import com.examly.springapp.exception.DuplicateAgroChemicalException;
import com.examly.springapp.model.AgroChemical;

public interface AgroChemicalService {

    /**
     * Adds a new agroChemical to the database.
     *
     * @param agroChemical the `AgroChemical` entity to be added.
     * @return the saved `agroChemical` entity.
     */
    public AgroChemical addAgroChemical(AgroChemical agroChemical) throws DuplicateAgroChemicalException;

    /**
     * Retrieves a list of agroChemicals.
     *
     * @return a list of sold `AgroChemical` entities.
     */
    public List<AgroChemical> getAllAgroChemical();

    /**
     * Retrieves a agroChemical by ID.
     *
     * @param agroChemicalId the ID of the agrochemical to retrieve.
     * @return the `AgroChemical` entity if found, or `null` otherwise.
     */
    public AgroChemical getAgroChemicalById(Long agroChemicalId) throws AgroChemicalNotFoundException;

    /**
     * Updates an existing agroChemical by ID.
     *
     * @param agroChemicalId the ID of the agroChemical to update.
     * @param agroChemical   the updated `AgroChemical` entity details.
     * @return the updated `AgroChemical` entity if found, or null.
     */
    public AgroChemical updateAgroChemical(Long agroChemicalId, AgroChemical agroChemical) throws AgroChemicalNotFoundException;

    /**
     * Deletes a agroChemical by ID.
     *
     * @param agroChemicalId the ID of the agroChemical to delete.
     * @return the deleted `AgroChemical` entity if found, or `null` otherwise.
     */
    public AgroChemical deleteAgroChemical(Long agroChemicalId) throws AgroChemicalNotFoundException;

}
