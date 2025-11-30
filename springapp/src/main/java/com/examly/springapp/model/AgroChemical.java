package com.examly.springapp.model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@Entity
/**
 * @author Team AgroVisionaries
 * Entity class representing a agroChemical.
 */
@Table(name="agro_chemical")
public class AgroChemical {
    /**
     * Unique identifier for a agroChemical.
     * Annotated with `@Id` to denote the primary key.
     * Uses `@GeneratedValue` with the strategy `GenerationType.AUTO` for automatic ID generation.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long agroChemicalId;
    private String name;
    private String brand;
    private String category;
    private String description;
    private String unit;
    private double pricePerUnit;
    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String image;


    public AgroChemical() {
    }

    public AgroChemical(String name, String brand, String category, String description, String unit, double pricePerUnit, String image) {
        this.name = name;
        this.brand = brand;
        this.category = category;
        this.description = description;
        this.unit = unit;
        this.pricePerUnit = pricePerUnit;
        this.image = image;
    }



    public Long getAgroChemicalId() {
        return agroChemicalId;
    }



    public void setAgroChemicalId(Long agroChemicalId) {
        this.agroChemicalId = agroChemicalId;
    }



    public String getName() {
        return name;
    }



    public void setName(String name) {
        this.name = name;
    }



    public String getBrand() {
        return brand;
    }



    public void setBrand(String brand) {
        this.brand = brand;
    }



    public String getCategory() {
        return category;
    }



    public void setCategory(String category) {
        this.category = category;
    }



    public String getDescription() {
        return description;
    }



    public void setDescription(String description) {
        this.description = description;
    }



    public String getUnit() {
        return unit;
    }



    public void setUnit(String unit) {
        this.unit = unit;
    }



    public double getPricePerUnit() {
        return pricePerUnit;
    }



    public void setPricePerUnit(double pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }



    public String getImage() {
        return image;
    }



    public void setImage(String image) {
        this.image = image;
    }
    

}