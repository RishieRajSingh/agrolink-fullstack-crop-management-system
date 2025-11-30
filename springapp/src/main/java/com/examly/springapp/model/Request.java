package com.examly.springapp.model;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Request {
    /**
     * Unique identifier for a request.
     * Annotated with `@Id` to denote the primary key.
     * Uses `@GeneratedValue` with the strategy `GenerationType.AUTO` for automatic ID generation.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long requestId;
    private int quantity;
    private String status;
    private LocalDate requestDate;
    /**

     * AgroChemical associated with the request.
     * 
     * Annotated with `@ManyToOne` to denote the many-to-one relationship with the `AgroChemical` entity.
     * Uses `@JoinColumn` to specify the foreign key column name in the database.
     */
    @ManyToOne
    @JoinColumn(name = "agroChemicalId")
    private AgroChemical agroChemical;
    /**

     * User associated with the request.
     * 
     * Annotated with `@ManyToOne` to denote the many-to-one relationship with the `User` entity.
     * Uses `@JoinColumn` to specify the foreign key column name in the database.
     */
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;
    /**

     * Crop associated with the request.
     * 
     * Annotated with `@ManyToOne` to denote the many-to-one relationship with the `Crop` entity.
     * Uses `@JoinColumn` to specify the foreign key column name in the database.
     */
    @ManyToOne
    @JoinColumn(name = "cropId")
    private Crop crop;

    public Request() {
    }

    public Request(long requestId, int quantity, String status, LocalDate requestDate, AgroChemical agroChemical,
            User user, Crop crop) {
        this.requestId = requestId;
        this.quantity = quantity;
        this.status = status;
        this.requestDate = requestDate;
        this.agroChemical = agroChemical;
        this.user = user;
        this.crop = crop;
    }

    public long getRequestId() {
        return requestId;
    }

    public void setRequestId(long requestId) {
        this.requestId = requestId;
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

    public AgroChemical getAgroChemical() {
        return agroChemical;
    }

    public void setAgroChemical(AgroChemical agroChemical) {
        this.agroChemical = agroChemical;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Crop getCrop() {
        return crop;
    }

    public void setCrop(Crop crop) {
        this.crop = crop;
    }

}
