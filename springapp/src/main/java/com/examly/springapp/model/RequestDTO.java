package com.examly.springapp.model;

import java.time.LocalDate;

public class RequestDTO {
    private Long agroChemicalId;
    private int userId;
    private Long cropId;
    private int quantity;
    private String status;
    private LocalDate requestDate;

    public RequestDTO() {
    }

    public RequestDTO(Long agroChemicalId, int userId, Long cropId, int quantity, String status, LocalDate requestDate) {
        this.agroChemicalId = agroChemicalId;
        this.userId = userId;
        this.cropId = cropId;
        this.quantity = quantity;
        this.status = status;
        this.requestDate = requestDate;
    }
    
    public Long getAgroChemicalId() {
        return agroChemicalId;
    }

    public void setAgroChemicalId(Long agroChemicalId) {
        this.agroChemicalId = agroChemicalId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Long getCropId() {
        return cropId;
    }

    public void setCropId(Long cropId) {
        this.cropId = cropId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(LocalDate requestDate) {
        this.requestDate = requestDate;
    }
}
