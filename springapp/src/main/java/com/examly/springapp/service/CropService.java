package com.examly.springapp.service;
import java.util.List;
import com.examly.springapp.exception.CropNotFoundException;
import com.examly.springapp.exception.EntityAlreadyExistsException;
import com.examly.springapp.model.Crop;
import com.examly.springapp.model.CropDTO;

import jakarta.persistence.EntityNotFoundException;

public interface CropService {

    Crop addCrop(Crop cropRequest) throws EntityAlreadyExistsException, EntityNotFoundException;

    List<Crop> viewAllCrops(int userId) throws CropNotFoundException;

    Crop viewCropById(Long cropId) throws CropNotFoundException;

    Crop editCropById(Long cropId, Crop newCrop) throws CropNotFoundException;

    boolean deleteCropById(Long cropId) throws CropNotFoundException;

    CropDTO convertToDTO(Crop crop);

    Crop convertToEntity(CropDTO cropDTO);

}
