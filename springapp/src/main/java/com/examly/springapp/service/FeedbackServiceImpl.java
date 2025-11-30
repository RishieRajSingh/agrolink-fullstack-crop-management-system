package com.examly.springapp.service;
import java.util.List;
import org.springframework.stereotype.Service;
import com.examly.springapp.exception.EntityAlreadyExistsException;
import com.examly.springapp.exception.FeedbackNotFoundException;
import com.examly.springapp.model.Feedback;
import com.examly.springapp.model.FeedbackRequestDto;
import com.examly.springapp.model.User;
import com.examly.springapp.repository.FeedbackRepo;
import com.examly.springapp.repository.UserRepo;
@Service
public class FeedbackServiceImpl implements FeedbackService {
    private FeedbackRepo feedbackRepo;
    private UserRepo userRepo;
    /**
     * Constructor to inject the FeedbackRepo bean.
     */
    public FeedbackServiceImpl(FeedbackRepo feedbackRepo, UserRepo userRepo){
        this.feedbackRepo=feedbackRepo;
        this.userRepo = userRepo;
     }
 
    @Override
 
    /**
     * Adds a new feedback.
     * @param feedback The feedback object to be added
     * @return The added feedback
     * @throws EntityExistsException if the feedback already exists
     */
    public Feedback addFeedBack(FeedbackRequestDto feedbackRequest) throws EntityAlreadyExistsException {
        User user = userRepo.findByUserId(feedbackRequest.getUserId());
        if(user == null) return null;
        Feedback feedback = new Feedback();
        feedback.setFeedbackText(feedbackRequest.getFeedbackText());
        feedback.setDate(feedbackRequest.getDate());
        feedback.setUser(user);
        return feedbackRepo.save(feedback);
    }
 
    @Override
 
    /**
     * Retrieves all feedbacks.
     * @return A list of all feedbacks
     * @throws EntityNotFoundException if no feedbacks are found
     */
    public List<Feedback> getAllFeedback() {
        return feedbackRepo.findAll();
    }
 
    @Override
 
    /**
     * Retrieves all user feedbacks.
     * @return A list of feedbacks for the specified user
     */
    public List<Feedback> getAllUserFeedbacks(int id) {
        return feedbackRepo.getAllUserFeedbacks(id);
    }
 
    @Override
 
    /**
     * Deletes a feedback by ID.
     * @param feedbackId The ID of the feedback to be deleted
     * @return The deleted feedback
     * @throws EntityNotFoundException if the feedback is not found
     */
    public Feedback deleteFeedbackById(int feedbackId) throws FeedbackNotFoundException {
        Feedback feedback = feedbackRepo.findById(feedbackId).orElse(null);
        if(feedback == null){
            throw new FeedbackNotFoundException("Feedback with feedback ID "+feedbackId+" not found.");
        }
        feedbackRepo.deleteById(feedbackId);
        return feedback;
    }
}