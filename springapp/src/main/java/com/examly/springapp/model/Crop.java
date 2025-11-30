package com.examly.springapp.model;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

/**
 * Entity class representing a Crop.
 */
@Entity
public class Crop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long cropId;

    @Column(unique = true)
    private String cropName;
    private String cropType;
    private String description;
    private LocalDate plantingDate;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    /**
     * Default constructor.
     */
    public Crop() {
    }

    /**
     * Parameterized constructor for creating a Crop.
     *
     * @param cropId      the ID of the crop.
     * @param cropName    the name of the crop.
     * @param cropType    the type of the crop.
     * @param description a brief description of the crop.
     * @param plantingDate the date when the crop was planted.
     * @param user        the user associated with the crop.
     */
    public Crop(long cropId, String cropName, String cropType, String description, LocalDate plantingDate, User user) {
        this.cropId = cropId;
        this.cropName = cropName;
        this.cropType = cropType;
        this.description = description;
        this.plantingDate = plantingDate;
        this.user = user;
    }

    /**
     * Gets the ID of the crop.
     *
     * @return the crop ID.
     */
    public long getCropId() {
        return cropId;
    }

    /**
     * Sets the ID of the crop.
     *
     * @param cropId the crop ID.
     */
    public void setCropId(long cropId) {
        this.cropId = cropId;
    }

    /**
     * Gets the name of the crop.
     *
     * @return the crop name.
     */
    public String getCropName() {
        return cropName;
    }

    /**
     * Sets the name of the crop.
     *
     * @param cropName the crop name.
     */
    public void setCropName(String cropName) {
        this.cropName = cropName;
    }

    /**
     * Gets the type of the crop.
     *
     * @return the crop type.
     */
    public String getCropType() {
        return cropType;
    }

    /**
     * Sets the type of the crop.
     *
     * @param cropType the crop type.
     */
    public void setCropType(String cropType) {
        this.cropType = cropType;
    }

    /**
     * Gets the description of the crop.
     *
     * @return the crop description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the crop.
     *
     * @param description the crop description.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the planting date of the crop.
     *
     * @return the planting date.
     */
    public LocalDate getPlantingDate() {
        return plantingDate;
    }

    /**
     * Sets the planting date of the crop.
     *
     * @param plantingDate the planting date.
     */
    public void setPlantingDate(LocalDate plantingDate) {
        this.plantingDate = plantingDate;
    }

    /**
     * Gets the user associated with the crop.
     *
     * @return the user.
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets the user associated with the crop.
     *
     * @param user the user.
     */
    public void setUser(User user) {
        this.user = user;
    }
}
