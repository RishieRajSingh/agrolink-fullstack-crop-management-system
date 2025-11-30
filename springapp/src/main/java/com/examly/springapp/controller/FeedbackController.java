/**
 * Controller class for managing organizer-related endpoints.
 * @author Jasmitha
 * Annotated with `@RestController` to indicate a RESTful controller.
 */
package com.examly.springapp.controller;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.examly.springapp.exception.EntityAlreadyExistsException;
import com.examly.springapp.exception.FeedbackNotFoundException;
import com.examly.springapp.model.Feedback;
import com.examly.springapp.model.FeedbackRequestDto;
import com.examly.springapp.service.FeedbackServiceImpl;
/**
 * Controller class for managing organizer-related endpoints.
 * @author Jasmitha
 * Annotated with `@RestController` to indicate a RESTful controller.
 */
@RestController
@RequestMapping("/api")
public class FeedbackController {
    private final FeedbackServiceImpl feedbackService;
     /**
     * Constructor to inject the FeedbackServiceImpl bean.
     */
     public FeedbackController(FeedbackServiceImpl feedbackServiceImpl){
        this.feedbackService=feedbackServiceImpl;
        
     }
     /**
      * Retrieves a list of all feedbacks.
      * @return ResponseEntity with the list of feedbacks and HTTP status 200
      */
    @GetMapping("/feedback")
    @PreAuthorize("hasRole('ROLE_SELLER')")
    public ResponseEntity<List<Feedback>> getAllFeedback(){
        return ResponseEntity.status(200).body(feedbackService.getAllFeedback());
    }
    
    
    /**
    * Adds a new feedback.
    * @param feedback The feedback object to be added
    * @return ResponseEntity with the created feedback and HTTP status 201
    */
    @PostMapping("/feedback")
    @PreAuthorize("hasAuthority('ROLE_FARMER')")
    public ResponseEntity<Feedback> addFeedback(@RequestBody FeedbackRequestDto feedbackRequest) throws EntityAlreadyExistsException{
        return ResponseEntity.status(201).body(feedbackService.addFeedBack(feedbackRequest));
    }

    /**
     * Retrieves all user feedbacks
     * @return ResponseEntity with the list of feedbacks for the user and HTTP status 200
     */
    @GetMapping("/feedback/user/{userId}")
    @PreAuthorize("hasAuthority('ROLE_FARMER')")
    public ResponseEntity<List<Feedback>> getAllUserFeedbacks(@PathVariable int userId){
        return ResponseEntity.status(200).body(feedbackService.getAllUserFeedbacks(userId));
    }

    /**
     * Deletes a feedback by ID.
     * @param feedbackId The ID of the feedback to be deleted
     * @return ResponseEntity with HTTP status 200 if successful, 404 if not found
     */
    @DeleteMapping("/feedback/{feedbackId}")
    @PreAuthorize("hasAuthority('ROLE_FARMER')")
    public ResponseEntity<Feedback> deleteFeedbackById(@PathVariable int feedbackId) throws FeedbackNotFoundException{
        Feedback feedback = feedbackService.deleteFeedbackById(feedbackId);
        return ResponseEntity.status(200).body(feedback);
    } 
}

