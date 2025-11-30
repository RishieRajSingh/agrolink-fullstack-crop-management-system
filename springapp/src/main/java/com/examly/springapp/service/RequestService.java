package com.examly.springapp.service;
import java.util.List;
import com.examly.springapp.exception.CropNotFoundException;
import com.examly.springapp.exception.RequestNotFoundException;
import com.examly.springapp.model.Request;
import com.examly.springapp.model.RequestDTO;
import com.examly.springapp.model.RequestResponseDTO;
import jakarta.persistence.EntityNotFoundException;

public interface RequestService {

    Request addRequest(Request request) throws EntityNotFoundException,CropNotFoundException;
    
    List<Request> viewMyRequest(int userId) throws RequestNotFoundException;

    Request viewMyRequestById(long id) throws RequestNotFoundException;

    boolean deleteRequest(long id) throws RequestNotFoundException;

    List<Request> viewAllRequest() throws RequestNotFoundException;

    Request editRequest(long id, Request request) throws RequestNotFoundException;

    RequestDTO convertToDTO(Request request);

    RequestResponseDTO convertToResponseDTO(Request request);

    Request convertToEntity(RequestDTO requestDTO);
}
