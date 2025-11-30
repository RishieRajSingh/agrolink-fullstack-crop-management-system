package com.examly.springapp.service;
import java.util.List;
import org.springframework.stereotype.Service;
import com.examly.springapp.exception.CropNotFoundException;
import com.examly.springapp.exception.EntityAlreadyExistsException;
import com.examly.springapp.model.Crop;
import com.examly.springapp.model.CropDTO;
import com.examly.springapp.model.User;
import com.examly.springapp.repository.CropRepo;
import com.examly.springapp.repository.UserRepo;

import jakarta.persistence.EntityNotFoundException;
/* 
 * Annotated with `@Service` to indicate that it's a Spring service.
 */
@Service
public class CropServiceImpl implements CropService {

    private final CropRepo cropRepo;
    private final UserRepo userRepo;
    private static final String NOT_FOUND = " not found.";

    public CropServiceImpl(CropRepo cropRepo, UserRepo userRepo) {
        this.cropRepo = cropRepo;
        this.userRepo = userRepo;
    }

    /**
     * Adds a new crop to the repository.
     *
     * @param cropRequest the crop to be added.
     * @return the added crop.
     * @throws EntityAlreadyExistsException if a crop with the same ID already exists.
     */
    @Override
    public Crop addCrop(Crop cropRequest) throws EntityNotFoundException, EntityAlreadyExistsException {
        if(cropRepo.existsByCropName(cropRequest.getCropName())) {
            throw new EntityAlreadyExistsException("Crop with Name: "+cropRequest.getCropName()+" already exists.");
        }
        return cropRepo.save(cropRequest);
    }
    

    /**
     * Retrieves all crops for a specific user.
     *
     * @param userId the ID of the user whose crops are to be retrieved.
     * @return a list of crops for the specified user.
     * @throws EntityNotFoundException if no crops are found for the specified user.
     */
    @Override
    public List<Crop> viewAllCrops(int userId) throws CropNotFoundException {
        List<Crop> crops = cropRepo.getByUserId(userId);
        if(crops.isEmpty()) {
            throw new CropNotFoundException("Crop with user ID "+userId+NOT_FOUND);
        }
        return crops;
    }

    /**
     * Retrieves a crop by its ID.
     *
     * @param cropId the ID of the crop to be retrieved.
     * @return the retrieved crop.
     * @throws EntityNotFoundException if the crop with the specified ID is not found.
     */
    @Override
    public Crop viewCropById(Long cropId) throws CropNotFoundException {
        if (!cropRepo.existsById(cropId)) {
            throw new CropNotFoundException("Crop with ID: " + cropId + NOT_FOUND);
        }
        return cropRepo.findById(cropId).orElse(null); // Retrieve and return the crop
    }

    /**
     * Updates an existing crop.
     *
     * @param cropId the ID of the crop to be updated.
     * @param newCrop the new crop data.
     * @return the updated crop.
     * @throws EntityNotFoundException if the crop with the specified ID is not found.
     */
    @Override
    public Crop editCropById(Long cropId, Crop newCrop) throws EntityNotFoundException {
        if (!cropRepo.existsById(cropId)) {
            throw new EntityNotFoundException("Updation unsuccessful! Crop with ID: " + cropId + NOT_FOUND);
        }
        Crop crop = cropRepo.findById(cropId).orElse(null);
        if (crop == null) return null;
        crop.setCropName(newCrop.getCropName());
        crop.setCropType(newCrop.getCropType());
        crop.setDescription(newCrop.getDescription());
        crop.setPlantingDate(newCrop.getPlantingDate());
        return cropRepo.save(crop);
    }

    /**
     * Deletes a crop by its ID.
     *
     * @param cropId the ID of the crop to be deleted.
     * @return true if the crop was successfully deleted.
     * @throws EntityNotFoundException if the crop with the specified ID is not found.
     */
    @Override
    public boolean deleteCropById(Long cropId) throws EntityNotFoundException {
        if (!cropRepo.existsById(cropId)) {
            throw new EntityNotFoundException("Deletion unsuccessful! Crop with ID: " + cropId + NOT_FOUND);
        }
        cropRepo.deleteById(cropId);
        return true;
    }

    public CropDTO convertToDTO(Crop crop) {
        return new CropDTO(
            crop.getCropId(),
            crop.getCropName(),
            crop.getCropType(),
            crop.getDescription(),
            crop.getPlantingDate(),
            crop.getUser().getUserId()
        );
    }

    public Crop convertToEntity(CropDTO cropDTO) {
        Crop crop = new Crop();
        crop.setCropId(cropDTO.getCropId());
        crop.setCropName(cropDTO.getCropName());
        crop.setCropType(cropDTO.getCropType());
        crop.setDescription(cropDTO.getDescription());
        crop.setPlantingDate(cropDTO.getPlantingDate());
        
        User user = userRepo.findByUserId(cropDTO.getUserId());
        crop.setUser(user);
        
        return crop;
    }

}
