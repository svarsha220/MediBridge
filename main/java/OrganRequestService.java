package net.organ.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrganRequestService {

    @Autowired
    private OrganRequestRepository organRequestRepository;

    // Save organ request (Create)
    public OrganRequest saveRequest(OrganRequest request) {
        return organRequestRepository.save(request);
    }

    // Get all organ requests (Read)
    public List<OrganRequest> getAllRequests() {
        return organRequestRepository.findAll();
    }

    // Get organ request by id (Read)
    public OrganRequest getRequestById(Long id) {
        return organRequestRepository.findById(id).orElse(null);
    }

    // Update organ request
    public OrganRequest updateRequest(Long id, OrganRequest updatedRequest) {
        // Find existing request
        OrganRequest existingRequest = organRequestRepository.findById(id).orElse(null);

        // If the request exists, update its details
        if (existingRequest != null) {
            existingRequest.setOrganName(updatedRequest.getOrganName());
            existingRequest.setRequesterName(updatedRequest.getRequesterName());
            existingRequest.setBloodGroup(updatedRequest.getBloodGroup());
            existingRequest.setRequesterAge(updatedRequest.getRequesterAge());
            return organRequestRepository.save(existingRequest);
        }

        // Return null if the request doesn't exist
        return null;
    }

    // Delete organ request
    public void deleteRequest(Long id) {
        organRequestRepository.deleteById(id);
    }
}
