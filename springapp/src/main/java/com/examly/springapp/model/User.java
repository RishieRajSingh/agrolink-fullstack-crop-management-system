package com.examly.springapp.model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * Represents a User Entity with various attributes like userId, email, password, username, mobileNumber, userRole.
 * @author Agrivisionaries
 * Annotated with `@Entity`.
 */
@Entity
public class User {
    /**
     * Unique identifier for a user.
     * Annotated with `@Id` to denote the primary key.
     * Uses `@GeneratedValue` with the strategy `GenerationType.IDENTITY` for ID generation.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    @Column(nullable = false, unique = true)
    private String email;

    private String password;
    
    @Column(nullable = false, unique = true)
    private String username;
    private String mobileNumber;

    @Column(nullable = false)
    private String userRole;
    
    //Default Constructor
    public User() {
    }
    /**
     * Parameterized constructor to initialize the user with given attributes.
     * 
     * @param userId unique identifier of the user.
     * @param email email of the user.
     * @param password password of the user.
     * @param username username of the user.
     * @param mobileNumber mobile number of the user.
     * @param userRole role of the user.
     */
    public User(int userId, String email, String password, String username, String mobileNumber, String userRole) {
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.username = username;
        this.mobileNumber = mobileNumber;
        this.userRole = userRole;
    }
    //getters and setters for userId
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    //getters and setters for email
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    //getters and setters for password
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
     //getters and setters for username
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
     //getters and setters for mobile number
    public String getMobileNumber() {
        return mobileNumber;
    }
    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
     //getters and setters for userrole
    public String getUserRole() {
        return userRole;
    }
    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }
    
    
}