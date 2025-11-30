package com.examly.springapp.model;
import java.time.LocalDate;

public class CropDTO {
    private long cropId;
    private String cropName;
    private String cropType;
    private String description;
    private LocalDate plantingDate;
    private int userId;

    public CropDTO() {
    }

    public CropDTO(long cropId, String cropName, String cropType, String description, LocalDate plantingDate, int userId) {
        this.cropId = cropId;
        this.cropName = cropName;
        this.cropType = cropType;
        this.description = description;
        this.plantingDate = plantingDate;
        this.userId = userId;
    }

    public long getCropId() {
        return cropId;
    }

    public void setCropId(long cropId) {
        this.cropId = cropId;
    }

    public String getCropName() {
        return cropName;
    }

    public void setCropName(String cropName) {
        this.cropName = cropName;
    }

    public String getCropType() {
        return cropType;
    }

    public void setCropType(String cropType) {
        this.cropType = cropType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getPlantingDate() {
        return plantingDate;
    }

    public void setPlantingDate(LocalDate plantingDate) {
        this.plantingDate = plantingDate;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
