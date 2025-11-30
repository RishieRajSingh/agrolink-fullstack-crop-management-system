package com.examly.springapp.controller;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.examly.springapp.exception.AgroChemicalNotFoundException;
import com.examly.springapp.exception.DuplicateAgroChemicalException;
import com.examly.springapp.model.AgroChemical;
import com.examly.springapp.service.AgroChemicalServiceImpl;

/**
 * Controller class for managing agroChemical-related endpoints.
 * @author Team AgroVisionaries
 * Annotated with `@RestController` to indicate a RESTful controller.
 * Mapped to the `/api/agrochemical` base path using `@RequestMapping`.
 */
@RestController
@RequestMapping("/api/agrochemical")
public class AgroChemicalController {
    private final AgroChemicalServiceImpl agroChemicalServiceImpl;

    /**
     * Service implementation for agroChemical operations.
     * Annotated with `@Autowired` to inject the `AgroChemicalServiceImpl` bean.
     */
    public AgroChemicalController(AgroChemicalServiceImpl agroChemicalServiceImpl){
        this.agroChemicalServiceImpl = agroChemicalServiceImpl;
    }
    
    /**
     * Adds a new agroChemical.
     * @param agroChemicalRequest                                                                                ./,.,bnbne `AgroChemical` entity provided in the request body.
     * @return a `ResponseEntity` containing the saved `agroChemical` entity or an error status.
     */
    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_SELLER')")
    public ResponseEntity<AgroChemical> addAgroChemical(@RequestBody AgroChemical agroChemicalRequest) throws DuplicateAgroChemicalException {
        AgroChemical agroChemical = agroChemicalServiceImpl.addAgroChemical(agroChemicalRequest);
            return ResponseEntity.status(201).body(agroChemical);
        }

    
    /**
     * Retrieves all agroChemicals.
     * @return a `ResponseEntity` containing a list of all `AgroChemicals` entities.
     */
    @GetMapping
    public ResponseEntity<List<AgroChemical>> getAllAgroChemical() {
        List<AgroChemical> agroChemicals = agroChemicalServiceImpl.getAllAgroChemical();
        return ResponseEntity.status(200).body(agroChemicals);
    }

    /**
     * Retrieves an agroChemical by ID.
     * @param id the ID of the agroChemical to be retrieved.
     * @return a `ResponseEntity` containing the `AgroChemical` entity or an error status.
     */
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_SELLER')")
    public ResponseEntity<AgroChemical> getAgroChemicalById(@PathVariable Long id) throws AgroChemicalNotFoundException{
        AgroChemical agroChemical = agroChemicalServiceImpl.getAgroChemicalById(id);
        return ResponseEntity.status(200).body(agroChemical);
    }

    /**
     * Updates a agroChemical from the current agroChemicals.
     *
     * @param id the ID of the agroChemical by Id.
     * @return a `ResponseEntity` containing the updated `AgroChemical` entity or an error status.
     */
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_SELLER')")
    public ResponseEntity<AgroChemical> updateAgroChemical(@PathVariable Long id, @RequestBody AgroChemical agroChemicalRequest) throws AgroChemicalNotFoundException{
        AgroChemical agroChemical = agroChemicalServiceImpl.updateAgroChemical(id, agroChemicalRequest);
        return ResponseEntity.status(200).body(agroChemical);
    }
    
    /**
     * Deletes a agroChemical by ID.
     *
     * @param id the ID of the agroChemical to be deleted.
     * @return a `ResponseEntity` containing the deleted `AgroChemical` entity or an error status.
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_SELLER')")
    public ResponseEntity<AgroChemical> deleteAgroChemical(@PathVariable Long id) throws AgroChemicalNotFoundException{
        AgroChemical agroChemical = agroChemicalServiceImpl.deleteAgroChemical(id);
        return ResponseEntity.status(200).body(agroChemical);
    }   

}
