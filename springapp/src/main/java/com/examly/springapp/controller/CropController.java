package com.examly.springapp.controller;
import java.util.List;
import java.util.stream.Collectors;
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
import com.examly.springapp.exception.CropNotFoundException;
import com.examly.springapp.exception.EntityAlreadyExistsException;
import com.examly.springapp.model.Crop;
import com.examly.springapp.model.CropDTO;
import com.examly.springapp.service.CropServiceImpl;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;

@RequestMapping("/api/crop")
@RestController
public class CropController {

    /**
     * Service implementation for crop operations.
     * Annotated with `@Autowired` to inject the `CropServiceImpl` bean.
     */
    private final CropServiceImpl cropService;

    public CropController(CropServiceImpl cropServiceImpl) {
        this.cropService = cropServiceImpl;
    }

    /**
     * Adds a crop.
     *
     * @param cropRequest the crop details to be added.
     * @return a `ResponseEntity` with the added crop if the operation is
     *         successful, or an error message otherwise.
     * @throws EntityExistsException if a crop with the same ID already exists.
     */
    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_FARMER')")
    public ResponseEntity<CropDTO> addCrop(@RequestBody CropDTO cropDTO) throws EntityAlreadyExistsException {
        Crop crop = cropService.addCrop(cropService.convertToEntity(cropDTO));
        return ResponseEntity.status(201).body(cropService.convertToDTO(crop));
    }

    /**
     * Retrieves a list of crops for a specific user.
     *
     * @param userId the ID of the user whose crops are to be retrieved.
     * @return a `ResponseEntity` containing a list of crops for the specified user.
     * @throws EntityNotFoundException if no crops are found for the specified user.
     */
    @GetMapping("/user/{userId}")
    @PreAuthorize("hasAuthority('ROLE_FARMER')")
    public ResponseEntity<List<CropDTO>> viewAllCrops(@PathVariable int userId) throws CropNotFoundException {
        List<Crop> crops = cropService.viewAllCrops(userId);
        List<CropDTO> cropDTOs = crops.stream().map(cropService::convertToDTO).collect(Collectors.toList());
        return ResponseEntity.status(200).body(cropDTOs);
    }

    /**
     * Retrieves a crop by its ID.
     *
     * @param id the ID of the crop to be retrieved.
     * @return a `ResponseEntity` containing the crop with the specified ID.
     * @throws EntityNotFoundException if the crop with the specified ID is not found.
     */
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_FARMER')")
    public ResponseEntity<CropDTO> viewCropById(@PathVariable Long id) throws CropNotFoundException {
        Crop crop = cropService.viewCropById(id);
        return ResponseEntity.status(200).body(cropService.convertToDTO(crop));
    }

    /**
     * Updates a crop.
     *
     * @param cropId  the ID of the crop to be updated.
     * @param newCrop the new crop details.
     * @return a `ResponseEntity` with the updated crop if the operation is
     *         successful, or an error message otherwise.
     * @throws EntityNotFoundException if the crop with the specified ID is not found.
     */
    
    @PutMapping("/{cropId}")
    @PreAuthorize("hasAuthority('ROLE_FARMER')")
    public ResponseEntity<CropDTO> editCropById(@PathVariable Long cropId, @RequestBody CropDTO newCropDTO)
            throws CropNotFoundException {
            Crop newCrop = cropService.convertToEntity(newCropDTO);
            Crop crop = cropService.editCropById(cropId, newCrop);
            return ResponseEntity.status(200).body(cropService.convertToDTO(crop));
    }

    /**
     * Deletes a crop.
     *
     * @param cropId the ID of the crop to be deleted.
     * @return a `ResponseEntity` with a success message if the operation is
     *         successful, or an error message otherwise.
     * @throws EntityNotFoundException if the crop with the specified ID is not found.
     */
    @DeleteMapping("/{cropId}")
    @PreAuthorize("hasAuthority('ROLE_FARMER')")
    public ResponseEntity<String> deleteCropById(@PathVariable Long cropId) throws CropNotFoundException {
        boolean cropFound = cropService.deleteCropById(cropId);
        if (cropFound) {
            return ResponseEntity.status(200).body("Crop with ID: " + cropId + " deleted successfully");
        }
        return ResponseEntity.status(500).build();
    }
}
