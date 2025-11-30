package com.examly.springapp.controller;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.examly.springapp.exception.CropNotFoundException;
import com.examly.springapp.exception.RequestNotFoundException;
import com.examly.springapp.model.Request;
import com.examly.springapp.model.RequestDTO;
import com.examly.springapp.model.RequestResponseDTO;
import com.examly.springapp.service.RequestServiceImpl;
import jakarta.persistence.EntityNotFoundException;

@RequestMapping("/api/request")
@RestController
public class RequestController {

    private final RequestServiceImpl requestService;

    public RequestController(RequestServiceImpl requestService) {
        this.requestService = requestService;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_FARMER')")
    public ResponseEntity<Request> addRequest(@RequestBody RequestDTO requestDTO) throws EntityNotFoundException, CropNotFoundException {
        Request request = requestService.addRequest(requestService.convertToEntity(requestDTO));
        return ResponseEntity.status(201).body(request);
    }

    @GetMapping("/user/{userId}")
    @PreAuthorize("hasAuthority('ROLE_FARMER')")
    public ResponseEntity<List<RequestResponseDTO>> viewMyRequest(@PathVariable int  userId) throws RequestNotFoundException {
        List<Request> requests = requestService.viewMyRequest(userId);
        List<RequestResponseDTO> requestResponseDTO = requests.stream().map(requestService::convertToResponseDTO).collect(Collectors.toList());
        return ResponseEntity.status(200).body(requestResponseDTO);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_FARMER')")
    public ResponseEntity<Request> viewRequestById(@PathVariable Long id) throws RequestNotFoundException {
        Request request = requestService.viewMyRequestById(id);
        return ResponseEntity.status(200).body(request);
    }

    @PutMapping("/{requestId}")
    @PreAuthorize("hasAuthority('ROLE_FARMER')")
    public ResponseEntity<RequestDTO> editRequestById(@PathVariable Long requestId, @RequestBody RequestDTO newRequestDTO)
            throws RequestNotFoundException {
            Request newRequest = requestService.convertToEntity(newRequestDTO);
            Request request = requestService.editRequest(requestId, newRequest);
            return ResponseEntity.status(200).body(requestService.convertToDTO(request));
    }

    @DeleteMapping("/{requestId}")
    @PreAuthorize("hasAuthority('ROLE_FARMER')")
    public ResponseEntity<String> deleteRequestById(@PathVariable Long requestId) throws RequestNotFoundException {
        boolean requestFound = requestService.deleteRequest(requestId);
        if (requestFound) {
            return ResponseEntity.status(200).body("Request with ID: " + requestId + " deleted successfully");
        }
        return ResponseEntity.status(500).build();
    }


    /**
     * Retrieves all requests.
     * @return a `ResponseEntity` containing a list of all `Request` entities.
     */
    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_SELLER')")
    public ResponseEntity <List<Request>> viewAllRequest() throws RequestNotFoundException{
        List<Request> requests = requestService.viewAllRequest();
        if(requests.isEmpty()){
            return ResponseEntity.status(500).build();
        }
        return ResponseEntity.status(200).body(requests);
    }
}
