package com.examly.springapp.model;
import java.time.LocalDate;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

/**
 * Entity class representing feedback.
 * Mapped to the "Feedback" table in the database.
 */
@Entity
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    /**
     * Unique identifier for the feedback.
     * Auto-generated value.
     */
    private int feedbackId;
    private String feedbackText;
    private LocalDate date;

    /**
     * User who submitted the feedback.
     * Foreign key to the User entity.
     */
    @ManyToOne
    @JoinColumn(name="userId")
    private User user;

    public Feedback() {
    }
    /**
     * Parameterized constructor.
     * @param feedbackId Unique identifier for the feedback
     * @param feedbackText Text content of the feedback
     * @param date Date when the feedback was submitted
     * @param user User who submitted the feedback
     */
    public Feedback(int feedbackId, String feedbackText, LocalDate date, User user) {
        this.feedbackId = feedbackId;
        this.feedbackText = feedbackText;
        this.date = date;
        this.user = user;
    }
    // Getter and setter methods
    public int getFeedbackId() {
        return feedbackId;
    }
    public void setFeedbackId(int feedbackId) {
        this.feedbackId = feedbackId;
    }
    public String getFeedbackText() {
        return feedbackText;
    }
    public void setFeedbackText(String feedbackText) {
        this.feedbackText = feedbackText;
    }
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    


}
