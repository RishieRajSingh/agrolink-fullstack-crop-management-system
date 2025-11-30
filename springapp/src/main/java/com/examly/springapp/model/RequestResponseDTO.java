package com.examly.springapp.model;

import java.time.LocalDate;

public class RequestResponseDTO {
    private long requestId;
    private String agroChemicalName;
    private String brand;
    private String category;
    private int quantity;
    private LocalDate submissionDate;
    private String cropName;
    private String status;

    public RequestResponseDTO() {
    }

    public RequestResponseDTO(long requestId, String agroChemicalName, String brand, String category, int quantity, LocalDate submissionDate, String cropName, String status) {
        this.requestId = requestId;
        this.agroChemicalName = agroChemicalName;
        this.brand = brand;
        this.category = category;
        this.quantity = quantity;
        this.submissionDate = submissionDate;
        this.cropName = cropName;
        this.status = status;
    }

    
    public long getRequestId() {
        return requestId;
    }

    public void setRequestId(long requestId) {
        this.requestId = requestId;
    }

    public String getAgroChemicalName() {
        return agroChemicalName;
    }

    public void setAgroChemicalName(String agroChemicalName) {
        this.agroChemicalName = agroChemicalName;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public LocalDate getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(LocalDate submissionDate) {
        this.submissionDate = submissionDate;
    }

    public String getCropName() {
        return cropName;
    }

    public void setCropName(String cropName) {
        this.cropName = cropName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
