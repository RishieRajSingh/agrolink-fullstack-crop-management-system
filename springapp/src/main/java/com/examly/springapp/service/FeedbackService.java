package com.examly.springapp.service;
import java.util.List;
import com.examly.springapp.exception.EntityAlreadyExistsException;
import com.examly.springapp.exception.FeedbackNotFoundException;
import com.examly.springapp.model.Feedback;
import com.examly.springapp.model.FeedbackRequestDto;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
 
/**
 * Service interface for managing feedback-related operations.
 * Provides methods for adding, retrieving, and deleting feedback.
 */
public interface FeedbackService {
    /**
     * Adds a new feedback.
     * @param feedback The feedback object to be added
     * @return The added feedback
     * @throws EntityExistsException if the feedback already exists
     */
    Feedback addFeedBack(FeedbackRequestDto feedback) throws EntityAlreadyExistsException;
    /**
     * Retrieves all feedbacks.
     * @return A list of all feedbacks
     * @throws EntityNotFoundException if no feedbacks are found
     */
    List<Feedback> getAllFeedback();
 
    /**
     * Retrieves all user feedbacks.
     * @return A list of feedbacks for the specified user
     */
    List<Feedback> getAllUserFeedbacks(int id);
    /**
     * Deletes a feedback by ID.
     * @param feedbackId The ID of the feedback to be deleted
     * @return The deleted feedback
     * @throws EntityNotFoundException if the feedback is not found
     */
    Feedback deleteFeedbackById(int feedbackId) throws FeedbackNotFoundException;
}
 