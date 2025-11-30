package com.examly.springapp.service;
import java.util.List;
import org.springframework.stereotype.Service;
import com.examly.springapp.exception.CropNotFoundException;
import com.examly.springapp.exception.RequestNotFoundException;
import com.examly.springapp.model.Request;
import com.examly.springapp.model.RequestDTO;
import com.examly.springapp.model.RequestResponseDTO;
import com.examly.springapp.repository.AgroChemicalRepo;
import com.examly.springapp.repository.CropRepo;
import com.examly.springapp.repository.RequestRepo;
import com.examly.springapp.repository.UserRepo;
import jakarta.persistence.EntityNotFoundException;

@Service
public class RequestServiceImpl implements RequestService {

    private final RequestRepo requestRepo;
    private final AgroChemicalRepo agroChemicalRepo;
    private final CropRepo cropRepo;
    private final UserRepo userRepo;
    private static final String NOT_FOUND = " not found.";

    public RequestServiceImpl(RequestRepo requestRepo, AgroChemicalRepo agroChemicalRepo, CropRepo cropRepo, UserRepo userRepo) {
        this.requestRepo = requestRepo;
        this.agroChemicalRepo = agroChemicalRepo;
        this.cropRepo = cropRepo;
        this.userRepo = userRepo;
    }

    @Override
    public Request addRequest(Request request) throws EntityNotFoundException, CropNotFoundException {
        if(request.getUser().getUserId() != request.getCrop().getUser().getUserId()) {
            throw new EntityNotFoundException("User mismatch");
        }
        if (!agroChemicalRepo.existsById(request.getAgroChemical().getAgroChemicalId())) {
            throw new EntityNotFoundException("AgroChemical ID: " + request.getAgroChemical().getAgroChemicalId() + NOT_FOUND);
        }
        if (!userRepo.existsById(request.getUser().getUserId())) {
            throw new EntityNotFoundException("User ID: " + request.getUser().getUserId() + NOT_FOUND);
        }
        if (cropRepo.getByUserAndCropId(request.getCrop().getUser().getUserId(), request.getCrop().getCropId()) == null) {
            throw new CropNotFoundException("Crop ID " + request.getCrop().getCropId() + " with appropriate userId" + NOT_FOUND);
        }
        return requestRepo.save(request);
    }

    @Override
    public List<Request> viewMyRequest(int userId) throws RequestNotFoundException {
        List<Request> myRequests = requestRepo.findAllByUserId(userId);
        if(myRequests.isEmpty()) {
            throw new RequestNotFoundException("Requests with user ID: " + userId + NOT_FOUND);
        }
        return myRequests;
    }

    @Override
    public Request viewMyRequestById(long id) throws RequestNotFoundException {
        if(!requestRepo.existsById(id)) {
            throw new RequestNotFoundException("Request ID " + id + NOT_FOUND);
        }
        return requestRepo.findById(id).orElse(null);
    }

    @Override
    public boolean deleteRequest(long id) throws RequestNotFoundException {
        if(!requestRepo.existsById(id)) {
            throw new RequestNotFoundException("Deletion unsuccessful: Request ID " + id + NOT_FOUND);
        }
        requestRepo.deleteById(id);
        return true;
    }

    @Override
    public List<Request> viewAllRequest() throws RequestNotFoundException {
        List<Request> requests = requestRepo.findAll();
        if(requests.isEmpty()) {
            throw new RequestNotFoundException("Requests not found.");
        }
        return requests;
    }

    @Override
    public Request editRequest(long id, Request newRequest) throws RequestNotFoundException {
        if(!requestRepo.existsById(id)) {
            throw new RequestNotFoundException("Request ID " + id + NOT_FOUND);
        }
        Request oldRequest = requestRepo.findById(id).orElse(null);
        if(oldRequest == null) {
            return null;
        }
        oldRequest.setQuantity(newRequest.getQuantity());
        oldRequest.setStatus(newRequest.getStatus());
        oldRequest.setRequestDate(newRequest.getRequestDate());
        return requestRepo.save(oldRequest);
    }

    public RequestDTO convertToDTO(Request request) {
        return new RequestDTO(
            request.getAgroChemical().getAgroChemicalId(),
            request.getUser().getUserId(),
            request.getCrop().getCropId(),
            request.getQuantity(),
            request.getStatus(),
            request.getRequestDate()
        );
    }

    public RequestResponseDTO convertToResponseDTO(Request request) {
        return new RequestResponseDTO(
            request.getRequestId(),
            request.getAgroChemical().getName(),
            request.getAgroChemical().getBrand(),
            request.getAgroChemical().getCategory(),
            request.getQuantity(),
            request.getRequestDate(),
            request.getCrop().getCropName(),
            request.getStatus()
        );
    }

    public Request convertToEntity(RequestDTO requestDTO) {
        Request request = new Request();
        request.setAgroChemical(agroChemicalRepo.findById(requestDTO.getAgroChemicalId()).orElse(null));
        request.setUser(userRepo.findByUserId(requestDTO.getUserId()));
        request.setCrop(cropRepo.findById(requestDTO.getCropId()).orElse(null));
        request.setQuantity(requestDTO.getQuantity());
        request.setStatus(requestDTO.getStatus());
        request.setRequestDate(requestDTO.getRequestDate());
        return request;
    }
}
