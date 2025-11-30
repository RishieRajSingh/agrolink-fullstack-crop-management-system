package com.examly.springapp.service;
import java.util.List;
import org.springframework.stereotype.Service;
import com.examly.springapp.exception.AgroChemicalNotFoundException;
import com.examly.springapp.exception.DuplicateAgroChemicalException;
import com.examly.springapp.model.AgroChemical;
import com.examly.springapp.repository.AgroChemicalRepo;

/**
 * @author Team AgroVisionaries
 * Service implementation for managing agroChemical-related operations.
 * 
 * Annotated with `@Service` to indicate it's a Spring service class.
 * Implements the ` AgroChemicalService` interface to provide specific business logic.
 */
@Service
public class AgroChemicalServiceImpl implements AgroChemicalService {

    private AgroChemicalRepo agroChemicalRepo;
    private static final String NOT_FOUND = " not found.";

    /**
     * Repository for managing `AgroChemical` entity data access.
     * Annotated with `@Autowired` to inject the `AgroChemicalRepo` bean.
     */
    public AgroChemicalServiceImpl(AgroChemicalRepo agroChemicalRepo) {
        this.agroChemicalRepo = agroChemicalRepo;
    }


    /**
     * Adds a new agroChemical to the database.
     *
     * @param agroChemical the `AgroChemical` entity to be added.
     * @return the saved `agroChemical` entity.
     */
    @Override
    public AgroChemical addAgroChemical(AgroChemical agroChemical) throws DuplicateAgroChemicalException {
        String name = agroChemical.getName();
        String brand = agroChemical.getBrand();
        
        AgroChemical existsAgroChemicalByNameAndBrand = agroChemicalRepo.getAgroChemicalByNameAndBrand(name, brand).orElse(null);
        if (existsAgroChemicalByNameAndBrand != null) {
            throw new DuplicateAgroChemicalException("AgroChemical with name " + name + " and brand " + brand + " already exists.");
        }        
        return agroChemicalRepo.save(agroChemical);
    }
    /**
     * Retrieves a list of agroChemicals.
     *
     * @return a list of sold `AgroChemical` entities.
     */
    @Override
    public List<AgroChemical> getAllAgroChemical(){
        return agroChemicalRepo.findAll();        
    }

    /**
     * Retrieves a agroChemical by ID.
     *
     * @param agroChemicalId the ID of the agrochemical to retrieve.
     * @return the `AgroChemical` entity if found, or `null` otherwise.
     */
    @Override
    public AgroChemical getAgroChemicalById(Long agroChemicalId) throws AgroChemicalNotFoundException{
        AgroChemical agroChemical = agroChemicalRepo.findById(agroChemicalId).orElse(null);
        if(agroChemical==null){
            throw new AgroChemicalNotFoundException("AgroChemical with id "+agroChemicalId+ NOT_FOUND);
        }  
        return agroChemical;      
    }

    /**
     * Updates an existing agroChemical by ID.
     *
     * @param agroChemicalId the ID of the agroChemical to update.
     * @param agroChemical   the updated `AgroChemical` entity details.
     * @return the updated `AgroChemical` entity if found, or null otherwise.
     */
    @Override
    public AgroChemical updateAgroChemical(Long agroChemicalId, AgroChemical agroChemical) throws AgroChemicalNotFoundException{
        AgroChemical existingAgroChemical = agroChemicalRepo.findById(agroChemicalId).orElse(null);
        if(existingAgroChemical==null){
            throw new AgroChemicalNotFoundException("Updation unsuccessful! AgroChemical with id "+agroChemicalId+ NOT_FOUND);
        }
        agroChemical.setAgroChemicalId(agroChemicalId);
        return agroChemicalRepo.save(agroChemical);
    }

    /**
     * Deletes a agroChemical by ID.
     *
     * @param agroChemicalId the ID of the agroChemical to delete.
     * @return the deleted `AgroChemical` entity if found, or `null` otherwise.
     */
    @Override
    public AgroChemical deleteAgroChemical(Long agroChemicalId) throws AgroChemicalNotFoundException{
        AgroChemical existingAgroChemical = agroChemicalRepo.findById(agroChemicalId).orElse(null);
        if(existingAgroChemical == null){
            throw new AgroChemicalNotFoundException("Deletion unsuccessful! AgroChemical with id "+agroChemicalId+ NOT_FOUND);
        }
        agroChemicalRepo.deleteById(agroChemicalId);
        return existingAgroChemical;
    }

}
